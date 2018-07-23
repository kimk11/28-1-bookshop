<!-- 07.23 송원민  / 책 소개를 하는 화면 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.BookIntroService" %>
<!DOCTYPE html>
<%
	int bookNo = Integer.parseInt(request.getParameter("bookNo"));	
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>책 소개 입력 화면</title>
	</head>
	<body>
		<h3>책의 줄거리</h3><br>
		<form action="<%=request.getContextPath()%>/member/bookIntro/insertBookIntroAction.jsp" method="post">
			<input type="hidden" name="bookNo" value="<%=bookNo %>">
			<textarea name="bookIntroContent" cols="100" rows="20"></textarea><br>
			작성자 : <input type="text" name="introWriter">
			<input type="submit" value="등록">
		</form>
	</body>
</html>