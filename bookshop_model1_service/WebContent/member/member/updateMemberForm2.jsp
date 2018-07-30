<!-- 2018.07.18 송유빈 -->
<!-- updateMemberForm.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "dto.MemberDTO" %>
<%@ page import = "service.MemberService" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 정보 수정 </title>
</head>
<body>
<%
	request.setCharacterEncoding("utf8");
	int memberNo = (Integer)session.getAttribute("sessionMemberNo");
	System.out.println("memberNo : " + memberNo);
	MemberService memberService = new MemberService();
	MemberDTO memberDTO = memberService.selectOneMemberService(memberNo);

%>
	<form action = "<%=request.getContextPath()+ "/member/member/updateMemberAction2.jsp" %>" method="post">
		<input type="hidden" name = "memberNo" value="<%= memberNo %>">	
		<label>아이디 : </label>
		<input type ="text" name="memberId" value="<%= memberDTO.getMemberId() %>" size="7" readonly="readonly"><br>
		<label>비밀번호 : </label>
		<input type ="password" name="memberPw" value="<%= memberDTO.getMemberPw() %>"  size="7"><br>
		<label>이름 : </label>
		<input type ="text" name="memberName" value="<%= memberDTO.getMemberName() %>"  size="7" readonly="readonly"><br>
		<label>주소 : </label>
		<input type ="text" name="memberAddr" value="<%= memberDTO.getMemberAddr() %>"  size="30"><br>
		<button type ="submit">수정</button>
	</form>
	
	<a href="<%= request.getContextPath() %>/member/member/deleteMemberAction2.jsp">회원 탈퇴</a>
</body>
</html>