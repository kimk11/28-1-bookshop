package service;

import dto.BookReviewDTO;
import dto.BookMemberJoinDTO;
import jdbcObject.JdbcObject;
import jdbcUtil.JdbcUtil;
import java.util.ArrayList;
import dao.BookReviewDAO;

public class BookReviewService {
	//책 리뷰 입력을 처리하는 Service메서드(책 리뷰 정보들이 저장되어있는 객체의 참조값을 매개변수로 받음)
	public int insertBookReviewService(BookReviewDTO bookReviewDTO) {
		//리턴결과값을 담을 변수
		int check = 0;
		
		try {
			BookReviewDAO bookReviewDAO = new BookReviewDAO();
			//책 리뷰 정보가 담겨있는 객체의 참조값을 매개변수로 책 소개 정보를 db에 저장하는 메서드를 호출, 처리에 대한 결과값을 리턴받는다.
			check = bookReviewDAO.insertBookReview(bookReviewDTO);
			
			if(1 == check) {
				//Connection의 요청을 완료하고 특별한 에러가 없다면 결과를 DB에 반영
				JdbcObject.getConnection().commit();
				check=1;
			}else {
				//Connection 수행 중 예기치 않은 에러가 발생하였다면 모든 과정을 취소하고 DB를 Connection이 수행되기 이전상태로 변경
				JdbcUtil.rollback(JdbcObject.getConnection());
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			JdbcUtil.rollback(JdbcObject.getConnection());
		} finally {
			JdbcUtil.close(JdbcObject.getResultSet());
			JdbcUtil.close(JdbcObject.getPreparedStatement());
			JdbcUtil.close(JdbcObject.getConnection());
		}
		System.out.println(check+"<--insertBookReviewService 메서드 처리 성공 여부");
		// 리턴값이 0=실패, 1=성공
		return check;
	}
	
	//책 하나의 리뷰정보를 검색하는 Service메서드(책 테이블의 기본키가되는 bookNo를 매개변수로 받음)
	public BookReviewDTO selectBookReviewService(int bookReviewNo) {
		BookReviewDTO bookReviewDTO = null;
		
		try {
			BookReviewDAO bookReviewDAO = new BookReviewDAO();
			//책을 식별하기위해 bookNo를 매개변수로 책 리뷰 정보를 검색하는 메서드를 호출, 처리에 대한 결과값을 리턴받는다.
			bookReviewDTO = bookReviewDAO.selectBookReview(bookReviewNo);
			
			if(null != bookReviewDTO) {
				//Connection의 요청을 완료하고 특별한 에러가 없다면 결과를 DB에 반영
				JdbcObject.getConnection().commit();
			}else {
				//Connection 수행 중 예기치 않은 에러가 발생하였다면 모든 과정을 취소하고 DB를 Connection이 수행되기 이전상태로 변경
				JdbcUtil.rollback(JdbcObject.getConnection());
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			JdbcUtil.rollback(JdbcObject.getConnection());
		} finally {
			JdbcUtil.close(JdbcObject.getResultSet());
			JdbcUtil.close(JdbcObject.getPreparedStatement());
			JdbcUtil.close(JdbcObject.getConnection());
		}
		System.out.println(bookReviewDTO+"<--selectBookReviewService 메서드 처리 성공 여부");
		//책리뷰 정보들이 저장된 객체의 주소값
		return bookReviewDTO;
	}
	
	//책 리뷰정보의 내용을 리스트처리하는 Service메서드
	public ArrayList<BookMemberJoinDTO> selectBookReviewListService(int bookNo) {
		//리턴결과값을 담을 변수
		ArrayList<BookMemberJoinDTO> bookReviewList = null;
		
		try {
			BookReviewDAO bookReviewDAO = new BookReviewDAO();
			//책 리뷰정보를 리스트 처리하는 메서드 호출, 처리에 대한 결과값을 리턴받는다.
			bookReviewList = bookReviewDAO.selectBookReviewList(bookNo);
			
			if(bookReviewList.size() != 0) {
				//Connection의 요청을 완료하고 특별한 에러가 없다면 결과를 DB에 반영
				JdbcObject.getConnection().commit();
			}else {
				//Connection 수행 중 예기치 않은 에러가 발생하였다면 모든 과정을 취소하고 DB를 Connection이 수행되기 이전상태로 변경
				JdbcUtil.rollback(JdbcObject.getConnection());
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			JdbcUtil.rollback(JdbcObject.getConnection());
		} finally {
			JdbcUtil.close(JdbcObject.getResultSet());
			JdbcUtil.close(JdbcObject.getPreparedStatement());
			JdbcUtil.close(JdbcObject.getConnection());
		}
		System.out.println(bookReviewList+"<--selectBookReviewListService 메서드 처리 성공 여부");
		//리턴값은 책리뷰정보들이 저장된 배열객체의 주소값
		return bookReviewList;
	}
	
	//책 리뷰정보 수정을 처리하는 Service메서드(책 리뷰정보들이 저장되어있는 객체의 참조값을 매개변수로 받음)
	public int updateBookReviewService(BookReviewDTO bookReviewDTO) {
		//리턴결과값을 담을 변수
		int check = 0;
		
		try {
			BookReviewDAO bookReviewDAO = new BookReviewDAO();
			//책 리뷰정보가 담겨있는 객체의 참조값을 매개변수로 책 소개 정보를 db에 수정하는 메서드를 호출, 처리에 대한 결과값을 리턴받는다.
			check = bookReviewDAO.updateBookReview(bookReviewDTO);
			
			if(1 == check) {
				//Connection의 요청을 완료하고 특별한 에러가 없다면 결과를 DB에 반영
				JdbcObject.getConnection().commit();
				check=1;
			}else {
				//Connection 수행 중 예기치 않은 에러가 발생하였다면 모든 과정을 취소하고 DB를 Connection이 수행되기 이전상태로 변경
				JdbcUtil.rollback(JdbcObject.getConnection());
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			JdbcUtil.rollback(JdbcObject.getConnection());
		} finally {
			JdbcUtil.close(JdbcObject.getResultSet());
			JdbcUtil.close(JdbcObject.getPreparedStatement());
			JdbcUtil.close(JdbcObject.getConnection());
		}
		System.out.println(check+"<--updateBookReviewService 메서드 처리 성공 여부");
		//리턴값이 0=실패, 1=성공
		return check;
	}
	
	//책 리뷰정보 삭제를 처리하는 Service메서드(책 테이블의 기본키가되는 bookNo를 매개변수로 받음)
	public int deleteBookReviewService(BookReviewDTO bookReviewDTO) {
		//리턴결과값을 담을 변수
		int check = 0;
		
		try {
			BookReviewDAO bookReviewDAO = new BookReviewDAO();
			//책을 식별하기위해 bookNo를 매개변수로 책 리뷰정보를 삭제하는 메서드 호출, 처리에 대한 결과값을 리턴받는다.
			check = bookReviewDAO.deleteBookReview(bookReviewDTO);
			
			if(1 == check) {
				//Connection의 요청을 완료하고 특별한 에러가 없다면 결과를 DB에 반영
				JdbcObject.getConnection().commit();
				check=1;
			}else {
				//Connection 수행 중 예기치 않은 에러가 발생하였다면 모든 과정을 취소하고 DB를 Connection이 수행되기 이전상태로 변경
				JdbcUtil.rollback(JdbcObject.getConnection());
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			JdbcUtil.rollback(JdbcObject.getConnection());
		} finally {
			JdbcUtil.close(JdbcObject.getResultSet());
			JdbcUtil.close(JdbcObject.getPreparedStatement());
			JdbcUtil.close(JdbcObject.getConnection());
		}
		System.out.println(check+"<--deleteBookReviewService 메서드 처리 성공 여부");
		//리턴값이 0=실패, 1=성공
		return check;
	}
}