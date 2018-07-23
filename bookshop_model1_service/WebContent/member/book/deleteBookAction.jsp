<!-- 07.23 송원민 / 책 정보 삭제처리 작업 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.BookService" %>
<!DOCTYPE html>
<%
	//selectBookList.jsp 에서 받아온 책 번호(bookNo) 값
	int bookNo = Integer.parseInt(request.getParameter("bookNo"));
	
	BookService bookService = new BookService();
	
	// deleteBookService 메소드에서 값을 받아오는데 실패 => 리턴값 0 , 성공 => 리턴값 1
	int deleteBookCheck = bookService.deleteBookService(bookNo);
	
	// 성공일 경우 책 정보 전체 화면으로 이동하여 하나의 정보가 삭제처리됨
	if(deleteBookCheck ==1) {
		response.sendRedirect(request.getContextPath() + "/book/selectBookList.jsp");
	} else {
		
	}
%>