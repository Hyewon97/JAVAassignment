package accessDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Test002
{
	// 싱글톤 사용 에제..
	public static void main(String[] args)
	{
	
		try {			
			Connection conn = DBConn.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from EmsUsers");
			
			while (rs.next()) {
				String str1 = rs.getString("name");
				String str2 = rs.getString("email");
				System.out.println(str1 + " " + str2);
			}
			
			
		}catch (Exception e) {
			System.out.println(e.toString());
		}
		
	}
}