package com.fanbakery.fancake.module.settlement.service;

import com.fanbakery.fancake.code.service.item.SettlementStatusCd;
import com.fanbakery.fancake.repository.mapper.SettlementMapper;
import com.fanbakery.fancake.repository.model.ItSettlementEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SettlementService {
    private final SettlementMapper settlementMapper;


    /**
     * 정산 예정 금액 조회
     * @param mbNo
     * @return
     */
    public Long getWaitingSettlAmount(Long mbNo) {

        Long settlAmount = settlementMapper.selectWaitSettlPriceSumByMbNo(mbNo, SettlementStatusCd.WAIT);
        if(settlAmount == null) {
            settlAmount = 0L;
        }
        return settlAmount;
    }


    /**
     * 미 정산 상품 목록
     * @param mbNo
     * @return
     */
    public List<ItSettlementEntity> getWaitingSettlItemList(Long mbNo) {

        List<ItSettlementEntity> settlItemList = settlementMapper.selectWaitSettlItemListByMbNo(mbNo, SettlementStatusCd.WAIT);

        return settlItemList;
    }


    /**
     *  정상된 상품 목록
     * @param mbNo
     * @return
     */
    public List<ItSettlementEntity> getDoneSettlItemList(Long mbNo) {

        List<ItSettlementEntity> settlItemList = settlementMapper.selectDoneSettlItemListByMbNo(mbNo, SettlementStatusCd.COMPLETE);

        return settlItemList;
    }



    /**
     * 정산 신청
     * @param mbNo
     * @param reqItemSeqList
     */
    @Transactional
    public void requestSettlement(Long mbNo, List<Long> reqItemSeqList) {

        //1. set request param
        List<ItSettlementEntity> reqItems = new ArrayList<ItSettlementEntity>();

        LocalDateTime todayTime = LocalDateTime.now();

        for(Long itemSeq : reqItemSeqList) {
            reqItems.add( ItSettlementEntity.builder()
                            .itemSeq(itemSeq)
                            .mbNo(mbNo)
                            .isSettleRequest(true)
                            .settleReqDate(todayTime)
                            .build());
        }

        settlementMapper.updateReqSettlItems(reqItems);

        return;
    }

}
