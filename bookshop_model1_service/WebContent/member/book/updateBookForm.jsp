<!-- 07.18 송원민 / 책 정보 수정 화면 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.BookService" %>
<%@ page import="dto.BookDTO" %>
<!DOCTYPE html>
<%
	int bookNo = Integer.parseInt(request.getParameter("bookNo"));
	BookService bookService = new BookService();
	BookDTO bookDTO = new BookDTO();
	int check = bookService.selectDetailBookService(bookNo);
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>책 정보 수정 화면</title>
	</head>

	<body>
		<div>
			<form action="<%= request.getContextPath() %>/member/book/updateBookAction.jsp" method="post">
			<%
				// bookService 에서 값을 받아오는데 실패 => 리턴값 0 , 성공 => 리턴값 1
				if(check == 1) {
			%>
				<div>
					<label>카테고리번호</label>
					<input type="text" name="bookCodeNo" value="">
				</div>
				<div>
					<label>출판사번호</label>
					<input type="text" name="bookPublisherNo" value="">
				</div>
				<div>
					<label>저자</label>
					<input type="text" name="bookAuthor" value="">
				</div>
				<div>
					<label>가격</label>
					<input type="text" name="bookPrice" value="">
				</div>
				<div>
					<label>책 수량</label>
					<input type="text" name="bookAmount">
					<input type="hidden" name="bookOut" value="">
					<input type="submit" value="입력">
				</div>
			<%		
				} else {
			%>
				
			<%		
				}
			%>
				
			</form>
		</div>
	</body>
</html>