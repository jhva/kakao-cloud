# Linux

> Git을 이용해서 커널의 소스 코드를 공개

- 리눅스 = 리누스 + 유닉스

### 리눅스의 종류

- Debian -> Ubuntu
- Slackware -> Open SUSE
- Redhat -> Fedora , CentOS

### 리눅스 구조

- Kernel
    - 핵심,구조,관리
- shell
    - 명령어 인터페이스
- utility
    - 기타 응용 프로그램

# Ubuntu

> 데비안 계열에서 가장 성공한 데스크톱 배포판

- GNOM 이라는 GUI환경을 제공
    - 현재는 구글이 가장 많이 사용

### 설치

- 컴퓨터에 직접 설치
    - 로컬 컴퓨터나 랜탈 환경에 설치
- 가상 머신 이용
- 도커 와 같은 컨테이너 이용
    - 리눅스를 학습할 때는 비추천

### 가상머신

> 현재 운영체제에 가상의 컴퓨터를 생성한 후 여기에 운영체제를 설치할 수 있도록 해주는 소프트웨어

- VMWare
- Virtual PC
- Virtual Box
    - Oracle 에서 제공

### 가상머신에 리눅스설치

- 리눅스 이미지가 필요

### vi 편집기

- 명령모드
    - 입력모드로 전환할때는 i,l,A,o,O 를 누르면 가능
- 입력모드
    - 명령 모드로 전환할 때는 esc
- q!
    - 저장하지 않고 종료
- wq!
    - 저장하고 종료

### process

> 실행 중인 프로그램

- 프로세스 번호 와 작업 번호
    - 프로세스 번호는 프로세스를 구분하기 위한 번호
    - 작업 번호는 백그라운드 프로세스가 CPU 를 점유해서 실행될때의 순서에 관련된 번호

### Daemon Process

> 다른 서비스를 제공하기 위해 존재하는 프로세스

- 평상시에는 대기 상태로 존재하다가 서비스 요청이 오면 해당 서비스를 제공

## 부모 자식 관계의 프로세스

> 부모 프레세스가 종료되면 자식 프로세스도 종료

### 고아 프로세스

> 자식 프로세스가 종료되지않은 상태에서 부모 프로세스가 종료되는 경우

### 좀비 프로세스

> 자식 프로세스가 정상적인 종료가 되지 않은 상태에서 부모프로세스가 종료되는 경우

### ps 명령

> 프로세스 목록 출력

- kill
    - 프로세스 종료
    - kill은 pid 로 종료
- pkill
    - 프로세스 이름으로 종료

### 패키지 관련 명령

- apt 명령
    - Advanced Package Tool 의 약자로 패키지 관련 명령
    - Fedora 와 CentOS 에서는 yum이라는 명령

### apt-cache

- 패키지 와 관련된 정보 출력
- 기본 형식
- apt-cache[옵션][하위명령]

- 옵션
    - -f
        - 전체 정보출력
    - -h
        - 도움말 출력
- 하위 명령
    - stats
        - 통계
    - dump
        - 패키지 업그레이드
    - pkgnmaes
        - 사용가능한 패키지 이름출력
    - search[키워드]
        - 검색
    - show[패키지 이름]
        - 패키지 정보출력
    - showpkg[패키지 이름]
        - 패키지

### apt-get

> 패키지 설치 및 업데이트

- 기본형식
- apt-get[옵션][하위 명령]
- 옵션
    - -d
        - 다운로드
    - -f
        - 깨진 패키지 수정
    - -h
        - 도움
- 서브 명령
    - update
        - 새로운 패키지 정보를 가져오기
    - upgrade
        - 모든 패키지를 최신 버전으로 업그레이드
    - install 패키지 이름
        - 설치
    - remove 패키지 이름
        - 제거
    - purge 패키지 이름
        - 패키지 서 와 설정 파일 모두 제거
    - autoremove
        - 시스템에 설치된 패키지를 자동으로 정리 및 제거
    - autoclean
        - 오래된 패키지 와 불완전한 다운로드 패키지 제거
    - check
        - 의존성이 깨진 패키지 확인
    - clean
        - 캐싱되어 있는 패키지 삭제

### shell Script

> 사용자 와 커널 사이에서 중계자 역할을 수행하는 프로그램

- 종류
    - bourne shell
        - 처리 속도가 빨라서 초창기에 많이 이용하고 sh 명령을 사용하는 경우가 bourne shell
    - C shell
        - bourne shell 의 기능이 확장된 것으로 작성 형식이 C 언어와 유사해서 붙여진 이름이며 여기서는 csh 라는 명령을 사용함, 처리속도가 느린편
    - Korn shell
        - bourne shell 과 호환성을 제공하고 처리 속도가 빠른 편이며 우분투에서는 별도로 설치해서 사용하면 ksh명령 을 사용
    - bash shell
        - 3가지 shell의 호환성을 유지하면 기능을 강화한 shell로 우분투 리눅스의 기본 셸이면 bash명령을 사용하는데 우분투 6.10 이상은 dash shell 기본이 Shell

### 자신의 기본 shell 확인

- ls -l /bin/sh

### 셸에서 환경 변수

- 변수명 앞에 $를 붙여야한다
- 환 경 변수의 이름은 대문자로 하는 관례
    - ex
        - HOME(사용자의 홈 디렉토리) 이라는 환경 변수의 값을 확인 하고싶으면
            - echo $HOME
        - bash의 버전 확인 이라는 환경 변수의 값을 확인
            - echo $BASH__VERSION

### 변수 생성

> 이름 = 문자열 의 형태로 작성 ,공백을 포함하면 안됨

- 출력할 때는 echo 명령 이용

### 셸 변수와 환경 변수 사이의 전환

> export [옵션] [셸 변수]

- 옵션으로 -n 을 설정하면 환경 변수를 셸 변수로 전환
- export = 셸변수 = 문자열 의 형태로 설정하는 것도 가능
- 환경 변수 확인 명령은 Env

### 셸 변수 삭제

- unset 변수 이름

## Shell Script

- 셸이나 커맨드라인 인터프리터에서 수행하도록 작성된 운영체제를 위한 스크립트
- 장점은 다른 프로그래밍 언어에서 작성된 코드보다 빠르게 처리된다는 것
- 단점은 고품질의 코드 와 확장을 기록하기는 힘들다는 것

### 셸 스크립트 작성 및 실행

> 현재 디렉토리에 셸 스크립트 파일을 생성하고 작성

```
#! /bin/sh

echo "Name Print"
echo " >> Connect Name :" $ USERNAME

exit 0
```

- 실행은 sh 파일명.sh
- 실행을 할 때 다른 디렉토리에 존재하면 실행 오류가 발생
    - 이런 경우에는 sh 파일을 현재 디렉토리로 옮기거나 chmod +x 스크립트파일명 명령을 이용해서 실행 가능 속성을 추가

### 키보드 입력

- read 변수명
- 스크립트 파일 생성
    - sudo gedit inout.sh

```
echo "Input: "
read input_string 
echo  "Output: $input_string "
```

### 산술 연산

> 백틱 (`) 다음에 expr 을 입력하고 산술 연산을 수행하고 백틱 (`) 으로 막으면 된다

### 조건문

```
if [조건식]
then 
       조건식이 참일 경우 수행할 내용 
fi 
```

- 같다 =
- [] 에서는 뒤 와 앞에 공백이 있어야 하고 = 도 조와우에 공백이 있어야 한다

### 관계 연산자

- 문자열 비교
    - =
    - !=
    - -n
        - NULL 이 아닌 값
    - -z
        - NULL 값

- 산술 비교
    - -eq
    - -ne
    - -gt
    - -ge
    - -lt
    - -le
    - !

### case ~ esac

- if 구문은 조건이 많아지면 구문이 복잡해지는데 case ~ esac 구문은 여러 개의 조건을 펼쳐놓고 어느 조건에 해당하는지를 판별해서 명령을 수행하기 때문에 if ~ else 에 비해서 간결하고 이해하기 쉬움

``` 
case 파라미터 또는 키보드 입력값 in 
    조건 1) 
    조건 1에 해당할경우 실행할 명령 
    .. 
    ..
     
    *) 
    앞에서 주어진 조건 이외의 모든 경우 실행할 명령 
    
esac 
```

- 작성

```
#! /bin/sh

echo " >> season choice : Spring / Summer / Fall / Window "
case "$1" in 
          Spring)
          echo " >> choice : Spring " ;; 

```

### and 와 or

- and
    - -a 또는 &&
- or
    - -0 또는 ||

### 반복문

> for

```

for 임시변수 in [범위](리스트 또는 배열 , 묶음 등)
do 
         반복 수행할 내용 
done 
```

```shell
cnt =0 
for num in 1 2 3 4 5
do 
        echo " >> No.$cnt : $num"
        cnt = `expr $cnt +1`
done 
```

### seq

> 범위를 만들 때 사용하는 것

```shell
`seq [시작값][종료값]`
```

- 범위 대신에 디렉토리 이름도 가능

### while

```shell
while [조건식]
do 
      반복 수행할 내용 
done 
```

### until

- while 차이
    - 조건식이 false 이면 반복 수행할 내용

```shell
until [조건식]
do
        조건식이 false 이면 반복 수행할 내용 
done 
```

### 기타 명령어

- break
    - 반복문 중단
- continue
    - 조건식으로 이동
- exit
    - 프로그램 종료
- return
    - 함수의 수행을 종료하고 호출한 곳으로 돌아가는 명령어

### 함수 와 파라미터

- function
    - 자주 사용되는 코드를 하나의 이름으로 묶어서 사용하기 위한 개념
- 함수 사용 이유
    - 한 번에 수행되어야 하는 코드가 너무 길어서 분할
    - 동일한 코드를 자주 호출하기 때문에

```shell
함수 이름 (){
          함수 내용 
          $1 
          $2 
        }
```

- 함수호출
    - 함수이름 파라미터1 파라미터 2 ..

## 네트워크

### 네트워크 인터페이스 설정 확인

- ipconfig 인터페이스이름 옵션 값 (window)
- ifconfig 인터페이스이름 옵션 값 (mac)

### 주소체제

- IPv4
    - 32 비트 주소 체계
    - 8bit 씩 묵어서 10진수로 표현
    - 127.0.0.1 :루프백
- IPv6
    - 128비트 주소 체제
        - 4비트씩 묶고 다시 4개씩 묶어서 16진수로 표현
    - 0::0::0::0::0::0::0::1  : 루프백

## 용어

### netmask

- 하나의 그룹을 만들기 위한 영역을 설정하는 주소
    - 1과 0 으로 구성되는데 1인 부분이 같으면 동일한 네트워크로 간주한다

### broadcast

- 방송을 위한 주소
    - 네트워크의 모든 구성 요소에게 데이터를 전달할때 사용

### gateway

- 다른 통신망과 원할한 접속을 유지하기 위해 사용되는 네트워크의 포인트
- 예를들어 공유기 ? 확인 해서 내잘못인지 kt 뭐 sk 잘못인지

### ping

- ping IP 주소 나 URL
- 메시지를 전송받고 돌려받음

### DNS

> ip 주소 와 도메인 사이의 변환을 위한 서비스

### nslookup

> nslookup 호스트네임 또는 DNS

- 호스트 네임이나 DNS 서버가 제대로 동작 중인지 확인

## Telnet

- 텔넷
    - 원격지에 있는 서버에 접속하는 프로그램

### 텔넷 서버 생성

- 텔넷 서버 설정을 위한 패키지 설정

```shell
sudo apt-get install xinetd
sudo apt-get install telnetd
```

- 환경 설정 파일을 열어서 추가
    - sudo gedit /etc/xinedt.conf

```shell
- service telnet
{
        disable = no 
        flags = REUSE
        socket_type = stream 
        wait = no 
        user = root 
        server = /usr/sbin/in.telnet 
        log_on_failure += USERID 
}
```

- 데몬으로 동작

```shell
sudo /etc/init.d/xinetd restart 
```

### 텔넷 서버 접속

- 자기 컴퓨터에 접속 : telnet 0
- 다른 컴퓨터에 접속
- telnet
- open IP 주소

### Open SSH

> 텔넷은 서버에 접속할 수 있는 대표적인 방법이긴하지만 데이터가 암호화되지 않은 상태로 전송되기 때문에 보안에 취약해서 패킷 캡쳐 프로그램을 이용하면 데이터를 전부 확인할 수 있다 .

- SSH (Secure Shell)
    - 공개키 방식의 암호화 방식을 이용해서 원격지 시스템에 접근해서 암호화된 메시지를 전송할 수 있는 시스템

### MariaDB 사용

- 설치

```shell
sudo apt -get update 
sudo apt-get install maiadb-server 
```

### web server 생성 
```shell
sudo apt-get install apache2
```
### 서비스 실행 
```shell
systemctl status apache2
sudo service apache2 start 
```

### 서비스 확인
```shell
ps -ef | grep apache
```


### 브라우저에서 확인 
- http://ip 주소 나 localhost 

### 방화벽 설정 
> 현재 컴퓨터가 아닌 다른 컴퓨터에서 접속이 안되는 경우에는 방화벽에서 80 번 포트를 제외해해주어야 한다

### 방화벽 실행 
```shell
sudo ufw enable
```

### 포트 허용 
```shell
sudo ufw allow 80/tcp
```

### 화인 
```shell
sudo ufw status
```

### 서비스 재시작 
```shell
systemctl status apache2
sudo service apache2 start
```