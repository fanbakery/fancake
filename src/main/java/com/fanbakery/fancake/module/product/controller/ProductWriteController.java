package com.fanbakery.fancake.module.product.controller;

import com.fanbakery.fancake.code.service.member.MbSignatureCd;
import com.fanbakery.fancake.module.product.model.ProductAddReq;
import com.fanbakery.fancake.module.product.service.ProductService;
import com.fanbakery.fancake.module.product.service.ProductWriteService;
import com.fanbakery.fancake.repository.model.ItItemEntity;
import com.fanbakery.fancake.repository.model.ItMemberEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductWriteController {

    private final ProductService productService;
    private final ProductWriteService productWriteService;

    // 단축 URL
    private static final String SHORT_URL = "/s/%s";

    @GetMapping("/add")
    public String procuctAddView(Model model, HttpSession session){

        ItMemberEntity user = (ItMemberEntity)session.getAttribute("user");

        //check the influencer
        if(!user.getMbSignature().equals(MbSignatureCd.INFLUEN_ACT)) {
            log.error("[ADD_PRODUCT] : Not Influencer MB_NO_", user.getMbNo());
            return "redirect:/";
        }
        
        model.addAttribute("productAddReq",new ProductAddReq());
        return "product/productWrite";
    }

    @PostMapping("/add")
    public String productAdd(ProductAddReq productAddReq, Errors errors, Model model, HttpSession session) throws Exception {
        // 1. validation 에러가 있을 경우
/*        if (errors.hasErrors()) {
            return "product/productWrite";
        }*/

        ItMemberEntity user = (ItMemberEntity)session.getAttribute("user");

        //1. image file 저장
        try{
            productWriteService.moveProductImage(productAddReq, productAddReq.getTempFile());
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
            model.addAttribute("result", "파일 업로드를 실패 했습니다. 다시 시도 해주세요 ");
            return "product/productWrite";
        }

        //2. db insert
        productWriteService.addProduct(productAddReq, user);

        return "redirect:/influencer/inven";
    }

    @GetMapping("/edit")
    public String productEdit(@RequestParam(name = "itemNo") Long itemNo
                              ,Model model, HttpSession session){
        ItMemberEntity user = (ItMemberEntity)session.getAttribute("user");


        ItItemEntity productInfo = productService.getProductInfo(itemNo, user.getMbNo());
        if(productInfo == null) {
            log.error("[EDIT_PRODUCT] no item info ITEM_SEQ_" + itemNo);
            return "redirect:/";
        }

        //set data
        model.addAttribute("productInfo", productInfo);

        return "product/productWrite2";
    }


//    @GetMapping(/zzim)
//    public String setProductZzim( Model model, HttpSession session ) {
//        return "";
//    }
}
