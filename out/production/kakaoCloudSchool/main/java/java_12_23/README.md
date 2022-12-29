# java.io.File

> 파일에 대한 정보를 가진 클래스

- 파일을 생성하고 삭제하고 정보를 확인할 수 있도록 해주는 클래스
- 제일 중요한 작업들은 파일의 존재 여부 파악, 마지막 수정 날짜를 파악하는 작업이 될 수있다 .

### java.nio.file.path

> 파일 경로에 대한 클래스

- nio 패키지는 io 패키지의 최신 버전
- 생성
    - Paths.get(파일경로)
    - Paths.get(URI uri) -> URI 인스턴스는 URI.create("file://")
- File 객체로 변환
    - toFile 이라는 메서드를 호출하면 File 객체로 변환해서 사용하는것이 가능

# Stream

> 데이터를 운반하는데 사용되는 통로

- 분류
    - 입력 스트림(읽어오는 것) 과 출력스트림 (내보내는 것)
    - 바이트 스트림(바이트 단위로 입출력) 과 문자 스트림 (문자 단위로 입출력 - 양쪽의 인코딩 방식이 같아야하고, 양쪽의 인ㅇ코딩 방식이 다르면 바이트 스트림을 사용해야한다 )
- 특징 
  - FIFO 구조 
  - 단방향성 

### 바이트 스트림 
>  데이터를 입출력하기 위한 스트림 
- InputStream
  - 입력을 위한 스트림의 최상위 클래스로 추상 클래스 
  - 읽기 작업을 위한 메서드가 선언되어 있다 .
  - 읽기 작업을 위한 메서드가 선언되어 있다 
  - int available()
    - 읽을 수 있는 바이트 수 리턴 
  - void close()
    - 스트림 닫기 
  - int read
    - 한 바이트 일고 읽은 바이트를 정수로 리턴하는데 읽은 데이터가 없으면 -1 리턴 
  - int read(byte[] b)
    - b 만큼 읽어서 b에 저장하고 읽은 바이트 수를 리터낳는데 읽은데이터가 없으면 -1 
  - int read(byte [] b, int offset,int length)
    - offset부터 length 만큼 읽어서 b에 저장하고 읽은 바이트 수를 리턴하는데 일은 데이터가 없으면 -1을 리턴
  - long skip(long n)
    - n 만큼 건너뛰기 

### outputStream
>바이트 단위로 출력하기 위한 스트림의 최상위 클래스로 추상 클래스
- 쓰기 잦ㄱ업을 위한 메서드 선언
- void close()
- void write(byte [] b)
  - b를 버퍼에 기록 
- void write(bye [] b, int offset,int length) 
  - b에서 offset부터 length 까지 기록 
- void write(int b)
  - b를 문자로 변환해서 기록 
- void flush 
  - 버퍼의 내용을 스트림에 기록 

### 파일에 바이트 단위로 읽고 쓰기 위한 스트림 
- FIleInputStream 
  - 파일에서 읽어오기 위한 스트림
- FIleInputStream(String 경로)
  - 매번 파일에 새로 기록 
- FileOutPutStream (String 경로,boolean appendMode) 
  - 파일이 존재하는 경우 추가할 수 있도록 설정 
- FileOutPutSTream
  - 파일에 기록하기 위한 스트림 

```java
package java_12_23;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ByteStreamMain {
    public static void main(String[] args) {
//        try {
//            FileOutputStream fos = new FileOutputStream("./sample.bin");
//            String msg ="123";
//            fos.write(msg.getBytes());
//            fos.flush();
//        } catch (Exception e) {
//            System.out.println(e.getLocalizedMessage());
//        }
        try{
            FileInputStream fis =new FileInputStream("./sample.bin");;
            while(true){
                int x = fis.read();
                if(x == -1){
                    break;
                }
                System.out.println(x);
            }

        }catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}


```

### 버퍼를 이용하는 스트림 
- 읽고 쓰기를 할 때 버퍼를 사용하는 이유 
  - 파일에 읽고 쓰기를 하는 작업은 OS(운영체제)의 Native Method를 호출해서 수행한다
  - 버퍼를 사용하지 않으면 요청을 할 때마다 OS의 Native Method 를 호출하는데 여러번 작업을 수행하다보면
  OS의 효율이 떨어지게된다 
  - 버퍼에모아서 버퍼가 가득차거나 강제로 버퍼의 내용을 비우는 형태로 작업을 수행하게되면 Native Method 호출의 횟수를 줄일수있다 
- 자바에서는 바이트 단위 입력을 할 때는 이러한 버퍼링을 상룡할 수 있는 BufferedInputStream 클래스를 제공하고 바이트 단위 출력을 할 때는 버퍼링을 사용할 수있는 PrintStream 클래스를 제공 
- 이 클래스들의 생성자는 다른 스트림의 인스턴스를 받아서 인스턴스를 생성할 수 있도록 만들어져있다 
- PrintStream 에는 write 외에 prit와 같은 출력 메서드가 제공된다 
- 문자열의 경우 byte로 변환하지 않고 기록이 가능하다 
- 하나의 패킷은 128 


## 문자 스트림 
> 문자 단위로 읽고 쓰기 위한 스트림 

### Reader 
> 문자 단위로 읽는 스트림 중 최상위 클래스 - 추상클래스 
- 메서드 
  - int read() 
    - 하나의 문자를 읽어서 정수로 리턴 
      - 못읽으면 -1 
  - int read(char [] buf) 
    - buf 만큼 읽어서 읽은 개수를 리턴 
      - 못읽으면 -1 
  - int read(char [] buf , int offset,int length) 
    - buf 에 저장하는데 offset부터 length 만큼 읽어온다 
      - 읽은 개수를 리턴하고, 못읽으면 -1 
  - void close () 


### Writer 
> 문자 단위로 기록을 하는 스트림 중 최상위 클래스 - 추상클래스 
- 메서드 
  - void close()
  - void write(String str)
  - void write(String str,int offset,int length)
  - void flush()


### InputStreamReader 와 OutputStreamWriter
> 바이트 스트림을 문자 스트림으로 변환해ㅑ주는 스트림 클래스 
- 네트워크 프로그래밍에서는 바이트 스트림만 제공하는데 채팅 같은 서비스 나 웹의 문자열을 가져오는 서비스에서는 읽고쓰는 단위가 문자열이므로 이 클래스들을
이용해서 문자 스트림으로 변환해서 읽는게 편리하다


### FIleReader 와 FileWriter
> 파일에 문자 단위로 입출력하기 위한 스트림 


### BufferedReader 와 PrintWriter
> 버퍼를 이용해서 문자 단위로 입출력하기 위한 스트림
- BufferedReader 에는 줄 단위로 읽을 수 있는 readLine 메서드가 추가로 제공됨 


### 실습 
> 파일을 만들고 써서 로그남기기 
```java
package java_12_23;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChacterStreamMain {
  public static void main(String[] args) {
    //오늘 날짜로 파일명 생성 : 2022-12-23.log
    String directory = "C:\\Users\\user\\Documents\\log";

    //오늘 날짜 문자열만들기
    Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String filename = sdf.format(date);
    //파일경로만들기
    String path = String.format("%s%s%s", directory, filename, ".log");
    try (PrintWriter pw = new PrintWriter(new FileWriter(path, true))) {
      pw.println("안녕하세요 ㅎㅇ");
      pw.println("으이 시");
    } catch (Exception e) {
      System.out.println(e.getLocalizedMessage());
    }


    try(BufferedReader br =new BufferedReader(new FileReader(path))){
      while(true){
        String str =br.readLine();
        if(str == null){
          break;
        }
        //숫자가 아닌 문자열을 숫자로 변환하면
        //NumberFormatException 발생
        //web에서 파라미ㅓ는 무조건 문자열 
        //파라미터를 숫자로 변환하는 경우 발생할 수 있는 예오 ㅣ
        System.out.println(Integer.parseInt(str));
      }
    }catch (Exception e) {
      System.out.println(e.getLocalizedMessage());
      e.printStackTrace();
    }
  }
}
```


### properties file 
> 속성과 값을 쌍으로 저장하는 텍스트 파일 
- 용도 
  - 처음 한 번 설정하면 변경되지 않고 사용되는 문자열을 저장하는 용도 
    - 환경설정이라고 생각하자 
```java
package java_12_23;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesMain {
    public static void main(String[] args) {
        //현재 디렉토리 위치
//        File f= new File ("./");
//        System.out.println(f.getAbsolutePath());
        File file = new File("C:\\Users\\user\\Documents\\kakaoCloudSchool\\java_study\\JAVA_Practice\\javapractice\\config.properties");
        try (FileInputStream fis = new FileInputStream(file)) {
            Properties properties = new Properties();
            properties.load(fis);
            System.out.println(
                    properties.getProperty("id")
            );

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
}

```
# Serializable
> 인스턴스를 스트림에 전송하기 위한 것 
- 스트림에서는 기본형 데이터 나 문자열만 전송이가능하다 
- 직접 작성한 클래스의 인스턴스는 기본적으로 네트워크를 통해서 인스턴스 단위로 전송을 모샇ㅁ 
- 스트림을 통해서 직접 작성한 클래스의 인스턴스를 전송하기 위한 기술일 Serializable
- 이것 때문에 응용 프로그램끼리 데이터 호환이 안되는 것입니다


### Serializable 이 가능한 클래스 생성
> Serializable 인터페이스를 implements 하면 모든 속성이 Serializable대상이된다 
- 속성 중에서 제외하고자 하는 경우는 앞에 trasient를 추가하면 된다 

### 인스턴스 단위로 읽고 쓰기위한 스트림 
- ObjectOutputStream, ObjectInputStream 을 이용해서 writeObject이고 읽는 것은 readObject입니다 

### 실습 
- 파일에 기록할 틀래스 
```java

package java_12_23;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class DataMain {
    public static void main(String[] args) {
        //인스턴스 단위로 기록할 수있는 스트림 생성
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("sample.dat"))){
            Data data = new Data(1,"adam","ㅅㅂ");
            oos.writeObject(data);
            oos.flush();
        }catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        }
    }
}

```


### log.txt 파일에서 가장 마지막 합계 구해보기 
- ip 별 접속 횟수 와 ip 별 트래픽 합계를 구해보세요 
- Map을 이용하는 데 Ip를 Map의 key를 사용하면 종복된 ip는 한 번만저장이된다



# Network
> 용어 
- Protocol
  - 통신을 하기 위한 규칙 
- TCP/IP
  - 인터넷 프로토콜 
- HTTP
  - Hyper Text Transfer Protocol
- HTTPS
  - Hyper Text Transfer Protocol over Secure Socket Layer
- FTP
  - 파일 전송 프로토콜 
- TELNET
  - 원격 
- IP
  - 인터넷 프로토콜 
    - IPv4 32 비트 주소 체계로 0.0.0.0 ~ 255.255.255.255
  - 사설 IP 대역 (외부에서 접속이 안되는것 구분 ip) - 접속이안된다 
    - 10.0.0.0 ~ 10.255.255.255 
    - 172.16.0.0 ~ 172.31.255.255,
    - 192.168.0.0 ~ 192.168.255.255
- IPv6 
  - 128비트 주소 체계 
- PORT 
  - 서비스를 구별하기 위한 번호 
  - 하나의 컴퓨터 안에서 Process 를 구별하기 위핞 번호 
  - 0 ~ 65535까지 사용가능 
  - 시스템 예약 
    - http(80) , https(443), ftp(20,21),SSH(22),Telnet(23)...
- 기본게이트웨이 가 죽으면 통신사에 저나해바야한다 ping으로 

### URI 
> 인터넷에 있는 자원을 나타태는 유일한 주소 


### Routing
> 최적의 경로를 찾는 것을 의미하는데 웹 프로그래밍에서 URL 과 요청방식에 따라 분리해서 요청을 처리하는것 


### Proxy 
> 클라이언트가 외부 네트워크에 요청을 하고자 하는 경우 네트워크 내부에서 처리할 수 있도록 해주는 컴퓨터 ㅓ시스템이나 프로그램

### PRC
> Remote Procedure Call - 원격 프로시저 호출 
- 다른 컴퓨터의 프로시저를 호출하는것 

### OpenAPI
> 누구나 사용할 수 있도록 공개된 API(라이브러리 나 데이터)


### REST 
> 소프트웨어 아키텍쳐의 한 형식 
- 일관성 있는 URL 사용 
- 