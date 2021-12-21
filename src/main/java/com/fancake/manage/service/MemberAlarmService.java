package com.fancake.manage.service;

import com.fancake.manage.dto.MemberAlarmDTO;
import com.fancake.manage.dto.MemberDTO;
import com.fancake.manage.dto.PageRequestDTO;
import com.fancake.manage.dto.PageResultDTO;
import com.fancake.manage.entity.Member;
import com.fancake.manage.entity.MemberAlarm;

public interface MemberAlarmService {

    int register(MemberAlarmDTO dto);
    MemberAlarmDTO view(int mbalarmseq);


    //이후에 추가한 부분
    PageResultDTO<MemberAlarmDTO, MemberAlarm> getList(PageRequestDTO requestDTO);

    default MemberAlarm dtoToEntity(MemberAlarmDTO dto){

        // mbalarmseq mbno mbalarmmsg mbinfulencerno mbalarmopenyn
        MemberAlarm entity = MemberAlarm.builder()
                .mbalarmseq(dto.getMbalarmseq())
                .mbno(dto.getMbno())
                .mbalarmmsg(dto.getMbalarmmsg())
                .mbinfulencerno(dto.getMbinfulencerno())
                .mbalarmopenyn(dto.getMbalarmopenyn())
                .build();
        return entity;

    }

    default MemberAlarmDTO entityToDto(MemberAlarm entity){

        MemberAlarmDTO dto = MemberAlarmDTO.builder()
                .mbalarmseq(entity.getMbalarmseq())
                .mbno(entity.getMbno())
                .mbalarmmsg(entity.getMbalarmmsg())
                .mbinfulencerno(entity.getMbinfulencerno())
                .mbalarmopenyn(entity.getMbalarmopenyn())
                .build();

       return dto;

    }

}
