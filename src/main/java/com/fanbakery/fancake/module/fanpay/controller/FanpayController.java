package com.fanbakery.fancake.module.fanpay.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/fanpay")
public class FanpayController {

    @GetMapping("/manage")
    public String fanpayManageView(Model model, HttpSession session){

        return "fanpay/fanpay";
    }

    @GetMapping("/setting")
    public String fanpaySettingView(Model model, HttpSession session){

        return "fanpay/fanpay2";
    }

    @GetMapping("/pay_method")
    public String fanpay3(Model model, HttpSession session){

        return "fanpay/fanpay3";
    }

    @GetMapping("/pay_method/bank")
    public String fanpay4(Model model, HttpSession session){

        return "fanpay/fanpay4";
    }
}
