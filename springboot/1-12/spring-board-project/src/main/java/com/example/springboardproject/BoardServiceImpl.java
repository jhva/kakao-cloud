package com.example.springboardproject;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor

public class BoardServiceImpl implements BoardService {


    private final BoardRepository boardRepository;

    private final CommentRepository commentRepository;


    @Override
    @Transactional
    public Long modifyBoard(BoardDTO dto) {
        //JPA에서는 삽입과 수정 메서드가 동일
        //upsert(있으면 수정 없으면 삽입) 를 하고자 하는 경우는 save를 호출하면 되지만
        //명시적으로 update를 하고자하면 데이터가 있는지 확인한 후
        if (dto.getBno() == null) {
            return 0L;
        }
        //데이터 존재 여부를 확인
        Optional<Board> board = boardRepository.findById(dto.getBno());
        //존재하는경우
        if (board.isPresent()) {
            board.get().changeTitle(dto.getTitle());
            board.get().changeContent(dto.getContent());
            boardRepository.save(board.get());
            return board.get().getBno();
        } else {
            return 0L;
        }

    }

    @Override
    @Transactional
    public void removeWithReplies(Long bno) {
        //댓글 삭제후
        commentRepository.deleteByBno(bno);


        //자기글 지우기
        boardRepository.deleteById(bno); //게시글 삭제
//        boardRepository.delete(bno);
    }

    public BoardDTO get(Long bon) {
        Object result = boardRepository.getBoardByBno(bon);
        Object[] arr = (Object[]) result;
        return entityToDTO((Board) arr[0], (Member) arr[1], (Long) arr[2]);
    }

    @Override
    public PageResponseDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
//        log.info(pageRequestDTO);
//Entity를 DTO로 변경하는 람다 인스턴스 생성
        Function<Object[], BoardDTO> fn =
                (en -> entityToDTO((Board) en[0], (Member) en[1], (Long) en[2]));

        //목록보기 요청처리
        Page<Object[]> result
                = boardRepository.getBoardWithReplyCount(
                pageRequestDTO.getPageable(Sort.by("bno").descending())
        );
        return new PageResponseDTO<>(result, fn);

    }

    @Override
    public Long register(BoardDTO boardDTO) {
//        log.info("boardDTO register method start" + boardDTO);
        //implements 하면 default 로 있기때문에 가능
        Board board = dtoToEntity(boardDTO);//리턴타입 board
        //board entity save해주기
        boardRepository.save(board);
//저장완료되었으니까 board에 아이디가져와서 리턴해주기
        return board.getBno();
    }


}
