<!-- 2018.07.24 송유빈 -->
<!-- qnaCommentList.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "dto.QnaCommentDTO" %>
<%@ page import = "dto.QnaCommentJoinAdminDTO" %>
<%@ page import = "service.QnaCommentService" %>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Q&A 댓글 목록</title>
</head>
<body>
<%
	int qnaNo = Integer.parseInt(request.getParameter("qnaNo"));
	System.out.println(qnaNo+"<--qnaNo commentList");
	
	QnaCommentService qnaCommentService = new QnaCommentService();
	ArrayList<QnaCommentJoinAdminDTO> CommentList = qnaCommentService.selectQnaCommentService(qnaNo);
	
	/* int qnaCommentNo = Integer.parseInt(request.getParameter("QnaCommentJoinAdminDTO.getQnaCommentDTO().getQnaCommentNo()")); */
	/* System.out.println(qnaCommentNo+"<--qnaCommentNo commentList"); */

%>
	<table>
<%
	for(int i= 0; i<CommentList.size(); i++) {
		QnaCommentJoinAdminDTO qnaCommentJoinAdminDTO = CommentList.get(i);
%>
		<tr>
			<td colspan="2" width="400"><hr>
				관리자 : <%= qnaCommentJoinAdminDTO.getAdminDTO().getAdminName() %>	
				날짜 : <%= qnaCommentJoinAdminDTO.getQnaCommentDTO().getCommentDate() %>
			</td>	<!-- comment_date(날짜)  -->
		</tr>
		<tr>
			<td><%=qnaCommentJoinAdminDTO.getQnaCommentDTO().getCommentContent() %></td>		<!-- comment_content(내용)  -->
			<td align="right">
				<a href ="<%=request.getContextPath() %>/admin/qnaComment/deleteQnaCommentAction.jsp?qnaNo=<%=qnaNo%>&qnaCommentNo=<%=qnaCommentJoinAdminDTO.getQnaCommentDTO().getQnaCommentNo()%>">삭제</a>
			</td>
		</tr>
<%
	}
%>
	</table>

</body>
</html>