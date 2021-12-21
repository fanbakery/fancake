package com.fanbakery.fancake.module.bidding.service;

import com.fanbakery.fancake.code.service.item.ItemBiddingStatusCd;
import com.fanbakery.fancake.code.service.item.ItemSelStatusCd;
import com.fanbakery.fancake.code.system.BidReqResultCd;
import com.fanbakery.fancake.module.bidding.model.BiddingReq;
import com.fanbakery.fancake.repository.mapper.AccountMapper;
import com.fanbakery.fancake.repository.mapper.BiddingMapper;
import com.fanbakery.fancake.repository.mapper.PaymentMapper;
import com.fanbakery.fancake.repository.mapper.ProductMapper;
import com.fanbakery.fancake.repository.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class BiddingService {
    private final ProductMapper productMapper;
    private final BiddingMapper biddingMapper;
    private final PaymentMapper paymentMapper;

    private final AccountMapper accountMapper;


    // get bidding item
    public ItItemEntity getBiddingItem(Long itemSeq) {
        ItItemEntity dbItem = productMapper.selectItemInfoByItemSeq(itemSeq);
        if( dbItem != null ) {
            LocalDate nowDate = LocalDate.now();

            if(LocalDate.from(dbItem.getItemSellStartDate()).isAfter(nowDate)
                || LocalDate.from(dbItem.getItemSellEndDate()).isBefore(nowDate)) {
                return null;
            }
        }

        return dbItem;
    }



    /**
     * 이전 입찰자 결제 정보 취소 및 입찰 취소 상태 업데이트
     * @param itemSeq
     * @param biddingSeq
     */
    public void cancelPreviousBidInfo(Long itemSeq, Long biddingSeq){

        //1. 이전 bidding 정보 조회
        ItItemBiddingEntity bidInfo = biddingMapper.selectBidInfoByItemSeqBidSeq(itemSeq, biddingSeq);
        if(bidInfo == null) {
            log.error("[CANCEL_PREVIOUS_BID] :: no bid Info BID_SEQ_" + biddingSeq );
            return;
        }

        //2. bid 결제 정보 조회
        ItPaymentEntity paymentInfo = paymentMapper.selectPaymentInfoByNo(bidInfo.getPaymentSeq());
        if( paymentInfo == null) {
            log.error("[CANCEL_PREVIOUS_BID] :: no bid payment Info BID_SEQ_" + biddingSeq + ", PAY_NO_" + bidInfo.getPaymentSeq() );
            return;
        }
        
        //3. bid 결제 정보로 결제 취소
        //---
        //TODO:: 이전 결제 취소 로직 있어야 함.
        //Something to do....
        // bidInfo.getPaymentSeq() 로 결제 취소 ( paymentInfo 사용)
        //---

        //4. 결제 취소 성공하면 이전 bidding 취소 상태로 update
        biddingMapper.updateCancelBeforeBiddingByItem(itemSeq
                , ItemBiddingStatusCd.BID_CANCEL_FORCED
                , LocalDateTime.now());

        log.info("[CANCEL_PREVIOUS_BID] :: BID_SEQ_" + biddingSeq );
        return;
    }



    //Inset db
    /**
     * 입찰 정보 DB Insert
     * @param biddingReq
     * @param paymentSeq
     * @param user
     */
    @Transactional
    public void addUserBiddingInfo(BiddingReq biddingReq, Long paymentSeq, ItMemberEntity user) {

        Long itemSeq = biddingReq.getItemNo();
        //1. set req param
        ItItemBiddingEntity bidding = ItItemBiddingEntity.builder()
                .itemSeq(itemSeq)
                .biddingMbNo(user.getMbNo())
                .biddingPrice(biddingReq.getMyBidPrice())
                .biddingStatus(ItemBiddingStatusCd.BIDDING)
                .biddingDate(LocalDateTime.now())
                .paymentSeq(paymentSeq)
                .build();

        //3.
        biddingMapper.insertBidding(bidding);
        Long bidDbSeq = bidding.getBiddingSeq();

        //4.update item bidding price update
        productMapper.updateBidInfoByItemSeq(itemSeq, bidDbSeq, biddingReq.getMyBidPrice());


        return;
    }




    /**
     * 입찰 결제 전 입찰 가격 확인 , return null이면 결제 진행
     * @param biddingReq
     * @param session
     * @return
     */
    public String checkBidPrice(BiddingReq biddingReq, HttpSession session) {

        String logActName = "[REQ_BID] :: ";

        ItMemberEntity user = (ItMemberEntity)session.getAttribute("user");
        Long mbNo = user.getMbNo();

        if( biddingReq == null ) {
            log.error(logActName + "payment cancel... !!, No Request bidding info !! MB_NO_" + mbNo);
            return "bidding/bidding";
        }


        //2. get current bidding item info
        Long itemNo = biddingReq.getItemNo();
        ItItemEntity bidItem = getBiddingItem(itemNo);
        if( bidItem == null
                || !bidItem.getItemStatus().equals(ItemSelStatusCd.SALE) ) {
            log.error( logActName + "payment cancel... !!, Not Bidding status::ITEM_SEQ_"+ itemNo);
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

        return null;
    }



    /**
     * 입찰 결제 완료 후 입찰 정보 추가
     * @param biddingReq : 입찰정보
     * @param paymentSeq : 결제 정보 테이블 seq
     * @param session
     * @return
     */
    public String setUserBidInfo(BiddingReq biddingReq, Long paymentSeq, HttpSession session) {

        String logActName = "[REQ_BID] :: ";

        ItMemberEntity user = (ItMemberEntity)session.getAttribute("user");
        Long mbNo = user.getMbNo();


        //2. bid 결제 정보 조회
        ItPaymentEntity currPaymentInfo = paymentMapper.selectPaymentInfoByNo(paymentSeq);
        if( currPaymentInfo == null) {
            log.error(logActName + "no bid payment Info, PAY_NO_" + paymentSeq );
            return "redirect:/";
        }


        if( biddingReq == null ) {
            log.error(logActName + "payment cancel... !!, No Request bidding info !! MB_NO_" + mbNo);

            //---
            //TODO:: 내 결제 취소 로직 있어야 함.
            //Something to do....
            // paymentSeq 로 결제 취소 ( currPaymentInfo 사용)
            //---

            return "redirect:/";
        }


        //2. get current bidding item info
        Long itemNo = biddingReq.getItemNo();
        ItItemEntity bidItem = getBiddingItem(itemNo);
        if( bidItem == null
                || !bidItem.getItemStatus().equals(ItemSelStatusCd.SALE) ) {
            log.error( logActName + "payment cancel... !!, Not Bidding status::ITEM_SEQ_"+ itemNo);

            //---
            //TODO:: 내 결제 취소 로직 있어야 함.
            //Something to do....
            // paymentSeq 로 결제 취소- ( currPaymentInfo 사용)
            //---
            return "redirect:/";
        }


        //이미 입찰진행 신청 여부 확인
        //현재 입찰 가격 확인
        if( bidItem.getItemLastBiddingSeq() > 0
                && (bidItem.getItemSellCurrPrice() > biddingReq.getMyBidPrice()) ){
            log.info(logActName + " current bidding price is bigger than req price ("
                    + bidItem.getItemSellCurrPrice() + ">" + biddingReq.getMyBidPrice());

            //---
            //TODO:: 내 결제 취소 로직 있어야 함.
            //Something to do....
            // paymentSeq 로 결제 취소 ( currPaymentInfo 사용)
            //---

            session.setAttribute("bidReqRet", BidReqResultCd.BID_REQ_DUP);
            return "redirect:/product/product?itemNo=" + itemNo;
        }

        //7. db insert/update
        try {

            //7-1. 이전 입찰자 결제 취소 및 입찰 취소 상태로 업데이트
            // TODO:: cancelPreviousBidInfo() 내에서 결제 취소 기능 추가 필요!!
            cancelPreviousBidInfo(itemNo, bidItem.getItemLastBiddingSeq());
            
            //7-2. 이전 결제제 취소가 성공하면, 현재 입찰 정보 추가
            addUserBiddingInfo(biddingReq, paymentSeq, user);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            log.error(logActName + " current bidding price is bigger than req price");

            session.setAttribute("bidReqRet", BidReqResultCd.BID_REQ_DUP);
            return "redirect:/product/product?itemNo=" + itemNo;
        }

        //3. 배송지 조회
        ItMbAddressBookEntity addressBook = getBidAddressBook(mbNo);
        if( addressBook == null ) {
            session.setAttribute("bidReqRet", BidReqResultCd.BID_NO_ADDR_BOOK);
            return "redirect:/product/product?itemNo=" + itemNo;
        }

        //8. go result
        session.setAttribute("bidReqRet", BidReqResultCd.BID_REQ_OK);
        return "redirect:/product/product?itemNo=" + itemNo;
    }


    /**
     * 입찰 에 필요한 배송지 정보 조회
     * @param mbNo
     * @return
     */
    public ItMbAddressBookEntity getBidAddressBook(Long mbNo) {

        ItMbAddressBookEntity addressBook = accountMapper.selectBidAddressBookByMbNo(mbNo);
        return addressBook;
    }


    //입찰 가격
    public Long getLastBidPrice(Long itemSeq) {
        Long lastBidPrice = biddingMapper.selectLastBidPriceByItemSeq(itemSeq);
        return lastBidPrice;
    }


    public ItItemBiddingEntity getLastBiddingInfo(Long itemSeq) {
        ItItemBiddingEntity lastBidInfo = biddingMapper.selectLastBidInfoByItemSeq(itemSeq);
        return lastBidInfo;
    }

    public Long getBiddingCont(Long itemSeq) {
        return biddingMapper.selectBiddingCunt(itemSeq);
    }


    /**
     * 상품 종료 상태 및 낙찰 상태 변경 Batch 작업
     */
    @Transactional
    public void batchSetBidSuccessEndSaleItem(){
        LocalDate today = LocalDate.now();

        //판매 종료된 상품에 대해서 update 입찰 - > 낙찰
        biddingMapper.updateBidSuccEndSaleItem(today, ItemBiddingStatusCd.BID_SUCC);

        //판매 종료된 상품 상태 변경
        productMapper.updateBidSuccEndSaleItemStatus(today, ItemSelStatusCd.ORDERED);

        //입찰 참여 이력 없이 끝난 상품 업데이트
        //상품 판매 가격 0, 상태 NO_BID_END로 변경
        productMapper.updateNoBidEndSaleItemStatus(today);

        return;
    }

}
