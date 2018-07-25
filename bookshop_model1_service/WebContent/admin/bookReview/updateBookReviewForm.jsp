<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.BookReviewService"%>
<%@ page import="dto.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>update Book Review Form</title>
</head>


<body>
<%
	int bookReviewNo = Integer.parseInt(request.getParameter("bookReviewNo")); // 리뷰 넘버값을 가져온다.
	int memberNo = (int)session.getAttribute("sessionMemberNo"); // 로그인을 성공한 멤버 넘버 세션값으로 멤버번호를 등록한다.
	
	BookReviewService bookReviewService = new BookReviewService();

	BookReviewDTO bookReviewDTO = bookReviewService.selectBookReviewService(bookReviewNo);
	
%>
		<form action="<%= request.getContextPath() %>/admin/bookReview/updateBookReviewAction.jsp" method="post">
		
			<div>
				<label>책 번호</label>
				<input type="text" name="bookNo" value="<%=bookReviewDTO.getBookNo()%>" readonly>
			</div>
			<div>
				<label>리뷰 번호</label>
				<input type="text" name="bookReviewNo" value="<%=bookReviewDTO.getBookReviewNo()%>" readonly>
			</div>
			<div>
				<label>멤버 번호</label>
				<input type="text" name="memberNo" value="<%=memberNo%>" readonly>
			</div>
			<div>
				<label>리뷰 내용</label>
				<input type="text" name="bookReviewContent" value="<%=bookReviewDTO.getBookReviewContent()%>">
			</div>
			<input type="submit" value="리뷰  등록">
		</form>
</body>
</html>