<!-- 07.23 송원민 / 책 소개에 대한 입력 처리 화면 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.BookIntroService" %>
<%@ page import="dto.BookIntroDTO" %>
<!DOCTYPE html>
<%
	// 글자 호환을 utf-8로 변경
	request.setCharacterEncoding("utf-8");
	
	int bookNo = Integer.parseInt(request.getParameter("bookNo"));
	String bookIntroContent = request.getParameter("bookIntroContent");
	String introWrite = request.getParameter("introWrite");
	
	// 입력한 텍스트와 작성자 이름 값을 셋팅
	BookIntroDTO bookIntroDTO = new BookIntroDTO();
	bookIntroDTO.setBookNo(bookNo);
	bookIntroDTO.setBookIntroContent(bookIntroContent);
	bookIntroDTO.setBookIntroWrite(introWrite);
	
	// 
	BookIntroService bookIntroService = new BookIntroService();
	int insertBookIntroServiceCheck = bookIntroService.insertBookIntroService(bookIntroDTO);
	
	if(insertBookIntroServiceCheck == 1) {
		response.sendRedirect(request.getContextPath() + "/member/bookIntro/selectBookIntroList.jsp");
	} else {
		out.print("실패임돠");
	}
%>
