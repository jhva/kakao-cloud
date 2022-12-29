//모듈 가져오기 

const http = require('http');
const fs = require('fs').promises;
const PORT = 8000;
//서버 생성
http.createServer(async (req, res) => {
    //응답 생성
    try {
        const data = await fs.readFile('./rename.html');
        res.writeHead(200, { 'Content-Type': 'text/html; charset=utf-8' });
        res.end(data);
    } catch (error) {
        res.writeHead(500, { 'Content-Type': 'text/html; charset=utf-8' });
        res.end("<p>실패!</p>");
    }
 
}).listen(PORT, () => {
    console.log(`${PORT} 연결 완룡`)
})
