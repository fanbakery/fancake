package com.fancake.manage.repository;


import com.fancake.manage.entity.Faq;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface FaqRepository extends JpaRepository<Faq, Integer>, QuerydslPredicateExecutor<Faq> {



}
