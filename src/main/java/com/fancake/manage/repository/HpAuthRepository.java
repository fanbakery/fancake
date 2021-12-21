package com.fancake.manage.repository;


import com.fancake.manage.entity.HpAuth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface HpAuthRepository extends JpaRepository<HpAuth, Integer>, QuerydslPredicateExecutor<HpAuth> {



}
