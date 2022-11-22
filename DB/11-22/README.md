# Scala Function
> 하나의 데이터를 받아서 하나의 데이터를 리턴하는 함수
- 컬럼을 데이터로 제공하면 각 컬럼의 데이터 단위로 작업을 수행한 후 결과를 하나의 컬럼으로 만들어서 리턴

### 수치 함수
> 숫자 연산 과 관련된 함수로 올림, 버림, 반올림 등의 함수가 제공된다.
- 데이터는 숫자 데이터여야 한다.
- FROM 절을 제외한 곳에서 사용이 가능하다.
- EMP 테이블에서 EMPNO 가 홀수인 데이터를 조회 
```
select *
from EMP
where mod(EMPNO,2)=1;
```

### 문자열 함수
- CONCAT :문자열결합
- UPPER, LOWER
- LTRIM , RTRIM ,TRIM : 공백제거 
- SUBSTRING: (str,pos,len) 
    - str:원본 문자열
    - pos:시작 위치값
    - len: 가져올 길이 값
- LENGTH
- EMP 테이블에서 1982년에 입사한 사원의 ENAME과 SAL 을 조회 
```
select ENAME,SAL
from EMP
where HIREDATE >= '19820101' and HIREDATE <= '19821231';


select ENAME,SAL
from EMP
where SUBSTRING(HIREDATE,1,4)='1982';
```
### 날짜 관련 함수
- 현재 날짜 및 시간

```
CURRENT_DATE(),CURDATE() -> 현재 날짜 
CURRENT_TIME(),CURTIME() -> 현재 날짜 
NOW(),LOCALTIME(),LOCALTIMESTAMP(),CURRENT_TIMESTAMP() -> 현재 날짜 및 시간
```

- 날짜 및 시간 연산 함수
    - ADDATE
    - SUBDATE
    - ADDTIME
    - SUBTIME

- 특정 날짜 생성
    - STR_TO_DATE(문자열,서식): 서식에 맞춰서 문자열을 날짜 형태로 변환
```
('1986-05-05 11:00:00','%Y-%M-%D %H:%i:%S')
mysql이나 mariadb는 일반적인 날짜 포맷의 문자열도 날짜로 간주

STR_TO_DATE('1986-05-05','%Y-%M-%D'),'1986-05-05'
날짜에서 많이 사용하는 모맷으로 날짜로 간주
```
- 참고 정훈
    - https://extbrain.tistory.com/78

### 시스템 정보 함수 
- ROW_COUNT()
- USER()
- DATABASE()

### 타입 변환 함수
- CAST(데이터 as 자료형)
    - 자료형
        - DATETIME
        - DATE
        - TIME
        - CHAR(varchar, TEXt)
        - INT 
        - DOUBLE
        - BINARY

### NULL 관련 함수
- IFNULL(데이터1,데이터2)
    - 데이터 1이 null이 아니면 데이터1을 리턴하고 데이터1이 null이면 데이터2를 리턴
- NULLIF (데이터1,데이터2)
    - 두 개의 데이터가 같으면 NULL을 리턴하고 그렇지 않으면 데이터1을 리턴
- COALESCE(데이터 나열)
    - 나열된 데이터 중 NULL이 아닌 첫번째 데이터를 리턴
- 데이터베이스에서는 NULL 과 연산을 하면 결과는 NULL

### 분기 관련 함수
- IF
- CASE 데이터 WHEN 값 THEN 결과 ElSE 결과

### GROUPING
> 그룹화 관련된 기능

- 그룹함수
    - COUNT :개수
    - SUM :합계
    - AVG : 평균
    - MAX : 최대값
    - MIN : 최소값
    - STDDEV : 표준편차
    - VARIANCE: 분산

- 특징 
    - NULL을 제외하고 연산
    - COUNT 를 제외한 모든 함수는 컬럼 이름 이나 연산식을 대입해야 하지만 COUNT 는 * 이가능 
        - COUNT(*)는 모든 컬럼이 NULL인 경우 제외하고 데이터의 개수를 계산
    - SUM 과 AVG,STDDEV,VARIANCE는 문자열에는 사용 못함
    - GROUP BY 이후 부터 사용 가능 
        - HAVING
        - SELECT
        - ORDER 에서 사용가능

- SELECT 절에 사용할 때는 대부분 별명과 함께 사용

- 그룹함수 
```
tStaff 테이블에서 score의 평균구하기 

select ROUND( AVG (score),0)
from tStaff;

// 66

select ROUND(SUM(score)/ COUNT (*),0)
from tStaff;

//데이터가 null 인경우는 0으로간주 
```

### COUNT 
```

select count(score)
from tStaff;
```


### GROUPING
> select 구문에서 데이터를 그룹화 하고자 할 때 사용하는 절
- where 절 다음에 수행
- 이 절이 수행되어야 그룹 함수를 사요하는 것이 가능
    - where 절에서는 그룹 함수를 사용 할  수 없음

- EMP 테이블에서 부서별(DEPTNO) 평균 급여(SAL) 조회
```
select DEPTNO,ROUND(AVG(SAL),0)
from EMP
group by   DEPTNO;
```
- 그룹화 한 항목을 제외하고 출력하면 데이터를 알아보기각 어렵기 때문에 그룹화 한 항목과 같이 조회

- 그룹화 는 여러개 가능
```
select DEPTNO,JON,ROUND(AVG(SAL),0)
FROM EMP
GROUP BY DEPTNO,JOB
```

- 그룹화 한 후 select 절에서 그룹화 한 항목이나 집계 함수가 아닌 데이터의 조회
    - oracle은 에러이고 mysql이나 mariadb는 그룹화 한 항목 중 첫번째 데이터 조회 


### HAVING
> GROUP BY 이후의 조건을 설정해서 행 단위로 추출
- <b>그룹 함수는 WHERE 절에서 사용 불가하기 때문에 </b>그룹 함수를 이용한 조건을 설정할 때는 HAVING에 작성해야함.


```
#tStaff 테이블에서 depart 별로 그룹화해서 평균 salary 가 340이 넘는 부서의 depart와 평균 salary를 조회

select depart, AVG(salary)
from tStaff
GROUP By depart
HAING AVG(salary) >340
```

### window 함수 
> 행 과 행 사이의 관계를 표현하기 위한 함수
- 순위나 누적합 등의 연산을 위한 함수
- 순위 함수로는 동등한 값일 떄 어던식으로 처리하는 냐에 따라 여러 함수를 제공하고 N등분한 그룹도 제공해주는 함수
    - RANK
    - DENSE_RANK
    - ROW_NUMBER
    - NTITLE
- 함수 뒤에 OVER를 이용해서 순위를 구할 방법을 ORDER BY로 제공해야함

```
# EMP 테이블에서 SAL 의 오름차순 순위
select ROW_NUMBER() OVER(ORDER BY SAL ASC),ENAME,SAL
FROM EMP;

# EMP 테이블에서 SAL 의 오름차순 정렬해서 4개의 그룹으로 분할
select NTILE(4) over(ORDER BY SAL ASC),ENAME,SAL
from EMP;
```

### JSON 출력
> 데이터를 조회할 때 JSON_OBJECT 로 감싸면 JSON 문자열을 리턴
```
select JSON_OBJECT('name',ENAME,'salary',SAL)
from EMP;
```

## SET Operator
> 개요 
- 동일한 테이블 구조를 가진 2개의 테이블을 가지고 수행하는 연산
    - 테이블 구조가 같다는말은 컬럼의 개수가 같아야하고 컬럼의 자료형이 일치해야한다. 
    - 컬럼의 이름이나 테이블의 이름은 아무런 상관이 없음.
        - 종류로는 UNION,UNION ALL,INTERSECT, EXCEPT(MINUS 인 데이터 베이스도 있음)
    - 컬럼의 이름은 첫 번째 테이블의 컬럼 이름을 사용
    - ORDER BY 는 마지막에 한 번 만 작ㅈ성
    - 데이터의 자료형이 BLOB,CLOB,BFILE,LONG(데이터의 사이즈가 너무 커서 일치 여부를 판단하는데 시간이 많이 걸리기 때문) -이자료형들은 index를 생성하지 않는다.
- 데이터가 분산되어 있는 경우 연산의 결과를 합칠 때 사용
- 데이터가 분산되어 있을 때 처리방식
    - 데이터를 모은 후 처리
    - 데이터를 처리한 후 결과를 모으는것
        - 속도가 빠른 경우가 많음.Map Reduce
- UNION: 결과를 합칠때 중복되는 행은 하나만 표시해줍니다 
- UNION ALL : 중복제거를 하지 않고 모두 합쳐서 보여준다.
- 정훈 참조 
    - https://dpdpwl.tistory.com/98



## 실습
 - DEPT 테이블의 DEPTNO와 EMP 테이블의 DEPTNO의 합집합
 ```
select DEPTNO
from DEPT 
UNION
SELECT DEPTNO
from EMP;


select DEPTNO
from DEPT 
UNION ALL
SELECT DEPTNO
from EMP;
```


 - DEPT 테이블의 DEPTNO와 EMP 테이블의 DEPTNO의 교집합
    - TableA 와 TableB 가 일치하는 결과를 리턴 

 ```
 select DEPTNO
from DEPT 
INTERSECT
SELECT DEPTNO
from EMP;
 ```



 - DEPT 테이블의 DEPTNO와 EMP 테이블의 DEPTNO의 차집합

    - TableA  Row 내용 중 TableB와 Row 내용이 같지 않거나<br/> TableA 에는 있는데 TableB에는 없는 데이터를 리턴 

 ```
 select DEPTNO
from DEPT 
EXCEPT
SELECT DEPTNO
from EMP;
 ```


## SubQuery 
> 다른 SQL 구문 안에 포함된 쿼리 
- Sub Query 는 SELECT 구문 