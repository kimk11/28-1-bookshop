package dao;

import dto.BookDTO;

import jdbcObject.JdbcObject;;

public class BookDAO {
	
	//책 추가 메서드
	//리턴 0:실패, 1:성공
	public int insertBook(BookDTO bookDTO) {
		
		//리턴값을 담을 변수
		int check=0;
		
		//쿼리 실행 문장
		String sql = "insert into book(bookcode_no, publisher_no, book_name, book_author, book_price, book_point, book_amount, book_out, book_date) "
					+ "values(?,?,?,?,?,?,?,?,now())";
		
		try {
			JdbcObject.setConnection(JdbcObject.getConnetionInfo());
			
			JdbcObject.setPreparedStatement(JdbcObject.getConnection().prepareStatement(sql));
			JdbcObject.getPreparedStatement().setInt(1, bookDTO.getBookcodeNo());
			JdbcObject.getPreparedStatement().setInt(2, bookDTO.getPublisherNo());
			JdbcObject.getPreparedStatement().setString(3, bookDTO.getBookName());
			JdbcObject.getPreparedStatement().setString(4, bookDTO.getBookAuthor());
			JdbcObject.getPreparedStatement().setInt(5, bookDTO.getBookPrice());
			JdbcObject.getPreparedStatement().setInt(6, bookDTO.getBookPoint());
			JdbcObject.getPreparedStatement().setInt(7, bookDTO.getBookAmount());
			JdbcObject.getPreparedStatement().setString(8, bookDTO.getBookOut());
			
			JdbcObject.getPreparedStatement().executeUpdate();
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return check;
	}
}
