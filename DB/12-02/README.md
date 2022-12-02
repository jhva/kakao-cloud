# JWT 12-01 이어서 진행

```javascript
router.post('/token', async (req, res) => {
    const { clientSecret } = req.body;
    try {
        //도메인 찾기 
        const domain = await Domain.findOne({
            where: { clientSecret },
            include: {
                model: URLSearchParams,
                attribute: ['nick', 'id']
            }
        })

        if (!domain) {
            return res.status(401).json({
                code: 401,
                message: "등록되지 않는 도메인입니다."
            })
        }
        // 토큰 생성

        const token = jwt.sign({
            id: domain.User.id,
            nick: domain.User.nick
        }, process.env.JWT_SECRET, {
            expiresIn: '1m',
            issuer: 'kjh' //누가발급했니 ?
        })

        return res.json({
            code:200,
            message:'토큰 생성완료',token
        })

    } catch (error) {
        console.error(error)
    }
})


//토큰을 확인하기 위한 처리 
router.get('/test', verifyToken, (req, res) => {
    res.json(req.decoded);
})

module.exports = router;

```
- apiserver를 만들었을때 데이터를 무제한 제공하게 되면 트래픽이 많이 발생해서 속도가 느려질수있음.
- 디도스 공격의 대상이 될수도있음.
- 사이즈ㅏ 횟수제한을 가하기도함

### 기존 서버를 수정했을 때 처리 
- 기존 코드를 무조건 바꾸는 것은 위험하다
- 기존 코드는 그대로 두고 deprecated 나 서비스 중지 메시지를 
전송하는 형태로 새로운 내용을 적용하는것이좋다.


```javascript

///사용량 제한을 위한 미들웨어 

const RateLimit = require('express-rate-limit');

exports.apiLimiter = RateLimit({
  windowMs: 60 * 1000, //1분,
  max: 10,// 1분안에 10번이상못함
  delayMs: 0,
  handler(req, res) {
    res.status(this.statusCode).json({
      code: this.statusCode,
      message: '1분 단위로 요청을 해야함여'
    })
  }
});

//구버전 api 요청시 동작할 미들웨어 

exports.deprecated = (req, res) => {
  res.status(410).json({
    code: 410,// 더이상없다 ,
    message: '새로운 버전이 나왔다. 새버전을 사용하셈'
  })
}

//해당 url 디렉토리에서 
router.use(deprecated)
```

# CORS(Cross-Origin Resource Sharing)
> SOP (Same Origin Policy -동일 출처 정책)

- 어떤 출처에서 불러온 문서나 스크립트가 다른 출처에서 가져온 리소스와 상호작용 하는것을 제한하는 브라우저의 보안방식
- 브라우저에서는 XMLHttpRequest(ajax)와 Fetch Api 같은 경우는 다른 출처에 리소스를 요청할 때 적용
- img,link,script,video,audio,object,embed,applet 태그는 SOP적용을 받지않음.

- 추가 HTTP 헤더를 사용해서 한 출처에서 실행 중인 웹 애플리케이션이 다른 출처의 자원에 접근할 수 있는 권한을 부여해서 브라우저에 알려주는 것.
    - ajax나 Fetch API 가 다른 출처의 데이터를 가져와서 사용하기 위해서는 올바른 CORS 헤더를 포함한 응답을 반환해야 한다 .