package com.example.springboardproject;

import com.example.springboardproject.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Modifying
    @Query("delete from Comment c where c.board.bno = :bno")
    void deleteByBno(@Param("bno") Long bnt);

    //게시글을 이용해서 데이터 찾아오기
    List<Comment> findByBoardOrderByRno(Board board);
}
