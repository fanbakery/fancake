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
public class AddressDTO {

    private int abookseq;
    private int abookmbno;
    private String abooktitle;
    private String abookreciever;
    private String abookphone;
    private String abookaddress1;
    private String abookaddress2;
    private String abookaddress3;
    private String abookzipcode;
    private String abookinfo;
    private String abookregtime;

    private LocalDateTime regDate, modDate;

}
