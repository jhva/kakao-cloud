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


