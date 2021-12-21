package com.fancake.manage.repository;


import com.fancake.manage.entity.Donate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface DonateRepository extends JpaRepository<Donate, Integer>, QuerydslPredicateExecutor<Donate> {



}
