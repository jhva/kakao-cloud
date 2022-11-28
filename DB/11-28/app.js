const express = require("express");
const path = require('path');

const { sequelize } = require("./models");

const app = express();

app.set('port', process.env.PORT || 3000);

//sequelize  연결
sequelize.sync({ force: false })
    .then(() => { console.log("sequelize 연결완룡") })
    .catch((err) => { console.log(err, "연결실패") })

app.listen(app.get('port'),()=>{
    console.log(app.get("port"),'포트연결완료')
})