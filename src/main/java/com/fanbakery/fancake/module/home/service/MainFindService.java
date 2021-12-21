package com.fanbakery.fancake.module.home.service;

import com.fanbakery.fancake.code.service.member.MbSignatureCd;
import com.fanbakery.fancake.common.utils.AuthUtil;
import com.fanbakery.fancake.module.home.model.FindIdReq;
import com.fanbakery.fancake.module.home.model.FindPwReq;
import com.fanbakery.fancake.module.home.model.ResSearchInfluencer;
import com.fanbakery.fancake.repository.mapper.JoinMapper;
import com.fanbakery.fancake.repository.mapper.MemberMapper;
import com.fanbakery.fancake.repository.model.ItMemberEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MainFindService {
    private final JoinMapper joinMapper;
    private final MemberMapper memberMapper;


    /**
     * 가입자 이메일 찾기
     * @param findPwReq
     * @return
     */
    public ItMemberEntity findUserMail (FindPwReq findPwReq ) {
        //return joinMapper.selectMbInfombyMbEmail(findPwReq.getEmail());
        return memberMapper.selectMemberInfoByMbEmail(findPwReq.getEmail());
        }

    /**
     * 가입자 핸드폰 찾기
     * @param findIdReq
     * @return
     */
    public ItMemberEntity findUserPhone (FindIdReq findIdReq ) {
        return joinMapper.selectMbInfombyMbHp(findIdReq.getUserPhone());
    }

    /**
     * 인플루언서 찾기
     * @param influenNick
     * @return
     */
    public List<ResSearchInfluencer> getSearchInfluncer(String influenNick) {

        return memberMapper.selectLikeInfluenNickByNick(influenNick, MbSignatureCd.INFLUEN_ACT);
    }


    public void resetTempUserPW(ItMemberEntity user, String tempPW) {

        user.setMbPassword(AuthUtil.encrypt_SHA256(tempPW));
        joinMapper.updateUserTempPW(user);
        return;
    }

}
