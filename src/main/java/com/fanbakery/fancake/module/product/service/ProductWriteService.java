package com.fanbakery.fancake.module.product.service;

import com.fanbakery.fancake.code.service.DirectoryCode;
import com.fanbakery.fancake.code.service.ShortUrlPathCode;
import com.fanbakery.fancake.code.service.item.ItemSelStatusCd;
import com.fanbakery.fancake.code.system.SystemDef;
import com.fanbakery.fancake.common.utils.DateUtil;
import com.fanbakery.fancake.config.ApplicationConfig;
import com.fanbakery.fancake.module.product.model.ProductAddReq;
import com.fanbakery.fancake.module.shorturl.service.ShortUrlService;
import com.fanbakery.fancake.repository.mapper.ProductMapper;
import com.fanbakery.fancake.repository.model.ItItemEntity;
import com.fanbakery.fancake.repository.model.ItMemberEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductWriteService {
    private final ProductMapper productMapper;
    private final ApplicationConfig applicationConfig;
    private final ShortUrlService shortUrlService;

    public void addProduct(ProductAddReq productAddReq, ItMemberEntity user) throws Exception {

        LocalDateTime today = LocalDateTime.now();


        //입찰 증가 금액 계산
        Float addPercent = productAddReq.getItemSellAddPercent();
        if( addPercent == null || addPercent.isNaN()){
            addPercent = SystemDef.ITEM_BIDDING_ADD_PERCENT;
        }

        Long sellStartPrice = productAddReq.getPBidcostVal();

        //1. data setting
        ItItemEntity item = ItItemEntity.builder()
                .itemName(productAddReq.getPSubject())
                .itemDesc(productAddReq.getPContent())
                .itemSellPrice(productAddReq.getPCostVal())
                .itemSellStartPrice(sellStartPrice)
                .itemSellAddPrice((long)Math.floor(sellStartPrice * addPercent * 0.01))
                .itemSellCurrPrice(productAddReq.getPBidcostVal())
                .itemLastBiddingSeq(0L)
                .itemAdult(productAddReq.isPAdult())
                .itemStatus(ItemSelStatusCd.SALE)
                .itemRegDate(today)
                .itemSellStartDate(today.toLocalDate())
                .itemSellEndDate(today.toLocalDate().plusDays(SystemDef.ITEM_SALE_DATE_PERIOD))
                .itemRegisterId(user.getMbId())
                .itemRegMbNo(user.getMbNo())
                .itemImg1(productAddReq.getImgUrl1())
                .itemImg2(productAddReq.getImgUrl2())
                .itemImg3(productAddReq.getImgUrl3())
                .itemImg4(productAddReq.getImgUrl4())
                .itemImg5(productAddReq.getImgUrl5())
                .itemImg6(productAddReq.getImgUrl6())
                .itemImg7(productAddReq.getImgUrl7())
                .itemImg8(productAddReq.getImgUrl8())
                .itemImg9(productAddReq.getImgUrl9())
                .itemImg10(productAddReq.getImgUrl10())
                .itemImg10(productAddReq.getImgUrl10())
                .build();

        //2. insert db
        productMapper.insertItem(item);
        Long item_seq = item.getItemSeq();

        //단축URL 업데이트
        item.setItemShortUrl(String.format("/s/%s/%s",
                ShortUrlPathCode.PRODUCT_PATH.getCode(),
                shortUrlService.encodingUrl(item_seq)));
        productMapper.updateShortUrl(item);

        log.debug("[ADD_ITEM] MB_NO_"+user.getMbNo() +", SEQ_" + item_seq + ", " + item.getItemName() );
        return;
    }



    public void editProduct(ProductAddReq productAddReq, ItMemberEntity user) throws Exception{
        //1. data setting
        ItItemEntity item = ItItemEntity.builder()
                .itemRegMbNo(user.getMbNo())
                .itemSeq(productAddReq.getItemSeq())
                .itemName(productAddReq.getPSubject())
                .itemDesc(productAddReq.getPContent())
                .itemImg1(productAddReq.getImgUrl1())
                .itemImg2(productAddReq.getImgUrl2())
                .itemImg3(productAddReq.getImgUrl3())
                .itemImg4(productAddReq.getImgUrl4())
                .itemImg5(productAddReq.getImgUrl5())
                .itemImg6(productAddReq.getImgUrl6())
                .itemImg7(productAddReq.getImgUrl7())
                .itemImg8(productAddReq.getImgUrl8())
                .itemImg9(productAddReq.getImgUrl9())
                .itemImg10(productAddReq.getImgUrl10())
                .itemImg10(productAddReq.getImgUrl10())
                .build();

        //2. insert db
        productMapper.updateItem(item);
    }



    public void moveProductImage(ProductAddReq productAddReq, List<String> tempFiles) throws IOException {
        // 디렉토리 경로 --> /full-path/product/yyyy
        String fullPathDir = String.format("%s/%s/%s", applicationConfig.getUploadConfig().getPhysicalPath(),
                DirectoryCode.PRODUCT_DIR.getCode(),
                DateUtil.getCurrentYear());

        int iLoop = 1;
        for (String tempFileName: tempFiles) {
            String tempFileFullPath = String.format("%s%s/%s", applicationConfig.getUploadConfig().getPhysicalPath(),
                    DirectoryCode.TEMP_DIR.getCode(),
                    tempFileName);
            String realFileFullPath = String.format("%s/%s", fullPathDir, tempFileName.substring(4));

            File fileProfile = new File(fullPathDir);
            File tempFile = new File(tempFileFullPath);
            File realFile = new File(realFileFullPath);

            // 미 존재시 디렉토리 생성.
            if (!fileProfile.exists()) {
                FileUtils.forceMkdir(fileProfile);
            }

            // temp 파일 이동
            FileUtils.moveFile(tempFile, realFile);

            // 실제 URL 경로 저장.
            String productUrl = String.format("%s/%s/%s/%s", applicationConfig.getUploadConfig().getUrlPath(),
                            DirectoryCode.PRODUCT_DIR.getCode(),
                            DateUtil.getCurrentYear(), tempFileName.substring(4));

            switch (iLoop){
                case 1:
                    productAddReq.setImgUrl1(productUrl);
                    break;
                case 2:
                    productAddReq.setImgUrl2(productUrl);
                    break;
                case 3:
                    productAddReq.setImgUrl3(productUrl);
                    break;
                case 4:
                    productAddReq.setImgUrl4(productUrl);
                    break;
                case 5:
                    productAddReq.setImgUrl5(productUrl);
                    break;
                case 6:
                    productAddReq.setImgUrl6(productUrl);
                    break;
                case 7:
                    productAddReq.setImgUrl7(productUrl);
                    break;
                case 8:
                    productAddReq.setImgUrl8(productUrl);
                    break;
                case 9:
                    productAddReq.setImgUrl9(productUrl);
                    break;
                case 10:
                    productAddReq.setImgUrl10(productUrl);
                    break;
            }

            iLoop++;
        }
    }
}
