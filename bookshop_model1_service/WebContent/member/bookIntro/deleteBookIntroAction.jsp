<!-- 07.23 송원민 / 책 소개글 삭제 처리 작업 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.BookIntroService"%>
<!DOCTYPE html>
<%
	//selectBookIntroList.jsp 에서 가져온 bookNo
	int bookNo = Integer.parseInt(request.getParameter("bookNo"));
	
	BookIntroService bookIntroService = new BookIntroService();
	
	// deleteBookIntroService 에서 받은 리턴값 성공=1, 실패=0
	int deleteBookIntroServiceCheck = bookIntroService.deleteBookIntroService(bookNo);
	
	if(deleteBookIntroServiceCheck == 1) {
		
	} else {
		
	}
%>