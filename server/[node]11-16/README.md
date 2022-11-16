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

### util 모듈
> 여러가지 편의 기능을 모아둔 모듈
- util.promisify : 콜백 패턴을 Promise 패턴으로 변경

### worker_threads 모듈
- html5 에서 Web Worker 라는 스레드 관련 API가 추가됨 
    - Web Worker 를 사용하기 쉽도록 해주는모듈
- Node는 14버전까지는 싱글 스레드 기반 
    - 하나의 스레드만 만들어서 사용자의 요청을 순서대로 처리

- 14버전 이후 멀티스레드 를 지원
    - express 모듈이나 웹 서버를 만들면 멀티 스레드 형식으로 사용자의 요청을 처리해준다.

- child_process 모듈
    - 다른 프로세스 를 실행하는모듈


```javascript 
const os = require('os');
let pos = os.type().toLocaleLowerCase().indexOf("linux")
if(pos>=0){
    console.log("linux")
}else{
    console.log("windows아님")
}

console.log(os.type());
//운영체제 확인

//========================================================================

//다른 프로세스를 실행할 수 있는 모듈을 가져오기
const exec = require('child_process').exec;
const os =require("os");
console.log(os.platform());

//프로세스 준비
//widows에서는 dir 이 디렉토리의 목록을 확인하는 것이다
// notepade.exe 는 메모장  
// let process = exec('notepad.exe cryption.js');
let process = exec('ls');


// 프로세스가 정상적으로 수행되면
process.stdout.on('data', function (data) {
    console.log(data.toString());
})

//수행되지 않으면
process.stderr.on('data', function (data) {
    console.log(data.toString());
})
```

- 문자열을 비교할 때는 일치하는 것을 찾는 경우보다는 포함된 경우를 찾는경우가 많다
    -<b> 이런경우에 indexOf 를 이용할수 있는 indexOf는 포함된 경우에는 시작 위치를 그렇지 않은경우엔 음수를리턴</b>


## 파일 시스템 
- 파일 읽고 쓰기
- 파일을 읽고 쓰기 위한 모듈은 fs

### 파일읽기
- fs.readFile('파일경로',[options],콜백함수): 비동기 방식으로 읽음
    - 콜백함수는 매개변수가 두개인데 첫번째 매개변수는 에러가 발생했을때 에러 내용을 가지고 있고 <br/>
    두번째 매개변수가 읽기에 성공했을 때 읽어낸 데이터

- fs.readFileSync('파일경로',[options]): 동기식으로 읽어내고 읽어낸 데이터를 리턴

### Buffer
- buffer: 데이터를 저장하기 위한 메모리
- buffering: 데이터를 한꺼번에 처리하기 위해서 데이터를 모으는 작업
- readfile: 이라는 함수는 읽어내고 난 후 Buffer 객체를 리턴
    - Buffer 객체에는 크기를 알려주는 length속성 그리고 문자열을 buffer<br/>
    로 변경하는 from 함수 나 Buffer 의 내용을<br/> 문자열로 변환하는 toString 함수등이 포함되어있다.

### 동기적으로 읽기
> 변경 가능하거나 변하지 않는 중요한 문자열은 파일이나 데이터베이스에 저장하고 읽는 방식을 사용

- 텍스트파일을 추가하고 샘플 데이터를 작성 -textRead.txt




- 운영 환경과 개발 환경이 다른 경우 소스 코드를 수정하게 되면 <br/> 컴파일을 다시하고 빌드를 다시해야한다.

- 클라이언트에 배포하는 프로그램을 만든 경우라면 대부분의 언어는 역 어셈블이 가능(자바)


### 비동기적 파일읽기

```javascript
fs.readFile('./textRead.txt', (error, data) => {
    console.log(data.toString());
    if(error){
        console.log(error.message)
    }
})
console.log("파일읽기 ")

//result ::
// 파일읽기
// ...data.toString() <= 결과

```

## Stream
> 데이터의 흐름
- 데이터를 일정한 크기로 잘라서 여러 번에 나누어서 처리
    - 용량이 큰 파일을 한 번에 읽어낼려고 하면 버퍼의 크기가<br/>
    커져서 메모리 부담이 생기게되므로  작게 잘라서 처리하는 것을 <br/>
    chunk라고 함.
        - <b>로그 파일을 읽을 때 이런방식을사용함.</b>
- 스트리밍 : 일정한 크기의 데이터를 지속적으로 전달하는 작업
    - fs모듈 :createReadStream 메서드나 <br/> createWriteStream 메서드를 이용해서 스트림을생성
        - 파일 경로 와 highWaterMark 옵션을 이용해서 버퍼의 크기를 설정
    - 읽기 스트림의 경우는 <br/>data(하나의 버퍼를 읽었을 때 발생),end(읽기 끝났을 때 발생) error(에러발생) 이벤트를 처리

    - 쓰기 스트림의 경우는 drain,finish,error 이벤트를 처리.

### Stream을 이용한 읽기

```javascript
//스트림을 이용한 읽기방법
const readStream = fs.createReadStream("./textRead.txt", { highWaterMark: 16 });

// 데이터를 저장하기 위한 객체를 생성
const streamData = [];

//읽는 동안 발생하는 이벤트를 처리
readStream.on('data', (chunk) => {
    //읽는 동안에는 읽어온 데이터를 추가
    streamData.push(chunk);

})

//읽기 끝나면 발생하는 이벤트를 처리
readStream.on('end', () => {
    //지금 까지 읽은 내용을 하나로 만들기 
    let endData = Buffer.concat(streamData);
    console.log(endData.toString())

})

//에러가  발생하는 이벤트를 처리
readStream.on('error', (err) => {
    console.log(err.message);
})
```


### 용량이 큰 Stream 파일을 생성

```javascript
const fs = require('fs');
// 여ㅛㅇ량이 큰 파일을 생성

const file = fs.createWriteStream('./data.txt');

for (let i = 0; i < 1000000; i++) {
    file.write("용량이 큰 파일 만들기 \n")
}
file.end();
```

### 파일의 크기만큼 필요(비교) stream 쓸때와 안쓸때 
> 스트림을 사용한 복사는 : 파일의 내용을 잘라서 읽기 때문에 파일의 크기만큼의 메모리가 추가로 필요하지않다.
- chunk :짤라서 처리한다라고생각하자
- stream: 나눠서 처리한다고 생각하자

```javascript 

//스트림을 사용하지 않고 읽어서쓰기

const fs = require('fs');

console.log("복사하기 전 메모리 사용량"+process.memoryUsage().rss);

const detail1 = fs.readFileSync('./data.txt');
fs.writeFileSync('./normal.txt',detail1);


console.log('복사하기 후 메모리 사용량:'+process.memoryUsage().rss);

//복사하기 전 메모리 사용량17940480
//복사하기 후 메모리 사용량:50483200

//====================================================================================

// 스트림을 이용한 복사


const fs = require('fs');

console.log("복사하기 전 메모리 사용량" + process.memoryUsage().rss);

const readStream = fs.createReadStream('./data.txt');
const writeStream = fs.createWriteStream('./asdf.txt');

//읽고 쓰삼
readStream.pipe(writeStream);
readStream.on('end', () => {
    console.log('복사하기 후 메모리 사용량:' + process.memoryUsage().rss);
})

//복사하기 전 메모리 사용량17940480
//복사하기 후 메모리 사용량:38461440
```

### 기타 함수 
> 아래와 같은 함수들의 콜백은 에러 객체를 넘겨받고 , <br/>
에러 객체가 존재하면 에러가 발생한 것이고 그렇지 않으면 에러가 발생하지 않은것

- access(경로,옵션,콜백) : 디렉토리나 파일에 접근할 수 있는지를 확인
- mkdir(경로,콜백) : 경로를 생성
- open (경로,옵션,콜백) : 경로의 파일을 열고 아이디를 리턴하는데 파일이 없으면 생성
- rename(기존 경로,새 경로, 콜백): 이름 변경    
- unlink (경로,콜백) : 파일 지우기 
- rmdir(경로,콜백) : 디렉토리 삭제


```javascript
* 기타 함수 사용

let today = new Date();

console.log(today)//오늘날짜
console.log(today.getFullYear());
console.log(today.getMonth() + 1); //월은 꼭 찍어야함 결과가 10월이기때문에 +1을꼭해주자
console.log(today.getDate());

let year = String(today.getFullYear());
let month = String(today.getMonth() + 1);
let day = String(today.getDate());

const completeToday = year + month + day; // result : 20221116

//오늘 날짜로 된 디렉토리가 없으면 생성
const fs = require('fs');

fs.access(completeToday, (err) => {
    if (err) {
        console.log("디렉토리 없다")
        fs.mkdir(completeToday, (err) => {
            if (err) { console.log("디렉토리 만들기 실패") }
            else { console.log("디렉토리 만들기 성공") }
        })
    } else {
        console.log("디렉토리 존재");
    }
})
```