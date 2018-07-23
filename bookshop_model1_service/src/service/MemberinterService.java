package service;

import java.util.ArrayList;

import dao.MemberinterDAO;
import dto.MemberNinterjoinDTO;
import dto.MemberinterDTO;
import jdbcObject.JdbcObject;
import jdbcUtil.JdbcUtil;

public class MemberinterService {
	// 한 명의 회원의 책에 대한 관심사를 입력 
	// check 리턴값  0 : 실패 , 1: 성공
	public int insertMemberinterService(MemberinterDTO memberinterDTO) {
		// 리턴 결과를 담을 변수
		int check = 0;
		
		try {
			MemberinterDAO memberinterDAO = new MemberinterDAO(); 
			memberinterDAO.insertMemberinter(memberinterDTO);
			
			if(1 == check) {
				check = 1;	// 리턴값을 담을 변수에 1을 대입
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
	
	
	
	
	// 한 명의 회원정보에서 책에 대한 관심사를 삭제할 수 있는 메서드
	// check 리턴값  0 : 실패 , 1: 성공
	public int deleteMemberinterService(int memberinterNo) {
		
		int check = 0;		// 리턴 결과를 담을 변수
		
		try {
			
			MemberinterDAO memberinterDAO = new MemberinterDAO(); 
			memberinterDAO.deleteMemberinter(memberinterNo);
			
			
			if(1 == check) {		// check에 담긴 값이 1이면 
				check = 1;			// 리턴값을 담을 변수에 1을 대입
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
	
	
	// 전체회원의 관심사를 조회하는 ArrayList
	// check 리턴값  0 : 실패 , 1: 성공
	public ArrayList<MemberNinterjoinDTO> selectMemberinterService(){
		ArrayList<MemberNinterjoinDTO> arrayList = new ArrayList<>();
		
		int check = 0;		// 리턴 결과를 담을 변수
		
		
		try {
			
			MemberinterDAO memberinterDAO = new MemberinterDAO();
			arrayList = memberinterDAO.selectMemberinter();
			
			if(null != arrayList.get(0)) {				// arrayList에 담긴 결과값이 null이 아니라면 
				JdbcObject.getConnection().commit();	// Connection의 요청을 완료하고 특별한 에러가 없다면 결과를 DB에 반영
			}else {
				// arrayList에 담긴 결과값이 null이라면 
				// Connection 수행 중 예기치 않은 에러가 발생하였다면 모든 과정을 취소하고 DB를 Connection이 수행되기 이전상태로 변경
				JdbcUtil.rollback(JdbcObject.getConnection());
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
			JdbcUtil.rollback(JdbcObject.getConnection());
		}finally {
			JdbcUtil.close(JdbcObject.getResultSet());
			JdbcUtil.close(JdbcObject.getPreparedStatement());
			JdbcUtil.close(JdbcObject.getConnection());
		}
		return arrayList;
	}
	
	
	
}
