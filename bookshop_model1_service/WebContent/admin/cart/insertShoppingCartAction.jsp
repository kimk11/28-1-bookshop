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
	ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO();
	shoppingCartDTO.setBookNo(Integer.parseInt(request.getParameter("bookNo")));
	shoppingCartDTO.setMemberNo((Integer)session.getAttribute("sessionMemberNo"));
	shoppingCartDTO.setShoppingcartAmount(Integer.parseInt(request.getParameter("shoppingcartAmount")));
	shoppingCartDTO.setShoppingcartPrice((Integer.parseInt(request.getParameter("shoppingcartAmount")))*(Integer.parseInt(request.getParameter("bookPrice"))));
	
	//추가 성공 유무
	int check = new ShoppingCartService().insertCart(shoppingCartDTO);
	
	if(0==check){
		response.sendRedirect(request.getContextPath()+"#");
		System.out.println("추가 실패");
	}else if(1==check){
		response.sendRedirect(request.getContextPath()+"/admin/cart/selectShoppingCart.jsp");
		System.out.println("추가 성공");
	}
%>
</body>
</html>