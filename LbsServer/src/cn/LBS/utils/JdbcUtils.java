package cn.LBS.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtils {

	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/parktable";
	private static String username = "root";
	private static String password = "root";

	static {
		try {
			/*
			 * InputStream in = JdbcUtils.class.getClassLoader()
			 * .getResourceAsStream("db.properties"); Properties prop = new
			 * Properties(); prop.load(in);
			 */

			/*
			 * driver = prop.getProperty("driver"); url =
			 * prop.getProperty("url"); username = prop.getProperty("username");
			 * password = prop.getProperty("password");
			 */

			Class.forName(driver);

		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	public static Connection getConnection() throws SQLException {

		return DriverManager.getConnection(url, username, password);
	}

	public static void release(Connection conn, Statement st, ResultSet rs) {

		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			rs = null;

		}
		if (st != null) {
			try {
				st.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}
}
