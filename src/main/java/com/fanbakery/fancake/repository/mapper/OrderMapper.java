package com.fanbakery.fancake.repository.mapper;

import com.fanbakery.fancake.common.model.ContentListReq;
import com.fanbakery.fancake.repository.model.OrderListInfoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {

    /**  ----------------
     * it_item_bidding table
     ** ---------------------- */

    public Integer selectSaleMyBidCntByMbNoStatus(ContentListReq listReq);
    public Integer selectEndSaleMyBidCntByMbNoStatus(ContentListReq listReq);

    public List<OrderListInfoEntity> selectTotalSaleBidHisByMbNo(Long mbNo);
    public List<OrderListInfoEntity> selectSaleBidHisByMbNo(ContentListReq listReq);
    public List<OrderListInfoEntity> selectEndSaleBidHisByMbNo(ContentListReq listReq);





}
