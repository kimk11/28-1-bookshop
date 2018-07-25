//28기 김진우
//2018/07/18
package service;

import dto.MemberDTO;
import jdbcObject.JdbcObject;
import jdbcUtil.JdbcUtil;
import java.util.ArrayList;
import dao.MemberDAO;
import dao.MemberInterDAO;

public class MemberService {
	private MemberDAO memberDAO;
	private MemberInterDAO memberInterDAO;
	
	public MemberService() {
		this.memberInterDAO = new MemberInterDAO();
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
			int selectCheck = memberDAO.selectIdCheckMember(memberDTO.getMemberId());
			
			//아이디 중복 확인
			if(0 == selectCheck) {
				insertCheck = memberDAO.insertMember(memberDTO);
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
	public int deleteMemberService(int memberNo) {
		//리턴값 담을 변수
		int deleteMemberCheck = 0;
		int deleteMemberInterCheck = 0;
			
		try {
			
			deleteMemberInterCheck = memberInterDAO.deleteMemberInter(memberNo);
			System.out.println(deleteMemberInterCheck+"inter delete");
			
			deleteMemberCheck = memberDAO.deleteMember(memberNo);
			System.out.println(deleteMemberCheck+"member delete");
			
			if(deleteMemberCheck > 0) {
				JdbcObject.getConnection().commit();
				System.out.println("commit ture");
			}else {
				JdbcUtil.rollback(JdbcObject.getConnection());
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			JdbcUtil.rollback(JdbcObject.getConnection());
		} finally {
			
			JdbcUtil.close(JdbcObject.getResultSet());
			JdbcUtil.close(JdbcObject.getPreparedStatement());
			JdbcUtil.close(JdbcObject.getConnection());
		}
		
		return deleteMemberCheck;
	}
	
	//member 테이블 수정
	
	//수정할 데이터 조회
	public MemberDTO selectOneMemberService(int memberNo) {
		MemberDTO memberDTO = new MemberDTO();
		
		try {
			memberDTO = memberDAO.selectMemberForUpdate(memberNo);
			
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
			 int updateCheck = memberDAO.updateMember(memberDTO);
			
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
	public ArrayList<MemberDTO> selectSearchMemberService(String searchKey, String searchValue){
		ArrayList<MemberDTO> arrayList = new ArrayList<>();
		
		try {
			arrayList = memberDAO.selectSearchMember(searchKey, searchValue);
			
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
	
	//memberLogin 세션처리
	public MemberDTO loginMember(String memberId, String memberPw) {
		MemberDTO memberDTO=null;
		
		try {
			//로그인 입력값 확인
			int check = memberDAO.selectLoginCheck(memberId, memberPw);
			
			//입력값과 db데이터가 같을 경우만 회원 정보를 가져옴
			if(2==check) {
				memberDTO = memberDAO.selectOneMember(memberId);
			}
			//DAO에 예외가 없다면 DB에 값 저장, 아니면 db변경사항 취소
			if(null != memberDTO) {
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
		
		return memberDTO;
	}
}