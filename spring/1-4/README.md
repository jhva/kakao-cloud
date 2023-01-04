### 7) bean 자동 생성

- 설정 파일에 `<context:component-scan base-package="패키지이름" />`을 추가하고, 패키지 안 클래스에 특정 어노테이션을 추가하면 bean을 자동 생성해준다.
- `@Component`(인스턴스 생성), `@Controller`(Servlet), `@Service`, `@Repository`, `@RestController` 등이 있다.
    - Spring Boot는 기본 패키지 이름을 설정하면 자동으로 `component-scan`이 추가된다.

### 수명주기 (Life Cycle)

> 어노테이션을 이용한 설정

- 메서드 위에 @PostConstruct
    - 생성자 다음에 호출
- 메서드 위에 @PreDestroy
    - 인스턴스가 소멸되기 직전에 호출

### InitializingBean 과 DisposableBean

> 인터페이스를 구현하면 유사한 역할을 수행하는 메서드를 사용하는 것이 가능

### 메서드를 생성하고 bean 태그의 init-method 속성 과 destory-method 속성에 메서드이름을 등록해도 유사한 효과

## Test

- 태스트 클래스 위에 @RunWith(SpringJUnit4ClassRunner.class) 를 추가하고 @ContextConfiguration(설정 파일 경로) 를 이용하면 설정 파일의 bean 을 이용해서
  테스트가 가능

## Spring Database 연동

> Java JDBC 코드 이용

- Spring JDBC 코드 이용
- SQL Mapper 이용
    - Mybatis
- ORM 이용
    - Hibernate

### Spring JDBC

- 스프링의 데이터베이스 지원
    - 템플릿 클래스를 이요해서 데이터를 접근
    - 의미있는 예외 클래스로 예외를 던짐
        - JDBC는 데이터베이스 예외는 모두 SQLException으로 던져서 구체적으로 예외내용을 파악하는것이어렵다
    - 트랜잭션 처리가 간단
    - 다른 데이터베이스 프레임워크와 연동을 지원함

### 데이터베이스 접속 테스트

- spring/test/java 디렉토리에 테스트를 위한 클래스를 생성하고 코드 작성

```java
public class DBTest {

    @Test
    public void testConnection() {
        try {
            //여기서 예외가 발생하면 이름을 틀렸거나 의존성 설정 잘못됨
            Class.forName("org.mariadb.jdbc.Driver");
            //연결
            Connection con = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/adam",
                    "root", "wnddkd");
            System.out.println(con);
            con.close();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
```

### DAO 패턴

> 데이터베이스 연관 로직은 별도의 클래스를 만들어서 작성

- POJO 형태로 작성하는 것을 권장
    - POJO 는 외부 프레임워크의 클래스를 상속하지 않는것.
    - 직접 인스턴스 생성을 하지 않고 IoC 와 DI 를 이용해서 사용하는 것을 권장
    - 예외를 외부에 던지는 것을 권장하지 않음

### DataSource

> 스프링에서는 데이터베이스를 사용할 때 데이터소스 사용을 강제함

- 데이터베이스 연결을 별도의 Bean을 이용해서 사용하도록한다

### Spring JDBC class

- Jdbctemplate
    - 기본 클래스
- NamedParameterjdbcTemplate
    - 파라미터를 설정할 때 인덱스 대신에 이름을 사용
- SimpleJdbcTemplate
    - 가변 인자 사용 가능
- SimpleJdbcInsert
    - 삽입에만 이용
- SimpleJdbcCall
    - 프로시저 호출

### Mybatis

> SQL Mapper Framework :Sql 을 자바 코드 와 분리시켜서 사용하는 Framework

- 장점
    - 사용하기 편리
    - 파라미터 매핑이나 select 구문의 결과 매핑이 자동
- 단점
    - 성능이 떨ㅇ러짐
- 의존성
    - 사용하고자 하는 관계형 데이터베이스 라이브러리
    - spring-jdbc
    - mybatis
    - mybastis-spring

### mybatis

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="good">
    <insert id="insertGood" parameterType="Good">insert into goods(code,
        name, manufacture, price, shelflife) values(#{code}, #{name}, #{manufacture},
        #{price}, #{shelflife})
    </insert>
</mapper>
```

### sql을 사용할 repository 클래스를 만들고 데이터 삽입 메서드를 작성

```java

@Repository
public class GoodRepository {

    @Autowired
    private SqlSeesion sqlSeesion;

    public int insertGood(Good good) {
        //상비을 제외한 모든 메서드는 리턴 타입은 정수이다 
        return sqlSeesion.insert("insertGood", good);
    }
}
```

### applicationContext.xml 파일에 xml을 이용해서 작성한 mybatis 사용을 위한 설정을 추가

### mybatis select

```xml

<insert id="getGood" resultType="domain.good" parameterType="int">
    select * from goods where code = #{code}
    <!--  resultType의 domain.good 은 해당 폴더에대한 -->
</insert>


        public good getCode(int code){
        return sqlSession.selectOne("getGood",code);
        <!--selectOne(id ,parameter) -->
        }
```

### interface를 이용한 query

```xml

<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
    <property name="dataSource" ref="dataSource"/>
</bean>
<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
<property name="basePackage"/>
</bean>
```

```java

@Repository
public interface GoodMapper() {
    @Select("select * from goods")
    public List<Good> allgood;
}
```


### 데이터베이스 작업 로그 출력
> log4jdbc-log4j2 라이브러리 이용 
- 로그를 출력하기 때문에 잘못된 SQL이나 잘못된 속성의 이름을 파악하는데든ㄴ 도움이 되지만 SQL 실행 속도는 느림


### 트랜잭션 처리 
> 스프링은 트랜잭션을 어노테이션을 이용해서 처리할 수있다 
- TransactionManager 클래스의 bean을 만들고 
- @Transaction만 추가하면 트랜잭션 처리가 되서 중간에 예외가 발생하면 rollback을 수행하고 예외가 발생하지않으면 commit을
```java


public void insert(){
    template.withTableName("goods");
    Map<String,Object> map = new HashMap<String,Object>();
    map.put("code",1);
    map.put("name","쌀");
    map.put("manufacture","경기도 이천")
        }
```



### Hibernate
> 개요 
- java 기반의 ORM
- hibernate가 만들어진 이후에 java에서도 orm 대한 표준 spec을 발표혀ㅐㅆ는지 이것이 JPA(Java Persistence API)
- JPA 는 인터페이스이고 Hibernate는 구현체 

```java

@Repository
public class HibernateRepository{
    
    @Autowired
  private SessionFactory sessionFactory;
    public void insertGood(Good good){
        Session session = sessionFactory.getCurrentSession();
        session.save(good);
    }
}
```


- 하이버네이트는 트랜잭션을 처리를 넣어줘야 에러가안난다.(JPA도 포함이다 )