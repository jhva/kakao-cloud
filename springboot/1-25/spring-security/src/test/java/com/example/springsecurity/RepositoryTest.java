package com.example.springsecurity;


import com.example.springsecurity.entity.ClubMember;
import com.example.springsecurity.entity.ClubMemberRole;
import com.example.springsecurity.repository.ClubMemberRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class RepositoryTest {

    @Autowired
    private ClubMemberRepository clubMemberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //샘플 데이터 삽입

    @Test
    public void insertMembers() {
        IntStream.rangeClosed(1, 100).forEach(i -> {

            ClubMember clubMember = ClubMember.builder()
                    .id("member" + i)
                    .mpw(passwordEncoder.encode("1111"))
                    .email("user" + i + "@gmail.com")
                    .name("사용자" + i)
                    .social(false)
                    .roleSet(new HashSet<ClubMemberRole>())
                    .build();
            clubMember.addRole(ClubMemberRole.USER);

            if (i > 90) {
                clubMember.addRole(ClubMemberRole.ADMIN);
            }
            clubMemberRepository.save(clubMember);
        });
    }

    @Test
    public void testRole() {
        //mid를 이용해서 조회하는 메서드
        Optional<ClubMember> result
                = clubMemberRepository.getWithRoles("memeber9099");

        if (result.isPresent()) {
            System.out.println(result);
            System.out.println(result.get().getRoleSet());
        } else {
            System.out.println("존재하지않는아이디");
        }
    }

    @Test
    public void testReadEmail() {
//        List<ClubMember> list = clubMemberRepository.findByEmail("user42@gmail.com");
//        for (ClubMember clubMember : list) {
////            System.out.println(clubMember);
//            System.out.println(clubMember.getRoleSet()); //호출하게되면 에러가난다
//
//        }

        Optional<ClubMember> clubMember =
                clubMemberRepository.findByEmail("user95@gmail.com");
        System.out.println(clubMember.get().getRoleSet());
    }
}
