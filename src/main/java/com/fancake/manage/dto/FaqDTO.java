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
public class FaqDTO {

    private int faid;
    private int fmid;
    private String fasubject;
    private String facontent;
    private int faorder;
    private String faregdate;

    private LocalDateTime regDate, modDate;

}
