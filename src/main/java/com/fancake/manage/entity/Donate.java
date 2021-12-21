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
@Table(name="it_donate")
@ToString
public class Donate extends BaseEntity{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="donate_seq")
        private int donateseq;

        @Column(name="donate_name",length=256,nullable=false)
        private String donatename;

        @Column(name="donate_regdate",length=60,nullable=false)
        private String donateregdate;

        @Column(name="donate_use_yn",length=11,nullable=false)
        private int donateuseyn;

}
