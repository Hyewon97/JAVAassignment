package accessDB;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

// DB 연결, 싱글톤 패턴
public class DBConn {

	private static Connection dbConn;

	public static Connection getConnection() throws ClassNotFoundException, SQLException, IOException {

		// properties 값 가져오기
		String propFile = "config/config.properties"; // config 파일 위치 알려줌

		Properties prop = new Properties();

		// 프로퍼티 파일 스트림에 담기
		FileInputStream fis = new FileInputStream(propFile);

		// 프로퍼티 파일 로딩
		prop.load(new java.io.BufferedInputStream(fis));

		// DB 연동
		String DB_url = prop.getProperty("db_url"); // DB url 
		String DB_user = prop.getProperty("db_user"); // DB 사용자
		String DB_password = prop.getProperty("db_password"); // DB 비번

		// DB 연결하기
		if (dbConn == null) {// dbConn이 null이면 DB를 연결한다
			// System.out.println("url : " + DB_url); // url 조합 확인
			
			// SQL Server JDBC 드라이버
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
			// DB 연결
			dbConn = DriverManager.getConnection(DB_url, DB_user, DB_password);
		}

		return dbConn;
	}

	// DB와 연결 끊기
	public static void close() throws SQLException{
		if(dbConn != null) { // dbConn이 null이 아니면
			if(!dbConn.isClosed()) {
				dbConn.close();
			}
		}

	}

}
