<!-- 28기 김진우 -->
<!-- 2018/07/24 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.ShoppingCartService"%>
<%@ page import="dto.ShoppingCartDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	int shoppingcartNo = Integer.parseInt(request.getParameter("shoppingcartNo"));
	
	//삭제 성공 유무
	int check = new ShoppingCartService().deleteCart(shoppingcartNo);
	
	if(0==check){
		response.sendRedirect(request.getContextPath()+"/member/cart/selectShoppingCart.jsp");
		System.out.println("삭제 실패");
	}else if(1==check){
		response.sendRedirect(request.getContextPath()+"/member/cart/selectShoppingCart.jsp");
		System.out.println("삭제 성공");
	}
%>
</body>
</html>