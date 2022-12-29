# Data Type

### Data의 분류

> 변경 가능 여부에 따른 분류

### Immutable (read only)

> 생성하면 수정할 수 없는 데이터

- Literal
    - 프로그래밍 언어가 데이터를 표현하기 위해서 정해준 방식, 사용자가 직접입력하는 데이터
- constant
    - 개발자가 의도적으로 읽기 전용으로 만든 데이터
    - ex) final DataType 변수이름 등등 ..

### Mutable Data

- 생성 한 후 상황에 따른 데이터의 참조를 저장할 수 있는 데이터

### 저장되는 데이터의 종류에 따른 분류

- Value Type
    - 실제 데이터를 저장하는 타입
- Reference Type
    - 데이터의 참조를(address 나 hash code 라고 하기도 한다) 저장하는 타입

### 저장되는 데이터의 개수에 따른 분류

- Scala Type
    - 하나의 데이터만 저장하는 자료형
    - 자바에서는 Primitive Type이라고함
    - C,Java 는 Static Type 언어
        - 소스 코드 작성단계에서 타입 불일치 와 같은 오류를 검출할 수 있다
- Collection(Vector) Type
    - 0 개이상의 데이터를 저장하는 자료형
    - Primitive Type을 제외하면 전부 Vector Type

### Data Type 기재 방법에 따른 분류

- Static Type
    - 변수 나 상수를 선언할 때 자료형을 결정해야 하는 Type
    - Template Programming을 이용해서 인스턴스를 생성할 때 Data Type을 결정하는 지원
        - Java에서는 이러한기능을 <b>Generic</b>이라고함
- Dynamic Type
    - 변수 나 상수를 선언할 때 자료형을 기재하지 않고 데이터의 참조를 대입할 때 결정하는 Type
    - Javascript 와 Python이 Dynamic Type언어

### Optional

> NULL이 저장 가능한 (Optional)자료형 과 NUll이 저장되지 않는 자료형으로 나누기도한다.

### Java의 자료형

> 자료형의 분류

- Primitive Type(기본형)
    - 하나의 데이터를 저장하기 위한 자료형
- NonPrimitive Type
    - 0개 이상의 데이터를 대표하는 참조를 저장하기 위한 자료형
- 초기값을 설정하지 않으면 초기값 설정만 가능하고 다른 용도로 사용하면 에러가 발생

### 명명 규칙

- Camel Case
    - 클래스나 인터페이스 이름은 <b>대문자</b>
    - 속성 과 메서드 이름은 소문자로 시작
    - 두 개 단어 이상의 조합일 때 두번째 단어의 시작은 대문자
- Snake Case
    - 상수의 이름은 모두 대문자로 표현
    - 첫글자는 영문이나 한글 그리고 $ 와 _가능
    - 한글은 가능하지만 인코딩 문제 때문에 잘 사용하지 않음

### 변수나 상수의 유효범위의 따른 분류

> 중요하다생각함 -정훈-

- Local Variable
    - 메서드 안에서 만들어진 변수로 데이터 저장 공간이 <b>stack</b>이다.
    - 메서드의 작업이 끝나면 메모리 와 함께 소멸된다
    - 자신이 만들어진 블럭 안에서만 사용이 가능하다
- Member(Instance) Variable
    - <b>클래스 안 그리고 메서드 바깥에 static 이라는 keyword 없이 만들어진 변수</b>
    - 데이터의 저장 공간은 Heap에 생성되고 인스턴스 통해서만 접근이 가능하다.
    - 인스턴스가 소멸되면 같이 소멸된다
    - 인스턴스는 자신을 참조하는 데이터가 없으면 메모리 정리 대상이 된다.
- Static Variable
    - 클래스 안 그리고 메서드 바깥에 static이라는 keyword와 함께 만들어진 변수
    - 데이터의 저장공간은 Class(Static)영역이 됨
    - 클래스를 통해서 접근이 가능하고 자바에서는 인스턴스를 통해서도 접근이 가능
    - 클래스는 한 번 로드 되면 메모리에서 소멸되지 않기 때문에 한 번 만들어지면 애플리케이션이 종료될때 까지 소멸되지 않는다.

### 메모리 구조 영역

> 참고 https://tape22.tistory.com/28

- stack 영역
    - 일시적
    - 메서드
    - 함수
- Heap 영역
    - 인스턴스
    - 삭제가능
- static 영역
    - 소멸이 안됨

### 자바의 기본 자료형

> Primitive Type(기본형)

- boolean
    - true or false 만 저장 가능 다른 자료형과 호환이안됨
- byte
    - -128 ~ 127 까지 저장가능
- short
    - 2byte , -32768 ~ 32767 까지 저장가능
- char
    - 2byte , 0 ~ 65535 까지 저장가능, 저장을 할 때는 정수로 저장하고 출력을 할 때 문자로 출력
- int
    - 4byte, 21억정도의 음수 와 양수 저장 가능
    - 정수 리터럴의 기준형
        - 그냥 숫자만 사용하면 10진 정수로 판닫하고 int 가 됨
        - 정수 뒤에 L을 붙이면 long 형 리터럴이 된다 .
        - byte ,short,char,int,long은 자신의 표현 범위보다 큰 데이터는 저장할 수 없다.
        - long형 리터럴은 long에만 저장가능
        - 천단위 구분기호는 _
            - 123_456_789L
        - 정수앞에 0을 붙이면 8진수
            - 0으로 시작하는 숫자를 사용할 때는 주의
        - 정수앞에 0x를 붙이면 16진수가 된다.
- long
    - 8byte
- float
    - 4byte
    - 10의 38승 정도를 저장할 수 있고 정밀도는 소수 7자리
- double
    - 8byte
    - 10의 308승정도를 저장할 수 있고 정밀도는 소수 15자리 정도, 실수 리터럴의 기준형

### char

- 문자 리터럴은 ' ' 안에 하나의 문자를 기재하면 된다
- 자바는 유니코드를 사용하기 때문에 char가 2byte 이다.
- 문자에 해당하는 정수 코드를 설정해도 된다
    - 문자 0은 48
    - 문자 A는 65
    - 문자 a 는 97
- 문자는 모든 경우에 정수로 취급하고 출력할 때만 문자로 취급
    - 대입할 땐 ₩ 다음에 8진수 3자리로 대입가능하고 ₩u 다음에 16진수로 4자리로 대입 가능하다

### 제어 문자

- ₩다음에 하나의 문자를 추가해서 특별한 기능을 갖도록 한 문자
- ₩n,₩t,₩₩,₩',₩",
- ₩0
    - null

### overflow 와 underflow

- Overflow는 표현범위를 위쪽으로 넘어선 경우로 앞쪽의 데이터를 잘라버리기 때문에 넘어가면 가장 작은 숫자부터 다시 시작하게되고 underflow는 반대의
  경우로 가장 큰 숫자부터 다시 시작된다

### String

- 0개 이상의 문자열을 저장할 때 사용할 수 있는 클래스
- 기본형은 아니지만 기본형 처럼 사용
- 인스턴스를 생성할 때 리터럴을 이용할 수 있음
- 문자열 리터럴은 "" 안에 기재

### 서식을 이용한 데이터 출력

> System.out.printf이용

- 서식을 설정할 때는 % 와 포맷문자를 조합해서 데이터를 서식에 맞춰서 출력
- 두번째 매개변수 부터는 앞의 서식 문자와 매핑되는 데이터를 나열함

### 서식문자

- %d
    - 10 진수
- %x
    - 16진수
- %o
    - 8진수
- %i
    - 10진수
- %f
    - 실수
- %e
    - 지수 형태로 출력
- %c
    - 하나의 문자
- %b
    - boolean
- %s
    - 문자열

### 컴퓨터 비트 부호

- int x =19
- 2^16

- unsigned
    - 부호가 없는 양수만 존재
- signed
    - 부호가 있는,양수와 음수 존재
    - 음수의 경우는 양수의 2의 보수
- 2의 보수
    - 1의 보수 +1

```java
public class test() {
    public static void main(String[] args) {
        int n1 = 20;
        int n2 = -20;
        System.out.println("n1:" + Integer.toBinaryString(n1));
        System.out.println("n1:" + Integer.toBinaryString(n2));
        System.out.println("n1:" + Integer.toBinaryString(~n2));

    }
}

//확인해보자 ! 
/*
  n1:10100
n1:11111111111111111111111111101100
 */
```

### 증감 연산자

- ++ 와 --
- 정수 변수 데이터에만사용
- 벼수의 데이터를 1증가 시키거나 감소시키는 연산자
- 앞에 사용되면 변수의 값을 먼저 증감
- 뒤에 사용되면 변수의 값을 나중에 증감

### 산술연산자

- %
    - 정수 데이터를 가지고 나머지를 구해주는 연산자
- /, +,- ,*
    - 숫자 데이터를 가지고 사칙 연산을 수행
- java에서는 산술 연산의 최소 단위가 int
- byte,short,char 는 int로 변환되서 산술 연산을 수행하고 결과를 int로 리턴
- 2개의 서로 다른 숫자 자료형끼리 연산을 하면 더 큰 자료형으로 변환해서 연산을 수행하고 결과도 큰 자료형으로 리턴
- byte<short<int <long<float<double<char
- 데이터 입력 도중 한 글자 오류가 나는걸 transcription error라고 한다

### transcription error 방지법

- 데이터 몇개를 추가해서 특정한 숫자로 나우었을때 나머지가 0이 되도록 하는 방법을 사용
- 주민등록번호 나 신용카드 번호 또는 계좌번호 등이 이 원리를 이용
- 숫자 3자리로 구분을 하고자 하는 경우 숫자 1개를 추가해서 4자리로 구성하는데 3자리는 직접 입력 하도록하고 1자리는 연산을해서 설정

### short 규칙

```java
public class main() {
    public static void main(String[] args) {


        short s1 = 20;
        short s2 = 30;
        //short 사이의 덧셈이고 결과도 50이라서 short 저장가능하지만 이문장은에러이다
//자바의 산술연산의 최소 단위는 int
        //자바는 s1과 s2로 int로 변환해서 연산을 수행하므로 
        //결과는 int가 되서 short 에 저장할 수 없다.
        short result = s1 + s2;
        //에러 문장이뜸
        //이 경우에는 결과를 int에 저장하던가 강제 형 변환을통해서 short에 저장해야한다 

    }
}
```

### 실수의 산술 연산 결과

```java
public class main() {
    public static void main(String[] args) {
        double d = 0.1;
        double tot = 0.0;

        for (int i = 0; i < 100; i++) {
            tot = tot + d;
        }
        //0.1을 100번 더했는데 10 이아니고 9.9999999999998
        System.out.println("tot" + tot);

    }
}
```

### Type Casting (자료형 변환)

> 데이터의 자료형을 변경하는 것

- 종류
    - 자동형
        - 묵시적으로 이루어지는 형 변환
            - 산술 연산에 int 보다 작은 숫자 자료형을 사용한 경우 int 로 자동 형 변환
            - 서로 다른 자료형 끼리 산술 연산을 수행하는 경우 더 큰 자료형으로 자동 형 변환해서 연산을 수행
    - 강제 형 변환
        - 명시적으로 형 변환을 수행하는것
        - 숫자 데이터끼리 강제 형 변환이 가능하고 인스턴스끼리는 상속 관계인 경우만 가능
        - 숫자 데이터와 문자 데이터끼리는 강제 형 변환은 안되지만 메서드가 제공됨
        - 문자열을 숫자 데이터로 변환할 때는 Wrapper클래스를 이용하면 되고 숫자 데이터를
          문자 데이터로 변경할때는
          String의 메서드를 이용해도되고 빈 문자열 과 + 연산을 수행해도 됨
        - 강제 형 변환을 하는 이유 ?
            - 원하는 결과를 만들기 위해서 실수를 정수로 강제 형 변환할 때는 소수 부분이 사라짐
            - 큰 자료형에서 작은 자료형 형 변환할 때는 데이터의 손실이 있을 수 있다.
- <b>산술 연산은 int 로 변환되서 수행되므로 결과가 최소 int</b>

```
(자료형 이름) 데이터 - 데이터의 자료형이 ()안의 자료형으로 변환됨
```

```java

public class TypeCasting {
    public static void main(String[] args) {
        double d = 37.4;
        //실수를 정수에 직접 대입하면 에러가 발생함
        // 실수가 정수보다 크기 때문이다
        int n = (int) d;

        //실수를 정수로 강제 형 변환하면 소수가 버려짐
        System.out.println("n" + n);

        //37


        short s1 = 200;
        short s2 = 300;
        //산술 연산은 int 로 변환되서 수행되므로 결과가 최소 int 

        short result = s1 + s2; //에러 

        short result = (short) (s1 + s2); //정상적으로 500이나옴

    }
}
```

### 위 상황에서 short 주의해야할점

```
short는 32767까지인데 두 개의 변수의 합이 32767 이넘었을경우 
음수값이 나오는걸 주의해야함
```

### 정수 2개의 값을 가지고 평균을 실수로 구하고자할ㄸ ㅐ

- 데이터 중 1개를 실수로 만들면 연산의 결과도 실수
- 정수를 가지고 산술 연산을 하면 결과는 정수

```java
class TypeCasting {
    public static void main(String[] args) {


        //정수 2개의 평균을 실수로 구하고자 할 때
        int scroe1 = 91;
        int scroe2 = 90;
        //정수를 가지고 산술 연산을 하면 결과는 정수
        int avg = (scroe1 + scroe2) / 2;
        int avg = ((double) scroe1 + scroe2) / 2;

        System.out.println(avg + "avg");
    }
}
```

- 언어마다 숫자를 다루는 방식이 다르므로 숫자연산을 수행하기 전에 그 언어의 숫자 데이터를 다루는 부분을 알아두어야한다.

### shift 연산자

- 정수 데이터에만 사용 가능
- 2진수를 가지고 비트 단위로 밀어내는 연산자
    - '>>'
        - 뒤에서 밀어내는데 맨 앞에 추가되는 비트는 첫번째 비트를 계속해서 추가함
        - 부호가 변경되지않음
    - <<
        - 첫번째 비트를 제외하고 나머지 비트를 왼쪽에서 제거하고 뒤에 0을 삽입하는 구조
        - 부호가 변경되지않음
    - '>>>'
        - 맨 앞에 0을 추가하면서 밀어내는 연산자
- 32번 이상 밀어내지도록 하면 32 로 나눈 나머지만큼 밀어낸다
- 32번 이상 shift하지 않음
- shift는 특정비트가 1인지 확인하고자 할 때 많이 사용함
    - 컴퓨터는 내부적으로 곱하기와 나누기를 할때사용

### 크기 비교 연산자(RelationOperator)

- '>','<=','<','>='
- 숫자 데이터에만 사용할 수 있고 결과는 boolean

### 동일성 여부

- ==
    - 해시 코드를 비교해서 일치하면 true
- !=
    - 해시 코드를 비교해서 일치하지 않으면 false
- 저장하고 있는 데이터가 일치하는지 확인하고자 하는 경우는 equals메서드를 정의해서 사용

```java
import java.util.Scanner;

public class main() {
    public static void main(String[] args) {
        String s1 = "JAVA";
        String s2 = "JAVA";
        //literal 을 이용해서 생성 
        // 데이터가 같으므로 동일한 해시코드를 갖음 해시코드가 같아서 true

        Scanner sc = new Scanner(System.in);
        //문자열을 입력받아서 생성 -리터럴을 만든것이 아님 
        String s3 = sc.nextLine();
        System.out.println(System.identityHashCode(s3));
        System.out.println(s1 == s3); // 해시코드가 달라서 false

        //인스턴스의 경우는 equals로 내용을 비교 
        System.out.println(s1.equals(s3)); //true
    }
}
```

### instance of

> 어떤 객체가 클래스의 인스턴스인지 확인하는 연산자

- 클래스는 상위 클래스이면 된다
- 일반 프로그래밍에서는 잘 사용되지 않는다 Generic 적용되는 경우에 사용된다

### 논리연산자

- &&
    - && 는 앞의 결과가 false이면 뒤의 결과를 확인하지 않는다
    - boolean 데이터에만 적용이 가능함
    - 둘다 true일 때만 true가 나오는 연산자
- ||
    - boolean 데이터에만 적용이 가능하고 둘다 false일 때 만 false 가 나오는 연산자
    - ||는 앞의 결과가 true이면 뒤의 결과를 확인하지 않는다.
- &&가 우선순위가 높다 
- java는 boolean 데이터 이외는 boolean으로 간주하지 않는다
 