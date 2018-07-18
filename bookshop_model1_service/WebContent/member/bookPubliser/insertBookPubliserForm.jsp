<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<form action="<%= request.getContextPath() %>/member/bookPubliser/insertBookPubliserAction.jsp" method="post">
			<div>
				<label>출판사명</label>
				<input type="text" name="publiserName">
			</div>
			<div>
				<label>출판사 사이트</label>
				<input type="text" name="publisherWebsite">
			</div>
			<div>
				<input type="submit" value="입력">
			</div>
		</form>
	</div>
</body>
</html>