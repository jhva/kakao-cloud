# REST API

### REST

> 분산 하이퍼미디어 시스템 아키텍쳐의 한 형식

- 자원에 이름을 정하고 (URL) URL에 명시된 HTTP Method 를 통해서 해당 자원의 상태를 주고받는것을 말한다.
    - 동일한 자원에 대한 요청은 동일한 URL로 처리
        - Seamless 가능 , 뷰를 만들지 말고 데이터를 전송
    - URL 은 소문자로만 작성

### REST API

> REST 아키텍쳐를 따르는 시스템/애플리케이션 인터페이스

- REST 아키텍쳐를 따르는 서비스를 RESTful 하다라고 표현함

### 특징

- 유니폼 인터페이스
    - 일관된 인터페이스
- 무상태성
    - 서버에 상태 정보를 따로 보관하거나 관리하지 않는다는 의미
    - 세션이나 쿠키 사용을 하지 않음
        - 서버에 불필요한 정보를 저장하지 않음
    - Web토큰이나 로컬 스토리지 사용으로 대체
- Layerd System
    - 서버는 네트워크 상의 여러 계층으로 구성 될 수 있지만 클라이언트는 서버의 복잡도 와 상관없이 End Point 만 알면됨
- 클라이언트
    - 서버아키텍쳐
        - 클라이언트 애플리케이션 과 서버 애플리케이션을 별도로 설계하고 구현해서 서로에 대한 의존성을 낮추는것

- URL 설계 원칙
    - URI의 마지막에 / 를 포함히지 않음
    - 언더바 대신에 - (하이픈) 를 사용
        - 언더바를 사용하지 않는이유는 시멘틱때문이다
    - URL에는 행위가 아닌 결과를 포함
        - 행위는 HTTP 메서드로 표현
    - URL 에 파일의 확장자를 표현하지 않는다
        - 파일의 확장자는 accept 헤더를 이용

### 요청을 처리하는 방식 컨트롤러

```java
public class Controller {
    @RequestMapping(value = "/hello", method = RequestMethod.GET) //옛날방식
    public String hello() {
        return "rest-api hello";
    }


    @GetMapping("/newhello")//현재
    public String newhello() {
        return "rest-api newhello";
    }
}
```

### URL에 포함된 파라미터 처리

> 파라미터가 1개 일때는 파라미터를 URL에 포함시켜 전송할 수 있다

- GET이나 DELETE인 경우
    - 요청 처리 메서드의 URL을 설정할 때 파라미터로 사용된 부분을 {변수이름} 으로 설정하고 요청처리 메서드에 매개변수로 @PathVariable (변수이름) 자료형 이름을 추가하면 된다
        - {}안의 변수 이름 과 @PathVariable() 안의 변수 이름은 일치해야 한다
        - 자료형이 맞지않으면 예외가 발생함

```java

@GetMapping("/product/{num}")//현재
public String newhello(@PathVariable("num") int number){
        return number+"number";
        }
```

### 일반 파라미터 처리

> HttpServletRequest이용해서 처리

- String getParameter (String 파라미터이름) 와 String[] getparameterValues(String파라미터이름) 을 이용
- @ReqeustParam (어노테이션 이용 )
    - 요청 처리 메서드의 매개변수로 @ReqeustParam(String 파라미터이름) 자료형 변수이름을 설정해서 처리
- Command 객체 이용
    - 파라미터 이름을 속성으로 갖는 클래스를 만들고 클래스의 참조형 변수를 요청 처리 메서드의 ㅁ매개변수로 대입해서 처리

### GET방식의 파라미터 설정

- URL 뒤에 ? 를 추가하고 이름= 값(&이름=값)
- form을 만들고 method 를 생략하거나 method 에 get이라고 설정
- ajax나 fetch api 에서 전송 방식을 GET으로 설정

### name, email organization 을 GET 방식으로 전송했을 때 처리

- 도메인
    - http://localhost:8080/api/v1/rest-api/param1?name=이름&email=이메일&organization=
- Controller 클래스에 HttpServletRequest 를 이용해서 처리하기 위한 메서드를 구현

```java

public class Controller {


    @GetMapping("/param")
    public String getParam(HttpServletRequest req) {
        String name = req.getParameter("name");
        String organization = req.getParameter("organization");
        String email = req.getParameter("email");

        return "name: " + name + ", organization: " + organization + "email" + email;

    }
}
```

### @RequestParam으로 처리하는 컨트롤러 메서드

```java
public class Controller {
    @GetMapping("/param1")
    public String getParam1(@RequestParam("name") String name,
                            @RequestParam("organization") String organization,
                            @RequestParam("email") String email
    ) {
        return "name: " + name + ", organization: " + organization + "email" + email;
    }
}
```

### Command객체를 이용해서 파라미터를 처리하기 위해서 파라미터 이름을 속성으로 갖는 DTO클래스를 생성

### POST API

- 리스소를 저장할 대 사용하는 요청방식
- POST방식에서는 리소스 나 값을 Htp Body에 담아서 서버에 전달
- 파라미터 처리는 HttpServletRequest 나 @RequstParam 그리고 Command객체를 이용한 처리가 모두가능하기는 한데 Command 객체를 이용해서 처리할 때는 클래스 이름 앞에
  @RequestBody를 추가해서 HttpBody의 내용을 객체에 매핑하겠다고 명시적으로 알려주는것을 권장

### POST 방식에서의 파라미터 전송

- form 태그의 method 를 post롤 설정해서 폼의 데이터를 전송
- ajax 나 fetch api 에서 method 속성의 값을 post 로 설정해서 전송

### POST 방식의 테스트

> 별도의 프로그램을 이용해서 테스트

```java
    @PostMapping("/post")
public String post(@RequestBody ParamDTO paramDTO){

        return paramDTO.toString();


        }
```

### PUT API

> 데이터를 수정할 때 사용하는 방식

- 유사한 역할을 하는 것으로 FETCH도 존재
- 사용방법은 post와 유사
    - PUT으로 설정하면 GET으로 처리가 된다

```java
public class Controller {
    @PutMapping("/param3")
    public ResponseEntity<ParamDTO> putParam(@RequestBody ParamDTO paramDTO) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(paramDTO);
    }
}
```

- return type을 ResponseEntity로 하게되면 상태코드와 body를 보낼수있게됨

### DELETE API

- 데이터를 삭제할 때 사용
- 삭제를 할 때는 기본값 하나만으로 삭제하는 경우가 많기 때문에 GET 방식과 동일한 방식으로 처리

```java
public class Controller {
    @DeleteMapping("/param3")
    public String deleteParam(@RequestParam("num") int num) {
        return num + "";
    }
}
```

### 로깅 라이브럴리

> Logging

- 애플리케이션이 동작하는 동안 시스템의 정보나 동작 정보를ㄹ 시간 순으로 기록하는것
- Logging 은 개발 영역 중 요구사항 (Common Concern) 에 속하지만 디버깅하거나 개발 이후 개발 발생한 문제를 해결할 때 원인분석에 꼭필요한요소
- 자바 진영에서 가장 많이 사용되는 로깅 라이브러리 logback

### Logback

- log4j 이후에 출시된 로깅 프레임워크로서 logfj에 비해 서능ㅇ이향
- 5개의 로그 레벨 설정가능
    - ERROR
        - 심각한 문제가 발생해서 어플리케이션의 동작이 불가능
    - WARN
        - 시스템 에러의 원인이 될 수 있는 경고 레벨
    - INFO
        - 상태 변경과 같은 정보 전달
    - DEBUG
        - 디버깅할 때 메시지를 출력

### Spring View

> Java Web Application 에서 화면을 출력하는 방법

- JSP 이용
    - 서버의 데이터를 출력하고자 EL 과 JSTL 을 학습해야한다
    - JSTL 은 아파치에서 제공하는 Custom Tag 로 웹 프로그래밍에서 많이 사용하는 자바 기능을 ㅐ그형태로 만들어준것
- Template Engine 이용
    - 서버의 데이터를 View로 출력하기 위해서 만든 문법
    - 일반적으로 확장자를 html을 사용
        - Thymeleaf, Velocity,FreeMaker,Mustache,Groovy

```groovy
    implementation 'org.apache.tomcat.embed:tomcat-embed-jasper'
// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-thymeleaf
implementation group: 'org.springframework.boot', name: 'spring-boot-starter-thymeleaf', version: '2.7.7'
```

```yml
#application yml
spring:
  mvc:
    view:
      prefix: /WEB-INF/views/
      suffix: .jsp
  thymeleaf:
    prefix: classpath:/templates/
    suffix: .html
    cache: false
    view-names: thymeleaf/*
```

### 뷰를 출력할 디렉토리 생성

- src/main 디렉토리안에 webapp디렉토리 생성
- webapp 디렉토리 안에 WEB-INF
- WEB-INF 안에 VIEW

### Thymeleaf

> 개요

- 화면 출력을 위한 템플릿 엔진 중 하나

- 장점
    - 데이터 출력이 JSP 의 EL 과 유사하게 ${} 를 이용
    - Model 에 담긴 데이터를 화면에서 Javascript 로 처리하기 편리
    - 연산이나 포맷과 관련된 기능을 추가적인 라이브러리 없이 지원
    - html 파일로 바로 출력이 가능
        - 서버사이드 렌더링을 하지 않고 출력가능

### 문법

- 속성이 아닌 곳에서 데이터 출력 : [[${(데이터}]]
- 반복문
- th:each = "임시변수 ${데이터 목록}"
- 반목문을 사용하면 state 객체가 생성되는데 이 객체에 인덱스가 전달된다

### 분기문

- th:if
    - ~ unless이용
- th:switch 와 th:case 제공

```java

@Controller
public class PageController {
    private final org.slf4j.Logger Logger = LoggerFactory.getLogger(PageController.class);

    @GetMapping("/")
    public String main(Model model) {
        Map<String, Object> map = new HashMap<>();

        map.put("msg", "java");
        map.put("buildTools", "gradle");
        model.addAttribute("msg", map);

        List<String> list = new ArrayList<>();
        list.add("123");
        list.add("쏘ㅑㄹ랴쏘ㅑㄹㄹ랴");
        model.addAttribute("list", list);
        //main.html 
        return "main";
    }

    @GetMapping("/ex1")
    //리턴 타입이 void 이면 출력하는 뷰 이름은 요청 url
    //view 의 이름은 ex1
    public void ex1(Model model) {
        Logger.info("ex1");


    }
}
```

```html

<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
        table, tr, td, th {
            border: 1px solid #444444;
        }
    </style>
</head>
<body>
<table>
    <tr>
        <th>언어</th>
        <th>IDE</th>
        <th>빌드 도구</th>
    </tr>
    <tr>
        <td>[[${msg.msg}]]</td>
        <td>[[${msg.buildTools}]]</td>
    </tr>

</table>
<table>
    <tr th:each="task:${list}">
        <td>[[${task}]]</td>
    </tr>
</table>

</body>
</html>
```

### DTO의 List 출력

- DTO 클래스 생성

```java

import java.time.LocalDateTime;

public class SampleVO {
    private Long sno;
    private String first;
    private String last;

    private LocalDateTime regTime;
}

```

- controller

```java
public class Controller {
    @GetMapping("/ex2")
    public void ex2(Model model) {
        //1~부터 20
        List<SampleVO> list = IntStream.range(1, 20).asLongStream().mapToObj(i -> {
            //정수를 객체로 바꾸겠다는의미이다
            SampleVO vo = SampleVO.builder()
                    .sno(i)
                    .first("..FIRST" + i)
                    .last("LAST.." + i)
                    .regTime(LocalDateTime.now())
                    .build();
            return vo;
            // 1~20 까지 의 숫자를
            //Sample VO로 바꾼후

//이러면 리스트로바뀜
        }).collect(Collectors.toList());
    }
}
```

- ex2.html

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>데이터 출력 목록</title>

</head>
<body>
<ul>
    <!-- li가 each 만큼 개수가생긴다 -->
    <li th:each="vo:${list}">
        [[${vo}]]
    </li>
</ul>

</body>
</html>
```

- 문법

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>데이터 출력 목록</title>

</head>
<body>
<ul>
    <!-- li가 each 만큼 개수가생긴다 -->
    <!--    state 는 index를 가져오기위한 -->
    <li th:each="vo,
    state:${list}"
        th:if="${vo.sno % 5 ==0}">
        [[${state.index}]] -- [[${vo}]]
        <!--[[${}]] 데이터 출력 -->
        <span th:if="${vo.sno % 5 ==0}" th:text="${vo.sno}"></span>
<!-- 각 태그:text 하면 굳이 태그안에 안넣어도 데이터 표출이되는듯 -->
        <span th:unless="${vo.sno % 5 ==0 }">
        [[${vo.first}]]
    </span>
    </li>

</ul>

</body>
</html>
```
- th:block 
  - 별도의 태그없이 출력하고자 할 때 사용 
  - case 에 boolean 이 가능 