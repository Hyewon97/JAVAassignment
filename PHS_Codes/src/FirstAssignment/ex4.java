package FirstAssignment;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ex4 {
	public static void main(String args[]) {
		String str1 = "abc";
		String str2 = new String("def");
		
		String ableStr = "에이블컴";
		System.out.println("에이블컴 글자 수 : " + ableStr.strip().length());
		
		String zeroXStr = "0x0100";
		String nonZeroXStr = "0A1000";
		
		System.out.println(zeroXStr + " : " + toInteger(zeroXStr));
		System.out.println(nonZeroXStr + " : " + toInteger(nonZeroXStr));
		
		
		String toMapStr = "{\"가\":\"1\"},{\"나\":\"2\"},{\"다\":\"3\"}";
		Map map = toMap(toMapStr);
		Set<String> keySet = map.keySet();
		for(String key : keySet) {
			System.out.println(key + " : " + map.get(key));
		}
		
		
	}
	
	
	private static int toInteger(String zeroXStr) {
		if(zeroXStr.startsWith("0x")) {
			return Integer.parseInt(zeroXStr.split("0x")[1], 2);
		}else return -1;
		
	}
	private static Map toMap(String toMapStr) {
		Map<String, String> map = new HashMap<String, String> ();
		for(String elementStr : toMapStr.split(",")) {
			String key = elementStr.split(":")[0].replace("{", "").trim();
			String val = elementStr.split(":")[1].replace("}", "").trim();
			map.put(key, val);
		}
		return map;
	}
}

