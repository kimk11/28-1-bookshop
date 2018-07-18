<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="service.MemberService" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 삭제</title>
</head>
<body>
<%
	int memberNo = Integer.parseInt(request.getParameter("memberNo"));

	MemberService memberService = new MemberService();
	int check = memberService.deleteMemberService(memberNo);
	
	// db저장 성공시
	if(1==check){
		//메인화면으로
		response.sendRedirect(request.getContextPath()+"/member/member/searchMemberList.jsp");
	// db저장 실패시
	}else{
		//실패시 경로
		response.sendRedirect(request.getContextPath()+"/member/member/searchMemberList.jsp");
		System.out.print("입력실패");
	}
%>
</body>
</html>