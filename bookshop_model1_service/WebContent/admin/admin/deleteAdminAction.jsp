<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="service.AdminService" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 삭제</title>
</head>
<body>
<%
	int adminNo = 0;

	try{
		adminNo = Integer.parseInt(request.getParameter("adminNo"));
		System.out.println(adminNo + "<-- adminNo");
		
	} catch(NullPointerException n) {
		response.sendRedirect(request.getContextPath() + "/admin/admin/searchAdminList.jsp");
	}

	AdminService adminService = new AdminService();
	int check = adminService.deleteAdminService(adminNo);
	
	// db저장 성공시
	if(1==check){
		//메인화면으로
		response.sendRedirect(request.getContextPath()+"/admin/admin/searchAdminList.jsp");
	// db저장 실패시
	}else{
		//실패시 경로
		response.sendRedirect(request.getContextPath()+"/admin/admin/searchAdminList.jsp");
		System.out.print("입력실패");
	}
%>
</body>
</html>