package com.example.springboardproject;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
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

}
