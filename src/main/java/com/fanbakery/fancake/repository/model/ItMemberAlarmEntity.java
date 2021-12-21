package com.fanbakery.fancake.repository.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
//Table ( name ="it_member_alarm" )
public class ItMemberAlarmEntity  implements Serializable {

	private static final long serialVersionUID =  1751737747648508723L;

	private Long mbNo;

	/*  알람 메세지 */
	private String mbAlarmMsg;

	/*  알람 관련 인플루언서 정보 */
	private Long mbInfulencerNo;

	/*  알람 열람 여부 (0/1) */
	private Integer mbAlarmOpenYn;

	/*  알람 등록일 */
	private LocalDateTime mbDatetime;

	private Long receivedHour;		//알람 받은 경과 시간


	private String influncerProfile;	//인플루언서 프로파일

}
