<!-- 2018.07.18 송유빈 -->
<!-- updateMemberForm.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "dto.AdminDTO" %>
<%@ page import = "service.AdminService" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>관리자 정보 수정 </title>
</head>
<body>
<%
	request.setCharacterEncoding("utf8");
	int adminNo = Integer.parseInt(request.getParameter("adminNo"));	
	System.out.println("adminNo : " + adminNo);
	AdminService adminService = new AdminService();
	AdminDTO adminDTO = adminService.selectOneAdminService(adminNo);

%>
	<form action = "<%=request.getContextPath()+ "/admin/admin/updateAdminAction.jsp" %>" method="post">
		<input type="hidden" name = "adminNo" value="<%= adminNo %>">	
		<label>아이디 : </label>
		<input type ="text" name="adminId" value="<%= adminDTO.getAdminId() %>" size="7" readonly="readonly"><br>
		<label>비밀번호 : </label>
		<input type ="password" name="adminPw" value="<%= adminDTO.getAdminPw() %>"  size="7" readonly="readonly"><br>
		<label>이름 : </label>
		<input type ="text" name="adminName" value="<%= adminDTO.getAdminName() %>"  size="7" readonly="readonly"><br>
		<button type ="submit">수정</button>
	</form>
</body>
</html>