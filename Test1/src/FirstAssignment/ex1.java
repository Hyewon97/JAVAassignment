package FirstAssignment;

import java.math.BigInteger;

/*
 * 1부터 100까지 곱하는 코드 작성
 */
public class ex1 {
	public static void main(String[] args) {
		BigInteger bg = new BigInteger("1");
		bg = bg.multiply(new BigInteger(fact(100)+""));
		System.out.println("100! = " + bg);
		System.out.println('1'+ 2);//'1'의 아스키코드 값이 49이므로 2를 더하면 51이 출력된다.
		
	}
	private static BigInteger fact(int n) {
		if(n==0 || n==1) return BigInteger.ONE;
		else return fact(n-1).multiply(new BigInteger(n+""));
	}

	
}

