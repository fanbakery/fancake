package com.fancake.manage.service;

import com.fancake.manage.dto.MemberAlarmDTO;
import com.fancake.manage.dto.NickBlockDTO;
import com.fancake.manage.dto.PageRequestDTO;
import com.fancake.manage.dto.PageResultDTO;
import com.fancake.manage.entity.MemberAlarm;
import com.fancake.manage.entity.NickBlock;

public interface NickBlockService {

    int register(NickBlockDTO dto);
    NickBlockDTO view(int mbno);

    //이후에 추가한 부분
    PageResultDTO<NickBlockDTO, NickBlock> getList(PageRequestDTO requestDTO);

    default NickBlock dtoToEntity(NickBlockDTO dto){

        // mbalarmseq mbno mbalarmmsg mbinfulencerno mbalarmopenyn
        NickBlock entity = NickBlock.builder()
                .mbno(dto.getMbno())
                .mbnick(dto.getMbnick())
                .build();
        return entity;

    }

    default NickBlockDTO entityToDto(NickBlock entity){

        NickBlockDTO dto = NickBlockDTO.builder()
                .mbno(entity.getMbno())
                .mbnick(entity.getMbnick())
                .build();

       return dto;

    }

}
