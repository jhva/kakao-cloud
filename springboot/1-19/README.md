# AOP(Aspect Oriented Programming)관점 지향 프로그래밍)

- 객체지향 프로그래밍을 보완하는 개념으로 메서드나 클래스를 관점에 따라 분리시켜서 구현하는 프로그래밍 방법
- MVC Pattern의 경우에는 데이터를 다루는 Repository 그리고 Service, View, Controller로 구분

### Repository와 Service를 Model로 설정

- Service부분을 살펴보면 업무로직을 처리하기 위한부분과 업무로직을 처리하기 위해서 필요한 부분 또는 업무와는 상관없지만 필요한 코드를 같이 작성하게 된다

- 실제 업무에 관련된 로직을 business Logic이라고 하고 다른 내용은 Cross Cutting Concern(공통 관심 사항)이라고 하고 이를 분리해서 프로그래밍 하는 것이 AOP이다.
- 위와 같이 프로그래밍을 하되 Java Web Programming에서는 직접 인스턴스를 생성하거나 메서드를 호출하지 않고 코드를 작성한 후 설정을 하면 컴파일할때 또는 빌드 할 때 또는 실행할 때 코드를 조합하는
  형태로 수행한다.

# AOP 구현 방법

### Filter

- Spring 과는 상관이 없어서 Filter 를 사용하면 Spring 의 Bean 조작이 안됨
- 인코딩 이나 XSS 방어에 주로 이용

### Spring Interceptor

- URL 요청 및 응답 시점을 가로채서 전/후 처리를 수행
- Controller 가 처리하기 전이나 후의 작업을 작성

### Spring AOP

- 메서드 호출 전 후 처리를 수행
- 비지니스 로직에서 쓰임

### HandlerInterceptor

> Interceptor는 낚아채다의 의미를 가지고있다. Client에서 Server로 들어온 Request 객체를 Controller의 Handler로 도달하기 전 가로채어, 원하는 추가 작업이나 로직을 수행 한
> 후 Handler로 보낼 수 있도록 해주는 Module이다.

- 메서드
    - preHandle
        - Controller가 처리하기 전에 호출되는 메서드
        - 여기서 true 를 리턴하면 컨트롤로 이동하고 false 를 리턴하면 Controller 로 이동하지 않음
    - postHandler
        - Controller가 처리한 후에 호출되는 메서드
            - 예외가발생하면 호출되지 않는다
    - afterCompletion
        - 컨트롤러가 처리한 후에 무조건 호출되는 메서드

- 설정
    - WebMvcConfigure
        - 인터페이스를 구현한 클래스의 addInterceptor를 재정의

### aop 구현해보기

```java 
@Log4j2
public class MeasuringInterceptor implements HandlerInterceptor {
    @Override
    //컨트롤러에게 요청을 하기전에 호출되는 메서드
    //리턴이 false면 컨트롤러에게 요청을 전달하지않음
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        //request는 세션을 찾아올수있다

        log.warn("컨트롤러가 요청을 처리하기 전에 호출 ");
        return true;
    }


    @Override
    //컨트롤러가 요청을 처리한 후
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        //로그 기록
        log.warn("요청을 정상적으로 처리한 후 호출");
    }

    @Override
    //Controller가 요청을 처리한 후 무조건 호출되는 메서드
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
```

### AOP

> aop 필요성

- 웹 애플리케이션 개발을 할 떄 여러 게층으로 나누어서 구현
- 여러 계층에 비지니스 로직 과 관련이 없는 공통적인 로직이 필요
- 이러한 로직을 Cross-Cutting Concern 이라고 하는데 이러한 토드를 각 계층에 구현하면 코드를 유지보수 하는게 어려움

### AOP 장점

- 비지니스 모듈에는 주요 관심사에 대한 로직만 존재
- 비지니스 모듈을 수정하지 않고도 추가 동작을 작성할 수 있음

### 구현 방식

> 프록시 패턴을 이용해서 구현

- 외부에서 비지니스 로직을 가진 객체 (Target)을 호출하면 이 객체를 감싸고 있는 외부 객체(proxy)를 호출해서 Target에게 전달하는 방식
- Proxy는 구현이 될 대 Target 을 상속받아서 만들어지기 때문에 Target과 동일한 방식으로 호출하는 것이 가능

### AOP 용어

- Advice
    - 공통 기능의 코드로 로그 출력 이나 트랜잭션 관리등
    - 언제 적용할 것(Before,After,Around,Returning,AfterThrowing,Introduction) 인지를 설정해서 사용
- JoinPoints
    - 적용 가능한 지점
        - 메서드 호출 전 후 와 속성 호출 전 후가 가능
        - 스프링은 메서드 호출 전 후 만 가능
- PointCut
    - advice 와 joinpoints 를 결합하기 위한 설정
    - 정규 표현식이나 패턴을 이용하는데 스프링에서는 apspectj pointcut 표현 언어를 사용
- Target
    - advice 가 적용되는 객체
- Aspect
    - 공통 관심사항 과 이를 적용하는 코드 상의 포인트를 모은 것
- Weaving
    - 어드바이스 와 target을 결합하는 시점으로 컴파일시, 클래스 로딩 할 때

### AOP 적용

```java

@EnableAspectJAutoProxy
public class SpringMovieProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMovieProjectApplication.class, args);
    }

}
```

- Domain 클래스 생성

# Spring Security

> 인증 과 인가

- Authentication (인증) : 작업을 수행할 수 있는 주체인지 확인
- Authorization (인가) : 권한을 확인하는 작업
- Spring에서의 Security
- 직접 구현해도 되지만 Security 관련 패키지를 제공

### 실습을 위한 프로젝트 생성

> 의존성 Spring Boot DevTools Lombok, Spring Web , Thymeleaf , Spring Security, OAuth2Client,Spring Data JPA, MariaDB

### 로그인 과정없이 요청을 처리함

```java

@Configuration
@Log4j2
@RequiredArgsConstructor
public class config {
    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity httpSecurity) throws Exception {
        log.info("필터 환경 설정");
        return httpSecurity.build();
    }
}
```

### 설정 클래스에 메서드를 추가해서 정적 파일에서는 시큐리티가 작동하지않도록

```java

public class config {
    //정적 파일 요청은 동작하지 않도록설정 
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
}
```

### Spring Security Customizing

- password encoder
    - 암호화해서 복호화가 불가능한 암호화를 수행해주는 클래스
        - 복호화는 불가능하지만 비교는 가능
- spring boot 에서는 내부적으로 BcryptPasswordEncoder를 사용
- PasswordEncoder config bean

```java

public class config {
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

- 비밀번호 암호화 및 비교 테스트

```java
public class SecurityTest {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void testPasswordEncoder() {

        String password = "1111";
        //암호화
        String enpw = passwordEncoder.encode(password);

        System.out.println("enpw" + enpw);
        enpw = passwordEncoder.encode(password);
        System.out.println("enpw" + enpw);

        boolean result = passwordEncoder.matches(password, enpw);
        System.out.println(result);

    }
}

```

### UserDetailsService

> 일반적인 로그인 처리는 회원 아이디 와 비밀번호를 가지고 데이터를 조회하고 올바름 데이터가있으면 세션ㄴ이나 쿠키로 처리하는 형식

- Spring security 에서는 회원정보를 User 라고 하고 아이디 대신에 username 이라는 용어를 사용
    - 아이디를 가지고 데이터를 먼저 조회하고(UserDetailsService가 수행) 그 후 비밀번호를 비교하는 형식으로 로그인을 수행
        - 비밀번호가 틀리면 Bad Cridential 이라는 결과를 만들어냄
        - 로그인 성고앟면 자원에 접근할 수 있는 권한이 있는 지 확인하고 권한이 없으면 Access Denied 를 만들어낸다

### loadUserByUsername

> 메서드만 소유

- username을 가지고 User 정보를 찾아오는 메서드로 리턴 타입은 UserDetails 인데 이 클래스를 이용하면 권한(Authorities,password,username ,계정 만료여부, 계정 잠김
  여부) 를 알아 낼 수 있다.
- 리턴 타입을 만드는 방법은 DTO 클래스에 UserDetails를 구현하는 방법이 있고 별도로 DTO 클래스를 생성하는 방법이있다.

### formLogin

> 인증이나 인증 절차에서 문제가 발생했을 때 로그인 페이지를 보여주도록 해주는 메서드로 연달아서 loginPage("/로그인 URL)를 호출해서
> 로그인하는 URL을 설정할 수 있고 loginProcessingURL("/로그인을 처리할 URL") 을 호출해서 로그인 처리 URL 을 설정할 수 있다

### CustomConfig에 formLogin을 호출하는 코드를 추가

```
    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity httpSecurity) throws Exception {
        log.info("필터 환경 설정");

        //인증이나 인가에 문제가 발생하면 로그인 폼 출력
        httpSecurity.formLogin();
        return httpSecurity.build();
    }
```

### 로그인 관련 로직을 처리해주는 클래스를 생성하고 메서드를 오버라이딩

> CustomUserDetailService

```java

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //아이디를 입력하고 로그인 요청을 하게되면 아이디에 해당하는 데이터를 찾아오는 메서드
        //로그인 처리를 해주어야한다
        log.info("loadUserByUsername" + username); //이렇게하면 로그인한 username이 뜸 
        return null;
    }
}
```

### 인가 설정

> 어노테이션으로 권한을 설정하려면 설정 관련 클래스에 @EnableGlobalMethodSecurity를 추가하고 컨트롤러에서 @PreAuthorize 어노테이션을 이용해서 설정

- @EnableGlobalMethodSecurity(prePostEnabled = true) config에서 설정후 controller 설정

```java
public class controller {

    //로그인한 유저만 접속이가능 
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/sample/member")
    public void member() {
        log.info("멤버 만 허용");
    }

    @GetMapping("/sample/all")
    public void main() {
        log.info("모두허용");
    }

    //로그인 한 관리만 접속이가능 
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/sample/admin")
    public void admin() {
        log.info("관리자 만 허용");
    }
    //결과로 유저로 로그인하였을경우 관리자로 들어가면 에러가남 
}
```


### 인증 설정 방식 
- formLoign 
  - 인증이 필요한 경우 로그인 폼을 출력하도록 해주는 설정
- loginPage: 로그인페이지설정 
- defaultSuceessUrl
  -  로그인 성공했을 때 리다이렉트 할 url로 설정 
- failureUrl 
  - 로그인 실패했을 때 리다이렉트 할 URL을 설정 

- 커스텀 로그인 페이지 출력해보기 
```java
  @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity httpSecurity) throws Exception {
        log.info("필터 환경 설정");

        //인증이나 인가에 문제가 발생하면 로그인 폼 출력
        httpSecurity.formLogin().loginPage("/member/login");

        return httpSecurity.build();
    }

```

### CSRF (Cross Site Request Forgery - 크로스 사이트 요청 위조)
> CSRF
- 사용자의 등급을 변경하는 URL을 알고 이 때 필요한 파라미터를 안다면 직접 로그인을 하지않고 img 태그나 form태그를 이용해서 URI 와 파라미터를 기록해 둔 상태에서 관리자가 이 링크를 클릭하게되면 공격자가 관리자 등급으로 변경되서 공격하는 기법 

- 방어 방법
  - referrer 체크 
    - put이나 delete 같은 방식을 사용 
    - csrf토큰을 활용 

### logout
> 로그아웃을 호출해도 되고 JESSIONID로 시작하는 쿠키를 삭제해도됩니다 