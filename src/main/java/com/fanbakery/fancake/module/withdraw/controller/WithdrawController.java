package com.fanbakery.fancake.module.withdraw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/withdraw")
public class WithdrawController {

    @GetMapping("/")
    public String withdraw(){
        return "withdraw/withdraw";
    }

    @GetMapping("/bidding2")
    public String bidding2(){
        return "bidding/bidding2";
    }

    @GetMapping("/bidding3")
    public String bidding3(){
        return "bidding/bidding3";
    }

    @GetMapping("/bidding4")
    public String bidding4(){
        return "bidding/bidding4";
    }

    @GetMapping("/bidding5")
    public String bidding5(){
        return "bidding/bidding5";
    }

    @GetMapping("/bidding6")
    public String bidding6(){
        return "bidding/bidding6";
    }

    @GetMapping("/biddingOk")
    public String biddingOk(){
        return "bidding/biddingOk";
    }
}
