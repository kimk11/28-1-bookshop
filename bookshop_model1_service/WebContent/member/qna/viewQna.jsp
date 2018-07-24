<!-- 2018.07.23 송유빈 -->
<!-- viewQna.jsp -->
<%@page import="service.QnaService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "dto.QnaDTO" %>
<%@ page import = "dao.QnaDAO" %>
<!DOCTYPE html>
<html>	<!-- 세션으로 멤버넘버, 이름 받아옴 -->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Q&A 뷰</title>
</head>
<body>
<%
	int qnaNo = Integer.parseInt(request.getParameter("qnaNo"));
	QnaService qnaService = new QnaService();
	QnaDTO qnaDTO = qnaService.selectQnaForUpdateService(qnaNo);
%>
	
	<table>
			<h3>Q&A VIEW</h3>
			<tr>
				<td colspan="2">	<!-- 세션 -->
					<input type="hidden" name="memberNo" value="<%=  %>">
				</td>
			</tr>
			<tr>
				<th>No.</th>
				<td>
					<input type="text" name="qnaNo" value="<%= qnaDTO.getQnaNo() %>" readonly="readonly">
				</td>
				
			</tr>	
			<tr>
				<th>이름</th>			<!-- 세션 -->
				<td><input type="text" name="memberName" value="<%= %>" size="10"></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="qnaTitle" value="<%=qnaDTO.getQnaTitle() %>" size="40"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name="qnaContent" value="<%=qnaDTO.getQnaContent() %>" cols="67"  rows="15"></textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<a href="<%=request.getContextPath()%>/member/qna/updateQnaForm.jsp?qnaNo=<%=qnaNo%>"><button>수정</button></a>
					<a href="<%=request.getContextPath()%>/member/qna/deleteQnaAction.jsp?qnaNo=<%=qnaNo%>"><button>삭제</button></a>
					<a href="<%=request.getContextPath()%>/member/qna/searchQnaList.jsp"><button>목록</button></a>
				</td>
			</tr>
		</table>
</body>
</html>