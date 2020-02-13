package projectCode20280;

import java.util.ArrayList;

public class ArrayStack<E> implements Stack<E> {
	private ArrayList<E> backing_array = new ArrayList<>();
	private int length;

	public static void main(String[] args) {
		ArrayStack<Integer> stack = new ArrayStack<>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		stack.push(6);
		stack.push(7);
		stack.push(8);
		stack.push(9);
		stack.push(10);
		while(!stack.isEmpty()){
			System.out.println(stack.pop());
		}

	}

	@Override
	public int size() {
		return this.length;
	}

	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}

	@Override
	public void push(E e) {
		this.backing_array.add(e);
		this.length++;
	}

	@Override
	public E top() {
		return this.backing_array.get(this.size() - 1);
	}

	@Override
	public E pop() {
		E data = this.backing_array.get(this.size() - 1);
		this.backing_array.set(this.size() - 1, null);
		this.length--;
		return data;
	}

}
