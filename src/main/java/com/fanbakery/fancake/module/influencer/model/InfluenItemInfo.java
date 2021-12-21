package com.fanbakery.fancake.module.influencer.model;

import com.fanbakery.fancake.repository.model.OrderListInfoEntity;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class InfluenItemInfo {

    private LocalDate regDate;
    private List<OrderListInfoEntity> product;
}
