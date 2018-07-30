<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="./admin/adminLoginForm.jsp"></jsp:include><br>
	
	<jsp:include page="./book/selectBookList.jsp"></jsp:include>
	
	
	<a href="<%= request.getContextPath() %>/admin/qna/searchQnaList.jsp"><h3>Q & A 보기</h3></a>
</body>
</html>