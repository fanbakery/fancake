package com.fancake.manage.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="it_member")
@ToString
public class Member extends BaseEntity{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="mb_no")
        private int mbno;//gno;

        //프로필사진
        @Column(name="mb_profile",length=60,nullable=false)
        private String mbprofile;//title;

        //ID email
        @Column(name="mb_email",length=50,nullable=false)
        private String mbemail;//writer;

        //닉네임
        @Column(name="mb_nick",length=60,nullable=false)
        private String mbnick;//content;

        //전화번호
        @Column(name="mb_hp",length=60,nullable=false)
        private String mbhp;//content;

        //APP/KAKAO/FACEBOOK
        @Column(name="mb_route",length=256,nullable=false)
        private String mbroute;//content;





}
