package com.fanbakery.fancake.repository.mapper;

import com.fanbakery.fancake.code.service.item.ItemBiddingStatusCd;
import com.fanbakery.fancake.code.service.item.ItemSelStatusCd;
import com.fanbakery.fancake.repository.model.ItItemBiddingEntity;
import com.fanbakery.fancake.repository.model.ItPaymentEntity;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Mapper
public interface PaymentMapper {

    /**  ----------------
     * it_payment table
     ** ---------------------- */

    //select item
    public ItPaymentEntity selectPaymentInfoByNo(Long no);

}
