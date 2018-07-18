<!-- 07.18 송원민  / 책 카테고리 중복 확인, 입력 처리 화면-->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.BookCodeService"%>
<!DOCTYPE html PUBLIC>
<%	
	//글자 호환을 utf-8로 변경
	request.setCharacterEncoding("utf-8");
	
	//Form에서 입력한 name값을 받아옴
	String bookCodeName = request.getParameter("bookCodeName");
	
	//BookCodeService 객체생성 , bookCodeName값을 DB에 입력결과값 처리 , 중복 결과 리턴값 확인
	BookCodeService bookCodeService = new BookCodeService();
	int bookCodeNameCheck = bookCodeService.insertBookCodeService(bookCodeName);
	System.out.println(bookCodeNameCheck + "<--리턴받은값");
%>