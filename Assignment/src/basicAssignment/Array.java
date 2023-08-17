package basicAssignment;

public class Array {

	public static void main(String[] args) {

		// 1. 2*3 배열 생성후 1~6 채우는 코드
		// 선언 후 for문으로 값 할당
		int[][] array = new int[2][3]; // 2 * 3 배열 생성

		int temp = 1; // 넣어줄 값 1로 초기화
		int i, j = 0; // for문으로 넣을 변수 선언/초기화

		// for문으로 값 할당
		for (i = 0; i < 2; i++) {
			for (j = 0; j < 3; j++) {
				array[i][j] = temp;
				temp++; // 할당할 값 증가
				System.out.printf("%d", array[i][j]); // 값 출력
			}
			System.out.println();
		}

		// 2. 배열의 내용을 복사하는 코드
		int[][] array2 = new int[2][3]; // 복사하기 위해 새 배열 생성

		// 배열 복사 함수. array의 인덱스 0번째 값을 array2 인덱스 0번째 배열에 array의 크기(6)만큼 복사
		System.arraycopy(array, 0, array2, 0, array.length);

		System.out.println("===============================");
		for (i = 0; i < 2; i++) {
			for (j = 0; j < 3; j++) {
				System.out.printf("%d", array2[i][j]); // 값 출력
			}
			System.out.println();
		}

		// 3 . 1000개의 int 배열에 두번째 항목을 제외하고 나머지 999개를 복사하는 코드를 효율적으로 작성
		System.out.println("===============================");
		int[] array3 = new int[1000];

		for (i = 0; i < 1000; i++) {
			array3[i] = i + 1; // 인덱스는 0부터 시작이기 때문에 1을 더해서 배열에 저장함
			// System.out.println(array3[i]); // 데이터 정상적으로 저장 됨
		}

		// 두 번째 항목을 제외하고 나머지 999개를 복사하는 코드를 효율적으로 작성
		
		// 복사를 위한 함수 생성
		int[] array4 = new int[1000];

		// 배열 복사 함수. array3의 인덱스 1번째 값을 array4 인덱스 0번째 배열에 array3의 크기-1(999)만큼 복사
		System.arraycopy(array3, 1, array4, 0, array3.length-1);
		
		// 결과 출력 (값이 2부터 1000까지 출력 됨)
		for (i = 0; i < 999; i++) {
			 System.out.printf("%d ",array4[i]); // 데이터 정상적으로 저장 됨
		}
		
	}

}
