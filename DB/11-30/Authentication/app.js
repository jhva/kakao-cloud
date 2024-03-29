const express = require("express");
const dotenv = require("dotenv");

const fs = require("fs");
const path = require("path");

dotenv.config();
//서버 설정
const app = express();
//로그 출력 설정

app.set("port", process.env.PORT);

//static 파일의 경로 설정
app.use(express.static(path.join(__dirname, "public")));

const nunjucks = require("nunjucks");
app.set("view engine", "html");
nunjucks.configure("views", {
  express: app,
  watch: true,
});

const morgan = require("morgan");
const FileStreamRotator = require("file-stream-rotator");
const logDirectory = path.join(__dirname, "log");

// 로그 디렉토리 생성
fs.existsSync(logDirectory) || fs.mkdirSync(logDirectory);
// 로그 파일 옵션 설정
const accessLogStream = FileStreamRotator.getStream({
  date_format: "YYYYMMDD",
  filename: path.join(logDirectory, "access-%DATE%.log"),
  frequency: "daily",
  verbose: false,
});
// 로그 설정
app.use(morgan("combined", { stream: accessLogStream }));

const compression = require("compression");
app.use(compression());
//post 방식에서 form이 아닌 ㄴ형태로 데이터를 전송하는경우 파라미터 읽기
let bodyParser = require("body-parser");
app.use(bodyParser.json()); // to support JSON-encoded bodies
app.use(
  bodyParser.urlencoded({
    // to support URL-encoded bodies
    extended: true,
  })
);
//쿠키 설정
const cookieParser = require("cookie-parser");
app.use(cookieParser(process.env.COOKIE_SECRET));

const session = require("express-session");
let options = {
  host: process.env.HOST,
  port: process.env.MYSQLPORT,
  user: process.env.USERID,
  password: process.env.PASSWORD,
  database: process.env.DATABASE,
};
const MySQLStore = require("express-mysql-session")(session);
app.use(
  session({
    secret: process.env.COOKIE_SECRET,
    resave: false,
    saveUninitialized: true,
    store: new MySQLStore(options),
  })
);

const { sequelize } = require("./models");
sequelize
  .sync({ force: false })
  .then(() => {
    console.log("디비 접속 성공!");
  })
  .catch((err) => {
    console.log(err);
  });

const passport = require("passport");
const passportConfig = require("./passport");

passportConfig();

app.use(passport.initialize());
//세션 기능은 passport 모듈이 알아서 사용
app.use(passport.session());

//라우터 설정
const pageRouter = require("./routes/page");
const authRouter = require("./routes/auth");
const postRouter = require("./routes/post");
const userRouter = require("./routes/users");

//여기 설정 한 URL 과 page.js 에 설정된 URL 의 조합으로
// URL을 결정
app.use("/", pageRouter);
app.use("/user", userRouter);
app.use("/post", postRouter);

//여기 설정 한 URL 과 page.js 에 설정된 URL 의 조합으로
// URL을 결정
app.use("/auth", authRouter);

//404 에러가 발생한 경우 처리
app.use((req, res, next) => {
  const err = new Error(`${req.method} ${req.url} 라우터가 없습니다.`);
  err.status = 404;
  next(err);
});

//에러가 발생한 경우 처리
app.use((err, req, res, next) => {
  res.locals.message = err.message;
  res.locals.error = process.env.NODE_ENV !== "production" ? err : {};
  res.status(err.status || 500);
  res.render("error");
});
app.listen(app.get("port"), () => {
  console.log(app.get("port"), "번 포트에서 대기 중");
});
