<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.BookCodeService"%>
<!DOCTYPE html PUBLIC>
<%
	//Form에서 입력한 name값을 받아옴
	String bookCodeName = request.getParameter("bookCodeName");
	
	//bookCodeName값을 DB에 입력결과값 처리 , 중복 결과 리턴값 확인
	BookCodeService bookCodeService = new BookCodeService();
	int bookCodeCheck = bookCodeService.insertBookCodeService(bookCodeName);
	System.out.println(bookCodeCheck + "<--리턴받은값");

%>