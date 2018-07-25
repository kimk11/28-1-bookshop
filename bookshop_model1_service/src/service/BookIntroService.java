package service;

import java.util.ArrayList;

import dao.BookIntroDAO;
import dto.BookIntroDTO;
import jdbcObject.JdbcObject;
import jdbcUtil.JdbcUtil;

public class BookIntroService {
	//책 소개 입력을 처리하는 Service메서드(책 소개 정보들이 저장되어있는 객체의 참조값을 매개변수로 받음)
	public int insertBookIntroService(BookIntroDTO bookIntroDTO) {
		//리턴결과값을 담을 변수
		int check = 0;
		
		try {
			BookIntroDAO bookIntroDAO = new BookIntroDAO();
			//책 소개 정보가 담겨있는 객체의 참조값을 매개변수로 책 소개 정보를 db에 저장하는 메서드를 호출, 처리에 대한 결과값을 리턴받는다.
			check = bookIntroDAO.insertBookIntro(bookIntroDTO);
			
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
		System.out.println(check+"<--insertBookIntroService 메서드 처리 성공 여부");
		//리턴값이 0=실패, 1=성공
		return check;
	}
	
	//책 하나의 소개정보를 검색하는 Service메서드(책 소개테이블의 기본키가되는 bookIntroNo를 매개변수로 받음)
	public BookIntroDTO selectBookIntroService(int bookIntroNo) {
		//리턴결과값을 담을 변수
		BookIntroDTO bookintroDTO = null;
		
		try {
			BookIntroDAO bookIntroDAO = new BookIntroDAO();
			//책을 식별하기위해 bookNo를 매개변수로 책 소개 정보를 검색하는 메서드를 호출, 처리에 대한 결과값을 리턴받는다.
			bookintroDTO = bookIntroDAO.selectBookIntro(bookIntroNo);
			
			if(null != bookintroDTO) {
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
		System.out.println(bookintroDTO+"<--selectBookIntroService 메서드 처리 성공 여부");
		//책소개정보들이 저장된 객체의 주소값
		return bookintroDTO;
	}
	
	//책 소개정보의 내용을 리스트처리하는 Service메서드
	public ArrayList<BookIntroDTO> selectBookIntroListService(int bookNo) {
		//리턴결과값을 담을 변수
		ArrayList<BookIntroDTO> bookIntroList = null;
		
		try {
			BookIntroDAO bookIntroDAO = new BookIntroDAO();
			//책 소개정보를 리스트 처리하는 메서드 호출, 처리에 대한 결과값을 리턴받는다.
			bookIntroList = bookIntroDAO.selectBookIntroList(bookNo);
			
			if(bookIntroList.size() != 0) {
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
		System.out.println(bookIntroList+"<--selectBookIntroListService 메서드 처리 성공 여부");
		//리턴값은 책소개정보들이 저장된 배열객체의 주소값
		return bookIntroList;
	}
	
	//책 소개정보 수정을 처리하는 Service메서드(책 소개정보들이 저장되어있는 객체의 참조값을 매개변수로 받음)
	public int updateBookIntroService(BookIntroDTO bookIntroDTO) {
		//리턴결과값을 담을 변수
		int check = 0;
		
		try {
			BookIntroDAO bookIntroDAO = new BookIntroDAO();
			//책 소개정보가 담겨있는 객체의 참조값을 매개변수로 책 소개 정보를 db에 수정하는 메서드를 호출, 처리에 대한 결과값을 리턴받는다.
			check = bookIntroDAO.updateBookIntro(bookIntroDTO);
			
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
		System.out.println(check+"<--updateBookIntroService 메서드 처리 성공 여부");
		//리턴값이 0=실패, 1=성공
		return check;
	}
	
	//책 소개정보 삭제를 처리하는 Service메서드(책 소개테이블의 기본키가되는 bookIntroNo를 매개변수로 받음)
	public int deleteBookIntroService(int bookIntroNo) {
		//리턴결과값을 담을 변수
		int check = 0;
		
		try {
			BookIntroDAO bookIntroDAO = new BookIntroDAO();
			//책을 식별하기위해 bookNo를 매개변수로 책 소개정보를 삭제하는 메서드 호출, 처리에 대한 결과값을 리턴받는다.
			check = bookIntroDAO.deleteBookIntro(bookIntroNo);
			
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
		System.out.println(check+"<--deleteBookIntroService 메서드 처리 성공 여부");
		//리턴값이 0=실패, 1=성공
		return check;
	}
}
