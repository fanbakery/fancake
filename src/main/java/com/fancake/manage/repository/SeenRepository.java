package com.fancake.manage.repository;


import com.fancake.manage.entity.Address;
import com.fancake.manage.entity.Seen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface SeenRepository extends JpaRepository<Seen, Integer>, QuerydslPredicateExecutor<Seen> {



}
