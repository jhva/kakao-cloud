const {odd,even} = require('./var');
const checkNumber = require('./func');
const path =require('path');

const url = require('url');

const addr = "https://www.naver.com/login?id=kjh";
//url 분해 
const p = url.parse(addr);
console.log(p);
// pathname 에는 서버 URL 을 제외한 경로를 저장하고있고 
// query는 query string 을 저장하고 있다.



console.log(checkNumber(5));
//현재 디렉토리를 확인
console.log(__dirname);
//현재 디렉토리의 public 경로 확인
console.log(path.join(__dirname,"public"))
