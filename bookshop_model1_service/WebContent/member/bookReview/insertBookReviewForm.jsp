<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.BookReviewService" %>
<!DOCTYPE html>
<%
	int bookNo = Integer.parseInt(request.getParameter("bookNo")); // 책 번호를 가져온다.
	int memberNo = (int)session.getAttribute("sessionMemberNo"); // 로그인을 성공한 멤버 넘버 세션값으로 멤버번호를 등록한다.

%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>책 리뷰</title>
	</head>
	<body>   
        <form action="<%= request.getContextPath() %>/member/bookReview/insertBookReviewAction.jsp" method="post">
		책 번호 : <input type="text" name="bookNo" value="<%= bookNo %>" readonly><br><br>
		                 
		멤버 번호:<input type="text" name="memberNo" value="<%= memberNo %>" readonly><br><br>
		              
		리뷰 내용:<textarea rows="3" cols="20" name="bookReviewContent"></textarea><br><br>
		
		<input type="submit" value="리뷰  등록">

        </form>
	</body>
</html>