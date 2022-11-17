const express = require('express') //모듈 가져오기

const path = require('path');//현재 디렉토리에 대한 절대 경로를 알아내기 위한 내장 모듈

const app = express(); //웹 서버 인스턴스 생성

// 사용자의 요청을 처리하는 코드 작성 

app.set('port', 8000); //포트 설정


app.get('/index',(req,res)=>{
    console.log(req.ip);
    console.log(req.query);
    res.sendFile(path.join(__dirname,'/mypage.html'));
    //__dirname -> 현재 디렉토리에 있는 main.html을 출력
})

app.listen(app.get('port'), () => {
    console.log(app.get('port')+`이 연결되었습니다`)
    //서버가 정상적으로 구동되었을 때 수행할 내용 
    //일반적으로는 콘솔에 메시지를 출력
})