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
	int qnaNo = Integer.parseInt(request.getParameter("qnaNo"));
	String sessionId = (String)session.getAttribute("sessionAdminId");
	String sessionName = (String)session.getAttribute("sessionAdminName");
	int sessionNo = (Integer)session.getAttribute("sessionAdminNo");
	System.out.println(sessionNo +"<--sessionNo");

	QnaCommentDTO qnaCommentDTO = new QnaCommentDTO();
	qnaCommentDTO.setAdminNo(sessionNo);
	qnaCommentDTO.setQnaNo(Integer.parseInt(request.getParameter("qnaNo")));
	qnaCommentDTO.setCommentContent(request.getParameter("commentContent"));
	System.out.println(request.getParameter("commentContent") + "<--commentContent");
	System.out.println(request.getParameter("qnaNo") + "<--qnaNo");
	System.out.println(request.getParameter("adminNo") + "<--adminNo");
	
	QnaCommentService qnaCommentService = new QnaCommentService();
	qnaCommentService.insertQnaCommentService(qnaCommentDTO, sessionNo);
 	response.sendRedirect(request.getContextPath()+"/admin/qna/viewQnaAdmin.jsp?qnaNo="+qnaNo);
%>	
</body>
</html>