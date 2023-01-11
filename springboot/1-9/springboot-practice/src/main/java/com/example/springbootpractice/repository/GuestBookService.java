package com.example.springbootpractice.repository;

import com.example.springbootpractice.domain.GuestBook;
import com.example.springbootpractice.domain.GuestBookDTO;
import com.example.springbootpractice.domain.PageRequestDTO;
import com.example.springbootpractice.domain.PageResponseDTO;

public interface GuestBookService {

    //데이터 삽입을 위한 메서드
    //매개변수는 대부분의 경우 dto
    //리턴 타입은 보통 삽입된 데이터를그대로 리턴하기도 하고
    //성공과 실패여부를 위해서 boolean을 리턴하기도하고
    //단순하게 영향받은 행의 개수를 의미하는 int
    //기본키의 값을 리턴하는 경우도 있다
    public Long register (GuestBookDTO dto);

    //dto를 entity로
    default GuestBook dtoToEntity(GuestBookDTO dto) {
        GuestBook entity = GuestBook.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();
        return entity;
    }

    //entity를 dto 로
    //이때는 전부 옮겨줘야한다
    default GuestBookDTO entityToEntity(GuestBook entiy) {
        GuestBookDTO dto = GuestBookDTO.builder()
                .id(entiy.getId())
                .title(entiy.getTitle())
                .content(entiy.getContent())
                .writer(entiy.getWriter())
                .regDate(entiy.getRegDate())
                .modDate(entiy.getModDate())
                .build();
        return dto;
    }

    //목록보기를 위한 메서드
    PageResponseDTO<GuestBookDTO, GuestBook> getList(PageRequestDTO dto);

}
