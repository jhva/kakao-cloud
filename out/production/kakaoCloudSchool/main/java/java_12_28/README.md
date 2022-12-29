# URL을 이용한 html 다운로드 한계

- 동적인 데이터(ajax로 가져오는 데이터) 나 iframe의 데이터를 가져올 수 없음
- 브라우저를 직접 조작할 수 있는 selenium 을 많이 이용
- selenium을 이용하면 브라우저 조작이 가능

### selenium

> 웹 앱을 테스트하기 위한 도구

### 준비

- 브라우저
- 브라우저 버전에 맞는 드라이버

# java에서 mariadb연결해서 사용하기

```java
package java_12_28;

import java.sql.Connection;
import java.sql.DriverManager;

public class MariaDB {
    public static void main(String[] args) {
        //데이터 베이스 로드
        //드라이버 의존성을 설정하지 않거나 클래스 이름이 틀리면 예외발생
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("드라이버 로드 성공");
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }

        //데이터베이스 접속 java.sql.Connection
        try (Connection con = DriverManager.getConnection(
                "jdbc:mariadb://0.0.0.0",
                "root",
                "12345678")) {
            System.out.println(con);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
        }
    }
}

```

### 데이터 베이스 접속정보를 별도의 파일에 작성

- java나 spring에서는 이러한설정을 properties나 xml파일에 많이함

### properties파일을 이용한 데이터베이스 연결

```java

package java_12_28;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;


public class MariaDB {
    public static void main(String[] args) {
        //데이터베이스 접속에 필요한 정보 불러오기
        String driver = null;
        String url = null;
        String id = null;
        String password = null;


        //읽어올 파일생성
        File file = new File("/Users/kimjeounghoun/Jeounghoun/강의/kakaoCloudSchool/java_study/JAVA_Practice/javapractice/db.properties");

        try (FileInputStream is = new FileInputStream(file)) {
//            파일의 내용을 propertiesdp 저장
            Properties properties = new Properties();
            properties.load(is);

            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            id = properties.getProperty("id");
            password = properties.getProperty("password");
        } catch
        (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
        //데이터 베이스 로드
        //드라이버 의존성을 설정하지 않거나 클래스 이름이 틀리면 예외발생
        try {
            Class.forName(driver);
            System.out.println("드라이버 로드 성공");
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }

        //데이터베이스 접속 java.sql.Connection
        try (Connection con = DriverManager.getConnection(
                url,
                id,
                password)) {
            System.out.println(con);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
        }
    }
}

```

### 트랜잭션 사용

- connection 인스턴스를 가지고 setAutoCommit(boolean b) 로 설정
- AutoCommit 을 해제한 경우는 commit 과 rollback 이라는 메서드로 트랜잭션을 사용

### SQL 구문 실행

- 실행클래스
    - Statement
        - Connection 인스턴스의 createStatement()로 생성
        - 데이터 바인딩이 안됨
            - 보안성이 떨어져서 거의 사용하지 않음
    - PreparedStatement
        - Connection 인스턴스의 prepareStatement(String sql) 로 생성
            - sql 로 만들때는 ? 로 설정
                - set자료형(? 인덱스, 실제 데이터) 메서드를 호출해서 ? 에 데이터를 바인딩
                - SQL실행은 int executeUpdate() 와 ResultSet executeQuery()를 호출
                - 날짜 및 시간의 경우 날짜는 java.sqlDate 와 시간은 java.sql.time그리고 날짜 시간을 모두 설정할때는 java.sql.Timestamp
                - 파일의 내용은 blob사용
    - CallableStatement
        - 프로시저를 수행하는데 Connection 인스턴스의 getCall(String sql) 로 생성
        - ResultSet executeQuery()로 실행
- 결과 사용 (ResultSet)
- next()
    - 다음 데이터가 있으면 true없으면 false 리턴
    - get자료형 (컬럼이름 이나 컬럼의 인덱스)
        - 컬럼 이름이나 인덱스에 해당하는 컬럼의 값을 리턴
    - 자료형을 String 으로 설정하면 모든 데이터를 전부 받아올수있다

## DTO & DAO 패턴

- DTO(Data Transfer Object) pattern
    - 여러 개의 데이터를 하나로 묶기 위해서 사용하는 패턴
- DAO(Data Access Object) pattern
    - 데이터를 연동하는 로직을 별도의 클래스로 만들어서 처리
- Singleton Design Pattern
    - 클래스의 인스턴스를 1개만 생성할 수 있도록 하는 Design Pattern (클래스를 설계하는 방법)
    - Server에서 작업을 처리하는 클래스(서버에서는 동시에 처리하는 것이 아니고 대부분 Multi Thread를 이용)나 공유 자원을 소유하는 클래스를 이 디자인패턴으로 디자인 함

- Template Method Pattern
    - 인터페이스에 메서드의 모양을 만들고 이 인터페이스를 implements 한 클래스를 만들어서 실제 내용을 구현하는 패턴

### 싱글톤패턴을 활용한 데이터베이스 연동 sql구문  실습

```java
package java_12_28;

import java.sql.*;

public class DAOImpl implements GoodDAO {

    //싱글톤을 만들기 위한 코드 - 안중요
    // 외부에서 인스턴스 생성을 못하도록 생성자를 private으로 설계
    private DAOImpl() {
    }

    //참조를 저장할 변수를 static으로 생성
    private static GoodDAO goodDAO;


    //변수가 null이면 생성후 리턴하고 null이 아니면 바로 리턴
    public static GoodDAO getInstance() {
        if (goodDAO == null) {
            goodDAO = new DAOImpl();
        }
        return goodDAO;
    }

    private Connection connection; //데이터베이스 연결

    private PreparedStatement preparedStatement; // sql 실행하는거

    private ResultSet resultSet; // select 구문의 결과

    //static 초기화 - 클래스가 로드 될때 1번만 수행
    //static 속성만 사용 가능
    static {
        //사용하고자 하는 데이터베이스 드라이버 로드
        try {
            Class.forName("org.mariadb.jdbc.Driver");
//            System.out.println("드라이버 로드 성공");
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }


    // 초기화 - 생성자를 호출할 때 마다 먼저 호출한다
    // 이영역은 init이라는 메서드로 생성
    // 모든 속성이 사용가능
    {
        //위에서 드라이버 는 클래스로드될때 1번만수행해주고
        try {
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306", "root", "12345678");
            System.out.println(connection);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
        }
    }
}

```

### 전체 데이터 가져오기

```java
public class DAOImpl implements GoodDAO {
    @Override
    public List<Good> getAll() {
        //리스트가 하나일경우 기본형을붙ㅇㅈㄴ다 ?
//        return null;
        List<Good> list = new ArrayList<Good>();
        //리스트는 조회할 데이터가 없더라도 인스턴스를 생성
        //조회할 데이터가 없다는 사실은 size 0이라는소리다

        try {

            //SQL 실행클래스의 인스턴스를 생성
            preparedStatement = connection.prepareStatement("select * from goods");
            //  쿼리 실행
            resultSet = preparedStatement.executeQuery();
            System.out.println("ResultSet" + resultSet);

            //데이터를 하나의 형씩 읽어서 DTO 객체로 변환해서 list에대입
            while (resultSet.next()) {
                Good good = new Good();
                good.setCode(resultSet.getString("code"));
                good.setManufacture(resultSet.getString("manufacture"));
                good.setName(resultSet.getString("name"));
                good.setPrice(resultSet.getInt("price"));

                list.add(good);
            }
            System.out.println(list);

        } catch (Exception e) {
            //내가 뭘 잘못했는지는 알아야하니깐 꼭 써주자
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
        return list;
    }

    //..
    //..

}
```


### 데이터삽입 
> DAO 인터페이스에가서 삽입 메서드 작성 
```java
package java_12_28;

import java.rmi.server.ExportException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOImpl implements GoodDAO {
    @Override
    public int insertGood(Good good) {
//        return 0;
        int cnt = 0;
        //삽입 작업이므로 트랜잭션을 고려
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement("insert into goods values(?,?,?,?)");


            preparedStatement.setString(1, good.getCode());
            preparedStatement.setString(2, good.getName());
            preparedStatement.setString(3, good.getManufacture());
            preparedStatement.setInt(4, good.getPrice());

            cnt = preparedStatement.executeUpdate();

            //서ㅏㅇ공하면 커밋
            connection.commit();

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            try {
                //삽입 실패시
                connection.rollback();

            } catch (Exception x) {
                x.printStackTrace();
            }
            e.printStackTrace();
        }

        return cnt;
    }
}
```



## mariadb & Mysql 백업 및 복원 

### 백업
- docker 에서 있는 데이텉베이스 백업 
  - 컨테이너 이름 확인 후 bash 진입
  - docker exec -it 컨테이너이름 bash 
  - mysqldump -h127.0.0.1 -p루트비밀번호 데이터베이스이름 > /tmp/파일명.sql
```
 mysqldump -h127.0.0.1 -p12345678 test > /tmp/test.sql
```

### 확인 
```
ls -al /tmp

exit 나가기 
```

### 파일을 로컬에 복사 
```
docker cp 컨테이너ID:/tmp/파일명.sql 복사할디렉토리 경로
docker cp mariadb:/tmp/test.sql /Users/kimjeounghoun
```
### 컨테이너로 복사  
- 실행중인 컨테이너으 ㅣ로컬 파일을 복사 
```
docker cp 복사할 파일 경로 컨테이너 
docker cp /Users/kimjeounghoun/test.sql mariadb:/tmp
```

### 복원 
```
mysql -h127.0.0.1 -p루트비밀번호 데이터베이스이름 /tmp/파일명
mysql -h127.0.0.1 -p12345678 adam < /tmp/test.sql
```

### 외부 접속 허용 
- bash shell 진입 
