# Nested Class

> 자바는 클래스 안에 클래스를 생성하는 것이 가능

- 클래스 안에 인터페이스 생성 가능
- 인터페이스 안에 인터페이스 생성 가능
    - 자바는 소스 파일 단위로 컴파일 되는 것이 아니고 클래스 단위로 컴파일이 된다

## 종류

- Instance Inner Class
    - 클래스 안에 만들어진 클래스
        - static 멤버가 없어야함
- Static Inner Class
    - 클래스 안에 만들어진 클래스
        - static 멤버 가능
- Local inner class
    - 메서드 안에 만들어진 클래스
- Anonymous class
    - 이름 없는 클래스

### Instance Inner class

> 클래스 안에 만들어지는 클래스

```java
class Outer {
    class Inner {
        속성 ..
        메서드 ..

    }
}
```

- 컴파일이 되고 나면 outer.class 와 inner.class 2개의 클래스가 생성됨
- 외부에서 사용하고자 할 때는 클래스의 접근 지정자를 public으로 설정하고 Outer 클래스의 인스턴스를 만들고 인스턴스를 통해서 Inner클래스의 인스턴스를 생성해야한다.
- Inner Class 접근 지정자에 private 과 protected 사용 가능
- Inner Class 는 클래스 특성으로 static 사용 가능
- 일반 클래스는 접근 지정자 public 이나 package 만 가능하고 클래스 특성으로는 abstract 와 final 만 가능
- 자기 클래스와 상속받은 클래스 에서 protected

### 폴더 bin

> binary

### static Inner Class

> Inner Class 안에 static 멤버가 있는 경우 일반 클래스로 생성하면 에러가 발생

- static 멤버는 클래스 이름만으로 호출이 가능해야 하는데 Instance Inner Class 로 만들면 인스턴스를 만들고 호출해야하기때문이다
- 이런 경우 에러를 없애고자 하면 Inner class 에 static 을 추가해주면 된다

### Local Inner Class

- 내부 클래스가 메서드 안에서 생성되는 형태
- 이 클래스는 메서드 안에서만 사용이 된다
- 메서드는 stack 에 만들어지지만 클래스는 static 영역에 만들어지기 때문이다

### Anonymous Class

> 익명 객체, 클래스

- 상속을 받거나 인터페이스를 구현해야하는 경우에
- 인스터늣를 1개만 생성해서 사용해야 하는 경우 클래스를 별도로 만드는 것은 자원의 낭비가될수있다,
- 최근에는 인터페이스에 메서드가 1개인 경우 여기서 확장된 람다를 이용하는 경우가 많음

### 인터페이스

```java
package java_12_19;

public class Main {
    public static void main(String[] args) {
        //외부 클래스으 인스턴스를 생성해야함
//        InstanceInner instanceInner = new InstanceInner();

        //인스턴스 생성
        // 인터페이스 나 추상 클래스 등을 상속해서 클래스를 만들고
        //인스턴스를 만드는 경우
        //변수는 대부분 인터페이스 나 추상 클래스
        //생성자는 사용하고 하는 클래스의 생성자를 이용하는 경우가 많다.

        //상위 클래스 나 인터페이스로 만들어진 변수에
        //하위 클래스의 인스턴스  참조를 대입할 수 있다 .

        //이렇게 만들어진 변수는 상위 클래스 나 인터페이스에 존재하는 이름만 호출이가능하다
        //실제 호출되는 것은 생성자를 따라간다 .
        SampleAbleImpl instance = new SampleAbleImpl();
        instance.method();

        //내부 클래스의 인스턴스 생성


        // Anonymous Class 사용
        SampleAble annoymous = new SampleAble() {
            @Override
            public void method() {
                System.out.println("!23");
            }
        };
        System.out.println(annoymous);
        annoymous.method();

        //메서드를 1번만 호출할 거라면 변수를 생성하지 않고 생성
        new SampleAbleImpl() {
            @Override
            public void method() {
                System.out.println("씨발아");
            }
        }.method();
    }
}

```

> 참고 https://gyrfalcon.tistory.com/entry/JAVAJ-Nested-Class

### java 에서 클래스를 상속받거나 인터페이스를 구현하는 방법

- 하위 클래스를 만들어서 사용
    - 인스턴스를 2개 이상 생상하고자 하는 경우
    - Anonymous class를 이용
        - 인스턴스 1개만 생성해서 사용하는 경우
            - 안드로이드의 이벤트 처리에서 많이 사용함
            - lambda 문법으로 코드를 수정하기도함

### final

- final 변수
    - 읽기 전용의 변수 , 값을 수정할 수 없음
- final 메서드
    - overriding 할 수 없는 메서드
- final 클래스
    - 기능 확장을 못하게 하는 이유의 대부분은 시스템을 핸들링하기 때문이다

### 데이터 공유

- 전역변수 (Global Variable)
    - 모든 곳에서 사용가능 한 데이터
    - singleton 패턴으로 클래스를 디자인해서 속성이나 메서드를 만들어서 사용
        - 권장 , 안드로이드 나 ios 또는 Web Application들이 이 방법을 이용해서 entry point에 접근 가능함.
- design pattern
    - 클래스를 용도에 맞게설계하는 기법, 가장 많이 사용되고 알려진 패턴으로는 Gof의 디자인 패턴이 있습니다

### singleton

- 클래스의 인스턴스를 1개만 만들 수 있도록 하는패턴으로 Server Application에서 주로 이용
- 장점
    - static을 사용하여 별도의 메모리 영역을 얻으면서 한번의 new 연산자로 인스턴스를 사용하기 때문에 메모리 낭비를 방지할수 있다. 싱글톤패턴으로 만들어진 클래스의 인스턴스는 전역 인스턴스이기
      때문에 다른 클래스의 인스턴스들이 데이터를 공유할 수 있다
    - 생성자를 private 으로 생성 후
        - 자신의 type으로 만들어진 static 속성을 정의 하고
        - static 속성에 데이터를 만들어서 주입하고 리턴하는 static 메서드를 생성
- 대부분의 경우 서버는 하나의 인스턴스를 이용햇서 멀티 스레드로 클라이언트의 요청을 처리하기 때문에 서버에서는 하나의 클래스에 대한 인스턴스를 1개만듬

```java

package java_12_19;

public class Main {
    public static void main(String[] args) {

        //디자인패턴을 적용
        SingleTon singleTon1 = SingleTon.sharedinstance();
        SingleTon singleTon2 = SingleTon.sharedinstance();

        System.out.println(System.identityHashCode(singleTon1));
        System.out.println(System.identityHashCode(singleTon2));
//결과 같다 .
    }
}

//singleton.java


public class SingleTon {
//    private Singleton() { 
    //error
//    }

    //하나의 인스턴스 참조를 저장하기 위한 속성을 생성
    private static SingleTon singleton;

    // 인스턴스의 참조를 리턴하는 메서드
    public static SingleTon sharedinstance() {
        if (singleton == null) {
            singleton = new SingleTon();
        }
        return singleton;
    }
}
```

- 동일한 클래스로부터 만들어진 인스턴스 사이의 데이터 공유
    - static 속성을 사용

### 캘래스 안에서 다른 클래스의 인스턴스를 만드는 경우

- has a 관계
- 포함하고 있는 클래스의 인스턴스는 포함되는 클래스의 인스턴스 참조를 알기 때문에 참조를 이용해서 바로 접근이 가능하지만 포함된 클래스의 인스턴스에서는
  외부클래스의 속성에 바로 접근이 안됨 .
- 생성자 나 setter 메서드를 이용해서 포함하는 클래스의 인스턴스 참조를 넘겨주어야한다.
- 이러한 방식을 생성자를 이용한 주입 또는 setter 메서드를 이용한 주입이라고 한다.


- 포함된 클래스

```java
package java_12_19;

//포함되는 클래스
public class Embeddedclass {
    public int score;
    private Embed embed;

    //생성자를 이용한 주입
    //다른곳에서 받아오고싶을때
    //만들때 생성이되는ㄴ거 메모리 효율은 떨어질수 있음
    public Embeddedclass(Embed embed) {
        this.embed = embed;
    }

    public void set() {
        //여기서 포함하는 클래스의 멤버를 수정
        embed.name = "독수리 다방";
        System.out.println(embed.name);
    }

    //주입을 위한 setter
    //필요할 때 호출 하는것
    public void setEmbed(Embed embed) {
        this.embed = embed;
    }

}
```

- 포함하고 있는 클래스

```java

public class Embed {
    public String name;

    public void createembedded() {
        //다른 클래스의 인스턴스를 생성
        //생성자를 이용한 주입 -생성자를 호출할 때 현재 인스턴스의 참조를 넘겨주는 것이다.

        Embeddedclass obj = new Embeddedclass(this);

        obj.setEmbed(this); //setter 이용

        //포함하는 인스턴스가 포홤된 인스턴스의 멤버 호출
        System.out.println(obj.score);
        obj.score = 124141;


        System.out.println(obj.score);
    }
}
```

### 데이터 공유

> 데이터를 공유하기 위해서는 매개변수로 계속 넘겨주는 방법도있다 .

- 공유 데이터를 모든 곳에서 접근 가능하도록 만들어서 사용할 수 있도록 하는 방법 가능
    - 모바일 앱은 이방식으로 기본적으로 제공하고 탭 형태의 애플리케이션에서 많이 사용
    - 포함되는 형태에서의 데이터 공유
        - 네비게이션 구조에서 사용
            - 생성자 나 setter를 이용한 주입

### Java Document

> 자바의 클래스 나 메서드에 기술하는 주석

- 자바는 문서화 주석 기능을 제공
    - 외부 라이브러리를 이용해도 생성 가능
- 문서화 주석은 클래스 ,메서드 필드에 작성 가능
- 자바 문서화 주석에 추가할 수 있는 태그

```java
/**
 * @author 작성자
 * @deprecated 폐지되었거나 폐지될 가능성이 있는 코드
 * @param 메서드의 파라미터 나 제너릭에 대한 설명
 * @return 리턴값에 대한 설명
 * @since 버전에 대한 설명
 * @throws 예외에 대한 설명 
 */
```

- 생성 - [project]- [Generate Javadoc]를 이용하면 HTML로 생성해줌
- https://docs.oracle.com/javase/8/docs/api/
    - javadocs

### Exception Handling

> 오류의 종류

- Compile Error
    - java 파일을 .class 파일로 만들 때 발생하는 오류
    - 문법 오류
    - IDE 를 사용하면 체크 가능
- Logical Error
    - 논리적 오류
        - 알고리즘의 잘못으로 인해서 잘못된 결과가 만들어지는 경우
        - black box test(기능 테스트 - 입력을 주고 정확한 출력이 만들어지는지 확인하는 테스트) 과정에서 발견되고 debugging 을 이용해서 수정
- Exception 예외
    - 문법적인 오류는 없어서 컴파일이 되고 빌드도 되서 실행이 되는데 실행 도중 예기치 않는 상황이 발생해서 프로그램이 중단되는 현상
    - 정상적인 동작이 수행되도록 해서 해결
- Assetion
    - 문법적으로 아무런 문제가 없지만 강제로 예외를 발생시켜서 프로그램을 중단시키는 것
    - 예전에는 별도의 기능으로 제공했지만 최근에는 예외 처리로 수행하는 경우가 많음
- 참고  정훈
  - https://velog.io/@jsj3282/%EC%9E%90%EB%B0%94%EC%9D%98-%EC%98%88%EC%99%B8%EC%9D%98-%EC%A2%85%EB%A5%98-3%EA%B0%80%EC%A7%80
  - https://chanhuiseok.github.io/posts/java-3/
### Debugging

> 메모리의 값을 확인하는 작업

- 방법
    - 출력하는 메서드를 이용해서 확인
    - System.out.print 메서드 이용
    - 테스트를 수행해주는 외부 라이브러리 나 테스팅 툴을 이용

### Exception

> Exception(예외)

- 문법적으로 이상이 없어서 컴파일 시점에는 아무런 문제가 되지 않지만 실행 중에 발생하는 예기치 않은 사건으로 인해서 프로그램이 중단되는 현상

#### 예외 처리

- 예외가 발생할 가능성이 있는 코드를 예외 처리 구문으로 묶어서 예외가 발생한 후의 동작을 설정하는 것
- 예외가 발생했을 때 예외 내용을 로깅하거나 예외가 발생했을 경우 정상적인 값으로 변경해서 동작하도록 하거나 예외가 발생한 경우 무시하고 동작하도록 하기 위해서
  수행

```java
public class Main() {
    public static void main(String[] args) {
        try{
            //예외가 발생 가능성ㅇ이 있는ㄱ코드
        }catch(//예외가 발생했을 때 수행할 내용){
            
        }finally{
        //예외 발생 여부에 상관없이 수행할내용 
    }
}
```


### Throwable 클래스의 멤버

- String getMessage(), String getLocalizedMessage():
    - 예외 인스턴스의 상세 메시지를 무낮열로 리턴
- void printStackTrace()
    - 예외 인스턴스 및 백트레이스를 표준 에러 스트림에 출력
        - 예외가 발생한 지점까지 호출된 메서드를 역순으로 빨간색으로 출력
- 예외를 처리하는 방법
    - 메서드 내에서 처리
    - 메서드를 호출한 지점으로 예외처리 양도
    
```java
  public static void main(String[] args){

int i = 0;
int j =0;

try{
System.out.println(i/j); //이렇게하게되면 에러발생`
}catch(NullPointerException e){
//catch가 여러개이면 일치하는 catch블럭의 내용을 처리하고 
//다른 catch는 모두 pass 
//상위 클래스의 참조형 변수에는 하위 클래스 인스턴스 참조를
//저장할 수 있기 때문에 catch를 여러개 작성할때 주의해야한다
//상위 클래스의 예
System.out.println("Null 이 속성이나 메서드를 호출하면 예외발생")
}catch(ArithmeticException e){
System.out.println("0으로 나누지마세요")
}
}
```
- catch 는 예외처리 클래스 이름 달리해서 여러개 작성가능
- finally 는 생략 가능하고 1번만 작성
- 예외처리 블럭은 각각의 블럭으로 메모리할당이 이루어짐


[https://reakwon.tistory.com/155](https://reakwon.tistory.com/155) 참고 

### BufferedReader 

[https://jhnyang.tistory.com/92](https://jhnyang.tistory.com/92)
  
