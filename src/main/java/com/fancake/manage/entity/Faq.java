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
@Table(name="it_faq")
@ToString
public class Faq extends BaseEntity{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="fa_id")
        private int faid;

        @Column(name="fm_id",length=11,nullable=false)
        private int fmid;

        @Column(name="fa_subject",length=256,nullable=false)
        private String fasubject;

        @Column(name="fa_content",length=256,nullable=false)
        private String facontent;

        @Column(name="fa_order",length=11,nullable=false)
        private int faorder;

        @Column(name="fa_reg_date",length=60,nullable=false)
        private String faregdate;

}
