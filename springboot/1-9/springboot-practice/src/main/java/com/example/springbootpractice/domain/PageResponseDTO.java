package com.example.springbootpractice.domain;


import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
//dto는 결과를 출력할 때 데이터 클래스
public class PageResponseDTO<DTO, EN> {

    //데이터 목록
    private List<DTO> dtoList;


    //전체 페이지개수
    private int totalCount;

    //현재 페이지 번호
    private int page;

    //하나의 페이지에 출력할 데이터 개수
    private int size;

    // 페이지번호에서 시작하는 페이지 번호 끝나는 페이지 번호
    private int start, end;
    //이전과 다음 여부
    private boolean NextPage, PrevPage;

    //페이지 번호 목록
    private List<Integer> pageList;

    //paging 결과를 가지고 추가한 항목들을 계산해주는 메서드
    private void makePageList(Pageable pageable) {
        //현재페이지번호
        //jpa 는 페이지 번호가 0부터 시작하므로 +_1
        this.page = pageable.getPageNumber() + 1;

        //페이지 당 데이터 개수
        this.size = pageable.getPageSize();

        //임시로 마지막 페이지 번호를 생성
        //페이지 번호를 10개를 출력
        //10으로 나누어서 소수가있으면 올림을 하고 곱하기 10
        int tempend = (int) (Math.ceil(page / 10.0)) * 10;

        start = tempend - 9;
        //이전 여부
        PrevPage = start > 1;

        //마지막 페이지 번호 계산
        end = totalCount > tempend ? tempend : totalCount;

        //다음ㅇ 여부
        NextPage = totalCount > tempend;

        pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
    }

    //Page를 함수를 적용해서 List로 변환해주는 메서드
    //Page 단위의 Entity를 받아서 DTO의 리스트로 변환
    //첫번째 매개변수가 Page단위의 Entity 이고
    //두번째는 데이터 변환을 위한 메서드
    public PageResponseDTO(Page<EN> result, Function<EN, DTO> fn) {

        //en과 dto ㅌ을 변환해주는
        //함수를 매개변수로 받아서
        // dto타입의 list로 변환해주는 것
        dtoList = result.stream().map(fn).collect(Collectors.toList());
        //전체 페이지 개수
        totalCount = result.getTotalPages();
        //페이지 목록 메서드 호출
        makePageList(result.getPageable());
    }
}
