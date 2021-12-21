package com.fanbakery.fancake.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//Table ( name ="it_settlement" )
public class ItSettlementEntity  implements Serializable {

	private static final long serialVersionUID =  7937082676528221879L;

	/*  계정번호 */
	private Long mbNo;

	/*  상품번호 */
	private Long itemSeq;

	/*  정산금액 */
	private Long settlePrice;

	/*  정산일자 */
	private String settleDay;

	/*  정산상태('WAIT', 'COMPLETE') */
	private String settleStatus;

	private LocalDateTime settleRegDate;

	/*  정산 신청여부(신청:1) */
	private Boolean isSettleRequest;

	/*  정산신청한 시간  */
	private LocalDateTime settleReqDate;


	//---- it_settlement 없는 항목
	/*  상품 낙찰 날자 */
	private LocalDate biddingSuccDate;

	private String itemName;





}
