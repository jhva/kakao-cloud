const express = require('express') //모듈 가져오기

const path = require('path');//현재 디렉토리에 대한 절대 경로를 알아내기 위한 내장 모듈

const app = express(); //웹 서버 인스턴스 생성

// 사용자의 요청을 처리하는 코드 작성 

app.set('port', 8000); //포트 설정

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
    verbose: true,
    size: "100M",

})

app.use(morgan('combined', { stream: acessLogStream }));

app.get('/index', (req, res) => {
    console.log(req.ip);
    console.log(req.query);
    res.sendFile(path.join(__dirname, '/mypage.html'));
    //__dirname -> 현재 디렉토리에 있는 main.html을 출력
})

app.listen(app.get('port'), () => {
    console.log(app.get('port') + `이 연결되었습니다`)
    //서버가 정상적으로 구동되었을 때 수행할 내용 
    //일반적으로는 콘솔에 메시지를 출력
})