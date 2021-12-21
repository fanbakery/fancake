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
public class DonateDTO {

    private int donateseq;
    private int donateuseyn;
    private String donatename;
    private String donateregdate;

    private LocalDateTime regDate, modDate;

}
