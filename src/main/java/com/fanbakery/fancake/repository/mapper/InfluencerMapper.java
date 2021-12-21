package com.fanbakery.fancake.repository.mapper;

import com.fanbakery.fancake.common.model.ContentListReq;
import com.fanbakery.fancake.repository.model.InfluencerListInfoEntity;
import com.fanbakery.fancake.repository.model.ItMbInfulenerInfoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InfluencerMapper {


    /**  ----------------
     * it_mb_infulener_info table
     ** ---------------------- */
    //select
    public Boolean existInfluenNickByMbNick(Long mbNo, String nick);

    public ItMbInfulenerInfoEntity selectInfluenInfo(Long mbNo );

    public List<InfluencerListInfoEntity> selectInfluenListInfo(ContentListReq listReq);


    //insert
    public void insertMbInfluencerInfo( ItMbInfulenerInfoEntity mbInfulenerInfo);


    public void updateIntroduce(Long mbNo, String introduction);

    public void updateCoverImage(Long mbNo, String realCoverImg1, String realCoverImg2, String realCoverImg3);
}
