package service;

import dao.BookPublisherDAO;
import dto.BookPublisherDTO;
import jdbcObject.JdbcObject;
import jdbcUtil.JdbcUtil;

public class BookPublisherService {
	
	// publisher 테이블에 데이터 추가
	// 리턴 -> 0 : 실패, 1 : 성공
	public int insertBookPublisherService(BookPublisherDTO bookPublisherDTO) {
		//System.out.println(bookPublisherDTO.getPubliserName()+"service");
		//System.out.println(bookPublisherDTO.getPublisherWebsite()+"service");
		// 리턴결과값을 담을 변수
		int check = 0;
		
		try {
			// BookPublisherDAO 메서드 호출
			BookPublisherDAO bookPublisherDao = new BookPublisherDAO();
			int check1 = bookPublisherDao.selectOneBookPublisher(bookPublisherDTO.getPubliserName());
			System.out.println(check1+"check1");
			
			int check2 =0;
			//출판사 중복 확인
			if(0==check1) {
				check2 = bookPublisherDao.insertBookPublisher(bookPublisherDTO);
				System.out.println(check2+"check2");
			}
			
			//insert메서드 성공 여부 확인
			if(0 != check2) {
				// Connection의 요청을 완료하고 특별한 에러가 없다면 결과를 DB에 반영
				JdbcObject.getConnection().commit();
				check = 1;
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
			int check1 = bookPublisherDao.deleteBookPublisher(publisherNo);
			
			if(1 == check1) {
				// Connection의 요청을 완료하고 특별한 에러가 없다면 결과를 DB에 반영
				JdbcObject.getConnection().commit();
				check = 1;
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