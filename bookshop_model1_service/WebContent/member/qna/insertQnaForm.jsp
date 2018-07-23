<!-- 2018.07.23 송유빈 -->
<!-- insertQnaForm.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>	<!-- 세션으로 멤버넘버, 이름 받아옴 -->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Q&A 입력</title>
</head>
<body>
	<form action="<%=request.getContextPath()%>/member/qna/insertQnaAction.jsp" method="post">
		<table>
			<h3>Q&A 등록</h3>
			<tr>
			<!-- memberNo 세션 -->
				<td colspan="2">	
					<input type="hidden" name="memberNo" value="<%= %>">
				</td>
			</tr>	
			<tr>
			<!-- 이름 세션 -->
				<th>이름</th>
				<td><input type="text" name="memberName" size="10" value="<%= %>"></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="qnaTitle" size="40"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name="qnaContent" cols="67" rows="15"></textarea></td>
			</tr>
			<tr>
				<td colspan="2"><button type="submit">등록</button><td>
			</tr>
		</table>
	</form>
</body>
</html>