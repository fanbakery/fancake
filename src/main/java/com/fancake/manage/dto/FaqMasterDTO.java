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
public class FaqMasterDTO {

    private int fmid;
    private String fmsubject;
    private String fmheadhtml;
    private String fmtailhtml;
    private String fmmobileheadhtml;
    private String fmmobiletailhtml;
    private int fmorder;

    private LocalDateTime regDate, modDate;

}
