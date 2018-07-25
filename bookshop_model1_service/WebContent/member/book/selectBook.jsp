<!-- 07.18 송원민 / 하나의 책 정보를 조회하는 화면 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.BookService" %>
<%@ page import="dto.BookMemberJoinDTO" %>
<%@ page import="dto.BookJoinListDTO" %>
<%@ page import="dto.BookIntroDTO" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<%
	// selectBookList.jsp 에서 받아온 책 번호(bookNo) 값
	int bookNo = Integer.parseInt(request.getParameter("bookNo"));
	//책 소개글에 대한 식별넘버
	String bookIntroNo = request.getParameter("bookIntroNo");
	//책 댓글에 대한 식별넘버
	String bookReviewNo = request.getParameter("bookReviewNo");
	// 로그인을 성공한 멤버 넘버 세션값으로 멤버번호를 등록한다.
	String memberNo = String.valueOf(session.getAttribute("sessionMemberNo"));
	
	// 책 하나의 정보를 조회하기 위한 객체들을 생성
	BookService bookService = new BookService();
	BookJoinListDTO detailBookDTO = bookService.selectDetailBookService(bookNo, bookIntroNo);
%>	
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>책 상세정보</title>
	</head>
	<body>
		<jsp:include page="../member/memberLoginForm.jsp"></jsp:include><br>
		<h3>책의 상세정보</h3>
		<table border="1">
			<tr>
				<th>책 번호</th>
				<th>책 카테고리</th>
				<th>출판사</th>
				<th>책 이름</th>
				<th>저자</th>
				<th>가격</th>
				<th>포인트</th>
				<th>책수량</th>
				<th>책절판상태</th>
				<th>출판일</th>
			</tr>
	<%
		if(detailBookDTO != null) {
	%>	
		<!-- 성공일 경우 화면에 출력되는 책 하나에 대한 정보들-->
			<tr>
				<td><%=detailBookDTO.getBookDTO().getBookNo() %></td>
				<td><%=detailBookDTO.getBookCodeDTO().getBookCodeName() %></td>
				<td><%=detailBookDTO.getBookPublisherDTO().getPubliserName() %></td>
				<td><%=detailBookDTO.getBookDTO().getBookName() %></td>
				<td><%=detailBookDTO.getBookDTO().getBookAuthor() %></td>
				<td><%=detailBookDTO.getBookDTO().getBookPrice() %></td>
				<td><%=detailBookDTO.getBookDTO().getBookPoint() %></td>
				<td><%=detailBookDTO.getBookDTO().getBookAmount() %></td>
				<td><%=detailBookDTO.getBookDTO().getBookOut() %></td>
				<td><%=detailBookDTO.getBookDTO().getBookDate() %></td>
			</tr>
		</table>
		<h3>장바구니에 넣기</h3>
		<jsp:include page="/member/cart/insertShoppingCartForm.jsp" flush="false">
			<jsp:param name="bookNo" value="<%=bookNo %>" />
			<jsp:param name="bookPrice" value="<%=detailBookDTO.getBookDTO().getBookPrice() %>" />
		</jsp:include>
	<%
		}
		if(detailBookDTO.getBookIntroListDTO().size() < 3){
			if(null != detailBookDTO.getBookIntroDTO()){
	%>
			<h3>책의 소개글</h3>
			<form action="<%=request.getContextPath()%>/member/bookIntro/updateBookIntroAction.jsp" method="post">
				작성자 : <input type="text" name="bookIntroWrite" value="<%=detailBookDTO.getBookIntroDTO().getBookIntroWrite() %>">
				<input type="submit" value="수정"><br><br>
				<input type="hidden" name="bookNo" value="<%=bookNo %>">
				<input type="hidden" name="bookIntroNo" value="<%=bookIntroNo %>">
				<textarea name="bookIntroContent" cols="100" rows="10"><%=detailBookDTO.getBookIntroDTO().getBookIntroContent() %></textarea><br>
			</form><br>
	<%
			} else {
	%>
			<h3>책의 소개글</h3>
			<form action="<%=request.getContextPath()%>/member/bookIntro/insertBookIntroAction.jsp" method="post">
				작성자 : <input type="text" name="bookIntroWrite">
				<input type="submit" value="등록"><br><br>
				<input type="hidden" name="bookNo" value="<%=bookNo %>">
				<textarea name="bookIntroContent" cols="100" rows="10"></textarea>
			</form><br>
	<%
			}
		}
	%>
		<h3>책의 소개 리스트</h3>
		<table border="1">
			<tr>
				<th>책 소개글 번호</th>
				<th>책 소개 내용</th>
				<th>작성자</th>
				<th>수정</th>
				<th>삭제</th>
			</tr>
		<%
			for(BookIntroDTO bookIntroDTO : detailBookDTO.getBookIntroListDTO()){
		%>
			<tr>
				<td><%=bookIntroDTO.getBookIntroNo() %></td>
				<td><%=bookIntroDTO.getBookIntroContent() %></td>
				<td><%=bookIntroDTO.getBookIntroWrite() %></td>
				<td><a href="<%=request.getContextPath()%>/member/book/selectBook.jsp?bookIntroNo=<%=bookIntroDTO.getBookIntroNo() %>&bookNo=<%=bookNo %>">수정</a></td>
				<td><a href="<%=request.getContextPath()%>/member/bookIntro/deleteBookIntroAction.jsp?bookIntroNo=<%=bookIntroDTO.getBookIntroNo() %>&bookNo=<%=bookNo %>">삭제</a></td>
			</tr>
		<%
			}
		%>
		</table><br>
	<%
		if(null != memberNo){
			if(detailBookDTO.getBookMemberJoinDTO().size() < 5){
	%>
			<h3>댓글</h3>
			<form action="<%= request.getContextPath() %>/member/bookReview/insertBookReviewAction.jsp" method="post">
				<input type="hidden" name="bookNo" value="<%= bookNo %>">
				<input type="hidden" name="memberNo" value="<%= memberNo %>">
			    <textarea cols="50" rows="5" name="bookReviewContent"></textarea><br><br>
				<input type="submit" value="리뷰  등록">
	        </form><br><br>
    <%
			}
		}
    %>
        <h3>댓글 리스트</h3>
        <table border="1">
        	<tr>
        		<th>댓글 내용</th>
        		<th>작성자</th>
        		<th>수정</th>
        		<th>삭제</th>
        	</tr>
        <%
			for(BookMemberJoinDTO bookMemberJoinDTO : detailBookDTO.getBookMemberJoinDTO()) {
		%>
			<tr>
				<td><%=bookMemberJoinDTO.getBookReviewDTO().getBookReviewContent() %></td>
				<td><%=bookMemberJoinDTO.getMemberDTO().getMemberName() %></td>
			<%
				if(String.valueOf(bookMemberJoinDTO.getBookReviewDTO().getMemberNo()).equals(memberNo)){
			%>
				<td><a href="<%=request.getContextPath() %>/member/bookReview/updateBookReviewForm.jsp?bookReviewNo=<%=bookMemberJoinDTO.getBookReviewDTO().getBookReviewNo() %>&bookNo=<%=bookNo %>">수정</a></td>
				<td><a href="<%=request.getContextPath() %>/member/bookReview/deleteBookReviewAction.jsp?bookReviewNo=<%=bookMemberJoinDTO.getBookReviewDTO().getBookReviewNo() %>&bookNo=<%=bookNo %>">삭제</a></td>
			</tr>
		<%	
				}
        	}
		%>
		</table><br><br>
	</body>
</html>