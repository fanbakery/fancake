package com.fancake.manage.repository;


import com.fancake.manage.entity.Address;
import com.fancake.manage.entity.Bidding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface BiddingRepository extends JpaRepository<Bidding, Integer>, QuerydslPredicateExecutor<Bidding> {



}
