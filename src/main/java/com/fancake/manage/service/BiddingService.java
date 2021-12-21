package com.fancake.manage.service;

import com.fancake.manage.dto.AddressDTO;
import com.fancake.manage.dto.BiddingDTO;
import com.fancake.manage.dto.PageRequestDTO;
import com.fancake.manage.dto.PageResultDTO;
import com.fancake.manage.entity.Address;
import com.fancake.manage.entity.Bidding;

public interface BiddingService {

    int register(BiddingDTO dto);
    BiddingDTO view(int biddingseq);

    //이후에 추가한 부분
    PageResultDTO<BiddingDTO, Bidding> getList(PageRequestDTO requestDTO);

    default Bidding dtoToEntity(BiddingDTO dto){

        Bidding entity = Bidding.builder()
                .biddingseq(dto.getBiddingseq())
                .itemseq(dto.getItemseq())
                .biddingmbno(dto.getBiddingmbno())
                .biddingprice(dto.getBiddingprice())
                .biddingstatus(dto.getAbookphone())
                .biddingdate(dto.getBiddingdate())
                .abookreciever(dto.getAbookreciever())
                .abookphone(dto.getAbookphone())
                .abookaddress1(dto.getAbookaddress1())
                .abookaddress2(dto.getAbookaddress2())
                .abookaddress3(dto.getAbookaddress3())
                .abookzipcode(dto.getAbookzipcode())
                .abookinfo(dto.getAbookinfo())
                .biddingcanceldate(dto.getBiddingcanceldate())
                .biddingsuccdate(dto.getBiddingsuccdate())
                .build();

        return entity;

    }

    default BiddingDTO entityToDto(Bidding entity){

        BiddingDTO dto = BiddingDTO.builder()
                .biddingseq(entity.getBiddingseq())
                .itemseq(entity.getItemseq())
                .biddingmbno(entity.getBiddingmbno())
                .biddingprice(entity.getBiddingprice())
                .biddingstatus(entity.getAbookphone())
                .biddingdate(entity.getBiddingdate())
                .abookreciever(entity.getAbookreciever())
                .abookphone(entity.getAbookphone())
                .abookaddress1(entity.getAbookaddress1())
                .abookaddress2(entity.getAbookaddress2())
                .abookaddress3(entity.getAbookaddress3())
                .abookzipcode(entity.getAbookzipcode())
                .abookinfo(entity.getAbookinfo())
                .biddingcanceldate(entity.getBiddingcanceldate())
                .biddingsuccdate(entity.getBiddingsuccdate())
                .build();

       return dto;

    }

}
