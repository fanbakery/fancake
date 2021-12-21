package com.fanbakery.fancake.module.home.model;

import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class FindPwReq {

    @Email(message = "올바른 이메일 주소가 아닙니다")
    private String email;

}
