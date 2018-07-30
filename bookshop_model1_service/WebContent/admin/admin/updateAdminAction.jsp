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
	adminDTO.setAdminNo(Integer.parseInt(request.getParameter("adminNo")));
	adminDTO.setAdminId(request.getParameter("adminId"));
	adminDTO.setAdminPw(request.getParameter("adminPw"));
	adminDTO.setAdminName(request.getParameter("adminName"));
// 	System.out.println(request.getParameter("memberPw"));
	
	AdminService adminService = new AdminService();
	adminService.updateAdminService(adminDTO);
	
	response.sendRedirect(request.getContextPath()+"/admin/index.jsp");
%>
</body>
</html>