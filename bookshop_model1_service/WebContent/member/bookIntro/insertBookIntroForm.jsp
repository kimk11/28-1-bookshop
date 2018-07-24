<!-- 07.23 송원민  / 책 소개를 하는 화면 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.BookIntroService" %>
<!DOCTYPE html>
<%
	//selectBookList.jsp(책 전체 리스트)에서 bookNo에 대한 값을 받아옴
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
			<!-- 굳이 책번호까지 화면에 나타낼 필요가 없다고 생각하여 hidden 처리 -->
			<input type="hidden" name="bookNo" value="<%=bookNo %>">
			<textarea name="bookIntroContent" cols="100" rows="20"></textarea><br>
			작성자 : <input type="text" name="introWrite">
			<input type="submit" value="등록">
		</form>
	</body>
</html>