package service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ServiceDTO2 {

	public static void main(String[] args) {
		String SELECT_ALL_USERS; // 전체 데이터 조회
		String SELECT_USER_BY_empNum; // 특정 데이터 조회
		String INSERT_USERS_SQL; // 정보 삽입
		String DELETE_USERS_SQL; // 정보 삭제
		String UPDATE_USERS_SQL; // 정보 수정
		
		// config 파일에 있는 sql 문 가지고 오기
		// properties 값 가져오기
		String propFile = "config/config.properties"; // config 파일 위치 알려줌
		Properties prop = new Properties();

		try {
			// 파일 입력 스트림을 이용하여 프로퍼티 파일 읽기
			FileInputStream fis = new FileInputStream(propFile);
			prop.load(new java.io.BufferedInputStream(fis));
			fis.close(); // 파일 닫음

			SELECT_ALL_USERS = prop.getProperty("select_all_users");
			
			System.out.println(SELECT_ALL_USERS);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
