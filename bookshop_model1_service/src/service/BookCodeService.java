package service;

import dao.BookCodeDAO;
import jdbcObject.JdbcObject;
import jdbcUtil.JdbcUtil;

public class BookCodeService {

	// 책 카테고리 이름 중복검사 및 등록 메서드
	public int insertBookCodeService(String bookCodeName) {
		// 리턴 결과값을 받은 변수
		int check = 0;
		
		try {
			BookCodeDAO bookCodeDAO = new BookCodeDAO();
			int check1 = bookCodeDAO.selectDuplicateBook(bookCodeName);
			
			
			int check2 = 0;
			// 책 카테고리 이름 중복 확인
			if(1 == check1) {
				check2 = bookCodeDAO.insertBookCode(bookCodeName);
			}
			// 책 카테고리 인설트 성공 여부 확인
			if(1 == check2) {
				// Connection의 요청을 완료하고 특별한 에러가 없다면 결과를 DB에 반영
				JdbcObject.getConnection().commit();
				check = 1;
			} else {
				// Connection 수행 중 예기치 않은 에러가 발생하였다면 모든 과정을 취소하고 DB를 Connection이 수행되기 이전상태로 변경
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