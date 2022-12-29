//파일을 읽고 쓸 수 있는 모듈 가져오기 
const fs = require('fs');
let data = fs.readFileSync("./textRead.txt");
// console.log(data.toString());

//Enter 단위로 분할해서 읽기
let ar = data.toString().split("\n");
console.log(ar)

//콜백 
// fs.readFile('./textRead.txt', (error, data) => {
//     console.log(data.toString());
//     if(error){
//         console.log(error.message)
//     }
// })
// console.log("vkdlf")



//promise
// fs.readFile('./textRead.txt').promises;
//     .then((data) => { console.log(data.toString()); })
//     .catch((error) => { console.log(error.message) })






//스트림을 이용한 읽기방법
const readStream = fs.createReadStream("./textRead.txt", { highWaterMark: 16 });

// 데이터를 저장하기 위한 객체를 생성
const streamData = [];

//읽는 동안 발생하는 이벤트를 처리
readStream.on('data', (chunk) => {
    //읽는 동안에는 읽어온 데이터를 추가
    streamData.push(chunk);

})

//읽기 끝나면 발생하는 이벤트를 처리
readStream.on('end', () => {
    //지금 까지 읽은 내용을 하나로 만들기 
    let endData = Buffer.concat(streamData);
    console.log(endData.toString())

})

//에러가  발생하는 이벤트를 처리
readStream.on('error', (err) => {
    console.log(err.message);
})