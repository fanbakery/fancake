package com.fancake.manage.service;

import com.fancake.manage.dto.FaqMasterDTO;
import com.fancake.manage.dto.PageRequestDTO;
import com.fancake.manage.dto.PageResultDTO;
import com.fancake.manage.entity.FaqMaster;

public interface FaqMasterService {

    int register(FaqMasterDTO dto);
    FaqMasterDTO view(int fmid);

    //이후에 추가한 부분
    PageResultDTO<FaqMasterDTO, FaqMaster> getList(PageRequestDTO requestDTO);

    default FaqMaster dtoToEntity(FaqMasterDTO dto){

        FaqMaster entity = FaqMaster.builder()
                .fmid(dto.getFmid())
                .fmsubject(dto.getFmsubject())
                .fmheadhtml(dto.getFmheadhtml())
                .fmtailhtml(dto.getFmtailhtml())
                .fmmobileheadhtml(dto.getFmmobileheadhtml())
                .fmmobiletailhtml(dto.getFmmobiletailhtml())
                .fmorder(dto.getFmorder())
                .build();

        return entity;

    }

    default FaqMasterDTO entityToDto(FaqMaster entity){

        FaqMasterDTO dto = FaqMasterDTO.builder()
                .fmid(entity.getFmid())
                .fmsubject(entity.getFmsubject())
                .fmheadhtml(entity.getFmheadhtml())
                .fmtailhtml(entity.getFmtailhtml())
                .fmmobileheadhtml(entity.getFmmobileheadhtml())
                .fmmobiletailhtml(entity.getFmmobiletailhtml())
                .fmorder(entity.getFmorder())
                .build();

       return dto;

    }

}
