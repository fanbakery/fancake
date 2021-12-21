package com.fancake.manage.repository;


import com.fancake.manage.entity.Member;
import com.fancake.manage.entity.MemberAlarm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface MemberAlarmRepository extends JpaRepository<MemberAlarm, Integer>, QuerydslPredicateExecutor<MemberAlarm> {



}
