<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>

<!-- jstl 사용을 위한 태그 라이브러리 설정 : JSP에서만 사용 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring MVC</title>
<!-- link는 파일의 위치 기준이 아니고 URL 기준입니다. -->
<link rel="stylesheet" href="./css/style.css" />
</head>
<body>
	<a href="item.xls">엑셀 다운로드</a><br/>
	<a href="item.pdf">PDF 다운로드</a><br/>
	<a href="itemlist.json">json 다운로드</a><br/>
	<a href="item.csv">텍스트 출력</a><br/>
	<a href="itemlistrest.json">json 다운로드</a><br/>
	<a href="exception">예외 발생</a><br/>
	
	<a href="message">메시지 출력</a><br/>
	
	<div align="center" class="body">
		<h2>상품 목록</h2>
		<table border="1">
			<tr class="header">
				<th align="center" width="80">아이디</th>
				<th align="center" width="320">상품 이름</th>
				<th align="center" width="100">가격</th>
			</tr>
			<c:forEach var="item" items="${list}">
				<tr class="record">
					<td align="center" width="80">${item.itemid}</td>
					<td align="left" width="320">
					<a href="detail/${item.itemid}">${item.itemname}</a></td>
					<td align="right" width="100">${item.price}원</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>




