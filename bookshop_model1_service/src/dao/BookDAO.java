package dao;

import java.sql.*;
import dbConnection.DbConnection;;

public class BookDAO {
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	//책 추가 메서드
	//리턴 0:실패, 1:성공
	public int insertBook() {
		
		//리턴값을 담을 변수
		int check=0;
		
		//쿼리 실행 문장
		String sql = "";
		
		try {
			connection = DbConnection.getConnetion();
			preparedStatement = connection.prepareStatement(sql);
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			
		}
		return check;
	}
}
