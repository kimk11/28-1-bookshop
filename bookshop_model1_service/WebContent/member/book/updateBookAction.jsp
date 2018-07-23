<!-- 07.18 송원민 / 책 정보 수정처리화면 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.BookService" %>
<%@ page import="dto.BookDTO" %>
<!DOCTYPE html>
<%
	//글자 호환을 utf-8로 변경
	request.setCharacterEncoding("utf-8");
	
	// updateBookForm.jsp에서 받아온 파라미터 값들
	int bookNo = Integer.parseInt(request.getParameter("bookNo"));
	int bookCodeNo = Integer.parseInt(request.getParameter("bookCodeNo"));
	int bookPublisherNo = Integer.parseInt(request.getParameter("bookPublisherNo"));
	String bookName = request.getParameter("bookName");
	String bookAuthor = request.getParameter("bookAuthor");
	int bookPrice = Integer.parseInt(request.getParameter("bookPrice"));
	int bookAmount = Integer.parseInt(request.getParameter("bookAmount"));
	String bookOut = request.getParameter("bookOut");
	System.out.println(bookNo + "<-책번호");
	System.out.println(bookCodeNo + "<-카테고리번호");
	System.out.println(bookPublisherNo + "<-출판사번호");
	System.out.println(bookName + "<-책이름");
	System.out.println(bookAuthor + "<-저자");
	System.out.println(bookPrice + "<-가격");
	System.out.println(bookAmount + "<-책수량");
	System.out.println(bookOut + "<- 책절판");
	
	BookService bookService = new BookService();
	
	// 새로운 객체를 생성하여 파라미터에 값들을 셋팅
	BookDTO bookDTO = new BookDTO();
	bookDTO.setBookNo(bookNo);
	bookDTO.setBookcodeNo(bookCodeNo);
	bookDTO.setPublisherNo(bookPublisherNo);
	bookDTO.setBookName(bookName);
	bookDTO.setBookAuthor(bookAuthor);
	bookDTO.setBookPrice(bookPrice);
	bookDTO.setBookAmount(bookAmount);
	bookDTO.setBookOut(bookOut);
	
	// updateBookService 메소드의 리턴값 : 실패=0, 성공=1
	int updateBookServiceCheck = bookService.updateBookService(bookDTO);
	
	// 성공일 경우 책 정보 전체 화면으로가서 수정된 정보 확인 
	if(updateBookServiceCheck == 1) {
		response.sendRedirect(request.getContextPath() + "/member/book/selectBookList.jsp");	
	} else {
		
	}
%>