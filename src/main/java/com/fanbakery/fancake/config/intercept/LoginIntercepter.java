package com.fanbakery.fancake.config.intercept;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@Component
public class LoginIntercepter implements HandlerInterceptor {

    public List loginIncludeUri = Arrays.asList("/", "/member/**", "/mypage/**"
                                            , "/product/**", "/influencer/**"
                                            , "/bidding/**", "/settlement/**"
                                            , "/account/**", "/donation/**"
                                            , "/order/**"
                                            , "/zzim/**"
                                            , "/fanpay/**"
                                            , "/alarm/**"
                                            , "/api/**"
                                            );

    public List loginExcludeUri = Arrays.asList("/login/**", "/join/**", "/find*", "/api/find-id/**" );

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String loginId = (String)request.getSession().getAttribute("userId");
        if(StringUtils.hasLength(loginId)){
            return true;
        }
        else{
            response.sendRedirect("/login/");
            return false;
        }
        
//    	return true;
        
    }
}

