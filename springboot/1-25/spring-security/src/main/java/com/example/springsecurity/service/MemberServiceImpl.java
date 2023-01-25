package com.example.springsecurity.service;

import com.example.springsecurity.dto.ClubMemberjoinDTO;
import com.example.springsecurity.dto.ClubMembmerSecurityDTO;
import com.example.springsecurity.entity.ClubMember;
import com.example.springsecurity.entity.ClubMemberRole;
import com.example.springsecurity.repository.ClubMemberRepository;
import com.example.springsecurity.repository.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Log4j2
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final ClubMemberRepository clubMemberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void join(ClubMemberjoinDTO clubMembmerSecurityDTO) throws MidExistException {

        String mid = clubMembmerSecurityDTO.getMid();
        boolean exist = clubMemberRepository.existsById(mid);

        if (exist) {
            throw new MidExistException();
        }
        //회원가입을 위해서 입력받은 정보를 가지고 Entity생성

        ClubMember member = ClubMember.builder()

                .id(clubMembmerSecurityDTO.getMid())
                .mpw(clubMembmerSecurityDTO.getMpw())
                .email(clubMembmerSecurityDTO.getEmail())
                .name(clubMembmerSecurityDTO.getName())
                .del(clubMembmerSecurityDTO.isDel())
                .social(clubMembmerSecurityDTO.isSocial())
                .build();


//        clubMemberRepository.save(member);
        //비밀번호 암호화
        member.changePassword(passwordEncoder.encode(clubMembmerSecurityDTO.getMpw()));

        //권한 설정
        member.addRole(ClubMemberRole.USER);
        log.info("member" + member);
        log.info(member.getRoleSet());


        clubMemberRepository.save(member);
    }
}
