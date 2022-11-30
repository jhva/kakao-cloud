const express = require('express');

const passport = require('passport'); // 로그인 및 로그아웃 처리를 위해서 가져오기
const bcrypt = require('bcrypt'); // 회원 가입을 위해서 가져오기

const { isLoggedIn, isNotLoggedIn } = require('./middlewares');

const User = require('../models/user');

const router = express.Router();


//회원 가입 처리 

router.post('/join', isNotLoggedIn, async (req, res, next) => {
    const { email, nick, password } = req.body;
    try {
        const exUser = await User.findOne({ where: { email } });
        if (exUser) {
            return res.redirect('/join?error=exist');
        }
        const hash = await bcrypt.hash(password, 12);
        await User.create({
            email,
            nick,
            password: hash,
        });
        return res.redirect('/');
    } catch (error) {
        console.error(error);
        return next(error);
    }
});

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