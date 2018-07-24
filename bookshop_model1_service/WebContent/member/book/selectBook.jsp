<!-- 07.18 송원민 / 하나의 책 정보를 조회하는 화면 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.BookService" %>
<%@ page import="dto.BookCodePublisherJoinDTO" %>
<!DOCTYPE html>
<%
	// selectBookList.jsp 에서 받아온 책 번호(bookNo) 값
	int bookNo = Integer.parseInt(request.getParameter("bookNo"));		
	
	// 책 하나의 정보를 조회하기 위한 객체들을 생성
	BookService bookService = new BookService();
	BookCodePublisherJoinDTO detailBookDTO = bookService.selectDetailBookService(bookNo);
%>	
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>책 상세정보</title>
	</head>	
	<body>
		<table border="1">
			<tr>
				<th>책 번호</th>
				<th>책 카테고리</th>
				<th>출판사</th>
				<th>책 이름</th>
				<th>저자</th>
				<th>가격</th>
				<th>포인트</th>
				<th>책수량</th>
				<th>책절판상태</th>
				<th>출판일</th>
			</tr>
	<%
		if(detailBookDTO != null) {
	%>	
		<!-- 성공일 경우 화면에 출력되는 책 하나에 대한 정보들-->
			<tr>
				<td><%=detailBookDTO.getBookDTO().getBookNo() %></td>
				<td><%=detailBookDTO.getBookCodeDTO().getBookCodeName() %></td>
				<td><%=detailBookDTO.getBookPublisherDTO().getPubliserName() %></td>
				<td><%=detailBookDTO.getBookDTO().getBookName() %></td>
				<td><%=detailBookDTO.getBookDTO().getBookAuthor() %></td>
				<td><%=detailBookDTO.getBookDTO().getBookPrice() %></td>
				<td><%=detailBookDTO.getBookDTO().getBookPoint() %></td>
				<td><%=detailBookDTO.getBookDTO().getBookAmount() %></td>
				<td><%=detailBookDTO.getBookDTO().getBookOut() %></td>
				<td><%=detailBookDTO.getBookDTO().getBookDate() %></td>
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