package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
			JdbcObject.getPreparedStatement().setString(3, bookReviewDTO.getBookReviewContent());	// bookreview_content
			
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
}