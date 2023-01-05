package com.example.repository;

import com.example.entity.MemberEntity;
import org.springframework.stereotype.Repository;


@Repository
public interface MemberRepository {

    //기본키를 가지고 하나의 데이터를 찾아오는 메서드

    public MemberEntity findById(String id);
    //기본키가 아닌애들은 다 리스트
}
