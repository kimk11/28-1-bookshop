<!-- 2018.07.18 송유빈 -->
<!-- insertMemberForm.jsp -->

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "service.MemberInterService"%>
<%@ page import = "dto.BookCodeDTO"%>
<%@ page import = "java.util.ArrayList"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>회원 등록 화면</title>
	</head>
	<body>
	<%
		MemberInterService memberInterService = new MemberInterService();
		ArrayList<BookCodeDTO> arrayBookCodeDto = memberInterService.selectBookCode();
	%>
		<h1>회원가입</h1>
		<form action ="<%=request.getContextPath()%>/member/member/insertMemberAction.jsp" method="post">
			<label>아이디 : </label><input type ="text" name="memberId" size="7"><br>
			<label>비밀번호 : </label><input type ="password" name="memberPw" size="7"><br>
			<label>이름 : </label><input type ="text" name="memberName" size="7" ><br>
			<label>주소 : </label><input type ="text" name="memberAddr" size="30"><br>
			<h3>관심 카테고리</h3>
	<%
		for(int i=0; i<arrayBookCodeDto.size(); i++){
			BookCodeDTO bookCodeDto = arrayBookCodeDto.get(i);
	%>
			<input type = "checkbox" name = "inter" value = "<%=bookCodeDto.getBookCodeNo()%>"><%=bookCodeDto.getBookCodeName() %><br>
	<%
		}
	%>
			<button type="submit">등록</button>
		</form>
	</body>
</html>
