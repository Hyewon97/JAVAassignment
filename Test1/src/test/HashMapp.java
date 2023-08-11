package test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class HashMapp {

	public static void main(String[] args) {
		HashMap<String,Integer> hash1 = new HashMap<>();
		hash1.put("가나다",3);
		hash1.put("가다나",1);
		hash1.put("가나",2);
		
		// 모든 데이터 출력
		System.out.println(hash1);
		
		// sortMap을 상속받는 TreeMap 클래스 sort선언
		Map<String, Integer> sort = new TreeMap<>(hash1);

		System.out.println(sort);
	}
	
	

}
