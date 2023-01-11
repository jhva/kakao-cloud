package com.example.springbootpractice.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Builder
@AllArgsConstructor
@Data
public class PageRequestDTO {

    //현재 페이지 번호
    private int page;

    //페이지 당 데이터 개수
    private int size;

    public PageRequestDTO() {
        this.page = 1;
        this.size = 10;
    }

    //목룍 요청을 위한 메서드
    public Pageable getPagealbe(Sort sort) {
        //page는 0개부터시작하니까 1개를 빼줌
        return PageRequest.of(page - 1, size, sort);
    }
}
