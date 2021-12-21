package com.fanbakery.fancake.module.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainApproveController {

    @GetMapping("/approve")
    public String intro(){
        return "/home/home_approve";
    }

    @GetMapping("/approve2")
    public String mainScreen(){
        return "/home/home_approve2";
    }
}
