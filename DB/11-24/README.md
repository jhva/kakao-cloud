# Node + Maria DB
> 프로그래밍 언어에서 관계형 데이터베이스를 사용하는 방법
- 데이터베이스 제조업체에서 제공하는 드라이버를 사용하거나 SQL MAPPER Framework를 사용하는 방식

- ORM Framework 이용 
    - sql을 이용하지않고 객체 지향 언어의 메서드를 이용해서 SQl을 자동 변환해서 수행하는 방식

- 데이터베이스 ㅔㅈ공업체에서 제공하는 드라이버를 이용해서 sql을 실행
    - node 에서는 mariadb는 mysql 과 같은 데이터베이스로 취급함

```javascript
const mariadb = require('mysql');

let connection = mariadb.createConnection({
    host:'192.168.0.156',
    port:3306,
    user:'root',
    password:'1234',
    database:'kjh'
})


//연결

connection.connect((err)=>{
    if(err){
        console.log(err);
        //에러발생했을떄
    }else{
        console.log(connection);

        //테이블 ㅁ생성구문
        connection.query('create table family(id int auto_increment primary key, name varchar(100))');
    //데이터 삽입 구문 
    connection.query('insert into family(name)values(?)','을지문덕');
    connection.query('insert into family(name)values(?)','김정훈');
    connection.query('insert into family(name)values(?)','오시훈');
    }
})


//데이터베이스 연결이 완료되었을떄 

```


# SQL 실행

### select 가 아닌 구문
- 결과가 성공 과 실패 또는 영향받은 개수의 형태
    - 연결객체.query(SQL,[파라미터배열]);
        - 파라미터배열은 SQL 작성할 때 값의 자리에 직접값을 작성하지 않고 ? 로 설정한 후 나중에 값을 대입할수있다.


### select 구문 
>select구문은 콜백함수를 매개변수로 추가하는데 콜백함수의 매개변수가 3개인데 첫번째 에러 객체, 두번째 검색된내인데 javascript 객체 형태로 제공되고 세번째 meta data로 검색된 결과에대한 정보가 보인다

- 조회한 결과 (Cursor 또는 하나의 객체 나 배열)



```javascript
   //테이블 생성구문
    connection.query('create table family(id int auto_increment primary key, name varchar(100))');
    //데이터 삽입 구문 
    connection.query('insert into family(name)values(?)','을지문덕');
    connection.query('insert into family(name)values(?)','김정훈');
    connection.query('insert into family(name)values(?)','오시훈');
```



- 화면에 출력할 거라면 javascript 객체를 그대로 이용하면 되고 데이터 형태로 제공하고자 하면 JSON 문자열로 변환해서 출력해야함.

```javascript
  //select 구문 
connection.query("select * from family ", (err, result, fields) => {
    if (err) {
       //이렇게하면 서버에서밖에안보인다.
        //클라이언트 한테 보내줘야함
       console.log("{result:false}");
       } else {
       //json문자열로 주겠다는얘기
       let results = JSON.stringify(result);
       console.log(results);
       console.log(fields);
        }
})
```

- json 으로 주었을때 
<img src="./images/jsonconsole.jpg"/>

- fields 객체 
<img src="./images/필드객체.jpg"/>


### 테이블 1개를 SQL 을 이용해서 연동

- 기능
    - 테이블의 데이터 전체를 가져오기
    - 테이블의 데이터 일부분을 가져오기 (페이지 단위로 가져오기)
    - 데이터 1개를 가져오기
    - 데이터 삽입 ,삭제,갱신
    - 파일 업로드 와 다운로드 
    - 가장 최근에 데이터를 수정한 시간을 기록하고 리턴함

### 서버와 클라이언트 사의 데이터교환
- 접속할 때 마다 서버의 데이터를 가져와서 출력
- 서버의 데이터를 클라이언트에 저장하고 접속할 때마다 서버의 데이터 와 클라이언트의 데이터를 비교햇 ㅓ수정ㅇ이 발생했을 때 만 업데이트 
- 서버의 데이터 와 클라이언트의 데이터를 비교할 수 있어야 하는데 가장 쉬운 방법은 양쪽에서 숫정한 시간을 기록한 후 클라이언트의 수정 시간이 서버의 수정시간보다 이전이면 업데이트를 수행
    



### 필요한 모듈 설치 

- 프로젝트에 설치 
```
npm install express morgan multer mysql cookie-parser express-session express-mysql-session dotenv compression file-stream-rotator
```
- express 
    - 웹 서버 모듈
- morgan 
    - 로그 기록을 위한 모듈
- file-stream-rotator
    - 로그를 파일에 기록하기 위한 모듈
- multer
    - 파일 업로드를 하기위한 모듈
- mysql 
    - mysql 이나 maria db를 사용하기 위한 모듈
- cookie-parser
    - 쿠키를 사요하기위한 모듈
- expresss-session 
    - 세션을 사용하기 위한 모듈
- express-mysql-session 
    - 세션을 mysql 이나 mariadb에 저장하기위한 모듈
- dotenv 
    - .env 파일의 내용을 process.env 로 저장해서 사용하기위한 모듈
- compression 
    - 서버가 처리한결과를 압축해서 클라이언트에게 전송하기 위한 모듈 (트래픽을 죽일목적으로 사용이됨.)

### 개발 모드로 설치
```
npm install --save-dev nodemon
```
- nodemon : 소스코드를 수정하고 다시 서버를 실행하지않고  재시작을 바로할수있도록 해주는 모듈

### 서버에 데이터 업데이트 시간
> update.txt에 로그기록

### 프로젝트에 .env 파일을 만들고 필요한 속성을 정의 

```javascript 
const dotenv = require('dotenv');

dotenv.config();
```

### post 방식의 파라미터 읽을 수 있도록 설정

```javascript
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }))
```

### 압축해서 전송하는 옵션설정
```javascript
app.use(compression());
app.use(morgan('combined', { stream: accessLogStream }));
```
    
### 서버구동
```javascript
app.listen(app.get('port'), () => {
    console.log(app.get('port'), '번 포트킴');
});

//서버에러시
app.use((err, req, res, next) => {
//서버에러시
    console.log(err);
    req.status(500).send(err.message);
});
```
### 세션설정

```javascript
app.use(session({
    secret: process.env.COOKIE_SECRET,
    resave: false,
    saveUninitialized: true,
    store: new MariaDBStore(options)
}))
```
### css경로
```html
 <link rel="stylesheet" href="/css/common.css"/>
```

### 로그를 매일 기록하기 위한 설정

```javascript
fs.existsSync(logDirectory) || fs.mkdirSync(logDirectory);
//로그 파일 옵션을 설정
let accessLogStream = FileStreamRotator.getStream({
    date_format: 'YYYYMMDD',
    filename: path.join(logDirectory, 'access-%DATE%.log'),
    frequency: 'daily',
    verbose: false
})
```

### 정적파일
- 웹에서는 소스 코드를 제외하고 서버의 데이터를 출력하는 용도가 아닌 파일
    - HTML
    - CSS
    - JS
    - 이미지
    - 사운드
    - 동영상


### index.html출력
```javascript
app.get('/', (req, res) => {
    res.sendFile(path.join(__dirname, 'index.html')) //html출력
    //서버의 데이터 출력못함 - ajax나 fetch api 를 이용해야함
    // 템플릿 엔진 :res.render(파일경로,데이터)
    // 템플릿 엔진에 넘겨주는 데이터는 프로그래밍 언어의 데이터
    

    //json 출력 res.json(데이터)
    //json문자열의 형태로 데이터를 제공

      //전체 출력할때 꼭 sort를 해줘야한다 sql은 기본적으로 오름차순이기때문에
    //2개 이상의 데이터를 조회할때는 정렬은 필수
    // 매개변수의 ? 가없으면 [] 는없애도된다
});
```


### 데이터 전체 가져오기
- url
    - data/all
```javascript
app.get('/data/all', (req, res) => {

    connection.query("select * from goods order by itemid desc", [], (err, results, fields) => {
        if (err) {
            //err 가 발생한경우
            // 에러가 발생했다고 데이터를 전송하지 않으면 안됨.
            res.json({ 'result': false })
            console.log(err);
        } else {
            res.json({ 'result': true, 'list': results });
            //정상 응답을 한 경우
        }
    })
});
```

### html 본문 바디에 추가 
```javascript
 let btn = document.getElementById('allData_btn');

        btn.addEventListener("click", (e) => {
            //ajax 로 데이터 가져오기 

            let req = new XMLHttpRequest();

            req.open('GET', '/data/all');
            //요청
            req.send('');
            //데이터 전송 
            req.addEventListener("load", () => {
                //json 문자열 확인
                alert(req.responseText);
            });
        });
```

### 데이터 일부분 가져오기 
> 데이터의 일부분 가져오기를 처리할 때는 일반적으로 2개의 데이터가 필요
- 하나는 페이지 번호는 시작하는 데이터의 번호 
    - 시작하는 데이터 번호를 주는 경우는 데이터의 개수가 고정인 경우가 많다. 

- 다른 하나는 한 페이지에 출력할 데이터 개수

### get방식에서의 서버로의 데이터 전달
- 전달하고자 하는 데이터가 1개인 경우
    - 파라미터로 전달 :URL?이름=값
    - URL에 전달 : URL/값 - 최근에는 이 방식을 선호함

- 전달하고자 하는 데이터가 2개 이상인 경우는 파라미터 형태로 전달
    - URL?이름=값&이름=값... ... 

```javascript
// 파라미터로 읽기 
   const page = req.params.page;
    console.log(page);
    // /data/all/ params...
```


### 정훈참조 라이브러리 bodyParser
- https://medium.com/@chullino/1%EB%B6%84-%ED%8C%A8%ED%82%A4%EC%A7%80-%EC%86%8C%EA%B0%9C-body-parser%EB%A5%BC-%EC%86%8C%EA%B0%9C%ED%95%A9%EB%8B%88%EB%8B%A4-%ED%95%98%EC%A7%80%EB%A7%8C-body-parser%EB%A5%BC-%EC%93%B0%EC%A7%80-%EB%A7%88%EC%84%B8%EC%9A%94-bc3cbe0b2fd



### Mysql 이나 MariaDb에서 데이터의 일부분을 가져오기 (TOP-N)
- SELECT 구문의 마지막에 limit(시작하는번호 또는 건너뛸 데이터개수,데이터 개수)를 추가해주면 된다.

```
 ex 데이터개수 : 10 이면
 1페이지의 시작번호는 0
 2페이지의 시작번호는 10
 3페이지의 시작번호는 20
 4페이지의 시작번호는 30
```

- 건너뛸 데이터 개수는 = (페이지 번호-1)*데이터개수


```javascript
//item 테이블에서 itemid 를 가지고 내림차순 정렬해서
    // 페이지 단위로 데이터 가져오기
    // select * from item order by itemid desc limit 시작번호, 5
    // 시작번호는 = (pageno-1)*5
    // ? 자리는 저렇게 써준다 [(page-1)* 5]
connection.query("select * from goods order by itemid desc limit ?, 5", [(pageno - 1) * 5], (err, results, fields) => { })
 //파라미터는 무조건 문자열이다
 // 파라미터를 가지고 산술연산을 할 때는 정수로 변환을 해주어야합니다.
```

```javascript
//전체 데이터 가져올때 

 connection.query("select * from goods order by itemid desc", [], (err, results, fields) => {
        if (err) {
            //err 가 발생한경우
            // 에러가 발생했다고 데이터를 전송하지 않으면 안됨.
            res.json({ 'result': false })
            console.log(err);
        } else {
            res.json({ 'result': true, 'list': results });
            //정상 응답을 한 경우
        }
    })
```

> 페이지 번호가 전체 데이터 개수보다 커지는 상황이 발생하면 데이터를 리턴하지 않는다
- 클라이언트 측에서 서버에게 전체 데이터 이후의 데이터를 요청하지 못하도록 하고자 하면 이 경우에 는 전체 데이터의 개수를 전달해주면된다.