package LMS_01;

public class print{

	public static void printStudent(String[] name, int[] id, String[] dept, String[] phN) {
		for(int i=0; i<id.length; i++) {
			System.out.println("학  번:" + id[i]);
			System.out.println("이  름:" + name[i]);
			System.out.println("학  과:" + dept[i]);
			System.out.println("연락처: " + phN[i]);
			System.out.println("----------------------");
		}
		
	}

}
