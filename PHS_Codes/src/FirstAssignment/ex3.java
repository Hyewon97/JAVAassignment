package FirstAssignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ex3 {

	public static void main(String[] args) {
		HashMap hs = new HashMap();
		hs.put("가나다", 3);
		hs.put("가다나", 2);
		hs.put("가나", 1);
		
		Set<String> keySet = hs.keySet();
		for(String key : keySet) {
			System.out.print(hs.get(key) + " ");
		}
		System.out.println();
		List<String> keyList = new ArrayList<>(keySet);
		Collections.sort(keyList);
		for(String key : keyList) {
			System.out.print(hs.get(key) + " ");
		}
		

	}

}
