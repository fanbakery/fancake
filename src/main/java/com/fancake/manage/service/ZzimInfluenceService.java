package com.fancake.manage.service;

import com.fancake.manage.dto.PageRequestDTO;
import com.fancake.manage.dto.PageResultDTO;
import com.fancake.manage.dto.ZzimInfluenceDTO;
import com.fancake.manage.entity.ZzimInfluence;

public interface ZzimInfluenceService {

    int register(ZzimInfluenceDTO dto);
    ZzimInfluenceDTO view(int mbno);

    //이후에 추가한 부분
    PageResultDTO<ZzimInfluenceDTO, ZzimInfluence> getList(PageRequestDTO requestDTO);

    default ZzimInfluence dtoToEntity(ZzimInfluenceDTO dto){

        ZzimInfluence entity = ZzimInfluence.builder()
                .zziminfluenseq(dto.getZziminfluenseq())
                .influenmbno(dto.getInfluenmbno())
                .zzimmbno(dto.getZzimmbno())
                .zzimdate(dto.getZzimdate())
                .build();

        return entity;

    }

    default ZzimInfluenceDTO entityToDto(ZzimInfluence entity){

        ZzimInfluenceDTO dto = ZzimInfluenceDTO.builder()
                .zziminfluenseq(entity.getZziminfluenseq())
                .influenmbno(entity.getInfluenmbno())
                .zzimmbno(entity.getZzimmbno())
                .zzimdate(entity.getZzimdate())
                .build();

       return dto;

    }

}
