package sqlConnectTEst;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) {
		String connectionUrl = "jdbc:sqlserver://192.168.50.107:1433;databaseName=hw_db;encrypt=true;trustServerCertificate=true;characterEncoding=UTF-8;serverTimezone=UTC";// (db서버가 따로 존재한다면 로컬호스트:포트번호
																							// 대신 서버아이피:포트번호 를 입력하면된다.

		// Declare the JDBC objects.

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {

			// Establish the connection.

			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			System.out.println("Driver okay");

			con = DriverManager.getConnection(connectionUrl, "phw", "1111");

			System.out.println("Connection Made");

		}

		// Handle any errors that may have occurred.

		catch (Exception e) {

			e.printStackTrace();

		}

	}

}
