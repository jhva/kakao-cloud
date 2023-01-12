package com.example.springboardproject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


@AllArgsConstructor
@Builder
@Data
public class PageRequestDTO {

    //페이징 처리학 ㅣ위한 속성
    private int page;
    private int size;


    //검색 관련 속성
    private String type;
    private String keyword;


    //page와 size 값이 없을 때 사용할 기본값설정
    public PageRequestDTO() {
        this.page = 1;
        this.size = 10;
    }

    //page와 size를 가지고 pageable 객체를 생성해주는 메서드
    public Pageable getPageable(Sort sort) {
        return PageRequest.of(page, size, sort);
    }
}
