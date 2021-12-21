package com.fanbakery.fancake.repository.mapper;

import com.fanbakery.fancake.common.model.ContentListReq;
import com.fanbakery.fancake.module.zzim.model.InfluencerZzimReq;
import com.fanbakery.fancake.module.zzim.model.ProductZzimReq;
import com.fanbakery.fancake.repository.model.InfluencerListInfoEntity;
import com.fanbakery.fancake.repository.model.ItemListInfoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ZzimMapper {

    /**  ----------------
     * item zzim
     * it_zzim_item table
     ** ---------------------- */
    public List<ItemListInfoEntity> selectItemZzimListByMbNo(ContentListReq listReq);

    public Long selectItemZzimCountByMyMbNo(Long myMbNo);

    public Long selectItemZzimCountByItemSeq(Long itemSeq);
    public Boolean exsitsMyItemZzimByMbNo(Long itemSeq, Long mbNo);

    public void insertItemZzim(ProductZzimReq zzimReq);
    public void deleteItemZzim(ProductZzimReq zzimReq);

    /**  ----------------
     * influencer zzim
     * it_zzim_influencer table
     ** ---------------------- */

    public List<InfluencerListInfoEntity> selectInfluenZzimListByMbNo(ContentListReq listReq);

    public Long selectInfluenZzimCountByMyMbNo(Long myMbNo);
    public Long selectInfluenZzimCountByMbNo(Long mbNo);

    public void insertInfluenZzim(InfluencerZzimReq zzimReq);
    public void deleteInfluenZzim(InfluencerZzimReq zzimReq);
}
