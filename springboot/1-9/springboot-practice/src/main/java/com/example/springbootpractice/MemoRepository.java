package com.example.springbootpractice;

import com.example.springbootpractice.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface MemoRepository extends JpaRepository<Memo, Long> {
    //mno 의 값이 from 부터 to 사이인 데이터 조회 하는 메서드
    List<Memo> findByIdBetween(Long from, Long to);

    //mno 의 값이 from 부터 to 사이인 데이터를 Mno의 내림차순 정렬해서 조회하는 메서드
    //페이징으로 리턴

    Page<Memo> findByIdBetweenOrderByIdDesc(Long from, Long to, Pageable pageable);

    //num보다 작은 id를 가진애들을 지워준다 num이 20이면
    //1~19까지 삭제
    void deleteByIdLessThen(Long num);


    @Transactional
    @Modifying
    //Native sql 이 아니기때문에 Table 이름을 적는 것이 아니고
    //Entity 클래스의 이름을 사용해야한다 .
    @Query("update Memo m set m.memoText = :#{#param.memoText} where m.id = #{#param.id}")
    public int updateQueryMemoText(@Param("param") Memo memo);


    @Query("select m from Memo where m.id > :id")
    Page<Memo> getListWithQuery(@Param("id") Long id ,Pageable pageable);

}
