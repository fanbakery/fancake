package com.fanbakery.fancake.module.donation.controller;

import com.fanbakery.fancake.exception.ApiErrorCode;
import com.fanbakery.fancake.exception.ApiServiceException;
import com.fanbakery.fancake.module.donation.model.ReqDonation;
import com.fanbakery.fancake.module.donation.service.DonationService;
import com.fanbakery.fancake.repository.model.ItMemberEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/donation")
public class DonationRestController {

    private final DonationService donationService;


    @PostMapping("/request")
    public ResponseEntity<?> request(ReqDonation reqDonation, HttpSession session) {

        ItMemberEntity user = (ItMemberEntity)session.getAttribute("user");

        Long mbNo = user.getMbNo();

        //1. check user balance
        Long balance = donationService.getAvailDonationBalance(mbNo);
        if( balance == 0 || balance < reqDonation.getDonatePrice()) {
            log.error("[REQ_DONATE] :: not available donation price MB_NO_" + mbNo);
            throw new ApiServiceException(ApiErrorCode.ERR_BALANCE_LACK, "잔액이 부족합니다.");
        }

        donationService.addDonationReq(mbNo, reqDonation);


        return ResponseEntity.ok().build();
    }
}
