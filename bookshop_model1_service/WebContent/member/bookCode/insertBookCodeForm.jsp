<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>책 이름 입력화면</title>
	</head>
	
	<body>
		<fieldset>
			<legend>책 이름 입력 화면</legend>
				<form Action="<%=request.getContextPath() %>/member/bookCode/insertBookCodeAction.jsp" method="post">
					제목 : <input type="text" name="bookCodeName">
					<input type="submit" value="누르기">
				</form>
		</fieldset>
	</body>
</html>