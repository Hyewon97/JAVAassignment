package test;

import java.sql.*;
import java.io.*;

public class sqlTest {
	public static void main(String[] args) throws Exception {
		String url = "jdbc:sqlserver://192.168.50.107:1433;DatabaseName=hs_db;";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		conn = DriverManager.getConnection(url, "phw", "1111");
		stmt = conn.createStatement();
		rs = stmt.executeQuery("SELECT * FROM testTable1");
		while (rs.next()) {
			String field1 = rs.getString("name");
//			String field2 = rs.getString("department");
			System.out.println(field1);
//			System.out.println(field2);
		}
		rs.close();
		stmt.close();
		conn.close();
	}
}