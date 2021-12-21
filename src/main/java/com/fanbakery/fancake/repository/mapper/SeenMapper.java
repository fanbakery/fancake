package com.fanbakery.fancake.repository.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;

@Mapper
public interface SeenMapper {
    /**
     * ------------
     * item_mb_seen table
     * ----------
     */
    //item_mb_seen -- insert
    public void insertItemMbSeen(Long itemSeq, Long mbNo, LocalDateTime itemSeenDate);

}
