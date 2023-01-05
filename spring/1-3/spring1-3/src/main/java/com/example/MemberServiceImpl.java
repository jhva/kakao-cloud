package com.example;

import com.example.dto.MemberDTO;
import com.example.entity.MemberEntity;
import com.example.repository.MemberRepository;
import com.example.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


//final 속성으로 만들어진 속성들에 동일한 자료형의 bean이있으면
//생성자를 이용해서 자동으루주입
@RequiredArgsConstructor
@Service
//bean 자동새성
public class MemberServiceImpl implements MemberService {
    //서비스는 Repository를 주입받아서 사용

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public MemberDTO findById(String id) {

        MemberEntity member = memberRepository.findById(id);
        // 레포지토리 메서드 호출
        MemberDTO memberDTO = MemberDTO.builder()
                .id(member.getId())
                .password(member.getPassword())
                .nickname(member.getNickname())
                .build();

        return memberDTO;
    }
}
