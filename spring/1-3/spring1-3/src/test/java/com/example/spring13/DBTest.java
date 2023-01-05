package com.example.spring13;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBTest {
    @Test
    public void testConnection() {
        try {
            //여기서 예외가 발생하면 이름을 틀렸거나 의존성 설정 잘못되었다
            Class.forName("org.mariadb.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/test", "root", "12345678");
            System.out.println(con);

            con.close();

        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());

        }
    }
}
