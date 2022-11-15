# JavaScript

## 비동기처리

> 비동기 
- 작업을 수행하다가 쉬는 시간이 생기거나 일정한 시간 이상 작업을 하거나 프로세서를 사용하지 않는 작업(입출력 작업 - 디스크 나 네트워크에서 데이터를 받아오거나 전송하는 작업)을 수행하는 경우 다른 작업을 수행할 수 있도록 하는 방식

- 오랜 시간이 걸리는 작업은 비동기로 처리하는 것이 일반적 

## 1 ) 비동기 작업이 완료 되었거나 중간에 에러가 발생한 경우 처리

- 콜백 - 연속된 경우 콜백 안에 콜백을 계속 삽입하는 구조 
- Promise - 연속된 콜백을 사용하는 경우의 가독성 문제와 콜백 메서드 안에서의 예외 처리 부분을 해결하기 위해 등장 then과 catch 를 연속해서 작성하는 구조 
  - then()
  - then()
  ...
  - catch()

- async/await
    - Promise를 조금 더 간결하게 작성하기 위해서 등장 

```javascript
async 함수(){
    변수 = await(비동기처리)
}
```

### CORS 

> 다른 도메인엥서 자원을 사용할 수 있도록 하는것

### Proxy
> 내부에 데이터를 요청하는 형태로 작성을 하지만 Application Server 에서 외부로 나가서 데이터를 가져와서 전달해주는 방식

### 비동기 기술종류
- ajax: 비동기 적으로 서버의 데이터를 받아오는 자바스크립트 기술
- Fetch API : ajax 의 단점을 보완하기 위해서 등장한 비동기 자바스크립트 기술

  - Promise 나 async/await를 적용해서 Fetch API 를 사용하기 쉽도록 해주는 자바스크립트 라이브러리 중에 axios가 있는데 이 라이브러리를 사용하기도 함 

- Web Push: 클라이언트의 요청이 없어도 서버에서 클라이언트로 데이터를 전송하는 기술 
- Web Socket: 연결형 서비스 


### 데이터 표현 방법

> 서로 다른 방식의 컴퓨터에서 데이터를 주고 받을려면 표준적인 포맷이 있어야한다 .

- <b>서버 입장에서는 이 데이터 표현방식을 데이터를 만들어서 제공하는 것이 중요하고 클라이언트 입장에서는 이 데이터를 파싱해서 출력하는 것이 중요하다.</b>

### 텍스트
 - 일반 문자열 
 - 구분 기호를 가지고 구분할 수 있도록 만든 포맷을 csv라고 한다 .
 - 변하지 않는 데이터를 제공하고자 할 때 주로 이용
 - 용량이 작기 때문에


### XML
 - 태그 형식으로 데이터를 표현
 - 해석을 개발자가 한다는 것이 HTML과 다른 점이다.
 - 설정이나 데이터 전송에 이용을 한다 
 - 최근에는 사용빈도 수가 낮아지고 있는 추세
   - 다른 방식보다 용량이 큼 

### JSON
- 자바스크립트의 데이터 표현법을 이용하는 방식
- XML보다 용량이 작기 때문에 데이터 전송에 많이 이용한다 

### YML
- 이메일 데이터 표현 방식으로 가독성이 좋고 용량이 작기 때문에 최근 설정에 많이 이용된다.
- 구글에서 만든 제품이나 Spring 에서는 YML을 설정에 이용하고있다 .
- 아직 데이터 전송에는 사용 빈도 수가 낮다.


### HTML
- HTML 은 데이터를 표현하는 것이 아니고 화면을 만드는것이다.
- 화면에 보여지기는 하지만 Open API 를 제공하지 않는 경우에 HTML을 가져와서 임의로 해석해서 데이터처럼 사용하는 경우가 있다 .

## 웹 클라이언트에서 웹 서버에게 데이터를 전송하는 방법

### URL에 포함시키는 방법 
> history에 남기 때무에 보완성이 떨어진다. 또한 캐싱이 가능해서 재전송이 가능하고, 데이터의 길이에 제한이있다.


- 요청 방식에 따른 데이터 전달 
  - GET: 데이터 요청할 때 사용하는 방식으로 URL 뒤에 ?를 추가하고 이름 =값 의 형태로 데이터를 전송
    - 데이터 부분을 parameter 라고 하기도 하고 query string 이라고 하기도 한다.
  - POST: 삽입,갱신을 할 때 사용하는 방식으로 HTTP Body에 데이터를 넣어서 전송하는 방식,데이터가 body에 포함되어있기때문에 보안성이 우수하고, 데이터의 길이에 제한도없음. 캐싱이 안되기 때문에 자동 재전송이가능
    - 최근에는 데이터를 삽입할 때 만 사용하고 갱신을 할 때는 PUT같은 방식을 이용하는 것을 권장합니다.


## 7. 여러방식을 이용한 ajax 

### Promise를 이용하는 방식

> 콜백을 이용하는 방법보다 가독성이 좋으나,<br/>일반적으로 콜백안에서 콜백을 호출하는 경우에는 Promise를 사용하는것이 좋다.

- promise형태 
```javascript
  const promiseajax = (method, url, payload) => {
            return new Promise((resolve, reject) => {
                //resolve -  성공시 -then
                //reject - 실패시 catch 
                const req = new XMLHttpRequest();
                req.open(method, url);
                req.setRequestHeader("Content-type", "application/json");

                req.send(JSON.stringify(payload));
                req.addEventListener("load", () => {
                    resolve(req.response);
                })
                req.addEventListener("error", () => {
                    reject(new Error(req.status));
                })
            });
  }
```


### Fetch API
> 요청과 응답 등의 요소를 JavaScript 에서 접근하고 조작할 수 있는 인터페이스를 제공하는 API
- fetch()라는 전역 함수를 이용해서 네트워크의 리소르를 비동기적으로 가져올 수 있음 XMLHttpRequest대신사용

```javascript
 fetch('./data.json')
            .then((response) => response.json())
            .then((data) => alert(data.count))
            .catch((e) => alert(e.message))
            //fetch(요청,URL,옵션)의 형태로 작성하는데 결과는 Promise객체로 리턴되기때문에 then,catch를 사용 
```

### 요청에 성공했을때 전달되는 객체 Response
> Response 속성 - status(상태코드 값),statusText(상태 코드 메시지),ok(성공여부 판단)


- 상태코드
  - 100 번대 :요청 중
  - 200 번대: 요청 처리 성공하고 응답을 전송받음
  - 300 번대: 요청을 처리하고 리다이렉트중
  - 400 번대: 클라이언트 오류(401 - 권한 없음, 404 - 요청 URL이 잘못됨)
  - 500 번대: 서버오류

> Response 메서드 
- 메서드
  - arrayBuffer() : 바이트 배열 - blob와 유사
  - blob(): 파일의 내용
  - formData():폼의 데이터 - FORM의 형태로 응답하는 경우
  - json(): JSON파싱한 결과 - JSON으로 응답하는 경우
  - text(): 결과 데이터 문자열 - 문자열로 응답하는 경우  


# HTML5
- 추가된태그
  - section,artice,aside,header,hgroup,footer,nav: 문서 구조와 관련된 태그
  - figure, audio,video,canvas,source:외부 컨텐츠 사용
  - keygen,output,progress,meter: 폼 관련 태그
  - mark,ruby,time,command,details,datalist:텍스트 관련 태그

- 변경된 태그
  - hr
  - menu
  - small
  - strong

- 태그의 추가된 속성
  - input - 모바일을 위한 속성 과 유효성 검사 관련 속성이 추가

# API 변화 
- 로컬 저장소 : 이전에는 Cookie만 사용
  - Web Storage
  - Web sql
  - Indexed DB

- Drag and Drop API

- GeoLocation : 위치 정보
- Canvas : 그리기 API

- Web Worker: Thread

- Math Ml : 수식을 표현, FIreFox 에서만 적용 가능

- Communication APi
  - ajax level 2
  - Server Sent Events(Web push)
  - Web Socket API

### 권장 사항 
> Cookie보다는 로컬 스토리지를 이용하는것을 권장
  - Cookie는 서버에게 요청할 때 마다 전송되고 문자열만 저장 가능
  - Local Storage 는 자바스크립트 객체를 저장할수있고 필요할 떄 서버에게 전송 가능
  - Cache 보다는 로컬 데이터베이스를 사용하는 것을 권장 
  - 부담을 많이 주는 작업은 웹 워커 사용을 권장


### 기본적인 문서 구조 
- DOCTYPE - DTD
> <!DOCTYPE Html>

### Mark up

- 추가된 섹션 요소
  - header,section ,article ,aside,nav,footer
  - div와 동일한 역할을 수행
    - 명확한 의미 전달을 위해서 추가
    - 브라우저의 내용을 인간이 아닌 robot이 읽었을때 명확하게 의미를 전달하기 위해서

  
### figure,figcaption
> 이미지 나 그래프 또는 예제 코드 등을 작성할 때 문서 안에 삽입해서 의미를 전달하기 위해서 사용
```html
<figure>
  <img src="이미지 파일의 경로"/>
  <figcaption> 그림에 대한 설명</figcaption>
  </figure>
```
- 이미지 아래에 그림에 대한 설명이 추가된다
  - 음성 브라우저에서는 그림에 대한 설명을 읽어준다.


## 멀티미디어 
> 외부 플러그 인 없이 동여상을 제어 할 수 있음 .
- video
  - 비디오 재생을 위한 태그 
  - javascript 객체로 추가됨 
- 브라우저 별로 코덱이 다르기 때문에 모든 동영상 파일이 전부 재생되는것은 아님


### SVG
- XML을 이용해서 백터 그래픽을 표시하는 API - 웹 표준
- Canvas는 Pixel 단위로 그림을 그리지만 SVG 벡터 이미지 


## FORM
- 기능 변화 
  - 여러가지 타입 추가 
  - 유효성 검사 기능 추가 
  - 진행 상황을 표시해주는 progress와 meter가 추가 됨.


## input type 추가 :모바일 을 위한 속성
- input type
  - text, password,checkbox,radio,file,button,image,submit,reset,hidden
- 추가된 type
  - search, tel,url ,email,number,range,color,date,month,week,time,datetime-local


### IP알아내는 명령 

> 다른 컴퓨터에서 접속이 가능하도록 할 때는 방화벽을 해제

- Window: ipconfig
- mac: wifi에서 확인 linux는 ifconig

- 포트포워딩 : 사설 ip랑 공용 ip랑 연결시켜줄수있는것 
  - 예외로 내가 찾아봄 kt https://m.blog.naver.com/zetezz/221224911338


## Jquery를 왜 사용했을까 ? 
- HTML5는 브라우저 별로 지원 범위가 다르다.
- HTML5 에서 추가된 기능을 사용하게 되면 브라우저 별로 다르게 설정을 해야 동일한 컨텐츠를 사용할 수 있다.
  - jQuery는 이 부분(cross browsing) 을 쉽게 해주는 자바스크립트 라이브러리 이다.
- 국내에서는 IE 비중이 많이 낮아졌고 IE 대신에 나온 Edge는 HTML5를 지원하기 때문에 최근에는 jQuery사용을 잘 하지 않는 추세이다.
- jQuery단점 중의 하나가 렌더링 속도가 느리다는 것이다.


## progress 와 meter
> 진행율을 보여주고자 할 때 사용하는 요소 : javascript를 이용해서 제어함.

- 기본적으로 max value는 100으로 설정되어 있고 value속성을 이용해서 진행율 표시.
  - 최대값이나 최소값을 변경하고자 하는 경우는 min과 max속성의 값을 변경하면 된다.


### Memory Leak
- <strong>용도가 끝났는데도 작업을 계속 수행하고 있어서 메모리 낭비가 발생하는것</strong>
  - 입출력 작업을 수행하거나 타이머 같은 자원을 사용할 때 주로 발생
  - 타이머는 사용이 끝나면 해제해주어야 하고 입출력 작업은 스트림으로 끝나여야한다.

### 추가된 속성
- file: multiple 속성이 추가되서 속성이 설정하면 한 번에 여러 개의 파일을 선택할 수 있음
- autocomplete 속성: 자동 완성 기능 사용 여부로 on 과 off로 설정
- list 속성 과 datalist 속성을 이용하면 입력값을 선택 핤 ㅜ있도록 할 수 있다.

- placeholder : 입력할 때 설명을 위한 텍스트 설정
- autofocus : 첫번째 포커스 설정


### 유효성 검사 (input)

- required : 필수입력
- maxlength : 최대 길이 
- max,min : 최소 최대 값 - 숫자나 날짜에 사용
- pattern: 정규 표현식 설정 가능.


## Web Server
- Web Client 에서 Web Server 전송하기 전에 유효성 검사를 해야 하고 Application Server 가 Web Client 로 부터 Data 를 전송받으면 유효성 검사를 해야 한다 .
- Web CLient 에서 유효성 검사를 수행해서 조건에 맞지 않는 경우 전송을 하지  않으면 Traffic을 아낄수 있기 때문에 수행해야하고 Application Server 에서 다시 하는 이유는 데이터 전송 중에 변형이 이루어졌을지 모르기 때문에 .