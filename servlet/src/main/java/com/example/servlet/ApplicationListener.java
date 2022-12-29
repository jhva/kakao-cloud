package com.example.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebListener
public class ApplicationListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    public ApplicationListener() {

    }
    //웹서버가 구동될 때 호출되는 메서드
    @Override
    public void contextInitialized(ServletContextEvent event) {
        System.out.println("서버시작");
    }

    //웹 서버가 종료될 때 호출되는 메서드
    @Override
    public void contextDestroyed(ServletContextEvent event) {
        System.out.println("서버종료 ");
    }

}
