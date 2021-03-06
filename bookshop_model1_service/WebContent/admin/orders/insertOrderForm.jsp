<!-- 2018.07.23 송유빈 -->
<!-- insertOrderForm.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.OrdersService"%>
<%@ page import="service.AdminService"%>
<%@ page import="dto.OrdersDTO"%>
<%@ page import="dto.AdminDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>배송지 입력</title>
</head>
<body>
<jsp:include page="../admin/adminLoginForm.jsp"></jsp:include><br>
<%
	int shoppingCartNo = Integer.parseInt(request.getParameter("shoppingCartNo"));
	int adminNo = (Integer)session.getAttribute("sessionAdminNo");	//세션

	OrdersService ordersService = new OrdersService();
	AdminService adminService= new AdminService();
	AdminDTO adminDTO = adminService.selectOneAdminService(adminNo);
%>
	<form action="<%=request.getContextPath()%>/admin/orders/insertOrderAction.jsp" method="post">
		<table>
			<h2>배송지 정보</h2>
			<tr>
				<td>
					<input type="hidden" name="shoppingCartNo" value="<%=shoppingCartNo%>">
					<hr><div>기존배송지 : </div>
					<%= adminDTO.getAdminNo() %><br><br><br>
					<div>신규 배송지 : </div>	
					<textarea name="ordersAddr" rows="3" cols="40" placeholder="미작성시 자동으로 기존배송지로 선택됩니다"></textarea>	
				</td>	
			</tr>
			<tr>
				<td><br>
					수량 : <input type="text" name="ordersAmount" size=7 >
				</td>				
			</tr>
			<tr>
				<td><br><button type ="submit">구매</button></td>
			</tr>
		</table>
	</form>
</body>
</html>