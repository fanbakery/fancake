package com.fanbakery.fancake.repository.mapper;

import com.fanbakery.fancake.code.service.item.ItemBiddingStatusCd;
import com.fanbakery.fancake.code.service.item.ItemSelStatusCd;
import com.fanbakery.fancake.module.product.model.productInfo.ProductPayMbInfo;
import com.fanbakery.fancake.repository.model.ItItemEntity;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;

@Mapper
public interface ProductMapper {

    /**
     * ------------
     * item table
     * ----------
     */

    //item table -- select
    public ItItemEntity selectItemInfoByItemSeq(Long itemSeq);
    public ItItemEntity selectBidItemInfoByItemSeq(Long itemSeq, ItemSelStatusCd itemStatus);

    //item table -- update
    public void updateBidInfoByItemSeq(Long itemSeq, Long biddingSeq, Long biddingPrice);

    public void updateBidSuccEndSaleItemStatus(LocalDate endDate, ItemSelStatusCd selStatus);
    public void updateNoBidEndSaleItemStatus(LocalDate endDate);


    //item table --insert
    public Long insertItem(ItItemEntity item);

    public void updateItem(ItItemEntity item);

    void updateShortUrl(ItItemEntity item);

    Long getShortUrl(String itemShortUrl);


    /**
     * ------------
     * it_item_bidding table
     * ----------
     */
    public ProductPayMbInfo selectItemPayedMbInfo(Long itemSeq, ItemBiddingStatusCd status);
}
