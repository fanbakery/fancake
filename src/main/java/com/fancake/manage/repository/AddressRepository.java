package com.fancake.manage.repository;


import com.fancake.manage.entity.Address;
import com.fancake.manage.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface AddressRepository extends JpaRepository<Address, Integer>, QuerydslPredicateExecutor<Address> {



}
