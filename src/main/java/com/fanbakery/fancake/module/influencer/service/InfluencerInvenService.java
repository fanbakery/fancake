package com.fanbakery.fancake.module.influencer.service;


import com.fanbakery.fancake.code.service.item.ItemSelStatusCd;
import com.fanbakery.fancake.code.service.item.SettlementStatusCd;
import com.fanbakery.fancake.code.service.member.MbSignatureCd;
import com.fanbakery.fancake.code.system.SystemDef;
import com.fanbakery.fancake.common.model.ContentListReq;
import com.fanbakery.fancake.common.utils.DateUtil;
import com.fanbakery.fancake.module.influencer.model.InfluenInvenInfo;
import com.fanbakery.fancake.module.influencer.model.InfluenItemInfo;
import com.fanbakery.fancake.repository.mapper.InfluenItemMapper;
import com.fanbakery.fancake.repository.mapper.InfluencerMapper;
import com.fanbakery.fancake.repository.mapper.MemberMapper;
import com.fanbakery.fancake.repository.mapper.SettlementMapper;
import com.fanbakery.fancake.repository.model.ItMemberEntity;
import com.fanbakery.fancake.repository.model.OrderListInfoEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class InfluencerInvenService {

    private final InfluencerMapper influencerMapper;
    private final InfluenItemMapper influenItemMapper;
    private final MemberMapper memberMapper;
    private final SettlementMapper settlementMapper;




    //판매 진행 중인 상품 개수
    public Integer getInfluenSaleItemCount(ContentListReq listReq) {
        Integer count = influenItemMapper.selectSaleItemCntByInfluenMbNo(listReq);
        if(count == null) {
            count = 0;
        }
        return count;
    }

    //판매 종료 상품 개수
    public Integer getInfluenEndSaleItemCount(ContentListReq listReq) {
        Integer count = influenItemMapper.selectSaleOutItemCntByInfluenMbNo(listReq);
        if(count == null) {
            count = 0;
        }
        return count;
    }


    public InfluenInvenInfo  getInfluencerInvenInfo( ItMemberEntity user) {

        Long myMbNo = user.getMbNo();

        //1. select influencer info
        ItMemberEntity dbUser = memberMapper.selectMemberInfoByMbNoSignature(myMbNo, MbSignatureCd.INFLUEN_ACT);
        if( dbUser == null) {
            return null;
        }

        //2. db reqeust param setting
        ContentListReq listReq = ContentListReq.builder()
                .mbNo(myMbNo)
                .itemStatus(ItemSelStatusCd.SALE)
                .startDate(LocalDate.now())
                .build();


        //3. select count
        Integer saleItemCnt = getInfluenSaleItemCount(listReq);
        Integer saleOutItemCnt = getInfluenEndSaleItemCount(listReq);

        //4. set response
        InfluenInvenInfo invenInfo = InfluenInvenInfo.builder()
                .mbNo(myMbNo)
                .mbNick(dbUser.getMbNick())
                .mbProfile(dbUser.getMbProfile())
                .addInfo(influencerMapper.selectInfluenInfo(myMbNo))
                .totalSaleAmount(influenItemMapper.sumInfulenItemSaleAmount(myMbNo, ItemSelStatusCd.SALE))
                .totalSaleCnt((saleItemCnt + saleOutItemCnt))
                .saleCount(saleItemCnt)
                .saleOutCount(saleOutItemCnt)
                .isSettlement(settlementMapper.existsInfluencerSettlement(myMbNo, SettlementStatusCd.WAIT))
                .build();

        return invenInfo;
    }


    public List<InfluenItemInfo> getInfluencerItemList(ItMemberEntity user ) {

        //1. select item list
        List<OrderListInfoEntity> saleItemList = influenItemMapper.selectInfluenSaleHisTotalByMbNo(user.getMbNo());


        //2. set data
        List<InfluenItemInfo> itemInfoList = new ArrayList<InfluenItemInfo>();

        LocalDateTime nowTime = LocalDateTime.now();

        InfluenItemInfo influenItem = null;
        for( OrderListInfoEntity dbItem : saleItemList) {
            //first
            if( influenItem == null) {
                influenItem = InfluenItemInfo.builder()
                        .regDate( dbItem.getItemSellStartDate())
                        .product(new ArrayList<>())
                        .build();

                itemInfoList.add(influenItem);
            }

            //reg date collection
            if(influenItem.getRegDate() == null
                    || (!influenItem.getRegDate().equals(dbItem.getItemSellStartDate())) ) {

                influenItem = InfluenItemInfo.builder()
                        .regDate(dbItem.getItemSellStartDate())
                        .product(new ArrayList<>())
                        .build();

                itemInfoList.add(influenItem);
            }

            //진행중인 상품에 대해서 종료 날자 남은 기간 계산
            if( dbItem.getItemStatus().equals(ItemSelStatusCd.SALE)) {
                dbItem.setSaleEndDateLimit(DateUtil.formatDurationBetween(nowTime, dbItem.getItemSellEndDate().atTime(SystemDef.ITEM_SALE_DATE_TIME,0)));

                if( dbItem.getItemLastBiddingSeq() > 0) {
                    dbItem.setItemSellCurrPrice( dbItem.getItemSellCurrPrice() + dbItem.getItemSellAddPrice());
                }
            }

            influenItem.getProduct().add(dbItem);
        }

        return itemInfoList;
    }


    //인플루언서 판매 내역 리스트 - 전체
    public List<OrderListInfoEntity> getInfleunSaleHistTotalList(Long mbNo) {
        //1. sql parameter setting
        ContentListReq listReq = ContentListReq.builder()
                .mbNo(mbNo)
                .build();


        //2.select db info
        List<OrderListInfoEntity> itemList = influenItemMapper.selectInfluenSaleHisTotalByMbNo(mbNo);

        if( itemList != null ) {
            //3. 진행중인 상품의 남은 종료 시간 계산
            LocalDateTime nowTime = LocalDateTime.now();
            for (OrderListInfoEntity dbItem : itemList) {
                if( dbItem.getItemStatus().equals(ItemSelStatusCd.SALE)) {
                    dbItem.setSaleEndDateLimit(DateUtil.formatDurationBetween(nowTime, dbItem.getItemSellEndDate().atTime(SystemDef.ITEM_SALE_DATE_TIME,0)));
                }

                if( dbItem.getItemLastBiddingSeq() > 0) {
                    dbItem.setItemSellCurrPrice( dbItem.getItemSellCurrPrice() + dbItem.getItemSellAddPrice());
                }
            }
        }

        return itemList;
    }



    //인플루언서 판매 내역 리스트 - 진행
    public List<OrderListInfoEntity> getInfleunSaleHistSaleList(Long mbNo) {
        //1. sql parameter setting
        ContentListReq listReq = ContentListReq.builder()
                .mbNo(mbNo)
                .itemStatus(ItemSelStatusCd.SALE)
                .startDate(LocalDate.now())
                .build();


        //2.select db info
        List<OrderListInfoEntity> itemList = influenItemMapper.selectInfluenSaleHisSaleByMbNo(listReq);
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



    //인플루언서 판매 내역 리스트 - 종료
    public List<OrderListInfoEntity> getInfleunSaleHistEndSaleList( Long mbNo) {
        //1. sql parameter setting
        ContentListReq listReq = ContentListReq.builder()
                .mbNo(mbNo)
                .itemStatus(ItemSelStatusCd.SALE)
                .startDate(LocalDate.now())
                .build();


        //2.select db info
        List<OrderListInfoEntity> itemList = influenItemMapper.selectInfluenSaleHisEndSaleByMbNo(listReq);

        return itemList;
    }

}
