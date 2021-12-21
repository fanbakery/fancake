package com.fanbakery.fancake.module.order.service;

import com.fanbakery.fancake.code.system.SystemDef;
import com.fanbakery.fancake.common.model.ContentListReq;
import com.fanbakery.fancake.common.utils.DateUtil;
import com.fanbakery.fancake.repository.mapper.OrderMapper;
import com.fanbakery.fancake.repository.model.OrderListInfoEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderMapper orderMapper;


    /**
     * 구매 참여한 진행 중인 상품 개수
     * @param listReq
     * @return
     */
    public Integer getSaleBidCount(ContentListReq listReq) {
        Integer saleBidCnt = orderMapper.selectSaleMyBidCntByMbNoStatus(listReq);
        if( saleBidCnt == null ) {
            saleBidCnt = 0;
        }
        return saleBidCnt;
    }


    /**
     * 구매 참여한 종료 상품 개수
     * @param listReq
     * @return
     */
    public Integer getEndSaleBidCount(ContentListReq listReq) {
        Integer endSaleBidCnt = orderMapper.selectEndSaleMyBidCntByMbNoStatus(listReq);
        if( endSaleBidCnt == null ) {
            endSaleBidCnt = 0;
        }
        return endSaleBidCnt;
    }


    // 구매 내역 리스트 - 전체
    public List<OrderListInfoEntity> getSaleBidHistTotalList(ContentListReq listReq) {

//        //2.select db info
//        List<OrderListInfoEntity> itemList = orderMapper.selectTotalSaleBidHisByMbNo(mbNo);
//
//        if( itemList != null ) {
//            //3. 진행중인 상품의 남은 종료 시간 계산
//            LocalDateTime nowTime = LocalDateTime.now();
//            for (OrderListInfoEntity dbItem : itemList) {
//                if( dbItem.getItemStatus().equals(ItemSelStatusCd.SALE)) {
//                    dbItem.setSaleEndDateLimit(DateUtil.formatDurationBetween(nowTime, dbItem.getItemSellEndDate().atTime(SystemDef.ITEM_SALE_DATE_TIME,0)));
//                }
//                if( dbItem.getItemLastBiddingSeq() > 0) {
//                    dbItem.setItemSellCurrPrice( dbItem.getItemSellCurrPrice() + dbItem.getItemSellAddPrice());
//                }
//            }
//        }

        List<OrderListInfoEntity> itemList = null;
        List<OrderListInfoEntity> saleItemList = getSaleBidHistSaleList(listReq);
        List<OrderListInfoEntity> enSaleItemList = getSaleBidHistEndSaleList(listReq);

        if(saleItemList != null) {
            itemList = saleItemList;
        }

        if(itemList == null ) {
            itemList = enSaleItemList;
        } else {
            itemList.addAll(enSaleItemList);
        }

        return itemList;
    }



    // 구매 내역 리스트 - 진행
    public List<OrderListInfoEntity> getSaleBidHistSaleList(ContentListReq listReq) {
        //1.select db info
        List<OrderListInfoEntity> itemList = orderMapper.selectSaleBidHisByMbNo(listReq);
        if( itemList != null ) {
            //3. 진행중인 상품의 남은 종료 시간 계산
            LocalDateTime nowTime = LocalDateTime.now();
            for (OrderListInfoEntity dbItem : itemList) {
                dbItem.setSaleEndDateLimit(DateUtil.formatDurationBetween(nowTime, dbItem.getItemSellEndDate().atTime(SystemDef.ITEM_SALE_DATE_TIME,0)));
                if( dbItem.getItemLastBiddingSeq() > 0) {
                    dbItem.setItemSellCurrPrice( dbItem.getItemSellCurrPrice() + dbItem.getItemSellAddPrice());
                }
            }
        }

        return itemList;
    }



    //구매 내역 리스트 - 종료
    public List<OrderListInfoEntity> getSaleBidHistEndSaleList( ContentListReq listReq) {
        //2.select db info
        List<OrderListInfoEntity> itemList = orderMapper.selectEndSaleBidHisByMbNo(listReq);

        return itemList;
    }
}
