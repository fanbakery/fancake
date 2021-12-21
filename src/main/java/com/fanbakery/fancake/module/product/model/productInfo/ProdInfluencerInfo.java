package com.fanbakery.fancake.module.product.model.productInfo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProdInfluencerInfo {
    private Long mbNo;
    private String mbNick;
    private String mbProfile;
    private Long zzimCnt;       //팔로워 수
}
