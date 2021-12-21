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
public class MemberAlarmDTO {

    private int mbalarmseq;
    private int mbno;
    private String mbalarmmsg;
    private String mbinfulencerno;
    private String mbalarmopenyn;
    private LocalDateTime mbdatetime;


}
