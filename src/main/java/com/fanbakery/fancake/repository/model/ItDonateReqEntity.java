package com.fanbakery.fancake.repository.model;

import com.fanbakery.fancake.code.service.item.DonateStatusCd;
import com.fanbakery.fancake.code.service.member.PersonSexCd;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
//Table ( name ="it_donate_req" )
public class ItDonateReqEntity  implements Serializable {

	private static final long serialVersionUID =  999996828566424883L;

	private Long reqSeq;

	/*  회원 일련번호 */
	private Long mbNo;

	/*  기부처 번호 */
	private Long donateSeq;

	/*  기부금액 */
	private Long donatePrice;

	/*  이름(실명) */
	private String donatePersonName;

	/*  생년월일 */
	private String donatePersonBirthday;

	/*  성별(M/F) */
	private PersonSexCd donatePersonSex;

	/*  주소 */
	private String donatePersonAddr;

	/*  기부 신청일자 */
	private LocalDateTime donateReqDate;


	/* 기부 처리 상태 */
	private DonateStatusCd donateStatus;

	private Long balance;

}
