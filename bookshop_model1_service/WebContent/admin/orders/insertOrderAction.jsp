<!-- 2018.07.14 송유빈 -->
<!-- insertorderAction.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="service.OrdersService"%>
<%@ page import="service.ShoppingCartService"%>
<%@ page import="dto.OrdersDTO"%>
<%@ page import="dto.ShoppingCartDTO"%>
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

		String ordersAddr = request.getParameter("ordersAddr");
		if(request.getParameter("ordersAddr") != null){
			ordersAddr= "blank";
		}
		
		ShoppingCartDTO shoppingCartDTO = new ShoppingCartService().selectOneCart(shoppingCartNo);

		OrdersDTO ordersDTO = new OrdersDTO();
		ordersDTO.setBookNo(shoppingCartDTO.getBookNo());
		ordersDTO.setMemberNo((Integer) session.getAttribute("sessionMemberNo"));
		ordersDTO.setOrdersAmount(Integer.parseInt(request.getParameter("ordersAmount")));
		ordersDTO.setOrdersPrice((shoppingCartDTO.getShoppingcartPrice())
				* (Integer.parseInt(request.getParameter("ordersAmount"))));

// 		System.out.println((String) session.getAttribute("sessionMemberAddr") + "체크ㅁㅇㄴㅁㅇ");
		if (!ordersAddr.equals("blank")) {
			ordersDTO.setOrdersAddr(request.getParameter("ordersAddr"));
// 			System.out.println(request.getParameter("ordersAddr")+"<<<request jsp");
// 			System.out.println(ordersDTO.getOrdersAddr()+"<<<addr jsp1");
		} else {
			ordersDTO.setOrdersAddr((String) session.getAttribute("sessionMemberAddr"));
// 			System.out.println(ordersDTO.getOrdersAddr()+"<<<addr jsp2");
		}

		OrdersService orderService = new OrdersService();
		int check = orderService.insertCart(ordersDTO, shoppingCartNo);
		System.out.println(check + "체크");

		response.sendRedirect(request.getContextPath() + "/admin/orders/searchOrderList.jsp");
	%>
</body>
</html>