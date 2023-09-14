package sqlConnectTEst;

import java.io.IOException;
import java.sql.SQLException;

import importTest.ImportTest;
import sql.SQLDTO;
import users.UsersService;

public class Main2 {

	public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException {

		ImportTest test = new ImportTest();
		test.importTest();

		//SQLConfig.main(args);

		SQLDTO dto = new SQLDTO();
		UsersService users = new UsersService();

		// 전체 사용자 조회
		users.selectAllUsers(dto.SELECT_ALL_USERS);

		/*
		 * // 특정 사용자 조회 users.selectUser(dto.SELECT_USER_BY_empNum);
		 * 
		 * // 데이터 삽입 users.insertUser(dto.INSERT_USERS_SQL);
		 * 
		 * // 데이터 삭제 users.deleteUser(dto.DELETE_USERS_SQL);
		 * 
		 * // 정보 수정 users.updateUser(dto.UPDATE_USERS_SQL);
		 */
	}

}
