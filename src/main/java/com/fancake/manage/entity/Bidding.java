package com.fancake.manage.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="it_item_bidding")
@ToString
public class Bidding extends BaseEntity{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="bidding_seq")
        private int biddingseq;

        @Column(name="item_seq",length=11,nullable=false)
        private int itemseq;

        @Column(name="bidding_mb_no",length=11,nullable=false)
        private int biddingmbno;

        @Column(name="bidding_price",length=11,nullable=false)
        private int biddingprice;

        @Column(name="bidding_status",length=50,nullable=false)
        private String biddingstatus;

        @Column(name="bidding_date",length=60,nullable=false)
        private String biddingdate;

        @Column(name="abook_reciever",length=60,nullable=false)
        private String abookreciever;

        @Column(name="abook_phone",length=60,nullable=false)
        private String abookphone;

        @Column(name="abook_address1",length=60,nullable=false)
        private String abookaddress1;

        @Column(name="abook_address2",length=60,nullable=false)
        private String abookaddress2;

        @Column(name="abook_address3",length=60,nullable=false)
        private String abookaddress3;

        @Column(name="abook_zip_code",length=60,nullable=false)
        private String abookzipcode;

        @Column(name="abook_info",length=60,nullable=false)
        private String abookinfo;

        @Column(name="bidding_cancel_date",length=60,nullable=false)
        private String biddingcanceldate;

        @Column(name="bidding_succ_date",length=60,nullable=false)
        private String biddingsuccdate;

}
