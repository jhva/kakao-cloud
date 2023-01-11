package com.example.springbootpractice.repository;


import com.example.springbootpractice.domain.GuestBook;
import com.example.springbootpractice.domain.GuestBookDTO;
import com.example.springbootpractice.domain.PageRequestDTO;
import com.example.springbootpractice.domain.PageResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class GuestServiceImpl implements GuestBookService {


    private final GuestRepository guestRepository;

    public Long register(GuestBookDTO dto) {
        //파라미터가 제대로 넘어오는지 확인
        log.info("삽입 서비스 데이터" + dto.toString());

        //repository 변형

        GuestBook entity = dtoToEntity(dto);
        guestRepository.save(entity);
        return entity.getId();
    }

    public PageResponseDTO<GuestBookDTO, GuestBook> getList(PageRequestDTO dto) {
        //repository에게 넘겨줄 Pageable 객체를 생성
        //gno 내림차순으로 정렬
        Pageable pageable = dto.getPagealbe(Sort.by("id").descending());

        //데이터 찾아오기
        Page<GuestBook> result = guestRepository.findAll(pageable);
        List<GuestBookDTO> list = new ArrayList<>();

        for (GuestBook guestBook : result.getContent()) {
            list.add(entityToEntity(guestBook));
        }

        //데이터 목록을 받아서
        //데이터 목록을 순회하면서 제공된 메서드가 리턴하는
        //목록을 변경해주는 람다
        //function은 변환해주는거
        Function<GuestBook, GuestBookDTO> fn = (entity -> entityToEntity(entity));

        return new PageResponseDTO<>(result, fn);

    }
}
