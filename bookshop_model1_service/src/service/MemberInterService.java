package service;

import java.util.ArrayList;
import dao.MemberInterDAO;
import jdbcObject.JdbcObject;
import jdbcUtil.JdbcUtil;
import dto.BookCodeDTO;

public class MemberInterService {	

	//회원가입 시 책 카테고리의 종류를 조회하는 메서드
	//리턴값 arrayBookCodeDto = MemberInterDAO 클래스의 객체들이 담긴 ArrayList<BookCodeDTO> 클래스의 객체참조변수
	public ArrayList<BookCodeDTO> selectBookCode() {
		
		ArrayList<BookCodeDTO> arrayBookCodeDto = null;
		
		try {
			MemberInterDAO memberInterDao = new MemberInterDAO();
			arrayBookCodeDto = memberInterDao.selectBookCode();
			
		} catch(Exception e) {
			e.printStackTrace();
			JdbcUtil.rollback(JdbcObject.getConnection());
			
		} finally {
			JdbcUtil.close(JdbcObject.getResultSet());
			JdbcUtil.close(JdbcObject.getPreparedStatement());
			JdbcUtil.close(JdbcObject.getConnection());
		}
		
		return arrayBookCodeDto;
	}
	
	//회원가입 시 관심 카테고리를 입력하기 위한 회원번호 조회
	//리턴값 memberNo = 회원가입 시 입력한 아이디의 회원번호
	public int selectMemberNo(String memberId) {
		
		int memberNo = 0;
		
		try {
			MemberInterDAO memberInterDao = new MemberInterDAO();
			memberNo = memberInterDao.selectMemberNo(memberId);
		} catch(Exception e) {
			e.printStackTrace();
			JdbcUtil.rollback(JdbcObject.getConnection());
			
		} finally {
			JdbcUtil.close(JdbcObject.getResultSet());
			JdbcUtil.close(JdbcObject.getPreparedStatement());
			JdbcUtil.close(JdbcObject.getConnection());
		}
		
		return memberNo;	
	}
	
	//회원가입 시 회원의 관심 카테고리를 입력하는 메서드
	//리턴값 0 -> 쿼리 실행 실패, 리턴값 > 0 -> 쿼리 실행 성공
	public int insertMemberInter(int memberNo, int[] bookCodeNo) {
		// 리턴 결과를 담을 변수
		int check = 0;
		
		try {
			MemberInterDAO memberinterDAO = new MemberInterDAO(); 
			check = memberinterDAO.insertMemberInter(memberNo, bookCodeNo);
			
			if(check > 0) {
				JdbcObject.getConnection().commit(); // Connection의 요청을 완료하고 특별한 에러가 없다면 결과를 DB에 반영
			}else {
				// Connection 수행 중 예기치 않은 에러가 발생하였다면 모든 과정을 취소하고 DB를 Connection이 수행되기 이전상태로 변경
				JdbcObject.getConnection().rollback();
			}
		}catch(Exception e){
			
			e.printStackTrace();
			JdbcUtil.rollback(JdbcObject.getConnection());
		}finally {
			
			JdbcUtil.close(JdbcObject.getResultSet());
			JdbcUtil.close(JdbcObject.getPreparedStatement());
			JdbcUtil.close(JdbcObject.getConnection());
		}
		System.out.println(check + "<--service메서드 처리 성공 여부");
		
		return check;
	}
	
	
}