package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import dto.BookJoinListDTO;
import dto.BookReviewDTO;
import dto.BookDTO;
import dto.MemberDTO;
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
	public int deleteBookReview(BookReviewDTO bookReviewDTO) {
		// 리턴값 0으로 초기화 , 리턴값을 담을 변수
		int check = 0;
		// 쿼리 실행 문장
		try {
			Connection connection = JdbcObject.getConnetionInfo();
			JdbcObject.setConnection(connection);
			
			String sql = "DELETE FROM bookreview WHERE bookreview_no=? AND member_no=?";
			
			PreparedStatement preparedStatement = JdbcObject.getConnection().prepareStatement(sql);
			
			JdbcObject.setPreparedStatement(preparedStatement);
			
			JdbcObject.getPreparedStatement().setInt(1, bookReviewDTO.getBookReviewNo()); // bookintro_no
			JdbcObject.getPreparedStatement().setInt(2, bookReviewDTO.getMemberNo());
		
			JdbcObject.getPreparedStatement().executeUpdate();
			
			//모든 처리가 완료되면 check값을 1로 변경
			check = 1;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println(check+"<-- deleteBookReview 리턴값");
		//리턴값이 0=실패, 1=성공
		return check;
		
	}
	
	// 책 리뷰 리스트 메서드
	public ArrayList<BookJoinListDTO> selectBookReviewList(int bookNo) {
		ArrayList<BookJoinListDTO> bookReviewList = new ArrayList<BookJoinListDTO>();
		
		try {
			Connection connection = JdbcObject.getConnetionInfo();
			JdbcObject.setConnection(connection);
			// 쿼리 실행 문장
			String sql = "SELECT a.bookreview_no, a.book_no, a.member_no, b.book_name, c.member_name, a.bookreview_content FROM bookreview a INNER JOIN book b ON a.book_no = b.book_no INNER JOIN member c ON a.member_no = c.member_no where a.book_no=? ORDER BY a.bookreview_no ASC";
			
			PreparedStatement preparedStatement = JdbcObject.getConnection().prepareStatement(sql);
			
			JdbcObject.setPreparedStatement(preparedStatement);
			
			JdbcObject.getPreparedStatement().setInt(1, bookNo);
			
			JdbcObject.setResultSet(JdbcObject.getPreparedStatement().executeQuery());
			
			while(JdbcObject.getResultSet().next()) {
				BookReviewDTO bookReviewDTO = new BookReviewDTO(); // BookReviewDTO
				bookReviewDTO.setBookReviewNo(JdbcObject.getResultSet().getInt("bookreview_no"));
				bookReviewDTO.setBookNo(JdbcObject.getResultSet().getInt("book_no"));
				bookReviewDTO.setMemberNo(JdbcObject.getResultSet().getInt("member_no"));
				bookReviewDTO.setBookReviewContent(JdbcObject.getResultSet().getString("bookreview_content"));
				
				BookDTO bookDTO = new BookDTO(); // BookDTO
				bookDTO.setBookName(JdbcObject.getResultSet().getString("book_name"));
				
				MemberDTO memberDTO = new MemberDTO(); // MemberDTO
				memberDTO.setMemberName(JdbcObject.getResultSet().getString("member_name"));
				
				BookJoinListDTO bookJoinDTO = new BookJoinListDTO(); //BookJoinListDTO
				bookJoinDTO.setBookReviewDTO(bookReviewDTO);
				bookJoinDTO.setBookDTO(bookDTO);
				bookJoinDTO.setMemberDTO(memberDTO);
				
				bookReviewList.add(bookJoinDTO);
			}
						
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println(bookReviewList +"<-- selectBookReviewList 리턴값");
		return bookReviewList;
	}
	
	// 책 리뷰 업데이트 메서드
	public int updateBookReview(BookReviewDTO bookReviewDTO) {
		// 리턴값 0으로 초기화 , 리턴값을 담을 변수
		int check = 0;
		
		try {
			Connection connection = JdbcObject.getConnetionInfo();
			JdbcObject.setConnection(connection);
			// 쿼리 실행 문장
			String sql = "UPDATE bookreview SET book_no=?, bookreview_content=? WHERE bookreview_no=? AND member_no=?";
			
			PreparedStatement preparedStatement = JdbcObject.getConnection().prepareStatement(sql);
			
			JdbcObject.setPreparedStatement(preparedStatement);
			
			JdbcObject.getPreparedStatement().setInt(1, bookReviewDTO.getBookNo());
			JdbcObject.getPreparedStatement().setString(2, bookReviewDTO.getBookReviewContent());
			JdbcObject.getPreparedStatement().setInt(3, bookReviewDTO.getBookReviewNo());
			JdbcObject.getPreparedStatement().setInt(4, bookReviewDTO.getMemberNo());
			
			check = JdbcObject.getPreparedStatement().executeUpdate();
			
			//모든 처리가 완료되면 check값을 1로 변경
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println(check+"<-- updateReviewBook 리턴값");
		//리턴값이 0=실패, 1=성공
		return check;
	}
	
	// 책 리뷰 하나의 정보를 불러오기 위한 메서드
	public BookReviewDTO selectBookReview(int bookReviewNo) {
		// 리턴값 0으로 초기화 , 리턴값을 담을 변수
		BookReviewDTO bookReviewDTO = new BookReviewDTO();
		
		try {
			Connection connection = JdbcObject.getConnetionInfo();
			JdbcObject.setConnection(connection);
			// 쿼리 실행 문장
			String sql = "SELECT bookreview_no, book_no, member_no, bookreview_content FROM bookreview WHERE bookreview_no=?";
			
			PreparedStatement preparedStatement = JdbcObject.getConnection().prepareStatement(sql);
			
			JdbcObject.setPreparedStatement(preparedStatement);
			
			JdbcObject.getPreparedStatement().setInt(1, bookReviewNo);
			
			JdbcObject.setResultSet(JdbcObject.getPreparedStatement().executeQuery());
			
			if(JdbcObject.getResultSet().next()) {

				bookReviewDTO.setBookReviewNo(JdbcObject.getResultSet().getInt("bookreview_no"));
				bookReviewDTO.setBookNo(JdbcObject.getResultSet().getInt("book_no"));
				bookReviewDTO.setMemberNo(JdbcObject.getResultSet().getInt("member_no"));
				bookReviewDTO.setBookReviewContent(JdbcObject.getResultSet().getString("bookreview_content"));
			}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		System.out.println(bookReviewDTO +"<-- selectBookReview 리턴값");
		return bookReviewDTO;
	}
}