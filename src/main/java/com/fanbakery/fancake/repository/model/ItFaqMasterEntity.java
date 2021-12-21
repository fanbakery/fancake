package com.fanbakery.fancake.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//Table ( name ="it_faq_master" )
public class ItFaqMasterEntity  implements Serializable {

	private static final long serialVersionUID =  7912970992153487012L;

	private Long fmId;

	private String fmSubject;

	private String fmHeadHtml;
	private String fmTailHtml;
	private String fmMobileHeadHtml;
	private String fmMobileTailHtml;

	private Long fmOrder;
}
