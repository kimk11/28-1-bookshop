<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.BookIntroService" %>
<%@ page import="dto.BookIntroDTO" %>
<!DOCTYPE html>
<%
	//글자 호환을 utf-8로 변경
	request.setCharacterEncoding("utf-8");

	int bookIntroNo = Integer.parseInt(request.getParameter("bookIntroNo"));
	String bookIntroContent = request.getParameter("bookIntroContent");
	String bookIntroWrite = request.getParameter("bookIntroWrite");
	
	BookIntroDTO bookIntroDTO = new BookIntroDTO();
	bookIntroDTO.setBookIntroNo(bookIntroNo);
	bookIntroDTO.setBookIntroContent(bookIntroContent);
	bookIntroDTO.setBookIntroWrite(bookIntroWrite);
	
	
	BookIntroService bookIntroService = new BookIntroService();
	int updateBookIntroServiceCheck = bookIntroService.updateBookIntroService(bookIntroDTO);
	
	if(updateBookIntroServiceCheck == 1 ){
		response.sendRedirect(request.getContextPath() + "/member/bookIntro/selectBookIntroList.jsp");
	} else {
		out.print("실패임돠");
	}
%>