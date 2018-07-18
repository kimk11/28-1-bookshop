<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.BookPublisherService" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	int publisherNo = Integer.parseInt(request.getParameter("publisherNo"));
	
	BookPublisherService bookPublisherService = new BookPublisherService();
	int check = bookPublisherService.deleteBookPubliserService(publisherNo);
	
	// db저장 성공시
		if(1==check){
			//메인화면으로
			response.sendRedirect(request.getContextPath()+"#");
		// db저장 실패시
		}else{
			//실패시 경로
			response.sendRedirect("#");
			System.out.print("입력실패");
		}
%>
</body>
</html>