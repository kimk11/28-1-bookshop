<!-- 2018.07.18 송유빈 -->
<!-- insertMemberForm.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 등록 화면</title>
</head>
<body>
	<h2>회원가입</h2>
	<form action ="<%=request.getContextPath()%>/member/member/insertMemberAction.jsp" method="post">
		<label>아이디 : </label><input type ="text" name="memberId" size="7"><br>
		<label>비밀번호 : </label><input type ="password" name="memberPw" size="7"><br>
		<label>이름 : </label><input type ="text" name="memberName" size="7" ><br>
		<label>주소 : </label><input type ="text" name="memberAddr" size="30"><br>
		<button type="submit">등록</button>
	</form>
</body>
</html>
