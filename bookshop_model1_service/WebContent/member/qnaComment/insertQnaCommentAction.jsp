<!-- 2018.07.24 송유빈 -->
<!-- insertQnaCommentAction.jsp --> 
<%@page import="dto.QnaCommentDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "service.QnaCommentService" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Q&A 댓글 입력 처리</title>
</head>
<body>
<%
	request.setCharacterEncoding("utf8");
	int adminNo = Integer.parseInt(request.getParameter("adminNo"));	// 세션

	QnaCommentDTO qnaCommentDTO = new QnaCommentDTO();
	qnaCommentDTO.setQnaNo(Integer.parseInt(request.getParameter("qnaTitle")));
	qnaCommentDTO.setCommentContent(request.getParameter("commentContent"));
	System.out.println(request.getParameter("commentContent") + "<--commentContent");
	System.out.println(request.getParameter("qnaTitle") + "<--qnaTitle");
	
	QnaCommentService qnaCommentService = new QnaCommentService();
	qnaCommentService.insertQnaCommentService(qnaCommentDTO, adminNo);
	
	response.sendRedirect(request.getContextPath()+"/member/qna/viewQna.jsp");
%>	
</body>
</html>