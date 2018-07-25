<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.BookIntroService" %>
<%@ page import="dto.BookIntroDTO" %>
<!DOCTYPE html>
<%
	//selectBookIntroList.jsp 에서 받아온 책 소개글 번호(bookIntroNo) 값
	int bookIntroNo = Integer.parseInt(request.getParameter("bookIntroNo"));
	
	BookIntroService bookIntroService = new BookIntroService();
	BookIntroDTO bookIntroDTO = bookIntroService.selectBookIntroService(bookIntroNo);
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>책 소개글 수정 화면</title>
	</head>
	<body>
		<div>
			<form action="<%= request.getContextPath() %>/admin/bookIntro/updateBookIntroAction.jsp" method="post">
				<div>
					<label>책 소개글번호</label>
					<input type="text" name="bookIntroNo" value="<%=bookIntroDTO.getBookIntroNo()%>" readonly>
				</div>
				<div>
					<label>책 소개 내용</label>
					<input type="text" name="bookIntroContent" value="<%=bookIntroDTO.getBookIntroContent()%>">
				</div>
				<div>
					<label>작성자</label>
				<input type="text" name="bookIntroWrite" value="<%=bookIntroDTO.getBookIntroWrite()%>">
				</div>
				<input type="submit" value="수정하기">
			</form>
		</div>
	</body>
</html>