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
public class HpAuthDTO {

    private String phone;
    private String cert;
    private String sendtime;
    private String expiretime;

    private LocalDateTime regDate, modDate;

}
