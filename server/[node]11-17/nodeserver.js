const express = require('express');
const app = express();
const session = require('express-session');

app.set('port', 8001);

let num = 1;

app.use(session({
    secret: "session-key",
    resave: false,
    saveUninitialized: true
}))

//사용자 요청 처리 
// 요청을 처리하는 함수 외부에 만든 변수는 
// 모든 사용자가 공유합니다.
app.get('/session', (req, res) => {
    //세션의 num의 값이 없으면 1로 초기화 하고 있으면 1증가
    if (!req.session.num) {
        req.session.num = 1;
    } else {
        req.session.num += 1;
    }


    //send 
    res.send("num:" + num+"<br/>"+"session num:" + req.session.num);
    num = num + 1;
})

//서버를 실행 시키고 사용자의 요청 처리

app.listen(app.get('port'), () => {
    console.log(app.get('port') + '연결되었습니다');
})