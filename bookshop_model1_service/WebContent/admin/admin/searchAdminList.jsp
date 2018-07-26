<!-- 2018.07.18 송유빈 -->
<!-- searchMemberList.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.AdminService"%>
<%@ page import="dto.AdminDTO"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>관리자 리스트</title>
</head>
<body>
		
<%
	request.setCharacterEncoding("utf8");
	
	AdminService adminService = new AdminService();
	ArrayList<AdminDTO> list = adminService.selectSearchAdminService();
%>
	
	<h2>관리자 리스트</h2>
	<table border="1">
		<tr>
			<th>No</th>
			<th>아이디</th>
			<th>이름</th>
			<th>등록날짜</th>
			<th>수정</th>
			<th>삭제</th>
		</tr>
<%	
		for(int i=0; i<list.size(); i++) {
			AdminDTO adminDTO = list.get(i);
%>		
		<tr>
			<td><%=adminDTO.getAdminNo()%> </td>
			<td><%=adminDTO.getAdminId() %></td>
			<td><%=adminDTO.getAdminName() %></td>
			<td><%=adminDTO.getAdminDate() %></td>
			<td><a href="<%= request.getContextPath()%>/admin/admin/updateAdminForm.jsp?adminNo=<%=adminDTO.getAdminNo() %>">수정</a></td>
			<td><a href="<%= request.getContextPath()%>/admin/admin/deleteAdminAction.jsp?adminNo=<%=adminDTO.getAdminNo() %>">삭제</a></td>
		</tr>
<%
		}
%>
	</table>
</body>
</html>