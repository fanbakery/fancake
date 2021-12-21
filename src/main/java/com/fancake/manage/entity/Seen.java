package com.fancake.manage.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="it_item_mb_seen")
@ToString
public class Seen extends BaseEntity{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="seen_seq")
        private int seenseq;

        @Column(name="item_seq",length=11,nullable=false)
        private int itemseq;

        @Column(name="mb_no",length=50,nullable=false)
        private int mbno;

        @Column(name="item_seen_date",length=60,nullable=false)
        private String itemseendate;

}
