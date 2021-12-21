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
public class ZzimDTO {

    private int zzimitemseq;
    private int itemseq;
    private int zzimmbno;
    private String zzimdate;

    private LocalDateTime regDate, modDate;

}
