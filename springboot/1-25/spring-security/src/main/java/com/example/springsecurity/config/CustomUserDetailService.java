package com.example.springsecurity.config;


import com.example.springsecurity.dto.ClubMembmerSecurityDTO;
import com.example.springsecurity.entity.ClubMember;
import com.example.springsecurity.repository.ClubMemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final ClubMemberRepository clubMemberRepository;


    //맨처음엔 autowired로 했지만 순환참조가 일어났다.
//    private PasswordEncoder passwordEncoder;

//    public CustomUserDetailService() {
//        this.passwordEncoder = new BCryptPasswordEncoder();
//    }

//    public CustomUserDetailService() {
//        this.passwordEncoder = passwordEncoder;
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //아이디를 입력하고 로그인 요청을 하게되면 아이디에 해당하는 데이터를 찾아오는 메서드
        //로그인 처리를 해주어야한다
        log.info("loadUserByUsername: " + username);


        //로그인 성공한 경우 생성
        //실제로는 데이터베이스에서 읽어서 생성
//        UserDetails userDetails = User.builder()
//                .username("user1")
//                .password(passwordEncoder.encode("1111"))
//                //권한
//                .authorities("ROLE_USER")
//                .build();

        Optional<ClubMember> result =
                clubMemberRepository.getWithRoles(username);
        if (result.isEmpty()) {
            throw new UsernameNotFoundException("없는 사용자 이름");
        }

        //존재하는 사용자 찾ㅇ아오기

        ClubMember member = result.get();
        ClubMembmerSecurityDTO clubMembmerSecurityDTO =
                new ClubMembmerSecurityDTO(member.getName(), member.getMpw(),
                        member.getRoleSet().stream().map(memberRole -> new SimpleGrantedAuthority
                                ("ROLE_" + memberRole.name())).collect(Collectors.toList()),
                        member.getId(), member.getMpw(), member.isDel(), false
                );

        return clubMembmerSecurityDTO;
    }
}
