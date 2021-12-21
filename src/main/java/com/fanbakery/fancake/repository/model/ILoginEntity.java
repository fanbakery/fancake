package com.fanbakery.fancake.repository.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class ILoginEntity {
    private String loIp;
    private String mbId;
    private String loDatetime;
    private String loLocation;
    private String loUrl;
}
