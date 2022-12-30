package com.example.servlet.controller;


import com.example.servlet.MemberDTO;
import com.example.servlet.service.MemberService;
import com.example.servlet.service.MemberServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private MemberService memberService;
    private static final long serialVersionUID = 1L;

    public LoginController() {
        memberService = MemberServiceImpl.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //webapp 디렉토리의 member 디렉토리의 login.jsp 로 포워딩
        req.getRequestDispatcher("/member/login.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //로그인 처리

        //파라미터 가져오기
        String mid = request.getParameter("mid");
        String mpw = request.getParameter("mpw");

        //자동 로그인 읽기
        String auto = request.getParameter("auto");
        //체크박스는 value가 없으면 체크하면 on
        //그렇지 않으면 null
        String uuid;
        if(auto == null) {
            uuid = null;
        }else {
            uuid = UUID.randomUUID().toString();
        }

        //서비스 메서드 호출
        MemberDTO dto = memberService.login(mid, mpw, uuid);
        //결과를 가지고 분기
        HttpSession session = request.getSession();
        if(dto == null) {
            session.invalidate();
            //로그인 페이지로 되돌아 가기
            response.sendRedirect("login?error=error");
        }else {
            session.setAttribute("logininfo", dto);
            if(uuid != null) {
                //쿠키를 생성해서 저장
                Cookie rememberCookie =
                        new Cookie("remember-me", uuid);
                rememberCookie.setMaxAge(60*60*24*2);
                rememberCookie.setPath("/");
                response.addCookie(rememberCookie);
            }
            //메인 페이지로 리다이렉트
            response.sendRedirect("./");
        }
    }

}
