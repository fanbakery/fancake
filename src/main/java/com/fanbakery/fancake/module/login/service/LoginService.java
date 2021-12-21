package com.fanbakery.fancake.module.login.service;

import com.fanbakery.fancake.module.login.model.LoginInfoReq;
import com.fanbakery.fancake.repository.mapper.LoginMapper;
import com.fanbakery.fancake.repository.model.ItMemberEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

    private final LoginMapper loginMapper;

    public ItMemberEntity loginCheck(LoginInfoReq loginInfoReq) {
        return loginMapper.selectLogin(loginInfoReq);
    }
}
