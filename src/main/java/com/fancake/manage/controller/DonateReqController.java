package com.fancake.manage.controller;


import com.fancake.manage.dto.DonateReqDTO;
import com.fancake.manage.dto.PageRequestDTO;
import com.fancake.manage.service.DonateReqService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/donatereq")
@Log4j2
@RequiredArgsConstructor //자동 주입을 위한 Annotation
public class DonateReqController {

    private final DonateReqService service; //final로 선언
    @GetMapping("/")
    public String index(){
        return "redirect:/donatereq/list";
    }

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model)
    {
        log.info("list-------------------");
        //log.info("list-------------------"+pageRequestDTO);
        model.addAttribute("result",service.getList( pageRequestDTO ));
    }

    @GetMapping("/view")
    public void view(int reqseq, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model){

        log.info("reqseq :"+reqseq);
        DonateReqDTO dto=service.view(reqseq);
        model.addAttribute("dto",dto);

    }

    @GetMapping("/register")
    public void register(){
        log.info("register get.....");
    }

    @PostMapping("/register")
    public String registerPost(DonateReqDTO dto, RedirectAttributes redirectAttributes)
    {
        log.info("dto-----"+dto );
        int reqseq = service.register(dto);

        //단한번만 메세지 모달창
        redirectAttributes.addFlashAttribute("msg", reqseq);
        return "redirect:/donatereq/list";
    }


}


