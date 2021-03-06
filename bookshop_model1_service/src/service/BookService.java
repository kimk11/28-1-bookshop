package service;

import java.util.ArrayList;

import dao.BookCodeDAO;
import dao.BookDAO;
import dao.BookIntroDAO;
import dao.BookPublisherDAO;
import dao.BookReviewDAO;
import dto.BookCodeDTO;
import dto.BookJoinListDTO;
import dto.BookMemberJoinDTO;
import dto.BookDTO;
import dto.BookIntroDTO;
import dto.BookPublisherDTO;
import jdbcObject.JdbcObject;
import jdbcUtil.JdbcUtil;

public class BookService {
	
	//책 정보 입력을 처리하는 Service메서드(책 정보들이 저장되어있는 객체의 참조값을 매개변수로 받음)
	public int insertBookService(BookDTO bookDTO) {
		//리턴결과값을 담을 변수
		int check = 0;
		
		try {
			//책 마일리지 포인트 계산
			//책 가격값
			double bookPrice = (double)bookDTO.getBookPrice();
			System.out.println(bookPrice+"책 가격값");
			
			//마일리지 포인트 변수
			int bookPoint = (int)(bookPrice * ((double)5 / (double)100));
			System.out.println(bookPoint+"마일리지 값");
			bookDTO.setBookPoint(bookPoint);
			
			BookDAO bookDAO = new BookDAO();
			//책 정보가 담겨있는 객체의 참조값을 매개변수로 책 정보를 db에 저장하는 메서드를 호출, 처리에 대한 결과값을 리턴받는다.
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
		
		System.out.println(check+"<--insertBookservice메서드 처리 성공 여부");
		//리턴값이 0=실패, 1=성공
		return check;
	}
	
	//출판사들의 정보와 책 카테고리들의 정보를 검색하는 메서드
	public BookJoinListDTO selectBookCodePublisherListService() {
		BookJoinListDTO bookJoin = new BookJoinListDTO();
		
		try {
			BookCodeDAO bookCodeDAO = new BookCodeDAO();
			//책 카테고리들을 전체검색하는 메서드 호출, 리턴값으로 카테고리들의 정보가 담긴 객체의 참조값을 저장한 배열객체의 참조값을 리턴한다.
			ArrayList<BookCodeDTO> bookCodeList = bookCodeDAO.selectBookCodeList();
			
			//배열객체의 주소값을 매개변수로 참조값을 저장하는 메서드 호출
			bookJoin.setBookCodeListDTO(bookCodeList);
			
			if(null != bookCodeList) {
				BookPublisherDAO bookPublisherDAO = new BookPublisherDAO();
				//출판사들을 전체검색하는 메서드 호출, 리턴값으로 출판사들의 정보가 담긴 객체의 참조값을 저장한 배열객체의 참조값을 리턴한다.
				ArrayList<BookPublisherDTO> bookPublisherList = bookPublisherDAO.selectAllBookPublisher();
				
				//배열객체의 주소값을 매개변수로 참조값을 저장하는 메서드 호출
				bookJoin.setBookPublisherListDTO(bookPublisherList);
				
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
		
		System.out.println(bookJoin+"<--selectBookCodePublisherListService 메서드 처리 성공 여부");
		//리턴값은 배열객체들이 저장된 객체
		return bookJoin;
	}
	
	//책 정보를 리스트을 처리하는 Service메서드(매개변수로 현재페이지값, 리스트의 갯수, 검색 조건값, 검색 단어값)
	public ArrayList<BookJoinListDTO> selectSearchBookListService(int currentPage, int pagePerRow, String searchKey, String searchValue) {
		BookDAO bookDAO = new BookDAO();
		ArrayList<BookJoinListDTO> bookList = null;
		
		try {
			//(현재페이지값, 리스트의 갯수, 검색 조건값, 검색 단어값)들을 매개변수로 책 정보들을 전체검색하는 메서드 호출
			//출판사와 카테고리와 책 정보들의 값이 들어간 객체의 주소값을 저장한 배열객체의 주소값을 리턴값으로 받는다.
			bookList = bookDAO.selectBookList(currentPage, pagePerRow, searchKey, searchValue);
			
			if(null != bookList) {
				//Connection의 요청을 완료하고 특별한 에러가 없다면 검색 결과를 커밋
				JdbcObject.getConnection().commit();
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
		
		System.out.println(bookList+"<--selectSearchBookListService 메서드 처리 성공 여부");
		//리턴값은 배열객체들이 저장된 객체
		return bookList;
	}
	
	//리스트 페이지에서 페이징 작업을 처리하는 Service메서드(매개변수로 리스트의 갯수, 검색 조건값, 검색 단어값)
	public int pagingService(int pagePerRow, String searchKey, String searchValue) {
		//리턴결과값을 받을 마지막 페이지의 변수
		int lastPage = 0;
		
		try {
			BookDAO bookDAO = new BookDAO();
			//(리스트의 갯수, 검색 조건값, 검색 단어값)들을 매개변수로 페이징 작업을 하는 메서드 호출
			//마지막 페이지의 변수값을 리턴값으로 받는다
			lastPage = bookDAO.paging(pagePerRow, searchKey, searchValue);
			
			//변수에 값이 있을경우 실행
			if(0 != lastPage) {
				//Connection의 요청을 완료하고 특별한 에러가 없다면 검색 결과를 커밋
				JdbcObject.getConnection().commit();
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
		
		System.out.println(lastPage+"<--pagingService 메서드 처리 성공 여부");
		//리턴값은 마지막 페이지의 변수
		return lastPage;
	}
	
	//책 상세정보 페이지에서 상세정보 검색과 도서 소개 및 댓글처리 메서드(책 테이블의 기본키가되는 bookNo를 매개변수로 받음)
	public BookJoinListDTO selectDetailBookService(int bookNo, String bookIntroNo) {
		//리턴값을 담을 객체참조변수
		BookJoinListDTO bookJoinListDTO = new BookJoinListDTO();
		
		try {
			BookDAO bookDAO = new BookDAO();
			//책을 식별하기위해 bookNo를 매개변수로 책 정보를 검색하는 메서드 호출, 리턴값으로 책의 정보가 저장된 객체의 참조값을 리턴받는다.
			bookJoinListDTO = bookDAO.selectBook(bookNo);
			
			BookIntroDAO bookIntroDAO = new BookIntroDAO();
			//책 소개정보를 리스트 처리하는 메서드 호출, 책 소개정보가 담겨있는 객체의 참조값을 가진 배열객체의 참조값을 리턴받는다.
			ArrayList<BookIntroDTO> bookIntroList = bookIntroDAO.selectBookIntroList(bookNo);
			bookJoinListDTO.setBookIntroListDTO(bookIntroList);
			
			BookReviewDAO bookReviewDAO = new BookReviewDAO();
			//댓글정보를 리스트 처리하는 메서드 호출, 댓글정보가 담겨있는 객체의 참조값을 가진 배열객체의 참조값을 리턴받는다.
			ArrayList<BookMemberJoinDTO> bookReviewList = bookReviewDAO.selectBookReviewList(bookNo);
			bookJoinListDTO.setBookMemberJoinDTO(bookReviewList);
			
			if(bookIntroNo.equals("")) {
				
			} else {
				//jsp페이지에서 타입변환을 하면 예외가 일어나기때문에 메서드안에서 실행
				int bookIntroNoInt = Integer.parseInt(bookIntroNo);
				//책을 식별하기위해 bookIntroNo를 매개변수로 책 소개 정보를 검색하는 메서드를 호출, 책 소개 정보가 저장된 객체의 참조값을 리턴한다
				BookIntroDTO bookIntroDTO = bookIntroDAO.selectBookIntro(bookIntroNoInt);
				bookJoinListDTO.setBookIntroDTO(bookIntroDTO);
			}
			
			//책의 정보가 저장된 객체의 참조값과 책 소개정보가 저장된 객체와 댓글정보가 저장된 객체의 참조값이 null이 아니면 커밋
			if(null != bookJoinListDTO && null != bookIntroList && null != bookReviewList) {
				//Connection의 요청을 완료하고 특별한 에러가 없다면 검색 결과를 커밋
				JdbcObject.getConnection().commit();
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
		
		System.out.println(bookJoinListDTO+"<--selectDetailBookService 메서드 처리 성공 여부");
		//리턴값은 책 정보와 책 소개정보가 저장된 객체의 주소값
		return bookJoinListDTO;
	}
	
	//책 정보 수정을 처리하는 Service메서드(책 정보들이 저장되어있는 객체의 참조값을 매개변수로 받음) 
	public int updateBookService(BookDTO bookDTO) {
		//리턴결과값을 담을 변수
		int check = 0;
		
		try {
			//책 마일리지 포인트 계산
			//책 가격값
			double bookPrice = (double)bookDTO.getBookPrice();
			System.out.println(bookPrice+"책 가격값");
			
			//마일리지 포인트 변수
			int bookPoint = (int)(bookPrice * ((double)5 / (double)100));
			System.out.println(bookPoint+"마일리지 값");
			bookDTO.setBookPoint(bookPoint);
			
			BookDAO bookDAO = new BookDAO();
			//책 정보들이 저장되어있는 객체의 참조값을 매개변수로 책 정보를 수정하는 메서드를 호출, 처리에 대한 결과값을 리턴받는다.
			check = bookDAO.updateBook(bookDTO);
			
			if(1 == check) {
				//Connection의 요청을 완료하고 특별한 에러가 없다면 검색 결과를 커밋
				JdbcObject.getConnection().commit();
				check=1;
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
		
		System.out.println(check+"<--updateBookService 메서드 처리 성공 여부");
		//리턴값이 0=실패, 1=성공
		return check;
	}
	
	//책 정보 삭제를 처리하는 Service메서드(책 테이블의 기본키가되는 bookNo를 매개변수로 받음)
	public int deleteBookService(int bookNo) {
		//리턴결과값을 담을 변수
		int check = 0;
		
		try {
			BookDAO bookDAO = new BookDAO();
			//책을 식별하기위해 bookNo를 매개변수로 책 정보를 삭제하는 메서드 호출, 처리에 대한 결과값을 리턴받는다.
			check = bookDAO.deleteBook(bookNo);
			
			if(1 == check) {
				//Connection의 요청을 완료하고 특별한 에러가 없다면 검색 결과를 커밋
				JdbcObject.getConnection().commit();
				check=1;
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
		
		System.out.println(check+"<--deleteBookService 메서드 처리 성공 여부");
		//리턴값이 0=실패, 1=성공
		return check;
	}
}
