package com.fanbakery.fancake.repository.mapper;

import com.fanbakery.fancake.code.service.member.MbSignatureCd;
import com.fanbakery.fancake.module.home.model.ResSearchInfluencer;
import com.fanbakery.fancake.repository.model.ItMemberEntity;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface MemberMapper {

    /**  ----------------
     * it_member table
     ** ---------------------- */

    public Long selectCountInfluencer(MbSignatureCd mbSignature);

    public ItMemberEntity selectMemberInfoByMbNo(Long mbNo);
    public ItMemberEntity selectMemberInfoByMbNoSignature(Long mbNo, MbSignatureCd mbSignature);
    public ItMemberEntity selectMemberInfoByMbEmail(String email);

    public List<ResSearchInfluencer> selectLikeInfluenNickByNick(String mbNick, MbSignatureCd mbSignature);


    //update
    public void updateInfluenReqStatusByMbNo(ItMemberEntity user);
    public void updateInfluenActStatusByMbNo(ItMemberEntity user);

    public void updateMbNickByMbNo(Long mbNo, String mbNick);

    public void updateMbPorfileByMbNo(Long mbNo, String mbProfile);


    public void updateUnregister(Long mbNo, LocalDateTime mbLeaveDate);
    public void updateMbAdultByMbNo(Long mbNo, int mbAdult);
}
