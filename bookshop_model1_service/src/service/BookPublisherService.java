package service;

import dao.BookPublisherDAO;
import dto.BookPublisherDTO;
import jdbcObject.JdbcObject;
import jdbcUtil.JdbcUtil;

public class BookPublisherService {
	
	// publisher 테이블에 데이터 추가
	// 리턴 -> 0 : 실패, 1 : 성공
	public int insertBookPublisherService(BookPublisherDTO bookPublisherDTO) {
		// 리턴결과값을 담을 변수
		int check = 0;
		
		try {
			// BookPublisherDAO 메서드 호출
			BookPublisherDAO bookPublisherDao = new BookPublisherDAO();
			check = bookPublisherDao.insertBookPublisher(bookPublisherDTO);
			
			if(1 == check) {
				// Connection의 요청을 완료하고 특별한 에러가 없다면 결과를 DB에 반영
				JdbcObject.getConnection().commit();
				check=1;
			}else {
				// Connection 수행 중 예기치 않은 에러가 발생하였다면 모든 과정을 취소하고 DB를 Connection이 수행되기 이전상태로 변경
				JdbcUtil.rollback(JdbcObject.getConnection());
			}
		} catch (Exception e) {
			e.printStackTrace();
			JdbcUtil.rollback(JdbcObject.getConnection());
		} finally {
			
			JdbcUtil.close(JdbcObject.getConnection());
			JdbcUtil.close(JdbcObject.getPreparedStatement());
			JdbcUtil.close(JdbcObject.getResultSet());
		}
		
		return check;
	}
	
	// publisher 테이블의 데이터 삭제
	// 리턴 -> 0 : 실패, 1 : 성공
	public int deleteBookPublisherService(int publisherNo) {
		// 리턴결과값을 담을 변수
		int check = 0;
		
		try {
			// BookPublisherDAO 메서드 호출
			BookPublisherDAO bookPublisherDao = new BookPublisherDAO();
			check = bookPublisherDao.deleteBookPublisher(publisherNo);
			
			if(1 == check) {
				// Connection의 요청을 완료하고 특별한 에러가 없다면 결과를 DB에 반영
				JdbcObject.getConnection().commit();
				check=1;
			}else {
				// Connection 수행 중 예기치 않은 에러가 발생하였다면 모든 과정을 취소하고 DB를 Connection이 수행되기 이전상태로 변경
				JdbcUtil.rollback(JdbcObject.getConnection());
			}
		} catch (Exception e) {
			e.printStackTrace();
			JdbcUtil.rollback(JdbcObject.getConnection());
		} finally {
			
			JdbcUtil.close(JdbcObject.getConnection());
			JdbcUtil.close(JdbcObject.getPreparedStatement());
			JdbcUtil.close(JdbcObject.getResultSet());
		}
		
		return check;
	}
}