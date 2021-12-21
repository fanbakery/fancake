package com.fanbakery.fancake.module.home.service;

import com.fanbakery.fancake.code.service.item.ItemSelStatusCd;
import com.fanbakery.fancake.code.system.SystemDef;
import com.fanbakery.fancake.common.model.ContentListReq;
import com.fanbakery.fancake.common.model.Paging;
import com.fanbakery.fancake.repository.mapper.HomMapper;
import com.fanbakery.fancake.repository.model.ItemListInfoEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MainContentsService {

    private final HomMapper homMapper;

    //인기 상품
    public Paging<List<ItemListInfoEntity>> getPopularProductList(Long mbNo
                                        , int cntPerPage) {

        //1. sql parameter setting
        ContentListReq listReq = ContentListReq.builder()
                .mbNo(mbNo)
                .cntPerPage(cntPerPage)
                .build();

        //2. select db
        //@@TODD:: Data 검증 필요
        List<ItemListInfoEntity> dbItemList = homMapper.selectPopularItemList(listReq);

        //판매 진행 상품 가격표시
        for(ItemListInfoEntity dbItem : dbItemList ){
            //상품 등록한 사람이 아닌경우, 입찰가 세팅 ( 다음 입찰 금액 세팅)
            if( dbItem.getItemStatus().equals(ItemSelStatusCd.SALE)
                && dbItem.getItemLastBiddingSeq() > 0) {
                    dbItem.setItemSellCurrPrice( dbItem.getItemSellCurrPrice() + dbItem.getItemSellAddPrice());
                }
        }

        return setListResData(listReq, dbItemList, 0L);
    }

    //최근 상품
    public Paging<List<ItemListInfoEntity>> getRecentProductList(Long mbNo
                                                            , Integer pageIndex, Integer cntPerPage) {
        //1. sql parameter setting
        ContentListReq listReq = ContentListReq.builder()
                .mbNo(mbNo)
                .startDate(LocalDate.now().minusDays(SystemDef.ITEM_RECENT_DATE_PERIOD+1))
                .pageIndex(ContentListReq.setReqPageIndex(pageIndex))
                .cntPerPage(cntPerPage)
                .build();

        //2. select db
        //@@TODD:: Data 검증 필요
        List<ItemListInfoEntity> dbItemList = homMapper.selectRecentItemList(listReq);

        //판매 진행 상품 가격표시
        for(ItemListInfoEntity dbItem : dbItemList ){
            //입찰가 세팅 ( 다음 입찰 금액 세팅)
            if( dbItem.getItemStatus().equals(ItemSelStatusCd.SALE)
                    && dbItem.getItemLastBiddingSeq() > 0) {
                dbItem.setItemSellCurrPrice( dbItem.getItemSellCurrPrice() + dbItem.getItemSellAddPrice());
            }
        }

        return setListResData(listReq, dbItemList, homMapper.selectCntRecentItemList(listReq));
    }


    //내가 본 상품
    public Paging<List<ItemListInfoEntity>> getSeenProductList(Long mbNo
                                                            , Integer pageIndex, Integer cntPerPage) {

        //1. sql parameter setting
        ContentListReq listReq = ContentListReq.builder()
                .mbNo(mbNo)
                .pageIndex(ContentListReq.setReqPageIndex(pageIndex))
                .cntPerPage(cntPerPage)
                .build();

        //2. select db
        // @@TODD:: Data 검증 필요
        List<ItemListInfoEntity> dbItemList = homMapper.selectSeenItemList(listReq);

        //판매 진행 상품 가격표시
        for(ItemListInfoEntity dbItem : dbItemList ){
            // 입찰가 세팅 ( 다음 입찰 금액 세팅)
            if( dbItem.getItemStatus().equals(ItemSelStatusCd.SALE)
                    && dbItem.getItemLastBiddingSeq() > 0) {
                dbItem.setItemSellCurrPrice( dbItem.getItemSellCurrPrice() + dbItem.getItemSellAddPrice());
            }
        }

        return setListResData(listReq, dbItemList, homMapper.selectCntSeenItemList(listReq));
    }


    //진행중 상품
    public Paging<List<ItemListInfoEntity>> getBiddingProductList(Long mbNo
                                                            , Integer pageIndex, Integer cntPerPage) {

        //1. sql parameter setting
        ContentListReq listReq = ContentListReq.builder()
                .mbNo(mbNo)
                .itemStatus(ItemSelStatusCd.SALE)
                .startDate(LocalDate.now())
                .pageIndex(ContentListReq.setReqPageIndex(pageIndex))
                .cntPerPage(cntPerPage)
                .build();

        //2. select db
        //@@TODD:: sql 작업 필요!!
        List<ItemListInfoEntity> dbItemList = homMapper.selectBidItemList(listReq);
        //판매 진행 상품 가격표시
        for(ItemListInfoEntity dbItem : dbItemList ){
            // 입찰가 세팅 ( 다음 입찰 금액 세팅)
            if( dbItem.getItemLastBiddingSeq() > 0) {
                dbItem.setItemSellCurrPrice( dbItem.getItemSellCurrPrice() + dbItem.getItemSellAddPrice());
            }
        }

        return setListResData(listReq, dbItemList, homMapper.selectCntBidItemList(listReq));
    }


    private Paging<List<ItemListInfoEntity>> setListResData(ContentListReq listReq, List<ItemListInfoEntity> items, Long totalCnt) {
        Paging<List<ItemListInfoEntity>> paging = Paging.<List<ItemListInfoEntity>>builder()
                .pageIndex(ContentListReq.setResPageIndex(listReq.getPageIndex()))
                .cntPerPage(ContentListReq.setResCntPerPage(listReq.getCntPerPage()))
                .lists(items)
                .totalCnt(totalCnt)
                .build();
        return paging;
    }
}
