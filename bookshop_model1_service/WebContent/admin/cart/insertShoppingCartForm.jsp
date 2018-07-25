<!-- 28기 김진우 -->
<!-- 2018/07/24 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	int bookNo = Integer.parseInt(request.getParameter("bookNo"));
	int bookPrice = Integer.parseInt(request.getParameter("bookPrice"));
%>
	<div>
		<form action="<%= request.getContextPath() %>/admin/cart/insertShoppingCartAction.jsp" method="post">
			<input type="hidden" name="bookNo" value="<%= bookNo %>">
			<input type="hidden" name="bookPrice" value="<%= bookPrice %>">
			<div>
				<label>수량 : </label>
				<input type="text" name="shoppingcartAmount">
			</div>
			<div>
				<button type="submit">장바구니 담기</button>
			</div>
		</form>
	</div>
</body>
</html>