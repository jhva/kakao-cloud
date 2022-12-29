const Sequelize = require('sequelize');
const env = process.env.NODE_ENV || 'development';
const config = require('../config/config.json')[env];
//만든 파일 import
const User = require('./user');
const Hashtag = require('./hashtag');
const Post = require('./post');


const sequelize = new Sequelize(
  config.database, config.username, config.password, config);


const db = {};

db.sequelize = sequelize;
db.Sequelize = Sequelize;


db.User = User;
db.Post = Post;
db.Hashtag = Hashtag;

//초기화작업
User.init(sequelize);
Post.init(sequelize);
Hashtag.init(sequelize)


//관계 설정
User.associate(db);
Post.associate(db);
Hashtag.associate(db);

module.exports = db;
