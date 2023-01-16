package com.example.springboardproject;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommenServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public Long register(CommentDTO commentDTO) {
        Comment comment = dtoToEntity(commentDTO);
        commentRepository.save(comment);
        return comment.getRno();
    }

    @Override
    public List<CommentDTO> getList(Long bno) {
        List<Comment> list =
                commentRepository.findByBoardOrderByRno(Board.builder().bno(bno).build());
        //result 내용을 정렬하기 - 수정한 시간의 내림차순

        list.sort(new Comparator<Comment>() {
            @Override
            public int compare(Comment o1, Comment o2) {
                return o1.getModDate().compareTo(o2.getModDate());
            }
        });
//comment의 list를 commentDTO의 list로 변경

        return list.stream().map(comment -> entityToDTO(comment)).collect(Collectors.toList());

        //댓글목록
    }

    @Override
    public Long modify(CommentDTO commentDTO) {
        Comment comment = dtoToEntity(commentDTO);
        commentRepository.save(comment);
        return comment.getRno();
    }

    @Override
    public Long remove(Long bno) {
        commentRepository.deleteById(bno);
        return bno;
    }
}
