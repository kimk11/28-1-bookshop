<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.BookReviewService" %>
<%@ page import="dto.*" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<%
	BookReviewService bookReviewService = new BookReviewService();
	ArrayList<BookReviewDTO> bookReviewList = bookReviewService.selectBookReviewListService();
%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title></title>
	</head>
	<body>
		<h1>리뷰 리스트</h1><br>
			<table>
			<%
				for(BookReviewDTO bookReviewListService : bookReviewList) {
			%>
				<tr>
					<th><%=bookReviewListService.getMemberNo()%></th>
					<td><%=bookReviewListService.getBookReviewContent()%></td>
					<td><a href="<%=request.getContextPath() %>/member/bookReview/deleteBookReviewForm.jsp?bookReviewNo=<%=bookReviewListService.getBookReviewNo() %>">삭제</a></td>
					<td><a href="<%=request.getContextPath() %>/member/bookReview/updateBookReviewForm.jsp?bookReviewNo=<%=bookReviewListService.getBookReviewNo() %>">수정</a></td>
				</tr>
			<%		
				}
			%>
			</table>
		</body>
	</html>