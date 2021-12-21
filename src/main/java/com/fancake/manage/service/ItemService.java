package com.fancake.manage.service;

import com.fancake.manage.dto.ItemDTO;
import com.fancake.manage.dto.MemberDTO;
import com.fancake.manage.dto.PageRequestDTO;
import com.fancake.manage.dto.PageResultDTO;
import com.fancake.manage.entity.Item;
import com.fancake.manage.entity.Member;

public interface ItemService {

    int register(ItemDTO dto);
    ItemDTO view(int itemseq);

    //이후에 추가한 부분
    PageResultDTO<ItemDTO, Item> getList(PageRequestDTO requestDTO);

    default Item dtoToEntity(ItemDTO dto){

        //itemseq  //itemimg1    //itemsellprice  //itemselladdprice //itemstartprice
        Item entity = Item.builder()
                .itemseq(dto.getItemseq())
                .itemname(dto.getItemname())
                .itemimg1(dto.getItemimg1())
                .itemsellprice(dto.getItemsellprice())
                .itemselladdprice(dto.getItemselladdprice())
                .itemstartprice(dto.getItemstartprice())
                .build();
        return entity;

    }

    default ItemDTO entityToDto(Item entity){

        ItemDTO dto = ItemDTO.builder()
                .itemseq(entity.getItemseq())
                .itemname(entity.getItemname())
                .itemimg1(entity.getItemimg1())
                .itemsellprice(entity.getItemsellprice())
                .itemselladdprice(entity.getItemselladdprice() )
                .itemstartprice(entity.getItemstartprice() )
                .itemregDate(entity.getItemregDate())
                .modDate(entity.getModDate())
                .build();

        return dto;

    }

}
