<!-- 07.18 송원민 / 책과 관련된 정보를 입력처리하는 화면 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.BookService"%>
<%@ page import="dto.BookDTO" %>
<!DOCTYPE html>
<%
	// insertBookForm에서 받아온 파라미터 값들 각각의 변수들에 값 복사
	int bookCodeNo = Integer.parseInt(request.getParameter("bookCodeNo"));
	int publisherNo = Integer.parseInt(request.getParameter("publisherNo"));
	String bookName = request.getParameter("bookName");
	String bookAuthor = request.getParameter("bookAuthor");
	int bookPrice = Integer.parseInt(request.getParameter("bookPrice"));
	int bookAmount = Integer.parseInt(request.getParameter("bookAmount"));
	String bookOut = request.getParameter("bookOut");
	System.out.println(bookCodeNo + "<-- 카테고리넘버값");
	System.out.println(bookCodeNo + "<-- 출판사넘버값");
	
	BookDTO bookDTO = new BookDTO();
	bookDTO.setBookNo(bookCodeNo);
	bookDTO.setPublisherNo(publisherNo);
	bookDTO.setBookName(bookName);
	bookDTO.setBookAuthor(bookAuthor);
	bookDTO.setBookPrice(bookPrice);
	bookDTO.setBookAmount(bookAmount);
	bookDTO.setBookOut(bookOut);
	
	BookService bookService = new BookService();
	bookService.insertBookService(bookDTO);
%>
