package com.example.springboardproject;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.stream.Collectors;

public class SearchBoardRepositoryImpl extends QuerydslRepositorySupport implements SearchBoardRepository {
    // QuerydslRepositorySupport 클래스에 Default Constructor가 없기 때문에
    // Constructor를 직접 생성해서 필요한 Constructor를 호출해줘야 한다.
    // -> 검색에 사용할 Entity 클래스를 대입해줘야 한다.
    public SearchBoardRepositoryImpl() {
        super(Board.class);
    }

    @Override
    public Page<Object[]> searchPage(String type, String keyword, Pageable pageable) {
        QBoard board = QBoard.board;
        QMember member = QMember.member;
        QComment comment = QComment.comment;

        JPQLQuery<Board> jpqlQuery = from(board);

        jpqlQuery.leftJoin(member).on(board.writer.eq(member));
        jpqlQuery.leftJoin(comment).on(comment.board.eq(board));

        JPQLQuery<Tuple> tupleJPQLQuery = jpqlQuery.select(board, member, comment.count());
        //조건 생성
        BooleanBuilder bB = new BooleanBuilder();
        BooleanExpression expression = board.bno.gt(0L);
        bB.and(expression);

        //타입에 따른 조건 생성
        if (type != null) {
            String[] typearr = type.split("");

            BooleanBuilder conditionBuilder = new BooleanBuilder();
            for (String str : typearr) {
                switch (str) {
                    case "t":
                        conditionBuilder.or(board.title.contains(keyword));
                        break;
                    case "c":
                        conditionBuilder.or(board.content.contains(keyword));
                        break;
                    case "w":
                        conditionBuilder.or(member.email.contains(keyword));
                        break;
                }
            }
            bB.and(conditionBuilder);
        }
        tupleJPQLQuery.where(bB);

        //정렬 방법 설정
        tupleJPQLQuery.orderBy(board.bno.desc());

        //그룹화
        tupleJPQLQuery.groupBy(board);

        //page처리

        tupleJPQLQuery.offset(pageable.getOffset());
        tupleJPQLQuery.limit(pageable.getPageSize());

        List<Tuple> tupleResult = tupleJPQLQuery.fetch();
        return new PageImpl<Object[]>(
                tupleResult.stream().map(t -> t.toArray()).collect(Collectors.toList()), pageable,tupleJPQLQuery.fetchCount());


    }

    @Override
    public Board search1() {
        // JOQL을 동적으로 생성해서 실행
        QBoard board = QBoard.board;
        QComment comment = QComment.comment;
        QMember member = QMember.member;


        // 쿼리 작성
        JPQLQuery<Board> jpqlQuery = from(board);
//
        // bno가 1인 데이터 조회
//        jpqlQuery.select(board).where(board.bno.eq(1L));
        jpqlQuery.leftJoin(member).on(board.writer.eq(member));
        jpqlQuery.leftJoin(comment).on(comment.board.eq(board));
        //게시글 번호 별로 묶어서 board와 member의 email그리고 comment개수를 가져오기
//        jpqlQuery.select(board, member.email, comment.count()).groupBy(board);
//        List<Board> result = jpqlQuery.fetch();

        JPQLQuery<Tuple> tuple =
                jpqlQuery.select(board, member.email, comment.count());
        tuple.groupBy(board);
        List<Tuple> result = tuple.fetch();
        System.out.println(result);
        return null;
    }
};