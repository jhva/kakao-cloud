// const path = require('path'); 
// // const session = require('express-session');
// // //세션 파일에 저장하기 위한 모듈 
// // const Filestore = require('session-file-store')(session);
// const morgan = require('morgan');
// //.env 파일의 내용을 읽어서 process.env 에 저장해주는모듈
// const dotenv = require('dotenv');
// const cookieParser = require('cookie-parser');
// const { urlencoded } = require('express');
// const fs = require('fs');
// const multer = require('multer');
// const express = require('express');


// // //세션
// // app.use(session({
// //     secret: "ㅅㅂ",
// //     resave: false, //(매 request 마다 세션을 계속 다시 저장하는 것)
// //     saveUninitialized: true,
// //     store: new Filestore()
// // }))
// dotenv.config();

// const app = express();

// //post 방식의 파라미터를 읽을 수 있도록 설정


// app.set('port', process.env.PORT);


// //env파일사용가능하게

// //로그 출력 



// // try {
// // fs.readdirSync('uploads');
// // } catch (error) {
// // console.error('uploads 폴더가 없어 uploads 폴더를 생성합니다.');
// // fs.mkdirSync('uploads');
// // }

// // //파일 업로드 설정
// // const upload = multer({
// //     storage: multer.diskStorage({
// //     destination(req, file, done) {
// //     done(null, 'uploads/');
// //     },
// //     filename(req, file, done) {
// //     const ext = path.extname(file.originalname);
// //     done(null, path.basename(file.originalname, ext) + Date.now() + ext);
// //     },
// //     }),
// //     limits: { fileSize: 5 * 1024 * 1024 },
// //     });


// // //쿠키 사용이 가능하도록 설정


// const abcRouter = require('./abc');
// const userRouter= require('./user');
// const boardRouter= require('./board');

// app.use("/",abcRouter)
// app.use("/user",userRouter)
// app.use("/board",boardRouter)
// app.use(morgan('dev'));
// app.use(express, urlencoded({ extended: false }))
// app.use(cookieParser(process.env.COOKIE))
// //사용자의 요청처리
// app.get('/motoroler', (req, res) => {

//     res.sendFile(path.join(__dirname, './ssibal.html'))
//     //send() -> 직접 출력내용작성
//     // sendfile -> html파일 경로
//     // json -> json데이터
// })

// //하나의 파일 업로드 처리
// app.get('/single', (req, res) => {
//     res.sendFile(path.join(__dirname, './multi.html'))

// })

// // app.post('/single', upload.single('image'),(req, res) => {
// //     console.log(req.body.title);
// //     res.send('성공')
// // })







// app.listen(app.get('port'), () => {
//     console.log(app.get('port'), '포트존나실행')
// })


const express = require('express');
const path = require('path'); 

const dotenv = require('dotenv');
const fs = require('fs');
const indexRouter = require('./router/index');
const userRouter= require('./router/user');
const boardRouter= require('./router/board');
const { urlencoded } = require('express');
// const cookieParser = require('cookie-parser');
const morgan = require('morgan');

dotenv.config();
const app = express();



app.set('port', process.env.PORT);


app.use(express, urlencoded({ extended: false }))
// app.use(cookieParser(process.env.COOKIE))
app.use('/',indexRouter);
app.use('/user',userRouter);
app.use('/board',boardRouter);

app.use(morgan('dev'));





app.get('/single', (req, res) => {
    res.sendFile(path.join(__dirname, '/single.html'))

})


app.listen(app.get('port'), () => {
    console.log(app.get('port'), '포트존나실행')
})
