<!-- 2018.07.23 송유빈 -->
<!-- updateQnaForm.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "service.QnaService" %>
<%@ page import = "dto.QnaDTO" %>
<%@ page import = "dto.QnaJoinMemberDTO" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Q&A 수정 </title>
</head>
<body>
<%
	request.setCharacterEncoding("utf8");
	int qnaNo = Integer.parseInt(request.getParameter("qnaNo"));	
	System.out.println("qnaNo : " + qnaNo);
	QnaService qnaService = new QnaService();
	QnaJoinMemberDTO qnaJoinMemberDTO = qnaService.selectQnaForUpdateService(qnaNo);

%>
	<!-- 제목, 내용만 수정 -->
	<form action="<%=request.getContextPath()%>/admin/qna/updateQnaAction.jsp" method="post">
		<table>
			<h3>Q&A 수정</h3>
			<tr>
				<th>No.</th>
				<td>
					<input type="text" name="qnaNo" value="<%= qnaNo %>" readonly="readonly">
				</td>
			</tr>	
			<tr>
				<th>이름</th> 	
				<td><input type="text" name="memberName" size="10" value="<%=qnaJoinMemberDTO.getMemberDTO().getMemberName()%>" readonly="readonly"></td>		
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="qnaTitle" size="40" value="<%=qnaJoinMemberDTO.getQnaDTO().getQnaTitle()%>"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name="qnaContent" cols="67" rows="15"><%=qnaJoinMemberDTO.getQnaDTO().getQnaContent()%></textarea></td>
			</tr>
			<tr>
				<td colspan="2"><button type="submit">수정</button><td>
			</tr>
		</table>
	</form>
</body>
</html>