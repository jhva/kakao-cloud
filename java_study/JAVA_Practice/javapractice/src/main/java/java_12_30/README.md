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