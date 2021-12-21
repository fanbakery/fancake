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
//Table ( name ="it_mb_infulener_info" )
public class ItMbInfulenerInfoEntity implements Serializable {

	private static final long serialVersionUID =  3315288001830776309L;

	/*  회원 seq (it_member.mb_no) */
	private Long mbNo;

	private String introduction;

	//활동 채널
	private boolean actYoutube;
	private boolean actAfreeca;
	private boolean actTwitch;
	private boolean actBroadcast;
	private boolean actInstagram;
	private boolean actWriter;

	//커버 사진
	private String coverImg1;
	private String coverImg2;
	private String coverImg3;

	/*  인플루언서 전환 신청시 받은 profile(전환 완료후에는 it_member.mb_profile 로 엡데이트) */
	private String tempNick;

	/*  인플루언서 전환 신청시 받은 profile(전환 완료후에는 it_member.mb_profile 엡데이트) */
	private String tempProfile;

	private LocalDateTime reqDate;
}
