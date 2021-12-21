package com.fanbakery.fancake.repository.model;

import com.fanbakery.fancake.code.service.member.MbRouteCd;
import com.fanbakery.fancake.code.service.member.MbSignatureCd;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItMemberEntity implements Serializable {
    private MbRouteCd mbRoute;  //가입 경로
    private Long mbNo;
    private String mbId;        //삭제하면 안됨!!... 아이템 등록 등에서 사용됨.
    private String mbPassword;
    private String mbName;
    private String mbNick;
    private Date mbNickDate;
    private String mbEmail;
    private String mbHomepage;
    private Integer mbLevel;
    private String mbSex;
    private String mbBirth;
    private String mbTel;
    private String mbHp;
    private String mbCertify;
    private Integer mbAdult;
    private String mbDupinfo;
    private String mbZip1;
    private String mbZip2;
    private String mbAddr1;
    private String mbAddr2;
    private String mbAddr3;
    private String mbAddrJibeon;
    private MbSignatureCd mbSignature;     //인플루언스 상태
    private LocalDate mbSignatureDate;  //인플루언스 신청일
    private LocalDate mbSignatureCompleteDate;  //인플루언스 전환 완료일
    private String mbRecommend;
    private BigDecimal mbPoint;
    private BigDecimal mbCostSms;
    private BigDecimal mbCostLms;
    private BigDecimal mbCostMms;
    private LocalDateTime mbTodayLogin;
    private String mbLoginIp;
    private LocalDateTime mbDatetime;
    private String mbIp;
    private Boolean mbIsUnregistered;
    private LocalDateTime mbLeaveDate;          //탈퇴일
    private String mbInterceptDate;
    private LocalDateTime mbEmailCertify;
    private String mbMemo;
    private String mbLostCertify;
    private Integer mbMailling;
    private Integer mbSms;
    private Integer mbOpen;
    private Date mbOpenDate;
    private String mbProfile;
    private String mbMemoCall;

    private String mbProfile2;
    private String mbProfile3;
    private String mbProfile4;
    private String mbProfile5;

    /*  메세지 알람 설정 (0/1) */
    private Integer mbAlarmMessageYn;

    /*  소리 설정 (0/1) */
    private Integer mbAlarmSoundYn;

    /*  진동 설정 (0/1) */
    private Integer mbAlarmVibrationYn;
}
