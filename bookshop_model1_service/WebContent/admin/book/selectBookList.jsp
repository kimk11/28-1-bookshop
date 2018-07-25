<!-- 07.18 송원민 / 책 전체 리스트 화면 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.BookService"%>
<%@ page import="dto.BookJoinListDTO"%>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<%
	//UTF-8로 인코딩
	request.setCharacterEncoding("utf8");

	//페이징 작업
	int pagePerRow = 7; //한 페이지당 보는 갯수
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
	//(현재페이지값, 리스트의 갯수, 검색 조건값, 검색 단어값)들을 매개변수로 책 정보들을 전체검색하는 메서드 호출
	//출판사와 카테고리와 책 정보들의 값이 들어간 객체의 주소값을 저장한 배열객체의 주소값을 리턴값으로 받는다.
	ArrayList<BookJoinListDTO> bookList = bookService.selectSearchBookListService(currentPage, pagePerRow, searchKey, searchValue);
	
	//페이징 작업 후 마지막 페이지값을 리턴
	int lastPage = bookService.pagingService(pagePerRow, searchKey, searchValue);
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
				for(BookJoinListDTO bookJoin : bookList){
			%>
			<tr>
				<td><%=bookJoin.getBookDTO().getBookNo() %></td>
				<td><%=bookJoin.getBookCodeDTO().getBookCodeName() %></td>
				<td><a href="<%=request.getContextPath() %>/admin/book/selectBook.jsp?bookNo=<%=bookJoin.getBookDTO().getBookNo() %>"><%=bookJoin.getBookDTO().getBookName() %></a></td>
				<td><%=bookJoin.getBookDTO().getBookAuthor() %></td>
				<td><%=bookJoin.getBookPublisherDTO().getPubliserName() %></td>
				<td><%=bookJoin.getBookDTO().getBookPrice() %></td>
				<td><%=bookJoin.getBookDTO().getBookPoint() %></td>
				<td><%=bookJoin.getBookDTO().getBookAmount() %></td>
				<td><%=bookJoin.getBookDTO().getBookOut() %></td>
				<td><%=bookJoin.getBookDTO().getBookDate() %></td>
				<td><a href="<%=request.getContextPath() %>/admin/book/updateBookForm.jsp?bookNo=<%=bookJoin.getBookDTO().getBookNo() %>">수정</a></td>
				<td><a href="<%=request.getContextPath() %>/admin/book/deleteBookAction.jsp?bookNo=<%=bookJoin.getBookDTO().getBookNo() %>">삭제</a></td>
			</tr>
			<%
				}
			%>
		</table><br>
		<form action="<%=request.getContextPath() %>/admin/book/selectBookList.jsp" method="post">
			<div>
				<select name="searchKey">
    				<option value="">직업선택</option>
    				<option value="book_name">책 이름</option>
   					 <option value="book_author">저자</option>
				</select>
				<input type="text" name="searchValue"> &nbsp; <input type="submit" value="검색">
			</div> <!-- 검색입력폼 -->
		</form>
		<div>
		<%
			if(currentPage > 1){
		%>
			<a href="<%=request.getContextPath() %>/admin/book/selectBookList.jsp?currentPage=<%=currentPage-1 %>">이전</a>
		<%
			}
			for(int j=1; j<=lastPage; j++){
		%>
			<a href="<%=request.getContextPath() %>/admin/book/selectBookList.jsp?currentPage=<%=j %>"><%=j %></a> <!-- 1 ~ 마지막페이지까지 링크 -->
		<%
			}
			if(currentPage < lastPage){
		%>
			<a href="<%=request.getContextPath() %>/admin/book/selectBookList.jsp?currentPage=<%=currentPage+1 %>">다음</a>
		<%	
			}
		%>
		</div><br>
	</body>
</html>