package FirstAssignment;

public class ArrStack {

	private Integer[] stack;
	private int index;
	public ArrStack(int size) {
		this.stack = new Integer[size];
		this.index = -1;
	}

	public void push(int n) {
		try {
			stack[++index] = n;
			showStack();
		}catch(Exception e) {
			System.out.println("스택이 가득 찼습니다.");
		}
	}

	public Integer pop() {
		try {
			int p = stack[index];
			stack[index--] = null;
			showStack();
			return p;
		}catch(Exception e) {
			System.out.println("스택이 비어있습니다.");
		}
		return null;
		
	}
	
	public void showStack() {
		System.out.print("\n현재 스택 : [ ");
		for(Integer i : stack) {
			System.out.print(i + " ");
		}
		System.out.print("]\n ");
	}
	
	public int getSize() {
		return this.stack.length;
	}
}
