package persistence;

import com.example.servlet.MemberVO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDAO {
    //싱글톤 패턴을 위한 코드 - Spring 에서는 필요 없음

    private MemberDAO() {
    }

    private static MemberDAO dao;

    public static MemberDAO getInstance() {
        if (dao == null) {
            dao = new MemberDAO();
        }
        return dao;
    }

    static {
        //초기화
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("드라이버 로드성공 ");
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    //데이터베이스 사용을 위한 속성
    private Connection connection;
    private PreparedStatement pstmt;
    private ResultSet rs;


    //데이터베이스 연결
    {
        try {
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/javapractice", "root", "12345678");
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }


    public MemberVO login(String mid, String mpw) {
        MemberVO vo = null;
        try {
            //수행할 sql 생성
            String sql = "select * from tbl_member where mid =? and mpw=?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, mid);
            pstmt.setString(2, mpw);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                vo = new MemberVO();
                vo.setMid(rs.getString("mid"));
                vo.setMname(rs.getString("mname"));

            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
        return vo;
    }

    public MemberVO login(String uuid) {
        MemberVO vo = null;
        try {
            String sql = "select * from tbl_member where uuid=?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, uuid);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                vo = new MemberVO();
                vo.setMid(rs.getString("mid"));
                vo.setMname(rs.getString("mname"));
            }

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
        return vo;
    }

    public void updateUUID(String mid, String uuid) {
        try {
            String sql = "update tbl_member set uuid=? "
                    + "where mid=?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, uuid);
            pstmt.setString(2, mid);
            pstmt.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
}

