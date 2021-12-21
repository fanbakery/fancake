package com.fanbakery.fancake.module.zzim.controller;

import com.fanbakery.fancake.code.system.SystemDef;
import com.fanbakery.fancake.common.model.ApiResponse;
import com.fanbakery.fancake.common.model.Paging;
import com.fanbakery.fancake.module.zzim.service.ZzimService;
import com.fanbakery.fancake.repository.model.InfluencerListInfoEntity;
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
@RequestMapping("/api/zzim")
public class ZzimRestController {
    private final ZzimService zzimService;


    /** ------------------
     *  Influnencer zzim
     *  -----------------
     */

    @GetMapping("/product/list")
    public ResponseEntity<?> getProductZzimList(@RequestParam(name="pageIdx", defaultValue = "1") int pageIndex
            , @RequestParam(name="cntPerPage", defaultValue = SystemDef.STR_PAGING_DEF_CONTENTS_SIZE ) int cntPerPage
            , HttpSession session) {

        ItMemberEntity user = (ItMemberEntity) session.getAttribute("user");

        ApiResponse<Paging<List<ItemListInfoEntity>>> res = ApiResponse.<Paging<List<ItemListInfoEntity>>>builder()
                .data(zzimService.getProductZzimList(user.getMbNo(), pageIndex, cntPerPage))
                .build();

        return ResponseEntity.ok(res);
    }


    @PostMapping("/product/add/{itemSeq}")
    public ResponseEntity<?> addProductZzim(@PathVariable Long itemSeq, HttpSession session){
        ItMemberEntity user = (ItMemberEntity)session.getAttribute("user");

        zzimService.AddProductZzim(user.getMbNo(), itemSeq);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/product/del/{itemSeq}")
    public ResponseEntity<?> delProductZzim(@PathVariable Long itemSeq, HttpSession session ){
        ItMemberEntity user = (ItMemberEntity)session.getAttribute("user");

        zzimService.delProductZzim(user.getMbNo(), itemSeq);

        return ResponseEntity.ok().build();
    }


    /** ------------------
     *  Influnencer zzim
     *  -----------------
     */

    @GetMapping("/influencer/list")
    public ResponseEntity<?> getInfluencerZzimList(@RequestParam(name="pageIdx", defaultValue = "1") int pageIndex
            , @RequestParam(name="cntPerPage", defaultValue = SystemDef.STR_PAGING_DEF_CONTENTS_SIZE ) int cntPerPage
            , HttpSession session) {

        ItMemberEntity user = (ItMemberEntity) session.getAttribute("user");

        ApiResponse<Paging<List<InfluencerListInfoEntity>>> res = ApiResponse.<Paging<List<InfluencerListInfoEntity>>>builder()
                .data(zzimService.getInfluenZzimList(user.getMbNo(), pageIndex, cntPerPage))
                .build();

        return ResponseEntity.ok(res);
    }


    @PostMapping("/influner/add/{influenMbNo}")
    public ResponseEntity<?> addInfluencerZzim(@PathVariable Long influenMbNo, HttpSession session){
        ItMemberEntity user = (ItMemberEntity)session.getAttribute("user");

        zzimService.AddInfluencerZzim(user.getMbNo(), influenMbNo);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/influner/del/{influenMbNo}")
    public ResponseEntity<?> delInfluencerZzim(@PathVariable Long influenMbNo, HttpSession session ){
        ItMemberEntity user = (ItMemberEntity)session.getAttribute("user");

        zzimService.delInfluencerZzim(user.getMbNo(), influenMbNo);

        return ResponseEntity.ok().build();
    }
}
