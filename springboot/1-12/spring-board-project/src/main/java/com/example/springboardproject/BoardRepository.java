package com.example.springboardproject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>, SearchBoardRepository {


    //Board 데이터를 가져올 때 Writer 정보도 가져오는 메서드
    @Query("select b,w from Board b left join b.writer w where b.bno=:bno")
    public Object getBoardWithWriter(@Param("bno") Long bno);


    //board 와 comment 가 결합한 형태의 목록으로 리턴
    @Query("select b,r from Board b left join Comment r on r.board = b where b.bno=:bno")
    List<Object[]> getBoardWithComment(@Param("bno") Long bon);


    //게시글 목록과 작성자 정보 그리고 댓글의 개수를 가져오는 메서드
    //개수를 셀려면 그룹바이를 사용해야함

    @Query("select b,w, count(r) from Board b left join b.writer w left join Comment r on r.board =b group by b")
    Page<Object[]> getBoardWithReplyCount(Pageable pageable);

    //글번호를 가지고 데이터를 찾아오는 메서드 - 상세보기
    @Query("select b, w , count(r) from Board b left join b.writer w left outer join Comment r  on r.board = b where b.bno = :bno")
    Object getBoardByBno(@Param("bno") Long bno);

}
