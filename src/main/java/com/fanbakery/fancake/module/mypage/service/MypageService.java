package com.fanbakery.fancake.module.mypage.service;

import com.fanbakery.fancake.code.service.item.ItemBiddingStatusCd;
import com.fanbakery.fancake.code.service.item.ItemSelStatusCd;
import com.fanbakery.fancake.code.service.item.SettlementStatusCd;
import com.fanbakery.fancake.code.service.member.MbSignatureCd;
import com.fanbakery.fancake.common.model.ContentListReq;
import com.fanbakery.fancake.module.mypage.model.MyInfo;
import com.fanbakery.fancake.module.upload.service.FileHandlingService;
import com.fanbakery.fancake.repository.mapper.*;
import com.fanbakery.fancake.repository.model.ItFaqEntity;
import com.fanbakery.fancake.repository.model.ItFaqMasterEntity;
import com.fanbakery.fancake.repository.model.ItMbInfulenerInfoEntity;
import com.fanbakery.fancake.repository.model.ItMemberEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MypageService {

    private final MypageMapper mypageMapper;
    private final MemberMapper memberMapper;
    private final OrderMapper orderMapper;
    private final FileHandlingService fileHandlingService;
    private final InfluencerMapper influencerMapper;
    private final InfluenItemMapper influenItemMapper;

    private final BiddingMapper biddingMapper;


    public ItMemberEntity getMyMemberInfo(Long mbNo) {

        return memberMapper.selectMemberInfoByMbNo(mbNo);
    }


    public MyInfo getMypageInfo( Long mbNo ) {

        ItMemberEntity dbUser = memberMapper.selectMemberInfoByMbNo(mbNo);

        //1. db reqeust param setting
        ContentListReq listReq = ContentListReq.builder()
                .mbNo(mbNo)
                .biddingStatus(ItemBiddingStatusCd.BID_SUCC)
                .itemStatus(ItemSelStatusCd.SALE)
                .startDate(LocalDate.now())
                .build();

        //check 입찰 참여중 개수
        Integer saleBidCnt = orderMapper.selectSaleMyBidCntByMbNoStatus(listReq);
        if( saleBidCnt == null ) {
            saleBidCnt = 0;
        }

        //check 낙찰 개수
        Integer endSaleBidCnt = orderMapper.selectEndSaleMyBidCntByMbNoStatus(listReq);
        if( endSaleBidCnt == null ) {
            endSaleBidCnt = 0;
        }

        MyInfo myInfo = MyInfo.builder()
                .mbNick(dbUser.getMbNick())
                .mbSignature(dbUser.getMbSignature())
                .mbProfile(dbUser.getMbProfile())
                .saleBidCnt(saleBidCnt)
                .endSaleBidCnt(endSaleBidCnt)
                .totalCnt(saleBidCnt + endSaleBidCnt)
                .build();

        // 배송지 주소
        if(StringUtils.hasLength(dbUser.getMbAddr1())) {
            myInfo.setShippingAddr(dbUser.getMbAddr1() + " " + dbUser.getMbAddr2());
        }

        //인플루언서일 경우 상품 보낼 주소 정보 세팅
        if( dbUser.getMbSignature().equals(MbSignatureCd.INFLUEN_ACT)) {
            myInfo.setProductSendAddr(""); //@@ TODO: 관리자 화면 주소 추가시.
        }
        return myInfo;
    }

    //update nick
    public void updateNick(Long mbNo, String chgNick) {
        memberMapper.updateMbNickByMbNo(mbNo, chgNick);
        return;
    }

    //update nick
    public String changeProfile(Long mbNo, String tempFileName) throws IOException {

        // 1. file move
        String profileUrl = fileHandlingService.moveProfileImage(tempFileName);
        memberMapper.updateMbPorfileByMbNo(mbNo, profileUrl);

        return profileUrl;
    }

    public ItMbInfulenerInfoEntity selectInfluenInfo(Long mbNo) {
        return influencerMapper.selectInfluenInfo(mbNo);
    }

    public void changeIntroduction(Long mbNo, String introduction) {
        influencerMapper.updateIntroduce(mbNo, introduction);
    }

    public void changeCoverImage(Long mbNo, String tempCoverImg1, String tempCoverImg2, String tempCoverImg3) throws IOException {
        String realCoverImg1 = null;
        String realCoverImg2 = null;
        String realCoverImg3 = null;

        // 1. file1 move
        if(StringUtils.hasLength(tempCoverImg1)) {
            realCoverImg1 = fileHandlingService.moveCoverImage(tempCoverImg1);
        }

        if(StringUtils.hasLength(tempCoverImg2)) {
            realCoverImg2 = fileHandlingService.moveCoverImage(tempCoverImg2);
        }

        if(StringUtils.hasLength(tempCoverImg3)) {
            realCoverImg3 = fileHandlingService.moveCoverImage(tempCoverImg3);
        }

        influencerMapper.updateCoverImage(mbNo, realCoverImg1, realCoverImg2, realCoverImg3);
    }



    public List<ItFaqEntity> getFaqList() {
        return mypageMapper.selectFaqList();
    }

    public ItFaqMasterEntity getFaqMasterInfo(Long fmId) {
        return mypageMapper.selectFaqMasterByFmId(fmId);
    }

    public boolean checkAvailUnRegister(ItMemberEntity user) {

        Long mbNo = user.getMbNo();
        Boolean isNotAvailable;

        //1. check bidding status
        isNotAvailable = biddingMapper.existBidJoinItemByMbNo(mbNo, ItemSelStatusCd.RECEIPTED);
        if( isNotAvailable != null && isNotAvailable) {
            log.error("[CHK_UN_REGISTER] ::  not available unregister !! exist bidding item!! MB_NO_" + mbNo);
            return isNotAvailable;
        }

        //2. 인플루언서 일경우, 판매 중/미정산 있는지 확인
        if(user.getMbIsUnregistered().equals(MbSignatureCd.INFLUEN_ACT) ) {
            //check 판매 중 상품
            isNotAvailable = influenItemMapper.existInfluenSaleItemByMbNo(mbNo
                                                                , ItemSelStatusCd.RECEIPTED
                                                                , SettlementStatusCd.COMPLETE);
            if( isNotAvailable != null && isNotAvailable) {
                log.error("[CHK_UN_REGISTER] ::  not available unregister !! Influencer exist Sale item or Settlement !! MB_NO_" + mbNo);
                return isNotAvailable;
            }
        }

        return isNotAvailable;
    }


    public void setUnregister(Long mbNo) {

        memberMapper.updateUnregister(mbNo, LocalDateTime.now());
        return;
    }
}
