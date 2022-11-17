# Nodejs

### module
- 독자적으로 실행가능한 단위 
    - 함수
    - 클래스 

### Package
- 1개 이상의 파일로 구성된 배포단위

### Process
- 실행 중인 프로그램

### Thread
- 독자적으로 실행할 수 없는 Process 작업 단위 

### NPM(Node Package Manage)
> 노드에서의 패키지 매니저인데 지금은 거의 모든 자바스크립트 라이브러리들이<br/>
저장소에 있기 때문에 자바스크립트 라이브러리들은 거의 모두 NPM을 이용해서 사용

- package.json
    - 노드에서 패키지 관리를 위한설정 파일
    - java 에서의 build.gradle 이나 pom.xml의 역할을 수행
    - 패키지를 설치하게 되면 패키지에 대한 정보가 전부 작성됨. 

### 패키지 설치 
- npm install 패키지 이름 나열 (나열 할때는 공백으로 구분)
    - 개발 과정에서만 사용하고 배포할때는 제외하고자하는경우에는 패키지 이름 앞에 --save-dev 를 추가하면 된다
    - 모든 프로젝트에서 사용할수 있도록 하기위해서는 global 모드로 설치하는데 <br/>
    패키지 이름 앞에 -g 를 추가하면 된다 
        - mac이나 linux에서 global 로 설치할 때는 맨앞에 관리자 모드를 의미하는 sudo 를 추가해야한다
            - sudo npm install --location =global 패키지이름의 형태롤 설치해야 한다.
- 설치 된 패키지는 프로젝트 내의 node_modules 라는 디렉토리에 저장이 된다
- 여러개 설치할 때
    - npm install morgan cookie-parser express-session

- nodemon 패키지 설치 : nodemon 패키지는 소스코드를 수정하면 자동으로 재실행되게<br/> 해주는 패키지로 개발 과정에서만사용
- rimraf 패키지 설치 : rimraf 패키지는 윈도우에서 터미널에서 rm  명령을 사용하기 위해서 설치 
    - npm install --location=g rimraf

### 패키지 재설치 
- 모든 패키지는 node_modules 에 다운로드 받아서 저장하게 되는데 <br/> 배포를 하거나 코드를 가지고 갈때 node_module를 포함시키면
크기가 너무커지는데 이런 경우에는 node_module 는 복사하지 않고 package.json 만 가져간후 새로운곳에서<br/>
npm init 명령만 다시 수행하면 패키지들이 전부 설치 된당.

### Express 
> 웹 애플리케이션 서버를 만들어주는 패키지  (npm install express)
```
npm install express 서버를 만들어주는 패키지 
```

- Web Server :다른곳에서 URL을 이용해서 사용할 수 있도록 해주는 서버
    
- Web Application Server : URL 과 작업을 매핑해서 URL 을 호출하면 작업을 수행하도록 해주는 서버

- Application Server: 작업을 해주는 서버 


### 패키지 버전 
> 3자리로 구성
- Major Version 
    - <b>변경된 경우엔</b> 하위 버전과 호환이 안될 수 있음
- Minor Version
    - <b>기능이 변경된 경우</b>
- Patch
    - <b>오류를 수정했을 때 변경</b>


### npm 관련 명령어
- npm uninstall 삭제할패키지이름
    - 패키지 삭제
- npm search  검색어 :패키지 검색
- npm publish: 패키지 배포
- npm unpublish: 배포 취소 
    - 배포 한 후 24시간 이내에 해야함


### node 를 이용한 웹 서버만들기

> express 모듈을 이용하는 경우가 많음 

- web3.0
    - Semantic Web 개념 등장: 로봇이 정보 자원의 뜻을 이해하고 논리적 추론까지 가능<br/>
    명확한 의미 전달이 중요 Rest API 속도의 변화 
    
- WOA( Web Oriented Architecture)
    - 사용자의 요구 사항 변경 : 여러 디바이스를 사용하고 디바이스 끼리 <br/>끊어짐이 없는 서비스 요구가 증대되면서 이를구현하기 위한 방법으로 웹 기술이 각광받음

    - 기술의 변화 : 인프라 측면에서는 클라우드 나 가상화 기술이 각광을 받고,<br/>
    소프트웨어 측면에서는 WOA(전체 시스템 아키텍쳐를 웹을 중심으로 설계)
- Framework 를 이용한 애플리케이션 개발

### 웹 서비스 구축 방식
- 정적 웹 서비스 
    - 요청이 오면 요청에 해당하는 HTML페이지를 찾아서 출력하는 방식
- CGI(Common Gateway Interface)
    - 클라이언트의 요청이 오면 서버가 작업을 수행해서<br/> 결과를 전송하거나 화면을 전송하는 방식
    - perl 이 시초
    - 이 방식은 사용자의 요청을 별도의 프로세스로 만들어서 처리
        - 하나의 요청을 전부 처리하기 전까지는 다른 요청을 처리할 수 없음
    - Perl 이나 ASP 가 이런 형태로 동작

### Application Server 방식
- 사용자의 요청을 Thread 를 만들어서 처리 
- 여러 사용자의 요청을 한꺼번에 처리하는 것 처럼 처리
    - Java -> Servlet(JSP) -> Spring Framework
    - C# -> ASP.net 
    - Javascript -> node.js framework
    - PHP -> laravel Framework
    - Python -> Flask 나 Django Framework
    - Ruby -> Rails

### 웹 프로그래밍의 구조 
- 웹 브라우저 <-> 웹 서버 <-> 애플리케이션 서버 <->데이터 저장소
- 언어 나 프레임워크 애플리케이션 서버를 만들기 위한 기술
- Serverless
    - 서버가 없는 것이 아니고 서버를 직접 구현 할 필요가 없는 것
- 웹 브라우저에서 서버에게 요청하는 것을 request 라고 하고 <br/>
서버가 웹 브라우저에게 대답을 하는 것을 response라고 한다.

### http 모듈
- 내장 모듈이므로 별도로 설치할 필요없음.

```javascript
* 서버 생성

http.모듈.createServer((request,response)=>{
    내용
})

*서버 실행

javascript
서버객체.listen(포트번호,아이피주소); //아이피주소는 현재 컴퓨터에 여러 개의ip가 존재하는경우 작성


* 서버 종료
서버객체.close();
```
### 서버에 발생하는 이벤트
- request: 클라이언트의 요청이있을때 
- connection : 클라이언트가 접속했을 때 
- clientError : 클라이언트 오류가 발생했을 때 

### request 객체
- url : 요청한 url
- method :요청 방식(GET,POST,PUT,PATCH,DELETE,OPTION)

### 웹 서버 만들고 직접 응답을 생성하기
- js 파일을 추가 후 작성하고 실행
- 포트번호는 일반적으로 1024번까지 예약되어 있으므로 사용하지않음
- 1521,3306,27017 번은 데이터베이스가 사용
- 8080은 톰캣 같은 WebContainer 가 사용
- 80을 사용하게 되면 http 의 경우 포트번호 생략 가능
- 443을 사용하게되면 https의 경우 포트번호 생략 가능

```javascript
// http모듈

//모듈 가져오기 
const http = require('http');
const PORT= 8000;
//서버 생성
http.createServer((req, res) => {
    //응답 생성
    res.writeHead(200,{'Content-Type': 'text/html;charset=utf-8'});
    res.write('<h1>연결완료 처음만든 웹서버</h1>')
    res.end('<p>http 모듈을 사용한 연동</p>')
}).listen(PORT,()=>{
    console.log(`${PORT} 연결 완룡`)
})
```

- requrest 객체
    - url : 클라이언트의 요청 경로
        - 요청 경로를 만들 때는 이해하기 쉬운 경로를 만들어야함<br/> _사용은 하지 않는 것을 권장
    - method : 요청방식
        - GET :서버 자원을 가져올 때 사용(조회 -READ)
        - POST : 서버 자원을 등록하고자 할때 사용
        - PUT: 서버의 자원을 수정하고자 할 때 사용(수정- UPDATE)
        - PATCH : 서버 자원의 일부분만 수정하고자 할 때 사용
            - 수정-UPDATE => 권장하지 않는다고함 
        - DELETE: 서버 자원을 삭제하고자 할때 사용
        - OPTIONS: 요청을 하기 전에 통신 옵션을 설명하기 위해서

- REST(Representational State Transfer) API
    - 서버의 자원을 정의하고 자원에 대한 URL을 지정하는 방법
    - URL 과 Method 만으로 작업을 예측할 수 있도록 하는것
        - URL은 /member 이고 method는 POST라면 회원가입
    - 클라이언트 종류에 상관없이 동일한 작업은 동일한 URL로처리
        - 클라이언트 어플리케이션 서버 애플리케이션 과 분리해서 <br/>구현하고 서버는 클라이언트의 뷰를 만들지 않고 데이터를 전송.
        - 클라이언트 애플리케이션 과 서버 애플리케이션을 하나의 프로젝트로 구현하면 모바일 기기의 Native Application 과 Web Browser의 요청에 응답하는 부분을 동일한 URL로 처리 할 수 없다.
        - 아이폰에서 회원가입은 URL이고 /member 이고 Method는 
        POST 라면 안드로이드에서 동일해야 하고 Web Browser에서도 동일한 URL로 처리되어야한다.
            - 위와 같이 만들어진 서버를 RESTful 하다라고함

- axios 라이브러리 
    - 브라우저나 Node.js에서 Promise API를 이용해서<br/>HTTP 비동기 통신을 할 수 있도록 해주는 API
    - 자바스크립트의 fetch api 를 사용하기 쉽도록 해주는 라이브러리

    - XSRF(Cross-Site Request Forgery): 쿠키만으로 인증하는 서비스의 취약점을 이용해서 <br/> 사용자가 모르게 서비스에 특정명령을 요청하는것
        - 브라우저에서 삽입하겠다고 요청을 하기 위해서 폼을 조회했는데 이 폼의 URL을 복사해서 다른 기기에서 데이터를 삽입하는 작업을 수행하는 것이 대표적 
- Promise 를 이용한 사용

```javascript
const axios = require('axios')
axios.요청메서드(url)
     .then((res)=>{
        //가져오는데 성공했을 수행할 내용
        //res는 가져온 데이터를 파싱한 결과
     })
     .catch((err)=>{
        //에러 발생했을경우
        //err 는 에러에대한 내용을 저장한 객체.
     })
     ...
     ... 
     .then(()=>{
        //성공과 실패 여부에 상관없이 수행할 내용
     })
```

## 쿠키(Cookie)와 Session
- Http나 Https는 상태가 없음
    - 상태가 없다라는 말은 <b> 클라이언트가 요청을 할 때 일시적으로<br/> 연결이 된 후 서버가 응답을 하고 나면 연결이 해제되버리기 <br/>때문에 다음 연결이 될 때는 이전에 어떤 상태였는지 알 수 없음</b>
    - 클라이언트와 서버가 이전에 어떤 상태 였는지 알수 있도록 하기위해서 Cookie와 Session의 개념을 사용

### 쿠키
> 클라이언트에 저장해서 클라이언트가 서버에게 요청할 때 마다 전송되는 객체

- 쿠키는 http 나 https 요청의 헤더에 저장하고 이름 과 값의 구조

```javascript
//node의 http모듈
response.writeHead(코드,{'set-Cookie':'쿠키이름=값'})의 형태로
//쿠키가 여러 개이면 ;을 하고 추가
```
- 쿠키의 옵션 
    - expires(만료시간) : 날짜 (단위)
    - Max-age(만료시간) : 초 (단위) 
    - Domain(도메인): Path(URL),Secure(HTTPS 인경우만 전송),HttpOnly(잦바스크립트에서 수정을 못하도록 하는 경우 사용)

### 세션

> 클라이언트의 정보를 서버에 저장하는 기술
- 사용하는이유 : 클라이언트에 저장하게 되면 노출이되고 이를 수정할 수 있기 때문에 보안에 취약하므로 <br/>노출이 되면 안되는 데이터를 서버에 저장하고 클라이언트에서는 이정보를 구별할 수 있는 세션 키 만 저장
    - 클라이언트 와 서버가 동일한 도메인 경우만 가능함.<br/>
    쿠키를 이용하면 클라이언트 와 서버의 도메인이 달라도 가능
    - 주로 로그인 정보를 저장하는데 많이 이용을함.
        - JWT(Json Web Token)를 이용하는것을 권장함.


### Cookie의 대안
- Web Storage, Web SQL, Indexed DB 같은 HTML5 API를 이용하기도함.

### https 모듈
- http 서버를 https 로 변경하기 위한 모듈
    - https는 암호화를 위한 인증서가 필요  
        - 무료나 유료로 인증서를 발급받아야 이 모듈을 사용하는것이 가능하다
- https 는 데이터가 암호화되서 전송되기 때문에 중간에 가로채도 변경을 할 수 없다.

- https를 사용하면 데이터 전송 간에는 암호화를 할 필요가 없는데<br/> 개발자들은 혹시 모르니 해야 한다고 함 .
    - 최근 브라우저에서는 https 서버가 아니면 접속을 못하도록 하기도함.
    - 스마트폰에서는 https 에 접속할려면 별도의 설정을 추가해야함.
- https모듈의 속도를 개선한 http2 모듈도 있다.

### cluster (클러스터)

> CPU 코어를 전부 사용할 수 있도록 해주는 모듈
- 여러 개의 연산을 동시에 수행할 수 있도록 해주는 모듈
    - 직접 서버 설정을 한다면 사용을 하지만 최근처럼 Cloud를 사용하는 경우엔 직접 설정하지않는다.

### Express 모듈을 이용한 웹 서버 생성 및 실행

- express 모듈
    - 내장 모듈이 아님 : 설치가 필요
    - http 모듈을 가지고 웹 서버를 만들 수 있는데 가독성이 떨어지고 확장성이 떨어짐.
    - http 모듈 보다는 코드 관리가 용이하고 편의성이 높은 모듈


### Express 패키지 설치 
- express(웹 서버 제작을 위한 모듈), nodemon(소스 코드를 수정하면 자동으로 재시작되도록 해주는 모듈)
```
npm install express
npm install --save-dev nodemon
```

### pcakge.json 파일의 설정을 수정
- package.json main 시작파일의 이름을 설정
- scripts 속성에 "start":"nodemon app"이라는 태그를 추가

```
 "start":"nodemon app", 

main 파일이름이 app 이면 start 맨끝에도 app
index 라하면 nodemon index
```

### Express web server 의 기본틀 
```javascript
const express = require('express') //모듈 가져오기

const app = express(); //웹 서버 인스턴스 생성

// 사용자의 요청을 처리하는 코드 작성 

app.set('port',포트번호); //포트 설정
app.listen(app.get('port'),()=>{
    //서버가 정상적으로 구동되었을 때 수행할 내용 
    //일반적으로는 콘솔에 메시지를 출력
})

```

### 사용자의 요청을 처리하는 함수의 종류
> 요청 방식에 맞게 함수를 적용
- app.get
- app.post
- app.delete
- app.put
- app.patch
- app.options


### 함수의 매개변수

- 첫번째는 url
- 두번째는 2개의 매개변수를 갖는 콜백 함수인데, 콜백 함수로 이 함수가 url 요청이 왔을때 호출.
    - 첫번째 매개변수는 사용자의 요청 객체(request)
    - 두번째 매개변수는 사용자에게 응답을 하기 위한 (response)

### 사용자에게 응답

- 직접 작성: response.send(문자열)
    - <b>html파일을 출력하고자할땐 response.sendFile('html파일경로')</b>

### 웹 서버 만들기 
- 출력할 html 파일을 1개 생성
- https://p-iknow.netlify.app/node-js/path-moudle/ 참고하자 __dirname

```javascript
const express = require('express') //모듈 가져오기

const path = require('path');//현재 디렉토리에 대한 절대 경로를 알아내기 위한 내장 모듈

const app = express(); //웹 서버 인스턴스 생성

// 사용자의 요청을 처리하는 코드 작성 

app.set('port', 8000); //포트 설정


app.get('/',(req,res)=>{
    res.sendFile(path.join(__dirname,'/mypage.html'));
    //__dirname -> 현재 디렉토리에 있는 main.html을 출력
})

app.listen(app.get('port'), () => {
    console.log(app.get('port')+`이 연결되었습니다`)
    //서버가 정상적으로 구동되었을 때 수행할 내용 
    //일반적으로는 콘솔에 메시지를 출력
})
```

### 요청 객체 - request 객체
> 클라이언트의 요청 정보를 저장하고 있는 객체
    - request.app : app 객체에 접근

- request.body : body-parser 미들웨어가 만드는 요청의 본문을 해석한 객체
    - post 나 put요청이 왔을 때 파라미터 읽기
- request.cookies: 쿠키 정보를 가지는 객체
- request.ip : 요청을 전송한 클라이언트의 ip 정보 
    - <b>ip를 알면 접속한 국가를 알 수 있다 </b>
    - 어떤 컴퓨터에서 접속했는지 알 수 있음 .

- request.params : 라우터 매개변수에 담긴 정보
- request.query: query string - get이나 delete 요청에서 파라미터 읽기
- reqeust.get(헤더이름) : 헤더의 값 가져오기, 인증에서 많이 사용 
    - 최근에는 API 사용 권한을 토큰을 이용해서 발급하고 토큰의 값을<br/>
    헤더에 저장해서 전송하도록 만드는 경우가 많다.
- request.signedCookies: 서명된 쿠키 정보
- request.session: 세션 객체

```javascript
app.get('/index',(req,res)=>{
    console.log(req.ip);//결과->   ::1  이건 루프백이라고 부르고 자기자신의 ip라는걸호출
    console.log(req.query); 
    // http://localhost:8000/index?name=adam
    // 이라고했을경우 //  { name: 'adam' }
})

```


### 응답 객체 - response 객체

- response.cookie(키,값,옵션) : 쿠키생성
- response.clearCookie(키,값,옵션): 쿠키 삭제
- response.end() :데이터 없이 응답을 보냄 
- response.json(JSON 문자열) : JSON 형식으로 보냄
- response.redirect(URL): 리다이렉트할 URL
- response.render(뷰이름,데이터) : 템플릿 엔진을 이용해서 서버의 데이터를 html 에 출력하고자 할때 사용 
    - 이를 서버렌더링이라고 한다.
- response.send(메시지) : 메시지를 화면에 출력
- response.sendFile(파일 경로): 파일을 읽어서 화면에 출력
- response.set(헤더이름, 값) : 헤더를 설정
- response.status(코드) : 응답 코드 설정


### Forwarding
> 현재 흐름을 유지한채 이동한 것으로 URL이 변경되지 않는다.node에서 html을 출력할때 기본
- forwarding은 조회 작업에 사용하고 그 이외의 작업은 redirect를 해야한다.
- 새로고침을 했을때 작업이 다시 이루어진다.

### Redirect
> 현재 흐름을 끊어 버리고 이동하는 것으로 URL이 변경된다.
- 새로고침 할 때 결과만 새로 고침이 된다.


# Forwarding 와 Redirect
> 서버에서 화면을 만들 때만 의미를 갖음, API server를 만드는 경우에는 해당 사항 없음
- 새로 고침을 가지고 판단하는 것이 좋다
    - 대부분의 경우 조회는 forwarding을 해야 하지만 트래픽을 줄이고자 할때는 조회도 redirect하기도한다. 
        - 삽입, 삭제, 갱신은 반드시 redirect를해줘라

### dotenv(.env)
> .env 파일을 읽어서 process.env 로 생성해주는 패키지입니당
- .env 파일에 작성한 내용을 소스 코드에서 process.env 객체를 이용해서 사용가능하도록 하는 패키지

- 프로젝트를 만들게 되면 프로젝트 내의 파일은 2가지로 분류된다
    - 하나는 소스 코드고 다른하나는 소스코드 이외의 자원이다
    - 자원을 읽어서 사용하는 것이 소스 코드를 실행을 위한 코드이다.

- 코드가 실행되는 과정
    - 1.소스코드 
    - 2.컴파일해서 운영체제나 Virtual Machine이 이해할 수 있는 코드로 변경(이상황에서 에러가 발생하면 문법오류)
    - 3.컴파일이 끝나고 나면 번역된 파일들을 실행할 수 있도록 작업을 한다. 이 작업을 build라고 함.
    (이 과정에서 에러가 발생하면 구조를 잘못만든것)
    - 실행
    - 실제 배포를 할 때는 소스 코드를 배포하는 것이 아니고 빌드 된 내용을 배포한다.
    - 소스 코드를 수정하면 컴파일 과 빌드를 다시 해야한다.
    - 소스 코드를 되도록 수정하지 않고 업데이트 하거나 환경을 변경하는 것이 가능하도록 
    프로그램을 제작하는 것이 바람직하다.
- 환경의 변화(개발 환경, 운영 환경, 테스트 환경) 
    - 변경되는 설정을 별도의 텍스트 파일에 만들어두면 환경이 변경될 때 텍스트 파일의 내용만
    변경하면 되기 때문에 컴파일이나 빌드를 다시 할 필요가 없어진다.

## Middle Ware
> <b>사용자의 요청이 발생하고 서버가 요청을 처리하고 응답을 전송하는 시스템에서<br/> 요청을 처리하기 전이나 후에 동작할 내용을 수행하는 객체를 Middle Ware라고 부른다 </b>

- 일반적으로 요청을 처리하기 전에 수행하는 일은 필터링
    - 필터링을 할 때는 유효성 검사 작업 과 로그인 확인 작업이 대표적이다.

- 요청을 처리한 후 수행하는 일은 변환이다.(래핑한다라고 부름)

- node에서는 app.use(미들웨어) 형태로 장착을함.

```javascript
app.use(미들웨어) : 모든 요청에서 미들웨어가 동작
app.use(url,미들웨어): url에서만 미들웨어가 동작
```
- 현재 미들웨어에서 다음 미들웨어로 넘어가는 함수 
    - next()

### morgan
> 로그를 기록해주는 미들웨어
- morgan(format,options) 로 사용
    - format 
        - dev - 개발용 ,배포를 할땐 모두 삭제됨.
        - tiny 
        - short
        - common
        - combined
    - options
        - immediate: response에 기록하는 것이 아니고 request 에서 기록(에러가 발생해도 기록됨)
        - skip :로깅을 스킵하기 위한 조건을 설정
        - stream () : 기본적으로 로그는 화면에 출력되지만 파일에 출력하고자 할 때 사용
- 로그 파일을 생성해주는 패키지 

```
npm install file-stream-rotator
```
- 위 패키지를 이용하면 주기적으로 파일을 생성해서 로그를 기록하는 것이 가능

- 로그형식 
    - HTTP 요청방식 요청 URL 상태코드, 응답속도 트래픽
        - 조금 ㅡ더 자세한 로그를 원하는 경우에는 winston 패키지를 사용

## 날짜 별로 로그 파일에 로그를 기록
- 패키지 설치 : morgan, file-stream-rotator
- app.js 파일에 추가

```javascript
//일단위 로그 기록을 위한 설정
const morgan = require('morgan');
const FileStreamRotator = require('file-stream-rotator');

// 내장 모듈
const fs = require('fs');

//로그 파일을 저장할 디렉토리 생성
const logDirectory = path.join(__dirname, 'log')
//디렉토리 존쟁 ㅕ부를 확인하고 없으면 생성
//항상 or 는 앞에있는게 true면 하고 false면 뒤에껄한다.
fs.existsSync(logDirectory) || fs.mkdirSync(logDirectory)

//일단위 로그 기록 설정
const acessLogStream = FileStreamRotator.getStream({
    date_format: 'YYYY-MM-DD',
    filename: path.join(logDirectory, 'access-%DATE%.log'),
    frequency: 'daily', //일별로 만들어주는것 일별 주별 월별 시간별로 다만들수있다고함.
    verbose: true
})

app.use(morgan('combined', { stream: acessLogStream }));
```