# Java

### 플래폼 독립적

- 여러 운영체제에서 실행되는 프로그램을 한 번만 작성
- 컴파일러가 JRE(JVM) 가 이해할 수 있는 코드를 생성
    - 운영체제 별로 별도의 프로그램을 JRE 를 설치해서 JE가 해석해서 운영체제에서 실행되는 프로글매을 생성해서 실행

## JAVA를 사용하는 이유

> 오픈 소스 프로젝트가 많이 구현되어 있음 - 뛰어난 Echo System

- 자바 개발에 편리한 라이브러리
    - apache common
- 서버 개발에 편리한 프레임워크
    - spring, struts
- 검색엔진
    - Lucene
- NoSQL
    - Cassandra
- 분산 파일 시스템
    - Hadoop

### 플래폼으로 서의 역할

> JVM 기반의 언어가 많음

- Jython
- Scala
- Kotlin
- Closure
- Jruby
- Groovy
- 소스 코드를 작성한 후 컴파일을 하면 JRE 가 이해할수 있는 코드로 번역

### JAVA 개발 플랫폼

- J2SE(Standard Edition)
    - PC용 어플리케이션 개발을 위한 플래폼
    - 웹 프로그래밍을 할 수 없음
    - J2EE에서 웹 관련 API를 가져오면 되는데 보통은 WAS Application 이나 Spring 같은 프레임워크가 제공
- J2ME(Micro Edition)
    - embedded 관련 애플리케이션 개발을 위한 플랫폼으로 J2SE에서 많은 기능을 제거
- J2EE
    - 가장 많은 기능을 가진 유로 버전이였는데 지금은 Eclipse 재단으로 소유권이 이전되면서 Open Source가 됨.

### JAVA 환경

- JDK (Java Development Kit)
    - 자바 개발 도구
    - Java API
        - Java로 프로그램을 만들 수 있도록 제공되는 클래스의 집합
    - JVM (Java Virtual Machine)
        - Java Program 을 실행할 수 있도록 추상화 한 영역
        - Java Program이 실행 될때 메모리영역을 구분해서 확보
            - Register
            - Stack
            - Heap
            - Method
    - 개발에 관련된 프로그램
        - bin 디렉토리
- JRE(Java Runtime Environment)
    - 자바 실행 환경 , 자바로 만든 프로그램을 실행하기 위한 플랫폼
    - JVM (JRE에도 JVM이 있음)
        - 라이센스가 있다 .
            - Hot Spot
            - Open Source
            - OpenJDK
    - glue
        - 플래폼 고유의 라이브러리 와 JNI
    - byte code
        - JDK 를 이용해서 개발한 후 Compile 을 하게되면 생성되는 JRE가 이해가능한 코드

### JVM 구성

- Native Method 영역
    - 운영체제에게 전달할 메서드 영역
- Register 영역
    - CPU에게 전달할 코드 영역
- Stack 영역
    - 일반 메서드를 호출했을때 메서드에게 할당되는 영역
- Heap영역(Young Generation, Old Generation - Garbage Collection)
    - 객체가 사용하는 메모리 영역
    - 가바지 컬렉터는 이영역을 Young 영역보다 적게 참조
- Method 영역
    - 클래스의 메서드가 사용하는 영역
    - 상수 의 영역이라고도함
    - Class 영역이라고도 함

### 개발 환경

- JDK
    - SE 버전을 설치
    - 버전 번호는 현재는 8,11,17 버전을 많이 사용
- JAVA 8
    - 람다 와 스트림이 적용 , 전자 정부 프레임워크가 이버전 기반
- JAVA 11
    - Spring 이 사용하는 버전, 최신 Eclipse도 이버전부터 사용가능
- JAVA17
    - 최신버전

### IDE

- Eclipse
    - 플러그인 형태로 별도의 라이브럴를 가진 형태로 제공되기도 함
        - 전자 전부 프레임워크 , Spring Tool Suite, 애니 프레임워크
- Inteli j

### 작성 및 실행

- 과정
    - pc에서 실행되는 Application 만들 때는 static void main 메서드 (entry point) 를 가진 클래스가 있어야함
    - compile수행 (javac 명령)
- build
    - 운영체제나 하드웨어가 인식할 수 있는 코드를 만들어주는 과정
    - 실패하면 구조적인 문제
- run(java 명렁)
    - 메모리 할당을 한 후 실행
    - 오류가 발생하면 메모리 오류 나 예외가 발생

### 작성 시 유의 사항

- 대소문자 구별
- 한 번에 실행되어야 하는 문장의 끝은 세미콜론 (;)
    - 블럭을 만드는 명령에는 ; 을 마지막에 하지 않아도 된다
- 블럭의 생성은 {}

### 명명 규칙

- 데이터 와 메서드 그리고 클래스에 붙이는 이름을 만드는 규칙!!
- 예약어
    - 예약어는 이름으로 사용할 수가 없다
    - 동일한 영역에 이름을 중복해서 만들순없다.
    - package -> class -> 변수 와 메서드의 원형

### 구성 요소

- Keyword : java가 정해준 기능
- variable
    - 데이터에 이름을 붙이고 데이터를 변경할 수 있도록 해놓은것
    - 소문자로 시작
- constant
    - 데이터에 이름을 붙였는데 변경하지 못하도록 해놓은것
    - 대문자로 시작
- literal
    - 사용자가 직접입력하는 데이터

- 연산자 (Operator)

- 제어문 (Control Statement)

- Array
    - 동일한 모양을 갖는 데이터의 연속적인 모임

- Class 와 instance, Interface
    - 데이터 와 Method(기능)을 같이 소유하고 있는 것

- Comment
    - 주석
- Annotaion
    - @로 시작하는 명령어로 자주 사용하거나 복잡한 구문을 하나의 이름으로 대신 사용할 수 있도록 만든 것이다.
    - java에서는 class로 취급