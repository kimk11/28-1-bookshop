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
	int bookReviewNo = Integer.parseInt(request.getParameter("bookReviewNo"));
	int memberNo = (int)session.getAttribute("sessionNo");
%>
		<form action="<%= request.getContextPath() %>/member/bookReview/deleteBookReviewAction.jsp" method="post">
		
			<div>
				<label>리뷰 번호</label>
				<input type="text" name="bookReviewNo" value="<%=bookReviewNo%>" readonly>
			</div>
			<div>
				<label>멤버 번호</label>
				<input type="text" name="memberNo" value="<%=memberNo%>" readonly>
			</div>

			<input type="submit" value="삭제 확인">
		</form>
</body>
</html>