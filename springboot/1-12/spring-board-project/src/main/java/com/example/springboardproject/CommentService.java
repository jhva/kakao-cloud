package com.example.springboardproject;

import java.util.List;

public interface CommentService {


    //댓글 등록
    Long register(CommentDTO commentDTO);

    //댓글 목록
    List<CommentDTO> getList(Long bno);


    //댓글 수정
    Long modify(CommentDTO commentDTO);


    //댓글 삭제
    Long remove(Long bno);


    //CommentDTO를 Comment Entity로 변환해주는 메서드
    default Comment dtoToEntity(CommentDTO commentDTO) {

        Board board = Board.builder()
                .bno(commentDTO.getBno())
                .build();
        Comment comment = Comment.builder().text(commentDTO.getText()).replyer(commentDTO.getReplyer())
                .board(board).build();


        return comment;
    }

    //Comment entity -> CommentDTO 변환
    default CommentDTO entityToDTO(Comment comment) {
        CommentDTO dto = CommentDTO.builder()
                .rno(comment.getRno())
                .text(comment.getText())
                .replyer(comment.getReplyer())
                .regDate(comment.getRegDate())
                .modDate(comment.getModDate()).build();
        return dto;
    }
}
