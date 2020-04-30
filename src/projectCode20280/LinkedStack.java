package projectCode20280;

public class LinkedStack<E> implements Stack<E> {
	private SinglyLinkedList<E> list;

	public LinkedStack(){
		this.list = new SinglyLinkedList<>();
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
		return this.list.size();
	}

	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}

	@Override
	public void push(E e) {
		this.list.addFirst(e);
	}

	@Override
	public E top() {
		return this.list.get(0);
	}

	@Override
	public E pop() {
		return this.list.removeFirst();
	}

	@Override
	public String toString() {
		return list.toString();
	}
}
