<!-- 2018.07.24 송유빈 -->
<!-- qnaCommentList.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "dto.QnaCommentDTO" %>
<%@ page import = "dto.QnaCommentJoinAdminDTO" %>
<%@ page import = "service.QnaCommentService" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Q&A 댓글 목록</title>
</head>
<body>
<%
	int qnaNo =1; //Integer.parseInt(request.getParameter("qnaNo"));
	QnaCommentService qnaCommentService = new QnaCommentService();
	QnaCommentJoinAdminDTO QnaCommentJoinAdminDTO = qnaCommentService.selectQnaCommentService(qnaNo);

	
	//세션추가
	int sessionAdminNo = (Integer)session.getAttribute("sessionAdminNo");
	String sessionId = (String)session.getAttribute("sessionAdminId");
	String sessionName = (String)session.getAttribute("sessionAdminName");
%>
	<table>
		<tr>
			<td>관리자 : <%= QnaCommentJoinAdminDTO.getAdminDTO().getAdminName() %></td>	
			<td>날짜 : <%= QnaCommentJoinAdminDTO.getQnaCommentDTO().getCommentDate() %></td>	<!-- comment_date(날짜)  -->
		</tr>
		<tr>
			<td><%= QnaCommentJoinAdminDTO.getQnaCommentDTO().getCommentContent()%></td>		<!-- comment_content(내용)  -->
			<td align="right">
				<a href ="<%=request.getContextPath() %>/admin/qnaComment/deleteQnaCommentAction.jsp?qnaNo=<%=qnaNo%>">삭제</a>
			</td>
		</tr>
	</table>
</body>
</html>