const express = require('express');
const path = require('path')
const app = express();

//사용자의 요청처리
app.get('/motoroler',(req,res)=>{
    
    res.sendFile(path.join(__dirname,'./ssibal.html'))
    //send() -> 직접 출력내용작성
    // sendfile -> html파일 경로
    // json -> json데이터
})


app.set('port', 3000);

app.listen(app.get('port'), () => {
    console.log(app.get('port'))
})
