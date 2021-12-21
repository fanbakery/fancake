package com.fancake.manage.service;

import com.fancake.manage.dto.AddressDTO;
import com.fancake.manage.dto.PageRequestDTO;
import com.fancake.manage.dto.PageResultDTO;
import com.fancake.manage.dto.SeenDTO;
import com.fancake.manage.entity.Address;
import com.fancake.manage.entity.Seen;

public interface SeenService {

    int register(SeenDTO dto);
    SeenDTO view(int mbno);

    //이후에 추가한 부분
    PageResultDTO<SeenDTO, Seen> getList(PageRequestDTO requestDTO);

    default Seen dtoToEntity(SeenDTO dto){

        Seen entity = Seen.builder()
                .seenseq(dto.getSeenseq())
                .itemseq(dto.getItemseq())
                .mbno(dto.getMbno())
                .itemseendate(dto.getItemseendate())
                .build();

        return entity;

    }

    default SeenDTO entityToDto(Seen entity){

        SeenDTO dto = SeenDTO.builder()
                .seenseq(entity.getSeenseq())
                .itemseq(entity.getItemseq())
                .mbno(entity.getMbno())
                .itemseendate(entity.getItemseendate())
                .build();

       return dto;

    }

}
