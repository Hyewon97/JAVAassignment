package basicAssignment;

import java.util.HashMap;
//import java.util.Map;

public class StringToMap {

	public static void main(String[] args) {
		// 문자열 선언
		String str = "{\"가\":\"1\"},{\"나\":\"2\"},{\"다\":\"3\"}";

		// map 선언
		HashMap<String, String> map = new HashMap<String, String>();

		String[] arr = str.split("\\},\\{"); //  '}' ',' '{' 로 문자열 분리하기
		for (String arr2 : arr) {
			arr2 = arr2.replace("{", "").replace("}", ""); // 중괄호 제거
			String[] keyValue = arr2.split(":"); // : 로 문자열 분리
			String key = keyValue[0].replace("\"", ""); // 따옴표 제거
			String value = keyValue[1].replace("\"", ""); // 따옴표 제거
			map.put(key, value);
		}

		// 결과 출력
		System.out.println(map);

	}

}
