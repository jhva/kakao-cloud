const path = require('path');
const express = require("express");
const User = require('./models/users');
const Comment = require("./models/comments");


const { sequelize } = require("./models");

const app = express();

app.set('port', process.env.PORT || 3000);

//sequelize  연결
sequelize.sync({ force: false })
    .then(() => { console.log("sequelize 연결완룡") })
    .catch((err) => { console.log(err, "연결실패") })

app.get('/', (req, res) => {
    const user = User.create({ name: "개바보", age: 24 })
    console.log(user)
})


app.listen(app.get('port'), () => {
    console.log(app.get("port"), '포트연결완료')
})