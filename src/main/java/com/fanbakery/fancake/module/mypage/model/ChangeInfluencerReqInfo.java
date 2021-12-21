package com.fanbakery.fancake.module.mypage.model;

import com.fanbakery.fancake.common.model.ImgUrlInfo;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Builder
public class ChangeInfluencerReqInfo {
    private Long mbNo;

    @NotBlank
    private String mbNick;     //활동명

    private boolean actYoutube;
    private boolean actAfreeca;
    private boolean actTwitch;
    private boolean actBroadcast;
    private boolean actInstagram;
    private boolean actWriter;


    //프로파일 사진 등록 필수~
    private ImgUrlInfo profile;             //프로파일  //인플루언서 전환 완료되면 it_member.mb_profile 로 업데이트
    private String temp_profile;             //임시 프로파일 파일명  //인플루언서 전환 완료되면 it_member.mb_profile 로 업데이트

    //커버 사진 등록 1개 필수
    private List<ImgUrlInfo> coverImg;      //커버이미지
    private List<String> temp_coverImg;      //임시 커버이미지

    //소개글
    private String introduction;

    private Boolean agree;

    public boolean isSetAnyActChannel(){
        return (this.actYoutube || this.actAfreeca
                    || this.actTwitch || this.actBroadcast
                    || this.actInstagram || this.actWriter );
    }

    public boolean isSetAnyCoverImg() {
        return (this.coverImg == null ? false : true);
    }
}
