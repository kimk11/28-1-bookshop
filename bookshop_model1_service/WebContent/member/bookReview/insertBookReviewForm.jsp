<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.BookReviewService" %>
<!DOCTYPE html>
<%
	int bookNo = Integer.parseInt(request.getParameter("bookNo"));
	int memberNo = (int)session.getAttribute("sessionNo"); 

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