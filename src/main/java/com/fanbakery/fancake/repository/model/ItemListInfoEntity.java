package com.fanbakery.fancake.repository.model;

import com.fanbakery.fancake.code.service.item.ItemSelStatusCd;
import lombok.Data;

@Data
public class ItemListInfoEntity {
    private Long itemSeq;

    private String itemName;
    private Long itemSellCurrPrice;

    private Long itemSellAddPrice;       //입찰 단위 가격 (시작가*증가율(default 10%))

    //마지막 입찰 정보
    private Long itemLastBiddingSeq;

    private String itemImg1;


    //item 등록자 벙보
    private String mbNick;
    private Long   mbNo;
    private String mbProfile;
    private ItemSelStatusCd itemStatus;

    //성인용 상품여부
    private Boolean itemAdult;

    //etc
    private Boolean isMyZzim;
}
