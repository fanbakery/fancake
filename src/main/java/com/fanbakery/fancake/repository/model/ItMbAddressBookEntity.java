package com.fanbakery.fancake.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//Table ( name ="it_mb_address_book" )
public class ItMbAddressBookEntity implements Serializable {

    private static final long serialVersionUID =  4047926612298530649L;

    private Long abookSeq;


    /*  it_member:mb_no */
    private Long abookMbNo;

    /* 기본배송지 설정(설정:1) */
    private Boolean abookBaseYn;

    /*  배송지명 */
    private String abookTitle;

    /*  받는사람 */
    private String abookReciever;

    /*  배송지 전화번 */
    private String abookPhone;

    /*  배송지 주소 */
    private String abookAddress1;

    /*  배송지 주소 */
    private String abookAddress2;

    /*  배송지 주소 */
    private String abookAddress3;

    /*  배송지 주소 */
    private String abookZipCode;

    /*  배송지 추가정 */
    private String abookInfo;

    private LocalDateTime abookRegTime;
}
