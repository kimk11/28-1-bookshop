<!-- 2018.07.14 송유빈 -->
<!-- searchOrderList.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "service.OrdersService" %>
<%@ page import = "dto.OrdersDTO" %>
<%@ page import = "dao.OrdersDAO" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>주문 목록</title>
</head>
<body>
<%
	//검색 조건 변수
	String searchKey = request.getParameter("searchKey");
	if(searchKey == null){
		searchKey="";
	}
	
	//검색 단어 변수
	String searchValue =request.getParameter("searchValue");
	if(searchValue == null){
		searchKey="";
	}
	
	
	
%>	
	<h2>주문 내역</h2>
	
	<!-- 검색창  -->
	<form action="<%= request.getContextPath()%>/member/qna/searchQnaList.jsp"  method="post">
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
			<th>주문번호</th>
			<th>도서이름</th>
			<th>구매자이름</th>
			<th>가격</th>
			<th>수량</th>
			<th>날짜</th>
			<th>기존배송지</th>
			<th>신규배송지</th>
		</tr>
<%
	for(int i =0; i<list.size(); i++) {
		OrdersDTO ordersDTO = list.get(i);
%>
		<tr>
			<td><%= ordersDTO.getOrdersNo() %></td>
			<td><%= ordersDTO.getBookNo() %></td>
			<td><%= ordersDTO.getMemberNo() %></td>		
			<td><%= ordersDTO.getOrdersPrice() %></td>
			<td><%= ordersDTO.getOrdersAmount() %></td>
			<td><%= ordersDTO.getOrdersDate() %></td>
			<td><%= ordersDTO.getOrdersAddr() %></td>
			<td><%= ordersDTO.getOrdersState() %></td>
		</tr>
<%
	}
%>		
	</table>
<!-- 페이징 작업 :: 이전과 다음페이지 -->
<%
	if (currentPage > 1) {
%>
<a href="<%= request.getContextPath() %>/teacher/teacherList.jsp?currentPage=<%=currentPage - 1%>&searchKey=<%=searchKey%>&searchValue=<%= searchValue%>">이전</a>
<%
	}

	int lastPage = (totalRow - 1) / pagePerRow;
	if ((totalRow - 1) % pagePerRow != 0) {
		lastPage++;
	}
	if (currentPage < lastPage) {
%>
<a href="<%= request.getContextPath() %>/teacher/teacherList.jsp?currentPage=<%=currentPage + 1%>&searchKey=<%=searchKey%>&searchValue=<%= searchValue%>">다음</a> <br>
<br>
<%
	}
%>
</body>
</html>