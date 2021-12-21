package com.fanbakery.fancake.api.nice;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultData {
	private String resultcode;      //결과코드			                                
	private String requestno;       //요청 고유 번호(회원사에서 전달보낸 값)			                  
	private String enctime;         //암호화 일시(YYYYMMDDHH24MISS)			            
	private String sitecode;        //사이트코드			                                인증)"				  
	private String responseno;      //응답고유번호			                            
	private String autauthtype;		//인증수단			                                
	private String name;            //이름
	private String birthdate;
	private String mobile_no;

	//인증완료후 데이터
	private String token_version_id;
	private String enc_data;
	private String integrity_value;
	
	//복호화시 사용 데이터
	private String encData;
	private String key;
	private String iv;
	
	
}
