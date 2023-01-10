# 1-9 springboot file continue

## Thymeleaf 이어서 1-9

- 링크생성
    - 기본 형식
        - <a href ="url">이미지나 텍스트
        - Thymeleaf 에서는 @ {} 로 href 설정
        - 파라미터 설정은 (파라미터이름=${데이터})

### 레이아웃 설정

> include 방식 외부에서 내용을 가져와서 삽입하는 방식

- th:insert :삽입
- th:replace :교체

- 화면ㄴ이동

```java
public class Controller {
    @GetMapping("/exlayout")
    public void exlayout() {
    }
}
```

## Spring boot 데이터베이스연동

### Test

- 테스트 코드 작성 이유
    - 개발 과정에서 문제를 미리 발견할 수 있음
    - 리팩토링 리스크가 줄어듬
    - 하나의 명세 문서가 됨
    - 불필요한 내용을 추가되는 것을 방지
- 테스트 종류
    - 단위 테스트 (Unit Test)
    - 통합 테스트 (Integration Tests)
    - 시스템 테스트 (System Tests)
- 코드 작성 방법 Given When Then 패턴
    - 단계를 설정해서 코드를 작성
    - 단계
        - GIVEN
            - 테스트를 하기 전에 필요한 환경을 설정 (변수 설정이나 Mock 객체) 생성
        - When
            - 테스트를 목적을 보여주는 단계로 테스트 코드를 작성하고 결과값을 가져오는 과정
        - Then
            - 결과 검증
- 좋은 테스트를 위한 5가지 속성
    - Fast
    - Elated(고립,독립)
        - 외부 요인으로 부터 독립적
    - Repeatable(반복)
    - Self Validating(자가 검증)
    - Timely(적시에)
        - 실제 구현되기 전에 테스트

### spring boot 에서 Test

- spring-boot-starter-test 의 의존성을 설정하면 됨
- JUnit5 와 Mock 이 포함됨

### JUnit5에서 어노테이션

- @Test: 테스트를 위한 메서드
- @BeforeAll : 테스트를 시작하기 전에 한 번 호출
- @BeforeEach : 모든 테스트 마다 시작하기 전에 호출
- @AfterAll : 테스트를 수행하고 난 후 한 번 호출
- @AfterEach : 모든 테스트 마다 수행하고 난 후 호출

### 가짜 객체

> 외부로 주입받아야 하는 경우 실제 객체를 만들어서 테스트를 해도 되지만 있다고 가정하고 테스트 가능

- @MockBean
    - 클래스 변수이름

### Spring boot database 연동 방법

- Java JDBC 코드 이용
- Framework 이용

# ORM(Object Relational Mapping)

### ORM

- 객체 지향 패러다임을 관계형 데이터베이스에 보존하는 기술
- 객체 와 관계형 데이터베이스의 테이블을 매핑해서 사용
- 관계형 데이터베이스에서 테이블을 설계하는 것 과 class 를 만드는 것을 템플릿을 만드는ㄴ점에서 유사

### 장점

- 특정 관계형 데이터베이스에 종속되지 안흥ㅁ
- 객체 지향 프로그래밍
- 생산성 향상

### 단점

- 쿼리 처리가 복잡함
- 성능 저하 위험
- 학습시간

### JPA (Java Persistence API)

- Java ORM 기술에 대한 표준 API
- JPA 는 인터페이스이고 구현체로는 Hibernate, EclipseLink ,OpenJpa

- Persistence Context
    - 애플리케이션 과 데이터베이스 사이의 중재자 역할
    - Entity <-> Persistence Context <-> DataBase
- 쓰기 지연을 수행
    - 트랜잭션 처리
        - commit 하기 직전까지는 데이터베이스에 반영하지 않음
            - Persisence 에 있는거다

### Entity 클래스를 만드는 방법

- VO 클래스의 상단에 @Entity

### JPA를 활용하는 애플리케이션 생성

- 프로젝트 생성
    - 의존성
        - Lombok,Spring Web , Spring Data JPA ,사용하고자 하는 데이터베이스

### jpa application.yml 설정

```yaml

spring:
  datasource:
    driver-class-name: org.mariadb.jdbc.Driver
    url: jdbc:mariadb://localhost:3306/jpapractice # jpaparactice 는 ㅇ데이터베이스
    username:
    password: 
```

## Entity Class 와 JpaRepository

### 개발에 필요한 코드

- Entity Class 와 Entity Object
- Entity 를 처리하는 Repository
    - 인터페이스로 설계
        - JPA에서 제공하는 인터페이스를 상속만 하면 Spring boot 내부에서
          나동으로 객체를 생성하고 실행하는 구조
        - 어느정도의 메서드는 이미 구현되어있고 메서드를 선언만 해도 구현해기도 함
        - 단순 CRUD 나 페이지 처리 등을 개발할 때 직접 코드를 작성하지 않아도 된다

### Entity 관련 어노테이션

- @Entity
    - Entity클래스 생성
- @Table
    - Entity 와 매핑할 테이블을 설정하는데 생략하면 클래스 이름 과 동일한 이름의 테이블 과 매핑되는데 mysql 이나 mariadb 는 첫글자를 제외하고 대문자가 있으면 _를 추가하고 소문자로 변경해서
      테이블 이름을 생성함

- uniqueConstraints
    - 여러 개의 컬럼이 합쳐져서 유일성을 갖는 경우 사용
    - uniqueConstraints={@UniqueConstraint(columnNames={컬럼이름 나열 })

- @Id :커럶 위에 기재해서 Primary Key를 설정 필수
- @GeneratedValue
    - 키 생성 전략을 기쟇나ㅡㄴ데 @Id 와 같이 사용
- @Column
    - 컬럼 위에 기재해서 테이블의 열 과 매핑
    - name : 컬럼 이름 설정 ,생략하면 속성 이름 과 동일한 컬럼으로 매핑됨
    - nullable
    - insertable , updateable
    - precision :숫자의 전체 자릿수
    - scale : 소수점 자릿수
    - length : 문자열 길이
- @Lob : BLOB 나 CLOB 타입
    - BLOB를 안쓰는이유가 직접 써줘야해서
    - lob 는 바이트 배열 , 보통은 파일의 내용을 저장할 목적으로 사용
    - 파일을 저장할 대는 대부분의 경우 파일의 경로를 문자열로 저장하고 파일의 내용은 파일 스토리지 (Amazon의 S3 가 대표적)에 별도로 둔다
- @Enumerated
    - enum 타입 (~~ 이러이러한 것들만 집어넣어야함)
    - check 제약조건
- @Transient
    - 테이블에서 제외 , 파생 속성
        - 다른 속성들을 가지고 만들어 내는 속성
    - @Temporal :날짜 타입 매핑
    - @CreatedDate : 생성날짜
    - @lastModifiedDate: 수정 날짜 자동 삽입
    - @Access : 사용자가 값을 사용할 때 바인딩 하는 방식
        - @Access(AccessType.FIELD)
            - 필드는 속성을 직접 사용하겠다는 얘기
        - @Access(AccessType.PROPERTY)
            - getter와 setter 사용
- 정훈 메모

```java

@Access(AccessType.PROPERTY)
public String getFullName(){
        return firstName+lastName;
        }
```

### 기본키 생성 전략

- AUTO
    - default 로 JPA 가 생성 전략을 선택
    - MariaDB는 AutoIncrement
- IDENTITY
    - AutoIncrement
- SEQUENCE
- TABLE
    - 직접 생성 전략을 생성

- 기본키로 사용할 속성들을 별도의 VO클래스로 만들고 Serializable 인터페이스를 구현하고 기본 생성자와 모든 속성을 매개변수로 갖는 생성자를 만들고 Getter만 추가해서 생성
- Entity 클래스 위 클래스의 속성을 추가하고 위에 @Id 와 @Embeddable 을 추가

### 데이터베이스 와 연동할 Entity 클래스

```java

@Entity
@Table(name = "tbl_memo")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Memo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200, nullable = false)
    private String memoText;
}
```

### application.yml 파일에 jpa설정 추가

```yml

jpa:
  hibernate:
    ddl-auto: update # 테이블이 업데이트되면 자동적으로 수정해달라는 얘기
  properties:
    hibernate:
      format_sql: true # format을 맞춰서 sql 을 보여달라
      show_sql: true # sql구문이 어떻게나가는 지 보여지게하는설정
# ? 에 값을 바인딩할 때 그 값을 확인 
logging:
  level:
    org.hibernate.type.descriptor.sql: trace
```

- ddl-auto
    - create
        - 시작될때 무조건 새로 생성해주는 부분
    - create-drop
        - 시작할때 생서앟고 종료할 때 삭제 된다
    - update
        - 변경되면 적용함
    - validate
        - 매핑만 확인 (연동되는 테이블이있는지)
    - none
        - 아무것도 시도를 안함

### Repository 의 기본 메서드 테스트

```java
//test method 
@SpringBootTest
public class MemoRespositoryTestt {

    @Autowired
    MemoRepository memoRepository;

    @Test
    public void testInsert() {
        //1~100 까지 insert 넣어줌 
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Memo memo = Memo.builder().memoText("sample .." + i)
                    .build();
            memoRepository.save(memo);
        });

    }
}
```

### 테이블 전체 데이터를 가져오는 메서드를 테스트

```java

@SpringBootTest
public class MemoRespositoryTestt {

    @Autowired
    MemoRepository memoRepository;

    @Test
    public void testAll() {
        List<Memo> list = memoRepository.findAll();
        for (Memo memo : list) {
            System.out.println(memo);
        }
    }

}
```

### 기본키를 가지고 하나의 데이터를 가져오는 메서드 테스트

```java

@SpringBootTest
public class MemoRespositoryTestt {

    @Autowired
    MemoRepository memoRepository;

    @Test
    public void getId() {
        //기본키를 가지고 조회하면 없거나 1개의 데이터를 리턴한다. 
        //optional 은 있을수도있고 없을수도있다 
        Optional<Memo> result = memoRepository.findById(100L);
        if (result.isPresent()) {
            System.out.println(result.get());
        } else {
            System.out.println("not found");
        }

    }
}
```

### 수정하는 메서드 테스트

```java

@SpringBootTest
public class MemoRespositoryTestt {

    @Autowired
    MemoRepository memoRepository;

    @Test
    public void updateTest() {
        Memo memo = Memo.builder()
                .memoText("데이터 수정 ..")
                .build();
        memoRepository.save(memo);

        //기본키의 값이 존재하면 수정이지만 
        //존재하지않은 경우에는 삽입이 발생하므로 데이터가 한 개 더 생긴다 
    }
}
```

### 삭제하는 메서드를 테스틑

```java

@SpringBootTest
public class MemoRespositoryTestt {

    @Autowired
    MemoRepository memoRepository;

    @Test
    public void 삭제메서드테스트() {
        memoRepository.deleteById(100L);// 기본키를 가지고 삭제
        memoRepository.delete(Memo.builder().id(99L).build());
        //entity를 이용해서 삭제
        //없는 데이터를 삭제하고 자하면 에러 
        //삭제를 할때는 존재여부를 확인해야한다.
        memoRepository.deleteById(1000L);
    }
}
```

### 페이징 & 정렬

> 관계형 데이터베이스에서 paging (페이지 단위로 데이터를 가져오는 것) MySql 이나 Mariadb는 limit를 이용

- JPA에서는 연결한 데이터베이스에 따라 SQL 을 자동으로 변환한다.
- 페이징 과 정렬은 findAll 메서드를 이용
    - Pageable 이라는 인터페이스의 객체를 대입하면 Paging 처리를 해서 Page<T>타입으로 리턴
- Pageable 인터페이스 생성
    - PageRequest.of(int page, int size)
        - 0부터 시작하는 페이지 번호 와 페이지 당 데이터 개수를 설정
    - PageRequest.of(int page, int size, Sort.Direction , String ...props)
        - 정렬 방향 과 정렬 속성을 추가
    - PageRequest.of(int page, Sort sort)
        - 정렬 관련 정보를 Sort객체를 생성해서 대입

### 페이징 조회

```java

@SpringBootTest
public class MemoRespositoryTestt {

    @Autowired
    MemoRepository memoRepository;

    @Test
    public void testPaging() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Memo> memo = memoRepository.findAll(pageable);
        //1~10번 까지의 데이터가 온다 
        System.out.println(memo + "result");

        System.out.println(memo.getTotalPages() + "get total page");
// getTotalPage :10


        //조회된 데이터 순회
        for (Memo memo1 : memo.getContent()) {
            System.out.println(memo1);
            //10개씩 , 10페이지 

        }
    }
}
```

### 정렬을 수행해서 페이징

```java

@SpringBootTest
public class MemoRespositoryTestt {

    @Autowired
    MemoRepository memoRepository;

    @Test
    public void testSort() {

        //id의 내림차순
        Sort sort = Sort.by("id").descending();

        Pageable pageable = PageRequest.of(0, 10, sort);
        //페이지번호 ,개수, 정렬

        Page<Memo> result = memoRepository.findAll(pageable);
        for (Memo memo : result.getContent()) {
            //id를 기준으로 내림차순 
            System.out.println(memo);
        }
    }
}

```

### 정렬 조건을 두개로 결합해서 페이징

```java
public class test {
    @Test
    public void testSortConcate() {
        Sort sort = Sort.by("id").descending();
        Sort sort2 = Sort.by("mnoText").descending();
        //2개의 결합 - sort1의 값이 같으면 sort2로 정렬
        Sort sortall = sort.and(sort2);
        Pageable pageable = PageRequest.of(0, 10, sortall);

        Page<Memo> result = memoRepository.findAll(pageable);
        for (Memo memo : result.getContent()) {
            System.out.println(memo);
        }
    }
}
```

### query methods

- find + (Entity 이름) + by + 컬럼이름
- itemEntity에서 name을 가지고 조회하는 메서드 : findByName(String name)
- or 나 And 사용 가능
    - 매개변수는 자신이 설정하는 select 형태에 따라서 설정을 하고 pageable 과 sort를 추가할 수있다
- Pageable이 없으면 리턴 타입은 list이고 존재하면 Page이다

```java

public interface interfaceClass extends JpaRepository<Memo, Long> {

    Page<Memo> findByIdBetweenOrderByIdDesc(Long from, Long to, Pageable pageable);
    //페이징으로 리턴
}

public class Test {
    @Test
    public void queryMethod1() {
//        List<Memo> list = memoRepository.findByIdBetweenOrderByIdDesc(30L,40L);
//
//        for(Memo memo : list){
//            System.out.println(memo);
//        }

        //페이징을 하고싶을땐 page를 ㄹ레포지토리에서 리턴값정해주고 매개변수에 넣어줘야함 
        Pageable pageable = PageRequest.of(1, 5);
        Page<Memo> result = memoRepository.findByIdBetweenOrderByIdDesc(0L, 50L, pageable);
        //0~50번중에서

        for (Memo memo : result.getContent()) {
            System.out.println(memo);
        }
    }
}
```

### 정해진 Id기준으로 그 이하 데이터들은 지워지는 메서드

```java

public class Test {
    @Test
    //특정한 작업에서는 트랜잭션을 적용하지않으면 예외가 발생
    @Transactional
    //트랜잭션이 적용되면 자동 COmmit 되지 않으므로 Commit을 호출해야
    //실제 작업이 완성된다.
    @Commit
    public void deleteMehotdTest() {
        //10번이하는 다 삭제된다 
        memoRepository.deleteByIdLessThen(10L);
    }
}
```

### SQL 실행 => @Query

- JPQL 이라고 해서 JPA 에서 제공하는 쿼리 문법을 이용할 수 있고 Native SQL (데이터베이스에서 실제 사용하는 SQL)을 이용하는 것이 가능합니다.

- select 가 아닌 경우는 @Modifying 과 같이 사용해야한다.
- JPQL은 SQL 과 거의 유사하기 때문에 별도의 학습이 필요없이 사용이 가능
- MemoRepository 인터페이스에 수정하는 메서드를 선언

```java
public interface testinterface {

    @Transactional
    @Modifying
    //Native sql 이 아니기때문에 Table 이름을 적는 것이 아니고 
    //Entity 클래스의 이름을 사용해야한다 .
    @Query("update Memo m set m.memoText = :memoText where m.id = :id")
    public void updateQueryMemoText(@Param("id") int id, @Param("memoText") String memoText);

    //객체를 가져와서사용할때 
    @Query("update Memo m set m.memoText = :#{#param.memoText} where m.id = #{#param.id}")
    public void updateQueryMemoText(@Param("param") Memo memo);
}
```

### 페이징 처리 JPQL

```java

public interface testInterface {
    @Query("select m from Memo where m.id > :id")
    Page<Memo> getListWithQuery(@Param("id") Long id, Pageable pageable);

}

public class Test {
    @Test
    public void testSelectQuerty() {
        //0번 페이지 10개의 데이터를 가져오고 내리참순으로 정렬해서

        //pageable객체
        Pageable pageable = PageRequest.of(0, 10, Sort.by("id").descending());

        Page<Memo> result = memoRepository.getListWithQuery(50L, pageable);

        for (Memo memo : result.getContent()) {
            System.out.println(memo);
        }
    }
}
```

### Object [] 리턴하는 메서드

- select 구문을 보낼 때 제공되는 기본 메서드나 Query 메서드를 이용하면 Entity 타입으로 리턴원하는 데이터만 추출할 수 없다
- JOIN 이나 GROUP BY 같은 SQL을 실행하면 이에 맞는 Entity 가 존재하지 않을 가능성이 높다
    - 이런 경우 원하는 내용만 추출하고자 할때 Object [] 을 리턴하는 메서드를 사용할 수 있다.

- id 와 memoText 와 그리고 현재시간(CURRENT_DATE) 를 조회하고자 하는 경우에 적당한 Entity 가 존재하지 않는다.(이때 Object [] 로 받는ㄷ )

```java
public interface testInterface {
    @Query("select m.id, m.memoText, CURRENT_DATE from Memo m where m.id > :id")
    Page<Object[]> getObjectWithQuery(@Param("id") Long id, Pageable pageable);
}

public class Test {
    @Test
    public void tesetObj() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("id").descending());
        Page<Object[]> result = memoRepository.getObjectWithQuery(50L, pageable);
        for (Object[] memo : result.getContent()) {
            System.out.println(Arrays.toString(memo));
        }
    }
}
```

### NativeSQL 실행

```java
@Query(value = "sql작성" , nativeQuery=true)
```

### 도커 이미지 검색 
- docker search 이름 