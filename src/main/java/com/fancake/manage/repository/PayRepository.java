package com.fancake.manage.repository;


import com.fancake.manage.entity.Pay;
import com.fancake.manage.entity.Zzim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface PayRepository extends JpaRepository<Pay, Integer>, QuerydslPredicateExecutor<Pay> {



}
