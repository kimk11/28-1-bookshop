<!-- 2018.07.23 송유빈 -->
<!-- updateQnaForm.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "service.QnaService" %>
<%@ page import = "dto.QnaDTO" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Q&A 수정 </title>
</head>
<body>
<%
	request.setCharacterEncoding("utf8");
	int memberNo = Integer.parseInt(request.getParameter("memberNo"));	
	System.out.println("memberNo : " + memberNo);
	QnaService memberService = new QnaService();
	QnaDTO qnaDTO = QnaService.selectOneUpdateService(memberNo);
	
%>
	<form action="<%=request.getContextPath()%>/member/qna/updateQnaAction.jsp" method="post">
		<table>
			<h3>Q&A 등록</h3>
			<tr>
				<td colspan="2">
					<input type="hidden" name="memberNo" value="<%=%>">
				</td>
			</tr>	
			<tr>
				<th>이름</th> 	<!-- 세션처리 -->
				<td><input type="text" name="memberName" size="10" value="<%=%>"></td>		
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="qnaTitle" size="40" value="<%=%>"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name="qnaContent" cols="67" rows="15" value="<%=%>"></textarea></td>
			</tr>
			<tr>
				<td colspan="2"><button type="submit">수정</button><td>
			</tr>
		</table>
	</form>
</body>
</html>