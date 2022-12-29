<%--
  Created by IntelliJ IDEA.
  User: kimjeounghoun
  Date: 2022/12/29
  Time: 3:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    //파라미터 읽기
    String first = request.getParameter("first");
    String second = request.getParameter("second");

    int result = Integer.parseInt(first) + Integer.parseInt(second);


    //결과 저장
    request.setAttribute("result", result);
    session.setAttribute("result", result);
    application.setAttribute("result", result);

    System.out.println("처리 페이지 ");


    //포워딩
    request.getRequestDispatcher("output.jsp").forward(request, response);

%>
