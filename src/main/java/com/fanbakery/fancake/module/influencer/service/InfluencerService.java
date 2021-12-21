package com.fanbakery.fancake.module.influencer.service;


import com.fanbakery.fancake.code.service.item.ItemBiddingStatusCd;
import com.fanbakery.fancake.code.service.item.ItemSelStatusCd;
import com.fanbakery.fancake.code.service.member.MbSignatureCd;
import com.fanbakery.fancake.code.system.SystemDef;
import com.fanbakery.fancake.common.model.ContentListReq;
import com.fanbakery.fancake.common.model.Paging;
import com.fanbakery.fancake.module.influencer.model.InfluenMypageInfo;
import com.fanbakery.fancake.repository.mapper.InfluenItemMapper;
import com.fanbakery.fancake.repository.mapper.InfluencerMapper;
import com.fanbakery.fancake.repository.mapper.MemberMapper;
import com.fanbakery.fancake.repository.mapper.ZzimMapper;
import com.fanbakery.fancake.repository.model.InfluencerListInfoEntity;
import com.fanbakery.fancake.repository.model.ItMbInfulenerInfoEntity;
import com.fanbakery.fancake.repository.model.ItMemberEntity;
import com.fanbakery.fancake.repository.model.ItemListInfoEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class InfluencerService {

    private final InfluencerMapper influencerMapper;
    private final InfluenItemMapper influenItemMapper;
    private final MemberMapper memberMapper;
    private final ZzimMapper zzimMapper;


    public Paging<List<InfluencerListInfoEntity>> getInfluenListInfo(ContentListReq listReq) {

        //인플루언서 new 표시 체크 기간( 인플루언서 전환 완료일이 30일이내)
        listReq.setMbSignatureCompleteDate(LocalDate.now().minusDays(30));

        //1. select db
        List<InfluencerListInfoEntity> dbInfluenList = influencerMapper.selectInfluenListInfo(listReq);

        //2. set data
        Paging<List<InfluencerListInfoEntity>> paging = Paging.<List<InfluencerListInfoEntity>>builder()
                .pageIndex(ContentListReq.setResPageIndex(listReq.getPageIndex()))
                .cntPerPage(ContentListReq.setResCntPerPage(listReq.getCntPerPage()))
                .totalCnt(memberMapper.selectCountInfluencer(MbSignatureCd.INFLUEN_ACT))
                .lists(dbInfluenList)
                .build();

        return paging;
    }


    //인플루언서 상세 화면 데이터
    public InfluenMypageInfo getInflunMypageInfo(Long influenMbNo, ItMemberEntity user) {

        //1. select influencer info
        ItMemberEntity dbInfluenMb = memberMapper.selectMemberInfoByMbNoSignature(influenMbNo, MbSignatureCd.INFLUEN_ACT);
        if( dbInfluenMb == null) {
            return null;
        }

        //2. select db info
        ItMbInfulenerInfoEntity itMbInfulenerInfoEntity = influencerMapper.selectInfluenInfo(influenMbNo);
        if (StringUtils.hasLength(itMbInfulenerInfoEntity.getIntroduction())) {
            itMbInfulenerInfoEntity.setIntroduction(StringUtils.replace(itMbInfulenerInfoEntity.getIntroduction(), System.lineSeparator(), "<br/>"));
        }

        InfluenMypageInfo mypageInfo = InfluenMypageInfo.builder()
                .mbNo(dbInfluenMb.getMbNo())
                .mbNick(dbInfluenMb.getMbNick())
                .mbProfile(dbInfluenMb.getMbProfile())
                .zzimCnt(zzimMapper.selectInfluenZzimCountByMbNo(influenMbNo))
                .addInfo(itMbInfulenerInfoEntity)
                .topBuyer(influenItemMapper.selectTopBuyerByMbNoBidStatus(influenMbNo, ItemBiddingStatusCd.BID_SUCC))
                .sale(getSaleProdList(influenMbNo, user.getMbNo(), 1, SystemDef.PAGING_DEF_CONTENTS_SIZE))
                .endSale(getEndSaleProdList(influenMbNo, user.getMbNo(), 1, SystemDef.PAGING_DEF_CONTENTS_SIZE))
                .build();

        return mypageInfo;
    }





    //진행중인 상품 리스트
    public List<ItemListInfoEntity> getSaleProdList(Long influenMbNo, Long myMbNo, int pageIndex, int cntPerPage) {
        //1. sql parameter setting
        ContentListReq listReq = ContentListReq.builder()
                .mbNo(myMbNo)
                .influenMbNo(influenMbNo)
                .itemStatus(ItemSelStatusCd.SALE)
                .startDate(LocalDate.now())
                .pageIndex(ContentListReq.setReqPageIndex(pageIndex))
                .cntPerPage(cntPerPage)
                .build();


        //2.select db info
        List<ItemListInfoEntity> itemList = influenItemMapper.selectSaleProdListByMbNo(listReq);

        return itemList;
    }



    //종료 상품 리스트
    public List<ItemListInfoEntity> getEndSaleProdList(Long influenMbNo, Long myMbNo, int pageIndex, int cntPerPage) {
        //1. sql parameter setting
        ContentListReq listReq = ContentListReq.builder()
                .mbNo(myMbNo)
                .influenMbNo(influenMbNo)
                .itemStatus(ItemSelStatusCd.SALE)
                .startDate(LocalDate.now())
                .pageIndex(ContentListReq.setReqPageIndex(pageIndex))
                .cntPerPage(cntPerPage)
                .build();


        //2.select db info
        List<ItemListInfoEntity> itemList = influenItemMapper.selectEndSaleProdListByMbNo(listReq);

        return itemList;
    }

}
