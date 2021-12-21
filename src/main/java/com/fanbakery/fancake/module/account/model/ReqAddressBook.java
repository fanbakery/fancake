package com.fanbakery.fancake.module.account.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class ReqAddressBook {
    private Long abookSeq;

    private Long abookMbNo;

    /*  배송지명 */
    @NotBlank
    private String abookTitle;

    /*  받는사람 */
    @NotBlank
    private String abookReciever;

    /*  배송지 전화번 */
    @NotBlank
    @Pattern(regexp = "^01([0|1|6|7|8|9])([0-9]{3,4})([0-9]{4})$")
    private String abookPhone;

    /*  배송지 주소 */
    @NotBlank
    private String abookAddress1;

    /*  배송지 주소 */
    private String abookAddress2;

    /*  배송지 주소 */
    private String abookAddress3;

    /*  배송지 주소 */
    @NotBlank
    private String abookZipCode;

    /*  배송지 추가정 */
    private String abookInfo;

}
