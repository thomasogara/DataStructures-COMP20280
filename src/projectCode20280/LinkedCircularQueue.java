package projectCode20280;

/**
 * Realization of a circular FIFO queue as an adaptation of a
 * CircularlyLinkedList. This provides one additional method not part of the
 * general Queue interface. A call to rotate() is a more efficient simulation of
 * the combination enqueue(dequeue()). All operations are performed in constant
 * time.
 */

public class LinkedCircularQueue<E> implements Queue<E> {
	private Node<E> rear;
	private Node<E> front;
	private int length;

	private static class Node<E>{
		Node<E> next;
		E data;

		Node(E e){
			this.data = e;
		}
	}

	public static void main(String[] args) {
		LinkedCircularQueue<Integer> queue = new LinkedCircularQueue<>();
		for(int i = 0; i < 10; i++){
			queue.enqueue(i);
		}

		for(int i = 0; i < 10; i++){
			System.out.println(queue.first());
			queue.rotate();
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
	public void enqueue(E e) {
		Node<E> node = new Node<>(e);
		if(this.rear == null){
			this.rear = node;
			this.front = node;
		}else{
			node.next = this.front;
			this.rear.next = node;
			this.rear = node;
		}
	}

	@Override
	public E first() {
		if(this.front == null) throw new IllegalStateException("cannot retrieve first item of empty queue");
		return this.front.data;
	}

	@Override
	public E dequeue() {
		if(this.front == null) throw new IllegalStateException("cannot dequeue from empty queue");
		E data = this.front.data;
		if(this.front == this.rear){
			this.front = null;
			this.rear = null;
		}else {
			this.front = this.front.next;
		}
		this.length--;
		return data;
	}

	public void rotate(){
		if(this.front == null) throw new IllegalStateException("cannot rotate empty queue");
        this.rear.next = this.front;
        this.rear = this.front;
		this.front = this.front.next;
	}

}
