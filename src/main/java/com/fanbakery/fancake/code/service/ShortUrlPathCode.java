package com.fanbakery.fancake.code.service;

public enum ShortUrlPathCode {

    PRODUCT_PATH("p", "제품 이미지 경로"),
    INFLUENCER_PATH("i", "인플루언서 커버 이미지 경로")
    ;

    private String code;
    private String desc;

    ShortUrlPathCode(String code, String desc) {
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
