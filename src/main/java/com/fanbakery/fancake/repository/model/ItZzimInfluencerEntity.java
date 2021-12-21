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
//Table ( name ="it_zzim_influencer" )
public class ItZzimInfluencerEntity implements Serializable {

	private static final long serialVersionUID =  3828086710747894649L;

	private Long zzimInfluenSeq;
	private Long influenMbNo;
	private Long zzimMbNo;
	private LocalDateTime zzimDate;

}
