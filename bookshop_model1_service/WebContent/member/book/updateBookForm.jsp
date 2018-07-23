<!-- 07.18 송원민 / 책 정보 수정 화면 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.BookService" %>
<%@ page import="dto.BookDTO" %>
<!DOCTYPE html>
<%
	//selectBookList.jsp 에서 받아온 책 번호(bookNo) 값
	int bookNo = Integer.parseInt(request.getParameter("bookNo"));
	
	//책 하나의 정보를 조회하기 위한 객체들을 생성
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
				// 성공할 경우 화면에 수정화면을 표시
				if(check == 1) {
			%>
					<div>
						<label>책이름</label>
						<input type="text" name="bookName" value="<%=bookDTO.getBookName()%>">
					</div>
					<div>
						<label>저자</label>
						<input type="text" name="bookAuthor" value="<%=bookDTO.getBookAuthor()%>">
					</div>
					<div>
						<label>가격</label>
						<input type="text" name="bookPrice" value="<%=bookDTO.getBookPrice()%>">원
					</div>
					<div>
						<label>포인트</label>
						<input type="text" name="bookPoint" value="<%=bookDTO.getBookPoint()%>">
					</div>
					<div>
						<label>책 수량</label>
						<input type="text" name="bookAmount" value="<%=bookDTO.getBookAmount()%>">
						<input type="hidden" name="bookOut" value="<%=bookDTO.getBookOut()%>">
						<input type="submit" value="입력">
					</div>
			<%		
				// 실패할 경우 화면에 수정화면이 나오지 않음
				} else {
			%>
				
			<%		
				}
			%>			
			</form>
		</div>
	</body>
</html>