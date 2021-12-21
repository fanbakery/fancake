package com.fanbakery.fancake.repository.mapper;

import com.fanbakery.fancake.code.service.item.DonateStatusCd;
import com.fanbakery.fancake.module.donation.model.ReqDonation;
import com.fanbakery.fancake.repository.model.ItDonateEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DonationMapper {


    /**  ----------------
     * it_donate table
     ** ---------------------- */

    //select
    public List<ItDonateEntity>  selectDonationList();



    /**  ----------------
     * it_donate_req table
     ** ---------------------- */

    //select
    public Long selectDonationAmountByMb(Long mbNo, DonateStatusCd donateStatus);

    //insert
    public void insertDonationReq(ReqDonation reqDonation);


}
