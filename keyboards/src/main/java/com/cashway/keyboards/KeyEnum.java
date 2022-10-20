package com.cashway.keyboards;

/**
 * @author wangmm
 * @date 2022/10/19
 * @description
 */
public enum KeyEnum {

    ZERO(48,"0"),
    ONE(49,"1"),
    TWO(50,"2"),
    THREE(51,"3"),
    FOUR(52,"4"),
    FIVE(53,"5"),
    SIX(54,"6"),
    SEVEN(55,"7"),
    EIGHT(56,"8"),
    NINE(57,"9"),
    TEN(58,"."),
    ELEVEN(59,"00");

    private int code;
    private String label;

    KeyEnum(int code, String label) {
        this.code = code;
        this.label = label;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public static String getLabelByCode(int code) {
        for (KeyEnum keyEnum: values()) {
            if (keyEnum.getCode() == code) {
                return keyEnum.label;
            }
        }
        return null;
    }
}
