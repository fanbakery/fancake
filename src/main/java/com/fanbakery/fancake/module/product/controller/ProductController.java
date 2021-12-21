package com.fanbakery.fancake.module.product.controller;

import com.fanbakery.fancake.code.system.BidReqResultCd;
import com.fanbakery.fancake.module.product.model.productInfo.ProductDetailInfo;
import com.fanbakery.fancake.module.product.service.ProductService;
import com.fanbakery.fancake.repository.model.ItMemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/product")
    public String product(@RequestParam(name = "itemNo") Long itemNo
                            , Model model
                            , HttpSession session){
        model.addAttribute("itemNo", itemNo);


        //check member bidding
        ItMemberEntity user = (ItMemberEntity) session.getAttribute("user");

        ProductDetailInfo productInfo = productService.getProductDetailInfo(itemNo, user);
        if(productInfo == null) {
            return "redirect:/";
        }

        //insert/update seen product
        productService.setUserSeenItem(itemNo, user.getMbNo());

        //set data
        model.addAttribute("productInfo", productInfo);

        //check bid result
        BidReqResultCd bidReqRet = (BidReqResultCd) session.getAttribute("bidReqRet");
        if( bidReqRet != null ) {
            session.removeAttribute("bidReqRet");
            model.addAttribute("bidReqRet", bidReqRet);
        }

        return "product/product";
    }

    @GetMapping("/product2")
    public String product2(@RequestParam(name = "itemNo", required = true) Long itemNo
                            , Model model){
        model.addAttribute("itemNo", itemNo);
        return "product/product2";
    }


    @GetMapping("/product3")
    public String product3(){
        return "product/product3";
    }

    @GetMapping("/product4")
    public String product4(){
        return "product/product4";
    }

    @GetMapping("/product5")
    public String product5(){
        return "product/product5";
    }

    @GetMapping("/product6")
    public String product6(){
        return "product/product6";
    }

    @GetMapping("/product7")
    public String product7(){
        return "product/product7";
    }

    @GetMapping("/product8")
    public String product8(){
        return "product/product8";
    }
}
