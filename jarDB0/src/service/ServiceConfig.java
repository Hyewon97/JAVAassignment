package service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ServiceConfig {

	public static void main(String[] args){

		// config 파일에 있는 sql 문 가지고 오기
		// properties 값 가져오기
		String propFile = "config/config.properties"; // config 파일 위치 알려줌
		Properties prop = new Properties();
		
		// DTO 호출
		ServiceDTO serviceDto = new ServiceDTO();
		
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
			
			
			
			  //System.out.println("select 값 확인하기 "+serviceDto.SELECT_ALL_USERS);
			 /* System.out.println("select by num 값 확인하기 "+serviceDto.SELECT_USER_BY_empNum);
			 * System.out.println("insert 값 확인하기 "+serviceDto.INSERT_USERS_SQL);
			 * System.out.println("del 값 확인하기 "+serviceDto.DELETE_USERS_SQL);
			 * System.out.println("update 값 확인하기 "+serviceDto.UPDATE_USERS_SQL);
			 */
			
			
		}catch (IOException e) {
            e.printStackTrace();
        }
		
		
		
		
		
		
		

		
		

	}
}
