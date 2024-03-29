const express = require('express');
//파일 업로드 를위한모듈
const multer = require('multer');
const path = require('path');
const fs = require('fs');
//데이터 삽입을 위한 모듈 
const { Post, Hashtag } = require('../models');


//로그인 여부 판단

const { isLoggedIn } = require('./middlewares');



const router = express.Router();


//파일을 업로드할 디렉토리가 없으면 생성

try {
    fs.readdirSync('public/img');
} catch (err) {
    fs.mkdirSync('public/img');
}

//파일 업로드 객체

const upload = multer({
    storage: multer.diskStorage({
        destination(req, file, cb) {
            cb(null, 'public/img/')
        },
        filename(req, file, cb) {
            const ext = path.extname(file.originalname);
            cb(null, path.basename(file.originalname, ext) + Date.now() + ext);
        }
    }), limits: { fileSize: 10 * 1024 * 1024 }
})

//이미지 업로드 
router.post('/img', isLoggedIn, upload.single('img'), (req, res) => {
    console.log(req.file);
    res.json({
        'result': true,
        url: `/img${req.file.filename}`
    })
})

//게시글 업로드

const upload2 = multer();
router.post('/', upload2.none(), async (req, res, next) => {
    try {
        //게시글업로드
        const post = await Post.create({
            content: req.body.content,
            img: req.body.url,
            UserId: req.user.id
        })
        //해시태그찾기
        const hashtags = req.body.content.match(/#[^\s#]*/g);
        //# 으로 시작하느거 다찾아줌 
        if (hashtags) {
            const result = await Promise.all(
                //배열의 전체 데이터를 순서대로 대입해서 
                // {} 안의 내용을 수행
                hashtags.map(tag => {
                    //없으면삽입 있으면 삽입안함
                    return Hashtag.findOrCreate({
                        where: {
                            title: tag.slice(1).toLowerCase()
                        }
                    })
                })

            );
            await post.addHashtags(result.map(r => r[0]))
        }
        res.redirect('/');
    } catch (error) {
        console.log(error)
        next(error)
    }
})

module.exports = router;

