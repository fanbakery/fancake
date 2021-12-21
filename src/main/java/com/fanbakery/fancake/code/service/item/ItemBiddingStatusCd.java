package com.fanbakery.fancake.code.service.item;

public enum ItemBiddingStatusCd {
    BIDDING, //입찰중
    BID_SUCC, //낙찰   (it_item.items status:: ItemSelStatus.ORDERED 주문완료)
    //BID_CANCEL, // 입찰사용자취소
    BID_CANCEL_FORCED, // 입찰강제취소
    ;
}
