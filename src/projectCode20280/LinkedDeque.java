package projectCode20280;

public class LinkedDeque<E> implements Deque<E> {
	// NOTE: My DoublyLinkedList class supports constant-time
	// insertions, retrievals, and removals at both ends. This makes it ideal
	// as an underlying data structure for a Deque, which needs constant
	// time insertions, retrievals, and removals at both ends
	private DoublyLinkedList<E> list;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public LinkedDeque(){
		this.list = new DoublyLinkedList<>();
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
	public E first() {
		return this.list.get(0);
	}

	@Override
	public E last() {
		return this.list.get(this.list.size() - 1);
	}

	@Override
	public void addFirst(E e) {
		this.list.addFirst(e);
		
	}

	@Override
	public void addLast(E e) {
		this.list.addLast(e);
		
	}

	@Override
	public E removeFirst() {
		return this.list.removeFirst();
	}

	@Override
	public E removeLast() {
		return this.list.removeLast();
	}

}
