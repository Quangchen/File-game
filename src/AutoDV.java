
public final class AutoDV extends Auto {

    public static int idQuest;
    private static int currentAmount;
    private static int totalAmount;
    private static boolean bka;
    public static ItemTemplate itemTemplate;
    public static ItemTemplate itemTemplate2;
    private static Item item1;
    private static Item item2;
    private static boolean isSuccessQuest;
    private static boolean isEnded;
    private static String txtIsComplete;
    private static String txtIsFullQuest;
    private static String txtIsDoingQuest;
    private static String txtIsNotInQuest;
    private static boolean isDone;
    private static int currentAmountDisplay;
    private static boolean canNotUseDVPhu;
    private static int totalAmountDisplay;
    public static boolean aca;
    private long bva;
    private static int countNV;
    private static int countRemainQuest;
    private static int upgrade = 8;
    private static boolean[] tempTickSetting;

    public AutoDV() {
        
    }

    public final void init() {
        super.a();
        currentAmountDisplay = 0;
        totalAmountDisplay = 0;
        idQuest = -2;
        currentAmount = -1;
        totalAmount = -1;
        bka = false;
        isSuccessQuest = false;
        isEnded = false;
        isDone = false;
        txtIsComplete = "Hoàn thành nhiệm vụ. Hãy gặp Ameji để trả nhiệm vụ";
        txtIsFullQuest = "Con đã hoàn thành đủ số nhiệm vụ cho ngày hôm nay rồi";
        txtIsDoingQuest = "Con hãy hoàn thành nhiệm vụ được giao trước.";
        txtIsNotInQuest = "Hiện tại con chưa nhận nhiệm vụ nào";
        tempTickSetting = new boolean[]{Char.tickDanhQuaiThuong, Char.tickDanhTinhAnh, Char.tickDanhThuLinh , Char.tickNhatDa};
    }
    
        public final void clean() {
        currentAmountDisplay = 0;
        totalAmountDisplay = 0;
        idQuest = -2;
        currentAmount = -1;
        totalAmount = -1;
        bka = false;
        isSuccessQuest = false;
        isEnded = false;
        isDone = false;
        canNotUseDVPhu = false;
        itemTemplate = null;
        itemTemplate2 = null;
        item1 = null;
        item2 = null;
    }

    public final void b_() {
        super.b();
        if (item2 != null) {
            switch (item2.typeUI) {
                case 5:
                    item2 = Char.getMyChar().arrItemBody[item2.template.type];
                    break;
                case 3:
                    item2 = Char.getMyChar().arrItemBag[item2.indexUI];
                    break;
                case 4:
                    item2 = Char.getMyChar().arrItemBox[item2.indexUI];
                    break;
                default:
                    System.out.println("Loi reset itemNV");
                    break;
            }
        }

        if (item1 != null) {
            if (item1.typeUI == 5) {
                item1 = Char.getMyChar().arrItemBody[item1.template.type];
                return;
            }

            if (item1.typeUI == 3) {
                item1 = Char.getMyChar().arrItemBag[item1.indexUI];
                return;
            }

            System.out.println("Loi reset itemBody");
        }

    }

    public static void checkNV(String text) {
        System.out.println("Text: ".concat(String.valueOf(text)));
        if (text.startsWith("- Có thể nhận thêm ")) {
            countRemainQuest = Integer.parseInt(text.substring(19).substring(0, text.substring(19).indexOf(' ')));
        }
        if (idQuest >= 0) {
            System.out.println("A1");
            if (StringUtils.contains(text, "-") || StringUtils.contains(text, "Đã hủy nhiệm vụ ")) {
                System.out.println("A2");
                itemTemplate = null;
                isSuccessQuest = false;
                idQuest = -1;
                LockGame.notifyDV();
            }
        } else {
            if (idQuest == -2) {
                if (text.startsWith("- Có thể nhận thêm ")) {
                    System.out.println("B2");
                    itemTemplate = null;
                    idQuest = -1;
                    LockGame.notifyDV();
                    return;
                }
            }

            System.out.println("C1");
            isSuccessQuest = false;
            String[] infoDV = Code.splitString(text, "\n");
            System.out.println("Nhiệm vụ: " + infoDV[0]);
            int var2;
            Item item;
            if (infoDV.length > 3 && infoDV[3].startsWith("- Có thể nhận thêm ")) {
                countRemainQuest = Integer.parseInt(infoDV[3].substring(19).substring(0, infoDV[3].substring(19).indexOf(' ')));
            }
            if (infoDV[0].equals("Tiêu diệt quái") && SettingNVDV.tickDanhQuaiThuong == 0) {
                for (var2 = 0; var2 < Char.getMyChar().arrItemBag.length; ++var2) {
                    item = Char.getMyChar().arrItemBag[var2];
                    if (item != null && item.template.type == 26 && item.template.id <= 4) {
                        Service.getInstance().saleItem1(item.indexUI, 1);
                    }
                }
                Char.tickDanhQuaiThuong = true;
                Char.tickDanhTinhAnh = false;
                Char.tickDanhThuLinh = false;
                idQuest = 1;
            } else if (infoDV[0].equals("Tiêu diệt tinh anh") && SettingNVDV.tickGietTA == 0) {
                for (var2 = 0; var2 < Char.getMyChar().arrItemBag.length; ++var2) {
                    item = Char.getMyChar().arrItemBag[var2];
                    if (item != null && item.template.type == 26 && item.template.id <= 4) {
                        Service.getInstance().saleItem1(item.indexUI, 1);
                    }
                }
                Char.tickSanTATL = true;
                Char.tickDanhQuaiThuong = true;
                Char.tickDanhTinhAnh = true;
                Char.tickDanhThuLinh = false;
                idQuest = 2;
            } else if (infoDV[0].equals("Tiêu diệt thủ lĩnh") && SettingNVDV.tickGietTL == 0) {
                for (var2 = 0; var2 < Char.getMyChar().arrItemBag.length; ++var2) {
                    item = Char.getMyChar().arrItemBag[var2];
                    if (item != null && item.template.type == 26 && item.template.id <= 4) {
                        Service.getInstance().saleItem1(item.indexUI, 1);
                    }
                }
                Char.tickSanTATL = true;
                Char.tickDanhQuaiThuong = true;
                Char.tickDanhTinhAnh = false;
                Char.tickDanhThuLinh = true;
                idQuest = 3;
            } else if (infoDV[0].equals("Nâng cấp vật phẩm") && SettingNVDV.tickNangCapVP == 0) {
                for (var2 = 0; var2 < Char.getMyChar().arrItemBag.length; ++var2) {
                    item = Char.getMyChar().arrItemBag[var2];
                    if (item != null && item.template.type == 26 && item.template.id < 3) {
                        Service.getInstance().saleItem1(item.indexUI, 1);
                    }
                }

                idQuest = 4;
            } else {
                String[] var13;
                int var14;
                if (infoDV[0].equals("Chiến thắng lôi đài") && SettingNVDV.tickWinLoiDai == 0) {
                    var13 = Code.splitString(SettingNVDV.nameCharLoiDai, ",");

                    for (var14 = 0; var14 < var13.length; ++var14) {
                        Code.a(var13[var14], "lodai");
                    }

                    for (var14 = 0; var14 < Char.getMyChar().arrItemBag.length; ++var14) {
                        Item itemInBag1 = Char.getMyChar().arrItemBag[var14];
                        if (itemInBag1 != null && itemInBag1.template.type == 26 && itemInBag1.template.id <= 4) {
                            Service.getInstance().saleItem1(itemInBag1.indexUI, 1);
                        }
                    }

                    idQuest = 5;
                } else if (infoDV[0].equals("Nông dân chăm chỉ") && SettingNVDV.tickNongDan == 0) {
                    for (var2 = 0; var2 < Char.getMyChar().arrItemBag.length; ++var2) {
                        Item itemInBag2 = Char.getMyChar().arrItemBag[var2];
                        if (itemInBag2 != null && itemInBag2.template.type == 26 && itemInBag2.template.id <= 4) {
                            Service.getInstance().saleItem1(itemInBag2.indexUI, 1);
                        }
                    }
                    Char.tickDanhQuaiThuong = true;
                    Char.tickDanhTinhAnh = true;
                    Char.tickDanhThuLinh = true;
                    idQuest = 6;
                } else if (infoDV[0].equals("Cừu sát người khác")) {
                    for (var2 = 0; var2 < Char.getMyChar().arrItemBag.length; ++var2) {
                        item = Char.getMyChar().arrItemBag[var2];
                        if (item != null && item.template.type == 26 && item.template.id <= 4) {
                            Service.getInstance().saleItem1(item.indexUI, 1);
                        }
                    }

                    if (SettingNVDV.tickCuuSat != 0) {
                        if (SettingNVDV.tickCuuSat == 2) {
                            Code.tatAuto();
                        } else {
                            idQuest = 0;
                        }
                    } else {
                        var13 = Code.splitString(SettingNVDV.nameCharLoiDai, ",");

                        for (var14 = 0; var14 < var13.length; ++var14) {
                            Code.a(var13[var14], "cusat");
                        }

                        idQuest = 7;
                    }

                } else {
                    idQuest = 0;
                }
            }
            if (idQuest != 0) {
                String nameEquip;
                String var17;
                String var5;
                String var6;
                int var15;

                if (idQuest < 4) {
                    if (infoDV[1].startsWith("- Sử dụng ") && infoDV[2].startsWith("- Tiêu diệt ")) {
                        Char.tickNhatDa = false;
                        nameEquip = infoDV[1].substring(10, infoDV[1].length());
                        var15 = (var17 = infoDV[2].substring(12).trim()).indexOf(47);
                        var5 = var17.substring(0, var15);
                        var6 = var17.substring(var15 + 1, var17.indexOf(32));

                        try {
                            currentAmountDisplay = currentAmount = Integer.parseInt(var5);
                            totalAmountDisplay = totalAmount = Integer.parseInt(var6);
                            System.out.println("TB=" + nameEquip + " gender=" + Char.getMyChar().cgender);
                            if ((itemTemplate = ItemTemplateManager.findByNameAndGender(nameEquip, (byte) Char.getMyChar().cgender)) == null) {
                                throw new Exception();
                            }

                            System.out.println("Equip=" + itemTemplate.id + " type=" + itemTemplate.type + " name=" + itemTemplate.name + " mumb: " + var5 + " max: " + var6);
                            if (infoDV.length >= 4 && infoDV[3].equals("- Hoàn thành nhiệm vụ. Hãy gặp Ameji để trả nhiệm vụ")) {
                                isSuccessQuest = true;
                            }
                        } catch (Exception var11) {
                            GameScr.chatPopup("Dừng Auto!");
                            isEnded = true;
                        }
                    } else {
                        GameScr.chatPopup("Dừng Auto!");
                        isEnded = true;
                    }
                } else if (idQuest == 4) {
                    if (infoDV[1].startsWith("- Sử dụng ") && infoDV[2].startsWith("- Nâng cấp ")) {
                        Char.tickNhatDa = true;
                        Char.ew = 4;
                        nameEquip = infoDV[1].substring(10, infoDV[1].length());
                        var15 = (var17 = infoDV[2].substring(11).trim()).indexOf(47);
                        var5 = var17.substring(0, var15);
                        var6 = var17.substring(var15 + 1, var17.indexOf(32));
                        int lastSpaceIndex = var17.lastIndexOf(32);
                        String lastWord = var17.substring(lastSpaceIndex + 1);
                        try {
                            currentAmountDisplay = currentAmount = Integer.parseInt(var5);
                            totalAmountDisplay = totalAmount = Integer.parseInt(var6);
                            upgrade = Integer.parseInt(lastWord);
                            System.out.println("TB=" + nameEquip + " gender=" + Char.getMyChar().cgender);
                            if ((itemTemplate = ItemTemplateManager.findByNameAndGender(nameEquip, (byte) Char.getMyChar().cgender)) == null) {
                                throw new Exception();
                            }

                            if ((itemTemplate2 = ItemTemplateManager.findByNameAndGender("Giày Thô Ma", (byte) Char.getMyChar().cgender)) == null) {
                                throw new Exception();
                            }

                            System.out.println("Equip=" + itemTemplate.id + " type=" + itemTemplate.type + " name=" + itemTemplate.name + " mumb: " + var5 + " max: " + var6);
                            if (infoDV.length >= 4 && infoDV[3].equals("- Hoàn thành nhiệm vụ. Hãy gặp Ameji để trả nhiệm vụ")) {
                                isSuccessQuest = true;
                            }
                        } catch (Exception var10) {
                            GameScr.chatPopup("Dừng Auto!");
                            isEnded = true;
                        }
                    } else {
                        GameScr.chatPopup("Dừng Auto!");
                        isEnded = true;
                    }
                } else if (idQuest == 5) {
                    label258:
                    {
                        Char.tickNhatDa = false;
                        if (infoDV[1].startsWith("- Sử dụng ") && infoDV[2].startsWith("- Chiến thắng ")) {
                            nameEquip = infoDV[1].substring(10, infoDV[1].length());
                            var15 = (var17 = infoDV[2].substring(14).trim()).indexOf(47);
                            var5 = var17.substring(0, var15);
                            var6 = var17.substring(var15 + 1, var17.indexOf(32));

                            try {
                                currentAmountDisplay = currentAmount = Integer.parseInt(var5);
                                totalAmountDisplay = totalAmount = Integer.parseInt(var6);
                                System.out.println("TB=" + nameEquip + " gender=" + Char.getMyChar().cgender);
                                if ((itemTemplate = ItemTemplateManager.findByNameAndGender(nameEquip, (byte) Char.getMyChar().cgender)) == null) {
                                    throw new Exception();
                                }

                                System.out.println("Equip=" + itemTemplate.id + " type=" + itemTemplate.type + " name=" + itemTemplate.name + " mumb: " + var5 + " max: " + var6);
                                if (infoDV.length >= 4 && infoDV[3].equals("- Hoàn thành nhiệm vụ. Hãy gặp Ameji để trả nhiệm vụ")) {
                                    isSuccessQuest = true;
                                } else {
                                    Code.autoLoiDai.init();
                                }
                                break label258;
                            } catch (Exception var12) {
                            }
                        }

                        GameScr.chatPopup("Dừng Auto!");
                        isEnded = true;
                    }
                } else if (idQuest == 6) {
                    if (infoDV[1].startsWith("- Sử dụng ") && infoDV[2].startsWith("- Kiếm ")) {
                        Char.tickNhatDa = false;
                        nameEquip = infoDV[1].substring(10, infoDV[1].length());
                        var15 = (var17 = infoDV[2].substring(6).trim()).indexOf(47);
                        var5 = var17.substring(0, var15);
                        var6 = var17.substring(var15 + 1, var17.indexOf(32));

                        try {
                            currentAmountDisplay = currentAmount = Integer.parseInt(var5);
                            totalAmountDisplay = totalAmount = Integer.parseInt(var6);
                            System.out.println("TB=" + nameEquip + " gender=" + Char.getMyChar().cgender);
                            if ((itemTemplate = ItemTemplateManager.findByNameAndGender(nameEquip, (byte) Char.getMyChar().cgender)) == null) {
                                throw new Exception();
                            }

                            System.out.println("Equip=" + itemTemplate.id + " type=" + itemTemplate.type + " name=" + itemTemplate.name + " mumb: " + var5 + " max: " + var6);
                            if (infoDV.length >= 4 && infoDV[3].equals("- Hoàn thành nhiệm vụ. Hãy gặp Ameji để trả nhiệm vụ")) {
                                isSuccessQuest = true;
                            }
                        } catch (Exception var9) {
                            GameScr.chatPopup("Dừng Auto!");
                            isEnded = true;
                        }
                    } else {
                        GameScr.chatPopup("Dừng Auto!");
                        isEnded = true;
                    }
                } else if (idQuest == 7) {
                    if (infoDV[1].startsWith("- Sử dụng ") && infoDV[2].startsWith("- Cừu sát ")) {
                        Char.tickNhatDa = false;
                        nameEquip = infoDV[1].substring(10, infoDV[1].length());
                        var15 = (var17 = infoDV[2].substring(9).trim()).indexOf(47);
                        var5 = var17.substring(0, var15);
                        var6 = var17.substring(var15 + 1, var17.indexOf(32));

                        try {
                            currentAmountDisplay = currentAmount = Integer.parseInt(var5);
                            totalAmountDisplay = totalAmount = Integer.parseInt(var6);
                            System.out.println("TB=" + nameEquip + " gender=" + Char.getMyChar().cgender);
                            if ((itemTemplate = ItemTemplateManager.findByNameAndGender(nameEquip, (byte) Char.getMyChar().cgender)) == null) {
                                throw new Exception();
                            }

                            System.out.println("Equip=" + itemTemplate.id + " type=" + itemTemplate.type + " name=" + itemTemplate.name + " mumb: " + var5 + " max: " + var6);
                            if (infoDV.length >= 4 && infoDV[3].equals("- Hoàn thành nhiệm vụ. Hãy gặp Ameji để trả nhiệm vụ")) {
                                isSuccessQuest = true;
                            }
                        } catch (Exception var8) {
                            GameScr.chatPopup("Dừng Auto!");
                            isEnded = true;
                        }
                    } else {
                        GameScr.chatPopup("Dừng Auto!");
                        isEnded = true;
                    }
                }

                if (itemTemplate != null && itemTemplate.level > Char.getMyChar().cLevel) {
                    idQuest = 0;
                }
            }

            LockGame.notifyDV();
        }
    }

    public static boolean checkMsgNV(String infoNV) {
        if (infoNV.equals(txtIsFullQuest)) {
            isDone = true;
            return true;
        } else if (infoNV.equals(txtIsDoingQuest) || infoNV.equals(txtIsNotInQuest)) {
            if(Code.auto instanceof AutoDV){
                ((AutoDV)Code.auto).clean();
            }
            return true;
        } else {
            if (StringUtils.contains(infoNV, txtIsComplete)) {
                if (idQuest >= 4 && idQuest != 6) {
                    if (idQuest == 4) {
                        Service.getInstance().viewInfo(Char.getMyChar().charName);
                        LockGame.q();

                        for (int var1 = 0; var1 < Char.getMyChar().arrItemBag.length; ++var1) {
                            Item item = Char.getMyChar().arrItemBag[var1];
                            if (item != null && item.upgrade == 8 && (Char.getMyChar().arrItemBag[var1].template.id == 126 || Char.getMyChar().arrItemBag[var1].template.id == 127)) {
                                Service.getInstance().splitItem(item);
                                LockGame.q();
                                Service.getInstance().viewInfo(Char.getMyChar().charName);
                                LockGame.q();
                                break;
                            }
                        }

                        isSuccessQuest = true;
                        currentAmountDisplay = totalAmount;
                    } else {
                        isSuccessQuest = true;
                    }
                } else {
                    currentAmount = totalAmount;
                }
            }

            if (StringUtils.contains(infoNV, "Đã hết số lần dùng trong ngày hôm nay rồi.")) {
                canNotUseDVPhu = true;
            }

            if (infoNV.indexOf("- Kiếm") != -1 && idQuest == 6) {
                infoNV = infoNV.substring(infoNV.indexOf("- Kiếm ") + 6, infoNV.indexOf(47)).trim();
                GameScr.chatPopup("Yên: " + infoNV + " / " + totalAmount);

                try {
                    currentAmountDisplay = currentAmount = Integer.parseInt(infoNV);
                } catch (NumberFormatException var6) {
                }
            }

            if (infoNV.indexOf("- Cừu sát ") != -1 && idQuest == 7) {
                infoNV = infoNV.substring(infoNV.indexOf("- Cừu sát ") + 9, infoNV.indexOf(47)).trim();
                GameScr.chatPopup("Cừu Sát: " + infoNV + " / " + totalAmount);

                try {
                    currentAmountDisplay = currentAmount = Integer.parseInt(infoNV);
                } catch (NumberFormatException var5) {
                }

                if (currentAmount < totalAmount) {
                    Session_ME.getInstance().c();
                    Controller.getInstance().d();
                }
            }

            if (infoNV.indexOf("- Chiến thắng ") != -1 && idQuest == 5) {
                infoNV = infoNV.substring(infoNV.indexOf("- Chiến thắng ") + 14, infoNV.indexOf(47)).trim();
                GameScr.chatPopup("Chiến Thắng : " + infoNV + " / " + totalAmount + " trận");

                try {
                    currentAmountDisplay = currentAmount = Integer.parseInt(infoNV);
                } catch (NumberFormatException var4) {
                }
            }

            if (infoNV.indexOf("- Sử dụng ") != -1 && idQuest < 4) {
                infoNV = infoNV.substring(infoNV.indexOf("Tiêu diệt") + 10, infoNV.indexOf(47)).trim();

                try {
                    currentAmountDisplay = currentAmount = Integer.parseInt(infoNV);
                } catch (NumberFormatException var3) {
                }
            }

            return true;
        }
    }

    private static Item[] getListItemByTypeAndGender(int type, int gender) {
        switch (type) {
            case 0:
                if (gender == 1) {
                    return GameScr.arrItemNonNam;
                }

                return GameScr.arrItemNonNu;
            case 1:
                return GameScr.arrItemWeapon;
            case 2:
                if (gender == 1) {
                    return GameScr.arrItemAoNam;
                }

                return GameScr.arrItemAoNu;
            case 3:
                return GameScr.arrItemLien;
            case 4:
                if (gender == 1) {
                    return GameScr.arrItemGangTayNam;
                }

                return GameScr.arrItemGangTayNu;
            case 5:
                return GameScr.arrItemNhan;
            case 6:
                if (gender == 1) {
                    return GameScr.arrItemQuanNam;
                }

                return GameScr.arrItemQuanNu;
            case 7:
                return GameScr.arrItemNgocBoi;
            case 8:
                if (gender == 1) {
                    return GameScr.arrItemGiayNam;
                }

                return GameScr.arrItemGiayNu;
            case 9:
                return GameScr.arrItemPhu;
            default:
                return null;
        }
    }

    private boolean buyItemRequest() {
        if (itemTemplate.level >= 50) {
            return true;
        } else if (!TileMap.isTruong(TileMap.mapID)) {
            this.goMap(SettingNVDV.mapLoiDai, -2, -1, -1);
            return false;
        } else {
            System.out.println("Buy " + itemTemplate.name);
            Item[] listItem = getListItemByTypeAndGender(itemTemplate.type, Char.getMyChar().cgender);
            if (itemTemplate.type == 1) {
                GameScr.PickNpc(0, 0, 0);
            } else if (itemTemplate.type == 8) {
                GameScr.PickNpc(1, 0, 4);
            } else if (itemTemplate.type == 6) {
                GameScr.PickNpc(1, 0, 3);
            } else if (itemTemplate.type == 4) {
                GameScr.PickNpc(1, 0, 2);
            } else if (itemTemplate.type == 2) {
                GameScr.PickNpc(1, 0, 1);
            } else if (itemTemplate.type == 0) {
                GameScr.PickNpc(1, 0, 0);
            } else if (itemTemplate.type == 9) {
                GameScr.PickNpc(2, 0, 3);
            } else if (itemTemplate.type == 7) {
                GameScr.PickNpc(2, 0, 2);
            } else if (itemTemplate.type == 5) {
                GameScr.PickNpc(2, 0, 1);
            } else if (itemTemplate.type == 3) {
                GameScr.PickNpc(2, 0, 0);
            }

            if (listItem == null) {
                LockGame.waitDV();
                listItem = getListItemByTypeAndGender(itemTemplate.type, Char.getMyChar().cgender);
            }

            if (listItem != null) {
                Item item = null;

                for (int var3 = 0; var3 < listItem.length; ++var3) {
                    if (listItem[var3].template.id == itemTemplate.id) {
                        item = listItem[var3];
                        break;
                    }
                }

                if (item != null) {
                    Service.getInstance().buyItem1(item.typeUI, item.indexUI, 1);
                    LockGame.g();
                }
            } else {
                System.out.println("BuyER: " + itemTemplate.type);
            }

            return false;
        }
    }

    private boolean buyItemUpgrade() {
        if (!TileMap.isTruong(TileMap.mapID)) {
            this.goMap(SettingNVDV.mapLoiDai, -2, -1, -1);
            return false;
        } else {
            System.out.println("Buy Item Up" + itemTemplate2.name);
            Item[] var1 = getListItemByTypeAndGender(itemTemplate2.type, Char.getMyChar().cgender);
            if (itemTemplate2.type == 8) {
                GameScr.PickNpc(1, 0, 4);
            }

            if (var1 == null) {
                LockGame.waitDV();
                var1 = getListItemByTypeAndGender(itemTemplate2.type, Char.getMyChar().cgender);
            }

            if (var1 != null) {
                Item item = null;

                for (int var3 = 0; var3 < var1.length; ++var3) {
                    if (var1[var3].template.id == itemTemplate2.id) {
                        item = var1[var3];
                        break;
                    }
                }

                if (item != null) {
                    Service.getInstance().buyItem1(item.typeUI, item.indexUI, 1);
                    LockGame.g();
                }
            } else {
                System.out.println("BuyER: " + itemTemplate2.type);
            }

            return false;
        }
    }

    private boolean isMissItem() {
        if (item2 == null) {
            if ((item2 = Char.f(itemTemplate.id)) == null) {
                if ((item2 = Char.ala(itemTemplate.id)) == null) {
                    return this.buyItemRequest();
                }

                System.out.println("ItemNV box type=" + item2.typeUI + " index=" + item2.indexUI);
                bka = true;
            } else {
                System.out.println("ItemNV bag type=" + item2.typeUI + " index=" + item2.indexUI);
            }

            return false;
        } else {
            if (item2.typeUI == 4) {
                GameScr.PickNpc(5, 0, 0);
                if (Char.countNullSlot() > 0) {
                    Service.getInstance().d(item2.indexUI);

                    try {
                        Thread.sleep(500L);
                    } catch (InterruptedException var2) {
                    }
                }
            } else {
                if (item1 == null) {
                    item1 = Char.getMyChar().arrItemBody[itemTemplate.type];
                }

                if (item1 != null) {
                    System.out.println("ItemBody type=" + item1.typeUI + " index=" + item1.indexUI);
                }

                Service.getInstance().useItem(item2.indexUI);
                LockGame.q();
            }

            return false;
        }
    }

    protected final void run() {
        if (this.isDead()) {
            Auto.autoRemap(false);
        } else if (isDone) {
            GameScr.chatPopup("Xong");
            countNV = 0;
            if (super.instance != null && !(super.instance instanceof AutoDV)) {
                try {
                    Thread.sleep(5000L);
                } catch (InterruptedException var15) {
                }

                Code.backToInstance();
            } else {
                Code.tatAuto();
            }
        } else {
            int var0;
            if (idQuest > 0 && !isSuccessQuest && !isEnded) {
                TaskOrder taskOrder;
                int var19;
                short mapIdDV;
                if (idQuest < 4) {
                    if (Char.getMyChar().arrItemBody[itemTemplate.type] != null && Char.getMyChar().arrItemBody[itemTemplate.type].template.id == itemTemplate.id) {
                        var19 = SettingNVDV.mapDanhVong > 0 ? (var0 = SettingNVDV.mapDanhVong) : ((taskOrder = Char.getTaskOrderById(0)) != null ? (var0 = taskOrder.mapId) : (var0 = -1));
                        mapIdDV = (short) var0;
                        if (var19 <= 0) {
                            GameScr.chatPopup("Chưa đặt map hoặc k có nvhn");
                            isEnded = true;
                            return;
                        }

                        if (TileMap.mapID != mapIdDV) {
                            this.goMap(mapIdDV, SettingNVDV.khuDanhVong, -1, -1);
                            return;
                        }

                        if (currentAmount < totalAmount) {
                            this.attack(-1, idQuest == 2 ? 3 : (idQuest == 3 ? 5 : 1));
                            this.pickUpItem(-1);
                            return;
                        }

                        isSuccessQuest = true;
                        Auto.tuSat();
                    } else if (this.isMissItem()) {
                        GameScr.chatPopup("Thiếu item: " + itemTemplate.name);
                        isEnded = true;
                    }
                } else {
                    Char var2;
                    Item item;
                    if (idQuest == 4 && SettingNVDV.tickNangCapVP == 0) {
                        if (Char.getMyChar().arrItemBody[itemTemplate.type] != null && Char.getMyChar().arrItemBody[itemTemplate.type].template.id == itemTemplate.id) {
                            if ((var0 = Char.getIndexItemById(itemTemplate2.id)) < 0) {
                                if (this.buyItemUpgrade()) {
                                    GameScr.chatPopup("Thiếu item: " + itemTemplate2.name);
                                    isEnded = true;
                                }
                            } else if (currentAmount < totalAmount) {
                                item = (var2 = Char.getMyChar()).arrItemBag[var0];
                                if (item.upgrade >= upgrade) {
                                    Service.getInstance().splitItem(item);
                                    LockGame.q();
                                    Service.getInstance().viewInfo(Char.getMyChar().charName);
                                    LockGame.q();
                                }

                                int var4 = 0;
                                int var5 = 0;
                                if (item.isTypeClothe()) {
                                    var4 = GameScr.upClothe[item.upgrade] / 3;
                                    var5 = GameScr.coinUpClothes[item.upgrade];
                                } else if (item.isTypeAdorn()) {
                                    var4 = GameScr.upAdorn[item.upgrade] / 3;
                                    var5 = GameScr.coinUpAdorns[item.upgrade];
                                } else if (item.isTypeWeapon()) {
                                    var4 = GameScr.upWeapon[item.upgrade] / 3;
                                    var5 = GameScr.coinUpWeapons[item.upgrade];
                                }

                                int var6;
                                for (var6 = 0; var6 < Char.getMyChar().arrItemBag.length; ++var6) {
                                    Item item2 = Char.getMyChar().arrItemBag[var6];
                                    if (item2 != null && item2.template.type == 26 && item2.template.id < getMinCrystalIdForDV()) {
                                        Service.getInstance().saleItem1(item2.indexUI, 1);
                                    }
                                }

                                int var8;
                                int var9;
                                if (getTotalValueCrystalsForDV(var2) >= var4 && var2.yen >= var5) {
                                    if (item.upgrade < upgrade) {
                                        GameScr.itemUpGrade = item;
                                        GameScr.arrItemUpGrade = new Item[18];
                                        var8 = addBaoHiemForDV(item, GameScr.arrItemUpGrade);
                                        if (var8 < 0) {
                                            GameScr.itemUpGrade = null;
                                            GameScr.arrItemUpGrade = null;
                                            isEnded = true;
                                            return;
                                        }

                                        var9 = fillCrystalsForDV(var2, GameScr.arrItemUpGrade, var8, var4);
                                        if (var9 <= 0) {
                                            GameScr.itemUpGrade = null;
                                            GameScr.arrItemUpGrade = null;
                                            GameScr.chatPopup("Chua chon du da nang cap");
                                            return;
                                        }
                                        var9 = var4;

                                        for (int var10 = 0; var10 < var2.arrItemBag.length && var9 < var4; ++var10) {
                                            Item item3 = var2.arrItemBag[var10];
                                            if (item.upgrade == 6 && SettingNVDV.tickUseBHNang7 == 0) {
                                                if (Char.getIndexItemById(242) < 0) {
                                                    if (Char.getMyChar().luong >= 10) {
                                                        Service.getInstance().buyItem1(14, 23, 1);
                                                        LockGame.g();
                                                    } else {
                                                        GameScr.chatPopup("Hết Lượng Mua BHSC");
                                                    }
                                                } else {
                                                    GameScr.arrItemUpGrade[2] = getBaoHiem(242);
                                                }
                                            }

                                            if (item.upgrade == 7 && SettingNVDV.tickUseBHNang8 == 0) {
                                                if (Char.getIndexItemById(242) < 0) {
                                                    if (Char.getMyChar().luong >= 10) {
                                                        Service.getInstance().buyItem1(14, 23, 1);
                                                        LockGame.g();
                                                    } else {
                                                        GameScr.chatPopup("Hết Lượng Mua BHSC");
                                                    }
                                                } else {
                                                    GameScr.arrItemUpGrade[6] = getBaoHiem(242);
                                                }
                                            }

                                            if (item3 != null && item3.template.type == 26 && item3.template.id == SettingNVDV.daUpgrade - 1) {
                                                var2.arrItemBag[var10] = null;
                                                GameScr.arrItemUpGrade[var8++] = item3;
                                                var9 += GameScr.upClothe[item3.template.id];
                                            }
                                        }
                                        removeUpgradeMaterialsFromBag(var2, GameScr.arrItemUpGrade);
                                        Service.getInstance().upgradeItem1(item, GameScr.arrItemUpGrade, false);
                                        Service.getInstance().viewInfo(Char.getMyChar().charName);
                                        LockGame.q();
                                        GameScr.itemUpGrade = null;
                                        GameScr.getInstance().resetButton();
                                        NinjaUtil.sleep(1000);
                                    }
                                } else {
                                    
                                    TaskOrder var21;
                                    var8 = SettingNVDV.mapDanhVong > 0 ? (var6 = SettingNVDV.mapDanhVong) : ((var21 = Char.getTaskOrderById(0)) != null ? (var6 = var21.mapId) : (var6 = -1));
                                    var9 = (short) var6;
                                    if (var8 <= 0) {
                                        GameScr.chatPopup("Chưa đặt map hoặc k có nvhn");
                                        isEnded = true;
                                        return;
                                    }

                                    if (TileMap.mapID == var9) {
                                        this.pickUpItem(-2);
                                        this.attack(-1, 1);
                                        return;
                                    }

                                    if (!TileMap.isTruong(TileMap.mapID)) {
                                        Auto.tuSat();
                                        return;
                                    }
                                    this.goMap(var9, SettingNVDV.khuDanhVong, -1, -1);
                                }
                            }
                        } else if (this.isMissItem()) {
                            GameScr.chatPopup("Thiếu item: " + itemTemplate.name);
                            isEnded = true;
                        }
                    }

                    if (idQuest == 5 && SettingNVDV.tickWinLoiDai == 0) {
                        String[] var17 = Code.splitString(SettingNVDV.nameCharLoiDai, ",");

                        for (int var16 = 0; var16 < var17.length; ++var16) {
                            if (var17[var16] == null || var17[var16].length() == 0) {
                                GameScr.chatPopup("Hãy chat ld để set thông tin");
                                isEnded = true;
                                return;
                            }

                            if (Char.getMyChar().arrItemBody[itemTemplate.type] != null && Char.getMyChar().arrItemBody[itemTemplate.type].template.id == itemTemplate.id) {
                                if (currentAmount < totalAmount) {
                                    Code.autoLoiDai.run();
                                }
                            } else if (this.isMissItem()) {
                                GameScr.chatPopup("Thiếu item: " + itemTemplate.name);
                                isEnded = true;
                            }
                        }
                    }

                    if (idQuest == 6 && SettingNVDV.tickNongDan == 0) {
                        if (Char.getMyChar().arrItemBody[itemTemplate.type] != null && Char.getMyChar().arrItemBody[itemTemplate.type].template.id == itemTemplate.id) {
                            var19 = SettingNVDV.mapDanhVong > 0 ? (var0 = SettingNVDV.mapDanhVong) : ((taskOrder = Char.getTaskOrderById(0)) != null ? (var0 = taskOrder.mapId) : (var0 = -1));
                            mapIdDV = (short) var0;
                            if (var19 <= 0) {
                                GameScr.chatPopup("Chưa đặt map hoặc k có nvhn");
                                isEnded = true;
                                return;
                            }

                            if (TileMap.mapID != mapIdDV) {
                                this.goMap(mapIdDV, SettingNVDV.khuDanhVong, -1, -1);
                                return;
                            }

                            if (currentAmount < totalAmount) {
                                this.attack(-1, this.a(Char.tickDanhQuaiThuong, Char.tickDanhTinhAnh, Char.tickDanhThuLinh, false));
                                this.pickUpItem(-1);
                                return;
                            }

                            isSuccessQuest = true;
                            Auto.tuSat();
                        } else if (this.isMissItem()) {
                            GameScr.chatPopup("Thiếu item: " + itemTemplate.name);
                            isEnded = true;
                        }
                    }

                    if (idQuest == 7) {
                        if (SettingNVDV.tickCuuSat == 0) {
                            if (Char.getMyChar().arrItemBody[itemTemplate.type] != null && Char.getMyChar().arrItemBody[itemTemplate.type].template.id == itemTemplate.id) {
                                if (TileMap.mapID != SettingNVDV.mapCuuSat) {
                                    this.goMap(SettingNVDV.mapCuuSat, SettingNVDV.khuCuuSat, -1, -1);
                                    return;
                                }

                                if (currentAmount < totalAmount) {
                                    for (var0 = 0; var0 < GameScr.vCharInMap.size(); ++var0) {
                                        var2 = (Char) GameScr.vCharInMap.elementAt(var0);
                                        if (var2.cHP > 0) {
                                            if (System.currentTimeMillis() - this.bva >= 1500L) {
                                                Char.charMove(var2.cx, var2.cy);
                                                this.bva = System.currentTimeMillis();
                                            }

                                            if (Char.getMyChar().cTypePk != 3) {
                                                Service.getInstance().z(3);
                                            }

                                            if (Char.getMyChar().hieuChien >= 5 && (item = Char.f(257)) != null && item.template.id == 257) {
                                                Service.getInstance().useItem(item.indexUI);
                                            }

                                            Auto.v.removeAllElements();
                                            Auto.w.removeAllElements();
                                            Auto.w.addElement(var2);
                                            if (selectSkill.template.type != 1) {
                                                if (GameScr.arrSkill[0].template.type == 1) {
                                                    selectSkill = GameScr.arrSkill[0];
                                                } else if (GameScr.arrSkill[1].template.type == 1) {
                                                    selectSkill = GameScr.arrSkill[1];
                                                } else if (GameScr.arrSkill[2].template.type == 1) {
                                                    selectSkill = GameScr.arrSkill[2];
                                                } else if (GameScr.arrSkill[3].template.type == 1) {
                                                    selectSkill = GameScr.arrSkill[3];
                                                } else if (GameScr.arrSkill[4].template.type == 1) {
                                                    selectSkill = GameScr.arrSkill[4];
                                                } else {
                                                    GameScr.chatPopup("Chọn 1 skill tấn công để tấn công");
                                                }
                                            }
                                            Service.getInstance().selectSkill(selectSkill.template.id);
                                            Service.getInstance().a(Auto.v, Auto.w, 2);
                                            if (System.currentTimeMillis() - selectSkill.lastTimeUseThisSkill >= (long) selectSkill.coolDown + 50L) {
                                                selectSkill.lastTimeUseThisSkill = System.currentTimeMillis();
                                                selectSkill.paintCanNotUseSkill = true;
                                                if (!Code.isBangSkill) {
                                                    Char.getMyChar().b(GameScr.skillPaints[selectSkill.template.id], 0);
                                                }
                                            }
                                        }
                                    }
                                    return;
                                }

                                isSuccessQuest = true;
                                Auto.tuSat();
                            } else if (this.isMissItem()) {
                                GameScr.chatPopup("Thiếu item: " + itemTemplate.name);
                                isEnded = true;
                            }
                        } else {
                            idQuest = 0;
                        }
                    }
                }
            } else {
                if (!TileMap.isTruong(TileMap.mapID)) {
                    this.goMap(SettingNVDV.mapLoiDai, -2, -1, -1);
                    return;
                }

                if (idQuest == -2) {
                    System.out.println("InfoNV");
                    GameScr.chatPopup("Xem Info NVDV " + countNV);
                    cleanItem();
                    if (Char.getIndexItemById(705) > 0 && !canNotUseDVPhu) {
                        for (var0 = 0; var0 < 5; ++var0) {
                            Service.getInstance().useItem(Char.getIndexItemById(705));
                        }
                    }

                    if (Char.getIndexItemById(35) < 0 && Char.getIndexItemById(37) < 0) {
                        if (Char.getMyChar().luong >= 20) {
                            Service.getInstance().buyItem1(14, 1, 1);
                        } else {
                            Service.getInstance().buyItem1(9, 6, 1);
                        }
                    }
                    if (TileMap.mapID != 1) {
                        this.goMap(1, -2, -1, -1);
                        return;
                    }
                    GameScr.PickNpc(2, 1, 6);
                    LockGame.waitDV();
                    return;
                }

                if (idQuest == -1) {
                    System.out.println("NhanNV");
                    GameScr.chatPopup("Nhận NVDV " + countNV);
                    if (Char.getIndexItemById(705) > 0 && !canNotUseDVPhu) {
                        for (var0 = 0; var0 < 5; ++var0) {
                            Service.getInstance().useItem(Char.getIndexItemById(705));
                        }
                    }

                    if (Char.getIndexItemById(35) < 0 && Char.getIndexItemById(37) < 0) {
                        if (Char.getMyChar().luong >= 20) {
                            Service.getInstance().buyItem1(14, 1, 1);
                        } else {
                            Service.getInstance().buyItem1(9, 6, 1);
                        }
                    }
                    if (TileMap.mapID != 1) {
                        this.goMap(1, -2, -1, -1);
                        return;
                    }
                    GameScr.PickNpc(2, 1, 0);
                    cleanItem();
                    LockGame.waitDV();
                    GameScr.PickNpc(2, 1, 6);
                    LockGame.waitDV();
                    return;
                }

                if (idQuest == 0) {
                    System.out.println("HuyNV");
                    GameScr.chatPopup("Hủy NVDV " + countNV);
                    if (TileMap.mapID != 1) {
                        this.goMap(1, -2, -1, -1);
                        return;
                    }
                    GameScr.PickNpc(2, 1, 2);
                    GameScr.goNPC(2);
                    Service.getInstance().o(4);
                    LockGame.waitDV();
                    Char.tickDanhQuaiThuong = tempTickSetting[0];
                    Char.tickDanhTinhAnh = tempTickSetting[1];
                    Char.tickDanhThuLinh = tempTickSetting[2];
                    Char.tickNhatDa = tempTickSetting[3];
                    return;
                }

                if (isEnded || isSuccessQuest) {
                    if (item1 != null) {
                        if (item1.typeUI != 5) {
                            System.out.println("Mac TB: " + item1.template.name + " index=" + item1.indexUI);
                            Service.getInstance().useItem(item1.indexUI);
                            LockGame.q();
                            item1 = null;
                            return;
                        }

                        if (Char.getMyChar().arrItemBody[item1.template.type].upgrade < 12) {
                            System.out.println("Loi item: " + item1.template.type);
                        }

                        item1 = null;
                    } else if (item2 != null) {
                        if (!bka) {
                            item2 = null;
                            return;
                        }

                        if (item2.typeUI == 3) {
                            System.out.println("Cat item " + item2.template.name + " vao ruong");
                            GameScr.PickNpc(5, 0, 0);
                            if (Char.countNullSlotBox() > 0) {
                                Service.getInstance().e(item2.typeUI);

                                try {
                                    Thread.sleep(1000L);
                                    return;
                                } catch (InterruptedException var12) {
                                    return;
                                }
                            }

                            item2 = null;
                            return;
                        }

                        item2 = null;
                    } else if (isSuccessQuest) {
                        if (Char.countNullSlot() <= 0) {
                            GameScr.chatPopup("Hành trang đầy");
                            return;
                        }

                        System.out.println("HoanThanhNV");
                        if (Char.getMyChar().cTypePk == 3) {
                            Service.getInstance().z(0);
                        }

                        if (TileMap.mapID != 1) {
                            this.goMap(1, -2, -1, -1);
                            return;
                        }
                        GameScr.chatPopup("Hoàn thành NVDV " + countNV);
                        GameScr.PickNpc(2, 1, 1);
                        LockGame.waitDV();
                        countNV++;
                        Char.tickDanhQuaiThuong = tempTickSetting[0];
                        Char.tickDanhTinhAnh = tempTickSetting[1];
                        Char.tickDanhThuLinh = tempTickSetting[2];
                        Char.tickNhatDa = tempTickSetting[3];
                    } else if (super.instance != null && !(super.instance instanceof AutoDV)) {
                        try {
                            Thread.sleep(5000L);
                        } catch (InterruptedException var13) {
                        }

                        Code.backToInstance();
                    }
                }
            }

        }
    }

    public static void cleanItem() {
        for (int var0 = 0; var0 < Char.getMyChar().arrItemBag.length; ++var0) {
            Item item = Char.getMyChar().arrItemBag[var0];
            if (item != null && item.template.level < 50 && item.template.type < 10 && item.upgrade == 0) {
                Service.getInstance().saleItem1(item.indexUI, 1);
            }
        }

    }

    public final String toString() {
        String strCur = "";
        switch (idQuest) {
            case 1:
                strCur += " - Đánh Quái: " + currentAmountDisplay + "/" + totalAmountDisplay;
                break;
            case 2:
                strCur += " - Đánh TA: " + currentAmountDisplay + "/" + totalAmountDisplay;
                break;
            case 3:
                strCur += " - Đánh TL: " + currentAmountDisplay + "/" + totalAmountDisplay;
                break;
            case 4:
                strCur += " - Nâng cấp: " + currentAmountDisplay + "/" + totalAmountDisplay;
                break;
            case 5:
                strCur += " - Thắng lôi đài: " + currentAmountDisplay + "/" + totalAmountDisplay;
                break;
            case 6:
                strCur += " - Kiếm: " + currentAmountDisplay + "/" + totalAmountDisplay + " Yên";
                break;
            case 7:
                strCur += " - Cừu sát: " + currentAmountDisplay + "/" + totalAmountDisplay + " người";
                break;
            default:
                strCur += "Auto NVDV";
                break;
        }

        strCur += " [AutoDV: " + countNV + "/" + countRemainQuest + "]";
        return strCur;
    }

    private static int addBaoHiemForDV(Item item, Item[] mats) {
        if (item == null || mats == null) {
            return 0;
        }

        if ((item.upgrade == 6 && SettingNVDV.tickUseBHNang7 == 0) || (item.upgrade == 7 && SettingNVDV.tickUseBHNang8 == 0)) {
            Item protect = getBaoHiem(242);
            if (protect == null) {
                if (Char.getMyChar().luong >= 10) {
                    Service.getInstance().buyItem1(14, 23, 1);
                    LockGame.g();
                    protect = getBaoHiem(242);
                } else {
                    GameScr.chatPopup("Het luong mua BHSC");
                    return -1;
                }
            }

            if (protect == null) {
                GameScr.chatPopup("Thieu BHSC");
                return -1;
            }

            mats[0] = protect;
            return 1;
        }

        return 0;
    }

    private static int getTotalValueCrystalsForDV(Char me) {
        if (me == null || me.arrItemBag == null || GameScr.crystals == null) {
            return 0;
        }

        int total = 0;
        for (int i = 0; i < me.arrItemBag.length; ++i) {
            Item item = me.arrItemBag[i];
            if (isValidCrystalForDV(item)) {
                total += GameScr.crystals[item.template.id];
            }
        }
        return total;
    }

    public static boolean canPickCrystalForUpgrade(ItemTemplate itemTemplate) {
        return Code.auto instanceof AutoDV && idQuest == 4 && SettingNVDV.tickNangCapVP == 0
                && Char.tickNhatDa && itemTemplate != null && itemTemplate.type == 26
                && GameScr.crystals != null && itemTemplate.id >= getMinCrystalIdForDV()
                && itemTemplate.id >= 0 && itemTemplate.id < GameScr.crystals.length
                && itemTemplate.id <= 11 && GameScr.crystals[itemTemplate.id] > 0;
    }

    private static int fillCrystalsForDV(Char me, Item[] mats, int start, int required) {
        if (me == null || me.arrItemBag == null || mats == null || required <= 0 || start >= mats.length) {
            return 0;
        }

        int total = 0;
        int count = 0;
        int index = start;
        boolean[] used = new boolean[me.arrItemBag.length];
        while (total < required && index < mats.length) {
            int crystalIndex = chooseCrystalIndexForDV(me, used, required - total, mats.length - index);
            if (crystalIndex < 0) {
                break;
            }

            Item crystal = me.arrItemBag[crystalIndex];
            used[crystalIndex] = true;
            mats[index++] = crystal;
            total += GameScr.crystals[crystal.template.id];
            ++count;
        }

        if (total < required) {
            for (int i = start; i < mats.length; ++i) {
                mats[i] = null;
            }
            return 0;
        }

        return count;
    }

    private static int chooseCrystalIndexForDV(Char me, boolean[] used, int remaining, int slotsLeft) {
        if (me == null || me.arrItemBag == null || GameScr.crystals == null || slotsLeft <= 0) {
            return -1;
        }

        int averageNeed = ceilDiv(remaining, slotsLeft);
        int bestUnder = -1;
        int bestUnderValue = -1;
        int bestOver = -1;
        int bestOverValue = 2147483647;

        for (int i = 0; i < me.arrItemBag.length; ++i) {
            Item crystal = me.arrItemBag[i];
            if (used[i] || !isValidCrystalForDV(crystal)) {
                continue;
            }

            int value = GameScr.crystals[crystal.template.id];
            if (value <= remaining) {
                if (value > bestUnderValue) {
                    bestUnder = i;
                    bestUnderValue = value;
                }
            } else if (value < bestOverValue) {
                bestOver = i;
                bestOverValue = value;
            }
        }

        if (bestUnder >= 0 && (bestUnderValue >= averageNeed || bestOver < 0)) {
            return bestUnder;
        }
        if (bestOver >= 0) {
            return bestOver;
        }
        return bestUnder;
    }

    private static boolean isValidCrystalForDV(Item item) {
        return item != null && item.template != null && item.template.type == 26 && item.quantity == 1
                && GameScr.crystals != null && item.template.id >= getMinCrystalIdForDV()
                && item.template.id >= 0 && item.template.id < GameScr.crystals.length
                && item.template.id <= 11 && GameScr.crystals[item.template.id] > 0;
    }

    private static int getMinCrystalIdForDV() {
        int min = Char.ew > 0 ? Char.ew - 1 : SettingNVDV.daUpgrade - 1;
        return min < 0 ? 0 : min;
    }

    private static int ceilDiv(int a, int b) {
        return b <= 0 ? a : (a + b - 1) / b;
    }

    private static void removeUpgradeMaterialsFromBag(Char me, Item[] mats) {
        if (me == null || me.arrItemBag == null || mats == null) {
            return;
        }

        for (int i = 0; i < mats.length; ++i) {
            Item mat = mats[i];
            if (mat == null || mat.template == null) {
                continue;
            }

            for (int j = 0; j < me.arrItemBag.length; ++j) {
                Item bag = me.arrItemBag[j];
                if (bag != null && bag.template != null && (bag == mat || bag.indexUI == mat.indexUI && bag.template.id == mat.template.id)) {
                    me.arrItemBag[j] = null;
                    break;
                }
            }
        }
    }

    public static Item getBaoHiem(int var0) {
        for (int var1 = 0; var1 < Char.getMyChar().arrItemBag.length; ++var1) {
            Item item = Char.getMyChar().arrItemBag[var1];
            if (item != null && item.template.type == 28 && item.template.id == var0) {
                return item;
            }
        }
        return null;
    }
}
