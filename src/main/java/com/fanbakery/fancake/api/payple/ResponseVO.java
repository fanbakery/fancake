package com.fanbakery.fancake.api.payple;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseVO {
	//정기 결제 등록전 인증 결과
	private String result;
	private String result_msg;
	private String server_name;
	private String custKey;
	private String PCD_PAY_URL;
	private String AuthKey;
	private String PCD_PAY_HOST;
	private String return_url;
	private String cst_id;
	

}
