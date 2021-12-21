package com.fanbakery.fancake.batch.service;


import com.fanbakery.fancake.config.ApplicationConfig;
import com.fanbakery.fancake.module.bidding.service.BiddingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CompleteSchedule {

    private final BiddingService biddingService;
    private final ApplicationConfig applicationConfig;

    // 테스트용 매분마다 배치 스케쥴 실행
    //@Scheduled(cron = "0 * * * * *")

    // 매일 00:01분마다 배치 스케쥴 실행
     @Scheduled(cron = "0 0 18 * * *")
    public void execute() throws Exception {
        log.info(">>> 상품 판매 종료 및 낙찰 완료 처리 배치 실행 시작 ");

        //판매 종료된 상품 상태 변경 및 마지막 입찰 -> 낙찰로 변경
         //입찰 참여 이력이 없는 상품은  종료일 +1로 추가
        biddingService.batchSetBidSuccessEndSaleItem();

        log.info(">>> 상품 판매 종료 및 낙찰 완료 처리 배치 실행 완료");
    }
}
