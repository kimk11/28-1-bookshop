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
	String sessionId = (String)session.getAttribute("sessionMemberId");
	String sessionName = (String)session.getAttribute("sessionMemberName");
	
	// 세션이 없을 경우 로그인 폼
	// 세션이 있을 경우 회원 정보
	if(null==session.getAttribute("sessionMemberId")){
%>
		<div>
			<h3>로그인</h3>
			<form action="<%= request.getContextPath() %>/member/member/memberLoginAction.jsp" method="post">
				<div>
					<label>ID : </label>
					<input type="text" name="memberId">
				</div>
				<div>
					<label>PASSWORD : </label>
					<input type="password" name="memberPw">
				</div>
				<div>
					<input type="submit" value="로그인">
				</div>
			</form>
		</div>
		
		<div>
			<a href="<%= request.getContextPath() %>/member/member/insertMemberForm.jsp">회원가입</a>
		</div>
<%
	} else {
	%>
		<div>
			<p><%= sessionName %>님 환영합니다.</p>
			<div>
				<a href="<%= request.getContextPath() %>/member/member/updateMemberForm.jsp">회원정보 수정</a>
				<a href="<%= request.getContextPath() %>/member/member/memberLogoutAction.jsp">로그아웃</a>
				<a href="<%= request.getContextPath() %>/member/cart/selectShoppingCart.jsp">장바구니로 가기</a>
				<a href="<%= request.getContextPath() %>/member/orders/searchOrderList.jsp">주문목록 보기</a>
			</div>
		</div>
	<%
	}
%>
</body>
</html>