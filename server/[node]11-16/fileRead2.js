// const fs = require('fs');
// // 여ㅛㅇ량이 큰 파일을 생성

// const file = fs.createWriteStream('./data.txt');

// for (let i = 0; i < 1000000; i++) {
//     file.write("용량이 큰 파일 만들기 \n")
// }
// file.end();



//스트림을 사용하지 않고 읽어서쓰기

// const fs = require('fs');

// console.log("복사하기 전 메모리 사용량"+process.memoryUsage().rss);

// const detail1 = fs.readFileSync('./data.txt');
// fs.writeFileSync('./normal.txt',detail1);


// console.log('복사하기 후 메모리 사용량:'+process.memoryUsage().rss);


// 스트림을 이용한 복사


const fs = require('fs');

console.log("복사하기 전 메모리 사용량" + process.memoryUsage().rss);

const readStream = fs.createReadStream('./data.txt');
const writeStream = fs.createWriteStream('./asdf.txt');

//읽고 쓰삼
readStream.pipe(writeStream);
readStream.on('end', () => {
    console.log('복사하기 후 메모리 사용량:' + process.memoryUsage().rss);

})

