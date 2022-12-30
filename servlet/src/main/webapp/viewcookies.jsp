<%@ page import="java.net.URLDecoder" %><%--
  Created by IntelliJ IDEA.
  User: kimjeounghoun
  Date: 2022/12/30
  Time: 9:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    //쿠키 전부 다 가져오기
    Cookie[] cookies = request.getCookies();
    for (Cookie cookie : cookies) {
        String value = URLDecoder.decode(cookie.getValue(), "UTF-8");
        System.out.println(value);
%>
<%=cookie.getName()%>: <%=value%>
<%
    }
%>

</body>
<script>
    //유효 시간 설정
    let expire = new Date();
    expire.setDate(expire.getDate() + 60 * 60 * 24);
    //쿠키 모양 생성
    let cookie = "nickname" + "=" + encodeURI("군계") + "; path=/";
    //유효시간 설정
    cookie += ";expires=" + expire.toString() + ";";
    document.cookie = cookie;
</script>
<script>
    //모든 쿠키 읽기
    let cookieData = document.cookie;
    let start = cookieData.indexOf("nickname");
    let cValue = "";
    if (start != -1) {
        //찾았다면
        start += "nickname".length;
        let end = cookieData.indexOf(";", start);
        if (end === -1) {
            end = cookieData.length;
        }
        console.log(cookieData.substring(start, end));

    }

</script>
</html>
