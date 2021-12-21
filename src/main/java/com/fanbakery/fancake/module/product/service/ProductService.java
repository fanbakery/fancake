package com.fanbakery.fancake.module.product.service;

import com.fanbakery.fancake.code.service.item.ItemBiddingStatusCd;
import com.fanbakery.fancake.code.service.item.ItemSelStatusCd;
import com.fanbakery.fancake.code.service.member.MbSignatureCd;
import com.fanbakery.fancake.module.product.model.productInfo.ProdInfluencerInfo;
import com.fanbakery.fancake.module.product.model.productInfo.ProductDetailInfo;
import com.fanbakery.fancake.module.product.model.productInfo.ProductPayMbInfo;
import com.fanbakery.fancake.repository.mapper.*;
import com.fanbakery.fancake.repository.model.ItItemEntity;
import com.fanbakery.fancake.repository.model.ItMemberEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private final MemberMapper memberMapper;

    private final ProductMapper productMapper;
    private final BiddingMapper biddingMapper;
    private final SeenMapper seenMapper;

    private final ZzimMapper zzimMapper;



    public void setUserSeenItem( Long itemSeq, Long MbNo ) {
        seenMapper.insertItemMbSeen(itemSeq, MbNo, LocalDateTime.now());
        return;
    }

    public ItItemEntity getItemInfo(Long itemSeq) {
        ItItemEntity dbItem = productMapper.selectItemInfoByItemSeq(itemSeq);
        return dbItem;
    }

    public ProductDetailInfo getProductDetailInfo(Long itemSeq, ItMemberEntity user)
    {
        String logActName = "[PROD_INFO] :: ";

        Long myMbNo = user.getMbNo();

        //1. get item db info
        ItItemEntity dbItem = productMapper.selectItemInfoByItemSeq(itemSeq);
        if(dbItem == null ) {
            log.error(logActName + "no item db info, ITEM_SEQ_" + itemSeq );
            return null;
        }

        //2021.12.03 수정사항 반영
        Long currPrice = dbItem.getItemSellCurrPrice();

        //상품 등록한 사람이 아닌경우, 입찰가 세팅 ( 다음 입찰 금액 세팅)
        if( dbItem.getItemStatus().equals(ItemSelStatusCd.SALE)
                && dbItem.getItemLastBiddingSeq() > 0) {
                dbItem.setItemSellCurrPrice( currPrice + dbItem.getItemSellAddPrice());
        }

        ProdInfluencerInfo influenInfo = getProdInfluencerInfo(itemSeq, dbItem.getItemRegMbNo());
        if( influenInfo == null) {
            return null;
        }

        if(dbItem.getItemDesc()!=null)
            dbItem.setItemDesc(StringUtils.replace(dbItem.getItemDesc(), System.lineSeparator(), "<br/>"));


        ProductDetailInfo detailInfo = ProductDetailInfo.builder()
                .zzimCount(zzimMapper.selectItemZzimCountByItemSeq(itemSeq))
                .biddingCount(biddingMapper.selectBiddingCunt(itemSeq))
                .myZzim(zzimMapper.exsitsMyItemZzimByMbNo(itemSeq, user.getMbNo()))
                .myBidding(biddingMapper.existBidItemMbNoStatus(itemSeq, user.getMbNo(), ItemBiddingStatusCd.BIDDING))
                .product(dbItem)
                .influencer(influenInfo)
                .build();

        //판매된 상품일 경우, 구입한 팬 정보 추가
        if( !dbItem.getItemStatus().equals(ItemSelStatusCd.SALE)
            &&  dbItem.getItemLastBiddingSeq() > 0) {
            ProductPayMbInfo dbPayedMb =  productMapper.selectItemPayedMbInfo(itemSeq, ItemBiddingStatusCd.BID_SUCC);
            detailInfo.setPayFan(dbPayedMb);
        }

        return detailInfo;
    }

    private ProdInfluencerInfo getProdInfluencerInfo(Long itemSeq, Long mbNo) {
        String logActName = "[PROD_IFLUN] :: ";

        ItMemberEntity dbMbInfo = memberMapper.selectMemberInfoByMbNoSignature(mbNo, MbSignatureCd.INFLUEN_ACT);
        if( dbMbInfo == null)
        {
            log.error(logActName + "no influencer db info, MB_NO_" + mbNo );
            return null;
        }

        ProdInfluencerInfo influencerInfo = ProdInfluencerInfo.builder()
                .mbNo(dbMbInfo.getMbNo())
                .mbNick(dbMbInfo.getMbNick())
                .mbProfile(dbMbInfo.getMbProfile())
                .zzimCnt(zzimMapper.selectInfluenZzimCountByMbNo(mbNo))
                .build();

        return influencerInfo;
    }


    public ItItemEntity getProductInfo(Long itemSeq, Long mbNo) {
        ItItemEntity item = productMapper.selectItemInfoByItemSeq(itemSeq);
        return item;
    }


}
