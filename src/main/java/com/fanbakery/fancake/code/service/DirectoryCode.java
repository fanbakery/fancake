package com.fanbakery.fancake.code.service;

public enum DirectoryCode {

    PROFILE_DIR("profile", "프로파일 이미지 경로"),
    PRODUCT_DIR("product", "제품 이미지 경로"),
    INFLUENCER_COVER_DIR("influencer", "인플루언서 커버 이미지 경로"),
    TEMP_DIR("temp", "임시 업로드 경로");

    private String code;
    private String desc;

    DirectoryCode(String code, String desc) {
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
