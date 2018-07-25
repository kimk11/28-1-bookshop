//28기 현희문
//2018.07.18(수)/

package dao;

import dto.MemberDTO;
import jdbcObject.JdbcObject;
import java.util.ArrayList;

public class MemberDAO {
	
	// 회원 가입 메서드
	// 리턴 0:실패, 1:성공
	public int insertMember(MemberDTO memberDTO) {

		// 리턴값 0으로 초기화 , 리턴값을 담을 변수
		int check = 0;

		// 출판사 이름과 사이트를 등록한다.
		String sql = "INSERT INTO member(member_id, member_pw, member_name, member_addr, member_date) VALUES(?, ?, ?, ?, now())";

		try {
			JdbcObject.setConnection(JdbcObject.getConnetionInfo());

			JdbcObject.setPreparedStatement(JdbcObject.getConnection().prepareStatement(sql));
			JdbcObject.getPreparedStatement().setString(1, memberDTO.getMemberId());//회원가입 시 입력한 아이디
			JdbcObject.getPreparedStatement().setString(2, memberDTO.getMemberPw());//회원가입 시 입력한 비밀번호
			JdbcObject.getPreparedStatement().setString(3, memberDTO.getMemberName());//회원가입 시 입력한 이름
			JdbcObject.getPreparedStatement().setString(4, memberDTO.getMemberAddr());//회원가입 시 입력한 주소
			
			//쿼리 실행 실패 0, 쿼리 실행 성공 1
			check = JdbcObject.getPreparedStatement().executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return check;
	}
	
	//아이디 중복확인 메서드
	// 리턴 0:실패, 1:성공
	public int selectIdCheckMember(String memberId) {

		//회원가입 시 입력한 아이디로 member 테이블의 member_id 컬럼 값을 조회하여 중복확인
		String sql = "SELECT member_id FROM member WHERE member_id = ?";
		
		// 리턴값 0으로 초기화 , 리턴값을 담을 변수
		int check = 0;
		
		try {
			JdbcObject.setConnection(JdbcObject.getConnetionInfo());

			JdbcObject.setPreparedStatement(JdbcObject.getConnection().prepareStatement(sql));
			JdbcObject.getPreparedStatement().setString(1, memberId);//회원가입 시 입력한 아이디

			JdbcObject.setResultSet(JdbcObject.getPreparedStatement().executeQuery());
			
			//아이디 중복 X 0, 아이디 중복O 1
			if(JdbcObject.getResultSet().next()) {
				check = 1;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return check;
	}
	
	//회원정보 수정을 위한 member 테이블 전체 조회
	//리턴 값 memberDTO = 수정할 회원 번호를 받아 member 테이블을 조회한 값들을 세팅된 MemberDTO 객체의 객체참조변수
	public MemberDTO selectMemberForUpdate(int memberNo) {

		MemberDTO memberDTO = new MemberDTO();
		
		//회원가입 시 입력한 아이디로 member 테이블의 member_id 컬럼 값을 조회하여 중복확인
		String sql = "SELECT member_no, member_id, member_pw, member_name, member_addr, member_point, member_date FROM member WHERE member_no = ?";
		
		try {
			JdbcObject.setConnection(JdbcObject.getConnetionInfo());

			JdbcObject.setPreparedStatement(JdbcObject.getConnection().prepareStatement(sql));
			JdbcObject.getPreparedStatement().setInt(1, memberNo);//회원 번호

			JdbcObject.setResultSet(JdbcObject.getPreparedStatement().executeQuery());
			
			//아이디 중복 X 0, 아이디 중복O 1
			if(JdbcObject.getResultSet().next()) {
				memberDTO.setMemberNo(JdbcObject.getResultSet().getInt("member_no"));
				memberDTO.setMemberId(JdbcObject.getResultSet().getString("member_id"));
				memberDTO.setMemberPw(JdbcObject.getResultSet().getString("member_pw"));
				memberDTO.setMemberName(JdbcObject.getResultSet().getString("member_name"));
				memberDTO.setMemberAddr(JdbcObject.getResultSet().getString("member_addr"));
				memberDTO.setMemberPoint(JdbcObject.getResultSet().getInt("member_point"));
				memberDTO.setMemberDate(JdbcObject.getResultSet().getString("member_date"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return memberDTO;
	}
	
	//회원 정보 수정 메서드
	// 리턴 0:실패, 1:성공
	public int updateMember(MemberDTO memberDTO) {
		
		String sql = "UPDATE member SET member_pw = ?, member_addr = ? WHERE member_no = ?;";
		
		// 리턴값 0으로 초기화 , 리턴값을 담을 변수
		int check = 0;
		
		try {
			JdbcObject.setConnection(JdbcObject.getConnetionInfo());

			JdbcObject.setPreparedStatement(JdbcObject.getConnection().prepareStatement(sql));
			JdbcObject.getPreparedStatement().setString(1, memberDTO.getMemberPw());//수정화면에서 입력한 비밀번호
			JdbcObject.getPreparedStatement().setString(2, memberDTO.getMemberAddr());//수정화면에서 입력한 주소
			JdbcObject.getPreparedStatement().setInt(3, memberDTO.getMemberNo());

			check = JdbcObject.getPreparedStatement().executeUpdate();
			
			//수정 실패 0, 수정 성공 1
			if(JdbcObject.getResultSet().next()) {
				check = 1;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return check;
	}
	
	//member 테이블 삭제
	// 리턴 0:실패, 1:성공
	public int deleteMember(int memberNo) {
		System.out.println("deleteMember");
		//회원가입 시 입력한 아이디로 member 테이블의 member_id 컬럼 값을 조회하여 중복확인
		String sql = "DELETE FROM member WHERE member_no = ?";
		
		// 리턴값 0으로 초기화 , 리턴값을 담을 변수
		int check = 0;
		
		try {
			JdbcObject.setConnection(JdbcObject.getConnetionInfo());

			JdbcObject.setPreparedStatement(JdbcObject.getConnection().prepareStatement(sql));
			JdbcObject.getPreparedStatement().setInt(1, memberNo);//회원가입 시 입력한 아이디

			check = JdbcObject.getPreparedStatement().executeUpdate();
			
			//삭제 실패 0, 삭제 성공 1
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return check;
	}
	
	//member 테이블 조회(검색)
	//리턴 값 list = member 테이블을 조회한 값들이 세팅 된 MemberDTO 객체의 객체참조변수들이 들어있는 ArrayList 객체의 객체참조변수
	public ArrayList<MemberDTO> selectSearchMember(String searchKey, String searchValue) {
		
		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
		
		//회원가입 시 입력한 아이디로 member 테이블의 member_id 컬럼 값을 조회하여 중복확인
		String sql1 = "SELECT member_no, member_id, member_pw, member_name, member_addr, member_point, member_date FROM member ORDER BY member_no DESC";
		String sql2 = "SELECT member_no, member_id, member_pw, member_name, member_addr, member_point, member_date FROM member WHERE member_id LIKE ? ORDER BY member_no DESC";
		String sql3 = "SELECT member_no, member_id, member_pw, member_name, member_addr, member_point, member_date FROM member WHERE member_name LIKE ? ORDER BY member_no DESC";
		
		try {
			JdbcObject.setConnection(JdbcObject.getConnetionInfo());
			
			if(searchKey.equals("") && searchValue.equals("")) {
				JdbcObject.setPreparedStatement(JdbcObject.getConnection().prepareStatement(sql1));
			}else if(searchKey.equals("") && !searchValue.equals("")) {
				sql1 = "SELECT member_no, member_id, member_pw, member_name, member_addr, member_point, member_date FROM member WHERE member_no = 0 ORDER BY member_no DESC";
				JdbcObject.setPreparedStatement(JdbcObject.getConnection().prepareStatement(sql1));
			}else if(searchKey.equals("memberId") && searchValue.equals("")) {
				sql1 = "SELECT member_no, member_id, member_pw, member_name, member_addr, member_point, member_date FROM member WHERE member_no = 0 ORDER BY member_no DESC";
				JdbcObject.setPreparedStatement(JdbcObject.getConnection().prepareStatement(sql1));
			}else if(searchKey.equals("memberId") && !searchValue.equals("")) {
				JdbcObject.setPreparedStatement(JdbcObject.getConnection().prepareStatement(sql2));
				JdbcObject.getPreparedStatement().setString(1, "%"+searchValue+"%");
			
			}else if(searchKey.equals("memberName") && searchValue.equals("")) {
				sql1 = "SELECT member_no, member_id, member_pw, member_name, member_addr, member_point, member_date FROM member WHERE member_no = 0 ORDER BY member_no DESC";
				JdbcObject.setPreparedStatement(JdbcObject.getConnection().prepareStatement(sql1));
			}else if(searchKey.equals("memberName") && !searchValue.equals("")) {
				JdbcObject.setPreparedStatement(JdbcObject.getConnection().prepareStatement(sql3));
				JdbcObject.getPreparedStatement().setString(1, "%"+searchValue+"%");
			}

			JdbcObject.setResultSet(JdbcObject.getPreparedStatement().executeQuery());
			
			//아이디 중복 X 0, 아이디 중복O 1
			while(JdbcObject.getResultSet().next()) {
				MemberDTO memberDTO = new MemberDTO();
				memberDTO.setMemberNo(JdbcObject.getResultSet().getInt("member_no"));
				memberDTO.setMemberId(JdbcObject.getResultSet().getString("member_id"));
				memberDTO.setMemberPw(JdbcObject.getResultSet().getString("member_pw"));
				memberDTO.setMemberName(JdbcObject.getResultSet().getString("member_name"));
				memberDTO.setMemberAddr(JdbcObject.getResultSet().getString("member_addr"));
				memberDTO.setMemberPoint(JdbcObject.getResultSet().getInt("member_point"));
				memberDTO.setMemberDate(JdbcObject.getResultSet().getString("member_date"));
				list.add(memberDTO);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	
	//memberLogin메서드
	//리턴값 0:아이디 불일치, 1:비번불일치, 2:로그인 성공
	public int selectLoginCheck(String memberId, String memberPw) {

		String sql = "SELECT member_pw FROM member WHERE member_id=?";
		
		int check = 0;
		
		try {
			JdbcObject.setConnection(JdbcObject.getConnetionInfo());

			JdbcObject.setPreparedStatement(JdbcObject.getConnection().prepareStatement(sql));
			JdbcObject.getPreparedStatement().setString(1, memberId);

			JdbcObject.setResultSet(JdbcObject.getPreparedStatement().executeQuery());
			
			//삭제 실패 0, 삭제 성공 1
			if(JdbcObject.getResultSet().next()) {
				check = 1;
				if(JdbcObject.getResultSet().getString(1).equals(memberPw)) {
					check=2;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return check;
	}
	
	//member정보를 가져오기 위한 메서드
	//id로 조회
	public MemberDTO selectOneMember(String memberId) {
		MemberDTO memberDTO = new MemberDTO();
		String sql = "SELECT member_no,member_id,member_pw,member_name,member_addr,member_point,member_date FROM member WHERE member_id=?";
		
		try {
			JdbcObject.setConnection(JdbcObject.getConnetionInfo());

			JdbcObject.setPreparedStatement(JdbcObject.getConnection().prepareStatement(sql));
			JdbcObject.getPreparedStatement().setString(1, memberId);

			JdbcObject.setResultSet(JdbcObject.getPreparedStatement().executeQuery());
			
			//삭제 실패 0, 삭제 성공 1
			if(JdbcObject.getResultSet().next()) {
				memberDTO.setMemberNo(JdbcObject.getResultSet().getInt(1));
				memberDTO.setMemberId(JdbcObject.getResultSet().getString(2));
				memberDTO.setMemberPw(JdbcObject.getResultSet().getString(3));
				memberDTO.setMemberName(JdbcObject.getResultSet().getString(4));
				memberDTO.setMemberAddr(JdbcObject.getResultSet().getString(5));
				memberDTO.setMemberPoint(JdbcObject.getResultSet().getInt(6));
				memberDTO.setMemberDate(JdbcObject.getResultSet().getString(7));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return memberDTO;
	}
}