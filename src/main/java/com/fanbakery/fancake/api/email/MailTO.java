package com.fanbakery.fancake.api.email;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MailTO {
    private String address;
    private String title;
    private String message;
    
	
}
