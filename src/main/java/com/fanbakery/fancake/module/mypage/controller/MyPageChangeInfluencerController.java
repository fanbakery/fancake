package com.fanbakery.fancake.module.mypage.controller;

import com.fanbakery.fancake.code.service.DirectoryCode;
import com.fanbakery.fancake.common.model.ImgUrlInfo;
import com.fanbakery.fancake.common.utils.DateUtil;
import com.fanbakery.fancake.config.ApplicationConfig;
import com.fanbakery.fancake.module.mypage.model.ChangeInfluencerReqInfo;
import com.fanbakery.fancake.module.mypage.service.MypageChangeInfluencerService;
import com.fanbakery.fancake.repository.model.ItMemberEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MyPageChangeInfluencerController {

    private final MypageChangeInfluencerService changeInfluencerService;
    private final ApplicationConfig applicationConfig;


    @GetMapping("/change_influencer/step1")        // 인플로언스 전환 - 1단계
    public String change_influencer_step1(Model model, HttpSession session){
        ItMemberEntity user = (ItMemberEntity)session.getAttribute("user");
        Long myMbNo = user.getMbNo();
        //check mbSignature

        if(!changeInfluencerService.isPossibleInfluenConversion(myMbNo) ) {
            log.error("Not possible infuencer conversion [MB_NO_" + myMbNo + "]" );
            return "redirect:/mypage/";
        }

        //set Request data form

        ChangeInfluencerReqInfo chgInfuencerReq = ChangeInfluencerReqInfo.builder()
                .mbNo(myMbNo)
                .build();

        model.addAttribute("chgInfuencerReq", chgInfuencerReq );
        return "mypage/change_influencer/step1";
    }


    @PostMapping("/change_influencer/step1") // 인플로언스 전환 - 1단계 (nick, profile, channel)
    public String change_influencer_step1_process(@Valid ChangeInfluencerReqInfo chgInfuencerReq, Model model, HttpSession session) {

        ItMemberEntity user = (ItMemberEntity)session.getAttribute("user");
        Long myMbNo = user.getMbNo();


        //1. check mbSignature
        if(!changeInfluencerService.isPossibleInfluenConversion(myMbNo) ) {
            log.error("Not possible infuencer conversion [MB_NO_" + myMbNo + "]" );
            return "redirect:/mypage/";
        }

        //2.check request data
        if( !chgInfuencerReq.isSetAnyActChannel() ) {
            model.addAttribute("errChannel", "활동 채널을 선택해 주세요" );

            model.addAttribute("chgInfuencerReq", chgInfuencerReq );
            return "mypage/change_influencer/step1";
        }

        //2-2 check dup nick
        if( changeInfluencerService.checkDupNick(myMbNo, chgInfuencerReq.getMbNick())) {
            model.addAttribute("errNick", "중복된 활동명입니다. 다시 입력해주세요" );
            model.addAttribute("chgInfuencerReq", chgInfuencerReq );
            return "mypage/change_influencer/step1";
        }

        //3. save profile img ??
        //@@@@ ---

        //4. go to next step
        model.addAttribute("chgInfuencerReq", chgInfuencerReq );

        return "mypage/change_influencer/step2";
    }


    @PostMapping("/change_influencer/step2")        // 인플로언스 전환 - 2단계 ( cover img )
    public String change_influencer_step2(@Valid ChangeInfluencerReqInfo chgInfuencerReq, Model model, HttpSession session) {

        ItMemberEntity user = (ItMemberEntity)session.getAttribute("user");
        Long myMbNo = user.getMbNo();

        //1. check mbSignature
        if(!changeInfluencerService.isPossibleInfluenConversion(myMbNo) ) {
            log.error("Not possible infuencer conversion [MB_NO_" + myMbNo + "]" );
            return "redirect:/mypage/";
        }

        //2. go to next step
        model.addAttribute("chgInfuencerReq", chgInfuencerReq );

        return "mypage/change_influencer/step3";
    }


    @PostMapping("/change_influencer/step3")         // 인플로언스 전환 - 2단계 ( 소개글 )
    public String change_influencer_step2_process(@Valid ChangeInfluencerReqInfo chgInfuencerReq
            , Model model, HttpSession session) {

        ItMemberEntity user = (ItMemberEntity)session.getAttribute("user");
        Long myMbNo = user.getMbNo();
        chgInfuencerReq.setMbNo(myMbNo);

        //1. check mbSignature
        if(!changeInfluencerService.isPossibleInfluenConversion(myMbNo) ) {
            log.error("Not possible infuencer conversion [MB_NO_" + myMbNo + "]" );
            return "redirect:/mypage/";
        }

        model.addAttribute("chgInfuencerReq", chgInfuencerReq );

        //2.check request data
        /*if( !chgInfuencerReq.isSetAnyActChannel() ) {
            model.addAttribute("errChannel", "활동 채널을 선택해 주세요" );

            return "mypage/change_influencer/step1";
        }

        if( !chgInfuencerReq.isSetAnyCoverImg() ) {
            model.addAttribute("errCoverImg", "커버사진을 등록 주세요" );
            return "mypage/change_influencer/step3";
        }*/


        //2-2 check dup nick
        if( changeInfluencerService.checkDupNick(myMbNo, chgInfuencerReq.getMbNick())) {
            model.addAttribute("errNick", "중복된 활동명입니다. 다시 입력해주세요" );
            return "mypage/change_influencer/step1";
        }


        // 2-3 파일 이동 처리 (profile)
        try {
            chgInfuencerReq.setProfile(new ImgUrlInfo());
            chgInfuencerReq.getProfile().setImgRealFileUrl(
                    moveTempFile(chgInfuencerReq.getTemp_profile())
            );
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return "redirect:/mypage/";
        }


        // 2-4 파일 이동 처리 (cover 이미지)
        try {
            chgInfuencerReq.setCoverImg(new ArrayList<>());
            for(int i=0; i<chgInfuencerReq.getTemp_coverImg().size(); i++) {
                String realUrlPath = moveTempFile(chgInfuencerReq.getTemp_coverImg().get(i));
                ImgUrlInfo tempImgUrlInfo = new ImgUrlInfo();
                tempImgUrlInfo.setImgRealFileUrl(realUrlPath);
                chgInfuencerReq.getCoverImg().add(tempImgUrlInfo);
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return "redirect:/mypage/";
        }

        //3.insert db
        try {
            changeInfluencerService.addChangInfluenReqInfo(chgInfuencerReq);

            //update seesion user info
            ItMemberEntity changUserInfo = changeInfluencerService.changeInfluencerActInfo(user);
            session.removeAttribute("user");
            session.removeAttribute("userType");
            session.setAttribute("user", changUserInfo);
            session.setAttribute("userType", changUserInfo.getMbSignature());
        } catch ( Exception e) {
            log.error(e.getMessage(), e);
            return "redirect:/mypage/";
        }

        return "mypage/change_influencer/stepOk";
    }



    // 프로파일 이미지 --> 이동
    private String moveTempFile(String tempProfFile) throws IOException {
        // 디렉토리 경로 --> /full-path/profile/yyyy
        String fullPathDir = String.format("%s/%s/%s", applicationConfig.getUploadConfig().getPhysicalPath(),
                DirectoryCode.INFLUENCER_COVER_DIR.getCode(),
                DateUtil.getCurrentYear());

        String tempFileFullPath = String.format("%s%s/%s", applicationConfig.getUploadConfig().getPhysicalPath(),
                DirectoryCode.TEMP_DIR.getCode(),
                tempProfFile);
        String realFileFullPath = String.format("%s/%s", fullPathDir, tempProfFile.substring(4));

        File fileProfile = new File(fullPathDir);
        File tempFile = new File(tempFileFullPath);
        File realFile = new File(realFileFullPath);

        // 미 존재시 디렉토리 생성.
        if (!fileProfile.exists()) {
            FileUtils.forceMkdir(fileProfile);
        }

        // temp 파일 이동
        FileUtils.moveFile(tempFile, realFile);

        // 실제 URL 경로 저장.
        return String.format("%s/%s/%s/%s", applicationConfig.getUploadConfig().getUrlPath(),
                        DirectoryCode.INFLUENCER_COVER_DIR.getCode(),
                        DateUtil.getCurrentYear(), tempProfFile.substring(4));
    }
}
