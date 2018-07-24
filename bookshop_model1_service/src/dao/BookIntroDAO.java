package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import dto.BookIntroDTO;
import jdbcObject.JdbcObject;
import java.util.ArrayList;

public class BookIntroDAO {

	// 책 인트로 추가 메서드
	public int insertBookIntro(BookIntroDTO bookIntroDTO) {
		// 리턴값 0으로 초기화 , 리턴값을 담을 변수
		int check = 0;
		
		try {
			Connection connection = JdbcObject.getConnetionInfo();
			JdbcObject.setConnection(connection);
			// 쿼리 실행 문장
			String sql = "INSERT INTO bookintro(book_no, bookintro_content, bookintro_write) VALUES(?, ?, ?)";
			
			PreparedStatement preparedStatement = JdbcObject.getConnection().prepareStatement(sql);
			
			JdbcObject.setPreparedStatement(preparedStatement);
			
			JdbcObject.getPreparedStatement().setInt(1, bookIntroDTO.getBookNo()); // book_no
			JdbcObject.getPreparedStatement().setString(2, bookIntroDTO.getBookIntroContent()); // bookintro_content
			JdbcObject.getPreparedStatement().setString(3, bookIntroDTO.getBookIntroWrite());	// bookintro_write
			
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
	public int deleteBookIntro(int bookIntroNo) {
		// 리턴값 0으로 초기화 , 리턴값을 담을 변수
		int check = 0;
		// 쿼리 실행 문장
		try {
			Connection connection = JdbcObject.getConnetionInfo();
			JdbcObject.setConnection(connection);
			
			String sql = "DELETE FROM bookintro WHERE bookintro_no=?";
			
			PreparedStatement preparedStatement = JdbcObject.getConnection().prepareStatement(sql);
			
			JdbcObject.setPreparedStatement(preparedStatement);
			
			JdbcObject.getPreparedStatement().setInt(1, bookIntroNo); // bookintro_no
		
			check = JdbcObject.getPreparedStatement().executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println(check +"<-- deleteBookIntro 리턴값");
		return check;
		
	}
	
	// 책 인트로 리스트 메서드
	public ArrayList<BookIntroDTO> selectBookIntroList() {
		ArrayList<BookIntroDTO> bookIntroList = new ArrayList<BookIntroDTO>();
		
		try {
			Connection connection = JdbcObject.getConnetionInfo();
			JdbcObject.setConnection(connection);
			// 쿼리 실행 문장
			String sql = "SELECT bookintro_no, book_no, bookintro_content, bookintro_write FROM bookintro ORDER BY bookintro_no DESC ";
			
			PreparedStatement preparedStatement = JdbcObject.getConnection().prepareStatement(sql);
			
			JdbcObject.setPreparedStatement(preparedStatement);
			
			JdbcObject.setResultSet(JdbcObject.getPreparedStatement().executeQuery());
			
			while(JdbcObject.getResultSet().next()) {
				BookIntroDTO bookintroDTO = new BookIntroDTO();
				bookintroDTO.setBookIntroNo(JdbcObject.getResultSet().getInt("bookintro_no"));
				bookintroDTO.setBookNo(JdbcObject.getResultSet().getInt("book_no"));
				bookintroDTO.setBookIntroContent(JdbcObject.getResultSet().getString("bookintro_content"));
				bookintroDTO.setBookIntroWrite(JdbcObject.getResultSet().getString("bookintro_write"));
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
	public int updateBookIntro(BookIntroDTO bookIntroDTO) {
		// 리턴값 0으로 초기화 , 리턴값을 담을 변수
		int check = 0;
		
		try {
			Connection connection = JdbcObject.getConnetionInfo();
			JdbcObject.setConnection(connection);
			// 쿼리 실행 문장
			String sql = "UPDATE bookintro SET bookintro_content=?, bookintro_write=? WHERE bookintro_no=?";
			
			PreparedStatement preparedStatement = JdbcObject.getConnection().prepareStatement(sql);
			
			JdbcObject.setPreparedStatement(preparedStatement);
			
			JdbcObject.getPreparedStatement().setString(1, bookIntroDTO.getBookIntroContent());
			JdbcObject.getPreparedStatement().setString(2, bookIntroDTO.getBookIntroWrite());
			JdbcObject.getPreparedStatement().setInt(3, bookIntroDTO.getBookIntroNo());
			
			JdbcObject.getPreparedStatement().executeUpdate();
			
			//모든 처리가 완료되면 check값을 1로 변경
			check = 1;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println(check+"<-- updateBook 리턴값");
		//리턴값이 0=실패, 1=성공
		return check;
	}
	
	// 책 인트로 하나의 정보를 불러오기 위한 메서드
	public BookIntroDTO selectBookIntro(int bookIntroNo) {
		// 리턴값 0으로 초기화 , 리턴값을 담을 변수
		BookIntroDTO bookIntroDTO = new BookIntroDTO();
		
		try {
			Connection connection = JdbcObject.getConnetionInfo();
			JdbcObject.setConnection(connection);
			// 쿼리 실행 문장
			String sql = "SELECT bookintro_no, book_no, bookintro_content, bookintro_write FROM bookintro WHERE bookintro_no=?";
			
			PreparedStatement preparedStatement = JdbcObject.getConnection().prepareStatement(sql);
			
			JdbcObject.setPreparedStatement(preparedStatement);
			
			JdbcObject.getPreparedStatement().setInt(1, bookIntroNo);
			
			JdbcObject.setResultSet(JdbcObject.getPreparedStatement().executeQuery());
			
			if(JdbcObject.getResultSet().next()) {
				
				bookIntroDTO.setBookIntroNo(JdbcObject.getResultSet().getInt("bookintro_no"));
				bookIntroDTO.setBookNo(JdbcObject.getResultSet().getInt("book_no"));
				bookIntroDTO.setBookIntroContent(JdbcObject.getResultSet().getString("bookintro_content"));
				bookIntroDTO.setBookIntroWrite(JdbcObject.getResultSet().getString("bookintro_write"));
			}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		System.out.println(bookIntroDTO +"<-- selectBookIntro 리턴값");
		return bookIntroDTO;
	}
}
