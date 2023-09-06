package main;

import java.io.IOException;
import java.sql.SQLException;

import service.ServiceConfig;
import service.ServiceDTO;
import users.UsersService;

public class Main {

	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		ServiceConfig.main(args); // ServiceConfig 클래스 실행

		ServiceDTO dto = new ServiceDTO(); // dto의 값이 null로 뜸
		UsersService users = new UsersService();

		// 전체 사용자 조회 
//		users.selectAllUsers(dto.SELECT_ALL_USERS);

		// 특정 사용자 조회
//		users.selectUser(dto.SELECT_USER_BY_empNum);

		// 데이터 삽입
//		 users.insertUser(dto.INSERT_USERS_SQL);
		
		// 데이터 삭제
//		users.deleteUser(dto.DELETE_USERS_SQL);
		
		// 정보 수정
		users.updateUser(dto.UPDATE_USERS_SQL);

	}

}
