package com.fancake.manage.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="it_mb_infulener_info")
@ToString
public class Influence extends BaseEntity{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="mb_no")
        private int mbno;

        @Column(name="introduction",length=100,nullable=false)
        private String introduction;

        @Column(name="act_youtube",length=50,nullable=false)
        private String actyoutube;

        @Column(name="act_afreeca",length=60,nullable=false)
        private String actafreeca;

        @Column(name="act_twitch",length=60,nullable=false)
        private String acttwitch;

        @Column(name="act_broadcast",length=60,nullable=false)
        private String actbroadcast;

        @Column(name="act_instagram",length=60,nullable=false)
        private String actinstagram;

        @Column(name="act_writer",length=60,nullable=false)
        private String actwriter;

        @Column(name="cover_img1",length=60,nullable=false)
        private String coverimg1;
        @Column(name="cover_img2",length=60,nullable=false)
        private String coverimg2;
        @Column(name="cover_img3",length=60,nullable=false)
        private String coverimg3;

        @Column(name="temp_nick",length=60,nullable=false)
        private String tempnick;

        @Column(name="temp_profile",length=60,nullable=false)
        private String tempprofile;

        @Column(name="req_date",length=60,nullable=false)
        private String reqdate;


}
