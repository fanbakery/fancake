package com.fanbakery.fancake.module.product.model;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProductAddReq {
    private Long itemSeq;

    @NotBlank(message = "상품이름을 등록해주세요")
    private String pSubject;

    private String imgUrl1;
    private String imgUrl2;
    private String imgUrl3;
    private String imgUrl4;
    private String imgUrl5;
    private String imgUrl6;
    private String imgUrl7;
    private String imgUrl8;
    private String imgUrl9;
    private String imgUrl10;

    @NotNull(message = "상품 희망가격을 입력해주세요")
    private Long pCost;
    @NotNull(message = "상품 희망가격을 입력해주세요")
    private Long pCostVal;
    @NotNull(message = "구매 시작가격을 입력해주세요")
    private Long pBidcost;
    @NotNull(message = "구매 시작가격을 입력해주세요")
    private Long pBidcostVal;
    @NotBlank(message = "상품설명을 입력해주세요")
    private String pContent;

    private LocalDate itemSellStartDate;
    private LocalDate itemSellEndDate;

    private boolean pAdult;

    private Long itemSellAddPrice;
    private Float itemSellAddPercent;

    private List<String> tempFile;
    private List<String> productFileUrl = new ArrayList<>();

    @NotNull(message = "상품 이미지를 올려주세요")
    private List<MultipartFile> files ;
}
