# Properties

> 이름 과 값의 쌍으로 데이터를 저장하는 구조

- Map은 Key 와 Value에 모든 자료형을 사용할 수 있는데 Key 와 Value에 String 만 가능
- 텍스트 파일에 이름 과 값의 쌍으로 작성한 후 읽어서 사용할 때 이용하던 클래스
- 현재도 Spring Framework에서는 이 형태를 사용하고 있는데 최근에는 XML 이나 YML(yaml - 야믈) 형태로 사용 - 환경 설정에 많이 이용

### 메서드

- String getProperty(String key)
- String setProperty(String key, String value)
- void store(OutputSteream out, String comment): 스트림에 일반 텍스트 형식으로 저장
- void storeToXML(OutputSteream out, String comment): 스트림에 XML(태그 와 유사) 형태로 저장
- Enumeration<K> keys(): 모든 키들을 접근할 수 있는 이터레이터를 리턴
- Enumeration<V> values(): 모든 값들을 접근할 수 있는 이터레이터를 리턴

### Collection 의 동기화

- Collection 클래스들을 사용할 때 다른 스레드가 수정 중인 경우 대기했다가 사용하도록 하고자 하는 경우에는 Collections 클래스를 이용해서 생성하면 됩니다.
- Collections.synchronizedCollection(Collection 클래스의 인스턴스)를 호출하면 동기화된 Collection을 리턴해주므로 리턴된 인스턴스를 사용하면 다른 곳에서 수정 중인 경우
  대기합니다.

### Random 클래스

> 랜덤한 데이터를 생성해주는 클래스

- 정수형 난수의 경우는 정수 범위 내에서 생성하고 실수형 난수의 경우 0.0 ~ 1.0 사이의 난수를 생성
  실수형 난수는 Math.random()으로도 가능합니다.
- 랜덤을 추출할 때는 seed 라고 하는 숫자를 설정한 후 이 seed를 기반으로 난수표를 만든 후 그 난수표에서 하나씩 숫자를 읽어옵니다.
- seed를 예측할 수 없다면 랜덤한 데이터이고 seed를 알고 있다면 순서대로 숫자를 읽어옵니다.
- java는 기본적으로 seed 가 랜덤입니다.
- 랜덤을 사용하는 대표적인 경우가 머신러닝에서 샘플 데이터 추출할 때 입니다.
- 생성자
    - Random(): seed가 랜덤
    - Random(long seed): seed 고정

- 메서드
    - float nextFloat()
    - boolean nextBoolean()
    - int nextInt(): 정수 범위 내에서 리턴
    - int nextInt(int n): 0 ~ n 사이의 값을 리턴

### StringTokenizer

- 문자열을 분할해주는 클래스
- String 클래스의 split 메서드를 조금 더 잘 활용할 수 있도록 만든 클래스

### java.text.SimpleDateFormat

> java.util.Date 타입을 받아서 원하는 형식의 문자열로 변환해주는 클래스

- 사용
    - (new SimpleDateFormat(String 날짜 서식)).format(Data 인스턴스)

## 정규 표현식

> 특정 문자열 패턴을 만드는데 사용하는 클래스

- java에서는 java.util.regex 패키지에 Match 클래스 와 Pattern 클래스를 제공
- Pattern 클래스로 정규 표현식 인스턴스를 생성하고 matcher 메서드를 이용해서 처리를 한 후 Matcher 클래스로 결과를 사용합니다.
- 문자열의 유효성 검사에 많이 이용

```java
public class main() {
    public static void main(String[] args) {
        //정규 표현식 객체 사용
        //문자열 배열에서 a로 시작하고 c로 끝나는 3글자 조회
        String[] ar =
                {"abc", "asc", "aiic"};
        //정규식 생성 - a로 시작하고 아무 글자나 하나 있고 c로 끝나는
        Pattern p = Pattern.compile("^a.c$");
        for (String str : ar) {
            //정규식 패턴과 일치하는지 조사
            Matcher matcher = p.matcher(str);
            System.out.println(str + ":" + matcher.find());
        }
    }
}
```

# Thread

> 동기와 비동기

- 쓰레드는 서로 독립적이다
- 동기
    - 작업을 순서대로 하나씩 처리
- 비동기
    - 하나의 작업이 완료되기 전에 다른 다른 작업을 처리할 수 있는 방식

### Thread에 대해서

- Task
    - 작업
    - process
    - Thread모두를 Task
- Process
    - 운영체제에서 자원을 할당받아서 독립적으로 수행되는 현재 실행중인 프로그램
- Thread
    - Process 내에서 생성되는 작업의 단위로 실행 중 다른 작업으로 제어권을 넘길수있다.
    - 비동기적으로 작업을 수행하고자 할 때 사용
- 참고 정훈 https://honbabzone.com/java/java-thread/

### single Thread & Multi Thread

- Single Thread
    - 현재 실행 중인 스레드가 1개 인 경우
- Multi Thread
    - 현재 실행 중인 스레드가 2개 이상인경우

### Multi Thread Programming 의 장점

- CPU 의 사용률을 향상

### Multi Thread Programming 의 단점

- 동기화 나 교착 상태 등을 방지하기 위한 코드가 삽입되어야 해서 프로그램잉이 어려워짐
- 너무 많은 스레드를 생성하면 Thrashing(Overhead - 스레드가 교체되는 시기에 현재까지 작업한 내용을 저장하고 수행할 스레드의 Context를 불러오는 시간이 늘어남)

### Thread 생성 및 실행

- Thread 클래스 나 Runnable 인터페이스를 이용할 수 있음
- Thread 클래스의 경우는 상속받아서 public void run 이라는 메서드에 스레드로 수행할 내용을 작성한 후 인스턴스를 생성하고 start()를 호추랗면 된다
- Runnable 인터페이스의 경우는 Runnable 인터페이스를 구현한 클래스를 만들어서 public void run 이라는 메서드에 스레드로 수행할 내용을 작성하고 인스턴스를 만든 후 이 인스턴스를 Thread
  클래스의 생성자에 대입해서 인스턴스를 만든 후 start()를 호출해야한다

### Thread 클래스

- 생성자
    - Thread()
        - name 은 jvm이 설정
    - Thread(String name)
        - name 을 설정해서 생성
    - Thread(Runnable runnable)
        - Runnable 인스턴스를 받아서 생성

- 메서드
    - static void sleep (long msec) throws InterruptedException
        - 현재 스레드를 mses 밀리 초 동안 대기
    - void run()
        - 스레드가 수행할 내용을 가진 메서드
    - void start()
        - 스레드를 시작시키는 메서드
- 항상 스레드를 만들고난 다음에 start메서드를 호출시켜야한다

```java


//thread 클래스로부터 상속받는 클래스
class ThreadEx extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
                System.out.println(i);
            } catch (Exception e) {
                System.out.println(new Exception("히이이ㅇ기모링"));
            }
        }
    }
}

public class ThreadCreateMain {
    public static void main(String[] args) {
        Thread t = new ThreadEx();
        t.start();


        //annonymous 클래스 사용
        Runnable r = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000);
                        System.out.println(i);
                    } catch (Exception e) {
                    }
                }
            }
        };
        Thread t2 = new Thread(t);
        t2.start();
        //결과 
        // 00 
        // 11
        // 22 
        // 33
        // 44

    }
}

```

### Thread 상태

> 수명주기

- NEW
    - 생성 만 된 상태
- RUNNABLE
    - 스레드가 실행중인 상태
- WAITING
    - 다른 스레드가 notify 나 notifyAll 을 불러주기를 기다리는 상태
- TIMED_WAITING
    - 일정 시간 대기하고 있는 상태
- BLOCK
    - CPU를 사용할 필요가 없는 입출력 작업을 수행중인 상태
- TERMINATED
    - 스레드가 종료된 상태

### Daemon Thread

- Daemon 이 아닌 스레드가 동작 중이지 않으면 종료되는 스레드
- 다른 스레드의 보조적인 역할을 수행하는 스레드
- 스레드를 생성하고 시작하기 전에 setDaemon에 true만 설정하면 된다
- 대표적인 Daemon Thread 가 main 메서드를 실행시켜 만들어진 main thread 와 garbage collection이다 .

### Tread 의 Priority (우선순위 )

- 모든 스레드의 우선 순위는 동일
    - 어떤 스레드를 먼저 수행하고 어떤 스레드가 자주 실행되는지를 알 수 없다 .
- 우선순위를 설정하면 정확하게 동작하지는 않지만 그래도 우선순위가 높은 스레드가 먼저 뜨는 자주 실행될 가능성이 높아진다
- 우선 순위 설정은 setPriority 라는 메서드로 하게되는데 메서드의 매개변수는 정수인데 숫자가 높은 것이 우선순위가 높다
- 우선 순위 설정을 위해서 JDK 에서는 Thread 클래스에 static final 변수 (Field Summary) 를 제공
    - MIN_PRIORITY
    - NORM_PRIORITY
    - MAX_PRIORITY

### Thread 의 강제 종료

> run 메서드에서 InterruptedException이 발생할때 return 하도록만들고 강제로 종료시키고자 하는 스레드가 있다면 스레드에서 interrupt()
> 라는 메서드 호출

- interrupt() 는 InterruptedException을 발생시키는 메서드이다

### Thread 의 실행 제어 메서드

- join
    - 일정 시간동안 작업을 수행하도록 해주는 메서드
    - Time Sharing(시분할) System
- suspend
    - 스레드를 일시정지 시키는 메서드
- resume
    - 일시 정지된 스레드를 다시 시작시키는 메서드

### MultiThread

> 2개 이상의 스레드가 동시에 실행 중인 상황

- 장점
    - 작업이 수행 도중 대기 시간이 있는 경우 대기 시간을 효율적으로 사용 가능
    - 긴 작업 과 짧은 작업이 있는 경우 짧은 작업이 긴 작업을 무한정 기다리는 것도 방지
- 단점
    - 메모리 사용량이 증가
    - 너무 많은 스레드가 동시에 스레가 동시에 실행되면 처리 속도가느려짐
    - 공유 자원 사용의 문제점
        - critical section (임계 영역 - 공유 자원을 사용하는 코드 영역) 에서의 데이터 수정 문제
        - 생산자와 소비자 문제
            - 생상자가 생산을 하지 않은 상태에서 소비자가 공유 자원에 접근하는 문제
            - Dead Lock (교착 상태) 문제
                - 결코 발생할 수 없는 사건을 무한정 기다리는 것

### 공유 자원의 수정 문제

> 하나의 스레드가 사용 중인 공유 자원을 다른 스레드가 수정하면서 발생할 수 있는 문제

```java
package java_12_22;


//스레드 작업을 위한 클래스
class ShareData implements Runnable {
    private int result; //합계를 저장할 속성
    private int idx;//합계를 구할 때 사용할 인ㄷ젝스

    public int getResult() {
        return result;
    }

    //idx 의 값을 1씩 증가하면서 result에 더해줄메서드
    private void sum() {
        for (int i = 0; i < 1000; i++) {
            idx = idx + 1;
            try {
                Thread.sleep(1);
                System.out.println();
            } catch (Exception e) {
            }
            result = result + 1;
        }
    }

    @Override
    public void run() {
        sum();
    }
}

public class MutexMain {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        Thread th1 = new Thread(shareData);

        th1.start();
        Thread th2 = new Thread(shareData);

        th2.start();
        try {
            Thread.sleep(5000);
            System.out.println(shareData.getResult());
        } catch (Exception e) {
        }
        System.out.println(shareData.getResult());
    }
}

```

### 어느 한쪽이 작업을 마무리하기 전에 다른 곳에서 작업을 수행하기 때문ㅇ ㅔ발생하는 문제

> 문제해결방법 2가지

- 스레드로 작업할 때 데이터를 복제하지 말고 원본에 작업하라고 할 수 있습니다 .이때 사용되는 예약어가 voatile
    - 작업을 순차적으로 실행하도록 하는것
- synchronized (인스턴스) {}:{} 내에서는 인스턴스를 사용하는 부분이 동시에 실행될 수 없음
- 권장 메서드에 synchronized 를 추가
    - 메서드가 동기화가 된다

### 생산자 와 소비자 스레드

> 생상자 스레드가 만든 자원을 소비자 스레드가 동시에 사용하는 것은 가능한데 생산자 스레드가 자원을 아직 생성하지 못했는데
소비자 스레드가 자원을 사용하려고 하면 자원이 만들어지지 않아서 예외가 발생하게 됨

- 이런 경우에는 사용할 자원이 없으면 소비자 스레드는 waiting 을 해야하 고 생산자 스레드는 자원을 생성하면 소비자스레드에게 시그널을 줘야한다
- 자바에서는 Object 클래스에서 waiting 을 위한 wait 메서드 와 notify 와 notifyAll 메서드를 제공하는데 이 메서드드들은 synchronized 메서드 안에서만
  동작

- 실습

```java 
package java_12_22;


import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

//공유자원클래스
class Product {
    //공유자우넌
    List<Character> list;

    //공유지원을 넘겨받기 위한 생성자
    public Product(List<Character> list) {
        this.list = list;
    }

    //공유 자원에 데이터를 삽입하는 메서드
    synchronized public void put(char ch) {
        list.add(ch);
        System.out.println(ch + "입고");
        try {
            Thread.sleep(1000);

        } catch (Exception e) {
        }
        System.out.println("재고 수량" + list.size());
        notify();
    }

    //공유자원을소비하는 메서드
    synchronized public void get() {
        if (list.size() == 0) {
            wait();
        }
        //첫번재 데이터 꺼내기
        Character ch = list.remove(0);
        try {
            Thread.sleep(1000);
            System.out.println(ch + "출고");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("재고 수량" + list.size());

    }
}


//생산자 스레드
class Producer extends Thread {
    private Product product;

    public Producer(Product product) {
        this.product = product;
    }

    public void run() {
        for (char ch = 'A'; ch <= 'Z'; ch++) {
            product.put(ch);
        }
    }

}

//소비자 스레드
class Customer extends Thread {
    private Product product;

    public Customer(Product product) {
        this.product = product;
    }

    public void run() {
        for (int i = 0; i < 26; i++) {
            product.get();
        }
    }
}

public class ProducerConsumerMain {
    public static void main(String[] args) {
        //공유 자원 생성
        List<Character> list = new ArrayList<>();

        Product product = new Product(list);

        //생산자 와 소비자 스레드 생성
        Producer producer = new Producer(product);
        Customer customer = new Customer(product);

        //thread start
        producer.start();
        customer.start();

    }
}
```

- 실행을 하면 소비자 스레드가 예외가 발생해서 중지가 된다.
    - product 의 list 에 데이터가 없는데 꺼낼려고 해서 발생하는 문제이다.
- 데이터 꺼내서 사용하는 메서드에서 데이터가 없으면 기다리라고 하고 데이터를 생성하는 메서드에서는 데이터를 생성하면 notify를 호출해서 wait중인 스레드를 수행하라고 해주면 된다

### Dead Lock

> 결고 발생할 수 없는 사건을 무한정 기다리는 것

- 동기화된 메서드 안에서 다른 동기화된 메서드를 호출하는 것 처럼 동기화 된 코드 안에 동기화 된 코드가 존재하는 경우에 발생하는 경우가 많음

### Semaphore

- 동시에 수행할 수 있는 스레드의 개수를 설정할 수 있는 클래스
- 공유 자원이 여러개인 경우 각각의 Lock 을 설정하게 되는데 이런 경우 모두 사용중이라는것을 알려면 모든스레드를 전부 조사해야한다
- 사용할 수 있는 Lock 을 관리하는 인스턴스를 하나 만들어서 사용하기 전에 사용할 수 있는 Lock 의 개수를 확인해서 수행하면 관리하기가 편리하다
- 공유자원을 사용할때 acur

```java 

package java_12_22;


import java.util.concurrent.Semaphore;

class SemaphoreThread implements Runnable {
    private Semaphore semaphore;
    private String message;

    public SemaphoreThread(Semaphore semaphore, String message) {
        this.semaphore = semaphore;
        this.message = message;
    }

    @Override
    public void run() {
        try {
            //공유 자원을 사용하기 전에 호출
            semaphore.acquire();
            System.out.println(message);
            Thread.sleep(10000);
        } catch (Exception e) {
        }
        semaphore.release();//공유자원해제

    }
}

public class SemaphoreMain {
    public static void main(String[] args) {
        //동시에 스실행될 수 있는 스레드의 개수를 설정하는ㅇ 클래 
        //동시에 여럭 ㅐ의 스레드가 수행될 상황에서
        //성능 저하를 막기위해서 
        Semaphore semaphore = new Semaphore(3);
        new Thread(new SemaphoreThread(semaphore, "1")).start();
        new Thread(new SemaphoreThread(semaphore, "2")).start();
        new Thread(new SemaphoreThread(semaphore, "3")).start();
        new Thread(new SemaphoreThread(semaphore, "4")).start();
    }
}
```