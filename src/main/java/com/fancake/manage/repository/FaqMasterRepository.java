package com.fancake.manage.repository;


import com.fancake.manage.entity.FaqMaster;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface FaqMasterRepository extends JpaRepository<FaqMaster, Integer>, QuerydslPredicateExecutor<FaqMaster> {



}
