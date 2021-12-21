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
public class ItemDTO {

    private int itemseq;
    private String itemname;
    private String itemimg1;
    private String itemsellprice;
    private String itemselladdprice;
    private String itemstartprice;
    private LocalDateTime itemregDate, regDate, modDate;

}
