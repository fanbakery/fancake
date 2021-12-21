package com.fanbakery.fancake.module.home.controller;

import com.fanbakery.fancake.code.system.SystemDef;
import com.fanbakery.fancake.common.model.ApiResponse;
import com.fanbakery.fancake.common.model.Paging;
import com.fanbakery.fancake.module.home.model.ResSearchInfluencer;
import com.fanbakery.fancake.module.home.service.MainContentsService;
import com.fanbakery.fancake.module.home.service.MainFindService;
import com.fanbakery.fancake.module.join.service.JoinService;
import com.fanbakery.fancake.repository.model.ItMemberEntity;
import com.fanbakery.fancake.repository.model.ItemListInfoEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MainRestController {

    private final MainContentsService contentsService;
    private final MainFindService findService;
    private final JoinService joinService;

    @GetMapping("/list/bidProduct")
    public ResponseEntity<?> getbidProductList(@RequestParam(name="pageIdx", defaultValue = "1") int pageIndex
                    , @RequestParam(name="cntPerPage", defaultValue = SystemDef.STR_PAGING_DEF_CONTENTS_SIZE ) int cntPerPage
                    , HttpSession session) {

        ItMemberEntity user = (ItMemberEntity) session.getAttribute("user");

        ApiResponse<Paging<List<ItemListInfoEntity>>> res = ApiResponse.<Paging<List<ItemListInfoEntity>>>builder()
                .data(contentsService.getBiddingProductList(user.getMbNo(), pageIndex, cntPerPage))
                .build();

        return ResponseEntity.ok(res);
    }


    @GetMapping("/search/influencer")
    public ResponseEntity<?> searchInfluencer(@RequestParam(name="nick") String influenNick) {


        ApiResponse<List<ResSearchInfluencer>> res = ApiResponse.<List<ResSearchInfluencer>>builder()
                .data(findService.getSearchInfluncer(influenNick))
                .build();
        return ResponseEntity.ok(res);
    }


    /**
     * 핸드폰 인증 번호 발송
     * @param userPhone
     * @return
     */
    @PostMapping("/find-id/cert-snd")
    public ResponseEntity<?> find_id_cert_send(@RequestParam(name="userPhone") String userPhone ){
        String cert = joinService.createPhoneCertNm(userPhone);
        if( cert == null ) {
            return ResponseEntity.internalServerError().build();
        }

        //TODO:: 핸드폰 연동 로직 추가 필요!!
        //something...

        return ResponseEntity.ok().build();
    }
}
