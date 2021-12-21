package com.fanbakery.fancake.module.zzim.service;

import com.fanbakery.fancake.code.service.member.MbSignatureCd;
import com.fanbakery.fancake.common.model.ContentListReq;
import com.fanbakery.fancake.common.model.Paging;
import com.fanbakery.fancake.module.zzim.model.InfluencerZzimReq;
import com.fanbakery.fancake.module.zzim.model.ProductZzimReq;
import com.fanbakery.fancake.repository.mapper.ZzimMapper;
import com.fanbakery.fancake.repository.model.InfluencerListInfoEntity;
import com.fanbakery.fancake.repository.model.ItemListInfoEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class ZzimService {
    private final ZzimMapper zzimMapper;

    /** ------------
     *  ITEM ZZIM
     *  -------------
     */
    public Paging<List<ItemListInfoEntity>> getProductZzimList(Long myMbNo, Integer pageIndex, Integer cntPerPage) {

        //1. sql parameter setting
        ContentListReq listReq = setListReqParam(myMbNo, pageIndex, cntPerPage);

        //2. select db
        List<ItemListInfoEntity> dbProdList = zzimMapper.selectItemZzimListByMbNo(listReq);

        //3. set data
        Paging<List<ItemListInfoEntity>> paging = Paging.<List<ItemListInfoEntity>>builder()
                .pageIndex(ContentListReq.setResPageIndex(listReq.getPageIndex()))
                .cntPerPage(ContentListReq.setResCntPerPage(listReq.getCntPerPage()))
                .totalCnt(zzimMapper.selectItemZzimCountByMyMbNo(myMbNo))
                .lists(dbProdList)
                .build();

        return paging;
    }



    public void AddProductZzim(Long myMbNo, Long itemSeq ) {

        ProductZzimReq zzimReq = ProductZzimReq.builder()
                .itemSeq(itemSeq)
                .mbNo(myMbNo)
                .zzimDate(LocalDateTime.now())
                .build();

        zzimMapper.insertItemZzim(zzimReq);
        return;
    }

    public void delProductZzim(Long myMbNo, Long itemSeq ) {

        ProductZzimReq zzimReq = ProductZzimReq.builder()
                .itemSeq(itemSeq)
                .mbNo(myMbNo)
                .build();

        zzimMapper.deleteItemZzim(zzimReq);
        return;
    }




    /** ------------
     *  INFLUENCER ZZIM
     *  -------------
     */


    public Paging<List<InfluencerListInfoEntity>> getInfluenZzimList(Long myMbNo, Integer pageIndex, Integer cntPerPage) {

        //1. sql parameter setting
        ContentListReq listReq = setListReqParam(myMbNo, pageIndex, cntPerPage);
        listReq.setMbSignature(MbSignatureCd.INFLUEN_ACT);

        //2. select db
        List<InfluencerListInfoEntity> dbInfluenList = zzimMapper.selectInfluenZzimListByMbNo(listReq);

        //3. set data
        Paging<List<InfluencerListInfoEntity>> paging = Paging.<List<InfluencerListInfoEntity>>builder()
                .pageIndex(ContentListReq.setResPageIndex(listReq.getPageIndex()))
                .cntPerPage(ContentListReq.setResCntPerPage(listReq.getCntPerPage()))
                .lists(dbInfluenList)
                .totalCnt(zzimMapper.selectInfluenZzimCountByMyMbNo(myMbNo))
                .build();

        return paging;
    }


    public void AddInfluencerZzim(Long myMbNo, Long zzimMbNo) {

        InfluencerZzimReq zzimReq = InfluencerZzimReq.builder()
                .zzimMbNo(myMbNo)
                .influenMbNo(zzimMbNo)
                .zzimDate(LocalDateTime.now())
                .build();

        zzimMapper.insertInfluenZzim(zzimReq);
        return;
    }

    public void delInfluencerZzim(Long myMbNo, Long zzimMbNo) {
        InfluencerZzimReq zzimReq = InfluencerZzimReq.builder()
                .zzimMbNo(myMbNo)
                .influenMbNo(zzimMbNo)
                .build();

        zzimMapper.deleteInfluenZzim(zzimReq);
        return;
    }


    /** ------------
     *  local function
     *  -------------
     */

    private ContentListReq setListReqParam( Long myMbNo, Integer pageIndex, Integer cntPerPage ) {
        ContentListReq listReqParam = ContentListReq.builder()
                .mbNo(myMbNo)
                .pageIndex(ContentListReq.setReqPageIndex(pageIndex))
                .cntPerPage(cntPerPage)
                .build();

        return listReqParam;
    }
}
