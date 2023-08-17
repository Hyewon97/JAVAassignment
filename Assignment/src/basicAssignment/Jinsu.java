package basicAssignment;

public class Jinsu {

	public static void main(String[] args) {
		String deco = "0x0100";
				
		int result =0;
		
		// 문자열의 처음 2글자가 0x로 시작하는지 판별하기
		if(deco.charAt(0)=='0' && deco.charAt(1)=='x') {
			if(deco.charAt(2)=='1') 
				result += 8;
			if(deco.charAt(3)=='1') 
				result += 4;
			if(deco.charAt(4)=='1') 
				result += 2;
			if(deco.charAt(5)=='1') 
				result += 1;			
		} // if문 끝
		
		System.out.println("값 : " + result);
		
		

	}

}
