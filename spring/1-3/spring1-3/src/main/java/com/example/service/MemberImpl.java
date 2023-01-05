package com.example.service;

import com.example.entity.MemberEntity;
import com.example.repository.MemberRepository;

public class MemberImpl implements MemberRepository {
    @Override
    public MemberEntity findById(String id) {
        MemberEntity memberEntity = MemberEntity.builder()
                .id("asd")
                .nickname("김정훈")
                .password("1234")
                .build();
        return memberEntity;
    }
}