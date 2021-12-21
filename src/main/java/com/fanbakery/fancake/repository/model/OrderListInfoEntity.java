package com.fanbakery.fancake.repository.model;

import com.fanbakery.fancake.code.service.item.ItemSelStatusCd;
import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderListInfoEntity {
    private Long itemSeq;

    private String itemName;
    private Long itemSellCurrPrice;
    private Long itemSellStartPrice;
    private Long itemSellAddPrice;       //입찰 단위 가격 (시작가*증가율(default 10%))

    private Long biddingPrice;
    private Long itemLastBiddingSeq;

    private String itemImg1;
    private ItemSelStatusCd itemStatus;

    private LocalDate itemSellStartDate;
    private LocalDate itemSellEndDate;
    private String saleEndDateLimit;

    //성인용 상품여부
    private Boolean itemAdult;
}
