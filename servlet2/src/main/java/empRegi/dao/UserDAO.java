package empRegi.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import empRegi.model.Employee;

public class UserDAO {

	// db url
	String url = "jdbc:sqlserver://192.168.50.107:1433;databaseName=hw_db;encrypt=true;trustServerCertificate=true;";

	// sql문
	String INSERT_USERS_SQL = "insert into EmsUsers (name, email, department) " + "values(?, ?, ?)";
	String SELECT_ALL_USERS = "select * from EmsUsers";
	String DELETE_USERS_SQL = "delete from EmsUsers where id = ?";
	String SELECT_USER_BY_empNum = "select empNum, name, email, department from EmsUsers where empNum=?";

	
	// 생성자 선언
	public UserDAO() {

	}

	// DB 연동
	protected Connection getConnection() {
		Connection connection = null;

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection = DriverManager.getConnection(url, "phw", "1111");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return connection;

	}

	// 새로 등록
	public void registerEmployee(Employee employee) throws ClassNotFoundException {

		try (Connection connection = getConnection();
				PreparedStatement PreStat = connection.prepareStatement(INSERT_USERS_SQL)) {
			PreStat.setString(1, employee.getName()); // 이름
			PreStat.setString(2, employee.getEmail()); // 이메일
			PreStat.setString(3, employee.getDepartment()); // 비밀번호

			PreStat.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}

	}
	
	// 모든 유저 출력
	public List<Employee> selectAllUsers() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Employee> employee = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int empNum = rs.getInt("empNum");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String department = rs.getString("department");
				employee.add(new Employee(empNum, name, email, department));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return employee;
	}
	
	

	// 에러 출력
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
