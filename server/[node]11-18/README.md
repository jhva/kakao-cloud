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