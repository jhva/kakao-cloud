package java_12_28;

import java.rmi.server.ExportException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOImpl implements GoodDAO {
    @Override
    public List<Good> likeGood(String content) {
        List<Good> list = new ArrayList<Good>();

        try {


            preparedStatement = connection.prepareStatement(
                    "select * from goods where name like ? or manufacture like ?");

            preparedStatement.setString(1, "%" + content + "%");
            preparedStatement.setString(2, "%" + content + "%");

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Good good = new Good();
                good.setCode(resultSet.getString("code"));
                good.setManufacture(resultSet.getString("manufacture"));
                good.setName(resultSet.getString("name"));
                good.setPrice(resultSet.getInt("price"));
                list.add(good);
            }


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
        }
        return list;
    }

    ;

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

    @Override
    public Good getCode(String code) {
        //조회가 안된경우

        Good good = null;
        try {

            preparedStatement = connection.prepareStatement("select * from goods where code = ?");
            //?에 바인딩
            //? 바인딩할때 Index는 1로시작한다 (0부터 시작하는게아니다 )
            preparedStatement.setString(1, code);
            resultSet = preparedStatement.executeQuery();


            if (resultSet.next()) {
                good = new Good();
                good.setCode(resultSet.getString("code"));
                good.setManufacture(resultSet.getString("manufacture"));
                good.setName(resultSet.getString("name"));
                good.setPrice(resultSet.getInt("price"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
        }
        return good;
    }

    ;

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
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/test", "root", "12345678");
            System.out.println(connection);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
        }
    }


}
