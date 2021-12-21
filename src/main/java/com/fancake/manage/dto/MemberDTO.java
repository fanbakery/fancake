package com.fancake.manage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberDTO {

    private int mbno;
    private String mbprofile;
    private String mbnick;
    private String mbemail;
    private String mbhp;
    private String mbroute;
    private LocalDateTime regDate, modDate;

}
