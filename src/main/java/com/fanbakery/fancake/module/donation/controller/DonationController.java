package com.fanbakery.fancake.module.donation.controller;

import com.fanbakery.fancake.module.donation.service.DonationService;
import com.fanbakery.fancake.repository.model.ItDonateEntity;
import com.fanbakery.fancake.repository.model.ItMemberEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/donation")
public class DonationController {

    private final DonationService donationService;


    @GetMapping("/")
    public String index(Model model, HttpSession session){

        ItMemberEntity user = (ItMemberEntity)session.getAttribute("user");

        List<ItDonateEntity> donateList = donationService.getDonationList();

        model.addAttribute("donateList", donateList);
        model.addAttribute("balance", donationService.getAvailDonationBalance(user.getMbNo()) );
        return "donation/donation";
    }

}
