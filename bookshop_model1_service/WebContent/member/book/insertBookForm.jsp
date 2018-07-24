<!-- 07.18 송원민 / 책과 관련된 정보를 입력하는 화면 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.BookService" %>
<%@ page import="dto.*" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<%
	//카테고리번호와 출판사 번호를 조회하기위한 배열객체 생성
	BookService bookService = new BookService();
	BookArrayListJoinDTO bookArrayListJoinDTO = bookService.selectBookCodePublisherListService();
	ArrayList<BookCodeDTO> bookCodeDTO = bookArrayListJoinDTO.getBookCodeList();
	ArrayList<BookPublisherDTO> bookPublisherDTO = bookArrayListJoinDTO.getBookPublisherList();	
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>책 정보 입력 화면</title>
	</head>

	<body>
		<div>
			<form action="<%= request.getContextPath() %>/member/book/insertBookAction.jsp" method="post">
				카테고리<br>
			<%
				for(BookCodeDTO bookCode : bookCodeDTO) {
					System.out.println(bookCode.getBookCodeNo() + "<- 번호값");
			%>
					<input type="radio" name="bookCodeNo" value="<%=bookCode.getBookCodeNo()%>"><%=bookCode.getBookCodeName()%>
			<%	
				}
			%>
				<br>출판사<br>
			<%
				for(BookPublisherDTO bookPublisher : bookPublisherDTO)	{
					System.out.println(bookPublisher.getPublisherNo() + "<- 출판사값");
			%>			
					<input type="radio" name="bookPublisherNo" value="<%=bookPublisher.getPublisherNo()%>"><%=bookPublisher.getPubliserName()%>
			<%
				}
			%>	
				<div>
					<label>책 이름</label>
					<input type="text" name="bookName" >
				</div>
				<div>
					<label>저자</label>
					<input type="text" name="bookAuthor">
				</div>
				<div>
					<label>가격</label>
					<input type="text" name="bookPrice">원
				</div>
				<div>
					<label>책 수량</label>
					<input type="text" name="bookAmount">
					<input type="hidden" name="bookOut" value="재고있음">
					<input type="submit" value="입력">
				</div>
			</form>
		</div>
	</body>
</html>