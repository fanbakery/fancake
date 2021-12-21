package com.fanbakery.fancake.api.nice;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestData {
	private String requestno;          // 서비스 요청 고유 번호				                                                  
	private String returnurl;          // 인증결과를 받을 회원사 url				                                              
	private String sitecode;           // 암호화토큰요청 API에 응답받은 site_code				                                      
	private String authtype;           // "인증수단 고정                                                                      
	private String mobilceco;          // (M:휴대폰인증,C:카드본인확인인증,X:인증서인증,U:공동인증서인증,F:금융인증서인증,S:PASS인증서인증)"				  
	private String businessno;         // 이통사 우선 선택(S : SKT, K : KT, L : LGU+)				                          
	private String methodtype;         // 사업자번호(법인인증인증에 한함)				                                              
	private String popupyn;            // 결과 url 전달시 http method타입				                                      
	private String receivedata;          // 인증 후 전달받을 데이터 세팅 (요청값 그대로 리턴)				                                  
	
		    
	
	
	
	
	
					
	
	
	
	

}
