package com.fanbakery.fancake.module.home.model;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class FindIdReq {

    @Pattern(regexp = "^01([0|1|6|7|8|9])([0-9]{3,4})([0-9]{4})$", message = "핸드폰 번호를 입력해야 합니다.(숫자만입력)")
    private String userPhone;

    //@NotBlank(message = "인증번호를 입력하세요")
    private String userCert;
}
