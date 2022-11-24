const express = require('express');
const path = require('path');
const mysql = require('mysql');
const dotenv = require('dotenv');
//세션을 저장하기 위한 MYSQL 데이터베이스 저장소생성

const morgan = require('morgan');
const compression = require('compression');
const cookieParser = require('cookie-parser');
const session = require('express-session');
const multer = require('multer');


let fs = require('fs');
let util = require('util');
let mime = require('mime');
let MariaDBStore = require('express-mysql-session')(session);
let bodyParser = require('body-parser');
let logDirectory = path.join(__dirname, 'log');
let FileStreamRotator = require('file-stream-rotator');
//로그를 기록할 디렉토리 경로설정



dotenv.config();

const app = express();

app.set('port', process.env.PORT || 7500);

const upload = multer({
    Storage: multer.diskStorage({
        destination(req, file, done) {
            done(null, 'public/img');
        },
        filename(req, file, done) {
            const ext = path.extname(file.originalname);
            done(null, path.basename(file.originalname, ext) +
                Date.now() + ext);
        }
    }),
    limits: { fileSize: 10 * 1024 * 1024 }
})

//로그를 매일 기록하기 위한 설정
fs.existsSync(logDirectory) || fs.mkdirSync(logDirectory);

//로그 파일 옵션을 설정
let accessLogStream = FileStreamRotator.getStream({
    date_format: 'YYYYMMDD',
    filename: path.join(logDirectory, 'access-%DATE%.log'),
    frequency: 'daily',
    verbose: false
})

let options = {
    host: process.env.HOST,
    port: process.env.MYSQLPORT,
    user: process.env.USERNAME,
    password: process.env.PASSWORD,
    database: process.env.DATABASE
}



let connection = mysql.createConnection(options);
connection.connect((err) => {
    if (err) {
        console.log(err);
        throw err;
    } else {
        console.log("db도연결완료")
    }
})


app.use('/', express.static('public'));
app.use(compression());
app.use(morgan('combined', { stream: accessLogStream }));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }))
app.use(session({
    secret: process.env.COOKIE_SECRET,
    resave: false,
    saveUninitialized: true,
    store: new MariaDBStore(options)
}))

app.get('/', (req, res) => {
    res.sendFile(path.join(__dirname, 'index.html'))
});

//여기까지함
app.get('/data/all', (req, res) => {
    let pageno = req.query.pageno;
    if (pageno == undefined) {
        pageno = 1;
    }
    console.log(pageno);

    connection.query("select * from goods order by itemid desc limit ?, 5", [(parseInt(pageno - 1)) * 5], (err, results, fields) => {
        if (err) {
            res.json({ 'results': false })
            console.log(err)
        } else {
            res.json({ 'results': true, 'list': results });
        }
    })


    // connection.query("select * from goods order by itemid desc", [], (err, results, fields) => {
    //     if (err) {
    //         //err 가 발생한경우
    //         // 에러가 발생했다고 데이터를 전송하지 않으면 안됨.
    //         res.json({ 'result': false })
    //         console.log(err);
    //     } else {
    //         res.json({ 'result': true, 'list': results });
    //         //정상 응답을 한 경우
    //     }
    // })
});


app.get('/data/all/:page', (req, res) => {
    const page = req.params.page;
    console.log(page);
});


app.use((err, req, res, next) => {
    console.log(err);
    req.status(500).send(err.message);
});



app.listen(app.get('port'), () => {
    console.log(app.get('port'), '번 포트킴');
});
















//=============================================================================================



// const mariadb = require('mysql');
// const dotenv = require('dotenv');

// dotenv.config();

// let connection = mariadb.createConnection({
//     host: '192.168.0.156',
//     port: 3306,
//     user: 'root',
//     password: '1234',
//     database: 'kjh'
// })


//연결

// connection.connect((err) => {
//     if (err) {
//         console.log(err);
//         //에러발생했을떄
//     } else {
//         // console.log(connection);

//         // //테이블 생성구문
//         // connection.query('create table family(id int auto_increment primary key, name varchar(100))');
//         // //데이터 삽입 구문
//         // connection.query('insert into family(name)values(?)','을지문덕');
//         // connection.query('insert into family(name)values(?)','김정훈');
//         // connection.query('insert into family(name)values(?)','오시훈');

//         //select 구문
//         connection.query("select * from family ", (err, result, fields) => {
//             if (err) {
//                 //이렇게하면 서버에서밖에안보인다.
//                 //클라이언트 한테 보내줘야함
//                 console.log("{result:false}");
//             } else {
//                 //json문자열로 주겠다는얘기

//                 let results = JSON.stringify(result);
//                 console.log(results);
//                 console.log(fields);
//             }
//         })
//     }
// })


//데이터베이스 연결이 완료되었을떄 









