const Sequelize = require('sequelize');

module.exports = class Domain extends Sequelize.Model {
    static init(sequelize) {
        return super.init({
            host: {
                type: Sequelize.STRING(100),
                allowNull: false,
            },
            clientSecret: {
                type: Sequelize.STRING(36),
                allowNull: false,
            },
            type: {
                type: Sequelize.ENUM('free', 'premium'),
                allowNull: false,
            }

        }, {
            sequelize,
            timestamps: true,
            underscored: false,
            modelName: 'Domain',
            tableName: 'domains',
            paranoid: true,
        });
    }
    static associate(db) {
        //유저의 기본키가 domain의 외래키에 추가된다 
        //1:N
        db.Domain.belongsTo(db.User);
    }
};

