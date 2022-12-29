# Node (ORM)
- 객체 지향 패러다임을 관계형 데이터베이스에 적용하는 기술
- 관계형 데이터베이스의 Table은 객체 지향 프로그래밍의 클래스와 유사한데 table에서는 여러 개의 컬럼을 만들지만 class에서는 속성을 만들어서 저장하는 것이 유사
    - 이런 이유 때문에 instance 와 Row 가 유사
- node에서는 <b>sequelize</b>모듈이 이러한 작업을 수행할 수 있다. 

### Sequelize를 이용한 하나의 테이블 연동
- 패키지 설치
    - sequelize , sequelize-cli,mysql2

### Sequelize 초기화
```
 npx sequelize init 
```    
- 초기화 실행 시 config,migration,models,seeders디렉토리가 생성됨
    - config
        - 데이터베이스 접속 정보 설정
    - models 
        - 각 테이블 과 매핑되는 클래스를 설정
    - migrations
        - 데이터베이스 스키마(구조,테이블)가 변경되는 경우를 위한 설정
    - seeders
        - 테스트 데이터 사용을 위한 설정

### 데이터베이스 접속 설정
```javascript
  "development": {
    "username": "",
    "password": ,
    "database": "",
    "host": "",
    "dialect": ""
  }
```

### models 디렉토리 수정
```javascript
//모듈 import
const Sequelize = require('sequelize');
//모델 가져오기
const Good = require('./good');

//환경 설정
const env = process.env.NODE_ENV || 'development';
//환경 설정 내용 가져오기
const config = require('../config/config.json')[env];
//내보낼 객체 생성
const db = {};
//ORM 설정
const sequelize = new Sequelize(
  config.database, config.username, config.password, config);

db.sequelize = sequelize;

db.Sequelize = Sequelize;

db.Good = Good;
Good.init(sequelize);

module.exports = db;
```
### 테이블과 연결할 모델 생성
- Sequelize.model 을 상속받은 클래스를 생성
- 2개의 메서드 오버라이딩 
    - static init 메서드 : 현재 테이블에 대한 설정
        - 첫번째 인수
            - 컬럼에 대한 설정
        - 두번째 인수
            - 테이블에 대한 설정 
    - static associate 메서드 : 다른 테이블 과의 관계를 위한 설정
- 두번째 인수 : 테이블에 대한 설정
    - timestamp :true를 설정하면 createdAt과 updatedAt컬럼이 자동으로 생성되서 데이터가 생성된 날짜와 수정된날짜를 자동으로 삽입
- 생성된 날짜 와 수정된 날짜를 자동으로 삽입
    - underscored
        - 시퀄라이즈는 이름을 기본적으로 Camel Case로 만드는데 이를 Snake Case로 변경하고자 할 때 사용
    - modelName
        - 노드 프로젝트에서 사용할 모델이름
    - tableName
        - 데이터베이스의 테이블이름
    - paranoid
        - 데이터를 삭제할 때 삭제하지 않고 deletedAt이라는 컬럼을 생성해서 이 컬럼의 값을 true로 만들고 조회할때 제외하기 위한옵션
            - charset과 collate
                - 캐릭터 셋으로 한글을 사용하고자 할 때는 utf8이나 utf8_general로 설정 하고 이모티콘까지 사용하고자 하면 utf8mb4 와 utf8m4_general_ci를 설정

### static associate 메서드
> 자신의모델이름.hasMany 나 belongsTo 를 호출하는데 hasMany 는 참조 되는 경우 (부모 테이블로 외래키의 참조 대상) 이고 belongsTo는 참조하는 경우 (외래키로 소유한 경우) 에 호출
- 매개변수로는 상대방모델이름,{foreignKey:'외래키이름','target':소유한키}
- 자신의모델이름.hasMany 나 belongsTo(상대방 모델명, {foreignKey:’외래키이름’,
targetKey:’외래키가 참조하는 속성’})

### 모델을 이용한 쿼리 메서드
- 삽입 create
- 조회 
    - findOne
    - findAll
- 수정
    - update
- 삭제 
    - delete

### models 디렉토리에 goods 테이블 과 연동할 모델을 생성 - goods
- 참고 
    - https://jeonghwan-kim.github.io/dev/2020/07/06/sequelize-model.html
```javascript
const Sequelize = require("sequelize");

module.exports = class Good extends Sequelize.Model {

    static init(sequelize) {
        return super.init({
            //컬럼에대한설정
            itemid: {
                type: Sequelize.INTEGER.UNSIGNED,
                allowNull: false,
                unique: true
            },
            itemname: {
                type: Sequelize.STRING(100),
                allowNull: true
            },
            price: {
                type: Sequelize.INTEGER.UNSIGNED,
                allowNull: true
            },
            description: {
                type: Sequelize.STRING(200),
                allowNull: true
            },
            pictureurl: {
                type: Sequelize.STRING(100),
                allowNull: true
            },
            updatedate: {
                type: Sequelize.STRING(20),
                allowNull: true
            }
        }, {
            //테이블에대한설정
            sequelize,
            timestamps: false, 
            underscored: false,
            tableName: 'goods',//프로그램에서 뭐라고쓸껀지
            paranoid: false,// deleteAt을 만들건지
            charset: 'utf-8',
            collate: 'utf-8_general_ci'

        })
    }
}
```
- models 디렉토리의 index.js 파일에 생성한 모델을 사용할 수 있도록 추가 설정을해야함
```javascript
db.Good = Goods;
Goods.init(sequelize);
```

### 메서드의 리턴
> 검색을 하는 경우에는 검색 결과가 리턴되지만 삽입,삭제, 갱신의 경우는 삽입,삭제,갱신된 데이터가 리턴된다.

### 하나의 테이블을 연동
- 테이블을 먼저 만들고 연결을 시켜도 되고 모델을 만들고 처음 실행을 하면 테이블이 존재하지 않으면 테이블이 생성됨
    - 테이블이 이미지 존재하면 존재하는 테이블과 연결을한다.

##  테이블 관계
- 1:1
    - 하나의 테이블에 컬럼이 너무 많아서 성능이 저하될때 테이블을 컬럼단위로 분할한경우 .
    - 양쪽의 기본키를 상대방의 외래키를 설정
- 1:N
    - 1
        - hasMany()
    - N
        - belongsTo()
    - 1에 기본키를 N쪽의 외래키를 설정한다.
- N:M
    - 양쪽의 기본키를 외래키로 갖는 별도의 테이블을 생성
    - 별도의 테이블을 만들자 
    


### 프로그래밍 언어에서 관계형 데이터베이스를 사용하는 방법
- 데이터베이스 드라이버만 이용해서 작업
    - 소스 코드 안에 SQL 을 삽입해서 작업하는 방식
    - 소스 코드 안에 SQL이 삽입되어 있어서 유지보수가 어려움 

- SQL Mapper 방식
    - 소스 코드 와 SQL 을 분리해서 작성하는 방식
    - 사용이 쉽기 때문에 SI 와 같은 여러 명이 공동으로 작업하는 프로젝트에서 많이 사용 
    <b>성능은 떨어짐 </b>
- ORM
    - 관계형 데이터베이스의 테이블을 클래스 와 그리고 테이블의 행을 인스턴스 와 매핑해서 사용하는 방식으로 SQL을 사용할 수 도 있고 사용하지 않을 수 도있음  
        - 성능이 sql Mapper 보다 좋기 때문에 솔루션 개발에 많이 이용된다.
    - java 의 JPA(Hibernate로 구현하는 경우가 많음) 나 node의 sequelize 그리고 python의 Django 등 대표적인 프레임워크이다.

### SPA 구현방법
- 하나의 HTML 파일에 스크립트를 이용해서 여러 개의 콘텐츠를 출력하는 방식
    - 비 추천
- 각 콘텐츠에 해당하는 별도의 HTML 파일을 만들고 이 를 불러들이는 방식
    - 대부분의 SPA 프레임워크 들이 이방식으로 구현 
        - 이렇게 만들어진 콘텐츠에 해당되는 파일들을 컴포넌트라고한다 .

### 클라이언트와 서버간의 데이터 교환시 고려할 내용
> 서버의 데이터를 로컬에 저장을 하고 로컬에 데이터가 없으면 다운로드 받고 로컬에 데이터가 있는 경우는 서버의 데이터에 변화가 있다면 변화가 생긴 데이터만 가져오는 방식으로 구현함.