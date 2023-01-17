### 1-16 이어어서

### 특정 영화 정보를 가지고 영화 이미지 정보와 리뷰 개수 및 grade평균을 구해주는 메서드

```java
public interface repository {
    @Query("select m,mi,r" +
            " from Movie m left outer join MovieImage  mi on mi.movie =m" +
            " left outer join Review r on r.movie =m" +
            " where m.mno = :mno")
    List<Object[]> getMovieWithAll(@Param("mno") Long bno);
}
```

### Object type 은 무조건 형변환 !

### Entity @annotation

- @Transient
    - 해당 데이터를 테이블의 컬럼과 매핑 시키지 않는다.
        - 컬럼를 제외하기 위해 사용합니다

### 리뷰가져오기

### @EntityGraph

> 엔티티의 특성 속성을 같이 로딩하도록 표시하는 어노테이션

- 2개의 속성을 설정할 수 있는데 로딩 설정을 변경하고자 하는 속성의 이름을 배열로 명시하는
  attributePath와 어떻게 적용할 것인지를 결정하는 type(FETCH - 명시한 속성만 EAGER 가 되고 나머지는 LAZY)

```java
public interface test {
    //    @Query()
    //페이지가 없다하면 보통 이런식이다
    //페이지가 있으면 Pageable이들어간다
    //Pageable이 매개변수로들어가면 리턴 타입이 page로바뀐다
    @EntityGraph(attributePaths = {"member"}, type = EntityGraph.EntityGraphType.FETCH)
    List<Review> findByMovie(Movie movie);
}
```

### @EntityGraph (2) 리뷰가져오기

```java

public class test {

    @Test
    public void getReviews() {
        Movie movie = Movie.builder().mno(4L).build();
        System.out.println(reviewRepository.findByMovie(movie)
        );

        //여기서 review.getRivew나 뭐 가져오게되면 에러가발생한다 그이유는
        //lazy로 해놓았기때문이다
        //이럴땐 @Transaction을 사용하자
        //EntityGraph를 적용하여 @Transaction을 사용안해도됨 .
    }
}
```

### 회원 탈퇴 처리 (영화 정보삭제시의 무비이미지와 리뷰 삭제문제도 동일)

> 회원 정보를 삭제하는 경우 외래키로 연결된 Review 데이터를 어떻게 할 것이냐 하는 문제

- 같이 삭제하는 경우가 있고 외래키의 값을 null로 설정하는 방법이 있음
- 회원 정보가 없더라도 리뷰 데이터 자체가 의미를 같ㅇ는 경우라면 Null로 설정하는것이 바람직하고 리뷰 데이터 자체가 의미가 없다면 삭제를 한다.

### jpql을 이용한 업데이트

```java
public interface interfaces {
    @Modifying //jpql에서 수정하고싶을때
    @Query("update Review r set r.member.mid=null where r.member.mid=:mid")
    void updateByMember(@Param("mid") Long id);

}
```

- 회원 정보를 삭제할때는 회원정보를 삭제하고 리뷰정보를 삭제하면 에러가난다
    - 외래키를 참조하는 테이블의 데이터를 먼저 삭제하거나 수정하고 참조되는 테이블의 데이터를 삭제해야한다

### delete 나 업데이트할땐 transaction 을 적용하자

### 파일 업로드

> 파일 업로드 방법

- servlet 3버전부터 자체적인 파일 업로드 라이브러리 이용
- 별도의 파일 업로드 라이브러리를 이용

```yml
 # 파일 업로드를 위한 설정
 #      스프링 부트 프로젝트를 내장된 Tomcat을 이용해서 실행한다면 별도의 추가적인 라이 브러리 없이 #application.yml 파일을 수정하면 됨
 application.yml 파일에 설정 추가
 spring.servlet.multipart.enabled=true
 spring.servlet.multipart.location=/Users/adam/Documents/data
 spring.servlet.multipart.max-request-size=30MB
 spring.servlet.multipart.max-file-size=10MB

 spring.servlet.multipart.enabled:파일 업로드 가능 여부를 선택
 spring.servlet.multipart.location:업로드된 파일의 임시 저장 경로로 절대 경로로 입력해야 하므로 자신의 디렉토리 경로로 설정해야 함
 spring.servlet.multipart.max-request-size:한 번에 최대 업로드 가능 용량
 spring.servlet.multipart.max-file-size:파일 하나의 최대 크기
```

### 파일 컨트롤러

```java

@RestController
@Log4j2
public class UploadController {

    @PostMapping("/upload")
    public void uploadFile(MultipartFile[] upladFies) {
        for (MultipartFile uploadFile : upladFies) {
            //업로드 된 파일이름 
            String originalName = uploadFile.getOriginalFilename();
            //ie 는 파일이름이 아니고 전체 경로를 전송하기때문에 
            //마지막 \ 뒤부분만 추출 
            String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);
            log.warn("filename" + fileName);
        }
    }
}

```

### 서버에 파일 저장

> Spring 에서 제공하는 FileCopyUtils 클래스를 이용해도 되고 MultipartFile 클래스에 trasfetTo라는 메서드를 이용해도 되고 byte 배열을 직접읽어서 쓰기작업을해도가능

- application.yml 파일 변수 만들어보기

```yml
com:
  kjh:
    upload:
      path: /Users
```

```java
public class Controlelr {
    @Value("${com.kjh.upload.path") //이렇게하면 application.yml에서 설정한 값을가져올수있음
    private String uploadpath;
}
```

### 디렉토리 생성

```java
public class Controller {
    //날짜 별로 디렉토리를 생성해주는 메서드 
    private String makeFolder() {
        //오늘 날짜로 된 디렉토리 경로를 설정 
        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        String realUploadPath = str.replace("//", File.separator);
        File uplaodPathDir = new File(uploadpath, realUploadPath);

        if (uplaodPathDir.exists() == false) {
            uplaodPathDir.mkdirs();
        }
        return realUploadPath;
    }
}
```

### 실제 파일업로드 => 내가 설정한 Path => 파일저장

```java
public class Controller {
    @PostMapping("/uploadajax")
    public void uploadFile(MultipartFile[] uploadFiles) {
//        makeFolder();
        for (MultipartFile uploadFile : uploadFiles) {
            //이미지 파일이 ㅏㅇ니면 이미지 업로드 를 수행하지않는다
            if (uploadFile.getContentType().startsWith("image") == false) {
                log.warn("이미지파일아님");
                return;
            }
//실제 파일 이름 IE는 전체 경로가 들어오므로 마지막 부분만 추출
            String originalName = uploadFile.getOriginalFilename();
            String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);
            log.info("fileName: " + fileName);

            //디렉토리 생성
            String realUplaodPath = makeFolder();
            //UUID 생성
            String uuid = UUID.randomUUID().toString();

            String SaveName = uploadpath
                    + File.separator +
                    realUplaodPath +
                    File.separator
                    + uuid
                    + fileName;
            //파일 업로드 성공
            Path savePath = Paths.get(SaveName);
            try {
                uploadFile.transferTo(savePath);
            } catch (IOException e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
    }
}
```

### 정훈 메모

- @RestController 와 @Controller 의 차이 점
    - @Controller 는 주로 View를 반환하기 위해 사용한다 또는 JSON데이터를 반환하기에도 사용한다
    - @RestController 는 @ResponseBody가 추가되었다 .
    - 하지만 ? @Controller는 View를 반환한다하고 @RestController는 객체나 데이터 JSON 등등으로 응답을할수있다 근데 @Controller도 객체로 반환 또는 JSON으로 데이터를
      반환할수있다고 보았는데 차이점 ?
- 트랜잭션을 사용하는 정확한 시점


### 영화 데이터에 대한 처리를 위한 서비스 인터페이스를 생성하고 삽입 메서드를 선언 



### select 를 두번이상하게되면 꼭 transaction을 붙여주자 