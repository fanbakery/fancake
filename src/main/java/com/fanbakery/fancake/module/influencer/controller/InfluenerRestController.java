package com.fanbakery.fancake.module.influencer.controller;

import com.fanbakery.fancake.code.service.ListOrderTypeCd;
import com.fanbakery.fancake.code.service.member.MbSignatureCd;
import com.fanbakery.fancake.code.system.SystemDef;
import com.fanbakery.fancake.common.model.ApiResponse;
import com.fanbakery.fancake.common.model.ContentListReq;
import com.fanbakery.fancake.common.model.Paging;
import com.fanbakery.fancake.module.influencer.service.InfluencerService;
import com.fanbakery.fancake.repository.model.InfluencerListInfoEntity;
import com.fanbakery.fancake.repository.model.ItMemberEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/influen")
public class InfluenerRestController {

    private final InfluencerService influencerService;


    @GetMapping("/tabList")
    public ResponseEntity<?> getInfluenTabList(@RequestParam(name="pageIdx", defaultValue = "1") int pageIndex
                    , @RequestParam(name="cntPerPage", defaultValue = SystemDef.STR_PAGING_DEF_CONTENTS_SIZE ) int cntPerPage
                    , @RequestParam(name="orderType", defaultValue = ListOrderTypeCd.values.RECENT, required = false) ListOrderTypeCd orderType
                    , HttpSession session) {

        ItMemberEntity user = (ItMemberEntity) session.getAttribute("user");

        //1.get influencer List Info
        ContentListReq listReq = ContentListReq.builder()
                .mbNo(user.getMbNo())
                .mbSignature(MbSignatureCd.INFLUEN_ACT)
                .pageIndex(ContentListReq.setReqPageIndex(pageIndex))
                .cntPerPage(cntPerPage)
                .orderType(orderType)
                .build();

        ApiResponse<Paging<List<InfluencerListInfoEntity>>> res = ApiResponse.<Paging<List<InfluencerListInfoEntity>>>builder()
                .data(influencerService.getInfluenListInfo(listReq))
                .build();

        return ResponseEntity.ok(res);
    }

}
