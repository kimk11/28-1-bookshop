<!-- 2018.07.24 송유빈 -->
<!-- qnaCommentList.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "dto.QnaCommentDTO" %>
<%@ page import = "service.QnaCommentService" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Q&A 댓글 목록</title>
</head>
<body>
<%
	int qnaNo = Integer.parseInt(request.getParameter("qnaNo"));
	QnaCommentService qnaCommentService = new QnaCommentService();
	QnaCommentDTO qnaCommentDTO = qnaCommentService.selectQnaCommentService(qnaNo);
  
%>
	<table>
		<tr>
			<td>관리자 : <%= %></td>	<!--이름 세션-->
			<td>날짜 : <%= qnaCommentDTO.getCommentDate() %></td>	<!-- comment_date(날짜)  -->
			
		</tr>
		<tr>
			<td><%= qnaCommentDTO.getCommentContent()%></td>		<!-- comment_content(내용)  -->
			<td align="right">
				<a href ="<%=request.getContextPath() %>/member/qnaComment/deleteQnaCommentAction.jsp?qnaNo=<%=qnaNo%>">삭제</a>
			</td>
		</tr>
	</table>
</body>
</html>