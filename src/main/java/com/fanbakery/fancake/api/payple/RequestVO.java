package com.fanbakery.fancake.api.payple;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestVO {
	//정기 결제 등록전 인증 결과
	private String PCD_PAY_TYPE;
	private String PCD_PAY_WORK;
	private String PCD_CARD_VER;
	private String PCD_PAY_GOODS;
	private Integer PCD_PAY_TOTAL;
	private String PCD_RST_URL;
	private String PCD_AUTH_KEY;
	private String PCD_PAY_URL;
	
	private String PCD_PAY_OID;
	private String PCD_PAYER_NO;
	private String PCD_PAYER_NAME;
	private String PCD_PAYER_HP;
	private String PCD_PAYER_EMAIL;
	private String PCD_PAY_ISTAX;
	private String PCD_TAXSAVE_FLAG;
	private String PCD_PAY_TAXTOTAL;
	
	
	private String PCD_CST_ID;
	private String PCD_CUST_KEY;
	private String PCD_PAYER_ID;
	private String PCD_SIMPLE_FLAG;
	
	private String callbackFunction;
	
	
	
//	결제없이 등록만을 수행할 때는 PCD_PAY_WORK 를 AUTH로 설정합니다.
//	결제와 동시에 등록을 수행할 때는  PCD_PAY_WORK 를 PAY로 설정합니다.
	
	
//	Request Message
//	필수 파라미터 정보
//	카드
//	계좌
//	PCD_PAY_TYPE
//	링크
//	String
//	20
//	card
//	결제수단 선택
//	card: 카드, transfer: 계좌
//	카드
//	계좌
//	PCD_PAY_WORK
//	링크
//	String
//	20
//	AUTH
//	결제요청 방식
//	1.비밀번호 등록만 할때는 AUTH,
//	2.결제와 등록을 동시에 처리할때는 PAY
//	카드
//	PCD_CARD_VER
//	링크
//	String
//	2
//	01
//	01: 정기결제
//	카드
//	계좌
//	PCD_PAY_GOODS
//	링크
//	String
//	2048
//	티셔츠
//	등록만 수행시(PCD_PAY_WORK = AUTH)일 경우에는 미입력
//	카드
//	계좌
//	PCD_PAY_TOTAL
//	링크
//	Number
//	20
//	1000
//	등록만 수행시(PCD_PAY_WORK = AUTH)일 경우에는 미입력
//	카드
//	계좌
//	PCD_RST_URL
//	링크
//	String
//	-
//	https://paytest.com
//	1. 요청결과 RETURN URL결제결과를 URL로 수신할 수 있습니다.
//	콜백함수(callbackFunction)를 사용하면 PCD_RST_URL에 설정한 URL로 결과를 수신할 수 없습니다.
//	2. 결제창 호출방식 설정경로를 상대경로로 지정하면 팝업방식으로,절대경로로 지정하면 다이렉트 방식으로 호출합니다.
//	카드
//	계좌
//	PCD_AUTH_KEY
//	링크
//	String
//	-
//	E3421H3J42K8274293J4H3J3
//	파트너 인증시 받은 AuthKey 값
//	카드
//	계좌
//	PCD_PAY_URL
//	링크
//	String
//	-
//	https://democpay.payple.kr/index.php?ACT_=PAYM&CPAYVER=....
//	파트너 인증시 받은 return_url 값 입력
//	선택 파라미터 정보
//	카드
//	계좌
//	PCD_PAY_OID
//	링크
//	String
//	255
//	test099942200156938
//	카드
//	계좌
//	PCD_PAYER_NO
//	링크
//	Number
//	20
//	1234
//	가맹점에서 사용하는 회원번호
//	카드
//	계좌
//	PCD_PAYER_NAME
//	링크
//	String
//	80
//	홍길동
//	결제고객 이름
//	카드
//	계좌
//	PCD_PAYER_HP
//	링크
//	String
//	20
//	01012345678
//	결제고객 핸드폰번호를 전송하시면 고객의 승인문자 민원을 방지하기 위한 알림톡이 발송됩니다.
//	카드
//	계좌
//	PCD_PAYER_EMAIL
//	링크
//	String
//	100
//	dev@payple.kr
//	해당 이메일 주소로 결제 안내메일이 발송됩니다.
//	카드
//	계좌
//	PCD_PAY_ISTAX
//	링크
//	String
//	1
//	Y
//	과세설정 (Default: Y 이며, 과세:Y, 복합과세:Y, 비과세: N) ISTAX:Y, TAXTOTAL:공란이면 페이플에서 10% 부가세를 자동으로 적용합니다.
//	등록만 수행시(PCD_PAY_WORK = AUTH)일 경우에는 미입력
//	계좌
//	PCD_TAXSAVE_FLAG
//	링크
//	String
//	1
//	Y
//	현금영수증 발행창 호출여부 (발행일 경우: Y)
//	카드
//	계좌
//	PCD_PAY_TAXTOTAL
//	링크
//	Number
//	11
//	10
//	복합과세(과세+면세) 주문건에 필요한 금액이며 가맹점에서 전송한 값을 부가세로 설정합니다.과세 또는 비과세의 경우 사용하지 않습니다.
//	등록만 수행시(PCD_PAY_WORK = AUTH)일 경우에는 미입력
//	카드
//	계좌
//	callbackFunction
//	링크
//	-
//	getResult
//	PCD_RST_URL대신 결제결과를 받을 수 있는 callback 함수를 설정할 수 있습니다.더 상세한 설명은 여기 에서 확인 할 수 있습니다.
//	
}
