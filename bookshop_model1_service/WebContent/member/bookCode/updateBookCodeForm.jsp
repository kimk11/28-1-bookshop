<%@ page language = "java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "service.BookCodeService" %>
<%@ page import = "dto.BookCodeDTO" %>
<!DOCTYPE html>
<%
	int bookCodeNo = Integer.parseInt(request.getParameter("bookCodeNo"));
	
	BookCodeService bookCodeService = new BookCodeService();
	//리스트에서 받은 카테고리No값으로 특정 카테고리 검색 메서드 호출, 카테고리의 정보가 담긴 객체의 주소값을 리턴받는다
	BookCodeDTO bookCodeDTO = bookCodeService.selectBookCodeService(bookCodeNo);
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>카테고리 수정 페이지</title>
	</head>
	<body>
		<div>
			<form action="<%= request.getContextPath() %>/member/bookCode/updateBookCodeAction.jsp" method="post">
				<div>
					<label>제목</label>
					<input type="text" name="bookCodeName" value="<%=bookCodeDTO.getBookCodeName() %>">
					<input type="hidden" name="bookCodeNo" value="<%=bookCodeDTO.getBookCodeNo() %>">
				</div><br>
				<div>
					<input type="submit" value="입력">
				</div>
			</form>
		</div>
	</body>
</html>