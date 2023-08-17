package test;

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
		rs = stmt.executeQuery("SELECT * FROM testTable1");
		while (rs.next()) {
			String field1 = rs.getString("name");
			System.out.println(field1);

		}
		rs.close();
		stmt.close();
		conn.close();
	}
}