<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.BookReviewService"%>
<%@ page import = "dto.BookReviewDTO" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>delete Book Action</title>
<%
	int bookNo = Integer.parseInt(request.getParameter("bookNo"));
	int bookReviewNo = Integer.parseInt(request.getParameter("bookReviewNo")); // 리뷰 넘버값을 가져온다.
	int memberNo = (int)session.getAttribute("sessionMemberNo"); // 로그인을 성공한 멤버 넘버 세션값으로 멤버번호를 등록한다.
	
	BookReviewService bookReviewService = new BookReviewService();
	
	BookReviewDTO bookReviewDTO = new BookReviewDTO();
	
	bookReviewDTO.setBookReviewNo(bookReviewNo);
	bookReviewDTO.setMemberNo(memberNo);
	
	int deleteBookReviewServiceCheck = bookReviewService.deleteBookReviewService(bookReviewDTO);
	// 삭제처리하는 Service메서드 호출, 처리에 대한 결과값을 리턴받는다

	if(deleteBookReviewServiceCheck ==1) {
		response.sendRedirect(request.getContextPath() + "/admin/book/selectBook.jsp?bookNo="+bookNo);
	} else {
		
	}
%>