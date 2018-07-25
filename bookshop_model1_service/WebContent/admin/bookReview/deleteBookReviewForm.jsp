<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.BookReviewService"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>delete Book Review Form</title>
</head>
<body>

<%
	int bookNo = Integer.parseInt(request.getParameter("bookNo"));
	int bookReviewNo = Integer.parseInt(request.getParameter("bookReviewNo")); // 리뷰 넘버값을 가져온다.
	int memberNo = (int)session.getAttribute("sessionMemberNo"); // 로그인을 성공한 멤버 넘버 세션값으로 멤버번호를 등록한다.
%>
		<form action="<%= request.getContextPath() %>/admin/bookReview/deleteBookReviewAction.jsp" method="post">
		
			<div>
				<label>리뷰 번호</label>
				<input type="text" name="bookReviewNo" value="<%=bookReviewNo%>" readonly>
			</div>
			<div>
				<label>멤버 번호</label>
				<input type="text" name="memberNo" value="<%=memberNo%>" readonly>
			</div>
			<input type="hidden" name="bookNo" value="<%=bookNo %>">
			<input type="submit" value="삭제 확인">
		</form>
</body>
</html>