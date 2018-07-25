<!-- 2018.07.24 송유빈 -->
<!-- deleteQnaCommentAction.jsp --> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "service.QnaCommentService" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Q&A 댓글 삭제 처리</title>
</head>
<body>
<%
	int qnaCommentNo = Integer.parseInt(request.getParameter("qnaCommentNo"));
	
	QnaCommentService qnaCommentService = new QnaCommentService();
	int check = qnaCommentService.deleteQnaCommentService(qnaCommentNo);
	
	//삭제 성공 시 
	if(1==check){
		//Q&A 뷰 화면으로 
		response.sendRedirect(request.getContextPath()+"/member/qna/viewQna.jsp");
	// db저장 실패시
	}else{
		//실패시 경로
		System.out.print("삭제실패");
	}

%>
</body>
</html>