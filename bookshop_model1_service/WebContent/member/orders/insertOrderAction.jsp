<!-- 2018.07.14 송유빈 -->
<!-- insertorderAction.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "service.OrdersService" %>
<%@ page import = "dto.OrdersDTO" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>주문처리</title>
</head>
<body>
<%
	request.setCharacterEncoding("utf8");
	int shoppingCartNo = (Integer.parseInt(request.getParameter("shoppingCartNo")));
	
	OrdersDTO ordersDTO = new OrdersDTO(); 
	ordersDTO.setOrdersState(request.getParameter("ordersState"));
	ordersDTO.setOrdersAmount(Integer.parseInt(request.getParameter("ordersAmount")));
	
	OrdersService orderService = new OrdersService();
	int check = orderService.insertCart(ordersDTO);

	
	
	
%>
</body>
</html>