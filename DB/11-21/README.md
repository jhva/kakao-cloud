# MariaDB

> SQL 에 기반을 둔 RDBMS(관계형 데이터베이스)로 Open Source형태로 제공

- MySQL 개발자가 만들어서 MySQL과 거의 유사
- sql도 거의 차이가 없음
- 작업단위
  - 데이터베이스 > 테이블
  - 하나의 데이터베이스는 여러 유저가 공유

### 설치

> 정훈 : wsl 사용

### 데이터베이스 외부 접속 허용

- 권한 설정

  - GRANT all privileges on 사용할데이터베이스이름 TO '계정'@'접속할ip';

    - 모든 데이터베이스를 `사용하고자 하는 경우는 _._
    - 모든 곳에서 접속하도록 하고자 할 때는 % 를 설정하고 로컬에서만 접속하도록 할때는 localhost
    - 권한 설정 명령은 설정하고 적용 명령을 수행

  - root 계정을 모든 곳에서 접속하도록 설정
    - GRANT all privileges on _._ TO 'root'@'%';
    - FLUSH privileges;

- 서버설정
  > /etc/mysql/mariadb.conf.d/50-server.cnf 파일의 bind-address 부분을 허용할 ip 로 변경해주어야하는데 0.0.0.0 이면 모든 곳에서 접속 가능
      - 실제 서버 설정이라면 Application Server 의 ip 만 ㅓㅎ용함
- 도커는 직접파일을 수정할 수 없기 때문에 터미널에서 컨테이너의 bash로 접속
  - docker exec -it 컨테이너이름 bash 명령으로 접속후
    - apt update
    - apt upgrade
    - apt install vim
  - vim /etc/mysql/mariadb.conf.d/50-server.cnf 명령을 수정한다

```
docker exec -it 컨테이너이름 bash



vim /etc/mysql/mariadb.conf.d/50-server.cnf
```

```
컨테이너실행

docker run --name mariadb -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=1234 mariadb
```

### SQL 작성 규칙

- SQL 의 예약어는 대소문자 구분을 하지 않는다.
- 테이블 이름 이나 컬럼 이름은 대소문자를 구분하는 데이터베이스도 있고 구분하지 않는 데이터베이스도 있음

### MySQL이나 Maria DB는 구분한다

- 값을 작성할 때는 대소문자 구분을 하는데 MariaDB는 대소문자 구분을 하지 않는경우도있다.
- 숫자 데이터는 따옴표를 하지 않고 문자는 작은 따옴표를 해서 표현하는데 MariaDB나 MySQL은 큰 따옴표도 허용된다
- 명령문의 마지막은 ; 인데 접속도구에서는 해도 되고 하지 않아도 되지만 절차적 프로그래밍을 할 때는 명확하게 해주어야 하고 프로그래밍 언어에서 SQL을 사용할때는 ; 을 하면안된다.

### 데이터베이스 관련 명령어

- 데이터베이스 생성

```
create database 데이터베이스이름;
```

    - create database 데이터베이스 이름; # 이미 존재하는 이름이면 에러
        - 일반적으로 프로젝트 진행할 때 마다 데이터베이스 생성

- 데이터베이스 확인

```
show databases;
```

### 데이터베이스 사용

```
use 데이터베이스이름;
```

### 데이터베이스에 존재하는 테이블 확인

```
show tables;
```

### 테이블 구조확인

```
DESC 테이블이름;
```

### SQL 분류

- DDL(구조에 관련된 명령어로 일반적으로 DBA의 명령어) - 취소 안됨
  - create: 구조 생성
  - alter: 구조 변경
  - drop: 구조삭제

### DQL - 검색 관련 명령어

- SELECT

### DML - 데이터 관련 명령어

- insert
- update
- delete

### TCL - 트랜잭션 관련 명령어 - 취소 불가능

- commit :현재까지 작업 내용을 원본에 반영
- rollback: 작업 내용을 취소
- savepoint: 취소할 지정을 만드는 명령어

### DCL - 제어명령 (취소 불가능하고 운영자의 언어)

- GRANT : 권한 부여
- REVOKE: 권한 회수

### SELECT

- 데이터 조회 명령어로 원본에 아무런 영향을 주지않음
- 원본에서 데이터를 복제해서 수행한다.

### 샘플 데이터 구조

- EMP 테이블

  - EMPNO : 사원번호로 정수 4자리이고 기본키
  - ENAME : 사원이름으로 문자
  - JOB : 직무로 문자
  - MGR: 관리자의 사원번호
  - HIREDATE: 입사일로 날짜 형식
  - SAL : 급여로 실수 7자리 소수 2자리
  - COMM : 상여금으로 실수 7자리 소수 2자리
  - DEPT : 부서번호로 정수 2자리이고 DEPT 테이블의 DEPTINO를 참조

- DEPT 테이블
  - DEPTNO : 부서번호로 정수 2자리이고 기본키
  - DNAME : 부서이름으로 문자
  - LOC : 위치로 문자
- SALGRADE 테이블
  - GRADE : 호봉으로 숫자이고 기본키
  - LOSAL : 호봉의 최저 급여로 숫자
  - HISAL : 호봉의 최고 급여로 숫자
- TICTY 테이블
  - NAME : 도시이름으로 문자열이고 기본키
  - AREA : 면적으로 정수
  - POPU : 인구수로 정수
  - METRO: 대도시 여부로 문자
  - REGION : 지역으로 문자
- TSTAFF 테이블
  - NAME : 직원으로 문자이고 기본키
  - DEPART : 부서이름으로 문자열
  - GENDER : 성별로 문자열
  - JOINDATE: 입사일로 문자열
  - GRADE: 직무로 문자열
  - SALARY: 급여로 문자열
  - SCORE: 고과 점수로 실수

### select 용어

- selection : 테이블의 행을 선택할 때 사용하는 것
- Projection: 테이블의 열을 선택할 때 사용하는 것
- join : 공유 테이블 양쪽의 열에 대해서 링크를 생성해서 다른 테이블의 데이터를 가져와서 합치는 것

### mariaDB에서의 SELECT 구조

- SELECT
- FROM: 데이터를 조회할 테이블을 나열
- WHERE: 데이터를 행 단위로 분할하기 위한 조건
- GROUP BY: 데이터를 그룹화하 시키기 위한 열 이름 이나 계산식을 나열
- HAVING: 데이터를 행 단위로 분할하기 위한 조건
- ORDER BY: 데이터를 저열하기 위한 열 이름이나 계산식 또는 SELECT 절의 번호
- LIMIT: 데이터의 위치와 개수를 지정해서 가져오기 위한 절로 표준은 아니다.

### SELECT 구문의 가장 기본적인 구조

> 테이블의 모든 데이터 조회

```
SELECT * FROM 테이블이름
```

### SELECT 절에서의 별명

- SELECT 절에서는 컬럼에 별명을 부여할 수 있다 .
- 하나의 공백을 두고 별명을 설정하면 된다.
  - 공백에 자리에 AS를 추가해도 된다
  - 별명에 공백이나 특수문자 또는 대문자가 있으면 " " 로 묶어야한다.
  - SELECT 절의 별명은 ORDER BY에서 사용 가능하고 프로그래밍 언어에서도 별명을 가지고 데이터를 가지고 온다 .
  - 계산식이나 그룹 함수의 결과를 조회하고자 할 때는 별명을 부여하는 것이 좋다.

```
* alias

select name as 이름
from

* 계산식
select name, popu *100 as 별명 from tCity;

예를들어 별명자리가  인구 수 이런식으로 띄워쓰기가 들어갈경우
" " 를 꼭붙혀주자!
```

### 계산식 출력

> FROM 절을 제외한 모든 곳에서는 계산식 사용이 가능

- 계산식은 가상의 컬럼이고 FROM은 실제 테이블을 가져오는 것이기때문에 FROM절에는 계산식을 사용할 수 없음
- tCity테이블에서 name과 popu에 10000을 곱한 결과를 조회
  - 단순 계산식은 FROm 을 생략해도 된다고함 .

```
select
```

### concat 함수

- 2개이상의 문자열을 합쳐주는 함수
- 2개이상의 컬럼 이나 연산식을 하나로 합쳐서 출력하기 위해서 사용

```
select concat(ENAME,'', JOB)
from EMP;
```

### DISTINCT

> SELECT 절의 맨 앞에 한번만 기재해서 컬럼의 중복된 값을 제거하는 역할

- 컬럼 이름이 하나이면 그 컬럼의 값이 중복된 것만 제거하고 컬럼이 2개 이상이면 모든 값이 일치하는 경우에 제외

```
select DISTINCT  region
from tCity;
```

### ORDER BY

> 기본이 오름차순이다

- 조회된 데이터를 정렬하기 위한 절
  - ORDER BY 컬럼이름 [ASC | DESC] 나열
    - ASC 는 오름차순
      - 숫자는 작은 것에서 큰 것 순으로
      - 날짜는 빠른 것에서 늦은 것으로
      - 문자는 알파벳 순서대로
      - NULL 이 가장 마지막
    - DESC는 내림차순
  - 컬럼이름 대신에 SELECT 절의 순서로 설정하는 것이 가능
  - SELECT 절에서 만든 별명 사용 가능
  - 2개이상의 필드 나열 가능함 .
    - 첫번째 필드로 정렬하고, 동일한 값이 있는 경우 두번째 필드의 정렬 조건을 확인
  - 계산식을 이용한 정렬 가능
  - 권장하지는 않지만 정렬 기준 필드를 출력하지 않아도 됨.

```
기본이 오름차순

select *
from tCity
order by popu;


내림차순

select *
from tCity
order by popu DESC;

```

- tCity 테이블의 데이터를 region 별로 정렬하고 동일한 값이 있으면 name의 내림차순으로 정렬을 하고 region,name,area,popu 컬럼을 조회

```
select region,name,area,popu
from tCity
order by region, name;
```

- select 구문의 결과가 2개이상의 행이 될 것 같은 경우에는 ORDER BY를 이용해서 정렬을 해주는 것 이 좋습니다.

### where

> 테이블의 데이터를 행 단위로 분할하기 위한 조건을 설정하는 절

- select
- update
- delete

- 비교연산자

  - =
  - '>'
  - '<'
  - '>=' NOT 컬럼이름 <
  - '<=' NOT 컬럼이름 >
  - <> ,!=,^=,NOT 컬럼이름 =

- tCity테이블에서 name 이 서울인 데이터의 모든 컬럼을 조회

```
select *
from tCity
where metro='y';


select *
from tCity
where name='서울';
```

- mariadb의 경우 대소문자르 ㄹ구별하지 않는 경우가 있을 수 있기 때문에 유의해야한다.

- 대소문자 구별하기
  - 조회를 할 때 컬럼 이름을 BINARY로 묶어주거나 컬럼을 만들 때 자료형 뒤에 BINARY를 추가해주어야한다

```
select *
from tCity
where BINARY(metro)='Y';
```

- 크다 작다 크거나 같다 작거나 같다 조건이 있는 경우 테스트를 할 때 경계값과 경계값 양쪽의 데이터를 반드시 테스트하세요
- Boundary Value Analysys(경계값 분석 기법)

### null 비교

> 데이터베이스에서 NULL을 저장하는 방법은 공간에 NULL을 대입하는 개념이 아니고 NULL을 저장할 수 있는 컬럼에는 데이터를 저장할 수 있는 공간에 하나의 공간을 추가해서 그 공간에 NULL여부를 표시하기 때문입니다.

- NULL은 일반 연산자로 비교 안됨
- IS NULL과 IS NOT NULL 로 비교

```
 * 널 여부 확인

select *
from tStaff
where score  IS NULL;

* null 이 아닌거

select *
from tStaff
where score  IS NOT NULL;
```

### 논리연산자 AND 와 OR을 제공

- AND 는 두개의 조건이 모두 일치하는 경우만 조회하는데 앞의 조건이 일치하지 않으면 뒤의 조건은 확인하지않음
- OR 은 두 개의 조건 중 하나의 조건만 일치해도 조회되는데 앞의 조건이 일치하면 뒤의 조건은 확인하지않음
- AND 가 OR 보다 우선 순위가 높다.

- tCity 테이블에서 popu 가 100만 이상이고 area 가 700 이상인 데이터의 모든 컬럼을 조회

### LIKE

- 부분 일치하는 데이터를 조회

  - \_: 하나의 문자와 매칭
  - % : 글자 수 상관없음
  - [] : 문자를 나열하면 문자 중 하나
  - [^] : 문자를 나열하면 문자에 포함되지 않는

- 와일드 카드 문자 검색할경우 (ESCAPE 이용)
  - tCity 테이블에서 name 에 천이 포함된 데이터를 조회
  - tCity 테이블에서 name 에 천으로 끝나는 데이터를 조회
  - tCity 테이블에서 name 에 천으로 시작하는 데이터를 조회

```
select *
from tCity
where name Like '%천%';

select *
from tCity
where name not Like '%천%';


* 천으로 끝나는 데이터
select *
from tCity
where name  Like '%천';

* 천으로 시작하는 데이터
select *
from tCity
where name  Like '천%';
```

- EMP 테이블에서 ename 에 s가 포함된 데이터

```
* S로 시작하는 데이터 모두 조회

SELECT *
from EMP
where ENAME like 'S%';

* N으로 끝나는 6자의 이름을 가진 데이터를 조회
select
from EMP
where ENAME like '_____N';


@ 예시

LIKE '67#%% Guilty' ESCAPE '#'

#% => #의 바로 뒤에있는 %는 문자열이에요~

= '67%' 로 시작하는 데이터 검색


LIKE '67#%#% Guilty' ESCAPE '#'

#% => #의 바로 뒤에있는 %는 문자열이에요~

= '67%%  Guilty' 데이터 검색



LIKE '67#__ Guilty' ESCAPE '#'

#_ => #의 바로 뒤에있는 _는 문자열이에요~

= '67_'로 시작하고 뒤에 한글자가 있는 데이터 검색

```

### Between ~ and

> BETWEEN a and b 형태로 a 부터 b 까지의 데이터 조회

- 모두사용이가능
  - 숫자
  - 날짜
  - 문자열
- 단순 AND 로도 가능하다

- tCity 테이블에서 popu가 50 ~100 사이인 데이터 조회

```
select *
from tCity
where popu between 50 and 100;
```

- 문자의 크기 비교는 맨 앞 글자부터 순서대로 하나씩 비교

```
tStaff 테이블에서 name 이 ㄱ에서 ㅅ으로 시작하는 데이터 조회

select *
from tCity
where name BETWEEN  '가' and '사';

```

- tStaff 테이블에서 name이 ㅊ이 포함된 문자로 시작하는 데이터 조회

```
select *
from tCity
where name >= '차';
```

- tStaff 에서 joindate 가 2015년 1월 1일 부터 2017년 12월 31일 사이인 데이터를 조회

```
select *
from tStaff
WHERE joindate BETWEEN  '20150101'and '20181231';
```

### IN 연산자

- IN (값을 나열) : 나열된 값에 포함되는 경우 조회
- tCity 테이블에서 region이 경상이나 전라 인 데이터 조회

```
select *
from tCity
where region = '경상' OR region ='전라'

select *
from tCity
where region in('경상','전라');

```

- ms 차이가 있다.

### Limit

> 행의 개수를 제한할때 사용함 - TOP N

- LIMIT [건너 뛸 행의 개수], 조회할 개수

  - 최근에는 LIMIT 개수 OFFSET 건너뛸 행의 개수

- tCity 테이블에서 popu가 큰 4개의 데이터 조회

```
select *
from tCity
ORDER By popu desc
limt 4;

내림차순으로 정렬한후
4개만 보여져라
```

- tCity테이블에서 popu 가 큰 5번째 데이터로부터 2개 조회

```
select *
from tCity
order by popu desc
limit 4,2;
(개수고정 , 번 ㅎ?)



select *
from tCity
order by popu desc;
 limit 1
 OFFSET 6;

tCity테이블에서 정렬해준후
1개만 가져오는데
6번째 행으로부터 가져온다.
```

### Scala Function

- Function

  - 데이터베이스에서 함수는 반드시 리턴을 해야한다.

- 스칼라함수 :1개만주는애
- 그룹함수 : 여러개를주는애
- 커스텀함수 : 사용자정의
