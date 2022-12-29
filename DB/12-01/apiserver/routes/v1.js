const express = require('express')
const { verifyToken, deprecated } = require('./middlewares')
const router = express.Router();
const jwt = require('jsonwebtoken');
const { Domain, User, Post, Hashtag } = require('../models');

//모든 라우팅 처리에서 deprecated 처리 
router.use(deprecated)

//데이터를 리턴하는 요청 처리 
router.get('/posts/my', verifyToken, (req, res) => {
    Post.findAll({ where: { userId: req.decoded.id } })
        .then((posts) => {
            console.log(posts);
            res.json({ code: 200, payload: posts })
        })
        .catch((err) => {
            console.error(err);
            res.status(500).json({
                code: 500,
                message: "서버 에러"
            })
        })

})



//토큰ㅁ발급
router.post('/token', async (req, res) => {
    const { clientSecret } = req.body;
    console.log(clientSecret, "!23")
    try {
        //도메인 찾기 
        const domain = await Domain.findOne({
            where: { clientSecret },
            include: {
                model: User,
                attribute: ['nick', 'id'],
            },
        });

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
            code: 200,
            message: '토큰 생성완료', token
        })

    } catch (error) {
        console.error(error)
        return res.status(500).json({
            code: 500,
            message: "토큰 생성 오류"
        })
    }
})


//토큰을 확인하기 위한 처리 
router.get('/test', verifyToken, (req, res) => {
    res.json(req.decoded);
})

module.exports = router;




/*
5
4 1 5 2 3
5
1 3 7 9 5
4
12345  7  9 
1
1
0
0
1


N개의 정수 A[1], A[2], …, A[N]이 주어져 있을 때,

이 안에 X라는 정수가 존재하는지 알아내는 프로그램을 작성하시오.

입력
첫째 줄에 자연수 N(1 ≤ N ≤ 100,000)이 주어진다.
 다음 줄에는 N개의 정수 A[1], A[2], …, A[N]이 주어진다.
  다음 줄에는 M(1 ≤ M ≤ 100,000)이 주어진다. 
  다음 줄에는 M개의 수들이 주어지는데,
  이 수들이 A안에 존재하는지 알아내면 된다.
   모든 정수의 범위는 -231 보다 크거나 같고 231보다 작다.
   N개의 정수 A[1], A[2], …, A[N]이 주어져 있을 때,
    이 안에 X라는 정수가 존재하는지 알아내는 프로그램을 작성하시오.

입력
첫째 줄에 자연수 N(1 ≤ N ≤ 100,000)이 주어진다. 다
음 줄에는 N개의 정수 A[1], A[2], …, A[N]이 주어진다. 
다음 줄에는 M(1 ≤ M ≤ 100,000)이 주어진다. 
다음 줄에는 M개의 수들이 주어지는데, 
이 수들이 A안에 존재하는지 알아내면 된다.
 모든 정수의 범위는 -231 보다 크거나 같고 231보다 작다.
1234579
11001
16+8+2ㅌ
arr[0] =4 
arr[1] =1
arr[2]= 5
arr[3]=2
arr[4]=3
1
1
0
0
1
*/