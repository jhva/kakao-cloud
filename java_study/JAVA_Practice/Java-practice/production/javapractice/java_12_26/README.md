# IP 확인 

```java
public class IPMain {
    public static void main(String[] args) {
        try{
            //나의 아이피 가져오기
            InetAddress localAddress =InetAddress.getLocalHost();
            //네이버 아이피가져오기

            InetAddress [] naver = InetAddress.getAllByName("www.naver.com");
            System.out.println(Arrays.toString(naver));
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
```


# socket 
> 네트워크 어댑터를 추상화 한 클래스 
- 종류 
  - StreamSocket 
    - TCP 통신을 위한 소켓 
  - DatagramSocket 
    - UDP 통신을 위한 소켓 

### socket 의 생성자 
- Socket()
- Socket(InetAddress addr, int port)
    - 상대방의 IP 와 port를 기재해서 연결준비 
- Socket(String addr, addr, int port)
    - 상대방의 IP와 port를 기재해서 연결준비
- Socket(InetAddress addr, int port,InetAddress addr, int localport)
    - 상대방의 IP 와 port를 기재해서 연결 준비

### 메서드 
- close()
- getInetAddress()
- getPort()
- getLocalAddress()
- getLocalPort()
- InputStream, getInputStream
- OutputStream getOutputStream()
    - 문자 단위로 통신을 하고자 하는 경우네는 스트림을 CharacterStream(InputStreamReader, OutputStreamWriter) 으로 변환하던지 바이트 단위로 받은 후 new String(바이트 배열, [인코딩 방식) 을 이용해서 변환 

### Web 문자열 socket 실습 

# TCP 통신 
### ServerSocket 
- TCP 요청을 받을 수 있는 소켓을 위한 클래스 

### 생성자 
- ServerSocket
- ServerSocket(int port) 
  - port번호로 요청을 받을 수 있는 소켓 생성 
- ServerSocket ( int port,int backlog)
  - port 번호로 요청을 받을 수 있는 소켓을 생성하는데 backlog는 동시에 접속할수 있는 개수 

### 메서드 
- socket accept()
  - 클라이언트의 접속을 대기하는 메서드로 클라이언트가 접속을 할 때 까지 코드를 블럭시키고 접속을 하면 다음으로 넘어가고 
클라이언트와 통신할수있는 socket을 리턴한다


### 통신과정
- Serversocket 을 생성해서 accept()를 호출 
- Socket을 생성해서 ServerSocket에 접속 


### UDP 통신 
- 비연결형 통신 
- DatagramSocket 클래스를 이용 
- 생성자 
  - DatagramSocket
    - 데이터를 전송하기 위한 소켓을 생성할 때 사용 
  - DatagramSocket(int port)
    - 데이터를 전송받기 위한 소켓을 생성할 때 사용 
  - DatagramSocket(int port, InetAddress addr)
    - 데이터를 전송받기 위한 소켓을 생성할 때 사용 

- 메서드 
  - close()
  - receive(DatagramPacket packet) 
    - 전송받기 
  - send
    - 전송하기 

- 데이터를 주고받기 위한 패킷 클래스의 새엇ㅇ자 
  - DatagramPacket(byte [] buf,int length)
    - 전송받기 위한 생성자 
      - 빈 배열을 대입 
  - DatagramPacket(byte [] buf, int length, InetAddress addr,int port) 
    - 전송을 하기 위한 생성자 
      - 내용이 있는 배열을 설정 

### DatagramPacket 
>클래스의 메서드 
- byte [] getData() 
  - 받은 데이터를 리턴
- int getLength()
  - 받은 데이터의 길이를 리턴 

### DatagramPacket 클래스의 메서드 
- byte[] getData 
  - 받은 데이터를 리턴 
- int getLength()
  - 받은 데이터의 길이를 리턴 

### 문자열과 바이트 배열의 변환 
- 문자열을 byte 배열로 변환 getBytes([인코딩 방식])
- byte 배열을 문자열로 변환 
  - new String (바이트배열[,인코딩 방식])

### 문자열 전송 
> 문자열을 전송받을 클래스를 등장



### UDP 실습 
```java
package java_12_26;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {
    public static void main(String[] args) {
        try (DatagramSocket dsoc = new DatagramSocket(7777)) {
            byte[] data = new byte[65536];
            //데이터를 전송받을 패킷 클래스의 인스턴스 생성
            DatagramPacket dp = new DatagramPacket(data, data.length);
            while (true) {
                //데이터 받기
                dsoc.receive(dp);
                //클라이언트 정보 확인 
                System.out.println(dp.getAddress());
                //받은메시지 출려ㅛㄱ
                System.out.println(new String(dp.getData()));
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}

```



## 통신 방식
- Unicast 
  - 일대일로 통신 
    - 상대방의 IP정보만 있으면 구현 가능 
- Brodcast
  - 일 대 전체의 토신 
    - IP 주소와 Subnetmask를 알아야 구현 가능하다 
- Multicast 
  - 일 대 그룹의 통신 - 멀티캐스트 대역의 IP를 알아야한다 .
  - IPv4 에서는 224.0.0.0 ~ 239.255.255.255 대역이 멀티캐스트 대역 
- Anycast
  - 가까운 것 과 통신 

# URL 통신 
> 직접 소켓을 생성해서 통신을 하는 방식을 저수준 통신이라고 부르는데 이방식은 효율은 좋지만 구현하기가 어렵다
- 솤켓을 직접 생성하지않고 라이브러리의 도움을 받아서 통신하는 방법을 고수준 통신이라고한다 
  - 가장 대표적인 방식이 URL 통신이다 
- 최근에는 Web에서도 저수준 통신을 할 수 있는 WebSocket등장 

## URL 클래스 
> 주소를 생성하기 위한 클래스 
- 생성자 
  - URL (String spec)
  - URL (String protocol , String host, int port,String file)
- 메서드 
  - 각각의 정보를 리턴하는 get메서드 
  - 연결을 해주는 메서드는 openConnection 인데 이메서드는 URLConnection 타입의 인스턴스를 리턴 

### URLConnection
> HttpURLConnection, HttpsUrlConnection, JarUrlConnection 클래스의 공통된 메서드를 소유한 추상 클래스 
- 강제 형 변환을 해서 사용 
  - 각종 옵션(ConnectTimeout, ReadTimeout,UseCashes,RequestProperty - 헤더,RequestMethod) 설정할 수 있는 set 메서드가 존재 
- 상태 코드 확인 
  - getResponseCode()
- 연결이 되면 사용할 수 있는 스트림을 리턴해주는 getInputStream 과 getOutputStream 이 존재 함.

### Thread 활용 
> 채팅처럼 데이터를 주고받는 애플리케이션이나 웹에서 다운로드처럼 오랜시간이 걸리는 작업은 Thread를 이용해서 실행하는것을 권장함
- 모바일에서는 네트워크 접속에 스레드를 사용하지않으면 실행이안되거나 마켓에 등록을해주지 않습니다
```java
package java_12_26;

public class AsyncStringDownload {
  public static void main(String[] args) {
    //스레드를 만들어서 당눌도ㅡ - 비동기처리
    ㅁ
    new Thread(){
      //스레드를 만들어서 당눌도ㅡ - 비동기처리
      // @Override
      public void run(){
          //얘내는 뭊곤 예외처리를해야함
        try{
            
        }catch(Exception e){
          System.out.println(e.getLocalizedMessage());
        }
      }
    }.start();
  }
}

```

### 문자열을 다운로드
```java
package java_12_26;

import java.net.URL;

public class ImageDownload {
    public static void main(String[] args) {
        new Thread(){
            public void run(){
                try{
                    String addr= "https://www.google.com/imgres?imgurl=https%3A%2F%2Fimg.etoday.co.kr%2Fpto_db%2F2022%2F11%2F600%2F20221108175829_1816470_1200_1800.jpg&imgrefurl=https%3A%2F%2Fwww.etoday.co.kr%2Fnews%2Fview%2F2190409&tbnid=Ze-_-YHfvk_khM&vet=12ahUKEwj3yNrwpZb8AhUqCqYKHQ9pAqQQMygNegUIARCnAQ..i&docid=8zujYjuCvpjUQM&w=600&h=900&q=%EC%9E%A5%EC%9B%90%EC%98%81&ved=2ahUKEwj3yNrwpZb8AhUqCqYKHQ9pAqQQMygNegUIARCnAQ";
                        //확장자추출
                    // .은 \와 함께 기재해야한다 . 
                    String [] ar= addr.split("\\.");
                    String ext =ar[ar.length-1];
                    System.out.println(ext);


                    URL url = new URL(addr);
                }catch (Exception e) {
                    System.out.println(e.getLocalizedMessage());
                }
            }
        }.start();
    }
}
```


### 파라미터 설정 
- GET 방식 
  - URL 뒤에 붙여서 전송하면 된다  
- 파라미터는 인코딩이 되어야 한다
- POST 방식 
  - 파일이 없는 경우
  - String [] data = {파라미터 값을 나열};
  - String [] dataName = {파라미터 이름나열 };
  - String lineEnd=;
  - Boundary 생성 
    - 항상 변한는 값을 설정하는 것이 좋다 
  - String boundary
    - UUID.randomUUID().toString()
- 파일이 있는 경우에 추가 
  - con.setRequestProperty("ENCTYPE","multipart/form-data");
  - con.setRequestProperty("Content-Type","multipart/form-data;boundary"= +boundary);
```java
String demlimiter = "--" + boundary +lineEnd;
StringBuilder postDataBuilder = new StringBuilder();
for(int i =0; i<data,kenghth){
    postDataBuilder.append(demlimiter);
        }
```

- 파일 파라미터 추가 
- String fileName = "파일이름";
- postDataBuilder.append(delimiter)

## kakao api 실습 

```java
package java_12_26;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class KaKaoSearch {
    public static void main(String[] args) {
//        URL url = new U
        //카카오도서검색api
        new Thread(new Runnable() {
            @Override
            public void run() {
                //url만들기

                try {
                    String apiurl = "https://dapi.kakao.com/v3/search/book?target=title";
                    apiurl += "&query=";
                    apiurl += URLEncoder.encode("노동청","utf-8");
                    URL url = new URL(apiurl);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    con.setUseCaches(false);
                    con.setConnectTimeout(30000);
                    con.setRequestProperty(
                            "Authorization",
                            "KakaoAK b363b5afcbfbfcefd27f66bc97e322f6"
                    );
                    BufferedReader br =new BufferedReader(new InputStreamReader(con.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    while (true) {
                        String imsi = br.readLine();
                        if (imsi == null) {
                            break;
                        }
                        sb.append(imsi+"\r\n");
                    }
                    String reuslt= sb.toString();
                    System.out.println(reuslt);
                    br.close();
                    con.disconnect();
                } catch (Exception e) {
                    System.out.println(e.getLocalizedMessage());
                    e.printStackTrace();
                }


            }
        }).start();
    }
}


```


### 객체지향프로그래밍
- 객체지향 프로그래밍
  - 클래스를 만들고 그 클래스를 이용해서 인스턴스를 생성한 후 메서드를 호출해나가는방식
  - 클래스를 지원하는 모든 ㅡㅍ로그래밍 언어에서 가능 

- 객체 기반 프로그래밍 
  - 클래스 없이 객체 생성이 가능 
    - 자바스크립트 

- 함수형 프로그래밍 언어 
  - 인스턴스 나 객체 없이 함수를 호출하는 것이 가능 
  - 함수도 하나의 자료형 
    - 함수르 ㄹ데이터처럼 사용(변수에 대입이 가능하고 매개변수로도 사용이가능)
    - 자바는 함수형 프로그래밍을 지원하지 않음 

### lambda
- 자바에서 함수형프로그매잉을 지원하기 만든 문법
- 매서드의 매개변수로 매서드의 내용을 전달할 수 있도록 하기 위한문법
- Anonymous Function 을 생성하기 위한 문법
- 추상 메서드가 1개인 인터페이스에만 적용이가능 
- Runnable 인터페이스는 public void run() 메서드 1개만 소유 
- 예시
```java

public class main(){
  public static void main(String[] args) {
    new Runnable(){
        @Override
      public void run(){
            //원래 이런형태이지만 람다식으로하게되면
        }
    }.start();
  }
  // ()-> {내용} 이런식인 형태로 바꾸는게가능함
  // 위식에서는 run이라는 메서드가 피룡하지만 아래식에서는이름이없음 
  //매개변수가 1개일때는 ()생략가능
  //내욧ㅇ이 한줄인경우 {} ; 생략가능 
  //return 을 사용할수있는데 {} return 문만 있는 경우에는 return 생략가능
}
```

- 자바에서는 람다식을 주로 메서드의 매개변수로만 사용하는ㄴ데 그 이유는 람다식은 직접 호출이 안되고
인터페이스 변수에 담아서 호출해야한다 
```java
package java_12_26;

public class Lambda {
    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                    System.out.println("람다식");

                } catch (Exception e) {
                    System.out.println(e.getLocalizedMessage());
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

```

### 함수형 인터페이스 
> @FunctionalInterfasce 라는 어노테이션을 Interface 위에 작성하면 이 Interface 는 하나의  추상메서드만 을 소유해야한다
- 람다식으로 작성 가능한 인터페이스라는 것을 의미 
- 안드로이드 스튜디오 나 Intelli j 를 사용하는 경우 함수적 인터페이스를 
annoymous class를 이용하는 문법으로 작성하면 lambda 로 변경이된다 


### Runnable 인터페이스를 Lambda 식으로 사용 
> Runnable 인터페이스는 public void run() 이라는 메서드 1개만 소유 


### Comparator 인터페이스를 Lambda 식으로 표현 
> Comparator 인터페이스는 public int compare(T o1, T o2) 메서드를 1개를 소유한 인터페이스 


```java

package java_12_26;

import java.util.Arrays;
import java.util.Comparator;

public class ComparatorLambda {
    public static void main(String[] args) {
        String[] ar = {"야구", "배구", "세종대왕", "축구"};
      
      
      //람다식이아니였을경우 
//        Arrays.sort(ar, new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                //내림차순
//                return o2.compareTo(o1);
//            }
//        });
//        System.out.println(Arrays.toString(ar));

      
      
      //람다식일겨웅 
        Arrays.sort(ar, ((o1, o2) ->
                //이게되는이유 매개변수 두번째자리가 Comparator인터페이스를 받고있기때문에 된다 
                o1.compareTo(o2)
        ));
        
        //코드는 간결해졌지만 가독성이 많이 떨어진다 
        System.out.println(Arrays.toString(ar));

    }
}
```

### 매서드 이름을 매개변수로 전달
> 코드를 작성하지 않고 메서드를 직접 남기고자 하는 경우에 작성방법
- 클래스 메서드 
  - 클래스 메서드 
    - 클래스이름::메서드이름
  - 인스턴스 메서드 
    - 인스턴스이름::메서드이름 

- List의 forEach 메서드는 List를 순회하면서 매개변수로 대입된 메서드를 실행한다 
  - 이때 대입되는 메서드는 매개변수는 1개이고 리턴타입은 void 인 메서드여야한다 .


### 표준 람다 인터페이스
- Consumer
  - 매개변수가 1개이고 리턴이 없는 메서드를 소유한 인터페이스 
- Supplier 
  - 매개변수가 없고 리턴이 있는 메서드를 소유한 인터페이스 
- Function
  - 매개변수가 1개 있고 리턴이있는 메서드를 소유한 인터페이스 
  - 데이터 변환에 사용하는 인터페이스
- Operator 
  - 매개변수가 2개 있고 리턴이 있는 메서드를 소유한 인터페이스 
  - 연산을 수행해서 결과를 리턴하는 인터페이스 
- Predicate
  - 매개변수가 1개있고 리턴이 boolean 인 메서드를 소유한 인터페이스 
  - 필터링 할 때 사용하는 인터페이스

### Stream
- 참고 정훈 https://futurecreator.github.io/2018/08/26/java-8-streams/
- 배열이나 List 의 작업을 빠르게 수행하기 위한 추가된 API
- 작업 처리 과정은 생성 -> 중간 처리 -> 최종 처리 형태로 동작
  - 중간 처리는 여러 번 수행할 수있ㅅ브니다 
- 데이터를 복제해서 수행하므로 원본에는 아무런 영향이 없음 

### Stream API
> java.util.stream 패키지에서 제공 
- BaseStream 이라는 상위 스트림 아래 
  - Stream
  - IntStream
  - LongStream
  - DoubleStream 

### 생성 
> 컬렉션이나 배열의 메서드를 이용해서 기존 데이터의 스트림을 생성해서 사용한다
- Collection 인터페이스의 stream 이나 paralleStream 메서드를 이용해서 생성가능하고 Arrays.stream 메서드를 이용해서 배열에서 생성가능 

### 중간 연산 
> 생성된 스트림을 가지고 필터링, 매핑(변환),정렬,그룹화,집계(합계,최대,최소)등 작업을 수행
할수 있으며 여러가지를 조합해서 연산하는 것도 가능 
- skip(long n)
- limit(long n)
- distinct():equals 매서드로 비교 
- filter(매개변수가 1개이고 Boolean을 리턴하는 메서드) 
  - true인 데이터만 골라서 리턴 

```java
package java_12_26;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ReductionOperator {
  public static void main(String[] args) {
    List<String> list = Arrays.asList("123", "축구", "농구", "축구", "6768", "안철수", "안수리");

    Stream<String> stream = list.stream();
//        stream.forEach((tem)-> System.out.println(tem+"\t"));

    //2개 빼고 출력
//        stream.skip(2).limit(3).forEach((temp) -> System.out.println(temp+"\t"));


    //중복제거
//        stream.distinct().forEach((tem)-> System.out.println(tem+"\t"));

    //필터링
    // 매개변수가 1개이고  Boolean 을 리턴하는 함수를 대입
//
//        stream.distinct().filter(s -> s.charAt(0) == '축').
//                forEach((t) -> System.out.println(t + "\t"));

    //0으로 시작하는

//        stream.filter(s -> s.charAt(0) >= '아' && s.charAt(0) < "자").
//                forEach((t) -> System.out.println(t + "\t"));

  }
}


```

### map 
> 매개변수가 1개이고 리턴이 있는 메서드를 이용해서 데이터의 자료형이나 값을 변경하는 중간 연산 
- sorted 
  - 정렬을 해주는데 매개변수가 없으면 데이터의 compareTo 메서드를 이용해서 오름차순 정렬 하고 Comparator<T> 인스턴스를 대입하면 인스턴스의 
  메서드를 이용해서 오름차순 정렬을수행
- peek
  - 순회 