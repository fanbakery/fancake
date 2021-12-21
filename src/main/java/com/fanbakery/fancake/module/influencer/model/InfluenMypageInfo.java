package com.fanbakery.fancake.module.influencer.model;

import com.fanbakery.fancake.repository.model.ItMbInfulenerInfoEntity;
import com.fanbakery.fancake.repository.model.ItemListInfoEntity;
import com.fanbakery.fancake.repository.model.TopBuyerInfoEntity;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class InfluenMypageInfo {
    private Long mbNo;
    private String mbNick;
    private String mbProfile;

    private Long zzimCnt;

    private ItMbInfulenerInfoEntity addInfo;

    private List<TopBuyerInfoEntity> topBuyer;

    private List<ItemListInfoEntity> sale;

    private List<ItemListInfoEntity> endSale;
}
