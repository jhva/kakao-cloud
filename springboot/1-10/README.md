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