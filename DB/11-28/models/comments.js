const Sequelize = require('sequelize');

module.exports = class Comment extends Sequelize.Model {
    static init(sequelize) {
        //테이블에 대한 설정
        return super.init({
            //컬럼에 대한 설정
            comment: {
                type: Sequelize.STRING(100),
                allowNull: false,
            },
            age: {
                type: Sequelize.INTEGER,
                allowNull: false,
            }
        }, {
            //테이블에 대한 설정

            sequelize,
            //생성날짜 자동으로
            timestamps: true,
            //프로그램에서 사용할 모델이름
            modelName: 'Comment',
            tableName: 'comments',
            paranoid: false,
            charset:'utf8',
            collate:'utf8_general_ci'
        })
    }

    static associate(db) {
        //외래키에 대한 설정
        db.Comment.belongsTo(db.User, { foreignKey: 'commenter', targetKey: 'id' })
    }
}