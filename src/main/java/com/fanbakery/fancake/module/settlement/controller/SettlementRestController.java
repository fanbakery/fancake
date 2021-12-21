package com.fanbakery.fancake.module.settlement.controller;

import com.fanbakery.fancake.module.settlement.service.SettlementService;
import com.fanbakery.fancake.repository.model.ItMemberEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/settlement")
public class SettlementRestController {

    private final SettlementService settlementService;

    @PostMapping("/request")
    public ResponseEntity<?> requestSettlement(@RequestParam(name="itemSeqList") List<Long> reqItemSeqList, Model model, HttpSession session) {
        ItMemberEntity user = (ItMemberEntity)session.getAttribute("user");
        Long myMbNo = user.getMbNo();

        settlementService.requestSettlement(myMbNo, reqItemSeqList );

       return ResponseEntity.ok().build();
    }
}

