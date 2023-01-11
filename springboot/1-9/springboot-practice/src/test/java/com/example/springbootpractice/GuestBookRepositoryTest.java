package com.example.springbootpractice;


import com.example.springbootpractice.domain.GuestBook;
import com.example.springbootpractice.domain.QGuestBook;
import com.example.springbootpractice.repository.GuestRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.stream.IntStream;

@SpringBootTest
public class GuestBookRepositoryTest {
    @Autowired
    private GuestRepository guestRepository;

    @Test
    public void insertDummies() {
        //test 삽입 메서드

        IntStream.rangeClosed(1, 300).forEach(i -> {
            GuestBook book = GuestBook.builder()
                    .title("제목 .." + i)
                    .content("내용 .." + i)
                    .writer("user" + i % 10)
                    .build();

            System.out.println(guestRepository.save(book));
        });
    }

    @Test
    public void updateDummies() {
        GuestBook book = GuestBook.builder()
                .id(1L)
                .title("제목변경")
                .content("내용 변경")
                .writer("군계")
                .build();
        System.out.println(guestRepository.save(book));
    }

    @Test
    //title에 1일라는 글자가 포함된 entity조회
    public void testQuery1() {
        //10개씩 첫번째 페이지의 데이터를 조회
        //modDate의 내림차순 정렬
        Pageable pageable = PageRequest.of(0, 10, Sort.by("modDate").descending());
//쿼리 수행
        QGuestBook qGuestBook = QGuestBook.guestBook;

        //타이틀에 1ㅇ이포함된 조건을 생성
        String keyword = "1";
        BooleanBuilder builder = new BooleanBuilder();

        BooleanExpression expression =
                qGuestBook.title.contains(keyword);
        builder.and(expression);

        Page<GuestBook> result =
                guestRepository.findAll(builder, pageable);

        for (GuestBook guestBook : result.getContent()) {
            System.out.println(guestBook);

        }
    }

    @Test
    //title에 1일라는 글자가 포함된 entity조회
    public void testQuery2() {
        //10개씩 첫번째 페이지의 데이터를 조회
        //modDate의 내림차순 정렬
        Pageable pageable = PageRequest.of(0, 10, Sort.by("modDate").descending());
//쿼리dsl 수행을 위해서 Q클래스 가져오기
        QGuestBook qGuestBook = QGuestBook.guestBook;

        //타이틀에 1ㅇ이포함된 조건을 생성
        String keyword = "1";
        BooleanBuilder builder = new BooleanBuilder();


        //title이 포함된 조건
        BooleanExpression expression = qGuestBook.title.contains(keyword);
        //content에 포함된 조건
        BooleanExpression expressionContent = qGuestBook.content.contains(keyword);

//2개의 조건을 or로 결합
        BooleanExpression exAll = expression.or(expressionContent);
        builder.and(exAll);
        //100보다 작은것만 골라서 가져오기
        builder.and(qGuestBook.id.lt(100L));
        Page<GuestBook> result =
                guestRepository.findAll(builder, pageable);

        for (GuestBook guestBook : result.getContent()) {
            System.out.println(guestBook);

        }
    }
}
