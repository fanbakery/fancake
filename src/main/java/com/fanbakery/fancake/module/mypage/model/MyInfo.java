package com.fanbakery.fancake.module.mypage.model;

import com.fanbakery.fancake.code.service.member.MbSignatureCd;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MyInfo {
    private String mbNick;
    private String mbProfile;

    private MbSignatureCd mbSignature;     //인플루언스 상태

    private Integer totalCnt;            //구매 총개수( 입찰 중  + 완료)
    private Integer saleBidCnt;              //구매 - 판매 진행
    private Integer endSaleBidCnt;              //구매 - 판매 완료

    private Long shippingAddrSeq;        //배송지 주소 seq ??
    private String shippingAddr;           //배송지 주소


    //인플루언서 일경우 추가 정보 노출
    private String productSendAddr;        //상품 보낼 주소 (관리 화면에서 관리)

    private String influencerCoverImg;        //인플로언서 커버 이미지
}
