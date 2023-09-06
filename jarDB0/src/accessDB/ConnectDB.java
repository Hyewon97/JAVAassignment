package accessDB;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ConnectDB {

	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		String propFile = "config/config.properties"; // config 파일 위치 알려줌

		Properties prop = new Properties();

		// 프로퍼티 파일 스트림에 담기
		FileInputStream fis = new FileInputStream(propFile);

		// 프로퍼티 파일 로딩
		prop.load(new java.io.BufferedInputStream(fis));

		// DB 연동
		String DB_ip = prop.getProperty("db_ip"); // DB ip
		String DB_port = prop.getProperty("db_port"); // DB port
		String DB_name = prop.getProperty("db_name"); // DB 이름
		String DB_user = prop.getProperty("db_user"); // DB 사용자
		String DB_password = prop.getProperty("db_password"); // DB 비번

		// 접속하려는 DB 정보 문자열로 다시 구성하기
		String connectionUrl = "jdbc:sqlserver://" + DB_ip + ":" + DB_port + ";" + "databaseName=" + DB_name
				+ ";encrypt=true;trustServerCertificate=true;characterEncoding=UTF-8;serverTimezone=UTC";

		System.out.println(connectionUrl); // 데이터 확인용

		// JDBC 오브젝트 선언
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		conn = DriverManager.getConnection(connectionUrl, DB_user, DB_password);
		stmt = conn.createStatement();
		rs = stmt.executeQuery("select * from EmsUsers");

		while (rs.next()) {
			String str1 = rs.getString("name");
			String str2 = rs.getString("email");
			System.out.println(str1 + " " + str2);
		}

		// DB 닫아줌
		rs.close();
		stmt.close();
		conn.close();

	}

}
