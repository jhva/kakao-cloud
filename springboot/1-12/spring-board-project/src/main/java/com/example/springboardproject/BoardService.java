package com.example.springboardproject;


public interface BoardService {


    //게시글 상세보기
    BoardDTO get(Long bon);

    //게시글 목록보기
    PageResponseDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO);


    //게시글 등록
    Long register(BoardDTO boardDTO);


    //dto 를 엔티티로 변환해주는 메서드
    default Board dtoToEntity(BoardDTO dto) {
        Member member = Member.builder().email(dto.getWriterEmail()).build();

        Board board = Board.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();

        return board;
    }

    //Entity를 dto로 변환해주는 거
    default BoardDTO entityToDTO(Board board, Member member, Long CommentCount) {
        BoardDTO dto = BoardDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .regDate(board.getRegDate())
                .modDate(board.getModDate())
                .writerEmail(member.getEmail())
                .writerName(member.getName())
                .replyCount(CommentCount.intValue())
                .build();
        return dto;
    }


    //게시글 번호를 가지고 댓글을 삭제
    void removeWithReplies(Long bno);


    //게시물 수정
    Long modifyBoard(BoardDTO dto);
}
