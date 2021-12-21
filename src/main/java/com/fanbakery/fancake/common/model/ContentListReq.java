package com.fanbakery.fancake.common.model;

import com.fanbakery.fancake.code.service.ListOrderTypeCd;
import com.fanbakery.fancake.code.service.item.ItemBiddingStatusCd;
import com.fanbakery.fancake.code.service.item.ItemSelStatusCd;
import com.fanbakery.fancake.code.service.member.MbSignatureCd;
import com.fanbakery.fancake.code.system.SystemDef;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ContentListReq {
    private int pageIndex ;      //page index
    private int cntPerPage; // need to def set : SystemDef.PAGING_DEF_CONTENTS_SIZE;
    private ListOrderTypeCd orderType;


    private Long influenMbNo;   //influencer mbNo
    private Long mbNo;          //my mbNo

    private LocalDate startDate;

    private ItemSelStatusCd itemStatus;
    private MbSignatureCd mbSignature;


    private LocalDate mbSignatureCompleteDate;

    private ItemBiddingStatusCd biddingStatus;

    public static int setReqPageIndex(Integer pageIndex) {
        if( pageIndex == null || pageIndex == 0) {
            return 0;
        }
        return pageIndex -1;
    }

    public static int setReqCntPerPage(Integer cntPerPage) {
        if( cntPerPage == null || cntPerPage == 0) {
            return SystemDef.PAGING_DEF_CONTENTS_SIZE;
        }
        return cntPerPage;
    }


    public static int setResPageIndex(Integer pageIndex) {
        if( pageIndex == null || pageIndex == 0) {
            return 1;
        }
        return pageIndex + 1;
    }

    public static int setResCntPerPage(Integer cntPerPage) {
        if( cntPerPage == null || cntPerPage == 0) {
            return SystemDef.PAGING_DEF_CONTENTS_SIZE;
        }
        return cntPerPage;
    }
}
