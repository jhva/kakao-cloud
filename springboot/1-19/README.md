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