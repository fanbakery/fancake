package com.fanbakery.fancake.repository.mapper;

import com.fanbakery.fancake.common.model.ContentListReq;
import com.fanbakery.fancake.repository.model.ItemListInfoEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HomMapper {
    /**  ----------------
     * it_item table
     ** ---------------------- */
    public List<ItemListInfoEntity> selectPopularItemList(ContentListReq listReq );

    public Long selectCntRecentItemList(ContentListReq listReq);
    public List<ItemListInfoEntity> selectRecentItemList(ContentListReq listReq );

    public Long selectCntSeenItemList(ContentListReq listReq);
    public List<ItemListInfoEntity> selectSeenItemList(ContentListReq listReq);

    public Long selectCntBidItemList(ContentListReq listReq);
    public List<ItemListInfoEntity> selectBidItemList(ContentListReq listReq);

}
