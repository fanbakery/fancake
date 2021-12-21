package com.fancake.manage.service;

import com.fancake.manage.dto.DonateReqDTO;
import com.fancake.manage.dto.PageRequestDTO;
import com.fancake.manage.dto.PageResultDTO;
import com.fancake.manage.entity.DonateReq;

public interface DonateReqService {

    int register(DonateReqDTO dto);
    DonateReqDTO view(int reqseq);

    //이후에 추가한 부분
    PageResultDTO<DonateReqDTO, DonateReq> getList(PageRequestDTO requestDTO);

    default DonateReq dtoToEntity(DonateReqDTO dto){

        DonateReq entity = DonateReq.builder()
                .reqseq(dto.getReqseq())
                .mbno(dto.getMbno())
                .donateseq(dto.getDonateseq())
                .donateprice(dto.getDonateprice())
                .donatepersonname(dto.getDonatepersonname())
                .donatepersonbirthday(dto.getDonatepersonbirthday())
                .donatepersonsex(dto.getDonatepersonsex())
                .donatepersonaddr(dto.getDonatepersonaddr())
                .donatereqdate(dto.getDonatereqdate())
                .donatestatus(dto.getDonatestatus())
                .build();

        return entity;

    }

    default DonateReqDTO entityToDto(DonateReq entity){

        DonateReqDTO dto = DonateReqDTO.builder()
                .reqseq(entity.getReqseq())
                .mbno(entity.getMbno())
                .donateseq(entity.getDonateseq())
                .donateprice(entity.getDonateprice())
                .donatepersonname(entity.getDonatepersonname())
                .donatepersonbirthday(entity.getDonatepersonbirthday())
                .donatepersonsex(entity.getDonatepersonsex())
                .donatepersonaddr(entity.getDonatepersonaddr())
                .donatereqdate(entity.getDonatereqdate())
                .donatestatus(entity.getDonatestatus())
                .build();

       return dto;

    }

}
