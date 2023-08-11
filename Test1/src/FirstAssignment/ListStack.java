package FirstAssignment;

import java.util.ArrayList;
import java.util.List;

public class ListStack {
	private List<Integer> stack;

	public ListStack() {
		this.stack = new ArrayList<>();
	}

	public void push(Integer n) {
		stack.add(n);
		showStack();
	}

	public Integer pop() {
		try {
			int p = stack.remove(stack.size()-1);
			showStack();
			return p;
		}catch(Exception e) {
			System.out.println("스택이 비어있습니다.");
		}
		return null;
		
	}

	public void showStack() {
		System.out.print("\n현재 스택 : [ ");
		for (Integer i : stack) {
			System.out.print(i + " ");
		}
		System.out.print("]\n ");
	}
	
	public int getSize() {
		return this.stack.size();
	}
}
