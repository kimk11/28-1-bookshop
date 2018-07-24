package service;

import java.util.ArrayList;

import dao.BookCodeDAO;
import dto.BookCodeDTO;
import jdbcObject.JdbcObject;
import jdbcUtil.JdbcUtil;

public class BookCodeService {

	//책 카테고리 이름 중복검사 및 등록 메서드
	public int insertBookCodeService(String bookCodeName) {
		//리턴값으로 넘겨줄 변수
		int check = 0;
		
		try {
			BookCodeDAO bookCodeDAO = new BookCodeDAO();
			//카테고리 이름의 중복을 확인하는 메서드와 카테고리 입력 메서드 호출
			int check1 = bookCodeDAO.selectDuplicateBook(bookCodeName);
			int check2 = bookCodeDAO.insertBookCode(bookCodeName);
			
			//책 카테고리 이름 중복 확인 and 책 카테고리 입력 성공 여부 확인
			if(1 == check1 && 1 ==check2) {
				// Connection의 요청을 완료하고 특별한 에러가 없다면 결과를 DB에 반영
				JdbcObject.getConnection().commit();
				check = 1;
			} else {
				//Connection 수행 중 예기치 않은 에러가 발생하였다면 모든 과정을 취소하고 DB를 Connection이 수행되기 이전상태로 변경
				JdbcUtil.rollback(JdbcObject.getConnection());
			}
		} catch (Exception e) {
			//TODO: handle exception
			e.printStackTrace();
			JdbcUtil.rollback(JdbcObject.getConnection());
		} finally {
			
			JdbcUtil.close(JdbcObject.getResultSet());
			JdbcUtil.close(JdbcObject.getPreparedStatement());
			JdbcUtil.close(JdbcObject.getConnection());				
		}
		System.out.println(check+"<--Service 카테고리 입력 메서드 체크");
		//리턴값이 0=실패, 1=성공
		return check;
	}
	
	//책 카테고리 리스트처리 Service메서드
	public ArrayList<BookCodeDTO> selectBookCodeListService() {
		//리턴 결과값을 받은 변수
		ArrayList<BookCodeDTO> bookCodeList = null;
		
		try {
			BookCodeDAO bookCodeDAO = new BookCodeDAO();
			//책 카테고리의 정보들을 전부 검색하는 메서드를 호출하고 그 정보들을 담은 객체의 주소값을 리턴값으로 받는다
			bookCodeList = bookCodeDAO.selectBookCodeList();
			
			//배열객체의 주소값이 있으면 커밋
			if(null != bookCodeList) {
				// Connection의 요청을 완료하고 특별한 에러가 없다면 결과를 DB에 반영
				JdbcObject.getConnection().commit();
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
		System.out.println(bookCodeList+"<-- Service카테고리 리스트 메서드 체크");
		//리턴값은 카테고리들의 정보를 담은 객체의 주소값
		return bookCodeList;
	}
	
	//하나의 책 카테고리 검색 Service메서드(특정 카테고리를 식별하기위해 bookCodeNo를 매개변수로 받는다)
	public BookCodeDTO selectBookCodeService(int bookCodeNo) {
		//카테고리의 정보를 담을 객체참존변수
		BookCodeDTO bookCodeDTO = null;
		
		try {
			BookCodeDAO bookCodeDAO = new BookCodeDAO();
			//카테고리 테이블의 No를 매개변수로 하나의 카테고리를 검색하는 메서드를 호출, 카테고리의 정보를 담은 객체의 주소값을 리턴받는다 
			bookCodeDTO = bookCodeDAO.selectBookCode(bookCodeNo);
			
			//객체의 주소값이 null이 아니면 커밋
			if(null != bookCodeDTO) {
				// Connection의 요청을 완료하고 특별한 에러가 없다면 결과를 DB에 반영
				JdbcObject.getConnection().commit();
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
		System.out.println(bookCodeDTO+"<-- Service카테고리 수정 메서드 체크");
		//리턴값은 카테고리의 정보를 담을 객체참존변수
		return bookCodeDTO;
	}
	
	//책 카테고리 수정 처리 Service메서드(매개변수로 카테고리의 정보가 담겨있는 객체의 주소값을 받음)
	public int updateBookCodeService(BookCodeDTO bookCodeDTO) {
		//리턴 결과값을 받은 변수
		int check = 0;
		
		try {
			BookCodeDAO bookCodeDAO = new BookCodeDAO();
			//카테고리 이름의 중복을 확인하는 메서드와 카테고리 입력 메서드 호출
			check = bookCodeDAO.updateBookCode(bookCodeDTO);
			
			// 책 카테고리 이름 중복 확인 and 책 카테고리 입력 성공 여부 확인
			if(1 == check) {
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
		System.out.println(check+"<-- Service카테고리 수정 메서드 체크");
		//리턴값이 0=실패, 1=성공
		return check;
	}
	
	//책 카테고리 삭제 처리 Service메서드(식별하기위해 매개변수로 카테고리의 No를 받음)
	public int deleteBookCodeService(int bookCodeNo) {
		//리턴 결과값을 받은 변수
		int check = 0;
		
		try {
			BookCodeDAO bookCodeDAO = new BookCodeDAO();
			//카테고리의 No를 매개변수로 카테고리의 정보를 삭제하는 메서드를 호출, 처리에 대한 리턴값을 리턴받는다
			check = bookCodeDAO.deleteBookCode(bookCodeNo);
			
			//리턴값이 1이면 커밋을 한다
			if(1 == check) {
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
		System.out.println(check+"<-- Service카테고리 삭제 메서드 체크");
		//리턴값이 0=실패, 1=성공
		return check;
	}
}