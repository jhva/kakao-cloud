### 데이터베이스 구조

- 회원테이블 과 게시글 테이블
- 게시글 테이블 과 댓글 테이블
- 회원테이블 과 댓글 테이블

### 게시판 프로젝트 생성

### 실행클래스 상단에 데이터베이스 감시옵션을 설정

- @EnableJpaAuditing

### domain 클래스 작업

- 공통으로 사용할 수정 날짜 와 날짜를 갖는 Entity
- @MappedSuperClass
    - 테이블 생성을 하지 않는 entity

### 회원 정보를 저장할 entity 를 생성

```java


@Entity
@Table(name = "tbl_member")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
//entity는 세터를 안넣는다
public class Member extends BaseEntity {

    @Id
    private String email;
    private String password;
    private String name;
}

```

### 관계 어노테이션의 세부 속성

- optional
    - 필수 여부를 설정하는 것으로 기본값은 true
    - 반드시 존재해야 하는 경우라면
        - false
    - 이 값이 true이면 데이터를 가져올 때 outer join 을 하게 되고 false 이면 inner join 을 수행
- inner join
    - 양쪽 테이블에 모두 존재하는 데이터만 join 에참여
- outer join
    - 한쪽 테이블에만 존재하는 데이터도 join 에참여
- mappedBy
    - 양방향 관계 설정 시 다른 테이블에 매핑되는 Entity 필드를 설정
- cascade
    - 상태 변화를 전파시키는 옵션
        - PERSIST
            - 부모 Entity 가 영속화 될 때 (저장) 자식 Entity도 같이 영속화
        - MERGE
            - 부모 Entity 가 병합될 때 자식 Entity도 같이 병합
        - REMOVE
            - 부모 Entity 가 삭제될 때 자식 Entity도 같이 삭제
        - REFRESH
            - 부모 Entity 가 refresh 될 때 자식 Entity도 같이 삭제
        - DETACH
            - 부모 Entity가 detach 될 때 자식 Entity도 같이 detach
                - 더 이상 Context의 관리 받지 않도록 하는것
        - All
            - 모든 상태를 전이
- opphanRemoval

> JoinColumn의 값이 NULL인 자식 객체를 삭제하는 옵션

### fetch

> 외래키로 설정된 데이터를 가져오는 시점에 대한 옵션으로 Eager 와 Lazy

### ManyToOne

> 외래키를 소유해야 하는 테이블을 위한 Entity에 설정하는데 @ManyToOne 이라고 컬럼 위에 작성

- 테이블의 방향을 반대로 설정할 수 있는데 @OneToMany
- @JoinColumn 을 이용해서 join하는 컬럼을 명시적으로 작성할 수 있는데 생략하면 참조변수이름_참조하는 테이블의 기본키컬럼이름
  으로 생성
- 외래키 값은 부모 entity 에서만 삽입 , 수정 ,삭제가 가능함
    - 자식 Entity 에서는 읽기만 가능 함

```java

//comment
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String rno;
    private String text;

    private String replyer;

    @ManyToOne
    private Board board;
}


//board 

public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bno;
    private String title;
    private String content;


    @ManyToOne
    private Member writer;
}
```

### Eager/Lazy Loading

> Entity 에 관계를 설정하면 데이터를 가져올 때 참조하는 데이터도 같이 가져온다

```java

public class test {
    @Test
    //게시글 1개를 자여노느 ㄴ메서드
    public void readBoard() {
        Optional<Board> result = boardRepository.findById(100L);

        //실제 쿼리는 조인을 하고있다 .
        //내가당장 작성자 정보를 가져오는게 아니라면 시간낭비가될수있다 .
        Board board = result.get();
        System.out.println(board);

    }
}
```

- join 이 이루어지는 과정

```
  select
        b1_0.bno,
        b1_0.content,
        b1_0.moddate,
        b1_0.regdate,
        b1_0.title,
        w1_0.email,
        w1_0.moddate,
        w1_0.name,
        w1_0.password,
        w1_0.regdate 
    from
        board b1_0 
    left join
        tbl_member w1_0 
            on w1_0.email=b1_0.writer_email 
    where
        b1_0.bno=?
```

### CommentTest

```java
public class Test {


    @Test
    //댓글
    public void readComment() {
        Optional<Comment> result = commentRepository.findById(100L);

        Comment comment = result.get();
        System.out.println(comment.getBoard());
    }
}
```

- 위에 방식을 test했을때 쿼리

```
  select
        c1_0.rno,
        b1_0.bno,
        b1_0.content,
        b1_0.moddate,
        b1_0.regdate,
        b1_0.title,
        w1_0.email,
        w1_0.moddate,
        w1_0.name,
        w1_0.password,
        w1_0.regdate,
        c1_0.moddate,
        c1_0.regdate,
        c1_0.replyer,
        c1_0.text 
    from
        comment c1_0 
    left join
        board b1_0 
            on b1_0.bno=c1_0.board_bno 
    left join
        tbl_member w1_0 
            on w1_0.email=b1_0.writer_email 
    where
        c1_0.rno=?
```

- 댓글을 가져오는데 필요없는자원이 너무많이 들어온다
- 그래서 필요할 때 가져오자 (Lazy)

### Lazy Loading

> 지연 로딩 (사용할 때 가져오는 것)

- 관계를 설정할 때 fecth=FetchType.LAZY 를 추가하면 지연로딩이 된다.
- 지연로딩을 사용할 때 주의할 사항
    - toString
        - 내부 모든 속성의 toString을 호출해서 그 결과를 가지고 하나의 문자열을 만들어낸다.
        - 지연로딩을 하게되면 참조하는 속성의 값이 처음에는 NULL 이다
        - 그래서 null.toString이 호출이되서 NullPointerException이 발생하게 된다
        - 이 문제를 해결하기 위해서는 지연로딩되는 속성은 toString에서 제외를 해야한다 .
            - @ToString(exclude = "제외할 속성이름")
- 지연로딩을 사용하게 되면 2개의 select 구문이 수행되어야 한다
    - 데이터를 찾아오고 그 후 데이터의 외래키를 이용해서 참조하는 테이블에서 데이터를 찾아오게된다
    - 하지만 JPA는 하나의 메서드에서 하나의 Context 연결만을 사용하는데 하나의 SQL구문이 끝나면 Context와의 연결이 해제된다 ( 2개의 SQL을 실행할수가없다)
        - 이런 경우에는 메서드에 @Translation을 붙여서 메서드의 수행이 종료될 때 까지 연결을 해제하지 말라고 해야한다.

### board 엔티티에 Lazy 를붙여주자

```java
public class Board {
    @ManyToOne(fetch = FetchType.LAZY)
    //처음에는 가져오지 않고 사용을 할 떄 가져온다 
    private Member writer;
}
```

- 이렇게했을경우 에러가 뜨고 select 구문하나만의 표출하고 join한 writer부분을 제외하고 가져오게된다
    - 이전에는 writer구문이 가져오게됬는데 이렇게함으로써 가져오고싶을때 사용할수있게되었다 .

```
에러구문 

could not initialize proxy [com.example.springboardproject.Member#user100namver] - no Session
org.hibernate.LazyInitializationException: could not initialize proxy [com.example.springboardproject.Member#user100namver] - no Session
```

- 이문제를 해결하기위한 방법
    - 위에말했던 null.toString발생
        - @ToString(exclude="writer") 넣어주자
            - 여기서 말하는 writer는 속성 기재한 writer이다
- 위에 문제를 해결하고 나면 get.writer() 하게되면 또 같은 에러구문 이 나는데
    - 이문제를 해결하기위해선 위에서 말했던 @Translation 붙이므로써 끝나지말라고 얘기를해줘야한
- 이렇게 되면서 select 구문이 두개가 날라가는데 항상 join보다는 서브쿼리가 속도가 빠르다
    - 정말 특별한경우가아니면 join보다는 서브쿼리를 사용해야한다.

### JOIN

> JOIN이라는 말 자체는 두개 이상의 테이블 (동일한 테이블2개도 가능)을 가로 방향으로 합치는 작업이다

- 종류
    - Cross Join(Cartesian Product)
        - 2개의 테이블의 모든 조합을 만들어내는 것
            - 외래키를 설정하지 않았을때 수행
    - INNER JOIN
        - EQUI JOIN
            - 양쪽 테이블에 동일한 의미를 갖는 컬럼의 값이 동일한 경우에만 적용
    - NON EQUI JOIN
        - 양쪽 테이블에 동일한 의미를 갖는 컬럼의 값이 동일한 경우를 제외한 방식(>,>=,<,<=,또는 between)으로 결합
    - OUTER JOIN
        - 한쪽 테이블에만 존재하는 데이터도 JOIN에 참여하는 방식
            - 방향에 따라서 LEFT OUTER JOIN , RIGHT OUTER JOIN , FULL OUTER JOIN
    - SELF JOIN
        - 동일한 테이블을 가지고 JOIN
            - 하나의 테이블에 동일한 의미를 갖는 컬럼이 2갱이상 존재할 때 수행한다
                - 예를들어서 어떤테이블에 회원 아이디와 친구아이디 가 같이 존재하는경우
                    - 내 친구의 친구를 조회하고자할때

### JPA JOIN QUERY

```
@QUERY("select ? from 엔티티이름 left outer join 엔티티안의참조속성 참초하는테이블의별칭")
```

- return 타입으로는 Object 타입이다.
- 결과를 Object 타입의 배열로 형 변환해서 사용해야한다
- join을 수행한 경우는 Arrays.toString 을 이용해서 내용을 출력해서 확인해보고 사용을 해야한다

### JOIN 테스트

> board데이터를 가져올 때 writer 의 데이터도 가져오기

```java
public interface test {
    //Board 데이터를 가져올 때 Writer 정보도 가져오는 메서드 
    @Query("select  from Board b left join b.writer w where b.bno=:bno")
    public Object getBoardWithWriter(@Param("bno") Long bno);
    //param 값이 콜론bno 와 같아야한다.
}
```

- 테스트 클래스에 메서드를 만들어서 확인

```java
public class Test {
    @Test
    public void joinQueryTest() {
        //번호가 이상하게들어가는경우가 있다 .
        Object result = boardRepository.getBoardWithWriter(100L);

        System.out.println(Arrays.toString((Object[]) result));

    }
}
```

### 관계가 설정되지 않은 경우에는 on 절을 이용해서 조인이 가능하다.

- 게시글을 가져올때 연관된 모든 댓글을 가져와보기
- 엔티티의 속성이없을 때 어떻게하냐 .

```java

public class test {
    //board 데이터를 가져올 때 Writer의 데이터도 가져오기
    //현재 지연로딩 일때 따로따로 가져오게되는데 이걸 한번에 가져오는 방법 join
    @Test
    public void joinQueryTest() {
        //번호가 이상하게들어가는경우가 있다 .
        Object result = boardRepository.getBoardWithWriter(100L);
        System.out.println(Arrays.toString((Object[]) result));
    }

    //===============================================================


    //글번호를 받아서 Board 와 그리고 Board 와 관련된 Comment 정보 찾아오기
    // Board 1개의 여러개의 Comment가 존재함 //return type: List
    //결과가 Board 와 Reply를 결합한 형태의 목록으로 리턴이되어서
    @Test
    public void testJoin2() {
        List<Object[]> result =
                boardRepository.getBoardWithComment(100L);

        for (Object[] ar : result) {
            System.out.println(Arrays.toString(ar));
        }
    }
}
```

### 게시글 목록을 가져올때 게시글 과 작성자 정보 그리고 댓글의 개수를 가져오기

```java
public interface test {
    //게시글 목록과 작성자 정보 그리고 댓글의 개수를 가져오는 메서드

    @Query("select b,w, count(r) from Board b left join b.writer w left join Comment r on r.board =b")
    Page<Object[]> getBoardWithReplyCount(Pageable pageable);
}
```

```java
public class test {
    @Test
    public void testJoin3() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

        Page<Object[]> result =
                boardRepository.getBoardWithReplyCount(pageable);

        result.get().forEach(row -> {
            Object[] ar = (Object[]) row;
//            System.out.println(Arrays.toString(ar));
        });
    }
}
```

### service 계층

- 데이터 전송에 사용하는 게시글의 DTO 를 생성

```java

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class boardDTO {

    private Long bno;
    private String title;
    private String content;
    private String writerName;
    private String writerEmail;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    private int replyCount;
}
```

### 게시글 관련 서비스 인터페이스

```java

public interface BoardService {

    //dto 를 엔티티로 변환해주는 메서드
    default Board dtoToEntity(BoardDTO dto) {
        Member member = Member.builder().email(dto.getWriterEmail()).build();

        Board board = Board.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();

        return board;
    }

    //Entity를 dto로 변환해주는 거
    default BoardDTO entityToDTO(Board board, Member member, Long CommentCount) {
        BoardDTO dto = BoardDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .regDate(board.getRegDate())
                .modDate(board.getModDate())
                .writerEmail(member.getEmail())
                .writerName(member.getName())
                .replyCount(CommentCount.intValue())
                .build();
        return dto;
    }
}

```

### 게시글 관련 서비스 메서드를 구현할 클래스 생성

```java

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
}

```

### 게시글 등록 요청을 생성

> 보드 서비스 인터페이스에 게시글 등록요청을 처리할 메서드선언

```java

```

### 게시글 목록 보기를 처리

> 게시글 목록 보기 요청을 저장할 DTO 클래스를 생성

```java
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
```

### 게시글 목록 결과를 출력하기 위한 DTO 클래스 생성

```java

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
```

### servie로직

```java

public class serviceImpl implements Service {
    @Override
    public PageResponseDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
        log.info(pageRequestDTO);
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
}
```

### 게시물 삭제

> 부모 엔티티에 있는 데이터를 삭제할 때는 엔티티의 데이터를 어떻게 할 것인지 고민

- 삭제를 할 것인지 아니면 삭제된 효과만 나타낼 것인지 (외래키의 값을 null로 하거나 삭제 여부를 나타내는 필드를 추가해서 필드의 값을 변경하는 등 )

```java
public interface IComment {
    @Modifying
    @Query("delete from Comment c where c.board.bno = :bno")
    void deleteByBno(@Param("bno") Long bnt);
}

```

### boardService 인터페이스에 글번호를 가지고 댓글을 삭제하는 메서드 선언

- 서비스에서 2가지가 연쇄적으로 삭제되기 때문에 트랜잭션을 적용해야한다

```java
public class boardServiceImpl implements BoadrdService {
    @Override
    @Transactional
    public void removeWithReplies(Long bno) {
        //댓글 삭제후
        commentRepository.deleteByBno(bno);

        //자기글 지우기
        boardRepository.deleteById(bno); //게시글 삭제
//        boardRepository.delete(bno);
    }
}
```

### 게시물 수정

- setter를 함부로 쓰지말자
- boardService 인터페이스에 게시글 수정을 위한 메서드를 생성

```java
public class boardService {
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
}
```

### controller 계층 
