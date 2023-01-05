package com.example.service;

import com.example.dto.MemberDTO;
import com.example.entity.MemberEntity;
import com.example.repository.MemberRepository;

public interface MemberService {


    //기본키1개를 받아서 하나의 데이터를 릴턴하는 메서드
//매개변수 나 리턴타입에 Entity 타입을 사용하면 안된다ㅓ
    public MemberDTO findById(String id);
        //repository에 필요한 매개변수를 생//컨트롤러 에게 전달할 데이터를 생성


}
