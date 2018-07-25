<!-- 2018.07.14 송유빈 -->
<!-- deleteOrderAction.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "service.OrdersService" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>주문 삭제</title>
</head>
<body>
<%
	int ordersNo = Integer.parseInt(request.getParameter("ordersNo"));

	OrdersService orderService = new OrdersService();
	int check = orderService.deleteCart(ordersNo);
	
	//삭제 성공 시 
	if(1==check){
		//Q&A 주문 목록 화면으로 
		response.sendRedirect(request.getContextPath()+"/member/orders/searchOrderList.jsp");
	// db저장 실패시
	}else{
		//실패시 경로
		System.out.print("입력실패");
	}
%>
</body>
</html>