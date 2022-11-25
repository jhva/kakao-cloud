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

app.get('/data/all', (req, res) => {
    let pageno = req.query.pageno;
    if (pageno == undefined) {
        pageno = 1;
    }
    console.log(pageno);
    //성공과 여실패여부를 저장
    let result = true;
    //성공했을때 데이터를 저장
    let list;


    //데이터목록가져오기
    connection.query("select * from goods order by itemid desc limit ?, 5",
        [(parseInt(pageno - 1)) * 5], (err, results, fields) => {
            if (err) {
                // res.json({ 'results': false })
                console.log(err);
                result = false;
            } else {
                // res.json({ 'results': true, 'list': results });
                list = results;
            }

            //데이터개수가져오기
            let cnt = 0;
            connection.query("select count(*) cnt from goods", [], (err, results, fields) => {
                if (err) {
                    console.log(err);
                    result = false;

                } else {
                    //하나의 행만 리턴되므로 0 번째 데이터를 읽어내면 된다 .
                    cnt = results[0].cnt;

                }
                if (result === false) {
                    res.json({ "result": false })
                } else {
                    res.json({ "result": true, 'list': list, "count": cnt })
                }
            })


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

//데이터 상세보기
app.get('/data/item/:itemid', (req, res) => {
    //파라미터 읽기
    let itemid = req.params.itemid;
    console.log(itemid);

    // itemid를 이용해서 1개의 데이터를 찾아오는 sql 실행 
    connection.query("select * from goods where itemid=?", itemid, (err, results, fields) => {
        if (err) {
            console.log(err);
            res.json({ "result": false });
        } else {
            res.json({ "result": true, "item": results[0] });
        }
    })
});


//이미지 다운로드  ????????? 
app.get('/img/:pictureurl', (req, res) => {
    let pictureurl = req.params.pictureurl;
    let file = '"C:\Users\\user\\Documents\kakaoCloudSchool\DB\11-24\public\img' + '/' + pictureurl;
    console.log("file:" + file);
    mimetype = mime.lookup(pictureurl);
    console.log("file:" + mimetype);
    res.setHeader('Content-disposition', 'attachment; filename=' + pictureurl);
    res.setHeader('Content-type', mimetype);
    let filestream = fs.createReadStream(file);
    filestream.pipe(res);
    // let pictureurl = req.params.pictureurl;

    // let file = "C:\Users\\user\\Documents\kakaoCloudSchool\DB\11-24\public\img" + "/" + pictureurl;
    // console.log(__dirname);
    // console.log(file);

    // //파일이름을 가지고 타입을 생성
    // let mimetype = mime.lookup(pictureurl);

    // res.setHeader('Content-disposition', 'attachment; filename=' + pictureurl);
    // res.setHeader('Content-type', mimetype);

    // let filestream = fs.createReadStream(file);
    // filestream.pipe(res);

})


const getDate = () => {
    let date = new Date();
    let year = date.getFullYear();
    let month = date.getMonth() + 1;
    let day = date.getDate();

    month = month >= 10 ? month : '0' + month;
    day = day >= 10 ? day : '0' + day;

    return year + "-" + month + "-" + day;
}
//날짜 와 시간을 리턴하는 함수
const getTime = () => {
    let date = new Date();
    let hour = date.getHours();
    let minute = date.getMinutes();
    let second = date.getSeconds();

    hour >= 10 ? hour : '0' + hour;
    minute >= 10 ? minute : '0' + minute;
    second >= 10 ? second : '0' + second;

    return getDate() + " " + hour + ":" + minute + ":" + second;
}

app.post('/data/post', upload.single('pictureurl'), (req, res) => {
    //파라미터 읽어오기
    const itemname = req.body.itemname;
    const description = req.body.description;
    const price = req.body.price;
    console.log(req.file);
    //파일이름 - 업로드하는 파일이 없으면 default.png
    let pictureurl;
    if (req.file) {
        pictureurl = req.file.originalname
    } else {
        pictureurl = 'default.png';
    }
    let itemid;

    connection.query("select max(itemid) maxid from goods",
        [], (err, results, fields) => {

            //최대값이 있으면 +1 하고 없으면 1로설정
            if (results.length > 0) {

                itemid = results[0].maxid + 1;
            } else {
                itemid = 1;
            }
            connection.query('insert into goods(itemid,itemname,price,description,pictureurl,updatedate) values(?,?,?,?,?,?);',
                [itemid, itemname, price, description, pictureurl, getDate()], (err, results, fields) => {
                    if (err) {
                        console.log(err);
                        res.json({ "results": false });
                    } else {
                        //현재 날짜 및 시간을 update.txtdp rlfhr
                        const writeStream = fs.createWriteStream('./update.txt');
                        writeStream.write(getTime());
                        writeStream.end();
                        res.json({ "results": true });
                    }
                }
            )
        })

})
app.post('/item/delete', (req, res) => {
    let itemid = req.body.itemid;

    connection.query("delete from goods where itemid=? ", [itemid], (err, results, fields) => {
        if (err) {
            console.log(err);
            res.json({ "results:": false })
        } else {
            res.json({ "results": true });
        }
    })

})

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









