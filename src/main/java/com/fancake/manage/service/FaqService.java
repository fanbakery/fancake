package com.fancake.manage.service;

import com.fancake.manage.dto.FaqDTO;
import com.fancake.manage.dto.PageRequestDTO;
import com.fancake.manage.dto.PageResultDTO;
import com.fancake.manage.entity.Faq;

public interface FaqService {

    int register(FaqDTO dto);
    FaqDTO view(int faid);

    //이후에 추가한 부분
    PageResultDTO<FaqDTO, Faq> getList(PageRequestDTO requestDTO);

    default Faq dtoToEntity(FaqDTO dto){

        Faq entity = Faq.builder()
                .faid(dto.getFaid())
                .fmid(dto.getFmid())
                .fasubject(dto.getFasubject())
                .facontent(dto.getFacontent())
                .faorder(dto.getFaorder())
                .faregdate(dto.getFaregdate())
                .build();

        return entity;

    }

    default FaqDTO entityToDto(Faq entity){

        FaqDTO dto = FaqDTO.builder()
                .faid(entity.getFaid())
                .fmid(entity.getFmid())
                .fasubject(entity.getFasubject())
                .facontent(entity.getFacontent())
                .faorder(entity.getFaorder())
                .faregdate(entity.getFaregdate())
                .build();

       return dto;

    }

}
