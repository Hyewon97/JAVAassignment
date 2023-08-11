package FirstAssignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ex2 {

	public static void main(String[] args) {
		// 1. 2x3 배열 생성 후 1부터 6까지 채우고 출력
		int[][] arr1 = new int[2][3];
		int con = 1;
		System.out.print("원본 배열 : ");
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3; j++) {
				arr1[i][j] = con++;
				System.out.print(arr1[i][j] + " ");
			}
		}
		// 2. 배열의 내용 복사
		System.out.print("\n복사 배열 : ");
		int[][] arr2 = arr1.clone();
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(arr1[i][j] + " ");
			}
		}

		// 3. 1000개의 int를 갖는 배열에 1부터 1000까지 저장하고 두 번째 항목을 제외하고 나머지 999개를 복사하는 코드
		int[] arr3 = new int[1000];
		int[] copyArr3 = new int[arr3.length-1];
		for (int i = 0; i < arr3.length; i++) {
			arr3[i] = i + 1;
		}
		// 복사
		System.arraycopy(arr3, 0, copyArr3, 0, 1);
		System.arraycopy(arr3, 2, copyArr3, 1, arr3.length-2);
		System.out.println("\n"+   Arrays.toString(copyArr3));

		// 배열 스택 : push
		System.out.println("배열 스택");
		ArrStack arrStack = new ArrStack(5);
		for (int i = 0; i < arrStack.getSize(); i++) {
			arrStack.push(i + 1);
		}
		// 배열 스택 : pop
		for (int i = 0; i < arrStack.getSize(); i++) {
			System.out.println("pop value : " + arrStack.pop());

		}

		// 리스트 스택 : push
		System.out.println("리스트 스택");
		ListStack listStack = new ListStack();
		for (int i = 0; i < 5; i++) {
			listStack.push(i + 1);
		}
		// 리스트 스택 : pop
		int listStackSize = listStack.getSize();
		for (int i = 0; i < listStackSize; i++) {
			System.out.println("pop value : " + listStack.pop());

		}

	}

}
