<!-- 07.23 송원민 / 책 소개글 삭제 처리 작업 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="service.BookIntroService"%>
<!DOCTYPE html>
<%
	//selectBookIntroList.jsp 에서 가져온 책 소개번호(bookIntroNo)
	int bookIntroNo = Integer.parseInt(request.getParameter("bookIntroNo"));
	String bookNo = request.getParameter("bookNo");

	BookIntroService bookIntroService = new BookIntroService();
	// deleteBookIntroService 에서 받은 리턴값 성공=1, 실패=0
	int deleteBookIntroServiceCheck = bookIntroService.deleteBookIntroService(bookIntroNo);
	
	// 성공시에 책 소개글 전체 리스트로 가고 실패하면 화면에 실패문구를 나타내줌
	if(deleteBookIntroServiceCheck == 1) {
		response.sendRedirect(request.getContextPath() + "/member/book/selectBook.jsp?bookNo="+bookNo);
	} else {
		out.println("실패임돠");
	}
%>