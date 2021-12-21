package com.fancake.manage.service;

import com.fancake.manage.dto.AddressDTO;
import com.fancake.manage.dto.PageRequestDTO;
import com.fancake.manage.dto.PageResultDTO;
import com.fancake.manage.entity.Address;

public interface AddressService {

    int register(AddressDTO dto);
    AddressDTO view(int mbno);

    //이후에 추가한 부분
    PageResultDTO<AddressDTO, Address> getList(PageRequestDTO requestDTO);

    default Address dtoToEntity(AddressDTO dto){

        Address entity = Address.builder()
                .abookseq(dto.getAbookseq())
                .abookmbno(dto.getAbookmbno())
                .abooktitle(dto.getAbooktitle())
                .abookreciever(dto.getAbookreciever())
                .abookphone(dto.getAbookphone())
                .abookaddress1(dto.getAbookaddress1())
                .abookaddress2(dto.getAbookaddress2())
                .abookaddress3(dto.getAbookaddress3())
                .abookzipcode(dto.getAbookzipcode())
                .abookinfo(dto.getAbookinfo())
                .abookregtime(dto.getAbookregtime())
                .build();

        return entity;

    }

    default AddressDTO entityToDto(Address entity){

        AddressDTO dto = AddressDTO.builder()
                .abookseq(entity.getAbookseq())
                .abookmbno(entity.getAbookmbno())
                .abooktitle(entity.getAbooktitle())
                .abookreciever(entity.getAbookreciever())
                .abookphone(entity.getAbookphone())
                .abookaddress1(entity.getAbookaddress1())
                .abookaddress2(entity.getAbookaddress2())
                .abookaddress3(entity.getAbookaddress3())
                .abookzipcode(entity.getAbookzipcode())
                .abookinfo(entity.getAbookinfo())
                .abookregtime(entity.getAbookregtime())
                .build();

       return dto;

    }

}
