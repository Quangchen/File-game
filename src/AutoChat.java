import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

public final class AutoChat {

    private static final String STORE_NAME = "AutoChatCfgV1";
    public static final int CHANNEL_PUBLIC = 0;
    public static final int CHANNEL_WORLD = 1;
    private static final int MIN_PUBLIC_DELAY_MS = 10000;
    private static final int MIN_WORLD_DELAY_MS = 60000;

    public static boolean enabled = false;
    public static boolean randomMessage = false;
    public static boolean pauseWhenBusy = true;
    public static int channel = CHANNEL_PUBLIC;
    public static int delaySeconds = 60;
    public static String messagesText = "Xin chao";

    private static boolean loaded = false;
    private static String[] messages = new String[0];
    private static int nextIndex = 0;
    private static long nextSendAt = 0L;
    private static long lastBusyPopupAt = 0L;

    private AutoChat() {
    }

    public static void update() {
        try {
            ensureLoaded();
            if (!enabled || messages.length == 0) {
                return;
            }

            long now = System.currentTimeMillis();
            if (nextSendAt == 0L) {
                scheduleNext(now);
                return;
            }

            if (now < nextSendAt) {
                return;
            }

            if (!isReadyToChat()) {
                nextSendAt = now + 3000L;
                return;
            }

            if (sendCurrentMessage(false)) {
                scheduleNext(now);
            } else {
                nextSendAt = now + getMinDelayMs();
            }
        } catch (Exception e) {
        }
    }

    public static void toggle() {
        ensureLoaded();
        enabled = !enabled;
        if (enabled) {
            reloadMessages();
            nextSendAt = 0L;
        }
        save();
        GameScr.chatPopup("Auto chat: " + (enabled ? "Bat" : "Tat"));
    }

    public static void stop() {
        ensureLoaded();
        enabled = false;
        nextSendAt = 0L;
        save();
        GameScr.chatPopup("Da dung auto chat");
    }

    public static boolean sendNow() {
        ensureLoaded();
        reloadMessages();
        if (messages.length == 0) {
            GameScr.chatPopup("Auto chat: chua co noi dung");
            return false;
        }

        if (!isReadyToChat()) {
            GameScr.chatPopup("Auto chat: dang ban, thu lai sau");
            return false;
        }

        boolean ok = sendCurrentMessage(true);
        if (ok) {
            scheduleNext(System.currentTimeMillis());
        }
        return ok;
    }

    public static boolean isCommand(String text) {
        if (text == null) {
            return false;
        }

        text = text.trim().toLowerCase();
        return text.equals("achat")
                || text.equals("autochat")
                || text.equals("setchat")
                || text.equals("menuchat")
                || text.equals("setautochat")
                || text.equals("achatnow")
                || text.equals("chatnow")
                || text.equals("stopchat")
                || text.equals("stopachat");
    }

    public static String getStatusText() {
        ensureLoaded();
        String status = enabled ? "Bat" : "Tat";
        return status + " - " + getChannelName() + " - " + messages.length + " cau";
    }

    public static String getChannelName() {
        return channel == CHANNEL_WORLD ? "The gioi" : "Cong cong";
    }

    public static int getMinDelayMs() {
        return channel == CHANNEL_WORLD ? MIN_WORLD_DELAY_MS : MIN_PUBLIC_DELAY_MS;
    }

    public static void reloadMessages() {
        MyVector list = new MyVector();
        addMessages(list, messagesText);
        messages = new String[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            messages[i] = (String) list.elementAt(i);
        }

        if (nextIndex >= messages.length) {
            nextIndex = 0;
        }
    }

    public static void save() {
        try {
            normalizeConfig();
            ByteArrayOutputStream byteout = new ByteArrayOutputStream();
            DataOutputStream dataout = new DataOutputStream(byteout);
            dataout.writeBoolean(enabled);
            dataout.writeBoolean(randomMessage);
            dataout.writeBoolean(pauseWhenBusy);
            dataout.writeInt(channel);
            dataout.writeInt(delaySeconds);
            dataout.writeUTF(messagesText == null ? "" : messagesText);
            dataout.flush();
            RMS.writeRecord(STORE_NAME, byteout.toByteArray());
            dataout.close();
            byteout.close();
            loaded = true;
            reloadMessages();
        } catch (Exception e) {
        }
    }

    public static void load() {
        if (loaded) {
            return;
        }

        try {
            byte[] data = RMS.getRecord(STORE_NAME);
            if (data != null) {
                ByteArrayInputStream bytein = new ByteArrayInputStream(data);
                DataInputStream datain = new DataInputStream(bytein);
                enabled = datain.readBoolean();
                randomMessage = datain.readBoolean();
                pauseWhenBusy = datain.readBoolean();
                channel = datain.readInt();
                delaySeconds = datain.readInt();
                messagesText = datain.readUTF();
                datain.close();
                bytein.close();
            }
        } catch (Exception e) {
        }

        loaded = true;
        normalizeConfig();
        reloadMessages();
    }

    private static boolean sendCurrentMessage(boolean manual) {
        String text = getNextMessage();
        if (text == null || text.length() == 0) {
            GameScr.chatPopup("Auto chat: noi dung rong");
            return false;
        }

        if (channel == CHANNEL_WORLD) {
            Char me = Char.getMyChar();
            if (me != null && me.luong < 5) {
                GameScr.chatPopup("Auto chat TG: thieu luong");
                return false;
            }
            Service.getInstance().l(text);
        } else {
            Service.getInstance().c(text);
        }

        if (manual) {
            GameScr.chatPopup("Da gui: " + text);
        } else {
            GameScr.chatPopup("Auto chat " + getChannelName() + ": " + text);
        }
        return true;
    }

    private static String getNextMessage() {
        if (messages.length == 0) {
            return null;
        }

        int index;
        if (randomMessage && messages.length > 1) {
            index = NinjaUtil.a(messages.length);
        } else {
            index = nextIndex;
            ++nextIndex;
            if (nextIndex >= messages.length) {
                nextIndex = 0;
            }
        }

        return messages[index];
    }

    private static boolean isReadyToChat() {
        try {
            Char me = Char.getMyChar();
            if (me == null || me.charName == null || me.charName.length() == 0 || me.arrItemBag == null) {
                return false;
            }

            if (TileMap.ag) {
                return false;
            }

            if (pauseWhenBusy) {
                if (GameScr.ck || GameCanvas.currentDialog != null || GameCanvas.menu != null && GameCanvas.menu.showMenu) {
                    return false;
                }

                GameScr game = GameScr.getInstance();
                if (game != null && game.da != 0) {
                    return false;
                }
            }

            return true;
        } catch (Exception e) {
            long now = System.currentTimeMillis();
            if (now - lastBusyPopupAt > 10000L) {
                lastBusyPopupAt = now;
                GameScr.chatPopup("Auto chat: chua san sang");
            }
            return false;
        }
    }

    private static void scheduleNext(long now) {
        int base = delaySeconds * 1000;
        int minDelay = getMinDelayMs();
        if (base < minDelay) {
            base = minDelay;
        }

        int jitter = base / 5;
        if (jitter > 0) {
            base += NinjaUtil.a(jitter + 1);
        }
        nextSendAt = now + base;
    }

    private static void addMessages(MyVector list, String text) {
        if (text == null) {
            return;
        }

        StringBuffer current = new StringBuffer();
        for (int i = 0; i < text.length(); ++i) {
            char c = text.charAt(i);
            if (c == '\n' || c == '\r' || c == '|' || c == ';') {
                addMessage(list, current.toString());
                current.setLength(0);
            } else {
                current.append(c);
            }
        }
        addMessage(list, current.toString());
    }

    private static void addMessage(MyVector list, String text) {
        if (text == null) {
            return;
        }

        text = text.trim();
        if (text.length() == 0) {
            return;
        }

        for (int i = 0; i < list.size(); ++i) {
            if (text.equals(list.elementAt(i))) {
                return;
            }
        }
        list.addElement(text);
    }

    private static void normalizeConfig() {
        if (channel != CHANNEL_WORLD) {
            channel = CHANNEL_PUBLIC;
        }

        int minSecond = getMinDelayMs() / 1000;
        if (delaySeconds < minSecond) {
            delaySeconds = minSecond;
        }

        if (messagesText == null) {
            messagesText = "";
        }
    }

    private static void ensureLoaded() {
        if (!loaded) {
            load();
        }
    }

    static {
        load();
    }
}
