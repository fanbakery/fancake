package com.fanbakery.fancake.module.mypage.controller;

import com.fanbakery.fancake.common.model.ApiResponse;
import com.fanbakery.fancake.config.ApplicationConfig;
import com.fanbakery.fancake.exception.ApiErrorCode;
import com.fanbakery.fancake.exception.ApiServiceException;
import com.fanbakery.fancake.module.join.service.JoinService;
import com.fanbakery.fancake.module.mypage.model.MyInfo;
import com.fanbakery.fancake.module.mypage.service.MypageService;
import com.fanbakery.fancake.repository.model.ItMemberEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mypage")
public class MypageRestController {

    private final MypageService mypageService;
    private final JoinService joinService;
    private final ApplicationConfig applicationConfig;

    @GetMapping("/baseInfo")        //get nick, profile
    public ResponseEntity<?> getMyProfileInfo(HttpSession session) {

        ItMemberEntity user = (ItMemberEntity)session.getAttribute("user");

        //1. get db user info
        ItMemberEntity dbUser = mypageService.getMyMemberInfo(user.getMbNo());

        //2. set res data
        MyInfo myInfo = MyInfo.builder()
                .mbNick(dbUser.getMbNick())
                .mbProfile(dbUser.getMbProfile())
                .build();


        ApiResponse<MyInfo> res = ApiResponse.<MyInfo>builder()
                .data(myInfo)
                .build();

       return ResponseEntity.ok(res);
    }


    @PostMapping("/change/nick")
    public ResponseEntity<?> changeNick(@RequestParam(name="nick") String chgNick, HttpSession session) {

        ItMemberEntity user = (ItMemberEntity)session.getAttribute("user");

        //1. get db user info
        ItMemberEntity dbUser = mypageService.getMyMemberInfo(user.getMbNo());

        //2. check duplicate nick
        if( joinService.checkDupNick(chgNick) ){
            log.error("[CHG_NICK] :: Duplicate nick("+ chgNick +"), MB_NO_" );

            throw new ApiServiceException(ApiErrorCode.ERR_DATA_DUPLICATE, "닉네임 중복입니다. 다시 변경 해주세요");
        }

        //3. update nick
        mypageService.updateNick(user.getMbNo(), chgNick);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/change/introduction")
    public ResponseEntity<Map> changeIntroduction(@RequestParam(name="introduction") String introduction, HttpSession session) {

        ItMemberEntity user = (ItMemberEntity)session.getAttribute("user");
        Map<String, String> map = new HashMap<>();

        mypageService.changeIntroduction(user.getMbNo(), introduction);
        map.put("result", "success");

        return ResponseEntity.ok().body(map);
    }


    @PostMapping("/change/profile")
    public ResponseEntity<Map> changeProfile(@RequestParam(name="tempFileName") String tempFileName, HttpSession session) {

        ItMemberEntity user = (ItMemberEntity)session.getAttribute("user");
        Map<String, String> map = new HashMap<>();

        try {
            String profileUrl = mypageService.changeProfile(user.getMbNo(), tempFileName);
            map.put("profileUrl", profileUrl);
            map.put("result", "success");
        } catch (IOException e) {
            map.put("result", "fail");
            map.put("resultMsg", "처리 중 오류가 발생하였습니다.");
        }

        return ResponseEntity.ok().body(map);
    }

    @PostMapping("/change/cover_img")
    public ResponseEntity<Map> changeCoverImage(@RequestParam(name="temp_coverImg1") String tempCoverImg1,
                                             @RequestParam(name="temp_coverImg2") String tempCoverImg2,
                                             @RequestParam(name="temp_coverImg3") String tempCoverImg3,
                                             HttpSession session) {

        ItMemberEntity user = (ItMemberEntity)session.getAttribute("user");
        Map<String, String> map = new HashMap<>();

        try {
            mypageService.changeCoverImage(user.getMbNo(), tempCoverImg1, tempCoverImg2, tempCoverImg3);
            map.put("result", "success");
        } catch (IOException e) {
            map.put("result", "fail");
            map.put("resultMsg", "처리 중 오류가 발생하였습니다.");
        }

        return ResponseEntity.ok().body(map);
    }
}

