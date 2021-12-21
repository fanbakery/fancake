package com.fancake.manage.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DonateReqDTO {

    private int reqseq;
    private int mbno;
    private int donateseq;
    private int donateprice;

    private String donatepersonname;
    private String donatepersonbirthday;
    private String donatepersonsex;
    private String donatepersonaddr;
    private String donatereqdate;
    private String donatestatus;

    private LocalDateTime regDate, modDate;

}
