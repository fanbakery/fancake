package com.fanbakery.fancake.module.influencer.controller;

import com.fanbakery.fancake.code.service.ListOrderTypeCd;
import com.fanbakery.fancake.code.service.item.ItemSelStatusCd;
import com.fanbakery.fancake.code.service.member.MbSignatureCd;
import com.fanbakery.fancake.code.system.SystemDef;
import com.fanbakery.fancake.common.model.ContentListReq;
import com.fanbakery.fancake.module.influencer.model.InfluenInvenInfo;
import com.fanbakery.fancake.module.influencer.model.InfluenItemInfo;
import com.fanbakery.fancake.module.influencer.model.InfluenMypageInfo;
import com.fanbakery.fancake.module.influencer.service.InfluencerInvenService;
import com.fanbakery.fancake.module.influencer.service.InfluencerService;
import com.fanbakery.fancake.module.mypage.service.MypageChangeInfluencerService;
import com.fanbakery.fancake.repository.model.ItMemberEntity;
import com.fanbakery.fancake.repository.model.OrderListInfoEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/influencer")
public class InfluencerController {

    private final InfluencerService influencerService;
    private final InfluencerInvenService influencerInvenService;
    private final MypageChangeInfluencerService changeInfluencerService;


    @GetMapping("/tab")
    public String tabView(@RequestParam(name="orderType"
                                        , defaultValue = ListOrderTypeCd.values.RECENT
                                        , required = false) ListOrderTypeCd orderType
                            , Model model, HttpSession session){

        ItMemberEntity user = (ItMemberEntity) session.getAttribute("user");

        //1.get influencer List Info
        ContentListReq listReq = ContentListReq.builder()
                .mbNo(user.getMbNo())
                .mbSignature(MbSignatureCd.INFLUEN_ACT)
                .pageIndex(ContentListReq.setReqPageIndex(1))
                .cntPerPage(SystemDef.PAGING_DEF_CONTENTS_SIZE)
                .orderType(orderType)
                .build();

        //2. set data
        model.addAttribute("orderType", orderType);
        model.addAttribute("influenList", influencerService.getInfluenListInfo(listReq));

        return "influencer/influencerTab";
    }


    @GetMapping("/inven")
    public String inven(@RequestParam(name="isReady", required = false) Boolean isReady
                                        ,  Model model, HttpSession session){

        ItMemberEntity user = (ItMemberEntity)session.getAttribute("user");


        //1. check the influencer ready status and update act status
        if(isReady != null && isReady.equals(true) ) {
            //update influencer act information
            //          it_mb_influen_info :: temp_nick, temp_profile
            //  ==>     it_member          :: nick, profile
           ItMemberEntity changUserInfo = changeInfluencerService.changeInfluencerActInfo(user);

           session.removeAttribute("user");
           session.removeAttribute("userType");
           session.setAttribute("user", changUserInfo);
           session.setAttribute("userType", changUserInfo.getMbSignature());

             user = changUserInfo;
        }

        //2. 인풀루언서 일반적인 정보
        InfluenInvenInfo invenInfo = influencerInvenService.getInfluencerInvenInfo(user);
        if(invenInfo == null) {
            log.error("[INFLUN_INVEN] :: not Influencer MB_NO_" + user.getMbNo());
            return "redirect:/";
        }

        //3. 날자별 진행중, 판매중 상품 리스트 조회
        if(invenInfo.getTotalSaleCnt() > 0 ) {
            List<InfluenItemInfo> influenItemList = influencerInvenService.getInfluencerItemList(user);
            model.addAttribute("influenItemList", influenItemList);
        }

        model.addAttribute("invenInfo", invenInfo);

        return "influencer/influencerInven";
    }



    @GetMapping("/mypage")
    public String mypage(@RequestParam(name = "no", required = true) Long influenMbNo
                            , Model model
                            , HttpSession session) {

        ItMemberEntity user = (ItMemberEntity)session.getAttribute("user");
        //1. get influen info
        InfluenMypageInfo mypageInfo = influencerService.getInflunMypageInfo(influenMbNo, user);
        if( mypageInfo == null) {
            log.error("[INFLUEN_MYPAGE] no influencer info !! MB_NO_"+ influenMbNo);
            return "redirect:/influencer/tab";
        }

        //2. set data
        model.addAttribute("mypage", mypageInfo );

        return "influencer/influencerMypage";
    }


    @GetMapping("/salelist")
    public String saleHistory( @RequestParam(name="type", defaultValue = "TOTAL") String type
                                                , Model model, HttpSession session){

        ItMemberEntity user = (ItMemberEntity)session.getAttribute("user");
        Long mbNo = user.getMbNo();

        //1.get sale count
        ContentListReq listReq = ContentListReq.builder()
                .mbNo(mbNo)
                .itemStatus(ItemSelStatusCd.SALE)
                .startDate(LocalDate.now())
                .build();


        //3. select count
        Integer saleCount = influencerInvenService.getInfluenSaleItemCount(listReq);
        Integer saleOutCount = influencerInvenService.getInfluenEndSaleItemCount(listReq);
        Integer totalSaleCnt = saleCount + saleOutCount;

        //2. check type
        List<OrderListInfoEntity> hisList = null;

        if( type.equals("TOTAL") && (totalSaleCnt > 0) ) {
            hisList =  influencerInvenService.getInfleunSaleHistTotalList(mbNo);
        } else if( type.equals("SALE") && (saleCount > 0)) {
            hisList =  influencerInvenService.getInfleunSaleHistSaleList(mbNo);
        } else {
            //type == "END"
            if( saleOutCount > 0) {
                hisList = influencerInvenService.getInfleunSaleHistEndSaleList(mbNo);
            }
        }


        //2. set res
        model.addAttribute("totalSaleCnt", totalSaleCnt);
        model.addAttribute("saleCount", saleCount);
        model.addAttribute("saleOutCount", saleOutCount);

        model.addAttribute("type", type);
        model.addAttribute("hisList", hisList);

        return "influencer/influencerSalelist";
    }
}
