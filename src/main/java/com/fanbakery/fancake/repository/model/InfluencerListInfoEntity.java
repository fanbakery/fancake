package com.fanbakery.fancake.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InfluencerListInfoEntity {

    private Long mbNo;
    private String mbNick;
    private String mbProfile;

    private LocalDate actDate;

    //활동 채널
    private boolean actYoutube;
    private boolean actAfreeca;
    private boolean actTwitch;
    private boolean actBroadcast;
    private boolean actInstagram;
    private boolean actWriter;

    //커버 사진
    private String coverImg1;

    private Long zzimCnt;       //팔로워 수
    private boolean isMyZzim;
    private boolean isNew;
}
