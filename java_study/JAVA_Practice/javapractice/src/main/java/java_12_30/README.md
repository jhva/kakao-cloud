# Cookie & Session)

- http나 https는 상태가 없음

- 클라이언틀가 서버에게 request를 전송하고 response를 받으면 연결이 끊어진다.
    - 이전에 무슨 작업을 했는지 알 수 없음

- 응답이 전송된 이후에도 데이터를 저장해서 상태 유지를 하기 위해서 저장소의 개념이 필요하다

### Cookie

- 클라이언트의 웹 브라우저에 저장한 후 동일한 도메인에서 요청을 전송하면 매번 서버에게 전송되는 데이터

### Session

- Cookie에 발급되는 키를 가지고 서버에 저장해서 사용하는 데이터


- 웹서버와 클라이언트가 정보를 주고받는 중의 하나

- 클라이언트에 파일의 형태로 존재하기 때문에 임의 조작이 가능

### Cookie생성

- 웹서버에서 생성하는것이 일반적인데 자바스크립트로도 생성가능

### Cookie 클래스의 메서드

- 구성을 get하고 set 메서드 제공

- 쿠키를 삭제하는 메서드는 별도로 없고 유효시간을 과거로 되돌리는 방식으로 삭제

### Java에서 쿠키 생성

- 생성 new Cookie(name,value)
- 저장 response.add(Cookie객체)
    - value는 반드시 인코딩 된 상태로 저장해야한다

### 자주 사용되는 인코딩

- UTF 8
    - 현재 가장 많이 사용되는 인코딩 방식
- euc-kr
    - 예전 웹에서 사용하던 인코딩 방식
- ms949,cp949
    - MS Windows 의 기본 인코딩 방식
- iso_latin1,iso-8859-1
    - 서유럽의 라틴어 인코딩 방식
        - 한글이 안됨

### jsp에서 쿠키생성

```java
        //value가 한글이 ㄴ경우는 인코딩을 해주어야한다

        Cookie cookie=new Cookie("name",URLEncoder.encode("군계","UTF-8"));
                response.addCookie(cookie);//쿠키생성이되어서 저장이됨
```

### jsp 에서 쿠키삭제

```html

<head>
    <title>Title</title>
</head>
<body>
<%
Cookie[] cookies = request.getCookies();
for (Cookie cookie : cookies) {
if (cookie.getName().equals("name")) {
Cookie cookie1 = new Cookie("name", "");
cookie1.setMaxAge(0);
response.addCookie(cookie1);
}
}
%>
</body>
</html>

```

### Web Storage

- web storage
    - 브라우저에 저장 가능한 HTML5 API
- 키 와 다른 점
    - 크기가 제한 없음
    - 데이터를 서버로 보내지 않음
    - 자바스크립트 객체를 저장 할 수 있음
- 종류
    - localStorage
        - 삭제하지 않는 이상 소멸되지 않음
    - sessionStorage
        - 세션 과 수명을 같이하는 스토리지
- 속성 과 메서드
    - length
    - key(index)
    - getItem(key)
    - setItem(key,data)
    - removeItem(key)
    - clear()

### 아이디를 로컬에 저장했다가 다음에 다시 접속할 때 출력

> 쿠키를 사용하지않으므로 html이나 jsp 상관엄ㅅ음

```javascript
    let idsave = document.getElementById("idsave");
let id = document.getElementById("id");
let loginform = document.getElementById("loginform")

//처음 로딩될 때 ids 존재 여부 를 확인해서 작업
window.addEventListener("load", (e) => {
    if (typeof localStorage.ids !== 'undefined') {
        ids.value = localStorage.ids;
        idsave.checked = true;
    }
})
//폼의 데이터 전송할 때 idsave 를 확인해서 아이디를 저장
loginform.addEventListener("submit", (e) => {
    if (idsave.checked === true) {
        localStorage.ids = ids.value;
    } else {
        localStorage.clear();

    }
})
```

### Session

> 접속한 브라우저에 대한 정보를 웹 컨테이너에 저장하는 객체

- 웹 컨테이너는 하나의 웹 브라우저에 하나의 session을 할당

### 세션사용

```
jsp: <%@ page session="true"%> 
```

- 위와 같이 작성했을시 session이 만들어지고 false로 설정하면 session이 만들어지지않음
- Spring Legacy Project로 Spring MVC Project를 만들면 index.jsp 에 세션을 사용하지 않는다는 옵션이 설정되어 ㅣㅇㅆ으므로 주의

- servlet
    - request 객체를 가지고 getSession() 을 호출하면 없으면 생성하고 있으면 세션을 리턴한다

### 세션에 데이터 활용

- void setAttribute(String name, Object value)
- Object getAttribute(String name)
- void removeAttribute(String name)

### 메서드

- String getId()
    - id 리턴
- long getCreation Time
    - 세션이 만들어진 시간 리턴
- long getLastAccessedTime()
    - 마지막 사용 시간
- void setMaxInactiveInterval(int seconds)
    - 세션을 사용하지 않았을 때 유지하는 시간으로 초단위
- long getmaxInactiveInterval()
- invalidate()
    - 세션 초기화
- 유효 시간 설정은 web.xml 파일에서도 가능한데 이경우는 분단위

```html

<session-config>
    <session-timeout>시간</session-timeout>
</session-config>
```

### 세션 사용

> redirect 하더라도 정보를 유지하기 위해서 사용하는데 대표적인 경우가 로그인 정보를 저장해서 한번 로그인을 하면 브라우저를 종료할 때 까지 또 는 일정시간 동안
> 조작하지않으면 로그아웃되도록 할 목적으로 이용 함

- 접속자가 아주 많은 경우는 세션이 서버의 메모리 사용하기 때문에 서버의 속도가 나빠질 수 있기 대문에 세션에
  저장해야 하는 정보를 데이터베이스 나 파일에 저장하기도한다

### Filter

> Controller 가 요청을 처리하기 전이나 후에 동작하는 java EE 의 객체

- Spring 에서는 Interceptor 또는 AOP 로 부른다 .
- Filter 인터페이스의 메서드를 구현한 클래스를 생성하고 URL Pattern을 등록하면 패턴에 해당하는
  요청이 왔을 때 동작힘.

### Filter 인터페이스

- doFilter
    - 요청이 왔을 대 호출되는 메서드 인데 3개의 매개변수를 가지고 있는데 request,response,
      chain 이다
    - chain.doFilter(request,response) 를 전송하면 원래 해야하는 동작을 수행한다.
        - 메서드 앞에서 Controller 가 처리하기 전에 수행할 내용을 작성하고 이 메서드 뒤에는 Controller 가 처리한 후에 수행할 동작을 설정한다
        - Controller 가 요청을 처리하기 전에 수행할 내용은 유효성 검사 나 로그 기록중
            - 유효성 검사를 해서 통과하지 못하면 되돌린다거나 다른곳으로 이동시킨다
        - 요청을 처리한 후에 하는 것은 로그 기록이나 데이터의 변환 작업
- init
- destroy
- filter사용
    - 전체 요청에 대해서 인코딩 설정을 할 때 많이 사용

## filter실습 (로컬 로그인 구현 )

### project 를 생성하고 기본 설정


### DB연결 
```java
public class MemberDAO {
    //싱글톤 패턴을 위한 코드 - Spring 에서는 필요 없음

    private MemberDAO() {
    }

    private static MemberDAO dao;

    public static MemberDAO getInstance() {
        if (dao == null) {
            dao = new MemberDAO();
        }
        return dao;
    }

    static {
        //초기화
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("드라이버 로드성공 ");
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    //데이터베이스 사용을 위한 속성
    private Connection connection;
    private PreparedStatement pstmt;
    private ResultSet rs;


    //데이터베이스 연결
    {
        try {
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/javapractice", "root", "12345678");
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }


    public MemberVO login(String mid, String mpw) {
        MemberVO vo = null;
        try {
            //수행할 sql 생성
            String sql = "select * from tbl_members where mid =? and mpw=?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, mid);
            pstmt.setString(2, mpw);
            rs = pstmt.executeQuery();
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
        return vo;
    }
}

```

### 회원 관련 요청을 처리할 메서드를 소유하는 Service 인터페이스를 생성

```java
public interface MemberService {
    //로그인 처리를 위한 메서드
    public MemberDTO login(String mid, String mpw);
}
```

### 3)회원 관련 요청을 처리할 메서드를 구현하는 ServiceImpl 클래스를 생성 - service.MemberServiceImpl

```java
public class MemberServiceImpl implements MemberService {
    //DAO 변수
    private MemberDAO memberDAO;

    private MemberServiceImpl() {
        memberDAO = MemberDAO.getInstance();
    }

    private static MemberService service;

    public static MemberService getInstance() {
        if (service == null) {
            service = new MemberServiceImpl();
        }
        return service;
    }

    @Override
    public MemberDTO login(String mid, String mpw) {
        MemberDTO dto = null;
        MemberVO vo = memberDAO.login(mid, mpw);
        //vo를 dto로 변환
        if (vo != null) {
            dto = new MemberDTO();
            dto.setMid(vo.getMid());
            dto.setMname(vo.getMname());
        }

        return dto;
    }

}
```

### 로그인 컨트롤 servlet

```java
package com.example.servlet.controller;
@WebServlet("/login")
public class LoginController extends HttpServlet {
    private MemberService memberService;
    private static final long serialVersionUID = 1L;

    public LoginController() {
        memberService = MemberServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //webapp 디렉토리의 member 디렉토리의 login.jsp 로 포워딩
        req.getRequestDispatcher("/member/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //로그인 처리

        //파라미터 가져오기
        String mid = req.getParameter("mid");
        String mpw = req.getParameter("mpw");


        //서비스 메서드 호출
        MemberDTO dto = memberService.login(mid, mpw);


        //결과를 가지고 분기
        HttpSession session = req.getSession();

        if (dto == null) {
            session.invalidate();
            //login 페이지로 되돌아가기
            //?붙인이유는 에러실패원인에대해서 알고싶어서 붙인부분이다 
            resp.sendRedirect("login?error=error");
        }else {
            //세션에 저장 
            session.setAttribute("logininfo",dto);
            //메인페이지로 리다이렉트 
            resp.sendRedirect("./");
        }


    }
}

```


### 로그아웃을 처리하는 logoutMethod

```java



@WebServlet("/logout")
public class LogoutController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //세션 초기화
        request.getSession().invalidate();
        response.sendRedirect("./");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

```


### 자동로그인 
> 로그인 할 때 자동로그인 체크 여부를 확인해서 자동 로그인을 체크하면 쿠키에 유일 무일한 값을 배정하고
이값을 데이터베이스에도 기록한 후 다음에 로그인을 시도할 때 쿠키의 값을 읽어서 쿠키의 값이 있는 경우에는 이 쿠키의 값을 가지고 데이터베이스에서
로그인을 수행할 수 있도록 하비다 .

- MemberVO와 MemberDTO 클래스에도 컬럼의 값을 저장할 수 있는 속성을 추가 
- getter와 setter추가 

- uuid 추가 













