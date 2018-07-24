<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "service.BookReviewService" %>
<%@ page import = "dto.BookReviewDTO" %>
<!DOCTYPE html>
<%
	request.setCharacterEncoding("utf8");
	
	// 업데이트 폼에서 받은 값 가져오기
	int bookNo = Integer.parseInt(request.getParameter("bookNo"));
	int bookReviewNo = Integer.parseInt(request.getParameter("bookReviewNo"));
	int memberNo = Integer.parseInt(request.getParameter("memberNo"));
	String bookReviewContent = request.getParameter("bookReviewContent");
	
	BookReviewDTO bookReviewDTO = new BookReviewDTO();
	
	bookReviewDTO.setBookNo(bookNo);
	bookReviewDTO.setBookReviewNo(bookReviewNo);
	bookReviewDTO.setMemberNo(memberNo);
	bookReviewDTO.setBookReviewContent(bookReviewContent);
	
	BookReviewService bookReviewService = new BookReviewService();
	// 수정처리하는 Service메서드 호출, 처리에 대한 결과값을 리턴받는다
	int check = bookReviewService.updateBookReviewService(bookReviewDTO);
	
	// 처리가 끝난후 리스트로
	response.sendRedirect(request.getContextPath()+"/member/bookReview/selectBookReviewList.jsp");
%>