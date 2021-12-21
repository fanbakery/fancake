package com.fanbakery.fancake.module.bidding.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BiddingReq {

    @NotNull
    private Long itemNo;

    @NotNull
    private Long myBidPrice;

    private Long myPaySeq;
    private Long myAddrBookSeq;

    @NotNull(message = "항목 확인 해주세요")
    private boolean note1;
    @NotNull(message = "항목 확인 해주세요")
    private boolean note2;
    @NotNull(message = "항목 확인 해주세요")
    private boolean note3;
    @NotNull(message = "항목 확인 해주세요")
    private boolean note4;
}
