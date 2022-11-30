const Sequelize = require('sequelize');

module.exports = class Post extends Sequelize.Model {
    static init(sequelize) {
        return super.init({
            content: {
                type: Sequelize.STRING(200),
                allowNull: false
            },
            img: {
                type: Sequelize.STRING(200),
                allowNull: true
            }
        }, {
            sequelize,
            timestamps: true,
            underscored: false,
            modelName: 'Post',
            tableName: 'posts',
            paranoid: true,
            charset: 'utf8mb4', // utf8mb4 는 이모티콘삽입가능
            collate: 'utf8mb4_general_ci'
        })
    }

    static associate(db) {
        // 유저와의 관계는 1대 N 관계 
        db.Post.belongsTo(db.User);
        // 해시태그와 관계는 다 대 다 
        //다대다 관계는 테이블이 생성되는데 through 가 테이블이름
        db.Post.belongsToMany(db.Hashtag, { through: 'PostHashtag' })

    }
} 