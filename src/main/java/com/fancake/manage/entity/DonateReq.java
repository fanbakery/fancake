package com.fancake.manage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="it_donate_req")
@ToString
public class DonateReq extends BaseEntity{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="req_seq")
        private int reqseq;

        @Column(name="mb_no",length=11,nullable=false)
        private int mbno;

        @Column(name="donate_seq",length=11,nullable=false)
        private int donateseq;

        @Column(name="donate_price",length=11,nullable=false)
        private int donateprice;

        @Column(name="donate_person_name",length=256,nullable=false)
        private String donatepersonname;

        @Column(name="donate_person_birthday",length=256,nullable=false)
        private String donatepersonbirthday;

        @Column(name="donate_person_sex",length=256,nullable=false)
        private String donatepersonsex;

        @Column(name="donate_person_addr",length=256,nullable=false)
        private String donatepersonaddr;

        @Column(name="donate_req_date",length=60,nullable=false)
        private String donatereqdate;

        @Column(name="donate_status",length=20,nullable=false)
        private String donatestatus;

}
