package com.fancake.manage.service;

import com.fancake.manage.dto.DonateDTO;
import com.fancake.manage.dto.PageRequestDTO;
import com.fancake.manage.dto.PageResultDTO;
import com.fancake.manage.entity.Donate;

public interface DonateService {

    int register(DonateDTO dto);
    DonateDTO view(int donateseq);

    //이후에 추가한 부분
    PageResultDTO<DonateDTO, Donate> getList(PageRequestDTO requestDTO);

    default Donate dtoToEntity(DonateDTO dto){

        Donate entity = Donate.builder()
                .donateseq(dto.getDonateseq())
                .donateuseyn(dto.getDonateuseyn())
                .donatename(dto.getDonatename())
                .donateregdate(dto.getDonateregdate())
                .build();

        return entity;

    }

    default DonateDTO entityToDto(Donate entity){

        DonateDTO dto = DonateDTO.builder()
                .donateseq(entity.getDonateseq())
                .donateuseyn(entity.getDonateuseyn())
                .donatename(entity.getDonatename())
                .donateregdate(entity.getDonateregdate())
                .build();

       return dto;

    }

}
