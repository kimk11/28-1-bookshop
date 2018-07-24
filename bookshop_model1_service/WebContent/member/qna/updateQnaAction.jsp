<!-- 2018.07.23 송유빈 -->
<!-- updateQnaAction.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "dto.QnaDTO" %>
<%@ page import = "service.QnaService" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Q&A 수정처리</title>
</head>
<body>
<%
	request.setCharacterEncoding("utf8");

	int qnaNo = Integer.parseInt(request.getParameter("qnaNo"));
	QnaDTO qnaDTO = new QnaDTO();
	qnaDTO.setQnaTitle(request.getParameter("qnaTitle"));
	qnaDTO.setQnaContent(request.getParameter("qnaContent"));
	QnaService qnaService = new QnaService();	
	int check = qnaService.updateQnaService(qnaDTO, qnaNo);

	//수정 성공 시 
	if(1==check){
		//Q&A 뷰 화면으로 
		response.sendRedirect(request.getContextPath()+"/member/qna/viewQna.jsp");
	// 수정 실패 시
	}else{
		//실패시 경로
		System.out.print("입력실패");
	}
%>
</body>
</html>