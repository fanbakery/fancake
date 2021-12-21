package com.fancake.manage.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="it_mb_address_book")
@ToString
public class Address extends BaseEntity{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="abook_seq")
        private int abookseq;

        @Column(name="abook_mb_no",length=11,nullable=false)
        private int abookmbno;

        @Column(name="abook_title",length=50,nullable=false)
        private String abooktitle;

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

        @Column(name="abook_reg_time",length=60,nullable=false)
        private String abookregtime;

}
