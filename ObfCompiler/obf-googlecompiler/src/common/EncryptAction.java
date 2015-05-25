package common;

public class EncryptAction {
    private static Encrypter encrypter = new Encrypter();    
    private EncryptAction() {
    }
    
    static public void enc(byte[] data, int index) throws ActionParseException {
     // 放弃反射，本地测试 采用反射方式耗时多10几个ms
        switch (index) {
        case 1:
            encrypter.encrypt_1(data);
            break;
        case 2:
            encrypter.encrypt_2(data);
            break;
        case 3:
            encrypter.encrypt_3(data);
            break;
        case 4:
            encrypter.encrypt_4(data);
            break;
        case 5:
            encrypter.encrypt_5(data);
            break;
        case 6:
            encrypter.encrypt_6(data);
            break;
        case 7:
            encrypter.encrypt_7(data);
            break;
        case 8:
            encrypter.encrypt_8(data);
            break;
        case 9:
            encrypter.encrypt_9(data);
            break;
        case 10:
            encrypter.encrypt_10(data);
            break;
        case 11:
            encrypter.encrypt_11(data);
            break;
        case 12:
            encrypter.encrypt_12(data);
            break;
        case 13:
            encrypter.encrypt_13(data);
            break;
        case 14:
            encrypter.encrypt_14(data);
            break;
        case 15:
            encrypter.encrypt_15(data);
            break;
        case 16:
            encrypter.encrypt_16(data);
            break;
        case 17:
            encrypter.encrypt_17(data);
            break;
        case 18:
            encrypter.encrypt_18(data);
            break;
        case 19:
            encrypter.encrypt_19(data);
            break;
        case 20:
            encrypter.encrypt_20(data);
            break;
        case 21:
            encrypter.encrypt_21(data);
            break;
        case 22:
            encrypter.encrypt_22(data);
            break;
        case 23:
            encrypter.encrypt_23(data);
            break;
        case 24:
            encrypter.encrypt_24(data);
            break;
        case 25:
            encrypter.encrypt_25(data);
            break;
        case 26:
            encrypter.encrypt_26(data);
            break;
        case 27:
            encrypter.encrypt_27(data);
            break;
        case 28:
            encrypter.encrypt_28(data);
            break;
        case 29:
            encrypter.encrypt_29(data);
            break;
        case 30:
            encrypter.encrypt_30(data);
            break;
        case 31:
            encrypter.encrypt_31(data);
            break;
        case 32:
            encrypter.encrypt_32(data);
            break;
        case 33:
            encrypter.encrypt_33(data);
            break;
        case 34:
            encrypter.encrypt_34(data);
            break;
        case 35:
            encrypter.encrypt_35(data);
            break;
        case 36:
            encrypter.encrypt_36(data);
            break;
        case 37:
            encrypter.encrypt_37(data);
            break;
        case 38:
            encrypter.encrypt_38(data);
            break;
        case 39:
            encrypter.encrypt_39(data);
            break;
        case 40:
            encrypter.encrypt_40(data);
            break;
        case 41:
            encrypter.encrypt_41(data);
            break;
        case 42:
            encrypter.encrypt_42(data);
            break;
        case 45:
            encrypter.encrypt_45(data);
            break;
        case 46:
            encrypter.encrypt_46(data);
            break;
        case 47:
            encrypter.encrypt_47(data);
            break;
        case 48:
            encrypter.encrypt_48(data);
            break;
        default:
            throw new ActionParseException("Invalid Arithmetic Index " + index
                    + " out of [1, 48].");
        }
    }
}
