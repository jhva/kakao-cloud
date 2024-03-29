# JPA의 Query처리 (1-9 파일폴더로 진행중)

### JPARepository가 기본적으로 제공하는 메서드를 사용

- save, delete, findAll, findById)

### QueryMethod 이용 - 이름이 Query인 메서드

- 인터페이스에 메서드를 만들때 듀칙을 지켜주면 메서드의 구현을 자동으로 수행

### @Query를 이요해서 JPQL이나 SQL을 작성해서 실행

- 쿼리를 문자열로 작성을 하기 때문에 컴파일 시점에 에러를 확인할 수 없음

- 동적인 쿼리를 만드는 것이 어려움 - 상황에 따라서 변하는 쿼리

# query dsl

> JPQL을 코드로 작성할 수 있또록 도와주는 빌더 API

- 쿼리를 자바 코드로 작성

### 장점

- 조건에 맞게 동적으로 쿼리를 생성할 수 있음

- 쿼리를 재사용할수 있고 제약조건 조립 및 가독성을 향상

- 컴파일 시점에 오류룰 잡아낼수 잇음

- IDE의 자동완성 기능을 사용할수 있음

### 단점

- springboot 버전에 따라서 설정 방법이 다르다.
- 2.5<-> 2.6, 2.7<-> 3.0

### MapperdSuperclass

> 테이블로 생성되지 않는 Entity클래스

- 추상클래스와 유사한데 여러 Entity가 공통으로 가져야하는 속성을 정의하는 클래스

- 등록시간이나 수정 시간처럼 여러 Entity가 공통으로 갖는 속성을 정의

### JPA Auditing

> Entity 객체에 어떤변화가 생길 때 감지하는 리스너가 존재

- @EntityListners(value={클래스이름.class})을 추가하면 Entity에 변화가 생기면 클래스의 메서드가 동작
- 보통은 클래스를 직접 만들지 않고 Spring JPA가 제공하는 AuditingEntityListner.class를 설정

- 이 기능을 사용하기 위해서 SpringBootApplication 클래스의 상단에 @EnabldJpaAuditing을 추가해야한다.

### Entity와 DTO

> 두가지 모두 속성들을 합쳐서 하나로 묶기위해 만드는 클래스

- Repository에서는 Entity 객체를 이용하고 그 이외의 영역에서는 DTO를 사용하는 권장

- 사용자의 요청이나 응답과 Entity가 일치하지 않는 경우가 많고 Entity는 JPA가 관리하는 Context에 속하기 때문에 직접 관리하는 것은 일관성에 문제가 발생할 가능성이 있기 때문이다.

- DTO는 용도 별로 생성하는 것을 원칙으로 한다.

### Entity 작업

> 생성 날 짜와 수정날짜를 모든 Entity 가 소유하도록 하고자할때

- 하나의 상위 entity를 ㅁ낟르어서 상속을 하면 되는데 이 상위 Entity는 테이블로 만들어지면안된다
    - 이럴 땐 @MappedSupperClass를 추가한다
- 공통된 속성을 가진 Entity 생성
- 수정 날짜는 속성위에 @LastModifiedDate 를 추가하면 된다
    - 공통된 속성을 가진 Entity 생성

```java

@MappedSuperclass
//entity 변화감시 
@EntityListeners(value = {AuditingEntityListener.class})
@Getter
//setter안넣은이유가 내가 수정을할수없도록 하기위해서 
public class BaseEntity {
    @CreatedDate
    @Column(name = "regdate", updatable = false)
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name = "moddate")
    private LocalDateTime modDate;
}

```

### application 에서 사용할 Entity 생성

```java

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GuestBook extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(length = 1500, nullable = false)
    private String content;

    @Column(length = 50, nullable = false)
    private String writer;
}

```

### 게스트북에 대한 데이터베이스 작업을 위한 레포지토리 생성

### query dsl 사용을 위한 설정

    - 2.x 버전

```groovy
buildscript {
    ext {
        queryDslVersion = "5.0.0"
    }
}

//plugin
//..
id "com.ewerk.gradle.plugins.querydsl" version "1.0.10"


//dependencies
// QueryDSL
implementation "com.querydsl:querydsl-jpa:${queryDslVersion}" implementation "com.querydsl:querydsl-apt:${queryDslVersion}"


//
def querydslDir = "$buildDir/generated/querydsl"
querydsl {
    jpa = true
    querydslSourcesDir = querydslDir
}
sourceSets {
    main.java.srcDir querydslDir
}
configurations {
    compileOnly {
        extendsFrom annotationProcessor
    }
    querydsl.extendsFrom compileClasspath
}
compileQuerydsl {
    options.annotationProcessorPath = configurations.querydsl
}

```

- 3.x 버전

```groovy
implementation 'com.querydsl:querydsl-jpa:5.0.0:jakarta' annotationProcessor "com.querydsl:querydsl-
apt:
$ { dependencyManagement.importedProperties['querydsl.version'] } : jakarta " annotationProcessor " jakarta .
annotation:
jakarta.annotation - api " annotationProcessor " jakarta .
persistence:
jakarta.persistence - api "
```

### queryDsl 을 사용하기위해서 레포지토리 클래스변경

```java
public interface GuestRepository
        extends JpaRepository<GuestBook, Long>, QuerydslPredicateExecutor<GuestBook> {

}
```

### test 디렉토리에 repository테스트를 위한 클래스를 만들고 샘플 데이터 삽입

```java

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
}
```

### querydsl 테스트

- booleanBuilder 를 생성
- 구문은 Predicate 타입의 함수를 생성
- BooleanBuilder에 작성된 Predicate을 추가하고 실행

- title에 1이라는 글자가 포함된 entity 조회

```java

public class Test {
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
                qGuestBook.title.contains(keyword); //조건 
        builder.and(expression);

        Page<GuestBook> result =
                guestRepository.findAll(builder, pageable);

        for (GuestBook guestBook : result.getContent()) {
            System.out.println(guestBook);

        }
    }
}
```

- 2개의 조건을 결합해서 사용할때

```java
public class Test {
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
```

- 항상 Q클래스를 가져와서 사용하고 (querydsl 사용시)
- Booleanbuilder를 생성함
- 그리고 나서 조건을하나씩만든다

### service Layer

> service 와 controller 그리고 view 가 사용할 Guestbook 관련 DTO 클래스 생성

```java

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data

public class GuestBookDTO {
    private Long id;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
}
```

### service interface create

> Service 에서 가장 많이 하는 것 중 하나가 DTO 와 Entity 사이의 변환

- 인터페이스에 default method 로 추가해주는 것이 좋다

```java
public interface service {
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
}
```

### serviceImpl 클래스

- 데이터삽입을 위한 메서드를 선언

```java
public interface test {
    //데이터 삽입을 위한 메서드
    //매개변수는 대부분의 경우 dto
    //리턴 타입은 보통 삽입된 데이터를그대로 리턴하기도 하고
    //성공과 실패여부를 위해서 boolean을 리턴하기도하고
    //단순하게 영향받은 행의 개수를 의미하는 int
    //기본키의 값을 리턴하는 경우도 있다
    public Long register(GuestBookDTO dto);
}

//service class 
public class service {

    public Long register(GuestBookDTO dto) {
        //파라미터가 제대로 넘어오는지 확인
        log.info("삽입 서비스 데이터" + dto.toString());

        //repository 변형

        GuestBook entity = dtoToEntity(dto);
        guestRepository.save(entity);
        return entity.getId();
    }
}
```

### service Test하기

### 목록보기

- 게시판 형태에서 목록 보기 요청
    - 페이지 번호, 페이지 당 데이터 개수 , 검색 항목, 검색 값 - 이런 형태의 요청 DTO가 필요
- 게시판 형태에서 목록 보기 응답
    - Pageable 형태의 DTO 목록
        - 페이지 번호 리스트
        - 이전 페이지 여부
        - 다음 페이지 여부
        - 현재 페이지 번호
        - 검색 항목
        - 검색 값

### 목록요청을 위한 DTO 클래스 생성

```java

public class PageResponseDTO<DTO, EN> {

    //데이터 목록
    private List<DTO> dtoList;

    //Page를 함수를 적용해서 List로 변환해주는 메서드
    //Page 단위의 Entity를 받아서 DTO의 리스트로 변환
    //첫번째 매개변수가 Page단위의 Entity 이고
    //두번째는 데이터 변환을 위한 메서드
    public PageResponseDTO(Page<EN> result, Function<EN, DTO> fn) {

        //en과 dto ㅌ을 변환해주는
        //함수를 매개변수로 받아서
        // dto타입의 list로 변환해주는 것
        dtoList = result.stream().map(fn).collect(Collectors.toList());
    }
}

```

### 서비스 인터페이스에 목록보기를 위한 메서드선언

```java
public interface service {
    PageResponseDTO<GuestBookDTO, GuestBook> getList(PageRequestDTO dto);
}

//serviceImpl

public class implservice implements service {
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
```


### 페이지 번호 목록ㄷ을 출력할수잇도록 PageResponseDTO 출력 
```java

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
```

