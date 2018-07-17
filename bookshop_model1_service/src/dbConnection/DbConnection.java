package dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	private	static String className="com.mysql.jdbc.Driver";
	private static String url="jdbc:mysql://localhost:3306/mysqlcrud_2?useUnicode=true&characterEncoding=euckr";
	private static String user = "root";
	private static String password = "java0000";		
	
	public static Connection getConnetion() throws ClassNotFoundException, SQLException {
		
		Class.forName(className);
		Connection connection = DriverManager.getConnection(url, user, password);
		
		
		return connection;
	}
}
