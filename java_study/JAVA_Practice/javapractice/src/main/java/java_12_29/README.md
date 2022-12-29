# Web Container

> 클라이언트의 요청이 Web Server 에게 전달되서 컴포넌트를 호출할 때 이 컴포넌트들을 관리하는 무엇인가가 필요한데 이 것이 Web Container

- Web Server 와 Web Container 의 역할을 동시에 할 수 있는 프로그램
    - Tomcat
    - Zeus

# Java Web programming 개발 환경

- JDK
    - 11버전 이상 설치 권장
        - https, eclipse
        - Spring 이 11버전에 최적화

### Web Container

> Tomcat

- 정훈 설치 했던 방법
- brew install tomcat
- new module 추가
- Web Application 그리고 tomcat서버로 설정
- /usr/local/Cellar .. tomcat/liber .. 머시기 경로설정 (EditConfiguratiion에서 )
- 그 후 editconfiguration에서 deployment 설정 (자기폴더에 맞게 하기)
- 그럼열림

### ServeltContext application

> 요청에 대한 모든 객체

### HttpServletRequest request

- 요청에 대한 정보를 저장한 객체
- 메서드
    - getRemoteAdd :클라이언트의 IP
    - getMethod: 요청 방식
    - getRequestURL : 요청한 URL
    - getQueryString :URL 부분 중에서 query String
    - getContextPath : 루트로부터의 경로
    - getParameters(String name) : name에 해당하는 파라미터 읽어오기
    - getParameterValues(String name) : name 에 해당하는 파라미터를 배열로 읽어오기

### get

- url 뒤에 ? 를 추가하고 파라미터를 이름 과 값의 형태로 전송
- 파라미터가 URL에 query string 의 형태로 포함되어 있는것
- 파라미터 길이에 제한 (128자)
- 자동 재전송
- 파라미터는 반드시 인코딩 되서 전송되어야한다
- 서버에서는 Web Container가 인코딩을 수행해야한다
- 최근의 Web Container 는 전부 UTF -8

### post

- 파라미터를 본문에 숨겨서 전송
- 길이에 제한 없음
- 자동재전송 기능이 없음
    - 인코딩을 서버 애플리케이션에서 수행 (request.setCharacterEncoding("UTF-8"))
        - 대부분 필터로 해결

### put

- post 와 동일하게 동작하는데 전체 수정에 사용

### fetch

- post 와 동일하게 동작하는데 부분 수정에 사용 ,멱등성이 없다라고 표현

### delete

- get과 동일하게 동작하는데 삭제에 이용

### head

- 리소스를 GET 방식으로 요청했을 때 돌아올 헤더를 요청

### options

- 통신 옵션

### connect 양방향 연결시도

- ssl 이나 Web Socket 에서 사용

### HttpServletResponse response

> 웹 브라우저에 응답을 보내는 응답 정보를 저장한 객체

- 뷰를 직접 만들 대나 헤더를 추가하고자 할 때 사용
- addDateHeader (String name, long value)
    - 시간은 epoch time 에 해당하는 정수로 설정
- addHeader (String name, String value)
    - setDateHeader
    - setHeader 도 존재
- Data Cashing
    - 브라우저는 동일한 요청을 보낼 때 빠르게 출력하기 위해서 캐시에 저장된 데이터를 사용 할 수 있다
    - 캐싱을 사용하지 못하도록 하기
        - 브라우저 다르게 설정
- response.setHeader("Pragma","no-cache");
- response.setHeader("Cache-control","no-cache");
- response.setHeader("Cache-control","no-cache");
- response.setHeader("Expires","1L");
- redirect: response.redirect(String url)
    - url 로 리다이렉트
    - 요청을 종료하지 않고 계속 이어지도록 하는 것을 forwarding 이라고 하고 요청을 종료하고
    - 새로운 요청을 요청을 만드는 것을 redirect 라고 한다.

- forwarding
    - forwarding 을 하게되면 이전 요청이 계속 이어지기 때문에 request 객체의 내용이 유지가 된다
    - url 이 변경되지 않는다. 새로 고침을 하게되면 이전 요청을 다시 수행한다
    - 도메인 내에서만 이동이 가능하다
    - redirect 를 하게되면 이전 요청이 종료되기 때문에 새로우 request 객체가 만들어지고 url이 변경된다.
        - 새로 고침을 하게되면 결과만 다시 출력된다.
        - 도메인에 상관없이 이동 가능하다
    - redirect를 할 때 데이터를 전달하고자 하면 session 객체를 이용해야 한다

### web.xml

- 웹 어플리케이션의 설정 파일로 WEB-INF 디렉토리에 위치해야함
- 애플리케이션이 실행하게 되면 가장 먼저 읽어서 설정을 수행하는 파일
- 필터,서블릿,웹 어플리케이션 정보 , 세션 설정 , 에러페이지 설정, 시작 요청 설정, 초기화 파라미터 설정들을 수행

### application

- 서버객체
    - 여기에 데이터를 저장하면 모든 클라이언트가 데이터를 공유할 수 있음
    - 초기 설정을 읽는데도 사용

### 저장객체

- 뷰에게 데이터를 전달할 때 사용하는 3개의 객체가 있다 (서버에서 뷰를 렌더링 할 때 사용 )
- request
    - 하나의 요청에 해당하는 데이터를 저장
        - redirect 를 하면 새로 만들어 진다
- session
    - 하나의 브라우저에 해당하는 데이터를 저장
        - 접속을 해지하지 않으면 유지 된다
- application
    - 모든 클라이언트가 공유
        - 서버를 재시작해야만 초기화된다

### 데이터 사용 메서드

- void setAttribute(String name, Object value) : 데이터 저장
- Object getAttribute(String name) :데이터 읽기인데 Object 타입으로 리턴되므로 강제 형 변환을 해서 사용
- removeAttribute(String name) : 데이터 삭제

### 이동

> forwarding

- 태그 이용 <jsp:forward page="이동할 페이지 - 결과를 출력할 페이지"/>
- 코드 이용 request getRequestDispatcher("페이지 경로").forward(request,response)
- spring 에서 기본적으로 :forwarding

### redirect

- response.sendRedirect("경로");

### forwarding 실습

- input.jsp
    - 요청
    - 이 부분은 별도의 Application 으로 만들기도 한다
- process.jsp
    - 처리
        - 이 부분은 나중에 Servlet 과 Java POJO Class (Model) 로 변환
- POJO Class
    - 다른 프레임워크의 클래스로부터 상속받지 않은 클래스
- output.jsp
    - 결과
- 조회의 경우는 forwarding 이 일반적인데 forwarding 을 하게되면 처리 작업을 다시 수행한다는 것을 기억해야한다
- 로그인 같은 경우는 ID 와 Password를 찾는 조회 작업이지만 forwarding하지 않는다

### Servlet

> model1 과 model2

- model1은 모든 로직을 Jsp 에 작성한것
- model2는 요청 과 출력은 jsp 가 담당하고 처리 부분은 servlet 이나 POJO class 가 담당하도록 하는것
- jsp 파일에 java 코드 와 출력하기 위한 태그가 같이 있으면 유지보수가 어렵다고 생각하기 때문이다 .

### MVC Pattern

> 처리하는 로직과 출력하는 부분을 분해

- 요청 과 출력을 위한 jsp 부분을 View 라고 해서 별도로 만들고 처리하는 부분을 Model 이라고 해서
  POJO Class 로 만들고 요청을 받아서 필요한 Model 을 호출하고 그 결과를 받아서 View 에게 전달되는 부분을 Controller 라고한다
- Model 부분을 일반 로직을 처리하는ㄴ 부분 (Service - Business Logic) 과 데이터 영속성 관련 부분 (Repository - DAO,Persistency)
  로 분해해서 작업하는 것이 일반적이다
- Front Controller 패턴
    - 모든 사용자의 요청을 먼저 받을 Front Controller 를 별도로 두고 그 이후 각 요청을 처리할 Page Controller 를 만드는 패턴
- Rest Controller
    - 출력할 뷰를 직접 생성하지 않고 데이터만 전달하도록 만든 것 

### url pattern 
> 서블릿을 등록할 때 매핑시키는 url을 설정하는 방법 
- /경로 
  - 경로와 매핑
- /* 
  - 모든 경로 와 매핑 
- /디렉토리/* 
  - 디렉토리가 포함된 모든 URL 과 매핑이됨 
- *.확장자 
  - 확장자로 끝나는 모든 URL 과 매핑 


### Listener
> Listener 는 이벤트가 발생했을때 호출되는 메서드를 소유한 인스턴스 
- Java EE 에서는 기본적으로 ServletContextListener 와 HttpSessionListener 인터페이스를 제공
  - ServletcontextListener 
    - 웹 어플리케이션 시작 될 때 와 종료될 때 호출되는 메서드를 소유 
  - HttpSessionListener 
    - 클라이언트가 접속 할 대 와 접속을 해제할 때 호출되는 메서드를 소유 
    - 클라이언트가 정상적으로 접속을 해제할 때 메서드를 호출하기 때문에 클라이언트가 비정상적으로 접속을 해제하면 감지를 못한다
    - 비정상적 접속 종료도 감지하기 위해서는 웹 페이지에서 window 객체의 beforeunload 이벤트가 발생할 때 서버에 요청을 전송하도록 해야한다 
    - 새로 고침을 할 때도 beforeunload 이벤트가 발생하게 되는데 이 경우는 아무일도 하지 않도록 해야 한다 


### serveltContextListener 생성 
