package com.fanbakery.fancake.module.zzim.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
public class InfluencerZzimReq {
    @NotNull
    private Long influenMbNo;

    private Long zzimMbNo;
    private LocalDateTime zzimDate;
}
