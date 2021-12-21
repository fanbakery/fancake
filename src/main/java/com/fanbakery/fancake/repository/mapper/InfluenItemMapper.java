package com.fanbakery.fancake.repository.mapper;

import com.fanbakery.fancake.code.service.item.ItemBiddingStatusCd;
import com.fanbakery.fancake.code.service.item.ItemSelStatusCd;
import com.fanbakery.fancake.code.service.item.SettlementStatusCd;
import com.fanbakery.fancake.common.model.ContentListReq;
import com.fanbakery.fancake.repository.model.ItemListInfoEntity;
import com.fanbakery.fancake.repository.model.OrderListInfoEntity;
import com.fanbakery.fancake.repository.model.TopBuyerInfoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InfluenItemMapper {

    //-- mypage info
    /**  ----------------
     * it_item_bidding table
     ** ---------------------- */
    public List<TopBuyerInfoEntity> selectTopBuyerByMbNoBidStatus(Long inflenMbNo, ItemBiddingStatusCd bidStatus);


    /**  ----------------
     * it_item table
     ** ---------------------- */

    public Integer selectSaleItemCntByInfluenMbNo(ContentListReq listReq);
    public Integer selectSaleOutItemCntByInfluenMbNo(ContentListReq listReq);

    //influencer mypage
    public List<ItemListInfoEntity> selectSaleProdListByMbNo(ContentListReq listReq);
    public List<ItemListInfoEntity> selectEndSaleProdListByMbNo(ContentListReq listReq);

    //influencer inven
    public Long sumInfulenItemSaleAmount(Long mbNo, ItemSelStatusCd itemStatus);

    public List<OrderListInfoEntity> selectInfluenSaleHisTotalByMbNo(Long mbNo);
    public List<OrderListInfoEntity> selectInfluenSaleHisSaleByMbNo(ContentListReq listReq);
    public List<OrderListInfoEntity> selectInfluenSaleHisEndSaleByMbNo(ContentListReq listReq);

    public Boolean existInfluenSaleItemByMbNo(Long mbNo, ItemSelStatusCd itemStatus, SettlementStatusCd settleStatus);
}
