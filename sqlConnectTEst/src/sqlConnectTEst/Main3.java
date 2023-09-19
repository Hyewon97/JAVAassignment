package src.sqlConnectTEst;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import importTest.ImportTest;
import sql.SQLDTO;
import users.UsersService;

public class Main3 {

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		
		// 임포트 테스트
		ImportTest test = new ImportTest();
		test.importTest();

		
		// properties (외부파일 값 읽어오기)
		String propFile = "config/config.properties";
		Properties prop = new Properties();

		// DTO 호출
		SQLDTO dto = new SQLDTO();
		
		// UserSerivce 클래스 호출
		UsersService users = new UsersService();


		try {
			// 파일 입력 스트림을 이용하여 프로퍼티 파일 읽기
			FileInputStream fis = new FileInputStream(propFile);
			prop.load(new java.io.BufferedInputStream(fis));
			fis.close(); // 파일 닫음

			// 프로퍼티 파일에서 가져온 값을 가져온 값을 serviceDto 변수에 저장
			dto.setSELECT_ALL_USERS(prop.getProperty("select_all_users")); // 전체 조회
			dto.setSELECT_USER_BY_empNum(prop.getProperty("select_user_by_empNum")); // 특정 정보 조회
			dto.setINSERT_USERS_SQL(prop.getProperty("insert_users_sql")); // 정보 삽입
			dto.setDELETE_USERS_SQL(prop.getProperty("delete_users_sql")); // 정보 삭제
			dto.setUPDATE_USERS_SQL(prop.getProperty("update_users_sql")); // 정보 수정
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		users.selectAllUsers(dto.SELECT_ALL_USERS); // 전체 사용자 조회
		users.selectUser(dto.SELECT_USER_BY_empNum); // 특정 사용자 조회
		users.selectUser(dto.INSERT_USERS_SQL); // 데이터 삽입
		users.selectUser(dto.DELETE_USERS_SQL); // 데이터 삭제
		users.selectUser(dto.UPDATE_USERS_SQL); // 데이터 수정

	}

}
