package com.example.servlet;

import com.example.servlet.service.MemberService;
import com.example.servlet.service.MemberServiceImpl;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(filterName = "LoginFilter")
public class LoginFilter implements Filter {


    private MemberService memberService;

    public LoginFilter() {
        super();
        memberService = MemberServiceImpl.getInstance();
    }

    public void destroy() {
    }

    public void init(FilterConfig config) throws ServletException {
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //request 와 response 형 변환
        HttpServletRequest req =
                (HttpServletRequest) request;
        HttpServletResponse res =
                (HttpServletResponse) response;

        //login 요청이 오면
        if (req.getRequestURI().equals("/Login/login")) {
            //쿠키 읽기
            Cookie[] cookies = req.getCookies();
            for (Cookie cookie : cookies) {
                //쿠키가 존재하면 쿠키의 값으로 로그인
                if (cookie.getName().equals("remember-me")) {
                    String uuid = cookie.getValue();
                    MemberDTO dto = memberService.login(uuid);
                    req.getSession().setAttribute(
                            "logininfo", dto);
                    //메인 페이지로 리다이렉트
                    res.sendRedirect("./");
                    return;
                }
            }
        }
        chain.doFilter(request, response);
    }
}
