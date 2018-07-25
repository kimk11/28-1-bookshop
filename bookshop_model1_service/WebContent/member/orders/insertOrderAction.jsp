<!-- 2018.07.14 송유빈 -->
<!-- insertorderAction.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "service.OrdersService" %>
<%@ page import = "service.ShoppingCartService" %>
<%@ page import = "dto.OrdersDTO" %>
<%@ page import = "dto.ShoppingCartDTO" %>
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
	
	ShoppingCartDTO shoppingCartDTO = new ShoppingCartService().selectOneCart(shoppingCartNo);
	
	OrdersDTO ordersDTO = new OrdersDTO(); 
	ordersDTO.setBookNo(shoppingCartDTO.getBookNo());
	ordersDTO.setMemberNo((Integer)session.getAttribute("sessionMemberNo"));
	ordersDTO.setOrdersAmount(Integer.parseInt(request.getParameter("ordersAmount")));
	ordersDTO.setOrdersPrice( (shoppingCartDTO.getShoppingcartPrice()) * (Integer.parseInt(request.getParameter("ordersAmount"))) );
	
	if(null != request.getParameter("ordersAddr")){
		ordersDTO.setOrdersState(request.getParameter("ordersAddr"));
	}else{
		ordersDTO.setOrdersState((String)session.getAttribute("sessionMemberAddr"));
	}
	
	OrdersService orderService = new OrdersService();
	int check = orderService.insertCart(ordersDTO);

	
	response.sendRedirect(request.getContextPath()+"/member/orders/searchOrderList.jsp");
	
%>
</body>
</html>