# MariaDB 
>SQL 에 기반을 둔 RDBMS(관계형 데이터베이스)로 Open Source형태로 제공
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
        - 모든 데이터베이스를 사용하고자 하는 경우는 *.*
        - 모든 곳에서 접속하도록 하고자 할 때는 % 를 설정하고 로컬에서만 접속하도록 할때는 localhost
        - 권한 설정 명령은 설정하고 적용 명령을 수행

    - root  계정을 모든 곳에서 접속하도록 설정
        - GRANT all privileges on *.* TO 'root'@'%';
        - FLUSH privileges;
    
- 서버설정 
> /etc/mysql/mariadb.conf.d/50-server.cnf 파일의 bind-address 부분을 허용할 ip 로 변경해주어야하는데 0.0.0.0 이면 모든 곳에서 접속 가능 
    - 실제 서버 설정이라면 Application Server 의 ip 만 ㅓㅎ용함
- 도커는 직접파일을 수정할 수 없기 때문에 터미널에서 컨테이너의 bash로 접속
    - docker exec -it 컨테이너이름 bash 명령으로 접속후 
        - apt update
        - apt upgrade
        - apt install vim
    - vim /etc/mysql/mariadb.conf.d/50-server.cnf 명령을  수정한다