<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.BookReviewService"%>
<%@ page import = "dto.BookReviewDTO" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>delete Book Action</title>
<%
	int bookReviewNo = Integer.parseInt(request.getParameter("bookReviewNo"));
	int memberNo = Integer.parseInt(request.getParameter("memberNo"));
	
	BookReviewService bookReviewService = new BookReviewService();
	
	BookReviewDTO bookReviewDTO = new BookReviewDTO();
	
	bookReviewDTO.setBookReviewNo(bookReviewNo);
	bookReviewDTO.setMemberNo(memberNo);
	
	int deleteBookReviewServiceCheck = bookReviewService.deleteBookReviewService(bookReviewDTO);
	

	if(deleteBookReviewServiceCheck ==1) {
		response.sendRedirect(request.getContextPath() + "/member/bookReview/selectBookReviewList.jsp");
	} else {
		
	}
%>