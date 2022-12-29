# Node + MongoDB + mongoose(Node의 ODM)
- RDBMS 와 NoSQL의 차이 
    - RDBMS 는 스키마를 생성하고 데이터를 저장하는 형식을 취하지만 NoSQL은 스키마를 생성하지않고 데이터를 저장하는 것이가능 
        - RDBMS 는 데이터의 값으로 다른 테이블의 데이터나 배열을 삽입 할 수 없기 때문에 테이블을 분할 하고 foreign Key와 join 의 개념을 이용해서 여러종류의 데이터를 저장함.
        - 일반적으로 엄격한 트랜잭션을 적용
    - NoSQL 
        - 복잡한 거래가 없는 경우나 비정형 데이터만을 저장하기 위한 용도로 많이 사용한다.
        - Join 대신에 Embedding이나 Linking의 개념을 사용
        - 스키마의 구조가 변경이 되도 데이터 구조를 변경할 필요없이 데이터 저장이 가능
        - 데이터의 값으로 객체 나 배열이 가능하기 때문에 하나의 Collection에 여러 종류의 데이터를 저장할 수 있어서 join을 하지 않아도 되기 때문에 처리속도가 빠를 수 있다 .

## ODM
> Relation 이라는 개념 대신에 Document 를 하나의 객체에 매핑하는방식
- 하나의 Document에 대한 모양을 만들고 사용해야 하기 때문에 NoSQL의 Collection도 하나의 정형화 된 모양을 가져야한다.
- MongoDB 에서 ODM 을 사용할 수 있도록 해주는 대표적인 라이브러리가 mongoose

```
한번 해보라는 말씀이있으셨음
```


# Authentication
> Authenctication (인증) Authorization(인가)
- 인증
    - 계정 관련
    - 로그인 관련
- 인가 
    - 권한 

### 인증을 구현하는 방법

- 로컬 로그인 
    - 회원 정보를 저장하고 있다가 인증
    - 회원 정보를 저장할 때 비밀번호는 복호화가 불가능한 방식을 사용하고 개인을 식별할 수 있는 정보를 마스킹 처리를 하거나 복호화가 가능한 방식의 암호화를 활용해야 한다 .
- OAuth 로그인
    - 다른 서버에 저장된 인증 정보를 활용해서 인증을 하는방식


### 인증일 위한 프로젝트 기본 설정
> 로그인을 할 수 있도록 회원가입을 하고 로그인 처리를 수행하고 간단한 글 과 파일을 업로드 할 수 있는 프로젝트

```
npm init

npm install npm install express morgan dotenv compression morgan file-stream-rotator multer cookie-parser express-session express-mysql-session

npx sequelize init 

public 
routes
views add 
```

### 프로젝트에 .env파일을 생성하고 작성
- 소스코드에 노출되서 안되는 내용이나 개발 환경에서 운영환경으로 이행(Migration)할 때 변경될 내용을 작성하는데 이 내용은 실행 중에는 변경되지 않는 내용이여야한다.
- 대표적인 내용이 데이터베이스 접속 정보 나 암호화를 하기 위한 키 또는 서버 포트 번호와 같은 것들 이다 .
- 이러한 내용은 대부분 실행 중에는 변경되지 않지만 개발 환경에서 운영 환경으로 이행 할 때 변경될 가능성이 높은 내용이다 


## 실습 
- 기본적인 app.js 서버설정, views파일에 정적파일 기입 

### 데이터베이스
- 테이블 구조 
    - 회원테이블 
        - 이메일
        - 닉네임 
        - 패스워드 
        - 생성시간
        - 수정시간
        - 삭제시간 
            - 삭제할때 실제 지워지지않고 삭제한 시간을 기록할부분
    - 포스트 테이블
        - 게시글 내용
        - 이미지 파일의 경로 
    - HashTag테이블 
        - 태그이름 
- 테이블 관계
    - User 와 Post 는 1:N 관계
    - HashTag 와 Post 는 N:M 관계
    - User 와 User는 N:N 관계 
- 로그인 방법 
    - 직접 로그인 or 카카오 로그인 여부를 저장
- 카카오아이디 

```javascript
*user.js
 db.user.belongsToMany(db.User, {
            foreignKey: 'followingId',
            as: 'Followers',
            through: 'Follow'
        })
// 다대다 관계시 이렇게 두개를 만들어줘야함
        db.user.belongsToMany(db.User, {
            foreignKey: 'followerId',
            as: 'Followings',
            through: 'Follow'
        })

* post.js


  //다대다 관계는 테이블이 생성되는데 through 가 테이블이름
        db.Post.belongsToMany(db.Hashtag, { through: 'PostHashtag' })
```

- 디비 연결

```javascript
const {sequelize} =require('./models');
sequelize.sync({force:false})
    .then(()=>{
        console.log("디비 접속 성공!")
    })
    .catch((err)=>{
        console.log(err);
    })

```

- 데이터베이스가 존재하지 않는 경우는 아래명어를 한 번 수행
```
npx sequelize-cli db:create
```


### Passport 모듈 
- node 에서 인증 작업을 도와주는 모듈
- 세션이나 쿠키 처리를 직접하지 않고 이 모듈의 도움을 받으면 쉽게 구현이 가능하다
- Social 로그인 작업을 쉽게 처리할 수 있도록 해준다.(Oauth)

- 인증작업
    - 로그인에 성공하면 세션을 생성해서 세션에 아이디 나 기타 정보를 저장하고 다음부터 로그인을 확인할 때는 세션의 정보가 있는지를 확인해서 로그인 여부를 판단하고 로그아웃을 하면 세션의 정보를 삭제한다.


### 로컬 로그인 구현
> 필요한 모듈 설치 
- passport,passport-local,bcrypt(암호화를 해서 비교는 가능하지만 복호화는 안되는 암호화모듈)
- app.js 파일에 Passport 모듈을 사용할 수 있는 설정을 추가


### Passport 모듈 사용 설정
- passport 디렉토리만들기

```javascript
module.exports = () => {
    //로그인 성공했을 때 정보를 deserializeUser 함수에게 넘기는 함수 
    passport.serializeUser((user, done) => {
        done(null, user.id);
    })

    // 넘어온 id 에 해당하는 데이터가 있으면 데이터베이스에서 찾아서 
    // 세션에 저장함 

    passport.deserializeUser((id, done) => {
        User.findeOne({ where: { id } })
            .then(user => done(null, user))
            .catch(err => done(err));
    })
    local()
}
```
- 위 처럼 하게되면 로그인 여부를 request 객체의 <b>isAuthenticated()</b>함수로 할 수 있게 된다.


### 로그인 여부 판단
- 웹 어플리케이션을 구현하게 되면 로그인 여불를 판단해서 작업을 해야 하는 경우가 발생하는데 이는 비지니스 로직과는 상관이 없음
- 이런 부분은 별도로 처리해야한다.
- node는 이런 로직을 middleware로 처리하고 java web에서는 filter 로 처리하고 spring 에서는 AOP나 Interceptor 를 이용해서 처리한다.

### 로그인 여부를 판단할 수 있는 함수를 routes 디렉토리의 middlewares.js 파일을 추가하고 작성
```javascript
* middlewares.js

exports.isLoggedIn = (req, res, next) => {
    if (req.isAuthenticated()) {
        console.log("로그인 되어있음")
        next();
    } else {
        res.status(403).send("로그인 필요해 바보야")
    }
}

exports.isNotLoggedIn = (req, res, next) => {
    if (!req.isAuthenticated()) {
        console.log("로그인 안되어있음")
        next();
    } else {
        const message = encodeURIComponent("로그인 한 상태입니다")
        res.redirect(`/?error=${message}`)
    }
}
```

### routes 디렉토리의 page.js를 수정한다.
```javascript
//메인화면 
router.get("/", (req, res, next) => {

    const twits = [];
    //res.render('뷰이름',데이터)
    res.render('main', { title: "Node Authentication", twits })
})

// 회원가입  
// 로그인이 되어있지 않은 경우에만 수행
router.get('/join', isNotLoggedIn, (req, res, next) => {

    res.render('join',
        { title: '회원가입 - Node Authentication' })
})


// 프로필 화면처리 

// 로그인 되어있는경우에만
router.get('/profile', isLoggedIn, (req, res, next) => {
    res.render('profile',
        { title: '나의 정보 - Node Authentication' })
})
```

### 회원가입, 로그인 ,로그아웃 처리를 위한 내용을 routes 디렉토리에 auth.js 파일을 만들고 작성
- page.js 는 화면을 보여주는 역할을 하고 auth.js 는 처리하는 역할을 하도록 분리하는 것이다.
```javascript
const express = require('express');

const passport = require('passport'); // 로그인 및 로그아웃 처리를 위해서 가져오기
const bcrypt = require('bcrypt'); // 회원 가입을 위해서 가져오기

const { isLoggedIn, isNotLoggedIn } = require('./middlewares');

const User = require('../models/user');

const router = express.Router();


//회원 가입 처리 

router.post('/join', isNotLoggedIn, async (req, res, next) => {
    // 데이터 찾아오기 
    const { email, nick, password } = req.body;
    try {
        const exUser = await User.findOne({ where: { email } });
        if (exUser) {//이메일 존재여부 

            //회원가입페이지로 리다이렉트  
            // err 키에 메시지를 가지고 이동
            return res.redirect('/join?error=exist');
        } else {
            //비밀번호 해싱
            const hash = await bcrypt.hash(password, 12);
            // 저장 
            await User.create({ email, nick, password: hash })

            // success 
            return res.redirect('/');
        }
    } catch (err) {
        console.log(err)
        return next();
    }

})

//로그인 처리 

router.post('/login', isNotLoggedIn, (req, res, next) => {
    //passport 모듈을 이용해서 로그인 
    passport.authenticate('local', (authErr, user, info) => {
        if (authErr) {
            console.error(authErr);
            return next(authErr);
        }
        // 일치하는 User 가 없을때 
        if (!user) {
            return res.redirect(`/?loginError=${info.message}`);
        }
        return req.login(user, (logginErr) => {
            if (logginErr) {
                console.error(logginErr);
                return next(logginErr);
            }
            //로그인 성공하면 메인페이지로 이동
            return res.redirect('/')
        })

    })(req, res, next)
})


//로그아웃 처리 
router.get('/logout', isLoggedIn, (req, res, next) => {
    req.logout((err) => {
        if (err) {
            return next(err);
        }
        //세션 초기화 
        res.session.destroy();
        res.redirect('/');
    })
})

module.exports = router;
```

### passport 를 이용한 모듈  로컬스토리지  

```javascript
//로컬 로그인 관련된 기능 구현 

const passport = require('passport');

const LocalStrategy = require('passport-local');
const bcrypt = require('bcrypt');

const User = require('../models/user');



module.exports = () => {
    passport.use(new LocalStrategy({
        usernameField: 'email',
        passwordField: 'password'
    }, async (email, password, done) => {
        try {
            const exUser = await User.findOne({ where: { email } });
            if (exUser) {
                //비밀번호 비교 
                const result = await bcrypt.compare(password, exUser.password);
                if (result) {
                    done(null, exUser);
                } else {
                    done(null, false, { message: "비밀번호가 틀립니다" })
                }
            } else {
                done(null, false, { message: "회원정보가 없습니다" })
            }
        } catch (err) {
            console.error(err)
        }
    }))
}
```

### app.js 파일에 로그인  관련 라우터 등록


### 카카오로그인 passport 

```
npm install passport-kakao
```

- RestAPI 키 복사 
- 플래폼 등록
    - web 에 자신의 도메인 과 포트번호 등록
- 로그인 활성화
    - 로그인 활성화 on 
    - Redirect URI 활성화 