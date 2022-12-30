<%@ page import="java.lang.reflect.Member" %>
<%@ page import="com.example.servlet.MemberDTO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>로그인 처리 </title>
</head>
<body>
프로젝트 구조실습
<%--<h1>쿠키와세션--%>
<%--</h1>--%>
<br/>
<%
    Object logininfo = session.getAttribute("logininfo");
    if (logininfo == null) {

%>
<a href="login">로그인</a>
<%
} else {

%>
<%
    MemberDTO dto = new MemberDTO();
    dto.getMname();
%> 님 환영합니다 .<br/>
<a href="Logout">로그아웃</a>
<% } %>
<%--<a href="hello-servlet">Hello Servlet</a>--%>
<%--<a href="createcookie.jsp">쿠키</a>--%>
<%--<a href="deletecookie.jsp">쿠키삭제하기</a>--%>
<%--<a href="idsave.html">로그인</a>--%>
</body>
</html>