# Spring

> Java Enterprise Application 개발을 편리하게 해주는 Open Source로 경량급 프레임워크

- 빠른 구현시간
- IoC (제어의 역전)
    - 컴포넌트 기반 개발 방법론 , 클래스는 개발자가 만들지만 인스턴스의 생성 과 수명주기 관리는 Framework나 Web Container 가하는방식
- DI (의존성 주입)
    - 클래스 내부에서 사용할 인스턴스를 외부에서 생성해서 주입시켜 주는 것
- AOP

## Lombok

> 생성자

- @NoArgsConstructor
    - 매개변수가 없는 생성자
- @AllArgsConstructor
    - 모든 속성이 매개변수인 생성자
- @RequiredArgsConstructor
    - 속성 중에서 final 이나 @NonNull인 속성이 존재하는 경우에 외부에 동일한 자료형의 데이터가 있으면
      자동으로 주입받아서 생성하는 생성자
- @Builder
    - 인스턴스를 생성할 때 생성자를 직접 호출하지 않고 Builder 패턴을 적용해서 생성하도록 해주는 어노테이션
- @NotNull
    - 속성위에 ㄱ기재해서 null여부를 체크해서 null이면 NullPointerException 발생

### 패턴

- Singleton Pattern
    - 인스턴스를 하나하나 만드는 패턴
- Template Method Pattern
    - 인터페이스에 메서드를 선언하고 클래스에서 구현되는 패턴
- Decorator Pattern
    - 다른 인스턴스들을 조합해서 만드는 패턴
- Builder Pattern
    - 속성들을 추가해나가면서 만드는 패턴

### Spring Project

- Spring Legacy Project
- Spring Boot Project
    - 내장된 WAS를 이용해서 실행 및 배포가 가능한 수준의 애플리케이션을 빠르게 생성

### 생성자 대신 정적 팩토리 또는 다른 클래스의 메서드를 호출해서 인스턴스 생성

> factory 패턴 (중요한거같음)

- 클래스의 인스턴스를 다른 클래스에서 생성하는 패턴
- 생성자를 이용하게 되면 생성자가 Overloading 된 경우 이름이 모두 동일하기 때문에 어떠한 방식으로 생성되는지 파악하기가 어려움
- 싱클톤 패턴을 적용하기 편리
- 매개변수에 따라 다양한 형태의 인스턴스를 생성할 수 있음
    - 상속 관계에 있는 여러 클래스의 인스턴스
- 단점으로는 API 문서에서 알아보기가 어려움
    - 생성자를 private 으로 만들기 때문에 상속이 불가능
- Factory 메서드의 Naming
    - from
        - 매개변수 1개를 받아서 생성
    - of
        - 매개변수 여러 개를 받아서 생성
    - valueOf
        - 자세한 버전
    - sharedInstance
        - 모두 동일한 인스턴스(싱글톤)
    - instance,getInstance
        - 매개변수를 받는 경우에는 모두 동일한 인스턴스가 아닐 수 있음
    - create,newInstance
        - 항상 새로운 인스턴스를 생성해서 리턴
    - getType
        - getInstance 와 동일하지만 현재 클래스가 아닌 다른 클래스의 인스턴스를 리턴하고 Type부분이 그에 해당하는 클래스 이름

## IoC(Inversion of Control)

> 제어의 역전 또는 제어의 역흐름

- 개발자가 작성한 프로그램이 재사용 라이브러리(Framework) 의 흐름 제어를 받게되는 소프트웨어 디자인 패턴

### IoC를 사용하는 이유

> 구현은 (개발자) 가 하고 수행(Framework) 의 분리

- 개발자는 수명 주기 나 디자인 패턴에 신경쓰지 않아도 된다
- 다른 모듈 과의 결합에 대해서 신경쓸 필요가 없어짐
- 모듈을 변경해도 다른 시스템에 부작용을 일으킬 가능성이 줄어든다

### Spring Bean

> 스프링이 제어권을 가지고 생성하고 관계를 설정하는 오브젝트  (이걸 android 나 React 에서는 Component 라고도 한다 )

- 정훈 참고
    - https://mangkyu.tistory.com/75
- 스프링에서 제어의 역전이 적용된 인스턴스를 스프링 bean이라고함
- Bean을 관리하는 인스턴스를 Bean Factory 라고 하고 Spring 에서는 Bean Factory 라는 인터페이스로 제공해준다
- @Component 어노테이션이 있는 클래스들을 찾아서 자동으로 빈 등록

### Application Context

> BeanFactory 인터페이스에 여러가지 기능이 추가된 인터페이스

- IoC 컨테이너 이면서 Singleton 을 저장하고 관리하는 Singleton Registry
- 기본적으로 모든 인스턴스를 Singleton 으로 생성
    - 이유는 Spring 은 대부분 서버 환경에서 구축 되기 때문이다.(변경 할 수 도 있다 )
- 여러가지 클래스에서 구현하고 있음

### AnnotationApplicationContext

> 어노테이션을 이용해서 Spring Bean 을 생성할 수 있도록 해주는 Application Context

- 팩토리 클래스 위에는 @Configuration 을 추가
- SpringBean 을 생성해주는 메서드 위에는 @Bean 을 추가
- AnnotationApplicationContext 클래스의 인스턴스를 만들 때 생성자에 Factory 클래스를 설정하고 인스턴스를 생성할때는 getBean 메서드로 호출
    - getBean 은 오버로딩되어있음
        - getBean(메서드 이름) :Object 타입으로 리턴
        - getBean(클래스이름.class) : 클래스 타입으로 리턴
        - getBean(메서드이름 , 클래스이름.class) :메서드를 호출홰서 클래스 타입으로 리턴
            - 가장 많이 사용함

### 생성

- 처음에 생성
- 필요할 때 생성 (지연생성 )
    - 필요할 때 만들ㅇ기
    - 이유 ; 메모리때문ㅇ ㅔ불러오고싶을때만 불러오자

```java

public class T {
    public Date date;

    public T() {
        date = new Date();
    }

    //이런걸 지연생성 
    //필요할때 만들어줘라 
    //왜냐 메모리 때문에 
    public Date getDate() {
        if (date == null) {
            date = new Date();
        }
        return date;
    }
}

//main 
public class main {
    T t = new T();
t.date.toString();
    // null pointerException
}
```

### DI (Dependency Injection - 의존성 주입)

> 개요

- 의존성
    - 인스턴스 내부에서 다른클래스의 인스턴스를 사용하는것
- 주입
    - 내부의 속성을 대입받는것
- 의존성주입
    - 인스턴스 내부에서 사용하는 다른 클래스의 인스턴스를 직접 생성하지 않고 외부에서 생성한 것을 대입받아서
      사용하는 것

### 목적

> 인스턴스 의 생성 과 사용의 관심을 분리하는 것

- 생성하는것과 사용하는것을 따로 두자
- 가독성이 높아짐

### 전제

- 사용되는 서비스 객체
- 사용하는 서비스에 의존하는 클라이언트 객체
- repository -> service - > controller
- 클라이언트의 서비스 사용방법을 정의하는 인터페이스
- 템플릿 메서드 패턴을 적용
    - Repository와 service에만 적용하는데 최근에는 Repository에도 적용하지 않는데 이유는 Repository를 인터페이스로 만들면 나머지를 프레임워크가
      구현해주기 때문에 (JPA를 사용하는 이유)
    - 서비스를 생성하고 클라이언트로 주입하는 책임을 갖는 주입자 - 프레임워크

### 주입 방법

> 생성자를 이용하는 방법

```xml

<bean id="아이디" class="클래스 경로">
    <constructor-arg value="값"/>
</bean>
```

- xml
    - 타입을 지정하지 않으면 타입은 기본적으로 String
    - 생성자의 매개변수가 여러개이면 여러개를 만들면 되는데 이 경우 순서는 의미가 없고 자료형이
      일치하는 매개변수를 이용한다
    - index 속성을 추가해서 몇 번째 인지 설정하는 것이 가능

### 어노테이션을 이용한 의존성 주입

> @Autowired

- 속성 위에 기재해서 동일한 자료형의 bean이 존재하면 자동으로 주입해주는 어노테이션
- 동일한 자료형의 bean이 없거나 2개 이상이면 예외 발생
- 필수해제
    - @Autowired(require=false) 로 설정
    - 동일한 자료형의 bean이 2개 이상인 경우
        - @Autowired
        - @Qualifier("사용할 bean의 아이디")

### 동일한 기능을 해주는 어노테이션

- @Resource(name = "bean 의 아이디)
- @Inject 와 @Named :javax.inject 라이브러리의 의존성을 설정해야한다

### 생성자에서 생성

- 매개변수가 있는 생성자 위에 @ConstructorProperties({"bean 아이디"}) 사용
- 주입받을 속성에 final을 적용하고 클래스 ㅅ상단에 @RequiredArgsConstructor 를 이용해도 동일한 기능이적용

### 데이터베이스 연동관련 메스드를 선언할 인터페이스

```java
public interface MemberRepository {

    //기본키를 가지고 하나의 데이터를 찾아오는 메서드 

    public MemberEntity findById(String id);
    //기본키가 아닌애들은 다 리스트 
}

```

### 데이터베이스 연동 관련 메소드를 구현할 클래스

```java
public class MemberImpl implements MemberRepository {
    @Override
    public MemberEntity findById(String id) {
        MemberEntity memberEntity = MemberEntity.builder()
                .id("asd")
                .nickname("김정훈")
                .password("1234")
                .build();
        return memberEntity;
    }
}

```

### 사용자의 요청을 메서드의 원형을 가진 service 생성

```java
public class MemberServiceImpl implements MemberService {
    //서비스는 Repository를 주입받아서 사용
    private MemberRepository memberRepository;

    @Override
    public MemberDTO findById(String id) {

        MemberEntity member = memberRepository.findById(id);
        // 레포지토리 메서드 호출
        MemberDTO memberDTO = MemberDTO.builder()
                .id(member.getId())
                .password(member.getPassword())
                .nickname(member.getNickname())
                .build();

        return memberDTO;
    }
}

```

### 사용자의 요청을 메서드를 구현할 service 클래스를 생성

```java

public class MemberServiceImpl implements MemberService {
    //서비스는 Repository를 주입받아서 사용
    @Autowired
    private MemberRepository memberRepository;

    @Override
    public MemberDTO findById(String id) {

        MemberEntity member = memberRepository.findById(id);
        // 레포지토리 메서드 호출
        MemberDTO memberDTO = MemberDTO.builder()
                .id(member.getId())
                .password(member.getPassword())
                .nickname(member.getNickname())
                .build();

        return memberDTO;
    }
}
```

### 사용자의 요청에 따라 필요한 Buisiness Loginc 을 호출하고 그 결과를 뷰에게 전달해주는 controller 클래스생성

### @Autowired 대신에 @RequiredArgsConstructor 사용

> 어노테이션을 클래스 위에 작성하고 주입받는 속성을 final로 설정해야한다

```java

//final 속성으로 만들어진 속성들에 동일한 자료형의 bean이있으면
//생성자를 이용해서 자동으루주입
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    //서비스는 Repository를 주입받아서 사용

    private final MemberRepository memberRepository;

    @Override
    public MemberDTO findById(String id) {

        MemberEntity member = memberRepository.findById(id);
        // 레포지토리 메서드 호출
        MemberDTO memberDTO = MemberDTO.builder()
                .id(member.getId())
                .password(member.getPassword())
                .nickname(member.getNickname())
                .build();

        return memberDTO;
    }
}

```
