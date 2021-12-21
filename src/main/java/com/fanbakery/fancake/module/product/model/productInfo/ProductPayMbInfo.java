package com.fanbakery.fancake.module.product.model.productInfo;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ProductPayMbInfo {
    private Long mbNo;
    private String mbNick;
    private String mbProfile;

    private LocalDate biddingSuccDate;   //낙찰일자
}
