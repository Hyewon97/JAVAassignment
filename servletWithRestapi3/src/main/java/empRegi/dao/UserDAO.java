package empRegi.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import empRegi.model.User;

public class UserDAO {

	// db url
	java.lang.String url = "jdbc:sqlserver://192.168.50.107:1433;databaseName=hw_db;encrypt=true;trustServerCertificate=true;characterEncoding=UTF-8;serverTimezone=UTC";

	// sql문
	java.lang.String INSERT_USERS_SQL = "insert into EmsUsers (name, email, department) " + "values(?, ?, ?)";
	java.lang.String SELECT_ALL_USERS = "select * from EmsUsers order by empNum desc"; // empNum 기준 내림차순 정렬
	java.lang.String DELETE_USERS_SQL = "delete from EmsUsers where empNum = ?";
	java.lang.String SELECT_USER_BY_empNum = "select empNum, name, email, department from EmsUsers where empNum=?";
	java.lang.String UPDATE_USERS_SQL = "update EmsUsers set name = ?,email= ?, department =? where empNum = ?";

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
			e.printStackTrace();
		}

		return connection;

	}

	// 새로 등록
	public void insertUser(User user) throws SQLException {
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			preparedStatement.setString(1, user.getName()); // 이름
			preparedStatement.setString(2, user.getEmail()); // 이메일
			preparedStatement.setString(3, user.getDepartment()); // 부서

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}

	}

	// 유저 선택
	public User selectUser(int empNum) {
		User user = null;

		// DB 연동
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_empNum);) {
			preparedStatement.setInt(1, empNum);

			// 쿼리 실행
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				java.lang.String name = rs.getString("name");
				java.lang.String email = rs.getString("email");
				java.lang.String department = rs.getString("department");
				user = new User(empNum, name, email, department);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return user;
	}

	// 모든 유저 선택
	public List<User> selectAllUsers() {
		List<User> users = new ArrayList<>();

		// db 연동
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
			ResultSet rs = preparedStatement.executeQuery();

			// 값이 있으면 해당되는 정보 출력
			while (rs.next()) { // 사용자의 정보가 있으면 계속 리스트에 저장. 사용자가 없으면 while문을 빠져나온다.
				int empNum = rs.getInt("empNum");
				java.lang.String name = rs.getString("name");
				java.lang.String email = rs.getString("email");
				java.lang.String department = rs.getString("department");
				users.add(new User(empNum, name, email, department));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return users;
	}

	// 정보 삭제
	public boolean deleteUser(int empNum) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
			statement.setInt(1, empNum);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	// 정보 수정
	public boolean updateUser(User user) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
			statement.setString(1, user.getName());
			statement.setString(2, user.getEmail());
			statement.setString(3, user.getDepartment());
			statement.setInt(4, user.getEmpNum());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	// 예외 출력
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
