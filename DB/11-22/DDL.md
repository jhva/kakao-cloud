# 관계형 데이터베이스 - RDMS
> 데이터 구조를 생성하고 변경하고 삭제하는 명령어
## 테이블 생성

### 기본형식
```
create  테이블이름(
    컬럼이름 자료형 [컬럼 제약조건]
    ..
    ..
    [테이블 제약 조건] 조건 나열;
)
```
## 자료형
- 숫자
    - TINYINT(1바이트 - 참/거짓)
    - INT(INTEGER)
    - FLOAT,DOUBLE
- 문자
    - CHAR(길이 - 길이가 고정)
        - 고정적인부분은 char를 쓰자 
    - VARCHAR(길이 - 길이가 가변)
    - TEXT 
    - BLOB(파일의 내용 저장) - 파일을 데이터베이스에 저장하는 방법
        - 파일의 경로를 저장하는 방법
            - 파일을 별도로 저장하고 그 경로를 저장
        - 파일의 내용을 저장하는 방법
            - 파일을 별도로 저장하지 않고 데이터베이스 저장(BLOB)
- 날짜
    - DATE(날짜)
    - DATETIME(날짜와 시간)
    - TIMESTAMP(날짜 와 시간)
        - 2037년까지 밖에 저장이안되어서 DATETIME을 쓰는걸 권장
    - TIME(시간)
    - YEAR(년도)
- 기타
    - JSON
    - GEOMETRY(공간 정보)

- 한글 1글자 3byte

## 조건 나열
### ENGINE
- MyISAM(Indexed Sequential Access Media - 조회 유리) 이나 InnoDB(삽입 삭제 갱신에 유리)를 설정할 수 있음.

### DEFAULT CHARSET
- 한글이 깨지는 경우 한글 설정하는 옵션으로 utf8 을 설정해주면 되는데  MariaDB 는 기본이 UTF8
- auto_increment
    - 일련번호를 사용할 때 시작숫자부터 시작한다.
- Timezone 설정 
    - Mac 에서 사용할 때 시간 대역이 안맞아서 설정해주어야 하는 경우가 있다.

 