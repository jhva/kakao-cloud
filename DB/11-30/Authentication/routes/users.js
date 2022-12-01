const express = require('express');
const User = require('../models/user');
const { isLoggedIn } = require('./middlewares')

const router = express.Router();

router.post('/:id/follow', isLoggedIn, async (req, res, next) => {
    try {
        //현재 로그인 한 유저를 찾는다 .
        const user = await
            User.findOne({ where: { id: req.user.id } });

        if (user) {
            //유저가 없다하면 추가한다.
            //팔로우로 추가 

            await
                user.addFollwing(parseInt(req.params.id, 10))//10진수로 바꾸는작업
            res.send('success');


        } else {
            res.status(404).send('회원이 없습니다 ')
        }
    } catch (error) {
        console.error(error);
        next(error);
    }
})

module.exports = router;