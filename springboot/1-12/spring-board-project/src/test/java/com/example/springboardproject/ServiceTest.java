//
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//public class ServiceTest {
//
//
//    @Autowired
//    public  BoardService boardService;
//
//
//    @Test
//    public void registerTest() {
//        BoardDTO dto = BoardDTO.builder()
//                .title("등록테스트")
//                .content("등록을 테스트한다 내용부분")
//                .writerEmail("user333")
//                .build();
//
//        Long bno = boardService.register(dto);
//        System.out.println(bno);
//
//    }
//}
package com.example.springboardproject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import java.util.List;

@SpringBootTest
@Service
public class ServiceTest {
    @Autowired
    public BoardService boardService;

    @Autowired
    public CommentService commentService;

    @Test
    public void reigster() {
        CommentDTO dto = CommentDTO.builder()
                .text("댓글 텍스트")
                .replyer("user1")
                .bno(27L)
                .build();
//        commentService.register(dto);
        System.out.println(commentService.register(dto));
    }


    @Test
    public void testGetList() {
        List<CommentDTO> list = commentService.getList(27L);
        list.forEach(dto -> System.out.println(dto));
    }

    @Test
    public void test() {
        System.out.println(boardService);
    }

    @Test
    public void updateTestBoard() {
        BoardDTO dto = BoardDTO.builder()
                .bno(72L)
                .title("이잉?")
                .content("내용")
                .build();
        boardService.modifyBoard(dto);
    }

    //등록 테스트
    @Test
    public void registerTest() {
        BoardDTO dto = BoardDTO.builder()
                .title("등록 테스트")
                .content("등록을 테스트합니다.")
                .writerEmail("user100namver")
                .build();
        Long bno = boardService.register(dto);
        System.out.println(bno);
    }

    @Test
    public void testList() {
        PageRequestDTO pageRequestDTO =
                new PageRequestDTO();


        PageResponseDTO<BoardDTO, Object[]> result
                = boardService.getList(pageRequestDTO);


        System.out.println(result);
    }

    @Test
    public void testGet() {
        Long bno = 100L;
        BoardDTO boardDTO = boardService.get(bno);
        System.out.println(boardDTO);
    }


    @Test
    public void tesetDelete() {
        boardService.removeWithReplies(99L);
    }


}
