<!-- 07.23 송원민 / 책 전체 소개글 조회 화면 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.BookIntroService" %>
<%@ page import="dto.*" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<%
	BookIntroService bookIntroService = new BookIntroService();
	ArrayList<BookIntroDTO> bookIntroList = bookIntroService.selectBookIntroListService();
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title></title>
	</head>
	<body>
		<table border="1">
			<tr>
				<th>책 소개글 번호</th>
				<th>책 번호</th>
				<th>책 소개 내용</th>
				<th>작성자</th>
			</tr>
		<%
			for(BookIntroDTO bookIntroListService : bookIntroList) {
		%>
			<tr>
				<td><%=bookIntroListService.getBookIntroNo()%></td>
				<td><%=bookIntroListService.getBookNo()%></td>
				<td><%=bookIntroListService.getBookIntroContent()%></td>
				<td><%=bookIntroListService.getBookIntroWriter()%></td>
				<td><a href="#"></a>수정</td>
				<td><a href="#"></a>삭제</td>
			</tr>
		<%		
			}
		%>
		</table>
	</body>
</html>