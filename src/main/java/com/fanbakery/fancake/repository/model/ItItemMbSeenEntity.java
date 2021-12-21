package com.fanbakery.fancake.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//Table ( name ="it_item_mb_seen" )
public class ItItemMbSeenEntity  implements Serializable {

	private static final long serialVersionUID =  135931779896367215L;

	private Long seenSeq;
	private Long itemSeq;
	private Long mbNo;

	private LocalDateTime itemSeenDate;
}
