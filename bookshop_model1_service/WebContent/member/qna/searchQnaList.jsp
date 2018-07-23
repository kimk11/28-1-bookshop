<!-- 2018.07.23 송유빈 -->
<!-- searchQnaList.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "dto.QnaDTO" %>
<%@ page import = "service.QnaService" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Q&A 목록</title>
</head>
<body>
<%
	request.setCharacterEncoding("utf8");

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
	
	QnaService qnaService = new QnaService();
	ArrayList<qnaDTO> list = qnaService.selectSearchMemberService(searchKey, searchValue);
%>
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
	for(int i=0; i<list.size(); i++) {
		QnaDTO qnaDTO = list.get(i);
%>		
		<tr>
			<td><%= qnaDTO.getQnaNo() %></td>
			<td><%= qnaDTO.getQnaTitle()%></td>
			<td><%= %></td>	<!-- 세션 -->
			<td><%= qnaDTO.getQnaDate()%></td>
		</tr>
	</table>
	<div>
		<button>질문작성</button>
		<button>목록</button>
	</div>
		
	<form action="<%=request.getContextPath()%>/member/qna/searchQnaList.jsp"  method="post">
		<select name="searchKey">
			<option value ="">전체</option>
			<optgroup label="----------"></optgroup>
			<option value ="qnaName">이름</option>
			<option value ="qnaId">아이디</option>
		</select>
		<input type= "text" name ="searchValue">
		<button type="submit">검색</button>
	</form>
	
</body>
</html>