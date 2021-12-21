package com.fancake.manage.repository;


import com.fancake.manage.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface MemberRepository extends JpaRepository<Member, Integer>, QuerydslPredicateExecutor<Member> {



}
