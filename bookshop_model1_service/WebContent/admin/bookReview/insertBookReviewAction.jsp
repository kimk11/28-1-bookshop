<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.BookReviewService" %>
<%@ page import="dto.BookReviewDTO" %>
<!DOCTYPE html>
<%
	request.setCharacterEncoding("utf-8");
	
	int bookNo = Integer.parseInt(request.getParameter("bookNo"));
	int adminNo = Integer.parseInt(request.getParameter("adminNo"));
	String bookReviewContent = request.getParameter("bookReviewContent");
	
	BookReviewDTO bookReviewDTO = new BookReviewDTO();
	bookReviewDTO.setBookNo(bookNo);
	bookReviewDTO.setMemberNo(adminNo);
	bookReviewDTO.setBookReviewContent(bookReviewContent);
	
	BookReviewService bookReviewService = new BookReviewService();
	int insertBookReviewServiceCheck = bookReviewService.insertBookReviewService(bookReviewDTO);
	
	if(insertBookReviewServiceCheck == 1) {
		response.sendRedirect(request.getContextPath() + "/admin/book/selectBook.jsp?bookNo="+bookNo);
	} else {
%>
<%		
	}
%>