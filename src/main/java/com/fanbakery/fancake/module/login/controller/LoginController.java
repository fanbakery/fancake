package com.fanbakery.fancake.module.login.controller;

import com.fanbakery.fancake.code.service.member.MbRouteCd;
import com.fanbakery.fancake.common.utils.AuthUtil;
import com.fanbakery.fancake.config.ApplicationConfig;
import com.fanbakery.fancake.module.join.model.JoinInfoReq;
import com.fanbakery.fancake.module.join.service.JoinService;
import com.fanbakery.fancake.module.login.model.KakaoProfile;
import com.fanbakery.fancake.module.login.model.LoginInfoReq;
import com.fanbakery.fancake.module.login.model.OAuthToken;
import com.fanbakery.fancake.module.login.service.LoginService;
import com.fanbakery.fancake.repository.model.ItMemberEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final JoinService joinService;

    private final ApplicationConfig applicationConfig;


    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("loginInfoReq",new LoginInfoReq());
        return "login/login";
    }

    @PostMapping("/login")
    public String loginProcess(@Valid LoginInfoReq loginInfoReq, Errors errors, Model model, HttpSession session){
        // 1. validation ????????? ?????? ??????
        if (errors.hasErrors()) {
            return "login/login";
        }

        ItMemberEntity user = loginService.loginCheck(loginInfoReq);
        if(user!=null){
            if (!user.getMbPassword().equals(AuthUtil.encrypt_SHA256(loginInfoReq.getPassword()))) {
                model.addAttribute("result", "??????????????? ???????????? ????????????.");
            }
            else {
                //?????? ???..sns ???????????? ?????? ?????? ???????????? ???.
                user.setMbId(user.getMbEmail());

//                session.setMaxInactiveInterval(60*60*8); // 8??????
                session.setAttribute("userId", user.getMbEmail());
                session.setAttribute("user", user);
                session.setAttribute("userType", user.getMbSignature());//user.mbSignature ??? user type ???.
                session.setAttribute("adult", user.getMbAdult());//user.mbSignature ??? user type ???.
                return "redirect:/";
            }
        }
        else{
            model.addAttribute("result", "????????? ????????? ???????????? ????????????.");
        }

        return "login/login";
    }


    @GetMapping("/login_fb")
    public String login_fb(){
        return "login/login_fb";
    }

    @GetMapping("/login_fb2")
    public String login_fb2(){
        return "login/login_fb2";
    }

    @GetMapping("/login_ka")
    public String login_ka(){
        return "login/login_ka";
    }

    @GetMapping("/auth/kakao/callback")
    public String kakaoCallback(String code, HttpSession session) throws JsonProcessingException {

        //POST ???????????? key=value ???????????? ?????? (??????????????????)
        RestTemplate rt = new RestTemplate();

        //HttpHeader ???????????? ??????
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        //HttpBody ???????????? ??????
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "cbd9e68b0d7695133e71098a963a6230");
        params.add("redirect_uri", "https://thefancake.com/auth/kakao/callback");
        params.add("code", code);

        //Httpheader??? HttpBody??? ????????? ??????????????? ??????
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
                new HttpEntity<>(params, headers);

        //Http ???????????? POST ???????????? ????????? response
        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        //Gson, Json Simple, objectMapper

        OAuthToken oAuthToken = null;
        ObjectMapper objectMapper = new ObjectMapper();
        oAuthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);

        System.out.println("dd" + oAuthToken.getAccess_token());

        //POST ???????????? key=value ???????????? ?????? (??????????????????)
        RestTemplate rt2 = new RestTemplate();

        //HttpHeader ???????????? ??????
        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Authorization", "Bearer " + oAuthToken.getAccess_token());
        headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        //Httpheader??? HttpBody??? ????????? ??????????????? ??????
        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 =
                new HttpEntity<>(headers2);

        //Http ???????????? POST ???????????? ????????? response
        ResponseEntity<String> response2 = rt2.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest2,
                String.class
        );

        ObjectMapper objectMapper2 = new ObjectMapper();
        KakaoProfile kakaoProfile = null;
        kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);

        System.out.println("????????? ?????????(??????) : " + kakaoProfile.getId());
        System.out.println("????????? ?????????      : " + kakaoProfile.getKakao_account().getEmail());


//        return response2.getBody();

        // 1. ?????? ?????? ??????
        LoginInfoReq loginInfoReq = new LoginInfoReq();
        loginInfoReq.setEmail(kakaoProfile.getKakao_account().getEmail());
        ItMemberEntity user = loginService.loginCheck(loginInfoReq);
        if (user != null) {
            System.out.println("?????? ?????? ?????? ????????? ");
            //?????? ???..sns ???????????? ?????? ?????? ???????????? ???.
            user.setMbId(user.getMbEmail());

//                session.setMaxInactiveInterval(60*60*8); // 8??????
            session.setAttribute("userId", user.getMbEmail());
            session.setAttribute("user", user);
            session.setAttribute("userType", user.getMbSignature());//user.mbSignature ??? user type ???.
            session.setAttribute("adult", user.getMbAdult());//user.mbSignature ??? user type ???.
            return "redirect:/";
        } else {
            // ?????? ?????? ?????? ?????? ??????
            System.out.println("?????? ?????? ?????? ---   ");

            String nick = null;
            String profileUrl = null;

            //check nick name
            if (kakaoProfile.getKakao_account().getProfile() == null
                    || kakaoProfile.getKakao_account().getProfile().getNickname() == null
                    || kakaoProfile.getKakao_account().getProfile().getNickname().isEmpty()) {
                log.warn("[JOIN_SNS] [KAKAO] :: no NickName, resetting [EMAIL_" + loginInfoReq.getEmail());

                nick = loginInfoReq.getEmail();
                nick = nick.substring(0, nick.lastIndexOf("@"));
            } else {
                nick = kakaoProfile.getKakao_account().getProfile().getNickname();
            }

            if (nick.length() > 10) {
                nick = nick.substring(0, 10);
            }

            //nicName check
            //5. ????????? ?????? ????????? ?????? ??? ?????? ??????
            if (joinService.checkNick(nick)) {
                log.warn("[JOIN_SNS] [KAKAO] :: ????????? ??? ?????? ?????????, resetting [EMAIL_" + loginInfoReq.getEmail());

                nick = joinService.createRandomNick(nick);
            }

            JoinInfoReq snsJoinReq = new JoinInfoReq();
            snsJoinReq.setMbRoute(MbRouteCd.KAKAO);
            snsJoinReq.setUserMail(loginInfoReq.getEmail());
            snsJoinReq.setUserNick(nick);

            //set kakao profile
            if ((kakaoProfile.getKakao_account().getProfile().getProfile_image_url() != null)
                    && (!kakaoProfile.getKakao_account().getProfile().getProfile_image_url().isEmpty())) {
                snsJoinReq.setUserProfRealFileUrl(kakaoProfile.getKakao_account().getProfile().getProfile_image_url());
            }


            //6.db insert
            try {
                Long mbNo = joinService.joinSns(snsJoinReq);
                if (mbNo <= 0) {
                    log.error("[JOIN_SNS] ????????? ?????? ????????????. : [EMAIL_" + loginInfoReq.getEmail());
                    return "redirect:/";
                }
            } catch (Exception e) {
                log.error("[JOIN_SNS] ????????? ?????? ????????????. : [EMAIL_" + loginInfoReq.getEmail()
                        + "\n" + e.getMessage());
                return "redirect:/";
            }

            //????????? ?????????
            user = loginService.loginCheck(loginInfoReq);
            if (user != null) {
                System.out.println("?????? ?????? ?????? ????????? ");
                //?????? ???..sns ???????????? ?????? ?????? ???????????? ???.
                user.setMbId(user.getMbEmail());

//                session.setMaxInactiveInterval(60*60*8); // 8??????
                session.setAttribute("userId", user.getMbEmail());
                session.setAttribute("user", user);
                session.setAttribute("userType", user.getMbSignature());//user.mbSignature ??? user type ???.
                session.setAttribute("adult", user.getMbAdult());//user.mbSignature ??? user type ???.
                return "redirect:/";
            }
        }
        return "redirect:/";
    }
}
