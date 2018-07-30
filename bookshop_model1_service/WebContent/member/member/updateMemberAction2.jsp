<!-- 2018.07.18 송유빈 -->
<!-- updateMemberAction.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "dao.MemberDAO" %>
<%@ page import = "dto.MemberDTO" %>
<%@ page import = "service.MemberService" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
<%
	request.setCharacterEncoding("utf8");

	MemberDTO memberDTO = new MemberDTO();
	memberDTO.setMemberNo(Integer.parseInt(request.getParameter("memberNo")));
	memberDTO.setMemberId(request.getParameter("memberId"));
	memberDTO.setMemberPw(request.getParameter("memberPw"));
	memberDTO.setMemberName(request.getParameter("memberName"));
	memberDTO.setMemberAddr(request.getParameter("memberAddr"));
	System.out.println(request.getParameter("memberPw"));
	
	MemberService memberService = new MemberService();
	memberService.updateMemberService(memberDTO);
	
	response.sendRedirect(request.getContextPath()+"/admin/index.jsp");
%>
</body>
</html>