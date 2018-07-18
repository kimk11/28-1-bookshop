<!-- 07.18 송원민 / 책과 관련된 정보를 입력하는 화면 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.BookService" %>
<%@ page import="dto.*" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<%
	// 카테고리 번호와 출판사 번호를 받아옴
	
	BookService bookService = new BookService();
	BookArrayListJoinDTO bookArrayListJoinDTO = bookService.selectBookCodePublisherListService();
	ArrayList<BookCodeDTO> bookCodeDTO = bookArrayListJoinDTO.getBookCodeList();
	ArrayList<BookPublisherDTO> bookPublisherDTO = bookArrayListJoinDTO.getBookPublisherList();
	
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>

	<body>
		<div>
			<form action="<%= request.getContextPath() %>/member/book/insertBookAction.jsp" method="post">
			<%
				for(BookCodeDTO bookCode : bookCodeDTO) {
			%>
					<input type="radio" name="bookCodeNo" value="<%=bookCode.getBookCodeNo()%>"><%=bookCode.getBookCodeName()%>
			<%	
				}
			%>
			<%
				for(BookPublisherDTO bookPublisher : bookPublisherDTO)	{
			%>			
					<input type="radio" name="bookPublisherNo" value="<%=bookPublisher.getPublisherNo()%>"><%=bookPublisher.getPubliserName()%>
			<%
				}
			%>	
				<div>
					<label>책 이름</label>
					<input type="text" name="bookName">
				</div>
				<div>
					<label>저자</label>
					<input type="text" name="bookAuthor">
				</div>
				<div>
					<label>가격</label>
					<input type="text" name="bookPrice">
				</div>
				<div>
					<label>포인트적립</label>
					<select>
						<option value="point">100</option>
						<option value="point">90</option>
						<option value="point">80</option>
						<option value="point">70</option>
						<option value="point">60</option>
					</select>
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