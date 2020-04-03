package projectCode20280;

public class LinkedQueue<E> implements Queue<E> {
	private DoublyLinkedList<E> list;
	public int length;

	public LinkedQueue(){
		this.list = new DoublyLinkedList<>();
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
		this.list.addLast(e);
		this.length++;
	}

	@Override
	public E first() {
		return this.list.get(0);
	}

	@Override
	public E dequeue() {
		if(this.list.size() > 0){
			E data = this.list.removeFirst();
			this.length--;
			return data;
		}else{
			throw new IllegalStateException("queue is empty, cannot dequeue");
		}

	}

}
