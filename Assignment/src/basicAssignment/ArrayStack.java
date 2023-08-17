package basicAssignment;

public class ArrayStack {
	private int maxSize; // 최대 크기
	private int[] stackArray;
	private int top;

	public ArrayStack(int size) {
		maxSize = size;
		stackArray = new int[maxSize]; // 최대 크기의 int 배열 생성
		top = -1; // top을 -1로 초기화
	}

	// 데이터 저장
	public void push(int value) {
		if (top < maxSize - 1) { // top이 마지막 인덱스 값이 아니면,, 사용할 수 있는 공간ㅇ ㅣ있다면
			stackArray[++top] = value; // top의 위치를 옯기고 입력받은 값을 저장함
		} else {
			System.out.println("스택 꽉 참");
		}
	}

	// 데이터 출력
	public int pop() {
		if (top >= 0) { // 데이터가 있으면
			return stackArray[top--]; // 인덱스 top의 값을 출력하고 인덱스 값 1감소 (위치 왼쪽으로 옮기기)
		} else {
			System.out.println("스택 비었음");
			return -1;
		}
	}

	public boolean isEmpty() {
		return (top == -1); // 스택이 비었으면 top을 -1로 초기화
	}

	public boolean isFull() {
		return (top == maxSize - 1);
	}

	public static void main(String[] args) {
		ArrayStack stack = new ArrayStack(5); // 크기가 5인 스택 생성

		// 값 1, 2, 3 push
		stack.push(1);
		stack.push(2);
		stack.push(3);

		System.out.println("출력: " + stack.pop());
		System.out.println("출력: " + stack.pop());

	}
}
