# Class

### Class 와 instance

#### class

> 자바에서 클래스는 정적이다

- 동일한 목적을 가진 속성(변수-데이터) 과 기능(method)의 집합
- 사용자 정의 자료형
- 유사한 인스턴스들의 설계도
- 자바에서는 클래스는 프로그램을 실행할때 정적 영역에 메모리를 할당해서 저장하고 정적인 형태로 존재한다.

#### Object

- 프로그램에 존재하는 모든것
- Class를 기반으로 만들어진 Obect를 Instance
- new 라는 연산자로 constructor를 호출해서 생성
- 동적
    - 필요한 내용을 수정할 수 있고 삭제할 수 있다

### Class 구성 요소

- 속성
    - field
    - state
    - attribute
- static 속성
    - class 가 소유 (공유)
- instance 속성
    - instance가 서유

#### 메서드

> 행위(bahavior)

- static
    - 클래스가 호출
- instance method
    - 인스턴스가 호출

#### 생성자

> constructor

- 인스턴스를 만들 때 호출하는 특별한 용도의 메서드

### 초기화블럭

- static 초기화
- insatnce 초기화

### Nested Class

> 클래스 안에 만들어진 클래스

#### 클래스 생성

```
[public 이나 생략 - package] [클래스 특성] class 클래스 이름 [extends 클래스이름] [ implements 인터페이스 이름 나열 ] {

```

- public 부분은 접근 제한자라고 하는데 클래스 안에 만들어지는 클래스는 <b>private이나 protected</b>도 있음
- 클래스 특성으로는 abstract (추상 - 상속을 통해서만 사용 나 )final(상속을 할 수 없는 클래) 가능
- extends 는 상속을 받고자 하는 경우에 사용하는 것으로 생략하
- 클래스이름은 대문자로 시작하는 것이관례
- 허너우ㅏ 퍼알 언애 존재하는 클래스 이름으로 만들어져야함
- 하나의 파일에 여러개의 클래스를 만들고 컴파일을 하게되면 클래스 파일은 여러개가 된다
    - 이렇게 만들어진 클래스 파일을 자바에서느 byte Code 라고한함
- 이 byte code를 만들어진 JRE 가 보고 운영체제가 이해할 수있든 코드로 번역해서실행

#### 인스턴스 생성

> new 라는 연산자를 이용해서 생성자를 호출하면 Heap 영역에 메모리할당을 하고 그 참조를 리턴

- 생성자 이외의 메서드를 이용해서 만들어진 것은 디자인 패턴이 적용되서 내부적으로 생성자를 호출해서 리턴하거나 메서드 내부에서 직접 생성해서 리턴하는 경우지 생성자를 호출하지 안혹는
  인스턴스를 생성할수 없다
- new 를 이용해서 생성자를 호출하면 참조를 리턴하는데 이 참조를 저장하지 않으면 이 인스턴스는 다시 사용할 수 없다

- 클래스이름 참조형 변수 = new 생성자(매개변수)
    - 인스턴스를 생성하고 재사용하기 위해서 인스턴스의 참조를 변수에 기억시키기

- 인스턴스 소멸
    - 인스턴스를 참조하는 변수가 없으면 인스턴스는 메모리 정리 대상이 된다
- 참조형 변수 = null
    - 정리 대상이 될 수는 있는데 다른 참조형 변수가 가리키고 있으면 정리 대상이 안되고 정리 대상이 되더라도 바로 소멸되지 않고 Garbage Collection이 나중에 메모리 정리를한다

### 속성이나 메서드 호출

> 클래스이름이나 메서드이름.속성이름 또는 메서드 이름 (매개변수 나열)

- 클래스 내부의 메서드안에서 자신의 속성이나 메서드를 호출할 때는 클래스 이름이나 메서드 이름을 생략할 수 있다 .

### 클래스 와 인스턴스 만들기

- 클래스를 만들어서 사용할 때는 사용할 클래스들을 별도의 파일에 만들고 main메서드를 소유한 클래스에서 이클래스들을 호출해서 사용

### property(속성)

> 속성

- class 나 instance 에 데이터를 저장하기 위해서 생성하는 변수
- 변수의 종류
    - Local Variable (지역변수)
        - 함수 나 메서드 안에 만들어져서 자신의 영역 내에서만 사용할 수있는 변수
    - Member Variable (instance Variable)
        - 클래스 안 메서드 외부에 static이 없이 선언되는 변수
    - Instance가 각자 소유하는 변수
        - 속성이라고부름
    - static variable
        - 클래스 안 메서드 외부에 static이라는 키워드 와 함께 선언되는 변수

### 속성 선언

> [접근 제한자] + [transient] +[volatile] +[final]+ 자료형

- package
    - 생략하는 것인데 자신의 package에서는 public 으로 동작하고 다른 package에서는 private으로 동작
- public
    - 클래스 내부에서 사용 가능하고 외부에서 (클래스 이름이나 인스턴스 이름으로 접근 가능)도 사용 가능하도록 생성
- protected
    - 자신의 클래스 내부와 상속받은 클래스 내부의 메서드에서 사용가능 + 자신의 패키지 내에서는 public
- private
    - 자신의 클래스 내부의 메서드에서만 사용이 가능

```
public > protected>package>private
```

- transient
    - 직렬화 대상에서 제외할 때 사용
- volatile
    - 이 속성을 사용할 대 복제하지 않고 직접 사용하도록 할 때 사용
- static
    - static 속성을 만들 때 사용
- final
    - 일기 전용을 만들 대 사용
- 초기 값을 설정하는 것응ㄴ 생성자나 static 초기화를 이용하는 것을 권장하고 직접 초기값을 설정하는 것은 비추천
- 속성은 만들어 질 때 자동 초기화를 수행한다

### static 속성

> 속성을 만들때 static을 추가하면 만들어지느 속성

- 클래스가 소유하는 속성
- 외부에서 접근할 수 있도록 만ㄴ들면 클래스 이름으로 접근가능
- 생성하는 목적
    - 모든 인스턴스가 공유하는 변수를 만들고자 하는 경우
    - 전역 변수를 만들고자 할 대 사용
        - 자바는 클래스 외부에 변수를 선언할 수없기 때문에 전역변수를 만들 수 없음
        - 객체 지향 프로그램잉에서는 전역 변수 사용을 금기지 함

### static 초기화

- 클래스 안에 static {} 를 하고 실행되는 코드를 작성하면 클래스가 메모리에 로드가 될때 코드를 수행해야한다
- static 속성 사용 과 지역 변수를 생성해서 사용하는 것도 가능함
- 인스턴스 속성을 사용하는 것은 안됨
- static 속성에 초기값을 부여하고자 할 대 주로 이요함

### final 속성

- 변수 앞에 final을 붙이면 일기 전용이 된다
- 지역 변수를 만들 때 final을 붙일 거라면 반드시 생성하자 마자 초기화를 해야한다
- 클래스 안에 만들 대는 대부분의 경우 static 과 함께 사용함
- 클래스 안에 만들 때는 생성하자 마자 초기화를 해도 되고 생성자에서 초기화해도 된다
- 다른 곳에서는 초기화가 되지 않는다
- 클래스 안에 만들어지는 final은 대부분은 클래스 안의 메서드에서 사용하는 옵션인 경우가 많다.
- 다른 데이터 안의 구별 문제 때문에 이름은 대문자만 사용하는 것이 관례이다.
    - 이러한 명명법을 Snake표기법이라한다

### 객체지향에서의 속성에 대한 접근 지정자

> 객체 지향에서는 속성을 직접 접근해서 핸들링하는 것을 비추천하기 때문에 속성의 접근지정자는 대부분의 경우 private이나 protected인 경우가 많다.

- static final 의 경우는 옵션으로 사용해야 하기 때문에 public 경우가 많다
- Java API에서 제공하는 Document 에는 일반 속성은 보이지 않고 static final 속성만 외부로 노출된다
- Java API 에서는 propety 라는 용어 대신에 field 라는 용어를 사용한다

# Method

- 전달받은 인수를 처리해서 결과를 돌려주는 작은 프로그램
- function 이라고 하기도 하는데 function은 전역 공간에 만들어져서 어디서나 호출할 수 있는 것이고 method는 클래스 안에 만들어져서 클래스나 인스턴스를 통해서만 사용할 수 있는것으로 분류

### method 선언

# abstract (추상)

- 내용이 없는 메서드
- final
    - overriding 할수 없는 메서드
- static
- synchronized
    - 스레드의 임계영역 처리를 위한 메서드
- native
    - native 기능 (대부분의 경우는 운영체제의 기능을 호출)을 위한 메서드
    - Embedded 나 System Programming에 이용

### return type - 생략 안됨

- return 하는 데이터의 자료형을 기재하는데 return 하는 데이터가 없다면 void라고 작성해야함
- 매개변수는 없으면 생략 가능

### method 호출

- 내부에서는 이름 과 매개변수 만으로 호출이 가능
- 외부에서는 클래스 이름 이나 인스턴스 이름을 기재해야함

```
.메서드 (매개변수 or x) 로 호출해야함  
```

### method 원형

- 리턴타입 메서드이름 (매개변수 자료형 나열)

### UML 에서의 메서드 표현

- 이름(매개변수 자료형 나열) :리턴 타입

### method 사용이유

> 반복적으로 사용하는 코드를 하나의 이름으로 묶어서 이름을 호출하는 것만으로 코드를 실행하기 위해서

- 일반적인 프로그래밍 언어에서의 함수는 한정된 메모리를 사용하기 때문에 구조화가 필요

### static 메서드 와 instance 메서드의 차이

- static 메서드는 외부에서 클래스가 호출해야함
- instance 메서드는 인스턴스가 호출해야한다
- 메서드는 ㄴ메서드 영역 또는 static영역이라고 부르는 영역에 하나만 만들어지고 이를 호출해서 사용하는 개념이므로
  코드는 기본적으로 공유가 된다

- static메서드에서는 instance 속성이 안됨
- static 메서드는 인스턴스 생성없이 메서드를 호출할수있다.
- 클래스 안에 기능 구현이 끝나고 난 후 instance속성을 사용하지 않는 메서드는 static메서드로 수정하는 것이 좋다 .(메모리 효율)

```java
  public class java_12_15 {
    public static void main(String[] args) {

        //static 메서드는 클래스 이름으로 호출가능
        MethodClass.method1();
        //인스턴스 메서드는 이므로 오류가 남 
//      MethodClass.method2();

        MethodClass method = new MethodClass();
        //인스턴스하고 인스턴스메서드를부르면 사용이가능해짐
        method.method2();

    }
}
```

```java
  public class java_12_15 {
    public static void main(String[] args) {
        private static int num;
        private String name;
        public static void method1 () {

            num = 1;
//static 메서드에서는 instance 속성이안됨
//이유가 this가 없기때문에
//        name ="adam";
            System.out.println("MethodClass method1");
        }

    }
}
```

- static method에는 instance 변수가 접근이 안된다
    - static 에는 this가 없기때문이다

### return

> return 메서드는 수행을 종료하고 호출한 곳으로 돌아가는 명렁어

- return 뒤에 있는 문장은 수행되지 않는다
- 메서드를 호출하면 메서드를 호출하기 위한 참조를 기억하기
  때문에 메서드의 호출 결과로 하나의 데이터를 받을수있다.
- 이렇게 데이터를 받고자 할 때 return 다음에 돌려줄 데이터를 기재하면 된다
- 자바에서는 이렇게 데이터를 돌려줄 때는 메서드 이름 앞에 돌려줄 데이터의 자료형을 기재해야한다

### parameter ,argument

> 메서드를 호출할 때 호출하는 곳에서 넘겨주는 데이터

- 없을 수 있고 개수 제한은 없다.
- 여러 개가 되면 순서를 기억해야하기때문에 매개변수가 여러 개인 경우는
  그 여러 개를 포함하는 클래스를 만들어서 사용하는 것을 권장함
    - 클래스 대신에 Map을 사용하는 경우가 있는데 비추천함
- 자바에서는 메서드를 호출할 때 메서드에 만들어진 매개변수를 생략할 수 없고,
  더 많이 줄 수 도 없다.
- 항상 자료형 과 개수가 맞아야한다
- 매개변수는 메서드 내의 지역변수가 된다
- 매서드 외부에서는 사용할 수 없다 .

### 클래스의 메서드 주의사항

> static 존재 여부를 확인하고 매개변수의 개수 와 자료형을 확인하고 리턴 타입을 확인해야한다

- 호출을 할 때는 리턴 타입은 의미가 없지만 그 결과를 사용하기 위해서는 return type을 확인해야한다.

### Method Overloading (중복정의)

> 하나의 클래스 또는 상속 관계에 있는 클래스에 이름은 같고 매개변수의 개수나 자료형이 다른 메서드가 존재하는 경우

- 메서드를 호출할 때 매개변수의 자료형 과 개수를 보고 호출할 메서드를 결정
- overloading 을 판단하는 근거는 메서드이름 과 매개변수의 자료형의 순서

### 매개변수 전달

- 기본형은 복제가 되서 전달
- 기본형이 아닌 자료형은 그대로 전달
- 내부 요소를 수정하게 되면 넘겨준 데이터가 수정된다
- <b>예전에는 기본형을 전달하는 것을 Call By Value라고 했고 참조형을 전달하는 것을 Call By Reference</b>
- 특별한 경우가 아니면 메서드는 return을 하는데 return 을 하지 않는 메서드가 참조형을 매개변수로 받으면 대부분 매개변수를 수정한다.

```java
package java_12_15;

public class MethodClass {

    public int addWithInteger(int firtst, int second) {
        return (firtst + second);
    }

    public void display() {
    }

    public void display(int a) {
    }

    //내부에서 매개변수의 값을 수정해도 넘겨준 데이터는 변경되지않는다
    public static void callByValue(int n) {
        n = n + 1;
        System.out.println("n callByValue" + n);
    }

    //매개변수가 참조형인경우
    public static void callByReference(int[] ar) {
        ar[0] = ar[0] + 1;
        System.out.println("n callByReference" + ar[0]);
    }
    //메인에서 callByValue 를 매개변수로넘기고 main에서 다시찍어보면 값은 안바뀐다
    //callByReference 는 변경이된다 
};

```

### this

> 인스턴스 메서드의 숨겨진 첫번째 매개변수

- static 메서드에는 this 가 없음
- 속성은 인스턴스가 소유하지만 메서드는 공유한다.

```
class T{
  public int num;
  public void method(){
  System.out.println(num)
  }
}


```

- 위 구문에 대한 정의
- obj(num,T에 대한 참조 소유)
- 메서드를 호출하게되면 클래스에 가서 메서드를 찾는다.
- 클래스는 인스턴스속성을 가지고있지않는다
- 클래스를 생성하면 Java는 코드를 숫정

### this를 사용하는 경우

- 인스턴스 속성과 메서드 내부에서 만든 지역 변수이름이 같은 경우
  변수 이름만 사용하면 scope의 법칙때문에 지역변수가 호출됨
- 이 때 지역 변수를 참조하지않고 인스턴스 속성을 참조하고자 하면 this.속성이름을 사용한다.
- this.이 붙으면 메서드 내의 지역변수는 찾지 않는다

### scope

> 동일한 이름의 엄청많을때 가까이에서 만든것을 사용하는거

# 전급자 메서드

- 객체 지향 언어에서 는 속성에 바로 접근하는 것을 권장하지 않습니다
- 속성에 접근해서 읽어오고 수정하는 메서드를 이용해서 사용하도록 권장한다
    - 이러한 메서드를 접근자 메서드라한다
- getter메서드
    - 속성의 값을 리턴하는 멧드
    - 메서드의 이름은 get속성이름을 ㅗ 하느네 속성이름의 첫글자만 대문자로표현해야한다
    - 메서드의 내용은 속성의 값을 리턴하는 것만 한다
    - 메서드이 변경하는 경우가 있는데 이경우는 속성이 자료형(boolean)인 경ㅇ우 get대신에 (id)를 사용해서
      get할때 생성을 해서 리턴하는 경우도 있는 데 이 를 지연생성이라한다
    - 대부분의 경우는 생성자에게 미리 생성해두고 사용을 하지만 메모리 부담이 커지거나 생성 속도가 너무다면 처음 get이나 set을 생성하기도 한다

- setter 메서드
    - 속성의 값을 설정하는 메서드
    - 메서드 이름은 set속성이름으로 하는데 속성 이름의 첫글자만 대문자료 표현한다
    - 매개변수로 데이터1개를 받아서 이매개변수의 값을 속성에 대입하는 여갛ㄹ을 수행한다
        - 리턴 타입은 void 로 하는 것이 일밙거다
        - 속성의 값을 대입하기전에 유효성검사를

- getter setter ㄹ르 직접 만들지아 ㄴㅎ고 대부부부의 경우 IDE 가 대부분 해줌

- 여러 개의 값 만을 하나로 묶기 위해서 사용하는 클래스르 VO(Value Object)라고 하고,
  이 클래스 영역을 벗어나서 사용되면 DTO(Data Transefer, Object) 라고 하고 데이터베이스 연동이되면 Entity라고한다

- setter는 없고 getter만 존재하는 경우도 있는데 이경우는 읽기 전용을 만들거나 derived속성

### varargs

- 매개변수의 개수를 가변으로 설정하는 것
- 매개변수를 설정할 때 .. 이름 을 사용하게 되면 메서드를 호추랗ㄹ 때 몇개의 데이터를 대입하더라도 이름의 배열로묶어서처리
- 매개변수 중에서 맨 마지막에 1번만 사용가능
- 중간에 포함되거나 여러 번 사용하게 되면 어디까지를 배열에 대입해야 할 지 알 수 없기때문
- System.out.printf 메서드가 대표적으로 varargs 를 사용할 것입니다.

```
        methodClass
        ...
        ...
        
        
          public static void display(String... args) {
        for (String temp : args) {
            System.out.println(temp);
        }
    }



  Main.class
  ...
  ...
        MethodClass.display("123","!23","!#$31431413");
        
        결과 : 123,!23,!#$31431413

```

## 재귀호출

- 메서드가 자기 자신을 다시 호출해서 리턴하는 것
- 사용하는 이유
    - 코드를 이해하기 쉽게 작성하기 위해서
- 단점
    - 메서드가 다시 자기 자신을 호출해야 하기 때문에 메모리 사용량이 늘어나고 수행 속도가 오래걸릴수 있음
- 재귀 호출을 이용할 수 있는 대표적인 알고리즘이 합계 , 팩토리얼, 피보나치 수열,하노이의 탑 등이 있다
- 재귀를 이용해서 메서드를 마들 대는 종료지점을 만들어 주어야한다는것이다

```java
public class main() {
//    피보나치 수열
//1,1,2,3,5,8,13,21,34 ...
//    처음 두개는 1
//    이고 세번째
//    부터는 앞 2
//    개의 합

    public static int noRecursionFibonacci(int n) {
        int n_1 = 1; //현재 구하고자 하는 피보나치 값의 바로 앞
        int n_2 = 1; //현재 구하고자 하는 피보나치 값의 두번째 앞
        //기본값 설정
        int fibo = 1;
        //규칙이 3번째 부터 적용
        int i = 3;
        while (i <= n) {
            fibo = n_1 + n_2;

            //n_1 과 n_2를 이동
            n_2 = n_1;
            n_1 = fibo;

            i = i + 1;
        }
        return fibo;
    }

    //재귀를 이용한 피보나치 수열
    public static int recursionFibonacci(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        return recursionFibonacci(n - 1) +
                recursionFibonacci(n - 2);
    }

}
```

