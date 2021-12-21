package com.fanbakery.fancake.module.order.controller;

import com.fanbakery.fancake.code.service.item.ItemBiddingStatusCd;
import com.fanbakery.fancake.code.service.item.ItemSelStatusCd;
import com.fanbakery.fancake.common.model.ContentListReq;
import com.fanbakery.fancake.module.order.service.OrderService;
import com.fanbakery.fancake.repository.model.ItMemberEntity;
import com.fanbakery.fancake.repository.model.OrderListInfoEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/list")
    public String orderList( @RequestParam(name="type", defaultValue = "TOTAL") String type
            , Model model, HttpSession session){

        ItMemberEntity user = (ItMemberEntity)session.getAttribute("user");
        Long mbNo = user.getMbNo();

        //1. db reqeust param setting
        ContentListReq listReq = ContentListReq.builder()
                .mbNo(mbNo)
                .biddingStatus(ItemBiddingStatusCd.BID_SUCC)
                .itemStatus(ItemSelStatusCd.SALE)
                .startDate(LocalDate.now())
                .build();


        //2. select count
        Integer saleBidCnt = orderService.getSaleBidCount(listReq);
        Integer endSaleBidCnt = orderService.getEndSaleBidCount(listReq);
        Integer totalCnt = saleBidCnt + endSaleBidCnt;

        //3. check type
        List<OrderListInfoEntity> hisList = null;

        if( type.equals("TOTAL") && (totalCnt > 0) ) {
            hisList =  orderService.getSaleBidHistTotalList(listReq);
        } else if( type.equals("SALE") && (saleBidCnt > 0)) {
            hisList =  orderService.getSaleBidHistSaleList(listReq);
        } else {
            //type == "END"
            if( endSaleBidCnt > 0) {
                hisList = orderService.getSaleBidHistEndSaleList(listReq);
            }
        }

        //4. set res
        model.addAttribute("totalCnt", totalCnt);
        model.addAttribute("saleBidCnt", saleBidCnt);
        model.addAttribute("endSaleBidCnt", endSaleBidCnt);

        model.addAttribute("type", type);
        model.addAttribute("hisList", hisList);

        return "order/orderlist";
    }
}
