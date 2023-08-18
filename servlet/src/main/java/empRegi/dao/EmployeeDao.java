package empRegi.dao;

import java.sql.*;

import empRegi.model.Employee;

public class EmployeeDao {

	public int registerEmployee(Employee employee) throws ClassNotFoundException {

		//                                               사원번호, 이름, 이메일, 비밀번호, 입사일
		String INSERT_USERS_SQL = "insert into empTest2 " + "values(?, ?, ?, ?, ?)";

		int result = 0;

		// mssql 드라이버 선언
		String url = "jdbc:sqlserver://192.168.50.107:1433;databaseName=hw_db;encrypt=true;trustServerCertificate=true;";

		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		try (Connection connection = DriverManager.getConnection(url, "phw", "1111");

				// connection object를 사용해서 구문 만들기
				PreparedStatement PreStat = connection.prepareStatement(INSERT_USERS_SQL)) {
			PreStat.setInt(1, employee.getEmpNum()); // 사원번호
			PreStat.setString(2, employee.getName()); // 이름
			PreStat.setString(3, employee.getEmail()); // 이메일
			PreStat.setString(4, employee.getPassword()); // 비밀번호
			PreStat.setString(5, employee.getHireDate()); // 입사일
			
		System.out.println(PreStat);
		
		// 쿼리 업데이트 실행하기
		result = PreStat.executeUpdate();		
		
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

}
