package empRegi.dao;

<<<<<<< HEAD
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
=======
/*import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
*/
import empRegi.model.Employee;
>>>>>>> 1e703d91817cd85a0c782278d646be9c515b7385

public class EmployeeDao {

	public int registerEmployee(Employee employee) throws ClassNotFoundException {
		String INSERT_USERS_SQL = "INSERT INTO employee"
				+ "  (id, first_name, last_name, username, password, address, contact) VALUES "
				+ " (?, ?, ?, ?, ?,?,?);";

		int result = 0;

		Class.forName("com.mysql.jdbc.Driver");

		try (Connection connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/mysql_database?useSSL=false", "root", "root");

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			preparedStatement.setInt(1, 1);
			preparedStatement.setString(2, employee.getFirstName());
			preparedStatement.setString(3, employee.getLastName());
			preparedStatement.setString(4, employee.getUsername());
			preparedStatement.setString(5, employee.getPassword());
			preparedStatement.setString(6, employee.getAddress());
			preparedStatement.setString(7, employee.getContact());

			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			result = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// process sql exception
			printSQLException(e);
		}
		return result;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
}