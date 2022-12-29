//암호화모듈
let crypto = require("crypto");
//단방향
let pw = "1234";

let result1 = crypto.createHash("sha256")
    .update(pw).digest('base64');

console.log(result1);


// let str = "1234";

// let result = crypto.createHash("sha256")
//     .update(str).digest('base64');

let str = "1234"
let result = crypto.createHash("sha256")
.update(str).digest("base64");


console.log(result1==result);

//암호화 모듈 가져오기 

const algorithm = "aes-256-cbc";
//node의 crypto 모듈에서는 key는 32자리 iv는 16자리
const key = "12345678912345678912345678912345";
const iv = "1234567891234567";

//암호화 객체 생성

let cipher = crypto.createCipheriv(algorithm,key,iv);
let rst = cipher.update('01047978158','utf8','base64');
rst +=cipher.final('base64');
console.log(rst);

//복호화 
let decipher = crypto.createDecipheriv(algorithm,key,iv);
let decipherResult = decipher.update(rst,'base64','utf8');
decipherResult+=decipher.final('utf8');
console.log(decipherResult,"원본");