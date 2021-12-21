package com.fanbakery.fancake.module.zzim.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
public class ProductZzimReq {
    @NotNull
    private Long itemSeq;
    private Long mbNo;
    private LocalDateTime zzimDate;
}
