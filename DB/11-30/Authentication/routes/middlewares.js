// exports.isLoggedIn = (req, res, next) => {
//     if (req.isAuthenticated()) {
//         console.log("로그인 되어있음")
//         next();
//     } else {
//         res.status(403).send("로그인 필요하다고 바보야")
//     }
// }

// //로그인 하지 않는 경우를 판단
// exports.isNotLoggedIn = (req, res, next) => {
//     if (!req.isAuthenticated()) {
//         console.log("로그인 안되어있음")
//         next();
//     } else {
//         const message = encodeURIComponent("로그인 한 상태입니다")

//         //리다이렉트는 이전 request 객체의 내용을 모두 삭제하고 
//         // 새로운 요청흐름을 만드는것이다 .새로고침을 하면 결과 화면만 새로고침이된다,
//         // 결과 화면만 새로고침됩니다.
//         res.redirect(`/?error=${message}`)
//     }
// }


exports.isLoggedIn = (req, res, next) => {
    //로그인 되어 있으면 다음 라우터 처리를 수행하고 그렇지 않으면 에러 발생
    if (req.isAuthenticated()) {
        console.log("로그인 되어있음")
        next();
    } else {
        res.status(403).send("로그인 필요하다고 바보야")
    }
};
exports.isNotLoggedIn = (req, res, next) => {
    //로그인 되어 있지 않았다면 다음으로 넘어가고 그렇지 않으면 리다이렉트
    if (!req.isAuthenticated()) {
        console.log("로그인 안되어있음")
        next();
    } else {
        const message = encodeURIComponent('로그인한 상태입니다.');
        res.redirect(`/?error=${message}`);
    }
};