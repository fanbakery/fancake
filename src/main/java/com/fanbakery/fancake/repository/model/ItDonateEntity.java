package com.fanbakery.fancake.repository.model;

import lombok.Data;

import java.io.Serializable;



@Data
//Table ( name ="it_donate" )
public class ItDonateEntity  implements Serializable {

	private static final long serialVersionUID =  955642079695168773L;

	private Long donateSeq;

	/*  기부처 이름 */
	private String donateName;

	/*  노출여부 (0:노출안함, 1:노출) */
	private Long donateUseYn;

}
