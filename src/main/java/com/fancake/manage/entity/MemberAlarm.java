package com.fancake.manage.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="it_member_alarm")
@ToString
public class MemberAlarm extends BaseEntity{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="mb_alarm_seq")
        private int mbalarmseq;

        @Column(name="mb_no")
        private int mbno;

        // 알람 메세지
        @Column(name="mb_alarm_msg",length=1024,nullable=false)
        private String mbalarmmsg;

        // 알람 관련 인플루언서 정보
        @Column(name="mb_infulencer_no",length=60,nullable=false)
        private String mbinfulencerno;

        // 알람 열람 여부 (0/1)
        @Column(name="mb_alarm_open_yn",length=60,nullable=false)
        private String mbalarmopenyn;




}
