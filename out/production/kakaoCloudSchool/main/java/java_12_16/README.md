# Java 생성자

### Constructor (생성자)

- 인스턴스를 생성할 때 호출하는 메서드
- 생성자의 이름은 클래스 이름과 동일
- 생성자는 new로 호출할 수 있음
- 생성자는 메모리 할당율 수행하고 핟랑받은 메몰의 참조를ㄹ리턴
- 생성자를 만들지 않으면 매개변수가 없는 생성자가 제공
- 생성자를 직접 작성하면 기본적으로 제공되는 생성자는 소멸
- 생성자는 리턴이 없다

```java

public class Member {

    private String email;
    private String pwd;
    private String[] nicknames;
    private Date birthday;
    private boolean married;
    private int age;

    ///매개변수가 없는생성자  -Default Constructor
    // 기본적으로 제공되지만 생성자를 만들면 없어진다
    public Member() {
        super();
    }


    //모든 속성을 매개변수로 받아서 초기화해주는 생성자
    public Member(String email, String pwd, String[] nicknames, Date birthday, boolean married, int age) {
        this.email = email;
        this.pwd = pwd;
        this.nicknames = nicknames;
        this.birthday = birthday;
        this.married = married;
        this.age = age;
    }

    public void init(
            String email,
            String pwd,
            String[] nicknames,
            Date birthday,
            int age,
            boolean married
    ) {
        //현재클래스에서찾고자할 댄 this
        //클래스의 메서드안에서 속성이름을 사용하면
        //메서드 안에 만들어진 것을 찾고 없으면 클래스에 만들어진
        //것을 그래도 없도없으면 상위클래스에서 찾고 없으면 에러
        this.pwd = pwd;
        this.nicknames = nicknames;
        this.birthday = birthday;
        this.age = age;
        this.married = married;
        this.email = email;


        //상위클래스에서 찾고싶을땐 super()

    }

    @Override
    public String toString() {
        return "Member{" +
                "email='" + email + '\'' +
                ", pwd='" + pwd + '\'' +
                ", nicknames=" + Arrays.toString(nicknames) +
                ", birthday=" + birthday +
                ", married=" + married +
                ", age=" + age +
                '}';
    }


}

```

- 매개변수가 있는 생성자를 만들면 매개변수가 없는 생성자가 소멸된다
- 매개변수가 있는 생성자를 만들대는 되도록이면 매개변수가 없는 생성자도 추가해주는 것이 좋ㄷ .

### 일련번호 생성

- static 속성은 클래스 안에 1개 만 만ㄷ르어잼
- instacne 속성은 instance별ㄹ로 별도록 소유한다,ㅣ

#### super

- 하위 클래스의 instance 매서드에 숨겨진 매개변수
- 상위 ㅡㅋㄹ래스의 멤버를 호출하고자 할때 이용
- instance메서드어 변수이름을 사용하면 현재 메서드 내부 그리고 거기에 없음 자신이 속한 클래스의 속성을 확인하고 자신의 클래스에 없으면 상위클래스에서 확인하고 없으면 에러 ..
- this 룰 추가하면 자신의 메서드 안에서는 찾지 않고 클래스에 확이나고 없으면 상윜zm랠스에서 확인.
- 하위클래스 와 ㅏㅅㅇ위클래스에 동일 한ㅇ 이름이나 속성, 메서드가 존재할때 상위클래스의 것읅ㅅ 호출 하고 자할땐 supe.
- 속성의 이름이 중복되는경우 는 겨우 없고 대부분 메서드의 이름이 중복되는 경우가 많기때문에 super.메서드이름() 의 형태로 사용하는 경우가 많다 .
- 참고
  - https://freestrokes.tistory.com/72
  - habonyphp.com/2021/01/java-28.html

### 참조형 변수의 대입

- 참조형 변수에는 자신의 참조형 데이터의 참조만 사용가능
- 상속 관계인 경우는 참조형 변수에 하위 클래스의 인스턴스 참조를 대입하 수있다 .
- 상위 클래스의 참조형 변수에는 하위 클래스 타입의 인스턴스 참조를 대입할 수 없지만 강ㅈ ㅔ형 변환을 하면 대입이 가능하다
- 상위 클래스 타입의 참조형 변수에는 하위클랫 ㅡ타입의 인스턴스 참조를 배로 대입할수있
- 하위 클래스 타입의 참조형 변수에는 상위클래스 타입의 인스턴스 참조를 대입할수 있다 .

```java
public class Main {
    public static void main(String[] args) {
        Person a = new Student("haenuu", 24);
        a.print(); // Student 메서드 호출: haenuu, 나이: 24
        Person b = new Teacher("Haen", "Computer");
        b.print(); // Techaer 메서드 호출: Haen, 과목: Computer
    }
}
```

### 참조형 변수의 멤버호출

> 컴파일 단계에서는 변수를 선언할 대 사용한 자료형이 있는지 그리고 변수가 자신의 자료형이 호출 할 수 있는 멤버를 호출하는 지 확인해서 에러여부를 결정

```java
public class PolymorphisMan {
    public static void main(String[] args) {
        //이부분은 superClass 만 바라보는 부분
        SuperClass superClass = new SuperClass();

        //변수를 선언할 때 사용한 클래스와
        //인스턴스를 생성하기 위해서 호출하는 클래스가 상속관계 이면 가능
        // 인스턴스를 생성하기 호출하는 클래스가 하위클래스
        SuperClass subclass = new SubClass();

        //subclass 는 선언할 때 superClass인데
        //인스턴스는 subclass 로생성
        //호출할 수있는 것은 superclass 를 참조 하지만
        //호출되는것은 subclassdlek
        subclass.sueprmethod(); //오버로딩이 안된 메서드
        subclass.display();//오버로딩된메서드
        //SubClass 의 메서드를 호출한다
    }
}

```

### Polymorphism (다형성)

> 동일한 메시지에 대하여 다르게 반응하는 성질

- 동일한 코드가 상황에 따라 다른 메서드를 호출하는것
  - 대입된 인스턴스에 따라 메서들

### 스타크래프트 게임 만들기

> 종족이 3개고 ㅡ유저는 종족 중 하나를 선택해서 게임을 수행

### abstract (추상)

> 내용이 없는 메서드로 오버라이딩을 위해서 생성

- 결과형 앞에 abstract 라는 키워드를 추가하고 {} 를 제거해서 생성
- abstract 메서드가 abstract class 나 interface 에 존재해야한ㄷㅏ .
- abastract 메서드를 소유한 class 를 extends하거나 interface 를 implements 하게되면 반드시 오버라이딩 해서 내용을 만들어야한다.

### abstract (추상 클래스)

> <b>인스턴스를 생성할 수 없는 클래스 </b>

- new 추상클래스 x 안댐
- 다른 클래스에 상속을 해서 사용
- 참조형 변수의 자료형으로는 사용할 수 있다 .

```java
//Protoss, Zerg, Terran 클래스의 인스턴스를
//하나의 변수에 대입할 수 있도록 하기 위한 상위 클래스
public abstract class Starcraft {
	//오버라이딩을 위해서 생성한 메서드 - 추상 메서드
	//추상 메서드는 추상 클래스 나 인터페이스에만 존재할 수 있음
	public abstract void attack();
}
```

### interface protocol

> 상수 와 abastract method 와 default method를 소유한 객체

- 인터페이스에 변수에 선언하면 상수가 되고 메서드를 만들면 기본적으로 추상 메서드가 됨
- 인스턴스를 생성할 수 없지만 변수의 자료형으로 는 싸용 가능
- 하나의 클래스의 여러개를 implements 가능

### 캐스팅에대해서

- https://yoon-ve.tistory.com/entry/JAVA-%EC%97%85%EC%BA%90%EC%8A%A4%ED%8C%85-%EB%8B%A4%EC%9A%B4%EC%BA%90%EC%8A%A4%ED%8C%85
