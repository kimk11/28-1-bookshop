package dao;

import dto.AdminDTO;
import jdbcObject.JdbcObject;
import java.util.ArrayList;

public class AdminDAO {
	
	//관리자 로그인 메서드
	//리턴값 0:아이디 불일치, 1:비번불일치, 2:로그인 성공
	public int selectAdminLoginCheck(String adminId, String adminPw) {

		String sql = "SELECT admin_pw FROM admin WHERE admin_id=?";
		
		int check = 0;
		
		try {
			JdbcObject.setConnection(JdbcObject.getConnetionInfo());

			JdbcObject.setPreparedStatement(JdbcObject.getConnection().prepareStatement(sql));
			JdbcObject.getPreparedStatement().setString(1, adminId);

			JdbcObject.setResultSet(JdbcObject.getPreparedStatement().executeQuery());
			
			//실패시 리턴값=0,아이디 일치 리턴값=1, 그 해당하는 아이디 비번 일치 리턴값=2
			if(JdbcObject.getResultSet().next()) {
				check = 1;
				if(JdbcObject.getResultSet().getString(1).equals(adminPw)) {
					check=2;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return check;
	}
	
	//한명의 관리자를 조회하는 메서드
	//관리자 컬럼 admin_no값을 넘김
	public AdminDTO selectOneAdmin(int AdminNo) {
		AdminDTO adminDTO = new AdminDTO();
		
		String sql = "SELECT admin_no, admin_id, admin_pw , admin_name FROM admin WHERE admin_no=?";
		
		try {
			JdbcObject.setConnection(JdbcObject.getConnetionInfo());

			JdbcObject.setPreparedStatement(JdbcObject.getConnection().prepareStatement(sql));
			JdbcObject.getPreparedStatement().setInt(1, AdminNo);

			JdbcObject.setResultSet(JdbcObject.getPreparedStatement().executeQuery());
			
			if(JdbcObject.getResultSet().next()) {
				adminDTO.setAdminNo(JdbcObject.getResultSet().getInt(1));
				adminDTO.setAdminId(JdbcObject.getResultSet().getString(2));
				adminDTO.setAdminPw(JdbcObject.getResultSet().getString(3));
				adminDTO.setAdminName(JdbcObject.getResultSet().getString(4));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return adminDTO;
	}
	
	//관리자 수정 메서드
	public int updateAdmin(AdminDTO adminDTO) {
		
		String sql = "UPDATE admin SET admin_pw=?, admin_name=? WHERE admin_no=?";
		
		// 리턴값 0으로 초기화 , 리턴값을 담을 변수
		int check = 0;
				
		try {
			JdbcObject.setConnection(JdbcObject.getConnetionInfo());
			JdbcObject.setPreparedStatement(JdbcObject.getConnection().prepareStatement(sql));
			JdbcObject.getPreparedStatement().setString(1, adminDTO.getAdminPw());//수정화면에서 입력한 비밀번호
			JdbcObject.getPreparedStatement().setString(2, adminDTO.getAdminName() );//수정화면에서 입력한 이름
				JdbcObject.getPreparedStatement().setInt(3, adminDTO.getAdminNo());

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
	
	//관리자 삭제 메서드
	public int deleteAdmin(int adminNo) {
		System.out.println("deleteAdmin");
		//회원가입 시 입력한 아이디로 admin 테이블의 admin_id 컬럼 값을 조회하여 중복확인
		String sql = "DELETE FROM member WHERE admin_no = ?";
		
		// 리턴값 0으로 초기화 , 리턴값을 담을 변수
		int check = 0;
				
		try {
			JdbcObject.setConnection(JdbcObject.getConnetionInfo());
			JdbcObject.setPreparedStatement(JdbcObject.getConnection().prepareStatement(sql));

			JdbcObject.getPreparedStatement().setInt(1, adminNo);//관리자로 회원가입시 데이터베이스에 등록된 관리자 번호
			check = JdbcObject.getPreparedStatement().executeUpdate();
					
			//삭제 실패 0, 삭제 성공 1
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return check;
	}

}
