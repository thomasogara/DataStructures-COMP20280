package projectCode20280;

import java.util.ArrayList;

public class ArrayQueue<E> implements Queue<E> {
	private ArrayList<E> backing_array = new ArrayList<>();
	private int length;
	private int front;

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
		this.backing_array.add(e);
		this.length++;
	}

	@Override
	public E first() {
		if(this.size() > 0){
			return this.backing_array.get(0);
		}
		return null;
	}

	@Override
	public E dequeue() {
		if(this.size() > 0){
			E data = this.backing_array.get(this.front);
			this.backing_array.set(this.front, null);
			this.length--;
			this.front++;
			return data;
		}
		return null;
	}

	@Override
	public String toString(){
		return this.backing_array.toString();
	}

}
