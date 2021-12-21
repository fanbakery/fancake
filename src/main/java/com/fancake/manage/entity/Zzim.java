package com.fancake.manage.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="it_zzim_item")
@ToString
public class Zzim extends BaseEntity{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="zzim_item_seq")
        private int zzimitemseq;

        @Column(name="item_seq",length=11,nullable=false)
        private int itemseq;

        @Column(name="zzim_mb_no",length=50,nullable=false)
        private int zzimmbno;

        @Column(name="zzim_date",length=60,nullable=false)
        private String zzimdate;

}
