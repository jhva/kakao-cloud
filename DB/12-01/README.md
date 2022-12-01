# API Server 
> API (Application Programming Interface)
- 프로그램과 프로그램을 연결시켜주는 매개체
- 다른 애플리케이션을 개발할 수 있도록 도와주는 프로그램(Software,Development kit)
- SDK 
- win API 
    - windows application을 만들기위한 함수의 집합
- 프로그램 개발에 도움을 주도록 또는 여러프로그램에서 공통으로 사용되어야하는 데이터가 있는경우에는 프로그램이 아니라 데이터를 제공

- 누구나 등록만 할수있도록해주는것을 <b>Open API</b>라고 한다.
- 데이터를 제공할 때는 데이터베이스에 직접 접근하도록 하는 것이 아니고 애플레키에션 서버를 통해서 제공

### API Server 가 제공하는 데이터 포맷
- txt 또는 csv
    - csv: 일반 텍스트로 구분 기호를 포함하는 경우가 있음
    - 변하지 않는 데이터를 제공하는데 주로 이용
    - 가끔 txt나 csv대신에 excel 이나 hwp 또는 pdf 로 제공하는 경우가 있음
- xml
    - eXtensible Markup Language
        - 태그의 해석을 브라우저가 아닌 개발자 또는 개발자가 만든 라이브러리가 하는 형태로 문법이 HTML 보다는 엄격
    - HTML은 데이터로 사용하기에는 부적합 
        - HTML은 구조적이지 못하기 때문에 
    - 아직도 설정 파일 이나 데이터를 제공하는 용도로 많이 사용
- json
    - 자바스크립트 객체 형태로 표현하는 방식
    - XML 보다 가볍기 때문에 데이터 전송에 유리 
    - 자바스크립트 객체 표현법으로 데이터를 표현하기 때문에 Javascript나 Python에서는 파싱하는것이 쉬움
        - 설정 보다는 데이터를 제공하는 용도로 많이 사용 
    - Apple , Google ,Twitter 등은 데이터 전송에는 json 만 사용
- yaml
    - email 표기 형식으로 표현하는 방식
    - 계층 구조를 가진 데이터 표현에 유리
    - 구글의 프로그램들이 설정을 할 때 yaml 많이 이용 


### API Server 를 만들기 위한 기본 설정 

```
npm install express dotenv compression morgan file-stream-rotator multer cookie-parser express-session express-mysql-session mysql2 sequelize sequelize-cli nunjucks passport passport-kakao passport-local bcrypt uuid


npm install --save-dev nodemon

* bcrypt => 복호화가 불가능한 암호화를 위한 모듈
```

- free,premium 두가지만 구분하고자 하는 경우 자료형
```
boolean : true 와 false 이용해서 구분
int : free 와 premium을 0과 1 또는 1과 2 형태로 구분 
string : free와 premium을 문자열로 저장해서 구분
```


# session
> 서버에 저장하는 방법을 세션에 저장한다고 한다.

- 세션은 기본적으로 서버의 메모리에 저장이 된다 
- 세션의 키를 확인하면 어떤 클라이언트의 세션인지 확인이 가능하다 
- session 이 만들어질 때는 하나의 키를 생성해서 클라리엉ㄴ트의 쿠키에 전송을한다.
- request 객체 안에는 session 이라고 하는 속성이 있어서 이 속성안에 저장을 하는 것이다 
- 클라이언트의 접속 개수가 많아지면 session 의 개수도 많아지고 session 의 개수가 많아지면 서버가 사용할 수 있는 메모리의 양이 줄어들어서 성능이 저하될 수 있다 .
- session 을 메모리에 저장하면 서버가 종료되었다가 다시 실행되면 모든 세션이 소멸된다.
- 메모리를 효율적으로 사용하고 종료되었다가 다시 부팅이 되었을 때도 기억하고자 하면 플랫 파일이나 데이터베이스르 이용한다.



## 11-30 일 이어서 진행 (카카오로그인)

- findOne 등등 include 관련 찾아본정보 (정훈)
    - https://sequelize.org/docs/v6/advanced-association-concepts/eager-loading/

```javascript
const passport = require('passport');
const KakaoStragegy = require("passport-kakao").Strategy;


//유저정보

const User = require('../models/user');


//카카오 로그인 구현

module.exports = () => {
    passport.use(new KakaoStragegy({
        clientID: process.env.KAKAO_ID,
        callbackURL: '/auth/kakao/callback' // 
    }, async (accessToken, refreshToken, profile, done) => {
        console.log('카카오 프로필', profile);
        try {
            //이전에 로그인한 적이 있는지 찾기 위해서
            //카카오 아이디와 provider가 kakako 되어있는
            //데이터가 있는지 조회
            const exUser = await (User.findOne({
                where: { snsId: profile.id, provider: 'kakao' }
            }))

            if (exUser) {
                done(null, exUser)
            } else {
                //이전에 로그인 한 적이 없으면 데이터를 저장 
                const newUser = await User.create({
                    email:profile._json.kakao_account.email,
                        nick: profile.displayName,
                        snsId: profile.id,
                        provider: 'kakao'
                    })
                    done(null, newUser);
                }

            } catch (error) {
                console.error(error);
                done(error);
        }
    }))
}
```





