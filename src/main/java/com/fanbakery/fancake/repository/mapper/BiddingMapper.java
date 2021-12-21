package com.fanbakery.fancake.repository.mapper;

import com.fanbakery.fancake.code.service.item.ItemBiddingStatusCd;
import com.fanbakery.fancake.code.service.item.ItemSelStatusCd;
import com.fanbakery.fancake.repository.model.ItItemBiddingEntity;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Mapper
public interface BiddingMapper {

    /**  ----------------
     * it_item_bidding table
     ** ---------------------- */

    //select item
    public Long selectBiddingCunt(Long itemSeq);

    //select item, mb_no
    public Boolean existBidJoinItemByMbNo(Long mbNo, ItemSelStatusCd itemStatus);
    public Boolean existBidItemMbNoStatus(Long itemSeq, Long mbNo, ItemBiddingStatusCd status);

    public Long selectLastBidPriceByItemSeq(Long itemSeq);
    public ItItemBiddingEntity selectLastBidInfoByItemSeq(Long itemSeq);

    public ItItemBiddingEntity selectBidInfoByItemSeqBidSeq(Long itemSeq, Long biddingSeq);


    // update
    public void updateCancelBeforeBiddingByItem(Long itemSeq, ItemBiddingStatusCd biddingStatus, LocalDateTime cancelTime);

    public void updateBidSuccEndSaleItem(LocalDate endDate,ItemBiddingStatusCd bidSuccStatus );


    //insert
    public Long insertBidding(ItItemBiddingEntity bidding);
}
