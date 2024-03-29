const Sequelize = require('sequelize');

module.exports = class User extends Sequelize.Model {
    static init(sequelize) {
        //테이블에 대한 설정
        return super.init({
            //컬럼에 대한 설정
            name: {
                type: Sequelize.STRING(20),
                allowNull: false,
                unique: true,
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
            modelName: 'User',
            tableName: 'users',
            paranoid: false,
            charset:'utf8',
            collate:'utf8_general_ci'
        })
    }

    static associate(db) {
        //외래키에 대한 설정
        db.User.hasMany(db.Comment, { foreignKey: 'commenter', sourceKey: 'id' })
    }
}