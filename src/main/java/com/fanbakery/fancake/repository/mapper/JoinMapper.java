package com.fanbakery.fancake.repository.mapper;

import com.fanbakery.fancake.repository.model.ItMemberEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JoinMapper {
    /**  ----------------
     * it_member table
     ** ---------------------- */
    public void insertPhoneCertNum( String phone,String certNo);

    public Long selectMbNoByMbEmail(String email);
    public String selectBlockNick(String nick);
    public Long selectMbNoByMbNick(String nick);



    public ItMemberEntity selectMbInfombyMbEmail( String email);

    public ItMemberEntity selectMbInfombyMbHp(String phone);

    public Long insertJoinEmail(ItMemberEntity login);
    public Long insertJoinSns(ItMemberEntity login);

    public void updateUserTempPW(ItMemberEntity user);
}
