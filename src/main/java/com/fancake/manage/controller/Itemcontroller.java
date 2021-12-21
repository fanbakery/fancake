package com.fancake.manage.controller;

import com.fancake.manage.dto.PageRequestDTO;
import com.fancake.manage.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/item")
@Log4j2
@RequiredArgsConstructor //자동 주입을 위한 Annotation
public class Itemcontroller {

    private final ItemService service; //final로 선언
    @GetMapping("/")
    public String index(){

        return "redirect:/item/list";
    }

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model)
    {
        log.info("list-------------------");
        //log.info("list-------------------"+pageRequestDTO);
        model.addAttribute("result",service.getList( pageRequestDTO ));
    }

}
