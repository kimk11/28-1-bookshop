<%@ page language = "java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "service.BookCodeService" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "dto.BookCodeDTO" %>
<!DOCTYPE html>
<%
	BookCodeService bookCodeService = new BookCodeService();
	ArrayList<BookCodeDTO> bookCodeList = bookCodeService.selectBookCodeListService();
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>카테고리 리스트</title>
	</head>
	<body>
		<table>
			<tr>
				<th>카테고리 이름</th>
				<th>수정</th>
				<th>삭제</th>
			</tr>
		<%
			for(BookCodeDTO dto : bookCodeList){
		%>
			<tr>
				<td><%=dto.getBookCodeName() %></td>
				<td><a href="<%=request.getContextPath() %>/admin/bookCode/updateBookCodeForm.jsp?bookCodeNo=<%=dto.getBookCodeNo() %>">수정</a></td>
				<td><a href="<%=request.getContextPath() %>/admin/bookCode/deleteBookCodeAction.jsp?bookCodeNo=<%=dto.getBookCodeNo() %>">삭제</a></td>
			</tr>
		<%
			}
		%>
		</table>
	</body>
</html>