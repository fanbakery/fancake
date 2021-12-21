package com.fanbakery.fancake.repository.model;

import lombok.Data;

@Data
public class TopBuyerInfoEntity {
    private Long mbNo;
    private String mbNick;
    private String mbProfile;

    private Long totalPrice;
}
