<!-- 2018.07.23 송유빈 -->
<!-- deleteQnaAction.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "service.QnaService" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Q&A 삭제</title>
</head>
<body>
<%
int qnaNo = Integer.parseInt(request.getParameter("qnaNo"));

QnaService qnaService = new QnaService();
int check = qnaService.deleteQnaService(qnaNo);

//삭제 성공 시 
if(1==check){
	//Q&A 목록 화면으로 
	response.sendRedirect(request.getContextPath()+"/member/qna/searchQnaList.jsp");
// db저장 실패시
}else{
	//실패시 경로
	System.out.print("입력실패");
}

%>
</body>
</html>