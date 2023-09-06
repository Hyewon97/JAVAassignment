package users;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import accessDB2.DBConn;

public class UsersService2 {

	// 생성자 선언, config 파일에 있는 값 가져오기
	public UsersService2() throws IOException {
		
		String propFile = "config/config.properties";
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(propFile);
		prop.load(new java.io.BufferedInputStream(fis));
	}
	
	

	// 모든 유저 선택
	public static List<UsersDTO> selectAllUsers(String sql) throws ClassNotFoundException, IOException {
		List<UsersDTO> users = new ArrayList<>();

		try {
			Connection conn = DBConn.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql); // sql문 받아서 처리

			while (rs.next()) {
				int empNum = rs.getInt("empNum");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String department = rs.getString("department");

				users.add(new UsersDTO(empNum, name, email, department)); // 리스트 만들어서

				// 데이터 확인
				System.out.println(empNum + " " + name + " " + email + " " + department);
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return users; // 리턴
	}

	// 특정 유저 선택
	public static UsersDTO selectUser(String sql) throws ClassNotFoundException, IOException {
		UsersDTO user = null;

		try {
			Connection conn = DBConn.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql); // sql문 받아서 처리

			while (rs.next()) {
				int empNum = rs.getInt("empNum");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String department = rs.getString("department");

				user = new UsersDTO(empNum, name, email, department); // 특정 유저 정보 담아서 보내기
				System.out.println(empNum + " " + name + " " + email + " " + department);
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		return user;
	}

	// 새로 등록
	public static void insertUser(String sql) throws SQLException, ClassNotFoundException, IOException {

		try {
			Connection conn = DBConn.getConnection();
			Statement stmt = conn.createStatement();
			stmt.executeQuery(sql); // sql문 받아서 삽입

		} catch (SQLException e) {
			System.out.println(e.toString());
		}

	}

	// 정보 삭제
	public static void deleteUser(String sql) throws SQLException, ClassNotFoundException, IOException {

		try {
			Connection conn = DBConn.getConnection();
			Statement stmt = conn.createStatement();
			stmt.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println(e.toString());
		}

	}

	// 정보 수정
	public static void updateUser(String sql) throws SQLException {

		try {
			Connection conn = DBConn.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql); // sql문 받아서 삽입

			((PreparedStatement) rs).setString(1, ((UsersDTO) rs).getName());
			((PreparedStatement) rs).setString(2, ((UsersDTO) rs).getEmail());
			((PreparedStatement) rs).setString(3, ((UsersDTO) rs).getDepartment());
			((PreparedStatement) rs).setInt(4, ((UsersDTO) rs).getEmpNum());

		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	////////////// main문.. 테스트용
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {

		// config 파일에 있는 sql 문 가지고 오기
		// properties 값 가져오기
		String propFile = "config/config.properties"; // config 파일 위치 알려줌
		Properties prop = new Properties();
		// 프로퍼티 파일 스트림에 담기
		FileInputStream fis = new FileInputStream(propFile);
		// 프로퍼티 파일 로딩
		prop.load(new java.io.BufferedInputStream(fis));

		String SELECT_ALL_USERS = prop.getProperty("select_all_users"); // 전체 정보 조회
		String SELECT_USER_BY_empNum = prop.getProperty("select_user_by_empNum"); // 특정 정보 조회
		String INSERT_USERS_SQL = prop.getProperty("insert_users_sql"); // 정보 삽입
		String DELETE_USERS_SQL = prop.getProperty("delete_users_sql"); // 정보 삭제
		String UPDATE_USERS_SQL = prop.getProperty("update_users_sql"); // 정보 삭제

		/*
		 * System.out.println("SELECT_ALL_USERS 값 확인 : " + SELECT_ALL_USERS);
		 * System.out.println("SELECT_USER_BY_empNum 값 확인 : " + SELECT_USER_BY_empNum);
		 * System.out.println("INSERT_USERS_SQL 값 확인 : " + INSERT_USERS_SQL);
		 * System.out.println("DELETE_USERS_SQL 값 확인 : " + DELETE_USERS_SQL);
		 * System.out.println("UPDATE_USERS_SQL 값 확인 : " + UPDATE_USERS_SQL);
		 */

		// selectAllUsers(SELECT_ALL_USERS); // 값 테스트
		// selectUser(SELECT_USER_BY_empNum);
		deleteUser(DELETE_USERS_SQL);
//		insertUser(INSERT_USERS_SQL);
		

	}

}
