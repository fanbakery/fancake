package com.fancake.manage.repository;


import com.fancake.manage.entity.Address;
import com.fancake.manage.entity.Influence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface InfluenceRepository extends JpaRepository<Influence, Integer>, QuerydslPredicateExecutor<Influence> {



}
