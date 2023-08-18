package sqlTest;

/*회사 내부 db 접속 확인 코드
정상 동작 확인함*/

import java.sql.*;
import java.io.*;

public class SQLServer {
	public static void main(String[] args) throws Exception {
		// db 주소
		String url = "jdbc:sqlserver://192.168.50.107:1433;databaseName=hw_db;encrypt=true;trustServerCertificate=true;";
		
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		conn = DriverManager.getConnection(url, "phw", "1111");
		stmt = conn.createStatement();
		rs = stmt.executeQuery("SELECT * FROM empTable");
		while (rs.next()) {
			String field1 = rs.getString("name");
			String field2 = rs.getString("email");
			System.out.println(field1 + " " + field2);

		}
		rs.close();
		stmt.close();
		conn.close();
	}
}