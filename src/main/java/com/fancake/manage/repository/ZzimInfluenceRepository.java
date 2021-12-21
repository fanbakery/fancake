package com.fancake.manage.repository;


import com.fancake.manage.entity.Zzim;
import com.fancake.manage.entity.ZzimInfluence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ZzimInfluenceRepository extends JpaRepository<ZzimInfluence, Integer>, QuerydslPredicateExecutor<ZzimInfluence> {



}
