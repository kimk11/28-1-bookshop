<!-- 2018.07.18 송유빈 -->
<!-- insertMemberAction.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import ="service.MemberService" %>
<%@ page import ="dto.MemberDTO" %>
<%@ page import ="dao.MemberDAO" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원 등록 처리 화면</title>
</head>
<body>
<%
	request.setCharacterEncoding("utf8");
 
	MemberDTO memberDTO = new MemberDTO();
	// 폼 입력자료에서 각 해당하는 변수를 요청해서 받아온 후 setting 값에 할당한다. 
	memberDTO.setMemberId(request.getParameter("memberId"));
	memberDTO.setMemberPw(request.getParameter("memberPw"));
	memberDTO.setMemberName(request.getParameter("memberName"));
	memberDTO.setMemberAddr(request.getParameter("memberAddr"));
	
	// memberService 객체 안에 insertMember메서드에 있는 리턴 값을 int check에 할당
	MemberService memberService =new MemberService();
	int check = memberService.insertMember(memberDTO);
	
	// DB 저장 성공 시에 
	if(1==check){
		//메인화면으로
		response.sendRedirect(request.getContextPath()+"#");
	// db저장 실패시	
	}else {
	//입력폼으로
		response.sendRedirect(request.getContextPath()+"/member/member/insertMemberForm.jsp");
		System.out.println("입력실패");
	}
%>
</body>
</html>