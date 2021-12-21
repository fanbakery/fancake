package com.fanbakery.fancake.repository.mapper;

import com.fanbakery.fancake.repository.model.ItFaqEntity;
import com.fanbakery.fancake.repository.model.ItFaqMasterEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MypageMapper {

    /**  ----------------
     * it_faq table
     ** ---------------------- */
    public List<ItFaqEntity> selectFaqList();


    /**  ----------------
     * it_faq_master table
     ** ---------------------- */
    public ItFaqMasterEntity selectFaqMasterByFmId(Long fmId);

}
