package com.fancake.manage.controller;


import com.fancake.manage.dto.DonateDTO;
import com.fancake.manage.dto.PageRequestDTO;
import com.fancake.manage.service.DonateService;

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
@RequestMapping("/donate")
@Log4j2
@RequiredArgsConstructor //자동 주입을 위한 Annotation
public class DonateController {

    private final DonateService service; //final로 선언
    @GetMapping("/")
    public String index(){
        return "redirect:/donate/list";
    }

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model)
    {
        log.info("list-------------------");
        //log.info("list-------------------"+pageRequestDTO);
        model.addAttribute("result",service.getList( pageRequestDTO ));
    }

    @GetMapping("/view")
    public void view(int donateseq, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model){

        log.info("donateseq :"+donateseq);
        DonateDTO dto=service.view(donateseq);
        model.addAttribute("dto",dto);

    }

    @GetMapping("/register")
    public void register(){
        log.info("register get.....");
    }

    @PostMapping("/register")
    public String registerPost(DonateDTO dto, RedirectAttributes redirectAttributes)
    {
        log.info("dto-----"+dto );
        int donateseq = service.register(dto);

        //단한번만 메세지 모달창
        redirectAttributes.addFlashAttribute("msg", donateseq);
        return "redirect:/donate/list";
    }


}


