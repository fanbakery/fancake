package com.fancake.manage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BiddingDTO {

    private int biddingseq;
    private int itemseq;
    private int biddingmbno;
    private int biddingprice;

    private String biddingstatus;
    private String biddingdate;
    private String abookreciever;
    private String abookphone;
    private String abookaddress1;
    private String abookaddress2;
    private String abookaddress3;
    private String abookzipcode;
    private String abookinfo;
    private String biddingcanceldate;
    private String biddingsuccdate;

    private LocalDateTime regDate, modDate;

}
