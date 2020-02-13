package projectCode20280;

public class LinkedQueue<E> implements Queue<E> {
	public Node<E> front;
	public Node<E> rear;

	public int length;

	private static class Node<E>{
		E data;
		Node<E> next;
		Node<E> prev;

		Node(E e){
			this.data = e;
		}
	}

	public static void main(String[] args) {
		LinkedQueue<Integer> queue = new LinkedQueue<>();
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
		Node<E> last = new Node<>(e);
		if(this.rear == null){
			this.rear = last;
			this.front = last;
		}else{
			last.next = this.rear;
			this.rear.prev = last;
			this.rear = last;
		}
		this.length++;
	}

	@Override
	public E first() {
		return this.front.data;
	}

	@Override
	public E dequeue() {
		if(this.front != null){
			E data = this.front.data;
			if(this.front.prev != null) {
				this.front.prev.next = null;
			}
			this.front = this.front.prev;
			this.length--;
			return data;
		}else{
			throw new RuntimeException("queue is empty, cannot dequeue");
		}

	}

}
