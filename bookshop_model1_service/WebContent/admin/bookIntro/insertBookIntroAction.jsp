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
	String bookIntroWrite = request.getParameter("bookIntroWrite");
	
	// 입력한 텍스트와 작성자 이름 값을 셋팅
	BookIntroDTO bookIntroDTO = new BookIntroDTO();
	bookIntroDTO.setBookNo(bookNo);
	bookIntroDTO.setBookIntroContent(bookIntroContent);
	bookIntroDTO.setBookIntroWrite(bookIntroWrite);
	
	// insertBookIntroService 에서 받은 리턴값 성공=1, 실패=0
	BookIntroService bookIntroService = new BookIntroService();
	int insertBookIntroServiceCheck = bookIntroService.insertBookIntroService(bookIntroDTO);
	
	// 성공시에 책 소개글 전체 리스트로 가고 실패하면 화면에 실패문구를 나타내줌
	if(insertBookIntroServiceCheck == 1) {
		response.sendRedirect(request.getContextPath() + "/admin/book/selectBook.jsp?bookNo="+bookNo);
	} else {
		out.print("실패임돠");
	}
%>
