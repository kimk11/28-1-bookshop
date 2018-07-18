package dao;

import dto.BookPublisherDTO;
import jdbcObject.JdbcObject;

public class BookPublisherDAO {
	
	// 매개변수 int publisherName :: 출판사 이름
	// 리턴값 0:실패(DB에 정보 없음), !0:성공 
	public int selectPublisherNo(String publisherName) {
		int publisherNo=0;
		
		String sql = "select publisher_no from publisher where publisher_name = ?";
		
		try {
			JdbcObject.setConnection(JdbcObject.getConnetionInfo());
			JdbcObject.setPreparedStatement(JdbcObject.getConnection().prepareStatement(sql));
			JdbcObject.getPreparedStatement().setString(1, publisherName); // publisher_name 출판사 이름
		
			JdbcObject.getPreparedStatement().executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return publisherNo;
	}
	
	// 매개변수 int publisherNo :: 출판사 no
	//리턴 0:실패, 1:성공
	public int deleteBookPublisher(int publisherNo) {
		
		// 매개변수 publisherNo 받아와서 한 레코드 삭제 즉, 한 개의 출판사가 삭제된다.
		String sql = "DELETE FROM publisher WHERE publisher_no = ?";
		
		// 리턴값 0으로 초기화 , 리턴값을 담을 변수
		int check = 0;
		
		try {
			JdbcObject.setConnection(JdbcObject.getConnetionInfo());
			JdbcObject.setPreparedStatement(JdbcObject.getConnection().prepareStatement(sql));
			JdbcObject.getPreparedStatement().setInt(1, publisherNo); // publisher_name 출판사 이름
		
			JdbcObject.getPreparedStatement().executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return check;
	}

	
	
	// 한 출판사 조회 메서드
	// 매개변수 int publisherName :: 출판사 이름 
	// 리턴값 0 : db값 없음 , 1 : db에 중복값 있음
	public int selectOneBookPublisher(String publisherName) {

		// 출판사 이름 검색 시 한 개의 출판사가 조회된다.
		String sql = "SELECT publisher_name,publisher_website FROM publisher WHERE publisher_name =?";
		
		// 리턴값 0으로 초기화 , 리턴값을 담을 변수
		int check = 0;
		
		try {
			JdbcObject.setConnection(JdbcObject.getConnetionInfo());

			JdbcObject.setPreparedStatement(JdbcObject.getConnection().prepareStatement(sql));
			JdbcObject.getPreparedStatement().setString(1, publisherName);		 // publisher_name 출판사 이름

			JdbcObject.setResultSet(JdbcObject.getPreparedStatement().executeQuery());
			if(JdbcObject.getResultSet().next()) { 		//   쿼리의 결과를 resultSet으로 리턴
				check =1;								//	 resultSet에 결과값이 있다면 다음 레코드로 
														//   다음 결과 값이 있다면 check = 1로 리턴값을 넘겨준다.
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return check;
	}
	

	// 출판사 등록 메서드
	// 리턴 0:실패, 1:성공
	public int insertBookPublisher(BookPublisherDTO bookPublisherDTO) {

		// 리턴값 0으로 초기화 , 리턴값을 담을 변수
		int check = 0;

		// 출판사 이름과 사이트를 등록한다.
		String sql = "INSERT INTO publisher(publisher_name,publisher_website) VALUES(?, ?)";

		try {
			JdbcObject.setConnection(JdbcObject.getConnetionInfo());

			JdbcObject.setPreparedStatement(JdbcObject.getConnection().prepareStatement(sql));
			JdbcObject.getPreparedStatement().setString(1, bookPublisherDTO.getPubliserName()); 			// publisher_name 출판사 이름
			JdbcObject.getPreparedStatement().setString(2, bookPublisherDTO.getPublisherWebsite()); 		// publisher_website 출판사 웹사이트

			check = JdbcObject.getPreparedStatement().executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return check;
	}
}
