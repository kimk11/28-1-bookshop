<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.BookReviewService" %>
<%@ page import="dto.*" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<%
	int bookNo = 0;
	BookReviewService bookReviewService = new BookReviewService();
	ArrayList<BookMemberJoinDTO> bookReviewList = bookReviewService.selectBookReviewListService(bookNo);
%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>select Book Review List</title>
	</head>
	<body>
		<h1>리뷰 리스트</h1><br>
			<table>
				<tr>
	        		<th>댓글 내용</th>
	        		<th>작성자</th>
	        		<th>삭제</th>
	        		<th>수정</th>
        		</tr>
			<%
				for(BookMemberJoinDTO bookReviewListService : bookReviewList) {
			%>
				<tr>
					<th><%=bookReviewListService.getBookReviewDTO().getBookReviewContent()%></th>
					<th><%=bookReviewListService.getMemberDTO().getMemberName()%></th>
					<td><a href="<%=request.getContextPath() %>/admin/bookReview/deleteBookReviewForm.jsp?bookReviewNo=<%=bookReviewListService.getBookReviewDTO().getBookReviewNo() %>">삭제</a></td>
					<td><a href="<%=request.getContextPath() %>/admin/bookReview/updateBookReviewForm.jsp?bookReviewNo=<%=bookReviewListService.getBookReviewDTO().getBookReviewNo() %>">수정</a></td>
				</tr>
			<%		
				}
			%>
			</table>
		</body>
	</html>