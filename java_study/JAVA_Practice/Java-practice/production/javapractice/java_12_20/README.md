### Object 클래스의 메서드 
> 모든 ㄴ인스턴스가 가능
- clone을 재정의 할 때 Cloneable 인터페이스 (약속,규칙,규약-protocol)를 implements하는 것을 권장
- API 클래스 중에 Cloneable 이라는 인터페이스를 implements한 클래스는 clone 이라는 메서드를 호출하면 깊은복사를 수행한 결과를 리턴
- 자료구조 와 관련된 클래스들은 대부분 구현되어 있고 Wrapper나 String 클래스는 복사 생성자(자신 과 동일한 데이터 타입을)매개변수로
받는 생성자를 이용해서 복제를합니다 

### boolean equals
>참조가 아니라 내용을 비교하고자 할 때 사용하는 메서드 이고 재정의 해야한다 
- int hashCode
  - 해시코드를 리턴하는 메서드인데 재정의 가능하다 
  - 이 메서드의 리턴값은 실제 해시코드가 아니다 
- void finalize 
  - 인스턴스가 메모리에 정리될 때 호출되는 메서드 
    - 재정의해서 사용
- String toString()
  - 인스턴스를 문자열로 표현하기 위해서 제공되는 메서드 
    - wowjddmlgotj tkdydgksek
    - 출력하는 메서드에 인스턴스 참조를 대입하면 이 메서드의 리턴 값이 출력된다 
- notify(),notifyAll(),wait()
  - 쉬고있는 스레드를 깨우는 메서드 와 동작 중인 스레드를 쉬도록해주는 메서드 
- 인스턴스의 등가성 판단
  - == 참조가 같은지 여부를 리턴
  - 재정의해서 사용 


### week copy와 deep copy
- week copy 는 참조를 대입하지 않고 내부 데이터를 복사를 해서 복사본을 만드는 것인데 재귀적으로
복사를 하지않을것
- 참조하는 형태의 데이터 안에 다시 참조하는 데이터가 있는 경우 복사본이 원본에 영향을 줄수있음
- deep copy는 재귀적으로 복사본을 만들어서 주는것으로 복사본이 원본에 영향을 줄 수 없음

-배열 같은경우는 깊은복사를 하고 대입을해야한다


### Wrapper class 
> 자바의 기본형 데이터를 인스턴스로 생성 할 수 있도록 해주는 클래스
- 자바의 기본형 데이터를 인스턴스로 생성할 수 있도록 해주는 클래스
- 자바의 기본형은 참조를 저장하기는 하지만 null을 대입할 수 없는 구조로 만들어져있다.
```
in a= null; 결과: err;
```
- 기본형과 다른 자료형은 데이터의 의미가 다르기 때문에 형변환도 불가능하다.
- 데이터베이스에 정수데이터를 저장하도록 설계한 후 이 데이터를 자바의 데이터와 매핑을 시키고자하는경우
int 나 long 을 사용하면 잠재적인 에러 발생가능성이 있음 
```
byte -> byte
short -> short
char -> character
int -> Integer
long -> Long
```
- 8개의 클래스는 object 클래스로부터 상속을 받았기 때문에 인스턴스를 생성해서 object 클래스
타입의 변수에 대입을 할 수 있고 기본형이 아니기 때문에 null 도 저장할 수 있고 클래스이므로 자신과 관련된 
메서드 나 속성도 소유할 수 있다.
- 데이터 베이스에서 null 저장 가능한 컬럼의 경우느 기본 자료형 보다는 Wrapper class 를 사용하는것이좋다

### 인스턴스 생성
> Default Constructor 가 존재하지 않는다
- new Integer(); //err;
- 매개변수가 1개인 생성자를 이용 
- 다른 종류읭 데이터 (문자열) 를 이용해서 생성
  - static 메서드인 parse자료형(데이터)를 이용 
  - Integer.parseInt("10") //문자열을 가지고 정수를 생성
- Wrapper Class의 데이터를 문자열로 변환
  - toString() 을 호출하면 된다.
- Wrapper 클래스 와 String 클래스는 내부 데이터를 수정할 수 없다.
  - 데이터를 변경하면 새로운 공간을 할당받아서 데이터를 저장한 후 그 참조를 기억한다.
- AutoBoxing 과 AutoUnBoxing
  - AutoBoxing 은 기본형 데이터를 자동으로 Wrapper Class 타입으로 변환하는것
  - 기본형 의 데이터를 Wrapper Class 인스턴스로변환
```
Integer i =new Integer(a);
Integer k =a; 
// 가능 a 부분을 jdk 가 new Integer(a)로 변환해서 수행 - autoBoxign
```
- Auto UnBoxing 은 Wrapper Class 타입의 데이터를 기본형으로 변경하는것
```
Integer i =new Integer(a);
int y = x.intvalue();
int z= i; //메서드를 호출하지 않고 직접대입이가능하다
```
### 메서드 재정의
- equals 와 hashCode 메서드가 재정의 되어있음
- 크기 비교하는 compare 메서드도 재정의 되어있음
- toString 도 재정의 되어있어서 toString 을 호출하면 저장하고 있는 데이터가 문자열로 리턴된다.

### BigInteger 와 BigDecimal
>BigInteger
- 아주 큰 정수를 저장하기 위한 자료형
- 내부는 int 배열로 구성
- 문자열로된 숫자를 생성자의 매개변수로 받아서생성

### BigDecimal
> 정밀한 숫자를 저장하기 위한 자료형,소수의 연산 때문에 만들어진 클래스 
- 숫자로 된 문자열을 생성자의 매개변수로 받아서 생성
- 일반 정수 나 실수로 변환할때는 intValue나 floatValue,doubleValue 같은 메서드를 이용
- Oracle 에서 number 로 만든 숫자 데이터를 자료형을 설정하지 않고 가져오면 BigDecimal로 가져온다
```
//정확한 산술연산
//BIgDeciaml 데이터를 만들고 연산을 수행하는 메서드를 
//호추랗면 정확한 결과를 만들 수 있다 .
BigDecimal b1 = new BigDecimal("1.600000000000000000");
BigDecimal b2 = new BigDecimal("1.600000000000000000");

System.out.println(b1.add(b2);
```
- 참고 java API sample 
  - https://java2s.com/Code/JavaAPI
- JAVA API 문서를 뽈때는 상속 계층 과 구현된 인터페이스를 확인
- Field Summary 
  - static final(클래스이름으로 호출)로 만들어져 있는데 이클래스안에서 사용되는 옵션값이다)
- Constructor 
  - 생성자
- Method
  - 하나씩 설명된 메서드는 자신의 클래스에만 있는 메서드이거나 상속된 메서드 중에서 재정의 된 메서드이고
  박스에 한꺼번에 나열된 메서드는 상속된 메서드를 그대로사용 

### String킆래스
> 문자열 클래스
- 문자열 클래스
  - String 
    - 고정된 문자열 저장, 문자열을 수정할 수 없음
  - StringBuffer
    - StringBuffer는 공통 메소드가 동기화되므로 멀티 쓰레드 환경에서는 StringBuffer를 사용하는 것이 안전하고
      - (값이 예상치 못하게 변경되는 것 방지)
    - StringBuffer는 무거운편에 속하며 메모리 사용량이늘고 속도가 많이느림.
      - 추가나 변경등의 작업이 많을경우는 그냥 StringBuffer를 쓰고, 거의 없는경우엔 String을 사용하자
    - 자료형에 + 연산이 있을때마다 새로운 String 객체를 생성된다
    - 변경 가능한 문자열 저장,Multi Thread 에 Safe 
      - 사용을 할 대 다른 Thread 가 사용중인지 확인 ,Legacy Class
  - StringBuilder
    - 성능이 아주뛰어남
    - Multi Thread 에 unsafe
      - 다른 Thread 가 사용중인지 확인하지않음 
    - 변경 가능한 문자열 저장 
- StringBuilder 와 StringBuffer 의 차이 
  - StringBuffer는 공통된 메소드 동기화로인해 멀티스레드환경에서만 사용하고 그 이외에는 StringBuilder를 사용하면된다
- 문자열 연산을 많이해야하는 경우에는 StringBuilder 를 이용해서 연산을 하고 String 으로 변환해서 사용하는것을권장
### 문자열 클래스의 차이 
```JAVA
public  class main(){
  public static void main(String[] args) {
    String str ="Hello";
    System.out.println(System.identityHashCode(str));
    //string 은데이터 수정이 안되기 때문에
    //새로운 공간에 기존의 문자열을 복사한 후 작업을 수행하고
    // 그 참조를 다시리턴
    //기존의 데이터가 저장된 공간은 메모리 낭비가 될수있다
    str+="java";
    System.out.println(System.identityHashCode(str));
  }
}
```

```java

public class main(){
  public static void main(String[] args) {
    //변경가능한 문자열을 저장
    StringBuilder sb = new StringBuilder("Hello");
    System.out.println(System.identityHashCode(sb));
    //문자열을 추가하면 현재 저장된 공간에 이어붙이기를 수행
    sb.append("kava");
    //해시코드가 이전데이터 와 동일
    System.out.println(System.identityHashCode(sb));
  }
}
```

### 인스턴스 생성 
> 문자열 리터럴을 이용해서 생성이 가능하고 생성자를 이용해서도 생성가능
- int Length : 문자열의 길이를 리턴
- char charAt(int idx) 
  - idx 번째 문자 리턴
  - 문자열이 변경되는 메서드는 전부 변경을 한 후 리턴을 한다 

### 문자열 처리 시 고려사항
- 영문의 경우는 대소문자 구분
- 좌우 공백 제거
- 문자열 분할
  - 위치를ㄹ 가지고 분할하기도 하고 특정 패턴을 찾아서 분할하기도함
- 특정 문자열 패턴의 존재 여부 확인 
  - 정규식을 이용 
- 문자열의 동일성 여부
  - equals
- 문자열의 크기 비교
  - compare
- 여러 데이터를 조합해서 하나의 문자열로 만들기 
  - String.format() 메서드를 이용하는데 printf메서등와 사용법이동일하다.
- 한글의 경우는 인코딩
  - getBytes 라는 메서드를 이용하면 인코딩 방식을 설정해서 byte배열로 리턴 받을수있고 
  new String(바이트 배열 ,"인코딩방식") 을 이용하면 바이트 배열을 인코딩 방식으로 변환후 문자열로리턴
  - 이 기종의 간의 통신에서 사용하는 경우가 많다
  - 최근에는 거의 utf-8을 사용하기 때문에 이 문제가 흔하지는 않는다 

```java

public class main(){
  public static void main(String[] args) {
      
      //String.format
    double lat = 12.789;
    double lng = 24.2987;
    //위의 데이터를 가지고 문자열로 생성
    String msg= String.format("위되:%.3f 경도 :%.3f\n", lat, lng);
    System.out.println(msg);
      
      
      
      
      
      //인코딩 
     String incodingStr= "안녕하세요";
        //바이트 배열로 무ㅠㄴ자열을 리턴
        try{
            //바이트 배열로 문자열을 변환 - MS949
            //동일한 프로그램이 아닌 시스템 과 채팅을 할 때는 
            //문자열을 직접 전송하지 않고
            //바이트  배열을 만들어서전송
            //Byte 배열을 문자열로 변환
          byte [] bytes = incodingStr.getBytes("MS949");
          //Byte 배열을 문자열로 변환
          String result = new String(bytes,"MS949");
          //문자열이 깨지면 인코딩 방식을 ㅎ확인하고 변경해야한다.
          System.out.println(result);
        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }
  }
}
```

### System 클래스
> 실행 환경과 관련된 속성 과 메서드를 제공하는 클래스 
- 공개된 생성자가 없음 
  - 즉 외부에서 인스턴스 생성을 할 수 없다. 
  - 생성자가 없는 경우 확인할 사항
- interface 나 abstract class 인지 확인 
  - 인스턴스를 만들 수 없기 때문에 보이지 않음
- 모든 멤버가 static 인지 확인 
  - 인스턴스 만들필요가없기때문에
- 자기 자신을 리턴하는 static메서드가 있는지 확인
  - 디자인 패턴 적용 
- 자신의 이름뒤에 Builder 나 Factory가 붙는 클래스가 있는지 확인
  - 직접 생성하는 것이 번거로워서 대신 생성해주는 클래스를 이용
- System 클래스는 모든 멤버가 static이라서 인스턴스 생성이 필요없어서 생성자를 숨김
- 속성 -내부 객체
- in 
  - 표준 입력 객체
- out
  - 표준 출력 객체
  - 일반적으로 모니터
- err 
  - 표준 에러 객체
  - out 처럼 출력으로 사용할 수 있는데 err 을 이용해서 출력하면 빨간색으로 표시되고
  out과는 별개로 동작하기때문에 out과 같이 사용하면 출력 순서를 알수없다

### 메서드
> 현재 시간을 밀리초 나 나노초 단위로 리턴 : currentTimeMillis 와 nanoTime
- 환경 설정 값을 가져오는 메서드
  - getProperty,getenv
- 해시 코드 리턴해주는 메서드
  - identityHashCode
### java 에서 시스템 운영체제 등 자바버전확인해보기 
```java
public class main(){
  public static void main(String[] args) {
    String os =System.getProperty("os.name");
    String version =System.getProperty("java.version");
    System.out.println(os);
    System.out.println(version);
  }
}
```


### math 
> 수학관련 메서드를 소유한 클래스
- 모든 멤버가 static 이라서 인스턴스를 생성할 필요가 없는 클래스 
  - 공개된 생성자가 없음 
- 클래스의 메서드가 리턴하는 결과는 운영체제 별로 다르게 나오기도 한다
- 운영체제의 특정 기능을 사용하지 않고 공통 기능만을 이용해서 연산하는 StrictMath클래스도 제공

### Runtime
> 프로세스
- 싱글톤패턴 


### 싱글톤패턴 
- 참고자료 정훈 
- https://itstudy402.tistory.com/8


### Fast EnumEeration(빠른열거)
> Collection 의 데이터를 순서대로 빠르게 접근하는것
- 배열과 Collection데이터들에서 가능 
- jdk 1.8에서는 이 작업도 내부적으로 구현해서 더 빠르게 접근하는 Stream API가 추가됨

```JAVA
public class main(){
  public static void main(String[] args) {
    for(임시변수:배열 또는 Collection 데이터){
        //컬렉션 데이터를 순차적으로 접근하면서 수행할 작업
    }
  }
}
```

### Generic
> Template Programming(일반화 프로그래밍)
- 데이터 타입을 미리 결정짓지 않고 실행할 때 결정하는 것
- 클래스 내부에서 사용할 데이터 타입을 인스턴스를 생성할 때 결정하는 것
- 자료구조 나 알고리즘을 구현할 때 동일한 알고리즘에도 불구하고 데이터의 자료형이 달라서 여러개 구현해야하는 번거로움을 없애기 위해서 사용
- 클래스를 만들 때는 미지정 자료형을 사용하고 인스턴스를 만들때 실제 자료형을 설정해야함 
  - Object클래스를 사용하는 것보다는 에러 발생 가능성이 낮다.

### Generics 선언 형식
```java
public class main(){
  public static void main(String[] args) {
    class 클래스이름 <미지정 자료형 이름 나열>{
        
    }
    
    //제네릭 적용되었을경우 인스턴스생성
    //클래스이름 <자료형> 변수 =new 생성자<>(매개변수 나열)
  }
}
```

- 인스턴스를 생성할때 자료형을 기재하지 않으면 경고가 발생하고 데이터는 Object클래스 타입으로 간주
  - 데이터를 형변환해서 사용해야한다

- 실습


```java
package java_12_20;

import java.io.UnsupportedEncodingException;

public class Main {
    public static void main(String[] args) {
        //generic 적용된 클래스의 인스턴스를 만들때는 
        //실제 자료형을 결정지어야 경고가 발생하지않는다
        GenericClass<String>obj1 = new GenericClass<>("아..졸려","2시부터4시지옥","힘드라");

        obj1.display();
    }
}

public class GenericClass<T>(){
    public GenericClass(T ...n){
        T =n;
    }
}
//기본형은 Generics에 적용할 수 없다.
```

### 제네릭 특징
> 변수를 선언하거나 메서드의 매개변수 그리고 리턴타입으로도 사용가능
- static 속성이나 메서드에는 적용이안된다.
  - 제네릭은 인스턴스를 생성할 때 자료형이 결정되는데 , static멤버는 클래스를 메모리에 로드할때 만들어지기때문에
- 미지정 자료형을 이용해서 배열 변수를 만드는 것은 되지만 배열을 만드는것은안된다 
- 미지정 자료형을 만들때 <자료형 이름 extends 인터페이스나 클래스>로 작성하면 인터페이스나 클래스를 상속하는 클래스의 자료형만가능
- 자료형이 일치하지않으면 실행시 ClassCastException이 발생한다
- <자료형이름 super 인터페이스나 클래스>로 작성하면 동일한 자료형 또는 상위 타입의 자료형만 가능하다
- <?> 하면 모든 자료형이가능하다 
  - 근데 ? 말고 자료형이름을 써도 기본적으로 모든 자료형이 가능하다

### Enum
> 나열형 상수 , 열거형 상수 
- 상수의 모임을 만드는 것 
- 선택을 제한하기 위한 목적으로 사용 
  - jdk api 에서는 enum 보다는 static final 상수를 많이 사용한다.
#### 선언 
```
enum 이름 {
상수 이름 나열 
}
```
- 사용시 
  - <b>이름.상수이름</b>
- 변수를 만들 때 enum 의 이름을 이용해서 생성하면 enum 에 정의된 상수만 대입이가능
- 실습
```java
 enum Gender{
    WOMAN,MAN
}
public class main(){
  public static void main(String[] args) {

    //int 로 만들면 이렇게 정의하지 않은 값을 대입하는 게 가능
    Gender gender = Gender.WOOMAN;
    System.out.println(gender);

  }
}
```

### 특징 
> 생성자와 일반 메서드 생성가능
- 자바는 enum을 하나의 클래스로 간주하고 각 상수는 하나의 인스턴스로 간주
- == 으로 일치 여부를 확인할 수 있고 compareTo라는 메서드로 크기비교 가능 

### Annotation
>jdk 1.5이상부터 지원을함
- @다음에 특정한 단어를 기재해서 주석을 만들거나 자바 코드에 특별한의미 또는 기능을 부여하는것


### 용도 
> 컴파일러가 특정 오류를 체크하도록 지정
- 컴파일러가 특정오류를 체크하도록 지시 
  - 자바가 사용
- Build 나 Batch를 할대 코드를 자동 생성 
  - 프레임워크가 이기능을 많이 사용함 
- Runtime 시 특정 코드를 실행

### JDK 가기본적으로 제공하는 것
>@override : 오버라이딩을 한다라는 의미를 전달
- 메서드가 상위클래스 나 인터페이스에 존재하지 않으면 컴파일 에러
- @Deprecated 
  - 더이상 사용하지 않은 것을 권장 
- @SuperWarning
  - 경고를 발생시키지 않도록 하는 기능
- @SafeVarags 
  - varargs에 Generics를 적용한다는 의미를 전달
- @FunctionInterface
  - 함수형 인터페이스라는 의미를 전달(추상 메서드 하나로 만들어진 인터페이스)
    - 이 인터페이스는 람다 적용이 가능하다라고 알리는 것 
    - 안드로이드에서는 이 인터페이스로 anonymous class 를 구현하면 람다식으로 구현



### java.util * 패키지 
> 자료구조 클래스 와 자주 사용되는 util 관련 클래스가 존재하는 패키지
- import 해서 사용해야하는 클래스 이름만으로 ㅏㅅ용가능 

#### Arrays 클래스
- 배열을 조작하기 위한 클래스
- static 메서드만 존재하기 때문에 공개된 생성자는 없음

#### copyOf(원본 배열, 데이터개수)
- 원본 배열에서 데이터 개수만큼 복제를 해서 배열로 리턴
- 배열의 데이터 개수보다 더 많은 데이터 개수를 기재하면 나머지는 기본값으로 채워서 리턴 
- 내부적으로는 System 클래스의 arraycopy라는 메서드를 이용 


#### toString(배열)
> 배열의 모든 요소의 toString 을 호출해서 그 결과를 다시 하나의 문자열로 만들어서 리턴 


#### sort 
>데이터를 정렬해주는 메서드 
- 배열만 대입하는 메서드는 크기 비교를 해서 오름차순 정렬을 수행 
- 숫자 데이터는 부등호를 이용해서 비교
  - 그 이외의 데이터는 Comparable인터페이스의 compareTo라는 메서드를 이용해서 비교
  - 숫자 데이터가 아닌 경우는 Comparable 인터페이스의 compareTo라는 메서드를 재정의 해야한다.
    - 그렇지않으면 ClassCastException이 발생함 
  - compareTo 
    - 메서드는 정수를 리턴하는데 양수를 리턴할려면 데이터의 순서를 변경하고 0이나 음수를 리턴하면 데이터의 순서를 변경하지않음
  - 배열 과 Comparator<T> 인터페이스를 구현한 인스턴스를 대입하는 메서드는 배열의 데이터를 Comparator에 정의 된 대로 오름차순 정렬 
    - 최근에는 이방법을 사용하며
      - 인터페이스를 구현한 클래스의 인스턴스를 대입할때는 대부분의 경우 anonymous class 를 이용함.

```java
package java_12_20;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Comparator;

public class ArraysSortMain {
    public static void main(String[] args) {
//        int [] ar= {60,30,29,524,10};
//
//        //문자열 배열
//        String [] br = {"아 졸려","자도자도 너무졸려","내일눈오는데 오기싫다"};
//        System.out.println(ar);
//        System.out.println(br);
//
//        //정수 배열 정렬
//        Arrays.sort(ar);
//        System.out.println(Arrays.toString(ar));
//
//        Arrays.sort(br);
//        System.out.println();

        //vo 클래스 인스턴스 5개를 소유하는 배ㅑ열
        VO [] datas = new VO[5];
        datas[0] =new VO(1,"장애인",234);
        datas[1] =new VO(2,"김정훈",3);
        datas[2] =new VO(3,"김똥훈",11);
        datas[3] =new VO(4,"김진훈",1413);
        datas[4] =new VO(5,"김상훈",1343);


        System.out.println(Arrays.toString(datas)); //error; 
      // classCastException 발생 - Comparable 인터페이스 형 변환이 안된다는메시지

    }

}


```
- 데이터를 정렬하기 위해서는 크기 비교를 위한 방법이 제공되어야 하는데 제공되지 않는다 .
  - 자바는 반드시 있어야하는 무엇인가를 만들 때 인터페이스를 이용한다 
- 데이터 클래스에 Comparable 인터페이스를 구현해야 한다라는 의미이다.

- VO클래스 수정 
```java 
package java_12_20;
//크기 비교가 가능한 메서드가 있다는것을 보장함
public class VO implements Comparable<VO> {


  private int num;
  private String name;
  private int age;
  //...
  // ...

  @Override
  //크기를 비교하는 메서드
  //양수를 리턴하면 순서가 변경이되고
  public int compareTo(VO o) {
    //숫자는 뺄셈을 해서 리턴하면 된다
    //순서를 변경하면 내림차순이 된다
//        return this.num - o.age;

    //문자는 뺄셈이안됨 
    //문자열은 comparable 인터페이스를 implements했기 때문에 
    //compareTo 메서드로 비교하면된다 
    return this.name.compareTo(o.name)
  }
}

public class main {
  public static void main(String[] args) {
  
    //vo 클래스 인스턴스 5개를 소유하는 배ㅑ열
    VO [] datas = new VO[5];
    datas[0] =new VO(1,"장애인",234);
    datas[1] =new VO(2,"김정훈",3);
    datas[2] =new VO(3,"김똥훈",11);
    datas[3] =new VO(4,"김진훈",1413);
    datas[4] =new VO(5,"김상훈",1343);


    System.out.println(Arrays.toString(datas));

  }

}


```