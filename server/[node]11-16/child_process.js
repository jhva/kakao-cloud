// //다른 프로세스를 실행할 수 있는 모듈을 가져오기
// const exec = require('child_process').exec;
// const os =require("os");
// console.log(os.platform());

// //프로세스 준비
// //widows에서는 dir 이 디렉토리의 목록을 확인하는 것이다
// // notepade.exe 는 메모장  
// // let process = exec('notepad.exe cryption.js');
// let process = exec('ls');


// // 프로세스가 정상적으로 수행되면
// process.stdout.on('data', function (data) {
//     console.log(data.toString());
// })

// //수행되지 않으면
// process.stderr.on('data', function (data) {
//     console.log(data.toString());
// })


const os = require('os');
let pos = os.type().toLocaleLowerCase().indexOf("linux")
if(pos>=0){
    console.log("linux")
}else{
    console.log("windows아님")
}

console.log(os.type());
[{},[]]