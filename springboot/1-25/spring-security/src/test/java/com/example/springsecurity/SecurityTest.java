package com.example.springsecurity;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest

public class SecurityTest {


    @Autowired
    PasswordEncoder passwordEncoder;


    @Test
    public void testPasswordEncoder() {

        String password = "1111";
        //암호화
        String enpw = passwordEncoder.encode(password);

        System.out.println("enpw" + enpw);
        enpw= passwordEncoder.encode(password);
        System.out.println("enpw" +enpw);

        boolean result = passwordEncoder.matches(password,enpw);
        System.out.println(result);

    }
}
