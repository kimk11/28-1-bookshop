<!-- 07.18 송원민 / 책과 관련된 정보를 입력처리하는 화면 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.BookService"%>
<%@ page import="dto.BookDTO" %>
<%@ page import="dao.BookDAO" %>
<!DOCTYPE html>
<%
	//글자 호환을 utf-8로 변경
	request.setCharacterEncoding("utf-8");	

	// insertBookForm에서 받아온 파라미터 값들 각각의 변수들에 값 복사
	int bookCodeNo = Integer.parseInt(request.getParameter("bookCodeNo"));
	int bookPublisherNo = Integer.parseInt(request.getParameter("bookPublisherNo"));
	String bookName = request.getParameter("bookName");
	String bookAuthor = request.getParameter("bookAuthor");
	int bookPrice = Integer.parseInt(request.getParameter("bookPrice"));
	int bookAmount = Integer.parseInt(request.getParameter("bookAmount"));
	String bookOut = request.getParameter("bookOut");
	String bookDate = null;
	
	// 넘어온 값들이 정상적으로 출력되는 지 확인
	System.out.println(bookCodeNo + "<-- 카테고리넘버값");
	System.out.println(bookPublisherNo + "<-- 출판사넘버값");
	System.out.println(bookName + "<-- 책이름값");
	System.out.println(bookAuthor + "<-- 저자값");
	System.out.println(bookPrice + "<-- 가격");
	System.out.println(bookAmount + "<-- 수량값");
	System.out.println(bookOut + "<-- 등록일");
	
	// 새로운 객체를 생성하여 파라미터에 값들을 셋팅
	BookDTO bookDTO = new BookDTO();
	bookDTO.setBookcodeNo(bookCodeNo);
	bookDTO.setPublisherNo(bookPublisherNo);
	bookDTO.setBookName(bookName);
	bookDTO.setBookAuthor(bookAuthor);
	bookDTO.setBookPrice(bookPrice);
	bookDTO.setBookAmount(bookAmount);
	bookDTO.setBookOut(bookOut);
	bookDTO.setBookDate(bookDate);
	
	
	BookService bookService = new BookService();
	// 리턴값 실패=>0 , 성공=>1
	int insertBookServiceCheck = bookService.insertBookService(bookDTO);
	
	if(insertBookServiceCheck == 1){
		response.sendRedirect(request.getContextPath() + "/member/book/selectBookList.jsp");	
	} else {
		
	}
%>
