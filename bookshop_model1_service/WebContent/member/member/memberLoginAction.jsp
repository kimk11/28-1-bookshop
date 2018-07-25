<!-- 28기 김진우 -->
<!-- 2018/07/24 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dto.MemberDTO" %>
<%@ page import="service.MemberService" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	request.setCharacterEncoding("utf8");

	String memberId = request.getParameter("memberId");
	String memberPw = request.getParameter("memberPw");
	
	MemberDTO memberDTO = new MemberService().loginMember(memberId, memberPw);
	System.out.println(memberDTO.getMemberId()+"<<<<<<<memberDTO ID");
	
	if(null != memberDTO){
		session.setAttribute("sessionMemberNo", memberDTO.getMemberNo());
		session.setAttribute("sessionMemberId", memberDTO.getMemberId());
		session.setAttribute("sessionMemberName", memberDTO.getMemberName());
		session.setAttribute("sessionMemberAddr", memberDTO.getMemberAddr());
	}
	
	response.sendRedirect(request.getContextPath()+"/member/index.jsp");
%>
</body>
</html>