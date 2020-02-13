package projectCode20280;

public class LinkedStack<E> implements Stack<E> {
	private int length;
	private Node<E> head;

	private static class Node<E>{
		E data;
		Node<E> next;
		Node(E e){
			this.data = e;
		}
	}

	public static void main(String[] args) {
		LinkedStack<Integer> stack = new LinkedStack<>();
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
		stack.push(11);
		stack.push(12);
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
		Node<E> top = new Node<>(e);
		top.next = this.head;
		this.head = top;
		this.length++;
	}

	@Override
	public E top() {
		return this.head.data;
	}

	@Override
	public E pop() {
		if(this.head != null) {
			E data = this.head.data;
			this.head = this.head.next;
			this.length--;
			return data;
		}else{
			throw new RuntimeException("this stack is empty, head cannot be popped");
		}
	}

}
