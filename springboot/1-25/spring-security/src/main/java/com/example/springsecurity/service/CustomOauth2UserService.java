package com.example.springsecurity.service;


import com.example.springsecurity.dto.ClubMembmerSecurityDTO;
import com.example.springsecurity.entity.ClubMember;
import com.example.springsecurity.entity.ClubMemberRole;
import com.example.springsecurity.repository.ClubMemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomOauth2UserService extends DefaultOAuth2UserService {


    private final ClubMemberRepository clubMemberRepository;
    private final PasswordEncoder passwordEncoder;

    //카카오 로그인 성공 후 넘어오는 데이터를 이용해서 eamil을 추출해서 리턴하는 메서드
    //카카오 계정 정보가 있는 Map을 추출
    private String getKakaoEmail(Map<String, Object> paramMap) {
        log.info("카카오 맵" + paramMap.toString());
        Object value = paramMap.get("kakao_account");
        LinkedHashMap accountMap = (LinkedHashMap) value;
        String email = (String) accountMap.get("email");

        log.info("카카오 계정 이메일" + email);
        return email;

    }

    //email 정보가 있으면 그에 해당하는 DTO를 리턴하고 없으면
    //회원가입하고 리턴하는 사용자 정의 메서드
    private ClubMembmerSecurityDTO genereateDTO(String email, Map<String, Object> params) {
        //eamil 을 가지고 이데이터 찾앙오기
        Optional<ClubMember> result = clubMemberRepository.findByEmail(email);

        if (result.isEmpty()) {
            log.info("유저 없어서 회원가입 진행중 ");
            //없어서 회원가입을 해야하는 경우
            ClubMember member = ClubMember.builder()
                    .id(email)
                    .mpw(passwordEncoder.encode("1111"))
                    .email(email)
                    .social(true)
                    .build();
            member.addRole(ClubMemberRole.USER);
            clubMemberRepository.save(member);
            //회원가입에 성공한 정보를 리턴
            ClubMembmerSecurityDTO membmerSecurityDTO = new ClubMembmerSecurityDTO(email, "1111", Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")), email, null, false, true);
            membmerSecurityDTO.setSocialMap(params);
            return membmerSecurityDTO;

        } else {

            //있을 경우 로그인 (이메일이 존재하는경우)
            ClubMember member = result.get();
            ClubMembmerSecurityDTO membmerSecurityDTO =
                    new ClubMembmerSecurityDTO(member.getName(), member.getMpw(), member.getRoleSet().stream().map(role ->
                            new SimpleGrantedAuthority("ROLE_" + role.name())).collect(Collectors.toList())
                            , member.getEmail(), member.getName(), member.isDel(), member.isSocial());

            System.out.println(membmerSecurityDTO);

            return membmerSecurityDTO;
        }
    }


    //로그인 성공했을 때 호출되는 메서드
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        //로그인에 성공한 서비스의 정보 가져오기
        ClientRegistration clientRegistration = userRequest.getClientRegistration();

        String clientName = clientRegistration.getClientName();
        log.info("client-Name (load User)" + clientName);

        //계정에 대한 정보 가져오기

        OAuth2User oAuth2User = super.loadUser(userRequest);

        Map<String, Object> paramMap = oAuth2User.getAttributes();
        String email = null;
        switch (clientName.toLowerCase()) {
            case "kakao":
                email = getKakaoEmail(paramMap);
                break;
        }
        log.info("email (loadUser method )" + email);

        return genereateDTO(email, paramMap);
//        return super.loadUser(userRequest);
    }


}
