let today = new Date();

console.log(today)//오늘날짜
console.log(today.getFullYear());
console.log(today.getMonth() + 1); //월은 꼭 찍어야함 결과가 10월이기때문에 +1을꼭해주자
console.log(today.getDate());

let year = String(today.getFullYear());
let month = String(today.getMonth() + 1);
let day = String(today.getDate());

const completeToday = year + month + day;


//오늘 날짜로 된 디렉토리가 없으면 생성

const fs = require('fs');

fs.access(completeToday, (err) => {
    if (err) {
        console.log("디렉토리 없다")
        fs.mkdir(completeToday, (err) => {
            if (err) { console.log("디렉토리 만들기 실패") }
            else { console.log("디렉토리 만들기 성공") }
        })
    } else {
        console.log("디렉토리 존재");
    }
})