package com.fanbakery.fancake.module.mypage.controller;

import com.fanbakery.fancake.code.service.member.MbSignatureCd;
import com.fanbakery.fancake.module.mypage.model.MyInfo;
import com.fanbakery.fancake.module.mypage.service.MypageService;
import com.fanbakery.fancake.repository.model.ItFaqEntity;
import com.fanbakery.fancake.repository.model.ItFaqMasterEntity;
import com.fanbakery.fancake.repository.model.ItMbInfulenerInfoEntity;
import com.fanbakery.fancake.repository.model.ItMemberEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MypageController {

    private final MypageService mypageService;


    @GetMapping("/")        //fan, 인플루언서 승인대기
    public String index(Model model, HttpSession session){

        ItMemberEntity user = (ItMemberEntity)session.getAttribute("user");

        MyInfo myInfo = mypageService.getMypageInfo(user.getMbNo());

        if(user.getMbSignature().equals(MbSignatureCd.INFLUEN_ACT)) {
            ItMbInfulenerInfoEntity itMbInfulenerInfoEntity = mypageService.selectInfluenInfo(user.getMbNo());
            if(StringUtils.hasLength(itMbInfulenerInfoEntity.getCoverImg1())) myInfo.setInfluencerCoverImg(itMbInfulenerInfoEntity.getCoverImg1());
            else if(StringUtils.hasLength(itMbInfulenerInfoEntity.getCoverImg2())) myInfo.setInfluencerCoverImg(itMbInfulenerInfoEntity.getCoverImg2());
            else if(StringUtils.hasLength(itMbInfulenerInfoEntity.getCoverImg3())) myInfo.setInfluencerCoverImg(itMbInfulenerInfoEntity.getCoverImg3());
        }

        model.addAttribute("myInfo", myInfo);
        return "mypage/mypage";
    }

    @GetMapping("/mypage_cover")
    public String mypage_cover(Model model, HttpSession session){

        ItMemberEntity user = (ItMemberEntity)session.getAttribute("user");

        ItMbInfulenerInfoEntity itMbInfulenerInfoEntity = mypageService.selectInfluenInfo(user.getMbNo());

        model.addAttribute("influenceInfo", itMbInfulenerInfoEntity);
        return "mypage/mypage_cover";
    }

    @GetMapping("/mypage_introduce")
    public String mypage_introduce(Model model, HttpSession session){

        ItMemberEntity user = (ItMemberEntity)session.getAttribute("user");

        ItMbInfulenerInfoEntity itMbInfulenerInfoEntity = mypageService.selectInfluenInfo(user.getMbNo());
        model.addAttribute("influenceInfo", itMbInfulenerInfoEntity);

        return "mypage/mypage_introduce";
    }


    @GetMapping("/change/cover")
    public String change_cover(){
        return "mypage/change_cover";
    }

    @GetMapping("/change/intro")
    public String change_introduction(){
        return "mypage/change-introduction";
    }

    @GetMapping("/myinfo")
    public String myinfo(){
        return "mypage/myinfo";
    }

    @GetMapping("/terms")
    public String terms(){
        return "mypage/terms";
    }

    @GetMapping("/faq")
    public String intro(Model model, HttpSession session){

        List<ItFaqEntity> faqList = mypageService.getFaqList();

        model.addAttribute("faqList", faqList);

//        if(faqList != null) {
//            ItFaqMasterEntity faqMaster = mypageService.getFaqMasterInfo(faqList.get(0).getFmId());
//            model.addAttribute("faqMaster", faqMaster);
//        }else {
            model.addAttribute("faqMaster", ItFaqMasterEntity.builder().build());
//        }
        return "mypage/faq";
    }

    @GetMapping("/unregister")
    public String unregister(Model model, HttpSession session){
        boolean unreg = false;

        model.addAttribute("unreg", unreg);
        return "mypage/unregister";
    }


    @PostMapping("/unregister")
    public String unregister_process(Boolean unreg, Model model, HttpSession session){
        ItMemberEntity user = (ItMemberEntity)session.getAttribute("user");

        //1. check param
        if( unreg ==null || !unreg ) {
            model.addAttribute("resultUnreg", "탈퇴 동의에 체크 해주세요");
            return "mypage/unregister";
        }

        //2. check bidding, sale item, settlement
        boolean isNotAvailable = mypageService.checkAvailUnRegister(user);
        if( isNotAvailable ) {
            model.addAttribute("resultErr","현재 입찰 중인 상품이 있어 탈퇴가 불가능합니다.");
            return "mypage/unregister";
        }

        //3. unregister
        mypageService.setUnregister(user.getMbNo());

        return "mypage/unregister_ok";
    }
    
}
