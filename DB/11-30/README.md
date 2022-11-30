# Node + MongoDB + mongoose(Node의 ODM)
- RDBMS 와 NoSQL의 차이 
    - RDBMS 는 스키마를 생성하고 데이터를 저장하는 형식을 취하지만 NoSQL은 스키마를 생성하지않고 데이터를 저장하는 것이가능 
        - RDBMS 는 데이터의 값으로 다른 테이블의 데이터나 배열을 삽입 할 수 없기 때문에 테이블을 분할 하고 foreign Key와 join 의 개념을 이용해서 여러종류의 데이터를 저장함.
        - 일반적으로 엄격한 트랜잭션을 적용
    - NoSQL 
        - 복잡한 거래가 없는 경우나 비정형 데이터만을 저장하기 위한 용도로 많이 사용한다.
        - Join 대신에 Embedding이나 Linking의 개념을 사용
        - 스키마의 구조가 변경이 되도 데이터 구조를 변경할 필요없이 데이터 저장이 가능
        - 데이터의 값으로 객체 나 배열이 가능하기 때문에 하나의 Collection에 여러 종류의 데이터를 저장할 수 있어서 join을 하지 않아도 되기 때문에 처리속도가 빠를 수 있다 .

## ODM
> Relation 이라는 개념 대신에 Document 를 하나의 객체에 매핑하는방식
- 하나의 Document에 대한 모양을 만들고 사용해야 하기 때문에 NoSQL의 Collection도 하나의 정형화 된 모양을 가져야한다.
- MongoDB 에서 ODM 을 사용할 수 있도록 해주는 대표적인 라이브러리가 mongoose

```
한번 해보라는 말씀이있으셨음
```


# Authentication
> Authenctication (인증) Authorization(인가)
- 인증
    - 계정 관련
    - 로그인 관련
- 인가 
    - 권한 

### 인증을 구현하는 방법

- 로컬 로그인 
    - 회원 정보를 저장하고 있다가 인증
    - 회원 정보를 저장할 때 비밀번호는 복호화가 불가능한 방식을 사용하고 개인을 식별할 수 있는 정보를 마스킹 처리를 하거나 복호화가 가능한 방식의 암호화를 활용해야 한다 .
- OAuth 로그인
    - 다른 서버에 저장된 인증 정보를 활용해서 인증을 하는방식


### 인증일 위한 프로젝트 기본 설정
> 로그인을 할 수 있도록 회원가입을 하고 로그인 처리를 수행하고 간단한 글 과 파일을 업로드 할 수 있는 프로젝트

```
npm init

npm install npm install express morgan dotenv compression morgan file-stream-rotator multer cookie-parser express-session express-mysql-session

npx sequelize init 

public 
routes
views add 
```

### 프로젝트에 .env파일을 생성하고 작성
- 소스코드에 노출되서 안되는 내용이나 개발 환경에서 운영환경으로 이행(Migration)할 때 변경될 내용을 작성하는데 이 내용은 실행 중에는 변경되지 않는 내용이여야한다.
- 대표적인 내용이 데이터베이스 접속 정보 나 암호화를 하기 위한 키 또는 서버 포트 번호와 같은 것들 이다 .
- 이러한 내용은 대부분 실행 중에는 변경되지 않지만 개발 환경에서 운영 환경으로 이행 할 때 변경될 가능성이 높은 내용이다 


## 실습 
- 기본적인 app.js 서버설정, views파일에 정적파일 기입 

### 데이터베이스
- 테이블 구조 
    - 회원테이블 
        - 이메일
        - 닉네임 
        - 패스워드 
        - 생성시간
        - 수정시간
        - 삭제시간 
            - 삭제할때 실제 지워지지않고 삭제한 시간을 기록할부분
    - 포스트 테이블
        - 게시글 내용
        - 이미지 파일의 경로 
    - HashTag테이블 
        - 태그이름 
- 테이블 관계
    - User 와 Post 는 1:N 관계
    - HashTag 와 Post 는 N:M 관계
    - User 와 User는 N:N 관계 
- 로그인 방법 
    - 직접 로그인 or 카카오 로그인 여부를 저장
- 카카오아이디 

```javascript
*user.js
 db.user.belongsToMany(db.User, {
            foreignKey: 'followingId',
            as: 'Followers',
            through: 'Follow'
        })
// 다대다 관계시 이렇게 두개를 만들어줘야함
        db.user.belongsToMany(db.User, {
            foreignKey: 'followerId',
            as: 'Followings',
            through: 'Follow'
        })
```