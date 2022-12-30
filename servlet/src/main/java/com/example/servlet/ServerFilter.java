package com.example.servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebFilter(filterName = "*.jsp")
public class ServerFilter implements Filter {

    public ServerFilter() {
    }

    //필터가 파괴 될 때 호출되는 메서드
    public void destroy() {
    }


    //url에 해당하는 요청이 왔을때 호출되는 메서드
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //여기에 작성하면 Controller 가 처리하기 전에 수행
        System.out.println("처리하기 전 ");
        chain.doFilter(request, response);


        //controller 가 처리한 후
        System.out.println("처리 한 후");
    }

    //메모리 할당 후 처음 사용될 때 호출되는 메서드

    public void init(FilterConfig config) throws ServletException {
    }

}
