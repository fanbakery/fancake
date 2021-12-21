package com.fanbakery.fancake.module.zzim.controller;

import com.fanbakery.fancake.code.system.SystemDef;
import com.fanbakery.fancake.module.zzim.service.ZzimService;
import com.fanbakery.fancake.repository.model.ItMemberEntity;
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
@RequestMapping("/zzim")
public class ZzimController {

    private final ZzimService zzimService;


    @GetMapping("/")
    public String index(Model model, HttpSession session){
        int pageIndex = 1;
        int cntPerPage = SystemDef.PAGING_DEF_CONTENTS_SIZE;

        ItMemberEntity user = (ItMemberEntity)session.getAttribute("user");

        model.addAttribute("prodZzim", zzimService.getProductZzimList(user.getMbNo(), pageIndex, cntPerPage));
        model.addAttribute("influenZzim", zzimService.getInfluenZzimList(user.getMbNo(), pageIndex, cntPerPage));

        return "zzim/zzim";
    }

    @GetMapping("/not-found")
    public String not_found(Model model, HttpSession session){
        return "zzim/not_found";
    }
}
