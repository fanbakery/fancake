package com.fanbakery.fancake.module.bidding.controller;

import com.fanbakery.fancake.code.service.item.ItemSelStatusCd;
import com.fanbakery.fancake.code.system.BidReqResultCd;
import com.fanbakery.fancake.module.account.service.AccountAddressService;
import com.fanbakery.fancake.module.bidding.model.BiddingReq;
import com.fanbakery.fancake.module.bidding.service.BiddingService;
import com.fanbakery.fancake.repository.model.ItItemEntity;
import com.fanbakery.fancake.repository.model.ItMbAddressBookEntity;
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
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/bidding")
public class BiddingController {
    private final BiddingService bidService;
    private final AccountAddressService addressService;



    @GetMapping("/bidding")
    public String biddingView(@RequestParam(name = "itemNo", required = true) Long itemNo
                              , Model model, HttpSession session){

        //1. get bidding item info
       ItItemEntity bidItem = bidService.getBiddingItem(itemNo);
       if(bidItem == null) {
           log.error("[LOAD_BID] Not Bidding Product Info::ITEM_SEQ_"+ itemNo);
           return "redirect:/";
       }

        ItMemberEntity user = (ItMemberEntity) session.getAttribute("user");

       //2. check bidding item
//       //2-1) 내가 등록한 상품 입찰 참여 불가 ??
//       if( bidItem.getItemRegMbNo() == user.getMbNo()){
//           log.error("[LOAD_BID] Same registered mb_no !! ::ITEM_SEQ_"+ itemNo);
//           return "redirect:/";
//       }

       //2-2) status
       if(!bidItem.getItemStatus().equals(ItemSelStatusCd.SALE)) {
           model.addAttribute("itemNo", itemNo);
           model.addAttribute("hisBackNum", -2);
            return "product/product2";
       }

        //3.  get last bidding info from item info
        BiddingReq biddingReq = new BiddingReq();
       biddingReq.setItemNo(itemNo);

        Long lastBidPrice = bidService.getLastBidPrice(bidItem.getItemSeq());
        if(lastBidPrice == null) {
            biddingReq.setMyBidPrice( bidItem.getItemSellStartPrice());
        } else  {
            biddingReq.setMyBidPrice(lastBidPrice + bidItem.getItemSellAddPrice());
        }
        //go page
       model.addAttribute("biddingReq", biddingReq);
       return "bidding/bidding";
    }

    @PostMapping("/bidding")
    public String biddingReq( @Valid BiddingReq biddingReq
                            , Errors errors, Model model, HttpSession session){

        String logActName = "[REQ_BID] :: ";

        //1. check error
        if(errors.hasErrors()){
            return "bidding/bidding";
        }

        //2. get current bidding item info
        Long itemNo = biddingReq.getItemNo();
        ItItemEntity bidItem = bidService.getBiddingItem(itemNo);
        if( bidItem == null
                || !bidItem.getItemStatus().equals(ItemSelStatusCd.SALE) ) {
            log.error( logActName + " Not Bidding status::ITEM_SEQ_"+ itemNo);
            return "redirect:/";
        }


        //이미 입찰진행 신청 여부 확인
        //현재 입찰 가격 확인
        if( bidItem.getItemLastBiddingSeq() > 0
                && (bidItem.getItemSellCurrPrice() > biddingReq.getMyBidPrice()) ){
            log.info(logActName + " current bidding price is bigger than req price ("
                            + bidItem.getItemSellCurrPrice() + ">" + biddingReq.getMyBidPrice());

            session.setAttribute("bidReqRet", BidReqResultCd.BID_REQ_DUP);
            return "redirect:/product/product?itemNo=" + itemNo;
        }


        ItMemberEntity user = (ItMemberEntity)session.getAttribute("user");
        Long mbNo = user.getMbNo();


        //4.결제정보 조회
//        {
//            session.setAttribute("bidReqRet", BidReqResultCd.BID_NO_ADDR_FANPAY);
//            return "redirect:/product/product?itemNo=" + itemNo;
//        }

        //7. db insert
        try {
            bidService.cancelPreviousBidInfo(itemNo, bidItem.getItemLastBiddingSeq());
            bidService.addUserBiddingInfo(biddingReq, 0L, user);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            log.error(logActName + " current bidding price is bigger than req price");

            session.setAttribute("bidReqRet", BidReqResultCd.BID_REQ_DUP);
            return "redirect:/product/product?itemNo=" + itemNo;
        }

        //3. 배송지 조회
        ItMbAddressBookEntity addressBook = addressService.getBidAddressBook(mbNo);
        if( addressBook == null ) {
            session.setAttribute("bidReqRet", BidReqResultCd.BID_NO_ADDR_BOOK);
            return "redirect:/product/product?itemNo=" + itemNo;
        }

        //8. go result
        session.setAttribute("bidReqRet", BidReqResultCd.BID_REQ_OK);
        return "redirect:/product/product?itemNo=" + itemNo;
    }

    @GetMapping("/bidding2")
    public String bidding2(){
        return "bidding/bidding2";
    }

    @GetMapping("/bidding3")
    public String bidding3(){
        return "bidding/bidding3";
    }

    @GetMapping("/bidding4")
    public String bidding4(){
        return "bidding/bidding4";
    }

    @GetMapping("/bidding5")
    public String bidding5(){
        return "bidding/bidding5";
    }

    @GetMapping("/bidding6")
    public String bidding6(){
        return "bidding/bidding6";
    }

    @GetMapping("/biddingOk")
    public String biddingOk(Model model){
        return "redirect:/";
    }

    @GetMapping("/card")
    public String card(){
        return "bidding/card";
    }

    @GetMapping("/charge")
    public String charge(){
        return "bidding/charge";
    }
}
