//파일을 읽고 쓸 수 있는 모듈 가져오기 
const fs = require('fs');
let data = fs.readFileSync("./textRead.txt");
// console.log(data.toString());

//Enter 단위로 분할해서 읽기
let ar =data.toString().split("\n");
console.log(ar)