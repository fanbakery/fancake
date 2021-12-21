package com.fanbakery.fancake.repository.mapper;

import com.fanbakery.fancake.module.login.model.LoginInfoReq;
import com.fanbakery.fancake.repository.model.ItMemberEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {
    public ItMemberEntity selectLogin(LoginInfoReq loginInfoReq);
}
