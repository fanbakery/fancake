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
public class PayDTO {

    private int mbno;
    private int itemseq;
    private int settleprice;
    private String settleday;
    private String settlestatus;
    private String settleregdate;
    private String issettlerequest;
    private String settlereqdate;

    private LocalDateTime regDate, modDate;

}
