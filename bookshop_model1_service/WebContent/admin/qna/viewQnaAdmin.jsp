<!-- 2018.07.23 송유빈 -->
<!-- viewQna.jsp -->
<%@page import="service.QnaService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "dto.QnaDTO" %>
<%@ page import = "dto.QnaJoinMemberDTO" %>
<%@ page import = "dao.QnaDAO" %>
<!DOCTYPE html>
<html>	<!-- 세션으로 멤버넘버, 이름 받아옴 -->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Q&A 뷰</title>
</head>
<body>
<%
	int qnaNo =Integer.parseInt(request.getParameter("qnaNo"));
	System.out.println(qnaNo+"<--qnaNo");
	QnaService qnaService = new QnaService();
	QnaJoinMemberDTO qnaJoinMemberDTO  = qnaService.selectQnaForUpdateService(qnaNo);
	String sessionName = (String)session.getAttribute("sessionMemberName");
	
%>
	
	<table>
			<h3>Q&A VIEW</h3>
			<tr>
				<th>No.</th>
				<td>
					<input type="text" name="qnaNo" value="<%= qnaNo %>" readonly="readonly">
				</td>
			</tr>	
			<tr>
				<th>이름</th>		
				<td><input type="text" name="memberName" value="<%= qnaJoinMemberDTO.getMemberDTO().getMemberName()%>" size="10" readonly="readonly"></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="qnaTitle" value="<%=qnaJoinMemberDTO.getQnaDTO().getQnaTitle() %>" size="40" readonly="readonly"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name="qnaContent" cols="67"  rows="15" readonly="readonly"><%= qnaJoinMemberDTO.getQnaDTO().getQnaContent()%></textarea></td>
			</tr>
			<tr>
				<!-- 버튼 구분 :: 로그인한 이름과 데이터의 이름이 같으면 나오는 버튼 -->
				<td colspan="2" align="right">
					<a href="<%=request.getContextPath()%>/admin/qna/deleteQnaAction.jsp?qnaNo=<%=qnaNo%>"><button>삭제</button></a>
					<a href="<%=request.getContextPath()%>/admin/qna/searchQnaList.jsp"><button>목록</button></a>

				</td>
			</tr>
		</table>
</body>
</html>