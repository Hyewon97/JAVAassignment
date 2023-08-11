package test;

import java.math.BigInteger;

public class DataType {

	public static void main(String[] args) {
		
		BigInteger result = new BigInteger("1"); // 1로 초기화

		// 1부터 100까지 곱한 값을 BigInteger 타입 변수 result에 저장
		for (int i = 1; i <= 100; i++)
			result = result.multiply(BigInteger.valueOf(i));

		// 결과 출력
		System.out.println(result); 
		
		// '1' + 2 코드
		System.out.println();
		
		// 10!의 연산결과과 저장되어 있는 BigInteger 타입 변수 result에 BigInteger 변수 2를 더함 
		result = result.add(BigInteger.valueOf(2));
		
		// 결과 출력
		System.out.println(result);
		

	}

}
