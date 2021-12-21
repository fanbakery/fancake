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
@Table(name="it_phone_cert")
@ToString
public class HpAuth extends BaseEntity{

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="phone")
        private String phone;

        @Column(name="cert",length=10,nullable=false)
        private String cert;

        @Column(name="send_time",length=60,nullable=false)
        private String sendtime;

        @Column(name="expire_time",length=60,nullable=false)
        private String expiretime;

}
