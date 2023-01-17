package com.movie.springmovieproject.repository;

import com.movie.springmovieproject.domain.Member;
import com.movie.springmovieproject.domain.Movie;
import com.movie.springmovieproject.domain.Review;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    //테이블의 전체데이터 가져오기
    // 기본키를 가지고 데이터 1개 가져오기
    //데이터 ㅏㅅㅂ입 과 수정에 사용되는 메서드 제공
    //기본키를 가지고 삭제하는 메서드와 엔티티를 가지고 삭제
    //이름을 기반으로 하는 메서드 생성이가능
    @EntityGraph(attributePaths = {"member"}, type = EntityGraph.EntityGraphType.FETCH)
    List<Review> findByMovie(Movie movie);
    //jpql 을 이용한 쿼리 작성 가능
    //쿼리디에스엘 을 이용한 쿼리 작성가능

    //user delete
    void deleteByMember(Member member);

    @Modifying //jpql에서 수정하고싶을때
    @Query("update Review r set r.member.mid=null where r.member.mid=:mid")
    void updateByMember(@Param("mid") Long id);
}
