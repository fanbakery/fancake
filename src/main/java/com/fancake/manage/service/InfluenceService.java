package com.fancake.manage.service;

import com.fancake.manage.dto.AddressDTO;
import com.fancake.manage.dto.InfluenceDTO;
import com.fancake.manage.dto.PageRequestDTO;
import com.fancake.manage.dto.PageResultDTO;
import com.fancake.manage.entity.Address;
import com.fancake.manage.entity.Influence;

public interface InfluenceService {

    int register(InfluenceDTO dto);
    InfluenceDTO view(int mbno);

    //이후에 추가한 부분
    PageResultDTO<InfluenceDTO, Influence> getList(PageRequestDTO requestDTO);

    default Influence dtoToEntity(InfluenceDTO dto){

        Influence entity = Influence.builder()
                .mbno(dto.getMbno())
                .introduction(dto.getIntroduction())
                .actyoutube(dto.getActyoutube())
                .actafreeca(dto.getActafreeca())
                .acttwitch(dto.getActtwitch())
                .actbroadcast(dto.getActbroadcast())
                .actinstagram(dto.getActinstagram())
                .actwriter(dto.getActwriter())
                .coverimg1(dto.getCoverimg1())
                .coverimg2(dto.getCoverimg2())
                .coverimg3(dto.getCoverimg3())
                .tempnick(dto.getTempnick())
                .tempprofile(dto.getTempprofile())
                .reqdate(dto.getReqdate())
                .build();

        return entity;

    }

    default InfluenceDTO entityToDto(Influence entity){

        InfluenceDTO dto = InfluenceDTO.builder()
                .mbno(entity.getMbno())
                .introduction(entity.getIntroduction())
                .actyoutube(entity.getActyoutube())
                .actafreeca(entity.getActafreeca())
                .acttwitch(entity.getActtwitch())
                .actbroadcast(entity.getActbroadcast())
                .actinstagram(entity.getActinstagram())
                .actwriter(entity.getActwriter())
                .coverimg1(entity.getCoverimg1())
                .coverimg2(entity.getCoverimg2())
                .coverimg3(entity.getCoverimg3())
                .tempnick(entity.getTempnick())
                .tempprofile(entity.getTempprofile())
                .reqdate(entity.getReqdate())
                .build();

       return dto;

    }

}
