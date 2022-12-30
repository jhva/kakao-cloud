<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>로그인</title>
</head>
<body>
<%
    String error = request.getParameter("error");
    if(error != null){
%>
<div>아이디나 비밀번호가 틀렸습니다.</div>
<%
    }
%>

<form method="post">
    아이디<input type="text" name="mid" /><br/>
    비밀번호<input type="password" name="mpw"><br/>
    자동 로그인<input type="checkbox" name="auto"><br/>
    <input type="submit" value="로그인" />
</form>
</body>
</html>


