package com.fanbakery.fancake.repository.mapper;

import com.fanbakery.fancake.code.service.item.SettlementStatusCd;
import com.fanbakery.fancake.repository.model.ItSettlementEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SettlementMapper {
    /**
     * ------------
     * it_settlement table
     * ----------
     */

    //select
    public Boolean existsInfluencerSettlement(Long mbNo, SettlementStatusCd status);

    public Long selectWaitSettlPriceSumByMbNo(Long mbNo, SettlementStatusCd status);

    public List<ItSettlementEntity> selectWaitSettlItemListByMbNo(Long mbNo, SettlementStatusCd status);
    public List<ItSettlementEntity> selectDoneSettlItemListByMbNo(Long mbNo, SettlementStatusCd status);

    public Long getAvailDonationBalance(Long mbNo, SettlementStatusCd settlementStatus);

    //update
    public void updateReqSettlItems(List<ItSettlementEntity> reqItems);
}
