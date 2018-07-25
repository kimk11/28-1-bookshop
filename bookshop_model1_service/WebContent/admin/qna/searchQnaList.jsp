<!-- 2018.07.23 송유빈 -->
<!-- searchQnaList.jsp -->
<%@page import="dto.QnaJoinMemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "dto.QnaDTO" %>
<%@ page import = "dto.QnaJoinMemberDTO" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "dao.QnaDAO" %>
<%@ page import = "service.QnaService" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Q&A 목록</title>
</head>
<body>
	<a href="<%= request.getContextPath() %>/member/index.jsp">홈으로 가기</a>
	<jsp:include page="../admin/memberLoginForm.jsp"></jsp:include><br>
<%	
	request.setCharacterEncoding("utf8");
	

	//세션추가
	int sessionMemberNo = (int)session.getAttribute("sessionMemberNo");
	String sessionId = (String)session.getAttribute("sessionMemberId");
	String sessionName = (String)session.getAttribute("sessionMemberName");
	
	
	//검색 조건 변수
	String searchKey = request.getParameter("searchKey");
	if(searchKey == null){
		searchKey="";
	}
	
	//검색 단어 변수
	String searchValue =request.getParameter("searchValue");
	if(searchValue == null){
		searchValue="";
	}
	
	// 페이징작업
	int currentPage = 1; //현재페이지
	int pagePerRow = 5; //한 페이지에 나올 row 수
	if (request.getParameter("currentPage") != null) {
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	int startRow = (currentPage - 1) * pagePerRow; // 시작 페이지
	int endRow = startRow + (pagePerRow - 1); // 끝날 페이지
	
	QnaDAO qnaDAO = new QnaDAO(); 
	int totalRow = qnaDAO.count(); // 총 row 수 
	
	

	QnaService qnaService = new QnaService();
	ArrayList<QnaJoinMemberDTO> arrayList = qnaService.selectSearchQnaService(startRow, pagePerRow, searchKey, searchValue);
	
	if (endRow > arrayList.size() - 1) {
		endRow = arrayList.size() - 1;
	}
%>
	<!-- 리스트 테이블 -->
	<h2>Q&A 목록</h2>
	<table border=1>
		<tr align="center" valign="middle">
			<th width="8%" height="26">
				<div align="center">번호</div>
			</th>
			<th width="50%">
				<div align="center">제목</div>
			</th>
			<th width="14%">
				<div align="center">작성자</div>
			</th>
			<th width="17%">
				<div align="center">날짜</div>
			</th>
		</tr>
<%
	for(int i=0; i<arrayList.size(); i++) {
		QnaJoinMemberDTO qnaJoinMemberDTO= arrayList.get(i);
		
%>		
		<tr>
			<td><%= qnaJoinMemberDTO.getQnaDTO().getQnaNo() %></td>	<!-- 번호 -->
			<td><a href ="<%= request.getContextPath()%>/admin/qna/viewQnaAdmin.jsp?qnaNo=<%=qnaJoinMemberDTO.getQnaDTO().getQnaNo()%>"><%= qnaJoinMemberDTO.getQnaDTO().getQnaTitle()%></a></td>	<!-- 제목 -->
			<td><%= qnaJoinMemberDTO.getMemberDTO().getMemberName() %></td><!-- 작성자 -->
			<td><%= qnaJoinMemberDTO.getQnaDTO().getQnaDate()%></td>	<!-- 날짜 -->
		</tr>
<%
	}
%>
	</table>
	
	<!-- 버튼 -->
	<div>
		<a href ="<%= request.getContextPath()%>/admin/qna/insertQnaForm.jsp"><button>질문작성</button></a>
		<a href ="<%= request.getContextPath()%>/admin/qna/searchQnaList.jsp"><button>목록</button></a>
	</div>
		
		
	<!-- 페이징 작업 :: 이전과 다음페이지 -->
			<%
				if (currentPage > 1) {
			%>
			<a href="<%= request.getContextPath() %>/admin/qna/searchQnaList.jsp?currentPage=<%=currentPage - 1%>&searchKey=<%=searchKey%>&searchValue=<%= searchValue%>">이전</a>
			<%
				}

				int lastPage = (totalRow - 1) / pagePerRow;
				if ((totalRow - 1) % pagePerRow != 0) {
					lastPage++;
				}
				if (currentPage < lastPage) {
			%>
			<a href="<%= request.getContextPath() %>/admin/qna/searchQnaList.jsp?currentPage=<%=currentPage + 1%>&searchKey=<%=searchKey%>&searchValue=<%= searchValue%>">다음</a> <br>
			<br>
			<%
				}
			%>
		
		
	<!-- 검색창  -->
	<form action="<%= request.getContextPath()%>/admin/qna/searchQnaList.jsp"  method="post">
		<select name="searchKey">
			<option value ="">전체</option>
			<optgroup label="----------"></optgroup>
			<option value ="memberName">이름</option>
			<option value ="qnaId">아이디</option>
		</select>
		<input type= "text" name ="searchValue">
		<button type="submit">검색</button>
	</form>
	
</body>
</html>