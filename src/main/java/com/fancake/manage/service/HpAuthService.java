package com.fancake.manage.service;

import com.fancake.manage.dto.HpAuthDTO;
import com.fancake.manage.dto.PageRequestDTO;
import com.fancake.manage.dto.PageResultDTO;
import com.fancake.manage.entity.HpAuth;

public interface HpAuthService {

    int register(HpAuthDTO dto);
    HpAuthDTO view(int phone);

    //이후에 추가한 부분
    PageResultDTO<HpAuthDTO, HpAuth> getList(PageRequestDTO requestDTO);

    default HpAuth dtoToEntity(HpAuthDTO dto){

        HpAuth entity = HpAuth.builder()
                .phone(dto.getPhone())
                .cert(dto.getCert())
                .sendtime(dto.getSendtime())
                .expiretime(dto.getExpiretime())
                .build();

        return entity;

    }

    default HpAuthDTO entityToDto(HpAuth entity){

        HpAuthDTO dto = HpAuthDTO.builder()
                .phone(entity.getPhone())
                .cert(entity.getCert())
                .sendtime(entity.getSendtime())
                .expiretime(entity.getExpiretime())
                .build();

       return dto;

    }

}
