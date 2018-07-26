<!-- 2018.07.18 송유빈 -->
<!-- updateMemberAction.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "dao.AdminDAO" %>
<%@ page import = "dto.AdminDTO" %>
<%@ page import = "service.AdminService" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
<%
	request.setCharacterEncoding("utf8");

	AdminDTO adminDTO = new AdminDTO();
	adminDTO.setAdminNo(Integer.parseInt(request.getParameter("memberNo")));
	adminDTO.setAdminId(request.getParameter("memberId"));
	adminDTO.setAdminPw(request.getParameter("memberPw"));
	adminDTO.setAdminName(request.getParameter("memberName"));
	System.out.println(request.getParameter("memberPw"));
	
	AdminService adminService = new AdminService();
	adminService.updateAdminService(adminDTO);
	
%>
</body>
</html>