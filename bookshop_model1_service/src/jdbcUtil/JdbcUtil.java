package jdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// close와 rollback을 편하게 하기위해 보조클래스로서 새로운 클래스를 만듬
public class JdbcUtil {
	
	// 오버로딩으로 close()메서드 구현
	// static을 사용하여 service클래스에서 쉽게 불러와서 사용할 수 있도록 함
	public static void close(Connection connection) {
		if(null != connection) {
			try {
				connection.close();
			}catch (SQLException ex) {
				// TODO: handle exception
				ex.printStackTrace();
			}
		}
	}
	
	public static void close(PreparedStatement preparedStatement) {
		if(null != preparedStatement) {
			try {
				preparedStatement.close();
			}catch (SQLException ex) {
				// TODO: handle exception
				ex.printStackTrace();
			}
		}
	}
	
	public static void close(ResultSet resultSet) {
		if(null != resultSet) {
			try {
				resultSet.close();
			}catch (SQLException ex) {
				// TODO: handle exception
				ex.printStackTrace();
			}
		}
	}
	
	//트랜잭션 처리에서 rollback을 메서드로 작성
	public static void rollback(Connection connection) {
		if(null != connection) {
			try {
				connection.rollback();
			}catch (SQLException ex) {
				// TODO: handle exception
				ex.printStackTrace();
			}
		}
	}
}
