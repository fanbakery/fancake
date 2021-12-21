package com.fanbakery.fancake.module.donation.model;

import com.fanbakery.fancake.code.service.item.DonateStatusCd;
import com.fanbakery.fancake.code.service.member.PersonSexCd;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class ReqDonation  {
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


	//------- 입력 받지 않는 값들
	/*  기부 신청일자 */
	private LocalDateTime donateReqDate;

	/* 기부 처리 상태 */
	private DonateStatusCd donateStatus;/* 기부 처리 상태 */
}
