### Node 내장 객체
> node 가 제공하는 객체 
- global
    - 노드의 전역 객체
    - 이 객체는 노드 프로그램 전체에서 1개만 생성
    - 사용하는 것을 권장하지는 않지만 모든 곳에서 사용해야 할 데이터가 있는 경우 사용 
        - 예를 들면 <b>현재 접속자 수나 대기자 수 , 로고</b>등은 모든 사용자에게 동일하게 보여진다.
        - 이런 데이터를 global을 이용해서 사용한다
            - global.countUser=10
### console 객체 
> 현재 보여지는 터미널 화면
- console.log(메시지): 메시지 로깅
- console.error(메시지): 에러 형태로 로깅
- console.trace(메시지) : 호출 스택 로깅 - 함수 나 메서드를 호출하는 순서를 출력(역순)
    - 호출하는 메서드의 순서를 확인하고자 할 때 사용
- console.table(배열): 테이블 형태로 출력
- console.time(메시지) & console.timeEnd(메시지) : 동일한 메시지를 사용하면 2개 호출사이의 시간을 출력
- console.dir(객체,옵션) : 객체를 로깅할 때 옵션을 설정하는 것이 가능
    - 로직이 복잡해지거나 여러 모듈을 거쳐가면서 수행되는 코드가 있다면<br/> 중간 중간 로그를 출력해서 확인하는것이좋다


### 경로 관련 속성

- __filename : 현재 파일의 경로

- __dirname : 현재 디렉토리의 경로
    - 최근에 등장하는 언어나 프레임워크는 _ 를 이용해서 <br/>
    만드는 경우가 많기 때문에 이제는 사용자 정의 이름을 만들때 _ 로 시작하는 이름을 사용하는 것을 추천하지 않는다.

### module.exports 
> 모듈의 내용을 다른곳에서 사용할 수 있도록 하고자 할 때 사용하는 속성

- module.exports = 내보낼내용;
```javascript
exports.내보내고자하는이름 = 내용;
//위와 같이해도 동일한내용임
```

### this
- <b> 함수나 클래스 외부에서는 module.exports</b>
- <b> 함수안에서는 global</b>
- <b> 클래스의 메서드 안에서는 인스턴스</b>

### require 함수 
> import 도 외부 모듈을 가져오는데 사용할 수 있지만 require는 caching을 한다.
- <b> 외부 모듈의 내용을 가져올 때 </b> 사용하는 함수 
    - 한 번 사용한 모듈을 메모리에 적재해두었다가 처음에 require를 하게 되면 캐싱된 모듈을 가져온다.
        -  .js 는 생략가능

### process 객체 
> 노드 프로세스에 대한 <b>정보</b>를 가지는 객체

- 노드 버전이나 CPU 사용량 등을 확인할 수 있는 속성과 멤서드를 제공
- process.nextTick(콜백 함수) : 다른 콜백 함수보다 우선해서 처리 
    - Promise.resolve.then() 으로 작성가능
    - process.exit() :노드 프로세스 종료


### OS 객체 
> 운영체제에 대한 정보를 가진 객체
- 운영체제에 대한 종류나 CPU 또는 메모리 사용량 등을 확인함
- 실제 서비스를 운영할때는 메모리 사용량을 확인하는것이 좋다
    - 서버를 운영할 때 메모리 사용량을 주기적으로 모니터링해서 물리적으로 늘릴 것인지 여부를 판단

### path객체
- 디렉토리와 파일의 경로를 쉽게 조작하도록 도와주는 모듈
- 속성과 함수
    - path.sep: 경로 구분자 (windows 딸러표시 나머지는 /) 
        - 서버를 만들때는 운영체제를 확정짓고 만들기때문에 잘 사용하지 않는다 .<br/>
        일반 애플리케이션을 만들 때는 경로 지정 시 이속성을 이용해서 지정해야 윈도우나 Mac에서 코드 수정없이 <br/>
        동작하는 애플리케이션을 만들 수 있다. 
        - OS 모듈을 이용해서 운영체제를 확인해서 별도로 코딩을 하는 방법도 있다 .
    - path.dirname(경로) : 경로에 해당하는 파일이 위치하는 디렉토리 
    - path.extname(경로) : 경로에 해당하는 파일의 확장자
    - path.join(경로를 나열)
```javascript
const path =require('path');

//현재 디렉토리를 확인
console.log(__dirname);
//현재 디렉토리의 public 경로 확인
console.log(path.join(__dirname,"public"))
// 현재 작업 디렉토리와 프로젝트 내의 public 디렉토리 경로 확인
```
> 소스코드 -> 컴파일(번역- 소스코드를 이해할 수 있는 코드로 변경, 문법 체크)-> Build(실행이 가능한 코드로 변경)
- <b>소스코드가 변경이 되면 컴파일 -> 빌드를 다시 해야한다 .</b>
    - 빌드를 하다보면 예기치 않은 문제가 발생할 수 있음


### url 모듈
> url 과 관련된 모듈

- parse 함수 : url을 분해
- format 함수 : 분해된 url을 하나로 복원


### searchParams 모듈 
- query string
    - 파라미터라고 하는데 클라이언트가 서버에게 get 방식으로 요청을 할 때 전송하는 데이터 위한 모듈
- getAll(key) : key 에 해당하는 모든 데이터를 가져온다. 체크 박스나 파일의 경우는 다중선택이 가능
- get(key): key에 해당하는 데이터 1개만 가져온다. 체크박스나 파일이 아니면 하나의 값만 전달

- 파라미터를 추가하는 함수
    - append(키,값): 추가
    - set(키,값): 수정


### 암호화 
> crypto 모듈을 암호화에 이용

- 1.암호화 방식
    - 단방향 암호화 
        - 암호화는 가능하지만 암호화된 문장을 이용해서 복호화하는것은 불가능
        - 원본 데이터 와의 비교는 가능 (동일한 문장을 암호화하면 동일한 결과가 만들어지기때문에).
        - 해시기법 (문자열을 고정된 길이의 다른 문자열로 만드는 방식)을 주로 이용
- <b>알고리즘 종류 </b>

> md5와 sha1는 취약점이 발견되서 거의 사용하지 않고 안드로이드에서 sha1을 가끔사용.
    - md5
    - sha1
    - sha256
    - sha512 
- 블록체인에서는 sha256을 사용하다가 sha512로 변환
- <b>비밀번호 저장이나 블록 체인에서는 단방향 암호화를 주로 이용</b>

### 양방향 암호화
- 암호화 할때 키를 사용해서 암호화하는 방식으로 복호화가 가능
    - 암호화 할 때 사용한 키 와 복호화 할 때 사용한 키가 같아야만 복호화가 가능
        -<b> 동일한 데이터를 암호화 했을 때 암호화 된 결과가 다를 수도 있음.</b>
            -<b> 이러한 데이터는 비교연산을 잘 하지 않고 복원해서 사용하는 경우가 대부분이다.</b>

## Node Crypto

- createHash(알고리즘) : 사용할 알고리즘 설정
- update(문자열) : 변환할 문자열을 설정
- digest(인코딩 방식): 인코딩할 알고리즘을 설정하는데 주로 base64를 많이사용 
- createCipheriv(알고리즘,키,초기화벡터) : 양방향 암호화 객체 생성
    - 암호화 객체.update(암호화 할 문자열 ,문자 인코딩 방식(utf-8), 출력 인코딩 방식(base64)):문자열이 리턴
    - 암호화 객체.final(출력 인코딩 방식): 암호화완료

- createDeccipheriv(알고리즘,키,초기화 벡터) : 양방향 복호화 객체 생성
    - 암호화 할 때 사용한 것을 그대로 대입

```javascript
//암호화모듈
const crypto = require("crypto");
//단방향
let pw = "1234";

let result1 = crypto.createHash("sha256")
    .update(pw).digest('base64');

console.log(result1);



//암호화 모듈 가져오기 

const algorithm = "aes-256-cbc";
//node의 crypto 모듈에서는 key는 32자리 iv는 16자리
const key = "12345678901234567890123";
const iv = "1234123412341234";


//암호화 객체 생성

let cipher = crypto.createCipheriv(algorithm,key,iv);
let rst = cipher.update('01047978158','utf8','base64');
rst +=cipher.final('base64');
console.log(rst);
//rst = 암호화 

//복호화 
let decipher = crypto.createDecipheriv(algorithm,key,iv);
let decipherResult = decipher.update(rst,'base64','utf8');
decipherResult+=decipher.final('utf8');
console.log(decipherResult,"원본");
//decipherResult = 01047978158

```

갔다와서 미러링해보기 