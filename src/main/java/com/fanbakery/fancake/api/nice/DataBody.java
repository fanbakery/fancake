package com.fanbakery.fancake.api.nice;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DataBody {
	private String req_dtim;
	private String req_no;
	private String enc_mode;
	private String rsp_cd;
	private String site_code;
	private String result_cd;
	private String token_version_id;
	private String token_val;
	private long period;
	
		    
}
