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
	int bookpublisherNo = Integer.parseInt(request.getParameter("bookPublisherNo"));
	String bookName = request.getParameter("bookName");
	String bookAuthor = request.getParameter("bookAuthor");
	int bookPoint = Integer.parseInt(request.getParameter("bookPoint"));
	int bookPrice = Integer.parseInt(request.getParameter("bookPrice"));
	int bookAmount = Integer.parseInt(request.getParameter("bookAmount"));
	String bookOut = request.getParameter("bookOut");
	String bookDate = null;
	
	System.out.println(bookCodeNo + "<-- 카테고리넘버값");
	System.out.println(bookpublisherNo + "<-- 출판사넘버값");
	System.out.println(bookName + "<-- 책이름값");
	System.out.println(bookAuthor + "<-- 저자값");
	System.out.println(bookPoint + "<-- 포인트값");
	System.out.println(bookPrice + "<-- 가격");
	System.out.println(bookAmount + "<-- 수량값");
	System.out.println(bookOut + "<-- 등록일");
	
	BookDTO bookDTO = new BookDTO();
	bookDTO.setBookcodeNo(bookCodeNo);
	bookDTO.setPublisherNo(bookpublisherNo);
	bookDTO.setBookName(bookName);
	bookDTO.setBookAuthor(bookAuthor);
	bookDTO.setBookPrice(bookPrice);
	bookDTO.setBookPoint(bookPoint);
	bookDTO.setBookAmount(bookAmount);
	bookDTO.setBookOut(bookOut);
	bookDTO.setBookDate(bookDate);
	
	BookService bookService = new BookService();
	bookService.insertBookService(bookDTO);
%>