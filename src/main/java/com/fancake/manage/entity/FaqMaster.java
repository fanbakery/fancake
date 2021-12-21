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
@Table(name="it_faq_master")
@ToString
public class FaqMaster extends BaseEntity{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="fm_id")
        private int fmid;

        @Column(name="fm_subject",length=11,nullable=false)
        private String fmsubject;

        @Column(name="fm_head_html",length=256,nullable=false)
        private String fmheadhtml;

        @Column(name="fm_tail_html",length=256,nullable=false)
        private String fmtailhtml;

        @Column(name="fm_mobile_head_html",length=256,nullable=false)
        private String fmmobileheadhtml;

        @Column(name="fm_mobile_tail_html",length=256,nullable=false)
        private String fmmobiletailhtml;

        @Column(name="fm_order",length=11,nullable=false)
        private int fmorder;

}
