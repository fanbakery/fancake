package com.fancake.manage.repository;


import com.fancake.manage.entity.Item;
import com.fancake.manage.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ItemRepository extends JpaRepository<Item, Integer>, QuerydslPredicateExecutor<Item> {



}
