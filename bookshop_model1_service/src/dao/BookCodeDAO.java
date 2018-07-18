package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.BookCodeDTO;
import jdbcObject.JdbcObject;

public class BookCodeDAO {
	
	//책 카테고리의 중복검사를 할 메서드
	public int selectDuplicateBook(String bookCodeName) {
		//리턴값을 담을 변수
		int check = 0;
		
		try {
			//드라이버 로딩 및 db연결 메서드 호출, Connection객체의 주소값을 리턴받는다.
			Connection connection = JdbcObject.getConnetionInfo();
			//리턴받은 주소값을 setConnection메서드를 호출해 JdbcObject클래스의 멤버변수 connection에 대입한다.
			JdbcObject.setConnection(connection);
			
			//bookcode테이블에서 책 카테고리 이름을 검색하는 쿼리문 준비
			String sql = "select bookcode_name from bookcode where bookcode_name=?";
			
			//getConnection메서드를 호출해서 리턴받은 주소값으로 PreparedStatement객체를 생성하고 준비한 쿼리문을 매개변수로 대입, PreparedStatement의 주소값을 리턴받는다.
			PreparedStatement preparedStatement = JdbcObject.getConnection().prepareStatement(sql);
			//리턴받은 주소값을 setPreparedStatement메서드를 호출해 JdbcObject클래스의 멤버변수 preparedStatement에 대입한다.
			JdbcObject.setPreparedStatement(preparedStatement);
			
			//쿼리문의 첫번째 인덱스에 입력한 책 카테고리값을 대입한다.
			JdbcObject.getPreparedStatement().setString(1, bookCodeName);
			
			//getPreparedStatement메서드를 호출해서 리턴받은 주소값으로 쿼리문을 실행시키는 메서드 호출, ResultSet객체의 주소값을 리턴받는다.
			ResultSet resultSet = JdbcObject.getPreparedStatement().executeQuery();
			//리턴받은 주소값을 setResultSet메서드를 호출해 JdbcObject클래스의 멤버변수 resultSet에 대입한다.
			JdbcObject.setResultSet(resultSet);
			
			//ResultSet객체의 테이블을 검색
			if(JdbcObject.getResultSet().next()) {
				//카테고리의 이름이 중복일경우 check의 값이 0
				check = 0;
			} else {
				//카테고리의 이름이 중복이 아닐경우 check의 값이 1
				check = 1;
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println(check+"<--중복검사 체크");
		//리턴값이 0=중복, 1=중복이 아님
		return check;
	}
	
	//책 카테고리의 이름을 입력하는 메서드
	public int insertBookCode(String bookCodeName) {
		//리턴값을 담을 변수
		int check = 0;

		try {
			//드라이버 로딩 및 db연결 메서드 호출, Connection객체의 주소값을 리턴받는다.
			Connection connection = JdbcObject.getConnetionInfo();
			//리턴받은 주소값을 setConnection메서드를 호출해 JdbcObject클래스의 멤버변수 connection에 대입한다.
			JdbcObject.setConnection(connection);
			
			//책 카테고리를 입력하는 쿼리문 준비
			String sql = "insert into bookcode(bookcode_name) values(?)";
			
			//getConnection메서드를 호출해서 리턴받은 주소값으로 PreparedStatement객체를 생성하고 준비한 쿼리문을 매개변수로 대입, PreparedStatement의 주소값을 리턴받는다.
			PreparedStatement preparedStatement = JdbcObject.getConnection().prepareStatement(sql);
			//리턴받은 주소값을 setPreparedStatement메서드를 호출해 JdbcObject클래스의 멤버변수 preparedStatement에 대입한다.
			JdbcObject.setPreparedStatement(preparedStatement);
			
			//쿼리문의 첫번째 인덱스에 입력한 책 카테고리값을 대입한다.
			JdbcObject.getPreparedStatement().setString(1, bookCodeName);
			
			//getPreparedStatement메서드를 호출해서 리턴받은 주소값으로 쿼리문을 실행시키는 메서드 호출, ResultSet객체의 주소값을 리턴받는다.
			JdbcObject.getPreparedStatement().executeUpdate();
			
			//모든 처리가 완료되면 check값을 1로 변경
			check = 1;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println(check+"<--카테고리 입력 체크");
		//리턴값이 0=실패, 1=성공
		return check;
	}
	
	//책 카테고리를 삭제하는 메서드
	public int deleteBookCode(int bookCodeNo) {
		//리턴값을 담을 변수
		int check = 0;
		
		try {
			//드라이버 로딩 및 db연결 메서드 호출, Connection객체의 주소값을 리턴받는다.
			Connection connection = JdbcObject.getConnetionInfo();
			//리턴받은 주소값을 setConnection메서드를 호출해 JdbcObject클래스의 멤버변수 connection에 대입한다.
			JdbcObject.setConnection(connection);
			
			//책 카테고리를 삭제하는 쿼리문 준비
			String sql = "delete from bookcode where bookcode_no=?";
			
			//getConnection메서드를 호출해서 리턴받은 주소값으로 PreparedStatement객체를 생성하고 준비한 쿼리문을 매개변수로 대입, PreparedStatement의 주소값을 리턴받는다.
			PreparedStatement preparedStatement = JdbcObject.getConnection().prepareStatement(sql);
			//리턴받은 주소값을 setPreparedStatement메서드를 호출해 JdbcObject클래스의 멤버변수 preparedStatement에 대입한다.
			JdbcObject.setPreparedStatement(preparedStatement);
			
			//쿼리문의 첫번째 인덱스에 카테고리의 넘버를 대입한다.
			JdbcObject.getPreparedStatement().setInt(1, bookCodeNo);
			
			//getPreparedStatement메서드를 호출해서 리턴받은 주소값으로 쿼리문을 실행시키는 메서드 호출, ResultSet객체의 주소값을 리턴받는다.
			JdbcObject.getPreparedStatement().executeUpdate();
			
			//모든 처리가 완료되면 check값을 1로 변경
			check = 1;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println(check+"<--카테고리 삭제 체크");
		//리턴값이 0=실패, 1=성공
		return check;
	}
	
	//책 카테고리 전체를 검색할 리스트 메서드
	public ArrayList<BookCodeDTO> selectBookCodeList(){
		//책 카테고리의 정보가 담긴 객체의 주소값을 담을 배열객체 생성
		ArrayList<BookCodeDTO> bookCodeList = new ArrayList<BookCodeDTO>();
		
		try {
			//드라이버 로딩 및 db연결 메서드 호출, Connection객체의 주소값을 리턴받는다.
			Connection connection = JdbcObject.getConnetionInfo();
			//리턴받은 주소값을 setConnection메서드를 호출해 JdbcObject클래스의 멤버변수 connection에 대입한다.
			JdbcObject.setConnection(connection);
			
			//bookcode테이블에서 책 카테고리넘버와 이름을 전체검색
			String sql = "select bookcode_no, bookcode_name from bookcode";
			
			//getConnection메서드를 호출해서 리턴받은 주소값으로 PreparedStatement객체를 생성하고 준비한 쿼리문을 매개변수로 대입, PreparedStatement의 주소값을 리턴받는다.
			PreparedStatement preparedStatement = JdbcObject.getConnection().prepareStatement(sql);
			//리턴받은 주소값을 setPreparedStatement메서드를 호출해 JdbcObject클래스의 멤버변수 preparedStatement에 대입한다.
			JdbcObject.setPreparedStatement(preparedStatement);
			
			//getPreparedStatement메서드를 호출해서 리턴받은 주소값으로 쿼리문을 실행시키는 메서드 호출, ResultSet객체의 주소값을 리턴받는다.
			ResultSet resultSet = JdbcObject.getPreparedStatement().executeQuery();
			//리턴받은 주소값을 setResultSet메서드를 호출해 JdbcObject클래스의 멤버변수 resultSet에 대입한다.
			JdbcObject.setResultSet(resultSet);
			
			//ResultSet객체의 테이블을 검색해서 검색값이 없을때까지 반복
			while(JdbcObject.getResultSet().next()) {
				//카테고리의 정보를 담을 DTO객체 생성
				BookCodeDTO bookCodeDTO = new BookCodeDTO();
				JdbcObject.getResultSet().getInt("bookcode_no");
				JdbcObject.getResultSet().getString("bookcode_name");
				
				//카테고리의 정보를 담은 객체의 주소값을 배열객체에 대입
				bookCodeList.add(bookCodeDTO);
			} 
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println(bookCodeList+"<--주소값이 null인지 체크");
		//리턴값은 배열객체의 주소값
		return bookCodeList;
	}
}
