package com.fanbakery.fancake.api.nice;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FormData {
	private String token_version_id;  //암호화토큰요청 API응답으로 받은 값		                                                  
	private String enc_data;          //인암호화한 요청정보	                                              
	private String integrity_value;   //enc_data의 무결성 값site_code				                                      
	private String action;            //요청 url
	private String m;
}

