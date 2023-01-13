package com.example.springboardproject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
interface SearchBoardRepository {
    Board search1();

    //검색을 위한메서드
    Page<Object[]> searchPage(String type, String keyword, Pageable pageable);
}
