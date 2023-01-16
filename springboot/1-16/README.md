## 1-12 이어서 진행

### querydsl springboot 2.6.x

```groovy
buildscript {
    ext {
        queryDslVersion = "5.0.0"
    }
}
plugins {

    id "com.ewerk.gradle.plugins.querydsl" version "1.0.10"
}
configurations {
    compileOnly {
        extendsFrom annotationProcessor
    }
}
repositories {
    mavenCentral()
}
dependencies {
// QueryDSL
    implementation "com.querydsl:querydsl-jpa:${queryDslVersion}" implementation "com.querydsl:querydsl-apt:${queryDslVersion}"
}
def querydslDir = "$buildDir/generated/querydsl"
querydsl {
    jpa = true
    querydslSourcesDir = querydslDir
}
sourceSets {
    main.java.srcDir querydslDir
}
configurations {
    compileOnly {
        extendsFrom annotationProcessor
    }
    querydsl.extendsFrom compileClasspath
}
compileQuerydsl {
    options.annotationProcessorPath = configurations.querydsl
}

```

### spring boot 3.x querydsl

```groovy
dependencies {
    implementation 'com.querydsl:querydsl-jpa:5.0.0:jakarta' annotationProcessor "com.querydsl:querydsl-
    apt:
    $ { dependencyManagement.importedProperties['querydsl.version'] } : jakarta " annotationProcessor " jakarta .
    annotation:
    jakarta.annotation - api " annotationProcessor " jakarta .
    persistence:
    jakarta.persistence - api "
}
```

### commentServiceImpl 만들기

```java
public class CommentServiceImpl implements CommentService {
    @Autowired
    public CommentService commentService;

    @Test
    public void reigster() {
        CommentDTO dto = CommentDTO.builder()
                .text("댓글 텍스트")
                .replyer("user1")
                .bno(27L)
                .build();
//        commentService.register(dto);
        System.out.println(commentService.register(dto));
    }


    @Test
    public void testGetList() {
        List<CommentDTO> list = commentService.getList(27L);
        list.forEach(dto -> System.out.println(dto));
    }
}
```

### M:M 관계

> 테이블의 하나의 행이 다른 테이블의 여러 개의 행과 매핑이 되고 반대로 상대방 테이블에서 하나의 행이 다시 여러 개의 행과 매핑이 되는 관계

- 쇼핑몰에서 회원 정보와 상품 정보의 관계
    - 한 명의 회원은 여러 종류의 상품을 구매할수있다.
    - 하나의 상품은 여러 회원이 구매가능하다
- 영화 와 회원 사이의 리뷰 관ㄱ ㅖ
    - 하나의 영화에 여러 회원이 리뷰를 남길 수 있고 한명의 회원이 여러 영화에 리뷰를 남길 수 있다 .

### 관계형 데이터베이스에서 M:N 관계 표현

- 관계 형 데이터베이스는 다대다 관계를 표현하지 못함
- 다대다 관계의 경우는 1:M 관계 2개로 분할해서 표현
    - 별도의 테이블을 만들어서 처리
- 별도의 테이블을 만들고 이 테이블에서 2개의 테이블의 기본ㄴ키를 외래키로 소유

### JPA 에서의 M:M 관계 표현

- JPA 에서는 ManyToMany 로 다대다 관계 표현이 가능
    - 실제로는 거의 사용하지 않음
- 이렇게 만들면 한명의 회원이 하나의 영화에 대해서 리뷰를 작성하게되면 2곳에서 데이터 삽입 이루어진다
    - 양쪽데이터의 불일치가 발생할 가능성이 있음

### 다대다 매핑 기준

> 연결 테이블의 기본키 설정

- 양쪽 테이블의 기본키를 받아서 외래키로 생성하고 이 기본키의 조합을 기본키로 사용
    - 식별관계의 별도의 키를 만들어서 기본키로사용(비식별 관계)

### 영화 스프링

### 영화 정보 , 영화 이미지 정보 , 리뷰 개수 , 평점 평균

- 평이나 개수를 구할땐 group by를 해줘야함
- select * from movie;

# coalesce 는 review.score가 없으면 0으로 반환

```java

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    //테이블 세개일때
    //coalesce 를 해주게 되면
    //있으면 r.score, 없으면 0을 쓴다
    //개수를 셀때는 distinct를 줘야한다 
    @Query("select m,mi, avg(coalesce( r.score,0)),count(distinct r.reviewnum)" +
            " from Movie m" +
            " left outer join MovieImage mi on mi.movie=m" +
            " left outer join Review r on r.movie =m" +
            " group by m")

//    @Query()
    //페이지가 없다하면 보통 이런식이다
    //페이지가 있으면 Pageable이들어간다
    //Pageable이 매개변수로들어가면 리턴 타입이 page로바뀐다
    public Page<Object[]> getList(Pageable pageable);
}

```

#### movieImage entity
```java

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
/*
제외하는 이유는 지연생성이기 때문에
get을 하지 않은 상태에서 toString을 호출하면
nullPoisterException 이 발생하기때문이다
 */
@ToString(exclude = "movie") //movie빼고 라는 말이다 .toString할때
@Embeddable // 부모테이블을 만들때 이 속성의 값을 포함시켜 생성해주세요
/*
1대1 관계에서 많이사용되며
양쪽에 데이터를 모두 갖고싶을때
 */
public class MovieImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inum;

    private String uuid; // 파일 이름이 겹치지않기위해서

    private String imgName; //파일이름
    private String path;//하나의 디렉토리의 너무 많은 파일이 저장되지 않도록 업로드 한 날짜별로 파일을 기록하기 위한 디렉토리이름


    /*하나의 movie가 여러 개의 무비 이미지를 소유한다.
    데이터를 불러올때 movie를 불러오지 않고 사용할 때 불러온다
    외래키의 이름은 (안쓰게될시 외래키가 겹쳐진다) movie_mno
    해당 필드에대한 id
     */
    @ManyToOne(fetch = FetchType.LAZY)
    //나여러개 movieimage one
    //반대는 oneToMany
    private Movie movie; //하나의 이미지는 여러
}

```

### spring test

```java
@SpringBootTest

public class RepositoryTest {


  @Test
  public void isertMovie() {
    //임의로 데이터 넣어보기
    IntStream.rangeClosed(1, 100).forEach(i -> {
      Movie movie = Movie.builder()
              .title("무비제목" + i)
              .build();

      movieRepository.save(movie);

      int count = (int) (Math.random() * 5) + 1;
      System.out.println(count);

      for (int j = 0; j < count; j++) {
        MovieImage movieImage =
                MovieImage
                        .builder()
                        .uuid(UUID.randomUUID().toString())
                        .movie(movie)
                        .imgName("testImage" + j + ".jpg")
                        .build();

        movieImageRepository.save(movieImage);
      }
    });
  }

  @Test
  public void registerMember() {
    IntStream.rangeClosed(1, 100).forEach(i -> {
      Member member = Member.builder()
              .nickname("nickname" + i)
              .pw("11111")
              .email("email" + i)
              .build();
      memberRepository.save(member);
    });
  }

  @Test
  public void insertReview() {
    IntStream.rangeClosed(1, 200).forEach(i -> {
      //영화 번호
      Long mno = (long) (Math.random() * 100) + 1;
      //회원 번호
      Long mid = (long) (Math.random() * 100) + 1;

      Movie movie = Movie.builder()
              .mno(mno)
              .build();

      Member member = Member.builder().mid(mid).build();

      Review review = Review.builder()
              .movie(movie)
              .member(member)
              .score((int) (Math.random() * 5) + 1)
              .text("영화느낌.." + i).build();
      reviewRepository.save(review);
    });
  }

  @Test
  //조인 연습
  public void jointest() {
    Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "mno"));

    Page<Object[]> reesult = movieRepository.getList(pageable);
    for (Object[] objects : reesult.getContent()) {
      System.out.println(Arrays.toString(objects));
    }
  }
}

```