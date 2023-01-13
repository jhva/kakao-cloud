# 1-12 이어서

### JPA 에서 쿼리를 실행하는 방법

> JPARepository 가 제공하는 기본 메서드 사용

- 테이블 전체 조회
- 테이블에서 기본키를 가지고 하나의 데이터 조회
- 삽입
- 기본키를 이용해서 조건을 만들어 수정
- 기본키를 이용해서 삭제
- Entity를 이용한 삭제

### Query Method

> Repository 인터페이스에 하나의 테이블에 대한 검색 및 삭제 메서드를 이름으로 생성하는 것

### @Query 를 이용한 JPQL 이나 NativeSQL을 작성해서 사용

### Querydsl

> JPQL 을 문자열로 작성하지 않고 Java 코드로 작성

- 문자열을 사용하지 않으므로 컴파일 할 때 오류를 확인하는 것이 가능
- 자바코드를 이용하기 때문에 조건문을 이용할 수 있어서 동적 쿼리를 생성하는 것이 편리
- 동적 쿼리는 상황에 따라 컬럼이나 테이블이름이 변경되는 쿼리

```java
  @Override
    public Board search1() {
        // JOQL을 동적으로 생성해서 실행
        QBoard board = QBoard.board;
        QComment comment = QComment.comment;
        QMember member = QMember.member;


        // 쿼리 작성
        JPQLQuery<Board> jpqlQuery = from(board);

        // bno가 1인 데이터 조회
//        jpqlQuery.select(board).where(board.bno.eq(1L));
        jpqlQuery.leftJoin(member).on(board.writer.eq(member));
        jpqlQuery.leftJoin(comment).on(comment.board.eq(board));
        //게시글 번호 별로 묶어서 board와 member의 email그리고 comment개수를 가져오기
        jpqlQuery.select(board, member.email, comment.count()).groupBy(board);
        List<Board> result = jpqlQuery.fetch();
        System.out.println(result);
        return null;
    }
```

- 실행 한 후 결과를 확인해보면 List타입의 list
  안쪽의 List 의 첫번째 요소가 board에 해당하는 Board객체이고 두번째는 member.email에 해당하는 문자열이고 세번째는 reply.count()에 해당하는 데이터이다

### tuple

> 관계형 데이터베이스에서 하나의 행(row)를 Tuple이라고한다

- 특정 언어들에서는 Tuple을 자료형을 제공하는데 여러개의 데이터를 묶어서 하나의 데이터를 표현하기 위한 자료형이다
- 가장 유사한 형태가 Struct(구조체)이다.
- 자바에는 Tuple이라는 자료형이 제공되지 않지만 Spring에서 제공을 합니다.

```java

 JPQLQuery<Tuple> tuple =
                jpqlQuery.select(board, member.email, comment.count());
 tuple.groupBy(board);
 List<Tuple> result = tuple.fetch();
```

### 검색구현

- 조건
  - 제목 검색 :t
  - 작성자 검색: w
  - 내용: c
  - 제목+내용 : tc
  - 제목+작성자 : tw

```java
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
```

- test

```java
   @Test
    public void testSearch2() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending()
                .and(Sort.by("title")).ascending());


        Page<Object[]> rsult =
                boardRepository.searchPage("tc", "씨발아", pageable);

        for(Object[] row : rsult.getContent()){
            System.out.println(Arrays.toString(row));
        }
    }
```

- PageRequestDTO type과 키워드 가 있는지
