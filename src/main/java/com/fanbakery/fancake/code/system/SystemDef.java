package com.fanbakery.fancake.code.system;

public class SystemDef {
    //
    public static final int PAGING_DEF_CONTENTS_SIZE = 20;
    public static final String STR_PAGING_DEF_CONTENTS_SIZE = "20";

    // item reg date + n day
    public static final  int ITEM_SALE_DATE_PERIOD = 2; //2021.11.29 요청사항에 의해 30 ->2 변경
    public static final  int ITEM_SALE_DATE_TIME = 18;

    // item bidding def
    //public static final Long ITEM_BIDDING_ADD_PRICE = 3500L;        //2021.12.03 요청사항에 의해 변경
    public static final Float ITEM_BIDDING_ADD_PERCENT = 10F;    //2021.11.29 요청사항에 의해 10%증가로 변경


    //home view
    //item seen history period, n day
    public static final int ITEM_SEEN_DATE_PERIOD = 30;

    //item seen history period, n day
    public static final int ITEM_RECENT_DATE_PERIOD = 3;
}
