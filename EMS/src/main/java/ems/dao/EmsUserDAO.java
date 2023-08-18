package ems.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ems.model.EmsUser;

public class EmsUserDAO {

	// mssql 드라이버 선언
	private String url = "jdbc:sqlserver://192.168.50.107:1433;databaseName=hw_db;encrypt=true;trustServerCertificate=true;";

	// 데이터 insert문
	//                                                                       이름, 이메일, 부서에 데이터를 입력 받아서 저장
	private static final String INSERT_USERS_SQL = "insert into EmsUsers " + "(name, email, department) values"
			+ "(?, ?, ?)";
	
	// empNum로 정보 조회
	private static final String SELECT_USER_BY_empNum = "select empNun, name, email, department from EmsUsers where empNum=?";

	// 모든 사원 조회
	private static final String SELECT_All_USERS = "select * from EmsUsers";

	// empNum에 해당하는 정보 삭제
	private static final String DELETE_USER_BY_empNum = "delete from EmsUsers where empNum=?";

	// empNum에 해당하는 정보 수정
	private static final String UPDATE_USERS_BY_empNum = "update EmsUsers set name = ?,email= ?, department =? where empNum = ?;";

	
	public EmsUserDAO() {
		
	}
	
	// DB 연결
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection = DriverManager.getConnection(url, "phw", "1111");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	// insert 문
	public void insertUser(EmsUser user) throws SQLException {
		System.out.println(INSERT_USERS_SQL); // sql 정상적으로 실행되는지 확인
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getDepartment());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 사원 조회
	public EmsUser selectUser(int empNum) {
		EmsUser empUser = null;
		
		// DB 연결
		try (Connection connection = getConnection();
				
				// sql문 실행
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_empNum);) {
			preparedStatement.setInt(1, empNum);
			System.out.println(preparedStatement);
			
			// 쿼리문을 실행하거나 업데이트
			ResultSet rs = preparedStatement.executeQuery();

			// ResultSet object를 프로세스
			while (rs.next()) {
				String name = rs.getString("name");
				String email = rs.getString("email");
				String department = rs.getString("department");
				empUser = new EmsUser(empNum, name, email, department);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return empUser;
	}
	
	// 모든 사원 조회
	public List<EmsUser> selectAllUsers() {

		List<EmsUser> empUser = new ArrayList<>();
		
		// DB 연결
		try (Connection connection = getConnection();

				// sql문 실행
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_All_USERS);) {
			System.out.println(preparedStatement);
			
			// 쿼리문을 실행하거나 업데이트
			ResultSet rs = preparedStatement.executeQuery();

			// ResultSet object를 프로세스
			while (rs.next()) {
				int empNum = rs.getInt("empNum");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String department = rs.getString("department");
				empUser.add(new EmsUser(empNum, name, email, department));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return empUser;
	}
	
	
	// 사원 정보 삭제
	public boolean deleteUser(int empNum) throws SQLException {
		boolean rowDeleted;
		
		// DB 연동
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_USER_BY_empNum);) {
			statement.setInt(1, empNum);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
	// 사원 정보 수정
	public boolean updateUser(EmsUser empUser) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_BY_empNum);) {
			statement.setString(1, empUser.getName());
			statement.setString(2, empUser.getEmail());
			statement.setString(3, empUser.getDepartment());
			statement.setInt(4, empUser.getEmpNum());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	

}





















