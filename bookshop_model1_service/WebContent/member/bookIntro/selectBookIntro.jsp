<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.BookIntroService" %>
<%@ page import="dto.BookIntroDTO" %>
<!DOCTYPE html>
<%
	//selectBookIntroList.jsp 에서 받아온 책 소개번호(bookIntroNo) 값
	int bookIntroNo = Integer.parseInt(request.getParameter("bookIntroNo"));	

	BookIntroService bookIntroService = new BookIntroService();
	BookIntroDTO bookIntroDTO =	bookIntroService.selectBookIntroService(bookIntroNo);
	
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<table border="1">
			<tr>
				<th>책 소개글 번호</th>
				<th>책 번호</th>
				<th>책 소개 내용</th>
				<th>작성자</th>
			</tr>
			
			<tr>
				<td><%=bookIntroDTO.getBookIntroNo() %></td>
				<td><%=bookIntroDTO.getBookNo() %></td>
				<td><%=bookIntroDTO.getBookIntroContent() %></td>
				<td><%=bookIntroDTO.getBookIntroWrite() %></td>
			</tr>
		</table>
		<a href="<%=request.getContextPath()%>/member/bookIntro/selectBookIntroList.jsp">돌아가기</a>
	</body>
</html>