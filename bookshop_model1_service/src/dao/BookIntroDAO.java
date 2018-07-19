package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import dto.BookintroDTO;
import jdbcObject.JdbcObject;
import java.util.ArrayList;

public class BookIntroDAO {

	// 책 인트로 추가 메서드
	public int insertBookIntro(BookintroDTO bookintroDTO) {
		// 리턴값 0으로 초기화 , 리턴값을 담을 변수
		int check = 0;
		
		try {
			Connection connection = JdbcObject.getConnetionInfo();
			JdbcObject.setConnection(connection);
			// 쿼리 실행 문장
			String sql = "INSERT INTO bookintro(book_no, bookintro_content, bookintro_writer) VALUES(?, ?, ?)";
			
			PreparedStatement preparedStatement = JdbcObject.getConnection().prepareStatement(sql);
			
			JdbcObject.setPreparedStatement(preparedStatement);
			
			JdbcObject.getPreparedStatement().setInt(1, bookintroDTO.getBookNo()); // book_no
			JdbcObject.getPreparedStatement().setString(2, bookintroDTO.getBookintroContent()); // bookintro_content
			JdbcObject.getPreparedStatement().setString(3, bookintroDTO.getBookintroWriter());	// bookintro_writer
			
			// 쿼리 처리 성공 1 , 실패 0
			check = JdbcObject.getPreparedStatement().executeUpdate();
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(check +"<-- insertBookIntro 리턴값");
		return check;
	}
	
	// 책 인트로 삭제 메서드
	public int deleteBookIntro(int bookintroNo) {
		// 리턴값 0으로 초기화 , 리턴값을 담을 변수
		int check = 0;
		// 쿼리 실행 문장
		try {
			Connection connection = JdbcObject.getConnetionInfo();
			JdbcObject.setConnection(connection);
			
			String sql = "DELETE FROM bookintro WHERE bookintro_no=?";
			
			PreparedStatement preparedStatement = JdbcObject.getConnection().prepareStatement(sql);
			
			JdbcObject.setPreparedStatement(preparedStatement);
			
			JdbcObject.getPreparedStatement().setInt(1, bookintroNo); // bookintro_no
		
			JdbcObject.getPreparedStatement().executeUpdate();
			// 쿼리 처리 성공 1 , 실패 0
			if(JdbcObject.getResultSet().next()) {
				check = 1;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println(check +"<-- deleteBookIntro 리턴값");
		return check;
		
	}
	
	// 책 인트로 리스트 메서드
	public ArrayList<BookintroDTO> selectBookIntroList() {
		ArrayList<BookintroDTO> bookIntroList = new ArrayList<BookintroDTO>();
		
		try {
			Connection connection = JdbcObject.getConnetionInfo();
			JdbcObject.setConnection(connection);
			// 쿼리 실행 문장
			String sql = "SELECT bookintro_no, book_no, bookintro_content, bookintro_writer FROM bookintro ORDER BY bookintro_no DESC ";
			
			PreparedStatement preparedStatement = JdbcObject.getConnection().prepareStatement(sql);
			
			JdbcObject.setPreparedStatement(preparedStatement);
			
			JdbcObject.getPreparedStatement().executeQuery();
			
			while(JdbcObject.getResultSet().next()) {
				BookintroDTO bookintroDTO = new BookintroDTO();
				bookintroDTO.setBookintroNo(JdbcObject.getResultSet().getInt("bookintro_no"));
				bookintroDTO.setBookNo(JdbcObject.getResultSet().getInt("book_no"));
				bookintroDTO.setBookintroContent(JdbcObject.getResultSet().getString("bookintro_content"));
				bookintroDTO.setBookintroWriter(JdbcObject.getResultSet().getString("bookintro_writer"));
				bookIntroList.add(bookintroDTO);
			}
						
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println(bookIntroList +"<-- selectBookIntroList 리턴값");
		return bookIntroList;
	}
	
	// 책 인트로 업데이트 메서드
	public int updateBookIntro(BookintroDTO bookintroDTO) {
		// 리턴값 0으로 초기화 , 리턴값을 담을 변수
		int check = 0;
		
		try {
			Connection connection = JdbcObject.getConnetionInfo();
			JdbcObject.setConnection(connection);
			// 쿼리 실행 문장
			String sql = "UPDATE bookintro SET bookintro_content=? bookintro_writer=? WHERE bookintro_no=?";
			
			PreparedStatement preparedStatement = JdbcObject.getConnection().prepareStatement(sql);
			
			JdbcObject.setPreparedStatement(preparedStatement);
			
			JdbcObject.getPreparedStatement().setString(1, bookintroDTO.getBookintroContent());
			JdbcObject.getPreparedStatement().setString(2, bookintroDTO.getBookintroWriter());
			
			// 쿼리 처리 성공 1 , 실패 0
			if(JdbcObject.getResultSet().next()) {
				check = 1;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println(check +"<-- updateBookIntro 리턴값");
		return check;
	}
	
	// 책 인트로 하나의 정보를 불러오기 위한 메서드
	public BookintroDTO selectBookIntro(int bookintroNo) {
		// 리턴값 0으로 초기화 , 리턴값을 담을 변수
		BookintroDTO bookintroDTO = new BookintroDTO();
		
		try {
			Connection connection = JdbcObject.getConnetionInfo();
			JdbcObject.setConnection(connection);
			// 쿼리 실행 문장
			String sql = "SELECT bookintro_no, book_no, bookintro_content, bookintro_writer FROM book WHERE bookintro_no=?";
			
			PreparedStatement preparedStatement = JdbcObject.getConnection().prepareStatement(sql);
			
			JdbcObject.setPreparedStatement(preparedStatement);
			
			JdbcObject.getPreparedStatement().setInt(1, bookintroNo);
			
			JdbcObject.getPreparedStatement().executeQuery();
			
			if(JdbcObject.getResultSet().next()) {
				
				bookintroDTO.setBookintroNo(JdbcObject.getResultSet().getInt("bookintro_no"));
				bookintroDTO.setBookNo(JdbcObject.getResultSet().getInt("book_no"));
				bookintroDTO.setBookintroContent(JdbcObject.getResultSet().getString("bookintro_content"));
				bookintroDTO.setBookintroWriter(JdbcObject.getResultSet().getString("bookintro_writer"));
			}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		System.out.println(bookintroDTO +"<-- selectBookIntro 리턴값");
		return bookintroDTO;
	}
}
