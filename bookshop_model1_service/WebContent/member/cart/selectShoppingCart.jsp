<!-- 28기 김진우 -->
<!-- 2018/07/24 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.ShoppingCartService"%>
<%@ page import="dto.BookJoinCartDTO"%>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="<%= request.getContextPath() %>/member/index.jsp">홈으로 가기</a>
<jsp:include page="../member/memberLoginForm.jsp"></jsp:include><br>
<%
	int memberNo = (Integer)session.getAttribute("sessionMemberNo");

	//현재페이지 변수
	int currentPage = 1;
	if(null != request.getParameter("currentPage")){
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	
	//한페이지의 리스트 개수 변수
	int rowPage = 5;
	
	ShoppingCartService shoppingCartService = new ShoppingCartService();
	
	ArrayList<BookJoinCartDTO> arrayList = shoppingCartService.selectCartList(currentPage, rowPage, memberNo);
	
	//장바구니에 담긴 책 총 가격
	int totalPrice=0;
	
%>
	<div>
		<form action="<%= request.getContextPath() %>/member/orders/insertOrderForm.jsp" method="post">
			<table>
				<thead>
					<tr>
						<th></th><th>장바구니 번호</th><th>책 번호</th><th>책 이름</th><th>책 수량</th><th>가격</th><th>담은 날짜</th><th>삭제</th>
					</tr>
				</thead>
				<%
					for(BookJoinCartDTO bookJoinCartDTO : arrayList){
						totalPrice += bookJoinCartDTO.getShoppingCartDTO().getShoppingcartPrice();
					%>
						<tbody>
							<tr>
								<td>
									<input type="radio" name="shoppingCartNo" value="<%= bookJoinCartDTO.getShoppingCartDTO().getShoppingcartNo() %>">
								</td>
								<td><%= bookJoinCartDTO.getShoppingCartDTO().getShoppingcartNo() %></td>
								<td><%= bookJoinCartDTO.getShoppingCartDTO().getBookNo() %></td>
								<td><%= bookJoinCartDTO.getBookDTO().getBookName() %></td>
								<td><%= bookJoinCartDTO.getShoppingCartDTO().getShoppingcartAmount() %></td>
								<td><%= bookJoinCartDTO.getShoppingCartDTO().getShoppingcartPrice() %></td>
								<td><%= bookJoinCartDTO.getShoppingCartDTO().getShoppingcartDate() %></td>
								<td><a href="<%= request.getContextPath() %>/member/cart/deleteShoppingCartAction.jsp?shoppingcartNo=<%= bookJoinCartDTO.getShoppingCartDTO().getShoppingcartNo() %>">삭제</a></td>
							</tr>
						</tbody>
					<%
					}
				%>
			</table>
			
			<div>
				<button type="submit">주문하기</button>
			</div>
		</form>
	</div>

<%
	int lastPage = shoppingCartService.selectLastPage(rowPage);
	
	if(currentPage>1){
	%>
		<a href="<%= request.getContextPath() %>/member/cart/selectShoppingCart.jsp?currentPage=<%= currentPage-1 %>">이전</a>
	<%
	}
	
	for(int i=0 ; i<lastPage ; i++){
	%>
		<a href="<%= request.getContextPath() %>/member/cart/selectShoppingCart.jsp?currentPage=<%= i+1 %>"> <%= i+1 %> </a>
	<%
	}
	
	if(currentPage<lastPage){
	%>
		<a href="<%= request.getContextPath() %>/member/cart/selectShoppingCart.jsp?currentPage=<%= currentPage+1 %>">다음</a>
	<%
	}
%>

</body>
</html>