package com.fanbakery.fancake.module.shorturl.controller;

import com.fanbakery.fancake.repository.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/s")
public class ShortUrlController {

    private final ProductMapper productMapper;


    // 단축 URL
    private static final String SHORT_URL = "/s/%s";

    // 상품 전환
    @GetMapping("/p/{short_id}")
    public String procuctiew(@PathVariable String short_id){

        Long itemSeq = productMapper.getShortUrl("/s/p/" + short_id);
        if (itemSeq==null) {
            return "redirect:/";
        }
        else {
            return "redirect:/product/product?itemNo="+ itemSeq;
        }
    }

    // 상품
    @GetMapping("/i/{short_id}")
    public String influenceView(@PathVariable String short_id){


        return "redirect:/influencer/product?itemNo=1";
    }
}
