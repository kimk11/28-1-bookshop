<!-- 28기 김진우 -->
<!-- 2018/07/18 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dto.BookPublisherDTO" %>
<%@ page import="dao.BookPublisherDAO" %>
<%@ page import="service.BookPublisherService" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	request.setCharacterEncoding("utf8");

	BookPublisherDTO bookPublisherDTO = new BookPublisherDTO();
	bookPublisherDTO.setPubliserName(request.getParameter("publiserName"));
	bookPublisherDTO.setPublisherWebsite(request.getParameter("publisherWebsite"));
	//System.out.println(bookPublisherDTO.getPubliserName());
	//System.out.println(bookPublisherDTO.getPublisherWebsite());
	
	BookPublisherService bookPublisherService = new BookPublisherService();
	int check = bookPublisherService.insertBookPublisherService(bookPublisherDTO);
	
	// db저장 성공시
	if(1==check){
		//메인화면으로
		response.sendRedirect(request.getContextPath()+"#");
	// db저장 실패시
	}else{
		//입력폼으로
		response.sendRedirect(request.getContextPath()+"/member/bookPubliser/insertBookPubliserForm.jsp");
		System.out.println("입력실패");
	}
%>
</body>
</html>