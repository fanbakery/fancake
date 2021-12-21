package com.fancake.manage.service;

import com.fancake.manage.dto.PageRequestDTO;
import com.fancake.manage.dto.PageResultDTO;
import com.fancake.manage.dto.SeenDTO;
import com.fancake.manage.dto.ZzimDTO;
import com.fancake.manage.entity.Seen;
import com.fancake.manage.entity.Zzim;

public interface ZzimService {

    int register(ZzimDTO dto);
    ZzimDTO view(int mbno);

    //이후에 추가한 부분
    PageResultDTO<ZzimDTO, Zzim> getList(PageRequestDTO requestDTO);

    default Zzim dtoToEntity(ZzimDTO dto){

        Zzim entity = Zzim.builder()
                .zzimitemseq(dto.getZzimitemseq())
                .itemseq(dto.getItemseq())
                .zzimmbno(dto.getZzimmbno())
                .zzimdate(dto.getZzimdate())
                .build();

        return entity;

    }

    default ZzimDTO entityToDto(Zzim entity){

        ZzimDTO dto = ZzimDTO.builder()
                .zzimitemseq(entity.getZzimitemseq())
                .itemseq(entity.getItemseq())
                .zzimmbno(entity.getZzimmbno())
                .zzimdate(entity.getZzimdate())
                .build();

       return dto;

    }

}
