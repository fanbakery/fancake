package com.fanbakery.fancake.repository.model;

import com.fanbakery.fancake.code.service.item.ItemBiddingStatusCd;
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
//Table ( name ="it_item_bidding" )
public class ItItemBiddingEntity implements Serializable {

	private static final long serialVersionUID =  3862306749416364990L;

	private Long biddingSeq;

	/*  it_item:item_seq */
	private Long itemSeq;

	/*  bidding member it_member:mb_no */
	private Long biddingMbNo;

	/*  입찰시간 */
	private LocalDateTime biddingDate;

	/*  입찰가격 */
	private Long biddingPrice;

	/*  입찰중, 낙찰, 사용자취소, 낙찰강제취소 */
	private ItemBiddingStatusCd biddingStatus;

	/* 입찰 취소 시간 */
	private LocalDateTime biddingCancelDate;

	/* 낙찰 시간 */
	private LocalDateTime biddingSuccDate;

	/*  받는사람 */
	private String abookReciever;

	/*  배송지 전화번 */
	private String abookPhone;

	/*  배송지 주소 */
	private String abookAddress1;

	/*  배송지 주소 */
	private String abookAddress2;

	/*  배송지 주소 */
	private String abookAddress3;

	/*  배송지 주소 */
	private String abookZipCode;

	/*  배송지 추가정 */
	private String abookInfo;


	/*  결제정보 테이블 SEQ */
	private Long paymentSeq;
	//@@@ TODO:: 결제 정보 필드 필요함!!
}
