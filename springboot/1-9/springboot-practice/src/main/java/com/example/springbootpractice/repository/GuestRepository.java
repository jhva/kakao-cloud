package com.example.springbootpractice.repository;

import com.example.springbootpractice.domain.GuestBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository
        extends JpaRepository<GuestBook, Long>, QuerydslPredicateExecutor<GuestBook> {


}
