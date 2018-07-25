<!-- 07.23 송원민 / 책 카테고리 삭제 처리 작업 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.BookCodeService" %>
<!DOCTYPE html>
<%
	int bookCodeNo = Integer.parseInt(request.getParameter("bookCodeNo"));
	
	BookCodeService bookCodeService = new BookCodeService();
	//Service메서드를 통해 특정 카테고리 삭제, 리턴값으로 처리에 대한 결과를 리턴받는다.
	int check = bookCodeService.deleteBookCodeService(bookCodeNo);
	
	//처리가 끝난후 리스트로
	response.sendRedirect(request.getContextPath()+"/admin/bookCode/selectBookCodeList.jsp");
%>