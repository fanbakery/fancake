package com.fanbakery.fancake.module.join.controller;

import com.fanbakery.fancake.module.join.model.JoinInfoReq;
import com.fanbakery.fancake.module.join.service.JoinService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class JoinController {
    private final JoinService joinService;

    @GetMapping("/join")
    public String joinView( Model model)
    {
        model.addAttribute("joinInfoReq",new JoinInfoReq());
        return "join/join";
    }

    @PostMapping("/join")
    public String joinEmail(@Valid JoinInfoReq joinInfoReq, Errors errors, Model model) throws Exception {
        // 1. validation 에러가 있을 경우
        if (errors.hasErrors()) {
            return "join/join";
        }
        //2.check Nick name null
        if( !joinInfoReq.checkUserNick() ) {
            model.addAttribute("resultNick", "닉네임은 2~10 글사이로 입력하셔야 합니다.(특수문자제외)");
            return "join/join";
        };

        //3. check agree
        if( !joinInfoReq.isAgree()) {
            model.addAttribute("resultAgree", "동의여부를 확인해야 합니다.");
            return "join/join";
        }

        //4. 이메일 중복 확인
        if( joinService.checkDupEmail(joinInfoReq.getUserMail())) {
            model.addAttribute("resultMail", "이미 가입된 이메일입니다.");
            return "join/join";
        }

        //5. 닉네임 불가 닉네임 확인 및 중복 확인
        if( joinService.checkBlockNick(joinInfoReq.getUserNick())) {
            model.addAttribute("resultNick", "사용할 수 없는 닉네임입니다.");
            return "join/join";
        }

        if( joinService.checkDupNick(joinInfoReq.getUserNick())) {
            model.addAttribute("resultNick", "이미 사용된 닉네임입니다.");
            return "join/join";
        }

        //6. 핸드폰 중복
        if( joinService.checkDupHp(joinInfoReq.getUserPhone())) {
            model.addAttribute("resultPhone", "이미 가입된 핸드폰입니다.");
            return "join/join";
        }


        //7. 이미지 파일 저장
        if (StringUtils.hasLength(joinInfoReq.getUserProfTempFileName())) {
            try{
                joinService.moveProfileImage(joinInfoReq, joinInfoReq.getUserProfTempFileName());
            }
            catch (Exception e) {
                log.error(e.getMessage(), e);
                model.addAttribute("result", "파일 업로드를 실패 했습니다. 다시 시도 해주세요 ");
                joinInfoReq.setUserProfTempUrlPath(null);
                return "join/join";
            }
        }

       //6.db insert
        try {
            Long mbNo = joinService.joinEmail( joinInfoReq );
            if( mbNo <= 0) {
                model.addAttribute("result", "가입에 실패 했습니다. 다시 시도 해주세요 \n"
                                        + "\t ID_" + joinInfoReq.getUserMail() + ", NICK_" + joinInfoReq.getUserNick());
                return "join/join";
            }
        } catch (Exception e){
            log.error("[JOIN_MAIL] 가입에 실패 했습니다. : " + e.getMessage());
            model.addAttribute("result", "가입에 실패 했습니다. 다시 시도 해주세요 \n"
                                    + "\t ID_" + joinInfoReq.getUserMail() + ", NICK_" + joinInfoReq.getUserNick());
            return "join/join";
        }


        log.info("[JOIN_MAIL] ID_" + joinInfoReq.getUserMail() + ", NICK_" + joinInfoReq.getUserNick());
        return "redirect:/";
    }


    @GetMapping("/joinkakao")
    public String joinkakao(){
        return "join/joinkakao";
    }


    @GetMapping("/joinfacebook")
    public String joinfacebook(){
        return "join/joinfacebook";
    }

}
