package com.fancake.manage.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="it_member_nick_block")
@ToString
public class NickBlock extends BaseEntity{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="mb_no")
        private int mbno;//gno;

        //Nick Block
        @Column(name="mb_nick",length=50,nullable=false)
        private String mbnick;//writer;



}
