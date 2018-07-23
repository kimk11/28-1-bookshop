<!-- 07.18 송원민 / 하나의 책 정보를 조회하는 화면 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.BookService" %>
<%@ page import="dto.BookDTO" %>
<!DOCTYPE html>
<%
	// selectBookList.jsp 에서 받아온 책 번호(bookNo) 값
	int bookNo = Integer.parseInt(request.getParameter("bookNo"));		
	
	// 책 하나의 정보를 조회하기 위한 객체들을 생성
	BookService bookService = new BookService();
	BookDTO bookDTO = new BookDTO();
%>	
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>	
	<body>
		<table border="1">
			<tr>
				<th>책 번호</th>
				<th>책 카테고리번호</th>
				<th>출판사번호</th>
				<th>책 이름</th>
				<th>저자</th>
				<th>가격</th>
				<th>포인트적립</th>
				<th>책수량</th>
				<th>책절판상태</th>
				<th>출판일</th>
			</tr>
	<%
		// bookService 에서 값을 받아오는데 실패 => 리턴값 0 , 성공 => 리턴값 1
		int check = bookService.selectDetailBookService(bookNo);
		if(check == 1) {
	%>	
		<!-- 성공일 경우 화면에 출력되는 책 하나에 대한 정보들-->
			<tr>
				<td><%=bookDTO.getBookNo() %></td>
				<td><%=bookDTO.getBookcodeNo() %></td>
				<td><%=bookDTO.getPublisherNo() %></td>
				<td><%=bookDTO.getBookName() %></td>
				<td><%=bookDTO.getBookAuthor() %></td>
				<td><%=bookDTO.getBookPrice() %></td>
				<td><%=bookDTO.getBookPoint() %></td>
				<td><%=bookDTO.getBookAmount() %></td>
				<td><%=bookDTO.getBookOut() %></td>
				<td><%=bookDTO.getBookDate()%></td>
			</tr>
		</table>
	<%
		// 실패일 경우 아무것도 나타나지 않음
		} else {
	%>
	
	<%		
		}
	%>
	</body>
</html>