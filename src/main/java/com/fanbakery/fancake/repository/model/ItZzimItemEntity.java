package com.fanbakery.fancake.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//Table ( name ="it_zzim_item" )
public class ItZzimItemEntity implements Serializable {

	private static final long serialVersionUID =  6841621417088496480L;

	private Long zzimItemSeq;

	private Long itemSeq;

	private Long zzimMbNo;

	private LocalDateTime zzimDate;

}
