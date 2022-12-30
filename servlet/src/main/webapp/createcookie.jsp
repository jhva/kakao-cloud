<%@ page import="java.net.URLEncoder" %><%--
  Created by IntelliJ IDEA.
  User: kimjeounghoun
  Date: 2022/12/30
  Time: 9:38 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>쿠키생성</title>
</head>
<body>
<%
    //value가 한글이 ㄴ경우는 인코딩을 해주어야한다

    Cookie cookie = new Cookie("name", URLEncoder.encode("군계", "UTF-8"));
    response.addCookie(cookie);//쿠키생성이되어서 저장이됨
%>
<a href="viewcookies.jsp">쿠키 보기</a>
</body>
</html>
