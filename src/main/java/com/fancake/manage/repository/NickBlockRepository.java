package com.fancake.manage.repository;


import com.fancake.manage.entity.MemberAlarm;
import com.fancake.manage.entity.NickBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface NickBlockRepository extends JpaRepository<NickBlock, Integer>, QuerydslPredicateExecutor<NickBlock> {



}
