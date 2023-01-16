package com.example.springboardproject;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PageResponseDTO<DTO, EN> {
    //데이터목록을 저장할 리스트
    private List<DTO> dtoList;
    //페이지 번호 관련 속성

    private int totalPage; //전체 페이지 개수

    private int page; //현재 페이지 번호
    private int size; // 페이지 당 데이터 출력 개수

    private int start, end; //페이지 시작 번호 와 끝 번호

    private boolean prev, next;//이전 과 다음 출력여부

    private List<Integer> pageList; //페이지 번호목록


    //검색결과 (Page<Board>

    public PageResponseDTO(Page<EN> result, Function<EN, DTO> fn) {
        //검색결과 목록을 DTO의 LIST로 변환
        dtoList = result.stream().map(fn).collect(Collectors.toList());
        //전체 페이지 개수 구하기
        totalPage = result.getTotalPages();

        //페이지 번호 목록 관련 속성을 결정하는 메서드
        makePageList(result.getPageable());

    }

    private void makePageList(Pageable pageable) {
        //현재 페이지 번호
        this.page = pageable.getPageNumber() + 1;

        //데이터 개수

        this.size = pageable.getPageSize();

        //임시로 마지막 페이지 번호계산

        int tempEnd = (int) (Math.ceil(page / 10.0)) * 10;

        //시작하는 페이지번호
        start = tempEnd - 9;

//이전여부
        prev = start > 1;
        //마지막페이지 번호수정

        end = totalPage > tempEnd ? tempEnd : totalPage;

        next = totalPage > end;

        //페이지 번호 목록만들기
        pageList = IntStream.rangeClosed(start, end)
                .boxed().collect(Collectors.toList());
    }
//
//    @SpringBootTest
//    public static class RepositoryTest {
//
//
//        @Autowired
//        private MemberRepository memberRepository;
//
//        @Autowired
//        private BoardRepository boardRepository;
//
//
//        @Autowired
//        private CommentRepository commentRepository;
//
//
//        //댓글을 ㅏㄱ져옥는 메서드 확인
//        @Test
//        public void testList() {
//            List<Comment> commentList =
//                    commentRepository.findByBoardOrderByRno(Board.builder().bno(27L).build());
//
//
//            commentList.forEach(list -> System.out.println(list));
//        }
//
//        @Test
//        public void testSearch1() {
//            boardRepository.search1();
//        }
//
//
//        @Test
//        //회원 데이터 삽입
//        public void insertMember() {
//
//            for (int i = 1; i <= 100; i++) {
//                Member member = Member.builder()
//                        .email("user" + i + "namver")
//                        .password("1111")
//                        .name("user" + i)
//                        .build();
//                memberRepository.save(member);
//            }
//        }
//
//        @Test
//        public void testSearch2() {
//            Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending()
//                    .and(Sort.by("title")).ascending());
//
//
//            Page<Object[]> rsult =
//                    boardRepository.searchPage("tc", "1", pageable);
//
//            for (Object[] row : rsult.getContent()) {
//                System.out.println(Arrays.toString(row));
//            }
//        }
//
//
//        @Test
//        //보드데이터 삽입
//        public void inserBoard() {
//
//            for (int i = 1; i <= 100; i++) {
//                Member member = Member.builder()
//                        .email("user" + i + "namver")
//                        .build();
//
//                Board board = Board.builder()
//                        .title("제목" + i)
//                        .content("내용" + i)
//                        .writer(member)
//                        .build();
//
//                boardRepository.save(board);
//            }
//        }
//
//        @Test
//        public void insertComment() {
//
//            for (int i = 1; i <= 300; i++) {
//                long bno = (long) (Math.random() * 100) + 1;
//
//                Board board = Board.builder()
//                        .bno(bno).build();
//
//
//                Comment comment = Comment.builder()
//                        .text("댓글" + i)
//                        .board(board)
//                        .replyer("guest")
//                        .build();
//
//
//                commentRepository.save(comment);
//
//            }
//        }
//
//        @Test
//        @Transactional
//        //게시글 1개를 자여노느 ㄴ메서드
//        public void readBoard() {
//            Optional<Board> result = boardRepository.findById(100L);
//
//            //실제 쿼리는 조인을 하고있다 .
//            //내가당장 작성자 정보를 가져오는게 아니라면 시간낭비가될수있다 .
//            Board board = result.get();
//            System.out.println(board);
//            System.out.println(board.getWriter());
//        }
//
//        @Test
//        //댓글
//        public void readComment() {
//            Optional<Comment> result = commentRepository.findById(100L);
//
//            Comment comment = result.get();
//            System.out.println(comment.getBoard());
//        }
//
//
//        //board 데이터를 가져올 때 Writer의 데이터도 가져오기
//        //현재 지연로딩 일때 따로따로 가져오게되는데 이걸 한번에 가져오는 방법 join
//        @Test
//        public void joinQueryTest() {
//            //번호가 이상하게들어가는경우가 있다 .
//            Object result = boardRepository.getBoardWithWriter(100L);
//            System.out.println(Arrays.toString((Object[]) result));
//        }
//
//        //글번호를 받아서 Board 와 그리고 Board 와 관련된 Comment 정보 찾아오기
//        // Board 1개의 여러개의 Comment가 존재함 //return type: List
//        //결과가 Board 와 Reply를 결합한 형태의 목록으로 리턴이되어서
//
//
//        @Test
//        public void testJoin2() {
//            List<Object[]> result =
//                    boardRepository.getBoardWithComment(100L);
//
//            for (Object[] ar : result) {
//                System.out.println(Arrays.toString(ar));
//            }
//        }
//
//
//        @Test
//        public void testJoin3() {
//            Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
//
//            Page<Object[]> result =
//                    boardRepository.getBoardWithReplyCount(pageable);
//
//            result.get().forEach(row -> {
//                Object[] ar = (Object[]) row;
//                System.out.println(Arrays.toString(ar));
//            });
//        }

}

