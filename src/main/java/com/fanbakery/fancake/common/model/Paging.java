package com.fanbakery.fancake.common.model;

import com.fanbakery.fancake.code.system.SystemDef;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Paging<T> {

	private static final int PAGE_BLOCK_SIZE = SystemDef.PAGING_DEF_CONTENTS_SIZE;

    private int pageIndex;      //page index
    private int cntPerPage;

    private Long totalCnt;

    private T lists;
}
