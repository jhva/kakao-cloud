# json 출력방법

- controller 클래스 요청 처리 메서드의 리턴 타입을 ResponseEntity 로 설정하고 Content-type 을 json으로 설정해서 리턴
- jackson-databind라는 라이브러리를 사용
    - Controller 클래스의 리턴타입을 일반 자바 객체 나 List 로 설정하고 앞에 @ResponseBody를 추가하는방법
        - JSONObject 나 JSONArray 를 데이터로 넘겨준 후 MappingJacksonJsonView 를 이용해서 출력
        - Spring4 버전 이후에는 RestController 를 생성해서 리턴
            - 최근에 많이 사용

```java
@RequestMapping(value = "itemlist.json", method = RequestMethod.GET)
public String jsonReport(Model model){
        return"";
        }

```

- @JSONController
    - POJO 클래스 위에 @JSONController 를 추가하면 문자열이나 JSON 을 출력할 수 있는 Controller를 생성
        - 요청 처리 메서드에서 문자열을 리턴하면 문자열이 출력되고 DTO 나 List를 리턴하면 JSON 형태의 문자열이 만들어진다.

```java

@RestController
public class JSONController {
    @RequestMapping(value = "item.csv", method = RequestMethod.GET)
    public String csv() {
        return "csv,xml,json";
    }
}
```

### 스프링의 예외 처리

> 컨트롤러가 처리하는 도중 예외가 발생하는 경우

- 예외의 내용을 브라우저에 출력
- 사용자는 예외의 내용을 읽어보아도 의미를 파악하기가 어렵다
    - 사용자에게는 예외의 내용을 직접 출력하는 것은 별 의막 없다

### Spring 에서 예외를 처리하는 방법

- 스프링에서는 @ExceptionHandler 와 @ControllerAdvice 어노테이션을 이용해서 처리하는 것이 가능
- @ExceptionHandler사용 컨트롤러 클래스 안에 작성

```java
@ExceptionHandler(예외종류)
public String 메서드이름(){
        return" 예외 출력 뷰의 이름 "
        }
```

- 예외 종류에 해당하는 예외가 발생하면 리턴하는 문자열을 가지고 ViewResolver와 결합해서 ㅂ발생했을때 출력될 페이지를 결정
- 요청 처리 메서드의 Excpetion 타입의 매개변수 대입가능

```java
@ExcpetionHandler(Exception.class)
public String handlerExcpetion(Model model,Exception e){
        return"";
        }
```


### Validation
```java
public class MemberValidatior  implements Validator{
    @Override
    public boolean supports(Class <?> clazz){
        return Member.class.isAssignableFrom(clazz);
    }
}

```

