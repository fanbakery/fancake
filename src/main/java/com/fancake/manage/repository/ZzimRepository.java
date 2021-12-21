package com.fancake.manage.repository;


import com.fancake.manage.entity.Seen;
import com.fancake.manage.entity.Zzim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ZzimRepository extends JpaRepository<Zzim, Integer>, QuerydslPredicateExecutor<Zzim> {



}
