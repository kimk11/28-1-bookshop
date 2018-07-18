//28기 김진우
//2018/07/18
package jdbcObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcObject {
	private static Connection connection;
	private static PreparedStatement preparedStatement;
	private static ResultSet resultSet;

	public static Connection getConnection() {
		return connection;
	}
	public static void setConnection(Connection connection) {
		JdbcObject.connection = connection;
	}

	public static PreparedStatement getPreparedStatement() {
		return preparedStatement;
	}
	public static void setPreparedStatement(PreparedStatement preparedStatement) {
		JdbcObject.preparedStatement = preparedStatement;
	}

	public static ResultSet getResultSet() {
		return resultSet;
	}
	public static void setResultSet(ResultSet resultSet) {
		JdbcObject.resultSet = resultSet;
	}

	public static Connection getConnetionInfo() throws ClassNotFoundException, SQLException {
		
		String className="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/bookshop?useUnicode=true&characterEncoding=utf8";
		String user = "root";
		String password = "java0000";	
		
		Class.forName(className);
		Connection connection = DriverManager.getConnection(url, user, password);
		connection.setAutoCommit(false);
		
		return connection;
	}
}
