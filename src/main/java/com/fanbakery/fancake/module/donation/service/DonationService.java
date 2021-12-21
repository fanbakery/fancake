package com.fanbakery.fancake.module.donation.service;

import com.fanbakery.fancake.code.service.item.DonateStatusCd;
import com.fanbakery.fancake.code.service.item.SettlementStatusCd;
import com.fanbakery.fancake.module.donation.model.ReqDonation;
import com.fanbakery.fancake.repository.mapper.DonationMapper;
import com.fanbakery.fancake.repository.mapper.SettlementMapper;
import com.fanbakery.fancake.repository.model.ItDonateEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class DonationService {

    private final DonationMapper donationMapper;
    private final SettlementMapper settlementMapper;


    /**
     * 기부처 리스트 조회
     * @return
     */
    public List<ItDonateEntity> getDonationList() {

        List<ItDonateEntity> donateList = donationMapper.selectDonationList();

        return donateList;
    }


    /**
     * 기부 신청
     * @param mdNo
     * @param reqDonation
     */
    public void addDonationReq(Long mdNo, ReqDonation reqDonation) {

        reqDonation.setMbNo(mdNo);
        reqDonation.setDonateReqDate(LocalDateTime.now());
        reqDonation.setDonateStatus(DonateStatusCd.WAIT);

        donationMapper.insertDonationReq(reqDonation);

        return;
    }


    /**
     * 기부 가능 금액 조회(정산예정금액)
     * @param mbNo
     * @return
     */
    public Long getAvailDonationBalance(Long mbNo) {

        Long balance = settlementMapper.getAvailDonationBalance(mbNo, SettlementStatusCd.WAIT);
        if( balance == null )  {
            balance = 0L;
        }

        //처리 안된 기부 신청금 제외
        if( balance > 0 ) {
            Long donationAmount = donationMapper.selectDonationAmountByMb(mbNo, DonateStatusCd.WAIT);
            if( donationAmount != null && donationAmount > 0 ) {
                balance = balance - donationAmount;
                if( balance <= 0 ) {
                    balance = 0L;
                }
            }
        }

        return balance;
    }
}
