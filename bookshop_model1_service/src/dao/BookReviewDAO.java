package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dto.BookIntroDTO;
import dto.BookReviewDTO;
import jdbcObject.JdbcObject;
import java.util.ArrayList;

public class BookReviewDAO {

	// 책 리뷰 추가 메서드
	public int insertBookReview(BookReviewDTO bookReviewDTO) {
		// 리턴값 0으로 초기화 , 리턴값을 담을 변수
		int check = 0;
		
		try {
			Connection connection = JdbcObject.getConnetionInfo();
			JdbcObject.setConnection(connection);
			// 쿼리 실행 문장
			String sql = "INSERT INTO bookreview(book_no, member_no, bookreview_content) VALUES(?, ?, ?)";
			
			PreparedStatement preparedStatement = JdbcObject.getConnection().prepareStatement(sql);
			
			JdbcObject.setPreparedStatement(preparedStatement);
			
			JdbcObject.getPreparedStatement().setInt(1, bookReviewDTO.getBookNo()); // book_no
			JdbcObject.getPreparedStatement().setInt(2, bookReviewDTO.getMemberNo()); // member_no
			JdbcObject.getPreparedStatement().setString(3, bookReviewDTO.getBookReviewContent()); // bookreview_content
			
			// 쿼리 처리 성공 1 , 실패 0
			check = JdbcObject.getPreparedStatement().executeUpdate();
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(check +"<-- insertBookReview 리턴값");
		return check;
	}
	
	// 책 리뷰 삭제 메서드
	public int deleteBookReview(int bookReviewNo) {
		// 리턴값 0으로 초기화 , 리턴값을 담을 변수
		int check = 0;
		// 쿼리 실행 문장
		try {
			Connection connection = JdbcObject.getConnetionInfo();
			JdbcObject.setConnection(connection);
			
			String sql = "DELETE FROM bookreview WHERE bookreview_no=?";
			
			PreparedStatement preparedStatement = JdbcObject.getConnection().prepareStatement(sql);
			
			JdbcObject.setPreparedStatement(preparedStatement);
			
			JdbcObject.getPreparedStatement().setInt(1, bookReviewNo); // bookintro_no
		
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
	
	// 책 리뷰 리스트 메서드
	public ArrayList<BookReviewDTO> selectBookReviewList() {
		ArrayList<BookReviewDTO> bookReviewList = new ArrayList<BookReviewDTO>();
		
		try {
			Connection connection = JdbcObject.getConnetionInfo();
			JdbcObject.setConnection(connection);
			// 쿼리 실행 문장
			String sql = "SELECT bookreview_no, book_no, member_no, bookreview_content FROM bookreview ORDER BY bookreview_no DESC";
			
			PreparedStatement preparedStatement = JdbcObject.getConnection().prepareStatement(sql);
			
			JdbcObject.setPreparedStatement(preparedStatement);
			
			JdbcObject.getPreparedStatement().executeQuery();
			
			while(JdbcObject.getResultSet().next()) {
				BookReviewDTO bookReviewDTO = new BookReviewDTO();
				bookReviewDTO.setBookReviewNo(JdbcObject.getResultSet().getInt("bookreview_no"));
				bookReviewDTO.setBookNo(JdbcObject.getResultSet().getInt("book_no"));
				bookReviewDTO.setMemberNo(JdbcObject.getResultSet().getInt("member_no"));
				bookReviewDTO.setBookReviewContent(JdbcObject.getResultSet().getString("bookreview_content"));
				bookReviewList.add(bookReviewDTO);
			}
						
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println(bookReviewList +"<-- selectBookIntroList 리턴값");
		return bookReviewList;
	}
}