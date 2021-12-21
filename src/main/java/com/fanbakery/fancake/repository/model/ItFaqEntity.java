package com.fanbakery.fancake.repository.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
//Table ( name ="it_faq" )
public class ItFaqEntity  implements Serializable {

	private static final long serialVersionUID =  5667006343245909563L;

	private Long faId;
	private Long fmId;

	private String faSubject;
	private String faContent;
	private Long faOrder;
	private LocalDateTime faRegDate;
}
