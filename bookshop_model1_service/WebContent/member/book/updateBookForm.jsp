<!-- 07.18 송원민 / 책 정보 수정 화면 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.*" %>
<%@ page import="dto.*" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<%
	//selectBookList.jsp 에서 받아온 책 번호(bookNo) 값
	int bookNo = Integer.parseInt(request.getParameter("bookNo"));
	//메서드 실행을 위한 더미변수
	String bookIntroNo = "";
	
	//책 하나의 정보를 조회하기 위한 객체들을 생성
	BookService bookService = new BookService();
	BookJoinListDTO selectDetailBookServiceJoinDTO = bookService.selectDetailBookService(bookNo, bookIntroNo);
	BookDTO bookDTO = selectDetailBookServiceJoinDTO.getBookDTO();
	
	BookJoinListDTO bookJoinListDTO = bookService.selectBookCodePublisherListService();
	ArrayList<BookCodeDTO> bookCodeDTO = bookJoinListDTO.getBookCodeListDTO();
	ArrayList<BookPublisherDTO> bookPublisherDTO = bookJoinListDTO.getBookPublisherListDTO();
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>책 정보 수정 화면</title>
	</head>

	<body>
		<div>
			<form action="<%= request.getContextPath() %>/member/book/updateBookAction.jsp" method="post">
				카테고리번호<br>
			<%
				for(BookCodeDTO bookCode : bookCodeDTO) {
					System.out.println(bookCode.getBookCodeNo() + "<- 번호값");
			%>
					<input type="radio" name="bookCodeNo" value="<%=bookCode.getBookCodeNo()%>"><%=bookCode.getBookCodeName()%>
			<%	
				}
			%>
				<br>출판사번호<br>
			<%
				for(BookPublisherDTO bookPublisher : bookPublisherDTO)	{
					System.out.println(bookPublisher.getPublisherNo() + "<- 출판사값");
			%>			
					<input type="radio" name="bookPublisherNo" value="<%=bookPublisher.getPublisherNo()%>"><%=bookPublisher.getPubliserName()%>
			<%
				}
			%>	
			
			<%
				// bookService 에서 값을 받아오는데 실패 => 리턴값 0 , 성공 => 리턴값 1
				// 성공할 경우 화면에 수정화면을 표시
				if(selectDetailBookServiceJoinDTO != null) {
			%>
					<div>
						<label>책번호</label>
						<input type="text" name="bookNo" value="<%=bookDTO.getBookNo()%>" readonly>
					</div>
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