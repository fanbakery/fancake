package com.fanbakery.fancake.module.account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {

    @GetMapping("/")
    public String account(){
        return "account/account";
    }

    @GetMapping("/2")
    public String account2(){
        return "account/account2";
    }

    @GetMapping("/ok")
    public String accountOk(){
        return "account/accountOk";
    }
}
