package com.fanbakery.fancake.module.product.model.productInfo;

import com.fanbakery.fancake.repository.model.ItItemEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDetailInfo {
    //private ProductInfo product;

    private Long zzimCount;
    private Long biddingCount;

    private boolean myBidding;
    private boolean myZzim;


    private ProductPayMbInfo payFan;
    private ItItemEntity product;
    private ProdInfluencerInfo influencer;
}
