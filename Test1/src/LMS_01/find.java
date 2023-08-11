package LMS_01;

public class find{

	public static void findStudent(int nextInt, String[] name, int[] id, String[] dept, String[] phN) {
		for(int i =0; i< id.length;i++) {
			if(id[i] == nextInt) {
				System.out.println("학번 : " + id[i]);
				System.out.println("이름 : " + name[i]);
				System.out.println("학과 : " + dept[i]);
				System.out.println("전화번호 : " + phN[i]);
				System.out.println("--------------------------");
				break;
			}else if(i == (id.length)-1) {
				System.out.println("일치하는 학번이 없습니다.");
			}
		}
		
	}

}
