package com.fanbakery.fancake.module.mypage.service;


import com.fanbakery.fancake.code.service.member.MbSignatureCd;
import com.fanbakery.fancake.common.model.ImgUrlInfo;
import com.fanbakery.fancake.module.mypage.model.ChangeInfluencerReqInfo;
import com.fanbakery.fancake.repository.mapper.InfluencerMapper;
import com.fanbakery.fancake.repository.mapper.MemberMapper;
import com.fanbakery.fancake.repository.model.ItMbInfulenerInfoEntity;
import com.fanbakery.fancake.repository.model.ItMemberEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MypageChangeInfluencerService {
    private final InfluencerMapper influencerMapper;
    private final MemberMapper memberMapper;


    public Boolean isPossibleInfluenConversion(Long myMbNo ) {

       ItMemberEntity dbMbInfo = memberMapper.selectMemberInfoByMbNo(myMbNo);
       if( dbMbInfo == null) {
           return false;
       }

       MbSignatureCd currMbSignature = dbMbInfo.getMbSignature();
       if( currMbSignature.equals(MbSignatureCd.FAN)
               || currMbSignature.equals(MbSignatureCd.INFLUEN_DENY) ) {
           return true;
       }

       return false;
    }


    public boolean checkDupNick(Long mbNo, String inputNick) {
        return influencerMapper.existInfluenNickByMbNick(mbNo, inputNick);
    }

    @Transactional
    public void addChangInfluenReqInfo(ChangeInfluencerReqInfo reqInfo) {
        //1. img 저장


        //2. insert influencer 추가 정보
        List<ImgUrlInfo> coverimgList = reqInfo.getCoverImg();
        ItMbInfulenerInfoEntity dbReqMbInfluenInfo = ItMbInfulenerInfoEntity.builder()
                .mbNo(reqInfo.getMbNo())
                .introduction(reqInfo.getIntroduction())
                .actYoutube(reqInfo.isActYoutube())
                .actAfreeca(reqInfo.isActAfreeca())
                .actTwitch(reqInfo.isActTwitch())
                .actBroadcast(reqInfo.isActBroadcast())
                .actInstagram(reqInfo.isActInstagram())
                .actWriter(reqInfo.isActWriter())
                .tempNick(reqInfo.getMbNick())
                .tempProfile(reqInfo.getProfile().getImgRealFileUrl())    //@@ : 승인 후 it_member.mb_profile 에 업데이트 필요!!
                .coverImg1(coverimgList.get(0).getImgRealFileUrl())
                .reqDate(LocalDateTime.now())
                .build();

        if (coverimgList.size()==2) dbReqMbInfluenInfo.setCoverImg2(coverimgList.get(1).getImgRealFileUrl());
        else if (coverimgList.size()==3) dbReqMbInfluenInfo.setCoverImg2(coverimgList.get(2).getImgRealFileUrl());

        influencerMapper.insertMbInfluencerInfo(dbReqMbInfluenInfo);

        //3. update member 에 influencer 정보
        ItMemberEntity dbReqMember= ItMemberEntity.builder()
                .mbNo(reqInfo.getMbNo())
                .mbSignature(MbSignatureCd.INFLUEN_ACT)
                .mbSignatureDate(LocalDate.now())
                .build();

        memberMapper.updateInfluenReqStatusByMbNo(dbReqMember);

        return;
    }


    public ItMemberEntity changeInfluencerActInfo(ItMemberEntity user) {

        Long mbNo = user.getMbNo();

        //1 check the mb_signature :: MbSignatureCd.INFLUEN_READY
        ItMemberEntity dbUser = memberMapper.selectMemberInfoByMbNo(mbNo);
        if(!dbUser.getMbSignature().equals(MbSignatureCd.INFLUEN_READY)) {
            log.error("[CHANG_INFLUEN_ACT :: not INFLUEN_READY !! MB_NO_" + mbNo );
            return dbUser;
        }

        //2. select request infleuncer info
        ItMbInfulenerInfoEntity reqInfo = influencerMapper.selectInfluenInfo(mbNo);
        if( reqInfo == null) {
            log.error("[CHANG_INFLUEN_ACT :: no influencer req info !! MB_NO_" + mbNo );
            return dbUser;
        }

        //@@@ nick, profile(reqInfo.tempProfile update 전에
        //
        String oldProfile = dbUser.getMbProfile();

        //3.update influencer act information
        //          it_mb_influen_info :: temp_nick, temp_profile
        //  ==>     it_member          :: nick, profile
        dbUser.setMbName(reqInfo.getTempNick());
        dbUser.setMbProfile(reqInfo.getTempProfile());
        dbUser.setMbSignature(MbSignatureCd.INFLUEN_ACT);       //요청 사항으로 INFLUN_REQ -> INFLUN_ACT로 변경
        dbUser.setMbSignatureCompleteDate(LocalDate.now());

        memberMapper.updateInfluenActStatusByMbNo(dbUser);


        //@@ DB update 성공하면
        // 기존 profile(dbUser.mbProfile) 은 삭제 해야 하나??
        //필요하면 삭제 로직 추가 !!
        //  oldProfile delete !!

        return dbUser;
    }
}
