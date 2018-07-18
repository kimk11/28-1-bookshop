//28기 김진우
//2018/07/18
package service;

import dto.MemberDTO;
import jdbcObject.JdbcObject;
import jdbcUtil.JdbcUtil;
import java.util.ArrayList;
import dao.MemberDAO;

public class MemberService {
	private MemberDAO memberDAO;
	
	private MemberService() {
		this.memberDAO = new MemberDAO();
	}
	//member 테이블 추가
	//리턴값(check) - 0 : 실패, 1 : 성공
	public int insertMemberService(MemberDTO memberDTO) {
		//리턴값 담을 변수
		int check = 0;
		//member데이터 입력 확인 변수
		int insertCheck = 0;
		
		try {
			int selectCheck = memberDAO.selectIdCheckMember();
			
			//아이디 중복 확인
			if(0 == selectCheck) {
				insertCheck = memberDAO.insertMember();
			}
			
			//DAO들이 예외가 없다면 DB에 값 저장, 아니면 db변경사항 취소
			if(0 != insertCheck) {
				JdbcObject.getConnection().commit();
				check = 1;
			}else {
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
		return check;
	}
	
	//member 테이블 삭제
	//리턴값(check) - 0 : 실패, 1 : 성공
	public int deleteMemberService(String memberNo) {
		//리턴값 담을 변수
		int check = 0;
			
		try {
			int deleteCheck = memberDAO.deleteMember();
			
			//DAO에 예외가 없다면 DB에 값 저장, 아니면 db변경사항 취소
			if(0 != deleteCheck) {
				JdbcObject.getConnection().commit();
				check = 1;
			}else {
				JdbcUtil.rollback(JdbcObject.getConnection());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			JdbcUtil.rollback(JdbcObject.getConnection());
		} finally {
			JdbcUtil.close(JdbcObject.getResultSet());
			JdbcUtil.close(JdbcObject.getPreparedStatement());
			JdbcUtil.close(JdbcObject.getConnection());
		}
		
		return check;
	}
	
	//member 테이블 수정
	
	//수정할 데이터 조회
	public MemberDTO selectOneMemberService(String memberNo) {
		MemberDTO memberDTO = new MemberDTO();
		
		try {
			memberDTO = memberDAO.selectOneMember();
			
			//DAO에 예외가 없다면 DB에 값 저장, 아니면 db변경사항 취소
			if(memberDTO.getMemberId() != null) {
				JdbcObject.getConnection().commit();
			}else {
				JdbcUtil.rollback(JdbcObject.getConnection());
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			JdbcUtil.rollback(JdbcObject.getConnection());
		}finally {
			JdbcUtil.close(JdbcObject.getResultSet());
			JdbcUtil.close(JdbcObject.getPreparedStatement());
			JdbcUtil.close(JdbcObject.getConnection());
		}
		return memberDTO;
	}
	
	//데이터 수정
	//리턴값(check) - 0 : 실패, 1 : 성공
	public int updateMemberService(MemberDTO memberDTO) {
		//리턴값 담을 변수
		int check = 0;
		
		try {
			 int updateCheck = memberDAO.updateMember();
			
			//DAO에 예외가 없다면 DB에 값 저장, 아니면 db변경사항 취소
			if(0 != updateCheck) {
				JdbcObject.getConnection().commit();
				check = 1;
			}else {
				JdbcUtil.rollback(JdbcObject.getConnection());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			JdbcUtil.rollback(JdbcObject.getConnection());
		} finally {
			JdbcUtil.close(JdbcObject.getResultSet());
			JdbcUtil.close(JdbcObject.getPreparedStatement());
			JdbcUtil.close(JdbcObject.getConnection());
		}
		
		return check;
	}
	//member 테이블 조회(검색)
	public ArrayList<MemberDTO> selectSearchMemberService(){
		ArrayList<MemberDTO> arrayList = new ArrayList<>();
		
		try {
			arrayList = memberDAO.selectSearchMember();
			
			//DAO에 예외가 없다면 DB에 값 저장, 아니면 db변경사항 취소
			if(null != arrayList.get(0)) {
				JdbcObject.getConnection().commit();
			}else {
				JdbcUtil.rollback(JdbcObject.getConnection());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			JdbcUtil.rollback(JdbcObject.getConnection());
		} finally {
			JdbcUtil.close(JdbcObject.getResultSet());
			JdbcUtil.close(JdbcObject.getPreparedStatement());
			JdbcUtil.close(JdbcObject.getConnection());
		}
		
		return arrayList;
	}
	
}