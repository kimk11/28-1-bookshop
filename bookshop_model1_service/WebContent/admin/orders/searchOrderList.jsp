<!-- 2018.07.14 송유빈 -->
<!-- searchOrderList.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "service.OrdersService" %>
<%@ page import = "dto.OrdersDTO" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "dao.OrdersDAO" %>
<%@ page import = "dto.BookJoinOrdersDTO" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>주문 목록</title>
</head>
<body>
<jsp:include page="../admin/memberLoginForm.jsp"></jsp:include>
<%
	int memberNo = (Integer)session.getAttribute("sessionMemberNo");

	//검색 조건 변수
	String searchKey = request.getParameter("searchKey");
	if(request.getParameter("searchKey") == null){
		searchKey="";
	}
	
	//검색 단어 변수
	String searchValue =request.getParameter("searchValue");
	if(request.getParameter("searchValue") == null){
		searchKey="";
	}
	
	// 페이징작업
	int currentPage = 1; //현재페이지
	int pagePerRow = 5; //한 페이지에 나올 row 수
	if (request.getParameter("currentPage") != null) {
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	int startRow = (currentPage - 1) * pagePerRow; // 시작 페이지
	int endRow = startRow + (pagePerRow - 1); // 끝날 페이지
	
	OrdersDAO ordersDAO = new OrdersDAO(); 
	int totalRow = ordersDAO.selectLastPage(pagePerRow); // 총 row 수 
	
	
	OrdersService ordersService = new OrdersService();
	ArrayList<BookJoinOrdersDTO> arrayList = ordersService.selectCartList(currentPage, pagePerRow, searchValue, searchKey, memberNo);
	if (endRow > arrayList.size() - 1) {
		endRow = arrayList.size() - 1;
	}
%>	
	<h2>주문 내역</h2>
	
	<!-- 검색창  -->
	<form action="<%= request.getContextPath()%>/admin/orders/searchOrderList.jsp"  method="post">
		<select name="searchKey">
			<option value ="">전체</option>
			<optgroup label="----------"></optgroup>
			<option value ="bookName">도서이름</option>
		</select>
		<input type= "text" name ="searchValue">
		<button type="submit">검색</button>
	</form><br>
	
	
	<!-- 목록 테이블 -->
	<table border="1">
		<tr>
			<th>도서번호</th>
			<th>주문번호</th>
			<th>도서이름</th>
			<th>회원번호</th>
			<th>가격</th>
			<th>수량</th>
			<th>날짜</th>
			<th>기존배송지</th>
			<th>신규배송지</th>
			<th>삭제</th>
		</tr>
<%
	for(int i =0; i<arrayList.size(); i++) {
		BookJoinOrdersDTO bookJoinOrdersDTO = arrayList.get(i);
		
		System.out.println(bookJoinOrdersDTO.getOrdersDTO().getBookNo()+"adsad");
%>
		<tr>
			<td><%= bookJoinOrdersDTO.getOrdersDTO().getBookNo()%></td>
			<td><%= bookJoinOrdersDTO.getOrdersDTO().getOrdersNo() %></td>
			<td><%= bookJoinOrdersDTO.getBookDTO().getBookName() %></td>	<!-- 도서이름 -->
			<td><%= bookJoinOrdersDTO.getOrdersDTO().getMemberNo() %></td>
			<td><%= bookJoinOrdersDTO.getOrdersDTO().getOrdersPrice() %></td>
			<td><%= bookJoinOrdersDTO.getOrdersDTO().getOrdersAmount() %></td>
			<td><%= bookJoinOrdersDTO.getOrdersDTO().getOrdersDate() %></td>
			<td><%= bookJoinOrdersDTO.getOrdersDTO().getOrdersAddr() %></td>
			<td><%= bookJoinOrdersDTO.getOrdersDTO().getOrdersState() %></td>
			<td><a href ="<%= request.getContextPath() %>/admin/orders/deleteOrderAction.jsp?ordersNo=<%= bookJoinOrdersDTO.getOrdersDTO().getOrdersNo() %>">삭제</a></td>
		</tr>
<%
	}
%>		
	</table>
<!-- 페이징 작업 :: 이전과 다음페이지 -->
<%
	if (currentPage > 1) {
%>
<a href="<%= request.getContextPath() %>/admin/orders/searchOrderList.jsp?currentPage=<%=currentPage - 1%>&searchKey=<%=searchKey%>&searchValue=<%= searchValue%>">이전</a>
<%
	}

	int lastPage = (totalRow - 1) / pagePerRow;
	if ((totalRow - 1) % pagePerRow != 0) {
		lastPage++;
	}
	if (currentPage < lastPage) {
%>
<a href="<%= request.getContextPath() %>/admin/orders/searchOrderList.jspteacherList.jsp?currentPage=<%=currentPage + 1%>&searchKey=<%=searchKey%>&searchValue=<%= searchValue%>">다음</a> <br>
<br>
<%
	}
%>
</body>
</html>