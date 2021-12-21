package com.fanbakery.fancake.code.service.member;

public enum MbUserTypeCd {

    FAN("FAN", "팬"),
    INFLENCER("INFLENCER", "인플루언서")
    ;

    private String code;
    private String desc;

    MbUserTypeCd(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
