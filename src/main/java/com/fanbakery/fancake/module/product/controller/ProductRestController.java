package com.fanbakery.fancake.module.product.controller;

import com.fanbakery.fancake.config.ApplicationConfig;
import com.fanbakery.fancake.module.product.model.ProductAddReq;
import com.fanbakery.fancake.module.product.service.ProductWriteService;
import com.fanbakery.fancake.repository.model.ItMemberEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductRestController {

    private final ProductWriteService productWriteService;
    private final ApplicationConfig applicationConfig;

    @PostMapping("/edit")
    public ResponseEntity<Map> editProduct(@RequestBody ProductAddReq productAddReq, HttpSession session) {

        ItMemberEntity user = (ItMemberEntity)session.getAttribute("user");

        Map<String, String> map = new HashMap<>();

        //1. image file 저장
        try{
            productWriteService.moveProductImage(productAddReq, productAddReq.getTempFile());
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
            map.put("result", "fail");
            map.put("resultMsg", "파일 업로드를 실패 했습니다. 다시 시도 해주세요");
        }


        try{
            productWriteService.editProduct(productAddReq, user);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            map.put("result", "fail");
            map.put("resultMsg", "수정에 실패 했습니다. 다시 시도 해주세요");
        }

        return ResponseEntity.ok().body(map);
    }

}

