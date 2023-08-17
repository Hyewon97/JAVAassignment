package basicAssignment;

import java.util.ArrayList;
import java.util.List;

public class ListStack {

	private List<Integer> stackList;

	public ListStack() {
		stackList = new ArrayList<>();
	}

	public void push(int value) {
		stackList.add(value);
	}

	public int pop() {
		// 데이터가 없으면 empty 출력
		if (isEmpty()) {
			throw new IllegalStateException("empty");
		}
		// 마지막 인덱스 번호를 리스트 값 -1로 지정
		int lastIndex = stackList.size() - 1;

		//
		int poppedValue = stackList.get(lastIndex);
		stackList.remove(lastIndex);
		return poppedValue;
	}

	public boolean isEmpty() {
		return stackList.isEmpty();
	}

	public static void main(String[] args) {
		ListStack stack = new ListStack();

		// 값 1, 2, 3 push
		stack.push(1);
		stack.push(2);
		stack.push(3);

		System.out.println("출력: " + stack.pop());
		System.out.println("출력: " + stack.pop());

	}

}
