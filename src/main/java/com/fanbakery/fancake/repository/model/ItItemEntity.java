package com.fanbakery.fancake.repository.model;

import com.fanbakery.fancake.code.service.item.ItemSelStatusCd;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItItemEntity implements Serializable {
    private static final long serialVersionUID = 5787741990030842277L;

    private Long itemSeq;

    private String itemName;
    private Long itemSellPrice;
    private Long itemSellAddPrice;       //입찰 단위 가격 (시작가*증가율(default 10%))
    private Long itemSellStartPrice;
    private Long itemSellCurrPrice;
    private String itemDesc;
    private boolean itemAdult;

    private ItemSelStatusCd itemStatus;

    private LocalDateTime itemRegDate;
    private LocalDate itemSellStartDate;
    private LocalDate itemSellEndDate;

    //item 등록자 벙보
    private String itemRegisterId;
    private Long itemRegMbNo;

    //item img url
    private String itemImg1;
    private String itemImg2;
    private String itemImg3;
    private String itemImg4;
    private String itemImg5;
    private String itemImg6;
    private String itemImg7;
    private String itemImg8;
    private String itemImg9;
    private String itemImg10;

    //마지막 입찰 정보
    private Long itemLastBiddingSeq;

    // 단축 url
    private String itemShortUrl;
}
