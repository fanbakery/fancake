package com.fanbakery.fancake.code.service.item;

public enum ItemSelStatusCd {
    SALE, //판매중
    ORDERED,    //주문완료      //낙찰
    PAYED,      //결재완료
    SHIPPING,       //배송중
    DELIVERED, //배송완료
    RECEIPTED, //수취확인
    NO_BID_END, //입찰없이 기간 종료된 상품
    ;
}
