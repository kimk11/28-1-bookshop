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
		<title>책 소개글 전체 리스트</title>
	</head>
	<body>
		<table border="1">
			<tr>
				<th>책 소개글 번호</th>
				<th>책 번호</th>
				<th>책 소개 내용</th>
				<th>작성자</th>
				<th>수정</th>
				<th>삭제</th>
			</tr>
		<%
			for(BookIntroDTO bookIntroListService : bookIntroList) {
		%>
			<tr>
				<td><%=bookIntroListService.getBookIntroNo()%></td>
				<td><a href="<%=request.getContextPath()%>/member/bookIntro/selectBookIntro.jsp?bookIntroNo=<%=bookIntroListService.getBookIntroNo()%>"><%=bookIntroListService.getBookNo()%></a></td>
				<td><%=bookIntroListService.getBookIntroContent()%></td>
				<td><%=bookIntroListService.getBookIntroWrite()%></td>
				<td><a href="<%=request.getContextPath()%>/member/bookIntro/updateBookIntroForm.jsp?bookIntroNo=<%=bookIntroListService.getBookIntroNo()%>">수정</a></td>
				<td><a href="<%=request.getContextPath()%>/member/bookIntro/deleteBookIntroAction.jsp?bookIntroNo=<%=bookIntroListService.getBookIntroNo()%>">삭제</a></td>
			</tr>
		<%		
			}
		%>
		</table>
	</body>
</html>