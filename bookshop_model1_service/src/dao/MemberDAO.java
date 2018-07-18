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
	
	//member 테이블 삭제
	// 리턴 0:실패, 1:성공
	public int deleteMember(int memberNo) {

		//회원가입 시 입력한 아이디로 member 테이블의 member_id 컬럼 값을 조회하여 중복확인
		String sql = "DELETE FROM member WHERE member_no = ?";
		
		// 리턴값 0으로 초기화 , 리턴값을 담을 변수
		int check = 0;
		
		try {
			JdbcObject.setConnection(JdbcObject.getConnetionInfo());

			JdbcObject.setPreparedStatement(JdbcObject.getConnection().prepareStatement(sql));
			JdbcObject.getPreparedStatement().setInt(1, memberNo);//회원가입 시 입력한 아이디

			JdbcObject.setResultSet(JdbcObject.getPreparedStatement().executeQuery());
			
			//아이디 삭제 실패 0, 아이디 삭제 성공 1
			if(JdbcObject.getResultSet().next()) {
				check = 1;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return check;
	}
	
	//member 테이블 조회(검색)
	
	public ArrayList<MemberDTO> selectAllMember() {
		
		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
		
		//회원가입 시 입력한 아이디로 member 테이블의 member_id 컬럼 값을 조회하여 중복확인
		String sql = "SELECT member_no, member_id, member_pw, member_name, member_addr, member_point, member_date FROM member";
		
		try {
			JdbcObject.setConnection(JdbcObject.getConnetionInfo());

			JdbcObject.setPreparedStatement(JdbcObject.getConnection().prepareStatement(sql));

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
}