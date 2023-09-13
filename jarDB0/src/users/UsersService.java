package users;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import accessDB.DBConn;

public class UsersService {

	// 생성자 선언
	public UsersService() {

	}

	/*
	 * 외부 파일에서 적은 쿼리문을 문자열로 받고, 메소드 실행시 문자열을 입력받아 수행
	 */
	// 모든 유저 선택
	public static List<UsersDTO> selectAllUsers(String sql) throws ClassNotFoundException, IOException {
		List<UsersDTO> users = new ArrayList<>(); // 사용자의 목록을 저장하기 위해 리스트 선언

		try {
			// DB 연동
			Connection conn = DBConn.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql); // sql문 받아서 처리

			// 데이터 확인.. 성공시
			System.out.println("모든 사용자 정보 조회 성공!");
			
			while (rs.next()) { // 반복문을 돌면서 데이터가 있는게 참일때만 동작. 리스트에 데이터 저장
				int empNum = rs.getInt("empNum");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String department = rs.getString("department");

				users.add(new UsersDTO(empNum, name, email, department)); // 리스트 만들어서

				
				System.out.println(empNum + " " + name + " " + email + " " + department);
			}
		} catch (SQLException e) {

			// 실패시
			System.out.println("모든 사용자 정보 조회 실패!");
			System.out.println(e.toString());
		}
		return users; // 리턴
	}

	// 특정 유저 선택
	public static UsersDTO selectUser(String sql) throws ClassNotFoundException, IOException {
		UsersDTO user = null;

		try {
			// DB 연동
			Connection conn = DBConn.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql); // sql문 받아서 처리

			// 데이터 확인..성공
			System.out.println("특정 사용자 정보 조회 성공!");
			while (rs.next()) {
				// 해당하는 정보를 가져오기
				int empNum = rs.getInt("empNum");

				String name = rs.getString("name");
				String email = rs.getString("email");
				String department = rs.getString("department");

				user = new UsersDTO(empNum, name, email, department); // 특정 유저 정보 담아서 보내기

				System.out.println(empNum + " " + name + " " + email + " " + department);
			}
		} catch (SQLException e) {

			// 실패시
			System.out.println("특정 사용자 정보 조회 실패!");
			System.out.println(e.toString());
		}
		return user;
	}
	
	

	// 새로 등록
	public static void insertUser(String sql) throws SQLException, ClassNotFoundException, IOException {
		try {
			Connection conn = DBConn.getConnection();
			Statement stmt = conn.createStatement();
			int rowsAffected = stmt.executeUpdate(sql);

			if (rowsAffected > 0) {
				// 성공
				System.out.println("데이터 삽입 성공!");
			}
		} catch (SQLException e) {
			// 실패
			System.out.println("데이터 삽입 실패!");
			System.out.println(e.toString());
		}
	}



	// 정보 삭제
	public static void deleteUser(String sql) throws SQLException, ClassNotFoundException, IOException {
		try {
			Connection conn = DBConn.getConnection();
			Statement stmt = conn.createStatement();
			int rowsAffected = stmt.executeUpdate(sql);

			if (rowsAffected > 0) {
				// 성공
				System.out.println("데이터 삭제 성공");
			} else {
				// 삭제할 데이터가 DB에 없는 경우
				System.out.println("삭제할 행이 없습니다.");
			}
		} catch (SQLException e) {
			System.out.println("데이터 삭제 실패!");
			System.out.println(e.toString());
		}
	}

	// 데이터 수정
	public static void updateUser(String sql) throws SQLException, ClassNotFoundException, IOException {
		try {
			Connection conn = DBConn.getConnection();
			Statement stmt = conn.createStatement();
			int rowsAffected = stmt.executeUpdate(sql);

			if (rowsAffected > 0) {
				System.out.println("데이터 수정 성공!");
			} else {
				System.out.println("수정할 행이 없습니다.");
			}
		} catch (SQLException e) {
			// 실패
			System.out.println("데이터 수정 실패!");
			System.out.println(e.toString());
		}
		
	}	
}

















