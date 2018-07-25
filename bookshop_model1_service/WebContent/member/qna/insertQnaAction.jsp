<!-- 2018.07.23 송유빈 -->
<!-- insertQnaAction.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "dto.QnaDTO" %>
<%@ page import = "service.QnaService" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Q&A 처리</title>
</head>
<body>
<%
	request.setCharacterEncoding("utf8");
	
	//세션추가
	int sessionMemberNo = (int)session.getAttribute("sessionMemberNo");
	String sessionId = (String)session.getAttribute("sessionMemberId");
	String sessionName = (String)session.getAttribute("sessionMemberName");

	QnaDTO qnaDTO = new QnaDTO();
	qnaDTO.setMemberNo(Integer.parseInt(request.getParameter("memberNo")));
	qnaDTO.setQnaTitle(request.getParameter("qnaTitle"));
	qnaDTO.setQnaContent(request.getParameter("qnaContent"));
	System.out.println(request.getParameter("qnaTitle") + "<--qnaTitle");
	System.out.println(Integer.parseInt(request.getParameter("memberNo")) + "<--memberNo");
	System.out.println(request.getParameter(request.getParameter("qnaContent")) +"<--qnaContent");
	
	QnaService qnaService = new QnaService();
	qnaService.insertQnaService(qnaDTO,sessionMemberNo);
	
	response.sendRedirect(request.getContextPath()+"/member/qna/searchQnaList.jsp");
%>	
</body>
</html>