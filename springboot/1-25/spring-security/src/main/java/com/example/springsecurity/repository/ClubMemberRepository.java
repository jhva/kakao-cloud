package com.example.springsecurity.repository;

import com.example.springsecurity.entity.ClubMember;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClubMemberRepository extends JpaRepository<ClubMember, String> {
    //mid를 매개변수로 받아서 소셜의 값이 false 인 데이터를 전부 찾아오는 메서드
    //sql
    //select * from club_member m,club_member_role_set s
    //where m.mid =s.mid and m.mid =? and m.social=false
    @EntityGraph(attributePaths = "roleSet")
    //ㅅ쓴이유 exclud를 사용했기때문에
    @Query("SELECT m FROM ClubMember  m  where m.id = :mid and m.social=false")
    Optional<ClubMember> getWithRoles(String mid);


    @EntityGraph(attributePaths = "roleSet", type = EntityGraph.EntityGraphType.LOAD)
    @Query("select m from ClubMember  m where m.email = :email")
    Optional<ClubMember> findByEmail(@Param("email") String email);

}
