package com.fanbakery.fancake.api.nice;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostData {
	private DataBody dataBody;
	private DataHeader dataHeader;
}
