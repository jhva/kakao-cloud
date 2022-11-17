# Nodejs

### module
- 독자적으로 실행가능한 단위 
    - 함수
    - 클래스 

### Package
- 1개 이상의 파일로 구성된 배포단위

### Process
- 실행 중인 프로그램

### Thread
- 독자적으로 실행할 수 없는 Process 작업 단위 

### NPM(Node Package Manage)
> 노드에서의 패키지 매니저인데 지금은 거의 모든 자바스크립트 라이브러리들이<br/>
저장소에 있기 때문에 자바스크립트 라이브러리들은 거의 모두 NPM을 이용해서 사용

- package.json
    - 노드에서 패키지 관리를 위한설정 파일
    - java 에서의 build.gradle 이나 pom.xml의 역할을 수행
    - 패키지를 설치하게 되면 패키지에 대한 정보가 전부 작성됨. 

### 패키지 설치 
- npm install 패키지 이름 나열 (나열 할때는 공백으로 구분)
    - 개발 과정에서만 사용하고 배포할때는 제외하고자하는경우에는 패키지 이름 앞에 --save-dev 를 추가하면 된다
    - 모든 프로젝트에서 사용할수 있도록 하기위해서는 global 모드로 설치하는데 <br/>
    패키지 이름 앞에 -g 를 추가하면 된다 
        - mac이나 linux에서 global 로 설치할 때는 맨앞에 관리자 모드를 의미하는 sudo 를 추가해야한다
            - sudo npm install --location =global 패키지이름의 형태롤 설치해야 한다.
- 설치 된 패키지는 프로젝트 내의 node_modules 라는 디렉토리에 저장이 된다
- 여러개 설치할 때
    - npm install morgan cookie-parser express-session

- nodemon 패키지 설치 : nodemon 패키지는 소스코드를 수정하면 자동으로 재실행되게<br/> 해주는 패키지로 개발 과정에서만사용
- rimraf 패키지 설치 : rimraf 패키지는 윈도우에서 터미널에서 rm  명령을 사용하기 위해서 설치 
    - npm install --location=g rimraf

### 패키지 재설치 
- 모든 패키지는 node_modules 에 다운로드 받아서 저장하게 되는데 <br/> 배포를 하거나 코드를 가지고 갈때 node_module를 포함시키면
크기가 너무커지는데 이런 경우에는 node_module 는 복사하지 않고 package.json 만 가져간후 새로운곳에서<br/>
npm init 명령만 다시 수행하면 패키지들이 전부 설치 된당.

### Express 
> 웹 애플리케이션 서버를 만들어주는 패키지  (npm install express)

- Web Server :다른곳에서 URL을 이용해서 사용할 수 있도록 해주는 서버
    
- Web Application Server : URL 과 작업을 매핑해서 URL 을 호출하면 작업을 수행하도록 해주는 서버

- Application Server: 작업을 해주는 서버 



