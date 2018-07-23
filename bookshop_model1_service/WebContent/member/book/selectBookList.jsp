<!-- 07.18 송원민 / 책 전체 리스트 화면 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.BookService"%>
<%@ page import="dto.BookCodePublisherJoinDTO"%>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<%
	//UTF-8로 인코딩
	request.setCharacterEncoding("utf8");

	//페이징 작업
	int pagePerRow = 3; //한 페이지당 보는 갯수
	int currentPage = 1; //현재 페이지
	if(request.getParameter("currentPage") != null){ //페이지 이동 후 currentPage가 String타입이 되기때문에 int 데이터타입으로 변경
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	
	//검색 조건 작업
	String searchKey = ""; //검색 조건 초기값 공백
	if(request.getParameter("searchKey") != null){
		searchKey = request.getParameter("searchKey"); //검색조건값이 들어오면 변수에 저장
		
		request.getSession().setAttribute("searchKey", searchKey);
	} else if(request.getSession().getAttribute("searchKey") != null){ 
		
		searchKey = (String)request.getSession().getAttribute("searchKey");
	}
	
	//검색 단어 작업
	String searchValue = ""; //검색 단어 초기값 공백
	if(request.getParameter("searchValue") != null){
		searchValue = request.getParameter("searchValue"); //검색단어값이 들어오면 변수에 저장
		
		request.getSession().setAttribute("searchValue", searchValue);
	} else if(request.getSession().getAttribute("searchValue") != null){ 

		searchValue = (String)request.getSession().getAttribute("searchValue");
	}
	
	BookService bookService = new BookService();
	ArrayList<BookCodePublisherJoinDTO> bookList = bookService.selectSearchBookListService(currentPage, pagePerRow, searchKey, searchValue);
	
	int lastPage = bookService.pagingService(pagePerRow, searchKey, searchValue); //페이징 작업 후 마지막 페이지값을 리턴
%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>책 리스트</title>
	</head>
	
	<body>
		<table border="1">
			<tr>
				<th>책 번호</th>
				<th>책 카테고리</th>
				<th>책 이름</th>
				<th>저자</th>
				<th>출판사</th>
				<th>가격</th>
				<th>포인트적립</th>
				<th>책수량</th>
				<th>책절판상태</th>
				<th>출판일</th>
				<th>수정</th>
				<th>삭제</th>
			</tr>
			<%
				for(BookCodePublisherJoinDTO bookJoin : bookList){
			%>
			<tr>
				<td><%=bookJoin.getBookDTO().getBookNo() %></td>
				<td><%=bookJoin.getBookCodeDTO().getBookCodeName() %></td>
				<td><%=bookJoin.getBookDTO().getBookName() %></td>
				<td><%=bookJoin.getBookDTO().getBookAuthor() %></td>
				<td><%=bookJoin.getBookPublisherDTO().getPubliserName() %></td>
				<td><%=bookJoin.getBookDTO().getBookPrice() %></td>
				<td><%=bookJoin.getBookDTO().getBookPoint() %></td>
				<td><%=bookJoin.getBookDTO().getBookAmount() %></td>
				<td><%=bookJoin.getBookDTO().getBookOut() %></td>
				<td><%=bookJoin.getBookDTO().getBookDate() %></td>
				<td><a href="#"></a>수정</td>
				<td><a href="#"></a>삭제</td>
			</tr>
			<%
				}
			%>
		</table><br>
		<form action="<%=request.getContextPath() %>/book/selectBookList.jsp" method="post">
			<div><input type="text" name="nameKeyword"> &nbsp; <input type="submit" value="이름검색"></div> <!-- 검색입력폼 -->
		</form>
		<div>
		<%
			if(currentPage > 1){
		%>
			<a href="<%=request.getContextPath() %>/member/book/selectBookList.jsp?currentPage=<%=currentPage-1 %>">이전</a>
		<%
			}
			for(int j=1; j<=lastPage; j++){
		%>
			<a href="<%=request.getContextPath() %>/member/book/selectBookList.jsp?currentPage=<%=j %>"><%=j %></a> <!-- 1 ~ 마지막페이지까지 링크 -->
		<%
			}
			if(currentPage < lastPage){
		%>
			<a href="<%=request.getContextPath() %>/member/book/selectBookList.jsp?currentPage=<%=currentPage+1 %>">다음</a>
		<%	
			}
		%>
		</div><br>
	</body>
</html>