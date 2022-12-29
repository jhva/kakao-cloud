## 데이터 상세보기 
- 데이터 한개의 정보를 전부 가져와서 출력
- 테이블에서 데이터 1개를 가져오는 방법은 기본키 나 unique 속성을 이용한 조회만 가능
- 기본키나 unique 속성의 값을 받아서 서버에서 처리
- itemid 를 URL 에 포함시켜 받아서 처리하는 구조로 구현

```javascript
//데이터 상세보기
app.get('/data/item/:itemid', (req, res) => {
    //파라미터 읽기
    let itemid = req.params.itemid;
    console.log(itemid);

    // itemid를 이용해서 1개의 데이터를 찾아오는 sql 실행 
    connection.query("select * from goods where itemid=?", itemid, (err, results, fields) => {
        if (err) {
            console.log(err);
            res.json({ "result": false });
        } else {
            res.json({ "result": true, "item": results[0] });
        }
    })
});

```

### 새롭게 알게된부분 startsWith
- startsWith
    - 어떤 문자열이 특정 문자로 시작하는지 확인하여 결과를 true혹은 false로 반환
    - 참고 : https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/String/startsWith

```javascript
 content.addEventListener("click", (e) => {
            //클릭한 대상의 id가  item으로 시작하는 경우에만 동작해주쑤ㅖ용
            if (e.target.id.startsWith('item')) {
                //클릭한 대상의 아이디에서 item을 제외한 부분 - itemid
                let itemid = e.target.id.substring(4).trim();
                console.log(e.target.id)
                alert(itemid);

            }
        })
```

### 첨부 파일 다운로드



### 데이터 삽입
- 데이터 삽입은 삽입 화면을 먼저 출력하고 데이터를 입력받은 후 데이터를 서버에게 전송하면 처리
- 데이터 삽입은 get방식이 post방식으로 처리 
```

```

- itemid:가장 큰 itemid를 찾아서 +1
- itemname,price,description,picureurl은 직접입력
- updatedate 는 현재 날짜를 문자열로 입녉
- 삽입 , 삭제, 갱신 작업이 발생하면 update.txt파일에 발생한 시간을 기록
    - 현재 데이터가 업ㄷ데이트 된 시간을 알기 위해서 기록 
- server.js 파일에 현재 날짜를 문자열로 리턴하는 함수와 현재 날ㅉ ㅏ및 시간을 문자열로 리턴하는 함수 작성
```javascript 

app.post('/data/post', upload.single('pictureurl'), (req, res) => {
    //파라미터 읽어오기
    const itemname = req.body.itemname;
    const descrription = req.body.description;
    const price = req.body.price;

    //파일이름 - 업로드하는 파일이 없으면 default.png

    let pictureurl;
    if (req.file) {
        pictureurl = req.file.filename
    } else {
        pictureurl = 'default.png';
    }

    connection.query("select max(itemid) maxid from goods",
        [], (err, results, fields) => {
            let itemid;
            //최대값이 있으면 +1 하고 없으면 1로설정
            if (results.length > 0) {
                itemid = results[0].maxid + 1;
            } else {
                itemid = 1;
            }
            connection.query("insert into goods(itemid,itemname,price,description,pictureurl,updatedate) values(?,?,?,?,?,?)", (
                [itemid, itemname, price, descrription, pictureurl, getDate()], (err, results, fields) => {
                    if (err) {
                        console.log(err);
                        res.json({ "results": false });
                    } else {
                        //현재 날짜 및 시간을 update.txtdp rlfhr
                        const writeStream = fs.createWriteStream('./update.txt');
                        writeStream.write(getTime());
                        writeStream.end();

                        res.json({ "results": true });

                    }
                }
            ))
        })
})


const getDate = () => {
    let date = new Date();
    let year = date.getFullYear();
    let month = date.getMonth() + 1;
    let day = date.getDate();

    month = month >= 10 ? month : '0' + month;
    day = day >= 10 ? day : '0' + day;
    
    return year + "-" + month + "-" + day;
}
//날짜 와 시간을 리턴하는 함수
const getTime = () => {
    let date = new Date();
    let hour = date.getHours();
    let minute = date.getMinutes();
    let second = date.getSeconds();

    hour >= 10 ? hour : '0' + hour;
    minute >= 10 ? minute : '0' + minute;
    second >= 10 ? seconde : '0' + second;

    return getDate() + " " + hour + ":" + minute + ":" + second;
}
```



- 24일 이어서 진행