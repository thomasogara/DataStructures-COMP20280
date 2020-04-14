package projectCode20280;

import java.util.Arrays;

public class ArrayQueue<E> implements Queue<E> {
	private Object[] backing_array;
	private int length;
	private int front;
	private int rear;

	public static void main(String[] args){
		ArrayQueue<Integer> queue = new ArrayQueue<>();
		queue.enqueue(1);
		queue.enqueue(2);
		queue.enqueue(3);
		queue.enqueue(4);
		queue.enqueue(5);
		queue.enqueue(6);
		queue.enqueue(7);
		queue.enqueue(8);
		queue.enqueue(9);
		queue.enqueue(10);
		while(!queue.isEmpty()){
			System.out.println(queue.dequeue());
		}
		System.out.println(queue);

	}

	@Override
	public int size(){
		return this.length;
	}

	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}

	@Override
	public void enqueue(E e) {

	}

	@Override
	public E first() {
		return null;
	}

	@Override
	public E dequeue() {
		return null;
	}

	@Override
	public String toString(){
		return Arrays.toString(this.backing_array);
	}

}
