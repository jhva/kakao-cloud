const express = require('express');
const { isLoggedIn, isNotLoggedIn } = require('./middlewares');

const router = express.Router();

//공통된 처리 - 무조건 수행

router.use((req, res, next) => {
    //로그인 한 유저정보 

    //유저정보를 res.locals.user 에 저장
    res.locals.user = req.user;

    //게시글을 follow 하고 되고 있는 개수 
    res.locals.followCount = 0;
    res.locals.followingCount = 0;
    //게시글을 follow 하고 있는 유저들의 목록
    res.locals.followerIdList = []

    next();
})


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

module.exports = router;