package com.fanbakery.fancake.module.settlement.controller;

import com.fanbakery.fancake.module.settlement.service.SettlementService;
import com.fanbakery.fancake.repository.model.ItMemberEntity;
import com.fanbakery.fancake.repository.model.ItSettlementEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/settlement")
public class SettlementController {
    private final SettlementService settlementService;

    @GetMapping("/summary")
    public String summary(Model model, HttpSession session){

        ItMemberEntity user = (ItMemberEntity)session.getAttribute("user");
        Long myMbNo = user.getMbNo();

        //미 정산 금액
        Long waitSettleAmount = settlementService.getWaitingSettlAmount(myMbNo);

        model.addAttribute("waitSettleAmount", waitSettleAmount);

        return "settlement/summary";
    }

    @GetMapping("/summary_list")
    public String summaryList(Model model, HttpSession session){
        ItMemberEntity user = (ItMemberEntity)session.getAttribute("user");
        Long myMbNo = user.getMbNo();


        List<ItSettlementEntity> settlItemList = settlementService.getWaitingSettlItemList(myMbNo);

        model.addAttribute("settlItemList", settlItemList);
        return "settlement/summary_list";
    }


    @GetMapping("/item_list")
    public String itemList(Model model, HttpSession session){

        ItMemberEntity user = (ItMemberEntity)session.getAttribute("user");
        Long myMbNo = user.getMbNo();

        List<ItSettlementEntity> settlItemList = settlementService.getDoneSettlItemList(myMbNo);

        model.addAttribute("settlItemList", settlItemList);

        return "settlement/item_list";
    }


}
