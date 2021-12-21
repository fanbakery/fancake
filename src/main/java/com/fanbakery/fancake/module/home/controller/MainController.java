package com.fanbakery.fancake.module.home.controller;

import com.fanbakery.fancake.api.email.MailService;
import com.fanbakery.fancake.api.email.MailTO;
import com.fanbakery.fancake.code.service.member.MbRouteCd;
import com.fanbakery.fancake.code.service.member.MbSignatureCd;
import com.fanbakery.fancake.code.system.SystemDef;
import com.fanbakery.fancake.common.utils.GenCertStringUtil;
import com.fanbakery.fancake.module.home.model.FindIdReq;
import com.fanbakery.fancake.module.home.model.FindPwReq;
import com.fanbakery.fancake.module.home.service.MainContentsService;
import com.fanbakery.fancake.module.home.service.MainFindService;
import com.fanbakery.fancake.module.join.service.JoinService;
import com.fanbakery.fancake.repository.model.ItMemberEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {
    private final MainFindService findService;
    private final JoinService joinService;

    private final MainContentsService contentsService;

    private final MailService mailService;

    // Intro
    @GetMapping("/intro")
    public String intro(){
        return "home/intro";
    }

    // 메인화면
    @GetMapping("/")
    public String mainScreen(Model model, HttpSession session){

        String page = "home/home";
        ItMemberEntity user = (ItMemberEntity)session.getAttribute("user");

        //1. check 인플루언서 전환 결과 - 로그인 후 1번만 check!!
        if (!user.getMbSignature().equals(MbSignatureCd.FAN)) {
            //1-1) 인플루언서 승인 완료
            if (user.getMbSignature().equals(MbSignatureCd.INFLUEN_READY)) {
                //@@ 팝업 확인 버튼 클릭하면 MbSignatureCd.INFLUEN_ACT 로 업데이트 !!
                model.addAttribute("influen_status", MbSignatureCd.INFLUEN_READY);
            }
            //1-2) 인플루언서 승인 거절
            else if (user.getMbSignature().equals(MbSignatureCd.INFLUEN_DENY)) {
                model.addAttribute("influen_status", MbSignatureCd.INFLUEN_DENY);
            }
        }

        //2. 팬, 인플루언서(활돌)
        int pageIndex = 1;

        model.addAttribute("popularProd", contentsService.getPopularProductList(user.getMbNo(), 10));
        model.addAttribute("recentProd", contentsService.getRecentProductList(user.getMbNo(), pageIndex, 10));
        model.addAttribute("seenProd", contentsService.getSeenProductList(user.getMbNo(), pageIndex, 10));
        model.addAttribute("biddingProd", contentsService.getBiddingProductList(user.getMbNo(), pageIndex,  SystemDef.PAGING_DEF_CONTENTS_SIZE));

        return page;
    }

    @GetMapping("/find-id")
    public String find_id(Model model){
        model.addAttribute("findIdReq",new FindIdReq());
        return "home/findid";
    }


    @PostMapping("/find-id")
    public String find_id_proc(FindIdReq findIdReq, Errors errors, Model model, HttpSession session){
        // 1. validation 에러가 있을 경우
        if (errors.hasErrors()) {
            return "home/findid";
        }

        if( findIdReq.getUserCert() == null || findIdReq.getUserCert().isEmpty() ) {
            model.addAttribute("result_cert", "인증번호를 입력해 주세요");
            return "home/findid";
        }
        //@@ TODO :: 입력받은 인증 번호 확인 절차 추가 작업 필요
        ItMemberEntity user = findService.findUserPhone(findIdReq);

        if(user != null) {
            //@@@ TODO :: 인증번호 확인 및

            //이메일 발송후 화면 전환
            model.addAttribute("email", user.getMbEmail());
            return "home/findid2";
        } else {
            model.addAttribute("result", "존재하지 않는 이메일 주소입니다.");
        }
        return "home/findid3";
    }


    @GetMapping("/find-id2")
    public String find_id2(){
        return "home/findid2";
    }

    @GetMapping("/find-id3")
    public String find_id3(){
        return "home/findid3";
    }

    @GetMapping("/find-pw")
    public String find_pw(Model model){
        model.addAttribute("findPwReq",new FindPwReq());
        return "home/findpw";
    }

    @PostMapping("/find-pw")
    public String find_pw_proc(@Valid FindPwReq findPwReq, Errors errors, Model model, HttpSession session){
        // 1. validation 에러가 있을 경우
        if (errors.hasErrors()) {
            return "home/findpw";
        }

        ItMemberEntity user = findService.findUserMail(findPwReq);

        Boolean isSns = false;
        if(user != null) {
            if( user.getMbRoute().equals(MbRouteCd.APP)) {
                //@@@ TODO :: 비밀번호 재 설정 이메일 발송 process 추가 필요

                String tempPw = GenCertStringUtil.generateCertString();
                String emailMsg =  user.getMbName() + "님 임시비밀 번호는 "
                                    + tempPw +"입니다.";
                try{
                    MailTO mailTO = MailTO.builder()
                            .address(user.getMbEmail()) // 보낼주소
                            .title("[fancake] 임시 비밀번호 입니다.") //제목
                            .message(emailMsg).build(); //내용
                    mailService.sendMail(mailTO);

                    findService.resetTempUserPW(user, tempPw );
                }catch (Exception e) {
                    log.error("[FIND_PW] :: 이메일 전송 실패 !!\n " + e.getMessage());
                    model.addAttribute("result", "시스템 오류 입니다. 잠시 후 다시 시도해 주세요");

                    return "home/findpw";
                }
            } else {
                isSns = true;
            }
            model.addAttribute("email", user.getMbEmail());
            model.addAttribute("isSns", isSns);

            return "home/findpw2";
        } else {
            model.addAttribute("result", "존재하지 않는 이메일 주소입니다.");
        }
        return "home/findpw";
    }


    @GetMapping("/withdraw")
    public String withdraw(){
        return "home/withdraw";
    }
}
