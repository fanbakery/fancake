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
public class SeenDTO {

    private int seenseq;
    private int itemseq;
    private int mbno;
    private String itemseendate;

    private LocalDateTime regDate, modDate;

}
