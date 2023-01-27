# Docker

### 도커와 가상화 기술 차이

> Virtual Box 나 VMWare UTM 같은 가상화 기술

- 가상화는 물리적인 대상을 소프트웨어로 구현했다는 의미로 운영체제를 설치하고 그 위에 소프트웨어를 구동시키는 것
- 도커는 리눅스 운영체제의 일부 기능만을 물리 서버에 맡겨서 수행하는 형태 수행하는 형태로 사용할 수 있는 소프트웨어는 리눅스 용으로 제한됨

### AWS EC2

> 도커 와 비슷하게 인스턴스라는 개념을 사용하는데 EC2 도 가상화 기술로 각각의 인스턴스가 완전히 독립된 컴퓨터처럼 동작하는 방식

- AWS EC2 는 AMI 라는 이미지로부터 생성되기 때문에 인스턴스를 배포하는 방법이 Docker 와 유사
- 가상서버를 만들지 않아도 컨테이너 이미지를 바로 실행할 수있는 호스팅 서비스의 형태

### 가상화 기술

- 하드웨어 <-> 리눅스 커널 <-> 리눅스 셸 <-> 소프트웨어 <-> 사용자

### 리눅스의 도커

- 하드웨어 <-> 리눅스 커널 <-> 도커 엔진 <-> 이미지(셸 과 소프트웨어) 또는 컨테이너 <-> 사용자

### 윈도우의 도커 
- 하드웨어 <-> 윈ㄷ우 <-> Hyper-V , Moby-VM, 리눅스 운영체제(커널) <-> 도커 엔진 



### image와 컨테이너 
- 이미지 = 클래스 
- 컨테이너 = 인스턴스 
- 컨테이너를 이용해서 이미지를 만든 후 export를 하고 다른 도커 엔진에서 Import하면 된다 

### docker hub
- 이미지의 종류 
  - 운영체제 
  - 소프트웨어가 포함된 이미지 
  - 소프트웨어가 여러 개 포함된 이미지 
### 데이터를 저장 
- 컨테이너를 삭제하면 컨테이너 안에서 만들어지 데이터도 소멸됨 
- 도커가 설치된 물리적 서버의 디스크를 마운트해서 저장하는 것이 가능 

### 도커의 장점 
- 개발환경과 운영 환경을 거의 동등하게 재현할수있다 
- 가상화 소프트웨어보다 가벼움 
- 물리적 환경의 차이나 서버 구성의 차이를 무시할 수 있음 
- 클라우드 플랫폼 지원 


### 주 용도 
- 동일한 개발 환경 제공 
- 새로운 버전을 테스트 
- 동일한 구조의 서버가 여러 개 필요한 경우 

### 도커의 기본적인 사용 
> 명령어의 기본 형식 
- 도커의 명령어는 docker 로 시작

- 명령어는 단순 명령어 와 상위 명령어 와 하위 명령어로 구분되기도 한다 
- 옵션은 도커에 대한 옵션 
- 인자는 이미지에 대한 옵션 

### 도커 상위 커맨드 
- network
  - 여러 개의 컨테이너를 하나로 묶고자 할 때 사용 
- volume 
 

### 하위 커맨드 
- container
  - start |
  - stop
  - create  
  - run 
    - 이미지가 없으면 내려받고 컨테이너를 생성하고 실행 (image pull, create, start) 가 합쳐진 부분 
  - rm 
    - 정지 중인 컨테이너 삭제 
  - exec 
    - 컨테이너 안에서 프로그램을 실행 
  - ls 
    - 컨테이너 목록 출력
  - cp 
    - 컨테이너 와 호스트 간에 파일을 복사 
  - commit 
    - 컨테이너를 이미지로 변환 

### image 커맨드
- pull
  - 이미지 다운로드 
- rm 
  - 이미지 삭제 
- ls 
  - 이미지 목록 출력 
- build 
  - 이미지를 생성 

### volume 
- create 
  - 생성 
- inspect
  - 상세 정보 출력 
- ls 
  - 목록 출력 
- prune
  - 마운트 되지 않은 볼륨을 모두 삭제 
- rm
  - 저장한 볼륨 삭제 

### 네트워크 
- connect 
  - 컨테이너를 네트워크에 연결
- disconnect 
  - 컨테이너를 네트워크 연결에서 해제 
- create
  - 네트워크 생성 
- inspect 
  - 상세 정보 출력
- ls 
  - 목록 출력 
- prune 
  - 현재 컨테이너가 접속하지 않은 경우 삭제 
- rm 
  - 네트워크 삭제 

### 기타 상위 커맨드 
- checkpoint
- node
- plugin
- secret
- service
- stack
- swarm
- system

### 단독으로 사용되는 명령 
- login
- logout
- search
- version 

### docker run 명령 
- docker image pull , docker container create, docker container start
- 기본형식 
```
docker run[옵션] 이미지 [인자]
```
- 옵션 
  - --name 
    - 컨테이너 이름 
  - -p 
    - 포트 포워딩
  - -v
    - 볼륨 마운트
  - -net
    - 네트워크 연결
  - -d 
    - 데몬으로 생성 
  - -i 
    - 컨테이너에 터미널을 연결함
  - -t 
    - 특수 키를 사용할 수 있도록 설정 
  - d,i,t 는 같이 사용하는 경우가 많아서 -dit 로 설정하는 경우가 많음 

### 컨테이너 확인 
- docker ps
  - 실행중인 컨테이너 확인 
- docker ps -a 
  - 모든 컨테이너 확인 

### 컨테이너 중지 
- docker stop 
  - 컨테이너 Id 또는 이름 

### 컨테이너 삭제 
- docker rm 컨테이너Id
  - 컨테이너가 실행 중이면 삭제되지 않음 
- docker rm $(docker ps -a -q) 
  - 모든 컨테이너 삭제 

### Apache 컨테이너를 이용한 실습 
- apache 와 nginx 
  - 웹 서버를 만들어주는 소프트웨어 
- 이미지 이름은 httpd 이고 컨테이너 이름은 apa000ex1
- 이미지를 다운받고 컨테이너 생성 후 실행 -> 컨테이너 상태 확인 -> 컨테이너 종료 -> 컨테이너 상태확인 -> 컨테이너 삭제 -> 컨테이너 상태확인 
- 동일한 하위 명령어가 없다면 상위 명령어는 생략 가능 
- 생성하고 실행
```
docker container run --name apa000ex1 -d httpd
```
- 웹서버나 디비는 데몬으로돌려야한다 (특별한 경우가 아니면) 
  - 데몬 다른 애들을 도와주는역할(ㄱㅔ속해서 실행중이여야함)
- 파일 핸들링 을 하겠다 -dit 
- 안할거면 -d
- 컨테이너 중지 
  - docker container stop apa000ex1 
- 실횅 확인 
  - docker ps 
- 삭제 

### 포트포워딩 
- 외부에서 컨테이너에 접근할려면 외부 와 접속하기 위한 설정ㅇ ㅣ필요한데 이 때 필요한 설정이 포트포워딩 
- apache 는 80q번 포트를 이용해서 외부와의 접속을 수행합니다 

### 다앙한  컨테이너 
> 리눅스 운영체제가 담긴 컨테이너 
- ubuntu : -d 없이 it 옵션만으로 사용 
- debian : -d 없이 it 옵션만으로 사용 
- centos : -d 없이 it 옵션만으로 사용 
- fedora : -d 없이 it 옵션만으로 사용 


### 웹 서버 및 데이터베이스 서버용 컨테이너 
- httpd 
  - 웹 서버로 -d 로 백그라운드 실행을 하고 -p로 포트 설정 
- nginx 
  - 웹 서버로 -d로 백그라운드 실행을 하고 -p 로 포트 설정 
- mysql
  

### docker search
```
docker search --limit 10 ubuntu 
```

### docker ubuntu
```
docker run --name 컨테이너이름 --it 이미지이름 
```


### 오라클 컨테이너 사용 
- 오라클은 8080포트 와 1521 (외부 접속을 위한 포트) 번 포트 사용 
- Mac은 M1 프로세서는 구동이 안됨 
  - docker 에 추가로 Colima를 설치하면 가능하다
- 오라클은 외부 접속을 위해서 아무것도 하지않아도된다 
  - 서비스 이름이 xe이고 관리자 계정의 비밀번호는 oracle이 된다 

### 여러개의 컨테이너를 하나의 네트워크로 묶어 연동 
- 워드프레스 와 MySQL을 연동 
- 워드 프레스 
  - 웹 사이트를 만들기 위한 소프트웨어로 서버에 설치해서 사용 
  - 워드프레스는 아파치,php런타임 그리고 데이터베이스가 필요하다 
### 도커 네트워크 
> 가상의 네트워크로 네트워크 에 속한 컨테이너 끼리 연결을 시켜서 서로 접속이 가능하도록 해주는 것
- 생성 
  - docker network create 이름 
- 삭제 
  - docker network rm 이름 
- 목록 
  - docker network ls 이름 
- mysql
```
docker run --name 컨테이너이름 -dit --net=접속할네트워크이름 -e MYSQL_ROOT_PASSWORD=관리자비밀번호 -e MYSQL_DATABASE=데이터베이스이름 -e MYSQL_USER=사용자이름 -e MYSQL_PASSWORD=사용자비밀번호 -p 호스트포트번호:3306 이미지이름 --character-set-server=문자인코딩 --collation-server=정렬순서 --default-authentication-plugin=인증방식

```
- -e 옵션은 환경변수 
  - 관리자 비밀번호는 필수 
  - 관리자만 있어도 모든 작업을 수행할 수 있지만 관리자 계정으로 데이터베이스를 사용하는 것은 보안상 문제가 발생할 소지가이싿
- MYSQL과 mariadb는 authentication-plugin 이 다른 의미를 갖는데 MYSQL 8.0버전 부터 보안을 위해서 인증 방식을 변경했는데 비밀번호를 해싱해서 보관을 하는 형태로 변경했다.
  - 이전 방식으로 데이터베이스 접속이 가능하도록 하고자하는 경우 --default-authentication-plugin 에 mysql_native_password를 설정해주어야한다 
  - 이 작업은 데이터베이스에서 관리자로 접속해서 변경하는것도 가능하다 
- 워드 프레스 컨테이너 생성 
```
docker run --name 컨테이너이름 -dit --net =접속할네트워크이름 -p 포트번호 -e WORDPRESS_DB_HOST =데이터베이스컨테이너이름 -e 
WORDPRESS_DB_NAME =데이터베이스이름 -e WORDPRESS_DB_USER =사용자계정 -e WORDPRESS_DB_PASSWORD =비밀번호 wordpress
```
- 실행확인 http://localhost:포트번호 

### 실습 

```
docker network create wordpress001net1

 mysql network 연결 
docker run --name mysql000ex11 -dit --net=wordpress000net1 -e MYSQL_ROOT_PASSWORD=myrootpass -e MYSQL_DATABASE=wordpress000db -e MYSQL_USER=wordpress000kun -e MYSQL_PASSWORD=wkunpass mysql --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci --default-authentication-plugin=mysql_native_password


 wordpress network 연결 

docker run --name wordpress000ex12 -dit --net=wordpress000net1 -p 8085:80 -e WORDPRESS_DB_HOST=mysql000ex11 -e WORDPRESS_DB_NAME=wordpress000db -e WORDPRESS_DB_USER=wordpress000kun -e WORDPRESS_DB_PASSWORD=wkunpass wordpress
```


### mysql 접속권한 만들기 

```
create user '유저아이디'@'접속할 ip' identified by '비밀번호';
```
- 모든 곳에서 접속하게 하고자 할때는 ip 대신 % 

```
grant all privileges on 데이터베이스이름 to '유저아이디'@'접속할ip' 
grant all privileges on *.* to 'user00';

모든 데이터베이스 접속가능한 권한 
```

```
create user 'user00'@'%' identified by 'user00';
Query OK, 0 rows affected (0.01 sec)

mysql> grant all privileges on *.* to 'user00';
    Query OK, 0 rows affected (0.01 sec)

```


```
alter user 'user00'@'%' identified with mysql_native_password by 'user00';
```
- 옛날버전일 경우에 여기까지만 설정해도되는데 요즘버전은 설정을한번 더해줘야한다
  - mysql 8.0은 인증방식을 예전 방식으로 되돌려야만 외부에서 접속이가능 

```
flush privileges;
```

### 레드마인 및 mariaDB 컨테이너 결합 
> 레드마인 
- 웹 기반의 프로젝트 관리 와 버그 추적 기능을 제공하는 도구 
- 프로젝트 관리에 도움이 되는 달력 과 Gantt Chart 를 제공하고 일정 관리 기능을 제공 
- MariaDB 컨테이너 생성 
  - 이미지 이름이 mariadb 일 뿐이고 컨테이너 생성 방법은 동일 
### 실습 
- 네트워크 생성
```
docker network create redmine000net3 
```
- 마리아디비 컨테이너 생성
```
docker run --name mariadb000ex15 -dit --net redmine000net3 -e MYSQL_ROOT_PASSWORD=wnddkd -e MYSQL_DATABASE=redmine000db -e MYSQL_USER=redmine000kun -e MYSQL_PASSWORD=rkunpass mariadb --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci --default-authentication-plugin=mysql_native_password 
```
- 레드마인 컨테이너 생성 
```
docker run -dit --name redmine000ex16 --net redmine000net3 -p 8089:3000 -e REDMINE_DB_MYSQL=mariadb000ex15 -e REDMINE_DB_DATABASE=redmine000db -e REDMINE_DB_USERNAME=redmine000kun -e REDMINE_DB_PASSWORD=rkunpass redmine
```
- 확인 localhost:레드마인포트번호 
- 뒷정리 


### 컨테이너 와 호스트 간에 파일 복사 
- 프로그램 만으로 구성된 시스템은 그리 많지 않음 
- 실제 시스템은 프로그램 ,프로그래밍 언어의 런타임 , 웹 서버 , 데이터베이스 서버 등이 함께 시스템을 구성하고 그 이외도 파일 데이터가 포함 
- apache 의  경우 내부적으로 index.html 을 소유하고 있다가 웹에서 호출을 하면 index.html 을 출력한다 
- 데이터베이스들도 백업을 하면 자신의 특정한 디렉토리 백업 파일을 보관한다 
- 이런 파일들을 호스트에게 전송할 수 있고 호스트에게 받아서 사용할 수 도 있다 . 


### 명령 
- 호스트에서 컨테이너로 복사 
  - docker cp 호스트경로 컨테이너이름:컨테이너경로 
- 컨테이너에서 호스트로 복사 
  - docker cp 컨테이너이름:컨테이너경로 호스트경로

### apache 의 index.html 파일의위치 
- /usr/local/apach2/htdocs 
- 호스트 컴퓨터의 index.html 파일을 apache 컨테이너의 index.html 파일로 사용 

### 아파치 컨테이너 생성 

```
docker run --name apa000ex19 -d -p 8089:80 httpd
```

### 아파치 컨테이너에 있는 index.html 을 로컬에서 만든 index.html로 바꿔주기 
```
docker cp /Users/kimjeounghoun/Jeounghoun/강의/kakaoCloudSchool/docker/index.html apa000ex19:/usr/local/apache2/htdocs
```
- 로컬에서 만들어둔 index.html 이 컨테이너에서 실행된 index.html이 아닌 로컬에서 만들고 복사된 index.html 이실행됨 

### 컨테이너의 파일을 호스트로 복사 
```
docker cp apa000ex19:/usr/local/apache2/htdocs/index.html /Users/kimjeounghoun/Jeounghoun/강의
```
- 강의 폴더에 컨테이너에서 실행시킨 index.html 파일이 복사되어있음 


### 스토리지 마운트 - 볼륨 마운트 
- Volume 
  - 스토리지의 한 영역을 분할 한 것 
- Mount 
  - 일반적으로 연결을 해서 운영체제나 소프트웨어가 관리를 할수 있도록 하는것
  
### 스토리지 마운트 이유 
> 컨테이너 안에 만들어진 데이터는 컨테이너가 소멸되면 같이 사라지게 되는데 이 때 데이터는 남겨서 다른 컨테이너나 호스트에서 사용하고자 하는 경우에 직접 복사를 해도 되지만 이런 복사 작업은 수정을 할 때 마다 수행을 해야 하기 때문에 번거롭기때문이다 .
- 데이터를 다른 외부 장치 와 연결해서 컨테이너 와 독립적으로 유지시키는 것을 Data Persistency 라고 하고 이를 위해서 스토리지 마운트를 수행한다  

### 마운트 방식 
- 볼륨 마운트 
  - 도커 엔진이 관리하는 영역 내에 만들어진 볼륨을 컨테이너에 디스크 형태로 제공하는 것을 볼륨마운트라고한다 
- 바운드 마운트 
  - 도커 엔진이 설치된 컴퓨터의 디렉토리 와 연결하는 방식
```
            볼륨 마운트          바인드 마운트 
 스토리지 영역  볼륨               디렉토리
 물리적 위치   도커 엔진의 관리 영역  어디든지 가능 
 마운트 절차   볼륨을 생성한 후 마운트 기존 파일이나 디렉토리 
 내용 편집     도커 컨테이너 이용    연관된 프로그램 
 백업         복잡               일반 파일과 동일 방식   
```


### 마운트 명령어 
- docker container run 할 때 옵션의 형태로 제공 
- 스토리지의 경로가 컨테이너 속 특정 경로 와 연결되도록 설정 
- 컨테이너의 경로는 도커 이미지의 문서를 참조해서 확인 

- apache의 경우는 /usr/local/apache2/htdocs 이고 mysql의 경우는 /var/lib/mysql


### 바인드 마운트 실습 
- 바인드 할 디렉토리 생성 
```
/Users/kimjeounghoun/Jeounghoun/web 
```
- apache 컨테이너 생성 
```
docker run --name apa000ex20 -d -p 8090:80 -v /Users/kimjeounghoun/Jeounghoun/web:/usr/local/apache2/htdocs httpd
```
- 이렇게 하게되면 web 에 index.html을 참조하게됨 

### 볼륨 마운트 
> 볼륨 생성 
- 기본 명령 
  - docker volume create 볼륨이름 
    - volume 은 도커엔진에 만들어지는 것 
- 볼륨 상세 정보 확인 
  - docker volume inspect 볼륨이름 
- 볼륨 삭제 
  - docker volume rm 볼륨이름 

### 볼륨 실습 
```
docker volume create apa000vol1
```

- 볼륨 목록보기
```
docker volume ls 
```
- 볼륨 상세보기 
```
docker volume inspect apa000vol1
```

- 연결 시켜보기 
```
docker run --name apa000ex21 -d -p 8091:80 -v apa000vol1:/usr/local/apache2/htdocs httpd
```

```
docker container inspect apa000ex21
```

- 컨테이너가 무슨볼륨이랑 연결되었는지 확인할수있다 

### 컨테이너로 이미지 만들기 
>다른 개발자와 동일한 개발 환경을 만들기 위해서 주로 이용 
- 이미지를 만드는 방법 
- commit 커맨드로 컨테이너를 이미지로 변환하는 방법 
- Dockerfile 스크립트로 이미지를 만드는 방법 

### commit 명령으로 이미지 만들기 
- 기본형식 
  - docker commit 컨테이너이름 새로운이미지이름 


### commit 명령으로 apache 컨테이너를 이미지로 생성 
- 아파치이미지를 컨테이너화 
```
docker run --name apa000ex22 -d -p 8091:80 httpd
```
- index.html을 생성된 컨테이너에 복사 - 해당 index.html로 인해 itworks -> 바뀐 index.html 내용 
```
docker cp /Users/kimjeounghoun/Jeounghoun/강의/docker/index.html apa000ex22:/usr/local/apache2/htdocs 
```

- docker commit - 커밋으로 인해 컨테이너를 이미지화  
```
docker commit  apa000ex22 copyimage
```

- 복사한 이미지를 기반으로 컨테이너 생성
```
docker run --name apa000ex23 -d -p 8093:80 copyimage
```

### Dockerfile 스크립트로 이미지 만들기
> 스크립트 파일에 명령어를 기재하고 이 파일을 빌드를 해서 만드는 방식
- 빌드 명령어
```
docker build -t 이미지이름 스크립트파일의디렉토리경로 
```
### 스크립트 명령어
```
FROM: 토대가 되는 이미지를 지정 
ADD : 이미지에 파일이나 폴더를 추가 
COPY: 이미지에 파일이나 폴더를 추가 
RUN: 이미지를 빌드 할 때 실행할 명령어를 지정 
CMD: 컨테이너를 실행할때 실행할 명령어를 지정 
```

- 컨테이너에 추가할 파일과 스크립트가 저장될 파일을 위한 디렉토리를 저장
- 컨테이너 와 함께 저장될 파일을 위의 디렉토리에 복사 
- Dockerfile 파일을 작성 
```
FROM httpd
COPY index.html /usr/local/apache2/htdocs  (index.html을 /usr/local/ … 에 복사하는 명령어) 
```
- Dockerfile 파일을 빌드해서 이미지를 생성 
```
docker build -t 이미지이름 Dockerfile경로 

docker build -t dockerimage /Users/kimjeounghoun/Jeounghoun/강의/web
```


### 컨테이너 개조 
> 도커를 실제 운용하는 현업에서는 사내에서 개발한 시스템을 사용하는 경우가 많음 
- 기본 이미지를 커스터마이징한 이미지를 많이 사용 

- 컨테이너 개조 
  - 파일 복사 와 마운트를 이용하는 방법 
  - 컨테이너에서 리눅스 명령어를 실행해서 소프트웨어를 설치하거나 설정을 변경하는 방법 
    - 리눅스 명령엉를 실행할려면 셸에서 작업을 수행해야 한다 
    - 셸 중에는 bash 셸을 많이 사용한다 
  - 컨테이너를 만들 떄 아무 옵션 없이 생성하면 셸에 들어갈 수 없는 경우가 발생 - bash셸을 사용할수있는 상태를 만들고자 하는 경우 
    -  docker exec 옵션 컨테이너이름 /bin/bash 
    -  docker exec 옵션 이미지이름 /bin/bash  
       - 이 구문에 명령어를 실행했을경우 컨테이너가 실행되지 않고 bash 가 실행됨
### bash 명령 
> 대부분의 경우 debian 계열의 리눅스에서 이미지를 생성 
- debian이여서 리눅스명령어를 알고있으며 좋다 

### 이미지 업로드  (도커 허브)
> 나만의 이미지를 만들어서 도커허브에 복사해서 저장시켜주고 땡겨오는 방식(정훈)
- 도커 허브 
  - 이미지를 내려받고 올려받을수있는 도커 제작사에서 운영하는 공식 도커 레지스트리
  - 오픈 소스 재단의 많은 개발자들이 도커 허브에 참여 
- 레지스트리
  - 레지스트리는 배포하는 장소
- 레포지토리 
  - 레지스트리를 구성하는 소프트웨어 

### 이미지 이름 과 태그 
> 도커 허브에 공개로 이미지를 업로드하는 경우는 이미지에 태그를 부여해야 함 
- 태그 형식 
  - 레지스트리주소(도커허브의 ID)/레포지토리이름:버전 
- 이미지에 태그를 부여하기 
  - 방법 1 
    - 처음부터 이미지 이름을 태그 형식으로 만들어서 사용 
  - 방법 2
    - docker tag 원본이미지이름 태그 

### 도커 허브에 업로드 
```
docker push 태그 
```