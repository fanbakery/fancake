package com.fancake.manage.repository;


import com.fancake.manage.entity.DonateReq;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface DonateReqRepository extends JpaRepository<DonateReq, Integer>, QuerydslPredicateExecutor<DonateReq> {



}
