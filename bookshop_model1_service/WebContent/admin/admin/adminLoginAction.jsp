<!-- 28기 김진우 -->
<!-- 2018/07/24 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dto.AdminDTO" %>
<%@ page import="service.AdminService" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	request.setCharacterEncoding("utf8");

	String adminId = request.getParameter("adminId");
	String adminPw = request.getParameter("adminPw");
	
	AdminDTO adminDTO = new AdminService().selectAdminLoginCheckService(adminId, adminPw);
	System.out.println(adminDTO.getAdminId()+"<<<<<<<adminDTO ID");
	
	if(null != adminDTO){
		session.setAttribute("sessionAdminNo", adminDTO.getAdminNo());
		session.setAttribute("sessionAdminId", adminDTO.getAdminId());
		session.setAttribute("sessionAdminName", adminDTO.getAdminName());
	}
	
	response.sendRedirect(request.getContextPath()+"/admin/index.jsp");
%>
</body>
</html>