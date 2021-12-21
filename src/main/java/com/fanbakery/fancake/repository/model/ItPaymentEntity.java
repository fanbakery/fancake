package com.fanbakery.fancake.repository.model;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ItPaymentEntity {

  private Long no;
  private String uid;
  private String payGroup;
  private String payItem;
  private String payModule;
  private String payMethod;
  private Long amount;
  private Long refundAmount;
  private String payInfos;
  private String discernCode;
  private String orderCode;
  private String tradeCode;
  private String payStatus;
  private String payIp;
  private LocalDateTime requestTime;
  private LocalDateTime paidTime;
  private LocalDateTime refundedTime;
  private LocalDateTime canceledTime;
  private String memo;
}
