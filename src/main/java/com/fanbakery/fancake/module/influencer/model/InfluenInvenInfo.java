package com.fanbakery.fancake.module.influencer.model;

import com.fanbakery.fancake.repository.model.ItMbInfulenerInfoEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InfluenInvenInfo {
    private Long mbNo;
    private String mbNick;
    private String mbProfile;

    private Long totalSaleAmount;

    private Integer totalSaleCnt;
    private Integer saleCount;
    private Integer saleOutCount;

    private Boolean isSettlement;

    private ItMbInfulenerInfoEntity addInfo;
}
