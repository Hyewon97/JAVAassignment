package accessDB2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class UserService {
	
	public  static void  main(String[] args) throws IOException  {
		
		// SQl 문 받아오기
		// config 파일 접근
		String propFile = "config/config.properties"; // config 파일 위치 알려줌
		Properties prop = new Properties();
		// 프로퍼티 파일 스트림에 담기
		FileInputStream fis = new FileInputStream(propFile);
		// 프로퍼티 파일 로딩
		prop.load(new java.io.BufferedInputStream(fis));
		
		String SELECT_ALL_USERS = prop.getProperty("select_all_users");  // 전체 정보 조
		
		/////////////////////////////
	}
	
	

}
