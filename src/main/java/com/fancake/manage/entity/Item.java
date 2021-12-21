package com.fancake.manage.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="it_item")
@ToString
public class Item extends BaseEntity{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="item_seq")
        private int itemseq;

        //아이템명
        @Column(name="item_name",length=255,nullable=false)
        private String itemname;

        //프로필사진
        @Column(name="item_img1",length=255,nullable=false)
        private String itemimg1;

        //가격
        @Column(name="item_sell_price",length=100,nullable=false)
        private String itemsellprice;

        //추가가격
        @Column(name="item_sell_add_price",length=100,nullable=false)
        private String itemselladdprice;

        //입찰 시작가
        @Column(name="item_start_price",length=100,nullable=false)
        private String itemstartprice;


}
