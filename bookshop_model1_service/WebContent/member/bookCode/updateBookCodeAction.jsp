<%@ page language = "java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "service.BookCodeService" %>
<%@ page import = "dto.BookCodeDTO" %>
<!DOCTYPE html>
<%
	request.setCharacterEncoding("utf8");

	String bookCodeName = request.getParameter("bookCodeName");
	int bookCodeNo = Integer.parseInt(request.getParameter("bookCodeNo"));
	
	BookCodeDTO bookCodeDTO = new BookCodeDTO();
	//수정 페이지에서 받은 카테고리No값과 이름값을 DTO객체에 복사
	bookCodeDTO.setBookCodeNo(bookCodeNo);
	bookCodeDTO.setBookCodeName(bookCodeName);
	
	BookCodeService bookCodeService = new BookCodeService();
	//수정처리하는 Service메서드 호출, 처리에 대한 결과값을 리턴받는다
	int check = bookCodeService.updateBookCodeService(bookCodeDTO);
	
	//처리가 끝난후 리스트로
	response.sendRedirect(request.getContextPath()+"/member/bookCode/selectBookCodeList.jsp");
%>