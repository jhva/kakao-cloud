# 스트림

- 최종연산
    - 매칭
        - allMatch,anyMatch,noneMatch
    - 집계
        - count , findFirst,max,min,average,reduce,sum
- Optional 이 붙는 자료형으로 리턴되는 경우는 null 일 수 있으므로 null 여부를 확인하고 사용하라고 하는 것 이다
- 루핑
    - forEach
- 수집
    - collect
        - Collectors.to자료형() 을 대입하면 중간 연산의 결과를 자료형(List,Set,Map)으로 변환
        - Collectors.groupingBy(그룹화할 함수, 집계함수) 를 대입하면 그룹화해서 결과를 Map으로 리턴을 한다
        - Map의 키는 자동으로 그룹화할 함수의 값입니다

### Collect 변환

```java

public class main() {
    public static void main(String[] args) {
        ...
        ...
        //남자만 추출해서 List 로 변환
        List<StudentVO> manList = list.stream().filter(student -> student.getGender().equals("남자"))
                .collect(Collectors.toList());

        System.out.println(manList);
    }
}
```

### 실습 VO

```java

public class Main {
    public static void main(String[] args) {
        //Student 클래스의 list 생성
        List<StudentVO> list = new ArrayList<StudentVO>();

        list.add(new StudentVO(1, "개객", "남자", 23));
        list.add(new StudentVO(2, "바박2", "남자", 55));
        list.add(new StudentVO(3, "개3", "여자", 66));
        list.add(new StudentVO(4, "개4", "여자", 45));
        list.add(new StudentVO(5, "개5", "남자", 12));

        //score 의 합계
        //student 를 student.getScore 메서드의 결과를 이용해서 정수로 변환
        int sum = list.stream().mapToInt(StudentVO::getScore).sum();
        System.out.println("점수합계" + sum);


        //평균 구하기
        //Optional 붙는 자료형은 null 여부를 확인 후 사용
        OptionalDouble avg = list.stream().mapToInt(StudentVO::getScore).average();

        //값이 존재한다면

        if (avg.isPresent() == true) {
            System.out.println("평균 " + avg.getAsDouble());
        } else {
            System.out.println("평균을 구할 수 없음 ");
        }

        //reduce 집계
        //매개변수가 2개이고 리턴이있는 메서드를 제공

        // 처음 2개의 데이터를 가지고 메서드를 호출해서 결과를 만들고
        //그 다음부터는 결과 다음 데이터를 가지고 메서드 호출
        sum = list.stream().mapToInt(StudentVO::getScore)
                .reduce(0, (o1, o2) -> o1 + o2);

        System.out.println(sum + "reduce 집ㄱㅖ ");
    }
}
```

### 그룹핑  stream

```java
 public class main() {
    public static void main(String[] args) {

        //그룹핑 
        Map<String, Double> genderMap = list.stream()
                .collect(Collectors.groupingBy(StudentVO::getGender,
                        Collectors.averagingDouble(StudentVO::getScore)));


        System.out.println(genderMap.get("남자"));

    }
}
 ```

### 병렬처리

- 용어
    - Multi Processing
        - 2개 이상의 프로세스가 동작 중인 것
    - Multi Threading
        - 2개 이상의 스레드가 동작 중인 것
    - Parellel Processing
        - 동시에 작업을 처리
    - 데이터 병렬성
        - 많은 양의 데이터를 나누어서 처리
    - 작업 병렬성
        - 동시에 발생하는 작업을 각각의 스레드에서 병렬로 처리 (Web Server)

### 스트림 API에서의 병렬 처리

- 데이터 병렬성
- 스트림을 만들 때 parallelStream 을 호출하거나 스트림을 만든 후 parallel()을 호출하면 데이터를 나누어서 병렬로 처리를 합니다

### 일반 병렬처리와 스트림 병렬처리 의 차이

```java

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ParallelMain {
    public static void main(String[] args) {
        //정수 리슽르 생성
        List<Integer> list = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7);


        long start = System.currentTimeMillis();
        //일반 스트림으로 1초씩 쉬면서 출력
        list.stream().forEach(imsi -> {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
                e.printStackTrace();
            }
        });
        long end = System.currentTimeMillis();
        System.out.println("걸린시간" + (end - start));


        //스트림에서 병렬처리하기

        start = System.currentTimeMillis();
        //일반 스트림으로 1초씩 쉬면서 출력
        list.stream().parallel().forEach(imsi -> {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
                e.printStackTrace();
            }
        });
        end = System.currentTimeMillis();
        System.out.println("걸린시간" + (end - start));
    }
}
```

# Java GUI Programming

### AWT

- 운영체제의 자원을 사용하는 방식
- 무거워서 지금은 거의 사용하지 않음

### Swing

- jdk 자원을 사용하기 때문에 AWT 보다는 가벼움
- 디자인이 어려움

### Java FX

- 스타일 설정이 가능해지고 다양한 컴포넌트를 제공

### Open Source 활용

> 소스코드를 누구나 열람할 수 있고 사용할 수 있도록 만든 것을 오픈소스라고 함.

- 장점
    - 편리성
        - 직접 만들려고 하면 시간과 비용이 많이 소모됨
        - 성능이 우수할 가능성이 높음
        - 버전이 높다면 신뢰성도 높음

### Java Open Source Project

- Hadoop
    - 아파치 재단에서 만든 오픈 소스 분산 처리 시스템
- Cassandra
    - Database
        - NoSQL
- Lucene
    - 검색 엔진 플랫폼
- Maven
    - Java Build 도구
        - Gradle
        - jenkins
        - Apache Web Server
        - Log4j
        - Common Project
            - 자바의 다양한 유틸

### Maven

- build
    - Source Code 를 Compile 하고 최종적인 실행 파일로 만드는 작업
    - 일반 Application 은 jar 파일로 생성하고 Web Application 은 war그리고 android는 apk 파일을 생성
    - jdk/bin 디렉토리에 있는 명령어로 작업을 수행할 수 있음
    - 외부 라이브러리 를 이용해서 build script에 빌드할 내용을 기술하고 수행

### Gradle

> CI 도구에서 자동으로 빌드를 하고 정기적으로 빌드를 할 수 있는지 확인 한 후 Deploy 까지 자동으로 수행하는 경우도 있음

- Jenkins 가 많이 사용됨

### Build tool

- Ant
- Maven
- Gradle
- Jenkins

### maven

- POM (Project Object Model - pom.xml)
    - POM 이라는 것에 기초를 두고 빌드 , 테스트 , 도큐먼테이션,성과물의 배치등의 라이브 사이클 전체를 관리하는 도구

### pom.xml

> maven 설정 파일

- repositories
    - 다운로드 받을 저장소 설정
        - Oracle 을 사용하는 경우 설정
- dependencies
    - 외부 라이브러리의 의존성 설정

### Maven 프로젝트 생성

- Maven 프로젝트를 생성
- 기존 프로젝트를 Maven 프로젝트로 변환

### 의존성 설정 - 외부 라이브러리 사용

> Java Application

- Build Path 에 사용하고자 하는 경우 라이브러리를 추가해주어야한다
- Java Web Application
    - WEB-INF/lib
        - 디렉토리에 라이브러리를 추가해주어야 한다
- build tool 을 사용하는 경우
    - 설정 파일에 작성만 하면 다운로드를 받아서 로ㅓㄹ에 저장을 하고 build를 할때 그 라이브러리를 프로젝트에 포함시켜 줘야한다.

[//]: # (### 외부라이브러리 사용시 사용방법 from Maven )

## JUnit

> 단위 테스트를 수행해주는 프레임워크

- 참고
    - https://donghyeon.dev/junit/2021/04/11/JUnit5-%EC%99%84%EB%B2%BD-%EA%B0%80%EC%9D%B4%EB%93%9C/
- TDD (테스트 주도 개발) 에서 가장 많이 사용되는 테스트 라이브러리

### 테스트 방법

- TestClass 로 부터 상속받는 클래스를 생성
- Object 클래스로부터 상속받는 클래스를 만들고 테스트 하고자 하는 메서드 위에 @Test를 추가

### 결과 테스트

- assetThat ,assertEquals
    - 메서드의 첫번째 인수로 실제 값을 그리고 두번째 인수에 기대하는 값을 기재해서 호출하는데 이 때 양쪽의 값이 다르면 AssetionError 예외가 발생

```java
package java_12_27;

import org.junit.jupiter.api.DisplayName;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestApplication {


    @Test
    public void testMethod() {
        System.out.println(new Source().add(100, 200));
    }


    //이런 방법을 많이 사용함
    @Test
    public void testMethod2() {
        Source source = new Source();

        //메서드의 수행 결과 찾아오기
        int cnt = source.add(200, 300);

        //기대값과 비교
        Assert.assertEquals(cnt, 300);

        //cnt 가 현재 인수로넘기는 결과값 상으론 500 이나온다 
        // 밑에 Assert.assertEquals는 첫번째 인수는 결과값이랑, 두번째 인수로는 예상 기대값을 넣어주면 
        // 비교가 된다.
    }


}
```

- 실제 테스트를 수행할 때는 별도의 패키지에서 수행
    - 배포할때 제외하기 위해
    - 최근에 프레임워크들은 프로젝트를 생성할때 test를 위한 디렉토리를 제공하고 그 디렉토리에 작성한 내용은 빌드를 제외시킨다.

# CSV 활용

> Comma Seperated Values의 약자로 항목을 쉼표로 구분해서 만든 텍스트 파일로 확장자는 csv 최근에는 분할 할 수 있는 텍스트는 전부 CSV

- 대부분의 경우는 첫번재 라인의 데이터는 헤더 항목 ( 컬러의 이름) , 두번째 라인부터가 실제 데이터인 경우가 많음
    - 사용하는 이유는 <b>변하지 않는 고정적인 데이터를 제공하고자 할 때 주로 이용 </b>
- 외부라이브러를 이용해서 사용하는 경우가 많음
- super-csv 라는 외부라이브러리가 csv 읽고 쓰기를 편리하게 해준다

### csv실습 데이터읽고 , 객체에 데이터넣어서 출력해보기

```java

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CsvMain {
    public static void main(String[] args) {
        //text파일을 읽을땐 BufferedReader 스트림생성
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream("java_study/JAVA_Practice/javapractice/CSVtest.csv")
        ))) {
//            System.out.println(br); 문제없음


            //첫줄은 데이턱가 아니마로 첫줄을 배제하기 위한 변수
            boolean flag = false;

            //파싱한 겨로가를 저장히기 위한 list
            List<Player> list = new ArrayList<>();


            //한줄씩읽어보기
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                if (flag == false) {
                    flag = true;
                    continue;
                }
//                System.out.println(line+"line 프린트 "); // success

                // csv에 , 단위로 분할해보기
                String[] ar = line.split(",");
                Player player = new Player();
                player.setName(ar[0]);
                player.setAge(Integer.parseInt(ar[1]));
                String birth = ar[2];

                //문자열을 Date 타입으로 변환해서 대입
                //데이터 파싱
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = sdf.parse(birth);
                player.setDate(date);
                player.setEmail(ar[3]);
                player.setNickname(ar[4]);
                list.add(player);
            }
            StringBuilder sb = new StringBuilder();

            for (Player player : list) {
                sb.append(player + "\t");
            }
            System.out.println(sb);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}


```

### 라이브러릴통한 csv파일읽기

```java
public class main() {
    public static void main(String[] args) {

        // ...
        // ...
        Path path = new Path("파일경로적기 ");


        // ...
        // ...


        CellProcessor[] processors = new CellProcessor[]{
                new ParseDate("20222")
        };

        try (ICsvBeenReader =nwe) {

        }

        try {
            //csv vkdlfdlfrrh TMrl 

        }

    }
}
```

# JSON parsing

> JSON(JavaScript Object Notation)

- 자바스크립트의 데이터 표현법을 이용해서 데이터를 표현하는 무자열 서식
- Key 와 Value 형태로 (Object)객체를 생성하고 배열은 [] 안에 데이터를 나열
- 문자열은 큰 따옴표를 해야하고 key 는 무조건 문자열

### JAVA 에서 JSON 파싱

```java

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class JSONParsingMain {
    public static void main(String[] args) {

        //데이터 다운로드
        //다운로드 받은 문자열을 저장하기 위한 변수
        String json = null;
        try {
            //다운로드 받기 위한 URL을 생성
            //한글이 포함되어 있으면 그 부분은
            //URLEncoder.encode 메서드를 이용해서 인코딩 한 후 생성
            URL url = new URL(
                    "https://jsonplaceholder.typicode.com/todos");
            //URL에 연결
            HttpURLConnection con =
                    (HttpURLConnection) url.openConnection();
            //옵션 설정
            con.setRequestMethod("GET"); //요청 방식
            con.setConnectTimeout(30000); //접속 요청 제한 시간
            con.setUseCaches(false); //캐싱된 데이터 사용 여부

            //문자열을 읽기 위한 스트림 생성
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            //많은 양의 문자열 읽기
            StringBuilder sb = new StringBuilder();
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                sb.append(line + "\n");
            }
            json = sb.toString();
            System.out.println(json);

        } catch (Exception e) {
            System.out.println("데이터 다운로드 실패");
            System.exit(0); //프로그램 종료
            return;//main 종료
        }


        //다운로드 받은 데이터 파싱
        List<ToDoVo> list = new ArrayList<>();
        try {
            if (json != null) {
                //전체 문자열을 배열로 변환
                JSONArray ar = new JSONArray(json);
                //System.out.println(ar);

                //배열을 순회
                for (int i = 0; i < ar.length(); i++) {
                    //배열의 요소를 JSON 객체로 가져오기
                    JSONObject obj = ar.getJSONObject(i);
                    //System.out.println(obj);
                    ToDoVo vo = new ToDoVo();

                    //객체를 key를 이용해서 가져옵니다.
                    vo.setUserId(obj.getInt("userId"));
                    vo.setId(obj.getInt("id"));
                    vo.setTitle(obj.getString("title"));
                    vo.setCompleted(obj.getBoolean("completed"));

                    list.add(vo);
                }
            }
        } catch (Exception e) {
            System.out.println("파싱 실패");
            System.out.println(e.getLocalizedMessage());
        }


        //파싱한 결과를 사용 - 출력
        for (ToDoVo vo : list) {
            System.out.println(vo);
        }

    }
}

```

### JSON 생성

> Spring Framework 에서는 이 기능을 자동으로 수행

```xml

<!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-core -->
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-core</artifactId>
    <version>2.14.1</version>
</dependency>

<!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind -->
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.14.1</version>
</dependency>
```

### JSON 생성을 위한 클래스 생성 - JSONCreate
- 직접 수행하고자 하는 경우의 의존성 - jackson-core, jackson-bind 
```java

public class JSONCreate {

	public static void main(String[] args) {
		ToDoVO vo1 = 
			new ToDoVO(1, 1, "한글", true);
		ToDoVO vo2 = 
			new ToDoVO(2, 11, "영어", false);
		
		//저장할 JSON 파일 생성
		File file = new File("todo.json");
		//JSON 기록을 위한 인스턴스 생성
		ObjectMapper mapper = new ObjectMapper();
		try {
			//기록
			mapper.writeValue(file, Arrays.asList(vo1, vo2));
			System.out.println("기록 성공");
		}catch(Exception e) {
			System.out.println("기록 실패");
		}
	}
}
```

### 기타
- XML Parsing - 내장 클래스 이용
- HTML Parsing - jsoup 라이브러리 이용
- ajax 나 frame으로 만들어진 데이터를 가져오기 위해서는 selenium 을 이용


### 정훈 참고 jackson 
- https://ko-ko.tistory.com/5
- JSON.parse( JSON으로 변환할 문자열 ) : JSON 텍스트 → 자바스크립트 객체

- JSON.stringify( JSON 문자열로 변환할 값 ) : 자바스크립트 객체 → JSON 텍스트