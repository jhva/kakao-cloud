# MVC Pattern

> Presentation Logic 과 Business Logic을 분리해서 표현

### 구조

- Model : 데이터 자체 또는 데이터를 처리하는 영역

- View: 결과 화면을 만들어내는 영역 - EL과 JSTL을 이용한 JSP, 템플릿 엔진, Front End Application 등 으로 구현

- Controller : Model과 View 사이를 연결 - HttpServlet을 이용, Controller(JSP 나 템플릿엔진)와 RestController(Front End Application)

- 장점
    - 유지보수가 쉬워짐

- 단점

    - 구조가 복잡함

### Controller 계층

> Front Controller

- 모든 요청을 전부 받아들이는 Controller

    - 공통으로 처리할 Logic을 호출

    - 추적이나 보안을 적용

    - Spring 에서는 DispatcherServlet이 이역할을 담당

### Page Controller

> 특정 요청을 처리하기 위한 Controller

- Business Logic을 호출

### MVC구조

> Request -> FrontController -> Controller -> Service -> DAO -> 데이터베이스 프레임 워크(MyBatis, Hibernate -JPA)->Database

- Front Controller 가 Template Engine을 이용해서 결과화면을 생성할수 있음

- Spring MVC에서 사용되는 Annotation)

    - Controller : 뷰를 선택하기 위한 Controller

    - RestController : 데이터를 전달하기 위한 Controller

    - Service

    - Repository

    - Component : bean을 자동 생성

### 5개의 어노테이션을 component-scan에 설정된 패키지에 만들면 bean을 자동생성

- RequestMapping
    - Controller에서 클라이언트가 요청한 URL과 처리할 메서드를 매핑하기 위한 어노테이션

- RequestParam
    - 파라미터 읽기위한 어노테이션﻿

- RequestHeader
    - 요청에서 특정 HTTP 헤더 정보를 추출할 때 사용

- PathVariable
    - URL의 일부분을 파라미터로 사용하는 경우
    - 현재의 URI에서 원하는 정보를 추출할 때 사용

- CookieValue
    - 쿠키가 존재하는 경우 쿠키의 이름을 이용해서 쿠키 값을 추출

- SessionAttribute
    - 세 션상에서 모델의 정보를 유지하고 싶은 경우 사용
- ModelAttribute
    - 자동으로 해당 객체를 뷰까지 전달하도록 만드는 어노테이션

- IntiBinder
    - 파라미터를 모아서 하나의 객체로 만들고자 하는 경우
- ResponseBody
    - 리턴 타입을 HTTP의 응답메시지로 사용하는 경우 -
        - RestController에서주로이용
- RequestBody요청문자열을
    - 그대로 파라미터로 전달

### Spring MVC 용어

- DispatcherServlet
    - front controller 의 역할을 수행해주는 클래스 자동생성
- HandlerMapping
    - 웹의 요청과 URL 과 controller 클래스를 매핑시키져ㅜㄴ는 클래스 .자동생성
- ModelAndView
    - Controller 에서 view 이름을 결정하고 데이터를 넘기고자 할 때 사용
- ViewResolver
    - Controller 클래스가 처리한 결과를 출력할 뷰를 결정하는 클래스

### 처리 흐름

- 클라이언트가 요청을 하면 DispatcherServlet 클래스가 요청을 받아서 알맞는 controller 가 있는지 확인하고 있으면 controller 에게 요청을 전달해서 처리를 하고 controller 가
  리턴한 view 이름을 가지고 viewResolver 설정을 확인해서 결과를 출력할 view를 결정해서 출력

### Srping MVC project

- sts3 에서는 레거시프로젝트 메뉴에서 생성
- 이크립스나 sts4라면 직접생성은 안되고 sts3 플러그인을 설치해서 생성

### 클라이언트가 전달한 데이터 읽기

> URL에 전달한 데이터 읽기

- 파라미터가 하나인 경우 최근에는 URL에 파라미터를 포함시켜 전송
- 상세보기 나 삭제를 할 대 ㅁ낳이사용

### paramter 처리방법

> 요청 처리 메서드에 httpServletRequest 타입의 매개변수를 설정해서 getParameter 나 getParameterValues 메서드를 호출해서 읽어 내기

- @RequestParam("파라미터이름") 자료형 일므을 매개변수로 추가해서 파라미터 이름에 해당하는 데이터를
  형 변환해서 이름으로 받기
- 파라미터 와 동일한 이름을 사용하는 속성을 갖는 command 클래스를 생성해서 사용

### Controller 의 요청 처리 메서드

> Controller 를 생성하는 방ㅂ법응ㄴ 클래스 위에 @Controller , @RestController를 추가해주면 된다

- URL 매핑
    - 클래스 위에 @RequestMapping("공통된 url") 을 기재하면 공통된 url
    - 메서드 위에 @ReqeustMapping(value="공통된 url", method=RequestMethod.전송방식) 을 작성하는ㄷ ㅔ이렇게하면 클래스 위에 있는것과 합쳐져서 요청 url을 결정
- 요청 처리 메서드 파라미터  ( 파라미터 작성 순서는 상관이 없다.)
    - HttpServletRequest, HttpServletResponse,HttpSession
    - Model
        - 뷰에 데이터를 전달하기 위해서 사용, 리다이렉트 할 때는 데이터를 전달할 수 없음
    - RedirectAttributes
        - 뷰에 데이터를 전달하는데 리다이렉트 할 때 한 번만 사용할 데이터 전달
        - 뷰에 데이터를 전달하기 위해서 사용
    - @RequestParam, @PathVariable, @CookieValue, @RequestHeader
        - 클라이언트의 데이터를 읽기위해서 사용한다
    - Errors,BindingResult
        - 유효성 검사에 사용
- 리턴타입
    - String
        - Controller에서는 뷰이름이 되고 RestController 에서는 텍스트 데이터
    - void
        - 뷰를 직접 생성 할 목적.
    - DTO, List,ResponseEntity
        - RestController 에서 json 리턴

### 뷰 이름 리턴시 이동 방법

- 뷰 이름만 리턴하면 ViewResolver 와 결합해서 출력할 뷰를 결정하는데 이동 방법은 Forwarding
    - Forwarding 결과화면으로가는것
- redirect 로 이동하고자 하는 경우
    - 요청경로를 기재하면 되는데 ViewResolver설정을 참조하지않고 Controller 로 이동
    - redirect 할 때 작성하는 것은 뷰 이름이 아니다 .
    - redirect 요청을다시보내는거

### View 에 전달하는 방법

- Model 을 매개변수로 만들고 Model 에 속성 이름 과 데이터를 저장
- HttpServletRequest,HttpSession,ServletContext(application) 을 이용해서 전달
- redirect 로 이동할 때 한 번만 데이터를 사용하는 경우는 Session을 사용하지 않고 RedirectAttributes를 이용
    - 한번만 사용할건데 redirect를 사용하면 나중에 지울때 문제가생기니 RedirectAttributes를 사용한다

### redirect 와 forwarding 의 차이

- https://kotlinworld.com/329

    - redirect 로 이동하게 되면 request 의 데이터는 전달되지 않는다
      redirect 가 새로 만들어지기 때문이다
      이런 경우에는 session 이나 RedirectAttributes 를 이용해야한다

```java

public String redirect(Model,model,HtttpSession session  /* RedirectAttributes attr*/){
        model.addAttribute("msg",메시지);
        session.setAttribute("msg","세션을 이용핟 데이터 전달 ")


        // attr.addFlashAttribute("mgs","일회용 데이터전달 "
        }
```

- 세션을 이용해서 보낼때는 브라우저가 종료하거나 세션을 초기화 하지 않는이상 계속 유지가 된다
    - 매개변수로 RedirectAttributes 받고 쓰게되면 한번만 쓰고 버려지게된다

## 데이터베이스연동

### Repository 에서 사용하는 테이블의 데이터를 표현하기 위한 클래스 - 기본 패키지.domain.itemEntity

```java

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemEntity {
    private int itemid;
    private String itemname;
    private int price;
    private String description;
    private String pictureurl;
}
```

### Service 계층 과 Controller 그리고 View 사이의 데이터를 전송하기 위한 클래스 - 기본패키지.dto.itemDTO

```java

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {
    private int itemId;
    private String itemname;
    private int price;
    private String description;
    private String pictureurl;
}
```

### 요청을 처리하기 위한 구조

> JPA 나 Mybatis 를 위한 인터페이스 생성 - 기본패키지.persistence.ItemMapper

```java
//entity폴더와 dto를 변환할때가많다 
//서비스이외에는 전부다 dto로 해야한다 
//그럼 변환을해야한다 
// 그럼 변환하는걸 serviceImpl에만들꺼냐 interface에만들꺼냐 
//이번엔 interface에 만들꺼다 이유가 
@Repository
public interface ItemMapper {
    
    public List<ItemEntity> allItme();


}
```

### 사용자의 요청을  처리하기 위한 서비스 생성 - 기본패키지.service.ItemService, ItemServiceImpl

```java

@Service
@RequiredArgsContstructor
public interface ItemService {
    @Autowired
    private ItemMapper itemMapper;
    //dto를 ㅇ엔티티로 변환하는 메서드 
    public Entity dtoToEntity(ItemDTO dto) {
        ItemEntity entity = ItemEntity.builder().
                itemId(dto.getItemId)
                .itemName(dto.getItemName)
                .itemDescription(dto.getItemDescription)
                .itemPictureUrl(dto.getItemPictureUrl)
                .build();

        return entity;

    }

    public default ItemDTO entityToDTO(ItemEntity entity) {
        ItemDTO = dto = ItemDTO.builder()
                .itemid(entity.getItemId)
                ...
        .build()
        return dto;
    }

}

public class ItemService implements ItemServiceImpl {

}
```

### 서비스 와 View 를 연결할 Controller

```java

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService; //주입받아야함 

    @RequestMapping()
    public class home() {

    }
}
```