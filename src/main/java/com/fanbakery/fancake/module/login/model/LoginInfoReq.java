package com.fanbakery.fancake.module.login.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class LoginInfoReq {

    @NotBlank(message = "아이디를 입력해주세요")
    @Email(message = "올바른 이메일 주소가 아닙니다")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요")
    private String password;
}
