const mariadb = require('mysql');

let connection = mariadb.createConnection({
    host: '192.168.0.156',
    port: 3306,
    user: 'root',
    password: '1234',
    database: 'kjh'
})


//연결

connection.connect((err) => {
    if (err) {
        console.log(err);
        //에러발생했을떄
    } else {
        // console.log(connection);

        // //테이블 생성구문
        // connection.query('create table family(id int auto_increment primary key, name varchar(100))');
        // //데이터 삽입 구문 
        // connection.query('insert into family(name)values(?)','을지문덕');
        // connection.query('insert into family(name)values(?)','김정훈');
        // connection.query('insert into family(name)values(?)','오시훈');

        //select 구문 
        connection.query("select * from family ", (err, result, fields) => {
            if (err) {
                //이렇게하면 서버에서밖에안보인다.
                //클라이언트 한테 보내줘야함
                console.log("{result:false}");
            } else {
                //json문자열로 주겠다는얘기

                let results = JSON.stringify(result);
                console.log(results);
                console.log(fields);
            }
        })
    }
})


//데이터베이스 연결이 완료되었을떄 