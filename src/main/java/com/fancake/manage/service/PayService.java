package com.fancake.manage.service;

import com.fancake.manage.dto.PageRequestDTO;
import com.fancake.manage.dto.PageResultDTO;
import com.fancake.manage.dto.PayDTO;
import com.fancake.manage.dto.ZzimDTO;
import com.fancake.manage.entity.Pay;
import com.fancake.manage.entity.Zzim;

public interface PayService {

    int register(PayDTO dto);
    PayDTO view(int mbno);

    //이후에 추가한 부분
    PageResultDTO<PayDTO, Pay> getList(PageRequestDTO requestDTO);

    default Pay dtoToEntity(PayDTO dto){

        Pay entity = Pay.builder()
                .mbno(dto.getMbno())
                .itemseq(dto.getItemseq())
                .settleprice(dto.getSettleprice())
                .settleday(dto.getSettleday())
                .settlestatus(dto.getSettlestatus())
                .settleregdate(dto.getSettleregdate())
                .issettlerequest(dto.getIssettlerequest())
                .settlereqdate(dto.getSettlereqdate())
                .build();

        return entity;

    }

    default PayDTO entityToDto(Pay entity){

        PayDTO dto = PayDTO.builder()
                .mbno(entity.getMbno())
                .itemseq(entity.getItemseq())
                .settleprice(entity.getSettleprice())
                .settleday(entity.getSettleday())
                .settlestatus(entity.getSettlestatus())
                .settleregdate(entity.getSettleregdate())
                .issettlerequest(entity.getIssettlerequest())
                .settlereqdate(entity.getSettlereqdate())
                .build();

       return dto;

    }

}
