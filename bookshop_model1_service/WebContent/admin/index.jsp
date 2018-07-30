<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>메인 페이지</title>
</head>
<body>
	<jsp:include page="./admin/adminLoginForm.jsp"></jsp:include>
	<a href="<%= request.getContextPath() %>/admin/admin/searchAdminList.jsp"><h3>관리자 리스트</h3></a>
	<a href="<%= request.getContextPath() %>/member/member/searchMemberList.jsp"><h3>일반 사용자 리스트</h3></a>
	
	<jsp:include page="./book/selectBookList.jsp"></jsp:include>
	<a href="<%= request.getContextPath() %>/admin/qna/searchQnaList.jsp"><h3>Q & A 보기</h3></a>
</body>
</html>