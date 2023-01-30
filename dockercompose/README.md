# Docker compose 
> Docker run 명령어를 여러개 모아 놓은 것과 유사하고 컨테이너 와 주변 환경을 생성할수 있음 (네트워크 나 볼륨 까지 생성 가능 )


### 쿠버네티스와의 차이 
> 쿠버네티스는 도커 컨테이너를 관리하는 도구로 여러개의 컨테이너를 다루는 것 과 관계가 깊음 
- 도커 컴포즈에는 컨테이너를 관리하는 기능이 없음 

### 사용하기 전 준비 사항 
- 도커 데스크탑을 사용하는 경우는 별도의 설치 과정이 필요없음
- 리눅스에서는 python3 Runtime 이 필요 
```
sudo apt install -y python3 python3-pip : python3 의 런타임 과 pip설치 
```
```
sudo pip3 install docker-compose  
```


### 사용법 
- 도커 컴포즈 가 사용할 디렉토리 생성 
- 정의 파일을 디렉토리에 작성 
  - 하나의 디렉토리에 정의 파일은 1개만 존재 
- 정의 내에서 사용될 파일들도 모두 동일한 디렉토리에 배치 
- 작성법
```yml
version: " 버전 -3"
services: 
        apa000ex2(사용할컨테이너이름) :
              image: 사용할 이미지 이름 (httpd)
              ports:
                - 포트포워딩 
              depends_on:
                - mysql000ex11  (이걸 추가한 이유는 워드프레스는 항상 mysql이 켜져있어야만 동작이가능함 
              restart: always
              environment:
                WORDPRESSS_DB_HOST = mysqlex000ex11
                ...
```

- 도커 워드프레스
```
docker run --name wordpress000ex12 -dit --net=wordpress000net1 -p 8085:80 -e WORD_PRESS_DB_HOST=mysql000ex11 -e 
...
```
### 도커컴포즈의 파일형식 yml 
> 기보넞ㄱ인 파일 이름은 docker-compose.yml인데 실행할 때 -f 옵션을 이용하면 다른이름도가능 
- 작성은 맨 앞에 compose 버전을 설정한 후 services, networks , volumes를 기재 
- 도커 컴포즈 와 쿠버네티스는 컨테이너의 집합을 서비스라는용어로 부름 
- 작성을 할 때는 주항목 - > 이름 추가 -> 설정 과 같은 순서로 작성 
- 각 이름들은 주항목보다 한 단 들여쓰기를 해야하는데 공백 개수는 몇 개 이던지 상관없지만 한 번 한단을 공백 한개로 정하면 그 뒤로는 계속 같은 공백을 사용해야한다 
  - 설정을 작성할 때 앞에 하나의 공백을 추가해줘야한다 
- 하나의 설정인 경우는 바로작성해도 되지만 여러 개의 설정을 하는 경우는 앞에 - 를 추가해주어야 한다 . 


### 컴포즈 파일의 항목 
> 주항목 
- services 
  - 컨테이너 (독립된 프로그램 과 데이터 )
- network 
  - 여러 개의 컨테이너는 하나로 묶어서 통신이 가능하도록 하고자 할때 사용 
- volumes
  - 호스트 컴퓨터나 도커 엔진의 영역과 컨테이너 내부 영역을 매핑시키고자 할 때 사용 

### 자주 사용하는 정의 내용 
- image
  - 사용할 이미지를 설정 
- networks
  - --net 옵션과 동일 
- volumes
  - -v, --mount
- ports
  - -p
- environments
  - -e
- depends_on
  - 다른 서비스에대한 의존관계설정 
- restart 
  - 컨테이너를종료시 재시작 여부를 설정 
  - no(재시작 하지않음), always(항상 재시작), on-failure(프로세스 0외의 상태로 종료되었다면 재시작), unless-stopped(종료의 경우는 재시작안함 그 외의 경우 재시작 )
- logging 
  - --log-driver 에 해당하는 것으로 로그 출력 대상을 설정 

### 컴포즈 파일 작성 (실습)
> 생성할 네트워크, 볼륨 , 컨테이너 정보 
- 네트워크이름 : wordpress000net1
- mysql 볼륨 : mysql000vol11
- 워드프레스볼륨 : wordpress000vol12
- Mysql컨테이너 : mysql000ex11
- 워드프레스 컨테이너 :wordpress000ex12

### Mysql컨테이너 
- 이미지 : mysql5.7
- 네트워크 :wordpress000net1
- 사용할 볼륨- mysql000vol11
- 마운트 위치 -/var/lib/mysql(mysql의 데이터 디렉토리)
- 재시작 설정 - always
- 환경변수 
  - 루트패스워드(MYSQL_ROOT_PASSWORD)
  - 사용할 데이터베이스이름 (MYSQL_DATABASE) - wordpress000db
  - 사용자 이름(MYSQL_USER) -wordpress000kun
  - 사용자 비밀번호(MYSQL_PASSWORD) - wkunpass

### wordpress 컨테이너 
- 의존관계 - mysql000ex11
- 이미지 - wordpress
- 네트워크 :wordpress000net1
- 사용할볼륨 - wordpress000vol12
- 마운트 위치 - /var/www/html(html파일의 경로)
- 포트 설정 - 8085:80
- 환경변수 
  - 데이터베이스 컨테이너이름 WORDPRESS_DB_HOST mysql000ex11
  - 데이터베이스 이름 WORDPRESS_DB_NAME wordpress000db
  - 데이터베이스 사용자 이름 WORDPRESS_DB_USER wordpress000kun
  - 데이터베이스 사용자 패스워드 WORDPRESS_DB_PASSWORD wkunpass

### 데이터베이스 프로그램을 docker를 이용해서 사용하고자 할때는 데이터 디렉토리를 확인해서 볼륨이나 외부 스토리지에 마운트를 하고 사용하는것을 권장한다 

### docker-compose mysql8.0을 사용하고자 하는 경우 
- 데이터베이스에 접속해서 사용자 패스워드 설정을 변경해주거나 mysql8.0 컨테이너를 생성할 때 커맨드를 추가 
```
공백 4개 추가하고 command: mysqld --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci --default-authentication-plugin=mysql_native_password
```

### 도커 컴포즈 관련 명령어 
- docker compose 명령
- 명령
  - up
    - 컴포즈 파일에 정의된 컨테이너 및 네트워크를 생성
  - down
    - 컨테이너와 네트워크를 종료하고 삭제
  - stop
    - 컨테이너 와네트워크를 종료 
```
docker-compose -f yml파일의경로 up 옵션
```
- 옵션 기능 
  - -d
    - 백그라운드로 실행 
  - --no-color
    - 화면 출력 내용을 흑백으로 출력 
  - --no-deps
    - 링크된 서비스를 실행하지 않음
  - --force-recreate
    - 설정 또는 이미지가 변경되지않더라도 컨테이너를 재생성
  - --no-create
    - 컨테이너가 이미 존재하는 경우 재생성하지 않음 
  - --no-build
    - 이미지가 없어도 이미지를 빌드하지 않음 
  - --build
    - 컨테이너를 실행하기 전에 이미지를 빌드 
  - --abort-on-container-exit
    - 컨테이너가 하나로도 종료되면 모든 컨테이너를 종료 
  - -t,-timeout
    - 컨테이너를 종료할 때 타임아웃 옵션으로 기본은 10초 
  - --remove-orphans
    - 컴포즈 파일에 정의되지않은 ㅓㅅ비스의 컨테이너를 삭제 
  - --scale
    - 컨테이너의 수변경 

- 도커 컴포즈 삭제 
```
docker-compose -f compose파일경로 down 옵션
```
- 옵션 
  - --rmi종류  
    - 삭제할때 이미지도 같이 삭제하는데 all로 설정하면 모든 이미지가 삭제되고 local로 지정하면 태그가 없는 이미지만 삭제 
  - -v,--volumes 
    - 볼륨을 삭제 
  - --remove-orphans
    - 컴포즈 파일에 정의되지 않은 서비스의 컨테이너도 삭제
- 도커 컴포즈 stop
```
docker-compose -f compose 파일 경로 stop 옵션 
```
- 컨테이너를 중지하고 삭제하지는 않음 
- 그 이외의 명령 
  - ps
    - 컨테이너 목록 출력 
  - config
    - 컴포즈 파일을 확인하고 내용을 출력 
  - port
    - 포트 설정 내용을 출력 
  - logs
    - 컨테이너가 출력한 내용을 화면에 출력 
  - start
    - 컨테이너 시작 
  - kill
    - 컨테이너 강제 종료 
  - exec
    - 명령어를 실행
  - run
    - 컨테이너 실행 
  - create
    - 컨테이너 만들기 
  - restart
    - 컨테이너 재시작 
  - pause
    - 컨테이너 일시 중지 
  - unpause
    - 컨테이너 일시 중지 해제 
  - rm
    - 컨테이너 삭제 
  - build
    - 컨테이너에 사용되는 이미지를 빌드 똔느 재빌드 
  - pull
    - 이미지 다운로드 
  - scale
    - 컨테이너 수를 지정 
    - 동일한 이미지를  컨테이너를 여러 개 생성 
  - events
    - 컨테이너로부터 실시간으로 이벤트를 수신 
  - help
    - 도움말 
### 실습 
> 컴포즈 내용을 이용해서 생성 
```
docker-compose -f 파일경로 up -d
```


### 볼륨 목록확인
- docker volume list
- docker volume rm 볼륨이름 