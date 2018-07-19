package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import dto.BookDTO;
import dto.BookCodePublisherJoinDTO;
import dto.BookCodeDTO;
import dto.BookPublisherDTO;
import jdbcObject.JdbcObject;
import java.util.ArrayList;

public class BookDAO {
	
	// 책 추가 메서드
	public int insertBook(BookDTO bookDTO) {
		// 리턴값 0으로 초기화 , 리턴값을 담을 변수
		int check = 0;
		
		try {
			Connection connection = JdbcObject.getConnetionInfo();
			JdbcObject.setConnection(connection);
			// 쿼리 실행 문장
			String sql = "INSERT INTO book(bookcode_no, publisher_no, book_name, book_author, book_price, book_point, book_amount, book_out, book_date) VALUES(?, ?, ?, ?, ?, ?, ?, ?, now())";
			
			PreparedStatement preparedStatement = JdbcObject.getConnection().prepareStatement(sql);
			
			JdbcObject.setPreparedStatement(preparedStatement);
			
			JdbcObject.getPreparedStatement().setInt(1, bookDTO.getBookcodeNo()); // bookcode_no
			JdbcObject.getPreparedStatement().setInt(2, bookDTO.getPublisherNo()); // publisher_no
			JdbcObject.getPreparedStatement().setString(3, bookDTO.getBookName()); // book_name
			JdbcObject.getPreparedStatement().setString(4, bookDTO.getBookAuthor()); // book_author
			JdbcObject.getPreparedStatement().setInt(5, bookDTO.getBookPrice()); // book_price
			JdbcObject.getPreparedStatement().setInt(6, bookDTO.getBookPoint()); // book_point
			JdbcObject.getPreparedStatement().setInt(7, bookDTO.getBookAmount()); // book_amount
			JdbcObject.getPreparedStatement().setString(8, bookDTO.getBookOut()); // book_out
			
			// 쿼리 처리 성공 1 , 실패 0
			check = JdbcObject.getPreparedStatement().executeUpdate();
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(check +"<-- insertBook 리턴값");
		return check;
	}
	
	// 책 삭제 메서드
	public int deleteBook(int bookNo) {	
		// 리턴값 0으로 초기화 , 리턴값을 담을 변수
		int check = 0;
		
		try {
			Connection connection = JdbcObject.getConnetionInfo();
			JdbcObject.setConnection(connection);
			// 쿼리 실행 문장
			String sql = "DELETE FROM book WHERE book_no=?";
			
			PreparedStatement preparedStatement = JdbcObject.getConnection().prepareStatement(sql);
			
			JdbcObject.setPreparedStatement(preparedStatement);
			
			JdbcObject.getPreparedStatement().setInt(1, bookNo); // book_no
		
			JdbcObject.getPreparedStatement().executeUpdate();
			// 쿼리 처리 성공 1 , 실패 0
			if(JdbcObject.getResultSet().next()) {
				check = 1;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println(check +"<-- deleteBook 리턴값");
		return check;
	}
	
	// 책 리스트 + 검색 메서드
	public ArrayList<BookCodePublisherJoinDTO> selectBookList(int currentPage, int pagePerRow, String searchKey, String searchValue ) { 
																								// 검색키 , 검색값
		ArrayList<BookCodePublisherJoinDTO> bookList = new ArrayList<BookCodePublisherJoinDTO>();
		
		int firstPage = (currentPage-1)*pagePerRow;
		
		try {
			Connection connection = JdbcObject.getConnetionInfo();
			JdbcObject.setConnection(connection);
			// 쿼리 실행 문장
			if(searchKey.equals("") && searchValue.equals("")) { // 검색키 없고 검색값 없을 때 리스트 출력
				String sql1 = "SELECT a.book_no, a.bookcode_no, a.publisher_no, b.bookcode_name, c.publisher_name, a.book_name, a.book_author, a.book_price, a.book_point, a.book_amount, a.book_out, a.book_date FROM book a INNER JOIN bookcode b ON a.bookcode_no = b.bookcode_no INNER JOIN publisher c ON a.publisher_no = c.publisher_no ORDER BY a.book_no DESC LIMIT ?, ?";
				PreparedStatement preparedStatement = JdbcObject.getConnection().prepareStatement(sql1);
				JdbcObject.setPreparedStatement(preparedStatement);
				
				JdbcObject.getPreparedStatement().setInt(1, firstPage);
				JdbcObject.getPreparedStatement().setInt(2, pagePerRow);
				
			} else if(searchKey.equals("") && !searchValue.equals("")) { // 검색키 없고 검색값 있을 때 책 이름으로 검색 후 리스트 출력
				String sql2 = "SELECT a.book_no, a.bookcode_no, a.publisher_no, b.bookcode_name, c.publisher_name, a.book_name, a.book_author, a.book_price, a.book_point, a.book_amount, a.book_out, a.book_date FROM book a INNER JOIN bookcode b ON a.bookcode_no = b.bookcode_no INNER JOIN publisher c ON a.publisher_no = c.publisher_no WHERE a.book_name LIKE ? ORDER BY a.book_no DESC LIMIT ?, ?";
				PreparedStatement preparedStatement = JdbcObject.getConnection().prepareStatement(sql2);
				JdbcObject.setPreparedStatement(preparedStatement);
				
				JdbcObject.getPreparedStatement().setString(1, "%"+searchValue+"%");
				JdbcObject.getPreparedStatement().setInt(2, firstPage);
				JdbcObject.getPreparedStatement().setInt(3, pagePerRow);
				
			} else if(!searchKey.equals("") && !searchValue.equals("")) { // 검색키 있고 검색값 있을 때 그 값으로 리스트 출력
				String sql3 = "SELECT a.book_no, a.bookcode_no, a.publisher_no, b.bookcode_name, c.publisher_name, a.book_name, a.book_author, a.book_price, a.book_point, a.book_amount, a.book_out, a.book_date FROM book a INNER JOIN bookcode b ON a.bookcode_no = b.bookcode_no INNER JOIN publisher c ON a.publisher_no = c.publisher_no WHERE " + searchKey + " LIKE ? ORDER BY a.book_no DESC LIMIT ?, ?";
				
				PreparedStatement preparedStatement = JdbcObject.getConnection().prepareStatement(sql3);
				JdbcObject.setPreparedStatement(preparedStatement);
				
				JdbcObject.getPreparedStatement().setString(1, "%"+searchValue+"%");
				JdbcObject.getPreparedStatement().setInt(2, firstPage);
				JdbcObject.getPreparedStatement().setInt(3, pagePerRow);
				
			} else { // 그 외 상황 발생 시 리스트 출력
				String sql1 = "SELECT a.book_no, a.bookcode_no, a.publisher_no, b.bookcode_name, c.publisher_name, a.book_name, a.book_author, a.book_price, a.book_point, a.book_amount, a.book_out, a.book_date FROM book a INNER JOIN bookcode b ON a.bookcode_no = b.bookcode_no INNER JOIN publisher c ON a.publisher_no = c.publisher_no ORDER BY a.book_no DESC LIMIT ?, ?";
				PreparedStatement preparedStatement = JdbcObject.getConnection().prepareStatement(sql1);
				JdbcObject.setPreparedStatement(preparedStatement);
				
				JdbcObject.getPreparedStatement().setInt(1, firstPage);
				JdbcObject.getPreparedStatement().setInt(2, pagePerRow);
			}
			
			JdbcObject.getPreparedStatement().executeQuery();
			while(JdbcObject.getResultSet().next()) {
				BookDTO bookDTO = new BookDTO();
				bookDTO.setBookNo(JdbcObject.getResultSet().getInt("book_no"));
				bookDTO.setBookcodeNo(JdbcObject.getResultSet().getInt("bookcode_no"));
				bookDTO.setPublisherNo(JdbcObject.getResultSet().getInt("publisher_no"));
				bookDTO.setBookName(JdbcObject.getResultSet().getString("book_name"));
				bookDTO.setBookAuthor(JdbcObject.getResultSet().getString("book_author"));
				bookDTO.setBookPrice(JdbcObject.getResultSet().getInt("book_price"));
				bookDTO.setBookPoint(JdbcObject.getResultSet().getInt("book_point"));
				bookDTO.setBookAmount(JdbcObject.getResultSet().getInt("book_amount"));
				bookDTO.setBookOut(JdbcObject.getResultSet().getString("book_out"));
				bookDTO.setBookDate(JdbcObject.getResultSet().getString("book_date"));
				
				BookCodeDTO bookCodeDTO = new BookCodeDTO();
				bookCodeDTO.setBookCodeName(JdbcObject.getResultSet().getString("bookcode_name"));
				
				BookPublisherDTO bookPublisherDTO = new BookPublisherDTO();
				bookPublisherDTO.setPubliserName(JdbcObject.getResultSet().getString("publisher_name"));
				
				BookCodePublisherJoinDTO bookCodePublisherJoinDTO = new BookCodePublisherJoinDTO();
				bookCodePublisherJoinDTO.setBookDTO(bookDTO);
				bookCodePublisherJoinDTO.setBookCodeDTO(bookCodeDTO);
				bookCodePublisherJoinDTO.setBookPublisherDTO(bookPublisherDTO);
				bookList.add(bookCodePublisherJoinDTO);
			}
						
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println(bookList +"<-- selectBookList 리턴값");
		return bookList;
	}
	
	// 책 업데이트 메서드
	public int updateBook(BookDTO bookDTO) {
		// 리턴값 0으로 초기화 , 리턴값을 담을 변수
		int check = 0;
		
		try {
			Connection connection = JdbcObject.getConnetionInfo();
			JdbcObject.setConnection(connection);
			// 쿼리 실행 문장
			String sql = "UPDATE book SET book_name=?, book_author=?, book_price=?, book_point=?, book_amount=?, book_out=? WHERE book_no=?";
			
			PreparedStatement preparedStatement = JdbcObject.getConnection().prepareStatement(sql);
			
			JdbcObject.setPreparedStatement(preparedStatement);
			
			JdbcObject.getPreparedStatement().setString(1, bookDTO.getBookName()); // book_name
			JdbcObject.getPreparedStatement().setString(2, bookDTO.getBookAuthor()); // book_author
			JdbcObject.getPreparedStatement().setInt(3, bookDTO.getBookPrice()); // book_price
			JdbcObject.getPreparedStatement().setInt(4, bookDTO.getBookPoint()); // book_point
			JdbcObject.getPreparedStatement().setInt(5, bookDTO.getBookAmount()); // book_amount
			JdbcObject.getPreparedStatement().setString(6, bookDTO.getBookOut()); // book_out
			JdbcObject.getPreparedStatement().setInt(7, bookDTO.getBookNo()); // book_no
			// 쿼리 처리 성공 1 , 실패 0
			if(JdbcObject.getResultSet().next()) {
				check = 1;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println(check +"<-- updateBook 리턴값");
		return check;
	}
	
	// 책 하나의 정보를 불러오기 위한 메서드
	public BookDTO selectBook(int bookNo) {
		// 리턴값 0으로 초기화 , 리턴값을 담을 변수
		BookDTO bookDTO = new BookDTO();
		
		try {
			Connection connection = JdbcObject.getConnetionInfo();
			JdbcObject.setConnection(connection);
			// 쿼리 실행 문장
			String sql = "SELECT book_no, bookcode_no, publisher_no, book_name, book_author, book_price, book_point, book_amount, book_out, book_date FROM book WHERE book_no=?";
			
			PreparedStatement preparedStatement = JdbcObject.getConnection().prepareStatement(sql);
			
			JdbcObject.setPreparedStatement(preparedStatement);
			
			JdbcObject.getPreparedStatement().setInt(1, bookNo);
			
			JdbcObject.getPreparedStatement().executeQuery();
			
			if(JdbcObject.getResultSet().next()) {
				
				bookDTO.setBookNo(JdbcObject.getResultSet().getInt("book_no"));
				bookDTO.setBookcodeNo(JdbcObject.getResultSet().getInt("bookcode_no"));
				bookDTO.setPublisherNo(JdbcObject.getResultSet().getInt("publisher_no"));
				bookDTO.setBookName(JdbcObject.getResultSet().getString("book_name"));
				bookDTO.setBookAuthor(JdbcObject.getResultSet().getString("book_author"));
				bookDTO.setBookPrice(JdbcObject.getResultSet().getInt("book_price"));
				bookDTO.setBookPoint(JdbcObject.getResultSet().getInt("book_point"));
				bookDTO.setBookAmount(JdbcObject.getResultSet().getInt("book_amount"));
				bookDTO.setBookOut(JdbcObject.getResultSet().getString("book_out"));
				bookDTO.setBookDate(JdbcObject.getResultSet().getString("book_date"));			
			}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		System.out.println(bookDTO +"<-- selectBook 리턴값");
		return bookDTO;		
	}
}