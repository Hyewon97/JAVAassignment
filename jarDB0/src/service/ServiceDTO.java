package service;

import java.io.IOException;

public class ServiceDTO {
	
	public static String SELECT_ALL_USERS; // 전체 데이터 조회
	public static String SELECT_USER_BY_empNum; // 특정 데이터 조회
	public static String INSERT_USERS_SQL; // 정보 삽입
	public static String DELETE_USERS_SQL; // 정보 삭제
	public static String UPDATE_USERS_SQL; // 정보 수정
	
	// 생성자 선언
	public ServiceDTO() {
		
	}

	//게터 세터 선언
	public String getSELECT_ALL_USERS() {
		return SELECT_ALL_USERS;
	}

	public void setSELECT_ALL_USERS(String sELECT_ALL_USERS) {
		SELECT_ALL_USERS = sELECT_ALL_USERS;
	}

	public String getSELECT_USER_BY_empNum() {
		return SELECT_USER_BY_empNum;
	}

	public void setSELECT_USER_BY_empNum(String sELECT_USER_BY_empNum) {
		SELECT_USER_BY_empNum = sELECT_USER_BY_empNum;
	}

	public String getINSERT_USERS_SQL() {
		return INSERT_USERS_SQL;
	}

	public void setINSERT_USERS_SQL(String iNSERT_USERS_SQL) {
		INSERT_USERS_SQL = iNSERT_USERS_SQL;
	}

	public String getDELETE_USERS_SQL() {
		return DELETE_USERS_SQL;
	}

	public void setDELETE_USERS_SQL(String dELETE_USERS_SQL) {
		DELETE_USERS_SQL = dELETE_USERS_SQL;
	}

	public String getUPDATE_USERS_SQL() {
		return UPDATE_USERS_SQL;
	}

	public void setUPDATE_USERS_SQL(String uPDATE_USERS_SQL) {
		UPDATE_USERS_SQL = uPDATE_USERS_SQL;
	}

	
	
}
