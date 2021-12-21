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
public class InfluenceDTO {

    private int mbno;
    private String introduction;
    private String actyoutube;
    private String actafreeca;
    private String acttwitch;
    private String actbroadcast;
    private String actinstagram;
    private String actwriter;
    private String coverimg1;
    private String coverimg2;
    private String coverimg3;

    private String tempnick;
    private String tempprofile;
    private String reqdate;

    private LocalDateTime regDate, modDate;

}
