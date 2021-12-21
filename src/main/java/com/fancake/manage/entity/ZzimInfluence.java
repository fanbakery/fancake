package com.fancake.manage.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="it_zzim_influencer")
@ToString
public class ZzimInfluence extends BaseEntity{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="zzim_influen_seq")
        private int zziminfluenseq;

        @Column(name="influen_mb_no",length=11,nullable=false)
        private int influenmbno;

        @Column(name="zzim_mb_no",length=50,nullable=false)
        private int zzimmbno;

        @Column(name="zzim_date",length=60,nullable=false)
        private String zzimdate;

}
