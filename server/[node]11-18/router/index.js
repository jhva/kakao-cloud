const express =require('express');
const router = express.Router();

router.get('/',(req,res)=>{
    res.send("헬로우메인")
})

module.exports=router;