package accessDB2;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class Test002 {
	// 싱글톤 사용 에제..
	public static void main(String[] args) throws IOException {
		
		// config 파일에 있는 sql 문 가지고 오기
		// properties 값 가져오기
		String propFile = "config/config.properties"; // config 파일 위치 알려줌
		Properties prop = new Properties();
		// 프로퍼티 파일 스트림에 담기
		FileInputStream fis = new FileInputStream(propFile);
		// 프로퍼티 파일 로딩
		prop.load(new java.io.BufferedInputStream(fis));
		
		String SQL_SELECT = prop.getProperty("select_user_by_empNum");

		///////////

		try {
			Connection conn = DBConn.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL_SELECT);

			while (rs.next()) {
				String str1 = rs.getString("name");
				String str2 = rs.getString("email");
				System.out.println(str1 + " " + str2);
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}
}