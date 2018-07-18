<!-- 2018.07.18 송유빈 -->
<!-- searchMemberList.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.MemberService"%>
<%@ page import="dto.MemberDTO"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 검색 리스트</title>
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
	
	MemberService memberService = new MemberService();
	ArrayList<MemberDTO> list = memberService.selectSearchMemberService(searchKey, searchValue);
%>
	
	<h2>회원 검색 리스트</h2>
	
	<form action="<%= request.getContextPath()%>/member/member/searchMemberList.jsp"  method="post">
		<select name="searchKey">
			<option value ="">전체</option>
			<optgroup label="----------"></optgroup>
			<option value ="memberName">이름</option>
			<option value ="memberId">아이디</option>
		</select>
		<input type= "text" name ="searchValue">
		<button type="submit">검색</button>
	</form>

	<table border="1">
		<tr>
			<th>No</th>
			<th>아이디</th>
			<th>비밀번호</th>
			<th>이름</th>
			<th>주소</th>
			<th>포인트</th>
			<th>등록날짜</th>
			<th>수정</th>
			<th>삭제</th>
		</tr>
<%	
		for(int i=0; i<list.size(); i++) {
			MemberDTO memberDTO = list.get(i);
%>		
		<tr>
			<td><%=memberDTO.getMemberNo()%></td>
			<td><%=memberDTO.getMemberId()%></td>
			<td><%=memberDTO.getMemberPw()%></td>
			<td><%=memberDTO.getMemberName()%></td>
			<td><%=memberDTO.getMemberAddr()%></td>
			<td><%=memberDTO.getMemberPoint()%></td>
			<td><%=memberDTO.getMemberDate()%></td>
			<td><a href="<%= request.getContextPath()%>/member/member/updateMemberForm.jsp?memberNo=<%=memberDTO.getMemberNo()%>">수정</a></td>
			<td><a href="<%= request.getContextPath()%>/member/member/deleteMemberAction.jsp?memberNo=<%=memberDTO.getMemberNo()%>">삭제</a></td>
		</tr>
<%
		}
%>
	</table>
</body>
</html>