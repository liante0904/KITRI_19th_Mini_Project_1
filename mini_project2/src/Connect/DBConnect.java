package Connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
	
	private static DBConnect db = new DBConnect();
	private Connection conn = null;
	String jdbc_driver = "core.log.jdbc.driver.OracleDriver";
	String jdbc_url = "jdbc:oracle:thin:@192.168.14.160:1521:xe";
	
	private DBConnect() {

	}

	public static DBConnect getInstance() {
		return db;
	}
	public Connection getConnection(){
		System.out.println(jdbc_driver);
		System.out.println("DBConnect...");
		try {
			Class.forName(jdbc_driver);
			conn = DriverManager.getConnection(jdbc_url, "kitri04", "kitri04");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("DBConnect...OK");
		return conn;
	}
	
}
