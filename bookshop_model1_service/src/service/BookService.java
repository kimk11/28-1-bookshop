package service;

import java.util.ArrayList;

import dao.BookCodeDAO;
import dao.BookDAO;
import dao.BookPublisherDAO;
import dto.BookArrayListJoinDTO;
import dto.BookCodeDTO;
import dto.BookDTO;
import dto.BookPublisherDTO;
import jdbcObject.JdbcObject;
import jdbcUtil.JdbcUtil;

public class BookService {
	
	//책 정보 입력을 처리하는 Service메서드
	public int insertBookService(BookDTO bookDTO) {
		//리턴결과값을 담을 변수
		int check = 0;
		
		try {
			BookDAO bookDAO = new BookDAO();
			//책 정보가 담겨있는 객체의 주소값을 매개변수로 책 정보를 db에 저장하는 메서드를 호출, 처리에 대한 결과값을 리턴받는다.
			check = bookDAO.insertBook(bookDTO);
			
			if(1 == check) {
				//Connection의 요청을 완료하고 특별한 에러가 없다면 결과를 DB에 반영
				JdbcObject.getConnection().commit();
				check=1;
			}else {
				//Connection 수행 중 예기치 않은 에러가 발생하였다면 모든 과정을 취소하고 DB를 Connection이 수행되기 이전상태로 변경
				JdbcUtil.rollback(JdbcObject.getConnection());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			JdbcUtil.rollback(JdbcObject.getConnection());
		} finally {
			JdbcUtil.close(JdbcObject.getResultSet());
			JdbcUtil.close(JdbcObject.getPreparedStatement());
			JdbcUtil.close(JdbcObject.getConnection());
		}
		System.out.println(check+"<--service메서드 처리 성공 여부");
		//리턴값이 0=실패, 1=성공
		return check;
	}
	
	//출판사들의 정보와 책 카테고리들의 정보를 검색하는 메서드
	public BookArrayListJoinDTO selectBookCodePublisherListService() {
		BookArrayListJoinDTO bookJoin = new BookArrayListJoinDTO();
		
		try {
			BookCodeDAO bookCodeDAO = new BookCodeDAO();
			//책 카테고리들을 전체검색하는 메서드 호출, 리턴값으로 카테고리들의 정보가 담긴 객체의 주소값을 저장한 배열객체의 참조값을 리턴한다.
			ArrayList<BookCodeDTO> bookCodeList = bookCodeDAO.selectBookCodeList();
			
			//배열객체의 주소값을 매개변수로 참조값을 저장하는 메서드 호출
			bookJoin.setBookCodeList(bookCodeList);
			
			if(null != bookCodeList) {
				BookPublisherDAO bookPublisherDAO = new BookPublisherDAO();
				//출판사들을 전체검색하는 메서드 호출, 리턴값으로 출판사들의 정보가 담긴 객체의 참조값을 저장한 배열객체의 참조값을 리턴한다.
				ArrayList<BookPublisherDTO> bookPublisherList = bookPublisherDAO.selectAllBookPublisher();
				
				//배열객체의 주소값을 매개변수로 참조값을 저장하는 메서드 호출
				bookJoin.setBookPublisherList(bookPublisherList);
				
				if(null != bookPublisherList) {
					//Connection의 요청을 완료하고 특별한 에러가 없다면 검색 결과를 커밋
					JdbcObject.getConnection().commit();
					
				} else {
					//Connection 수행 중 예기치 않은 에러가 발생하였다면 모든 과정을 취소하고 DB를 Connection이 수행되기 이전상태로 변경
					JdbcUtil.rollback(JdbcObject.getConnection());
				}
			} else {
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
		System.out.println(bookJoin+"<--service 메서드 처리 성공 여부");
		//리턴값은 배열객체들이 저장된 객체
		return bookJoin;
	}
	
/*	//책 소개 입력을 처리하는 Service메서드
	public int insertBookIntroService(BookIntroDAO bookIntroDAO) {
		//리턴결과값을 담을 변수
		int check = 0;
		
		try {
			
			
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
			JdbcUtil.rollback(JdbcObject.getConnection());
		} finally {
			JdbcUtil.close(JdbcObject.getResultSet());
			JdbcUtil.close(JdbcObject.getPreparedStatement());
			JdbcUtil.close(JdbcObject.getConnection());
		}
		return check;
	}*/
}
