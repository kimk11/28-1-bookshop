<!-- 07.18 송원민  / 책 카테고리 입력 화면-->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>책 이름 입력화면</title>
	</head>
	
	<body>
		<div>
			<form action="<%= request.getContextPath() %>/member/bookCode/insertBookCodeAction.jsp" method="post">
				<div>
					<label>제목</label>
					<input type="text" name="bookCodeName">
				</div><br>
				<div>
					<input type="submit" value="입력">
				</div>
			</form>
		</div>
	</body>
</html>