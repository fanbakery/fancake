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
public class ZzimInfluenceDTO {

    private int zziminfluenseq;
    private int influenmbno;
    private int zzimmbno;
    private String zzimdate;

    private LocalDateTime regDate, modDate;

}
