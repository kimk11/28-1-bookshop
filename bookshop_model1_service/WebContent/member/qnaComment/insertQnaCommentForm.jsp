<!-- 2018.07.24 송유빈 -->
<!-- insertQnaCommentForm.jsp --> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>	<!-- 이름, admin no, id 세션 -->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Q&A 댓글 입력</title>
</head>
<body>
<%
	int qnaNo = Integer.parseInt(request.getParameter("qnaNo"));
	System.out.println(qnaNo +"<--qnaNo");
	
	//세션추가
	int sessionAdminNo = (int)session.getAttribute("sessionAdminNo");
	String sessionId = (String)session.getAttribute("sessionAdminId");
	String sessionName = (String)session.getAttribute("sessionAdminName");
%>
	
	<form action = "<%=request.getContextPath() %>/member/qnaComment/insertQnaCommentAction.jsp" method="post">
		<hr>
		<h3> 댓글입력 </h3><p>50자 이내로 쓰시오,</p>
		<div>
			<input type = "hidden" name ="qnaNo" value="<%= qnaNo %>">		<!-- qna_no -->
			<input type = "hidden" name ="adminNo" value="<%=sessionAdminNo %>"><!-- admin_no(세선) -->
			<textarea name="commentContent" rows="3" cols="40"></textarea>	<!-- 댓글내용 -->
			<button type="submit">등록</button>
		</div>
	</form>
</body>
</html>