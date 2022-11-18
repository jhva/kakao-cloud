# Node Express

> Node 라이브러리 중에서 웹 애플리케이션 서버를 만드는 가장 많이 이용하는 라이브러리

### middle Ware
- Filter, Aop
    - 클라이언트의 요청을 처리하기 전이나 처리 한 후에 공통으로 해야 할 작업을 미리 만들어두고 사용하는것



```javascript
const path = require('path')
//사용자의 요청처리
app.get('/motoroler',(req,res)=>{
    res.send("asf")
    //send() -> 직접 출력내용작성
    // sendfile -> html파일 경로
    res.sendFile(path.join(__dirname,'./ssibal.html'))

    // json -> json데이터
})
// 파일의 경로는 절대경로기때문에 항상 path 를 가져와야함.
```
### 세션 
- 세션을 메모리에 저장하게 되면 재사용이 안되고 다른 서버와의 공유도 어렵기 때문에 세션을 파일에 저장해서 공유하고 재사용 가능하도록 만들 수 있다.

```javascript
app.use(session({
    secret:"ㅅㅂ",
    resave:false, //(매 request 마다 세션을 계속 다시 저장하는 것)
    saveUninitialized:true
}))
//session 객체에 대해 알아보았음
//reference : https://fierycoding.tistory.com/36
```


## 미들웨어 사용 
- .env파일 
    - process.env 이름으로 사용가능 (생성 작성)


```javascript
const express = require('express');
const path = require('path')
const session = require('express-session');
//세션 파일에 저장하기 위한 모듈 
const Filestore = require('session-file-store')(session);
const morgan = require('morgan');
//.env 파일의 내용을 읽어서 process.env 에 저장해주는모듈
const dotenv = require('dotenv');
const { urlencoded } = require('express');
const app = express();

//env파일사용가능하게
dotenv.config();

//로그 출력 
app.use(morgan('dev'))

//세션
app.use(session({
    secret: "ㅅㅂ",
    resave: false, //(매 request 마다 세션을 계속 다시 저장하는 것)
    saveUninitialized: true,
    store: new Filestore()
}))


//post 방식의 파라미터를 읽을 수 있도록 설정
app.use(express, urlencoded({ extended: false }))

//쿠키 사용이 가능하도록 설정
const cookieParser = require('cookie-parser');
app.use(cookieParser(process.env.COOKIE))
```


### 파일 업로드 처리 - multer 미들웨어
> web service 에서 파일을 업로드할려면 multipart/form-data 형태로 데이터를 전송해야한다.
- node에서 multer 를 가지고 파일 업로드를 처리할때는 4가지로 나누어서 처리함.
    - none: 파일업로드가없을때
    - single : 하나의 파일만 업로드 될때
    - array: 한번에 여러개의 파일이 업로드 가능한데 하나의 파라미터로 업로드
    - fields: 여러개의 파일을 여러 개의 파라미터로 업로드 하는 경우 사용 

- 파일 업로드 처리를 할 대 파일의 이름을 유일 무일하게 변경하는 경우가 있음<br/>
이런 경우에는 UUID(유일한 문자열) 나 현재시간을 파일 이름에 추가해서 생성하는 것이 일반적이다.

- 실제 운영을 할 떄는 애플리케이션 서버 디스크가 아닌 별도의 디스크((AWS 의 S3 서비스 나 Google Firebase 서비스))에 저장해야함
- 저장을 할 때 디렉토리는 미리 만들어져 있어야한다.

### 파일 업로드 예제 
```javascript
try {
    //디렉토리가 없으면 예외발생
    fs.readdirSync('uploads');
} catch (err) {
    //디렉토리없으니까 생성
    fs.mkdirSync('uploads')
}
//파일 업로드 설정
const upload = multer({
    storage: multer.diskStorage({
        destination(req, file, done) {
            done(null, 'uploads/');
        },
        filename(req, file, done) {
            const ext = path.extname(file.originalname);
            done(null, path.basename(file.originalname, ext) + Date.now() + ext);
        }
    }),
    limits: { fileSize: 1024 * 1024 * 10 }
})

```


### 여러 개의 파일 업로드 -폼의 내용을 ajax로 전송



## Routing

- Routing :최적의 경로를 탐색하는 것을 라우팅
- Node 에서의 Routing
- 사용자의 요청을 처리하는 부분을 모듈화 하는것
    - 웹 애플리케이션 서버가 커지면 처리해야할 URL이 늘어나는데 이를 하나의 파일에서 <br/> 전부 처리하면 가독성이 떨어지게 되므로 url을 모듈화해서 처리
- 라우팅 
    - 기본 요청과 user 가 포함 된 요청 과 board가 포함된 요청을 분리해서 ㅜ현


### URL의 일부분을 파라미터로 사용하기
- 최근에는 파라미터 1개인 경우 파라미터를 만들지 않고 URL에 포함시켜 전송이된다.
- 처리하는 URL 을 설정할 때 경로/:변수명 의 형태로 작성한 후 내부에서 req.params.변수명 을 사용하면된다 
```javascript
```