package sql;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SQLConfig {

	public static void main(String[] args){

		// properties (외부파일 값 읽어오기)
		String propFile = "config/config.properties"; // config 파일 위치 알려줌, 위치는 프로젝트 내의 'config' 폴더 안에 'config.properties' 파일
		Properties prop = new Properties();
		
		// DTO 호출
		SQLDTO serviceDto = new SQLDTO();
		
		try {
			// 파일 입력 스트림을 이용하여 프로퍼티 파일 읽기
			FileInputStream fis = new FileInputStream(propFile);
			prop.load(new java.io.BufferedInputStream(fis));
			fis.close(); // 파일 닫음
						
			// 프로퍼티 파일에서 가져온 값을 가져온 값을 serviceDto 변수에 저장
			serviceDto.setSELECT_ALL_USERS(prop.getProperty("select_all_users")); // 전체 조회
			serviceDto.setSELECT_USER_BY_empNum(prop.getProperty("select_user_by_empNum")); // 특정 정보 조회
			serviceDto.setINSERT_USERS_SQL(prop.getProperty("insert_users_sql")); // 정보 삽입
			serviceDto.setDELETE_USERS_SQL(prop.getProperty("delete_users_sql")); // 정보 삭제
			serviceDto.setUPDATE_USERS_SQL(prop.getProperty("update_users_sql")); // 정보 수정		
		}catch (IOException e) {
            e.printStackTrace();
        }
		
		
		
		
		
		
		

		
		

	}
}
