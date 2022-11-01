package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static Connection conn;
	public static Connection getConnection() {
		if(conn == null) {

			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection(url,user,password);
			} catch (ClassNotFoundException e) {
			}catch (SQLException sqle) {
			}
		}
		return conn;
	}
}






