package com.example.springboardproject;


import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@SpringBootTest
public class RepositoryTest {


    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private CommentRepository commentRepository;


    @Test
    //회원 데이터 삽입
    public void insertMember() {

        for (int i = 1; i <= 100; i++) {
            Member member = Member.builder()
                    .email("user" + i + "namver")
                    .password("1111")
                    .name("user" + i)
                    .build();
            memberRepository.save(member);
        }
    }


    @Test
    //보드데이터 삽입
    public void inserBoard() {

        for (int i = 1; i <= 100; i++) {
            Member member = Member.builder()
                    .email("user" + i + "namver")
                    .build();

            Board board = Board.builder()
                    .title("제목" + i)
                    .content("내용" + i)
                    .writer(member)
                    .build();

            boardRepository.save(board);
        }
    }

    @Test
    public void insertComment() {

        for (int i = 1; i <= 300; i++) {
            long bno = (long) (Math.random() * 100) + 1;

            Board board = Board.builder()
                    .bno(bno).build();


            Comment comment = Comment.builder()
                    .text("댓글" + i)
                    .board(board)
                    .replyer("guest")
                    .build();


            commentRepository.save(comment);

        }
    }

    @Test
    @Transactional
    //게시글 1개를 자여노느 ㄴ메서드
    public void readBoard() {
        Optional<Board> result = boardRepository.findById(100L);

        //실제 쿼리는 조인을 하고있다 .
        //내가당장 작성자 정보를 가져오는게 아니라면 시간낭비가될수있다 .
        Board board = result.get();
        System.out.println(board);
        System.out.println(board.getWriter());
    }

    @Test
    //댓글
    public void readComment() {
        Optional<Comment> result = commentRepository.findById(100L);

        Comment comment = result.get();
        System.out.println(comment.getBoard());
    }


    //board 데이터를 가져올 때 Writer의 데이터도 가져오기
    //현재 지연로딩 일때 따로따로 가져오게되는데 이걸 한번에 가져오는 방법 join
    @Test
    public void joinQueryTest() {
        //번호가 이상하게들어가는경우가 있다 .
        Object result = boardRepository.getBoardWithWriter(100L);
        System.out.println(Arrays.toString((Object[]) result));
    }

    //글번호를 받아서 Board 와 그리고 Board 와 관련된 Comment 정보 찾아오기
    // Board 1개의 여러개의 Comment가 존재함 //return type: List
    //결과가 Board 와 Reply를 결합한 형태의 목록으로 리턴이되어서


    @Test
    public void testJoin2() {
        List<Object[]> result =
                boardRepository.getBoardWithComment(100L);

        for (Object[] ar : result) {
            System.out.println(Arrays.toString(ar));
        }
    }


    @Test
    public void testJoin3() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

        Page<Object[]> result =
                boardRepository.getBoardWithReplyCount(pageable);

        result.get().forEach(row -> {
            Object[] ar = (Object[]) row;
            System.out.println(Arrays.toString(ar));
        });
    }

}
