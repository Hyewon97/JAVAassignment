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

public class UsersService {

	// 생성자 선언
	public UsersService() {

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
			
			// System.out.println("sql: "+sql);

			while (rs.next()) {
				int empNum = rs.getInt("empNum");
				
				//System.out.println("empNum"+empNum);
				
				String name = rs.getString("name");
				String email = rs.getString("email");
				String department = rs.getString("department");

				user = new UsersDTO(empNum, name, email, department); // 특정 유저 정보 담아서 보내기
				System.out.println( empNum + " " + name + " " + email + " " + department);
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
			System.out.println("오류");
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
	
	/*
	 * public static void main(String[] args) throws IOException,
	 * ClassNotFoundException { // selectAllUsers("select * from EmsUsers");
	 * //selectUser("select * from EmsUsers where empNum = 48");
	 * 
	 * String SELECT_USER_BY_empNum = ("select * from EmsUsers where empNum = 74");
	 * // 특정 정보 조회 selectUser(SELECT_USER_BY_empNum);
	 * 
	 * 
	 * 
	 * }
	 */


}
