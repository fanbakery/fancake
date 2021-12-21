package com.fancake.manage.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="it_settlement")
@ToString
public class Pay extends BaseEntity{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="mb_no")
        private int mbno;

        @Column(name="item_seq",length=11,nullable=false)
        private int itemseq;

        @Column(name="settle_price",length=50,nullable=false)
        private int settleprice;

        @Column(name="settle_day",length=60,nullable=false)
        private String settleday;

        @Column(name="settle_status",length=60,nullable=false)
        private String settlestatus;

        @Column(name="settle_reg_date",length=60,nullable=false)
        private String settleregdate;

        @Column(name="is_settle_request",length=60,nullable=false)
        private String issettlerequest;

        @Column(name="settle_req_date",length=60,nullable=false)
        private String settlereqdate;

}
