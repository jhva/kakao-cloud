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
- /usr/local/Cellar .. tomcat/liber .. 머시기 경로설정 (EditConfiguratiion에서 )
- 그 후 editconfiguration에서 deployment 설정 자기폴더에 맞게 하기 
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