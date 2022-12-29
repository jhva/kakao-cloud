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
    -  2개 이상의 프로세스가 동작 중인 것 
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
 package java_12_27;

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