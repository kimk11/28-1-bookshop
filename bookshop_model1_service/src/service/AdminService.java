package service;

import java.util.ArrayList;
import dao.AdminDAO;
import dto.AdminDTO;
import jdbcObject.JdbcObject;
import jdbcUtil.JdbcUtil;

public class AdminService {
	private AdminDAO adminDAO;
	
	public AdminService() {
		this.adminDAO = new AdminDAO();
	}
	
	//member 테이블 조회(검색)
	public ArrayList<AdminDTO> selectSearchAdminService() {
		ArrayList<AdminDTO> arrayList = new ArrayList<>();
		
		try {
			arrayList = adminDAO.selectAdminList();
			
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
	
	// adminLogin 세션처리
	public AdminDTO selectAdminLoginCheckService(String adminId, String adminPw) {
		AdminDTO adminDTO = null;
		
		try {
			//로그인 입력값 확인
			int check = adminDAO.selectAdminLoginCheck(adminId, adminPw);
			
			//입력값과 db데이터가 같을 경우만 회원 정보를 가져옴
			if(2==check) {
				adminDTO = adminDAO.selectOneAdmin(adminId);
			}
			//DAO에 예외가 없다면 DB에 값 저장, 아니면 db변경사항 취소
			if(null != adminDTO) {
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
		
		return adminDTO;
}
	
	// 수정할 데이터 조회
	public AdminDTO selectOneAdminService(String adminId) {
		AdminDTO adminDTO = new AdminDTO();
		
		try {
			adminDTO = adminDAO.selectOneAdmin(adminId);
			
			//DAO에 예외가 없다면 DB에 값 저장, 아니면 db변경사항 취소
			if(adminDTO.getAdminId() != null) {
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
		return adminDTO;
	}
	
	// 데이터 수정
	public int updateAdminService(AdminDTO adminDTO) {
		//리턴값 담을 변수
		int check = 0;
		
		try {
			 int updateCheck = adminDAO.updateAdmin(adminDTO);
			
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
	
	// admin 테이블 삭제
	public int deleteAdminService(int adminNo) {
		//리턴값 담을 변수
		int check = 0;
			
		try {
			int deleteCheck = adminDAO.deleteAdmin(adminNo);
			
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
	
	
}