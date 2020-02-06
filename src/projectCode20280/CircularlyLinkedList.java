package projectCode20280;

import java.util.Iterator;

public class CircularlyLinkedList<E> implements List<E> {
	private int length;
	private Node<E> tail;

	private static class Node<E> {
		E data;
		Node<E> next;

		public Node(E e){
			this.data = e;
		}

		public String toString(Node<E> tail) {
			return "Node{" +
					"data=" + data +
					", next=" + (next != tail && next != null ? next.toString(tail) : "HEAD") +
					'}';
		}
	}

	private static class Iterator<E> implements java.util.Iterator<E>{
		private Node<E> tail;
		private Node<E> curr;

		public E next(){
			E data = this.curr.data;
			this.curr = this.curr.next;
			return data;
		}

		public boolean hasNext(){
			return this.curr.next != null && this.curr.next != this.tail;
		}

		public Iterator(Node<E> e){
			this.tail = e;
			this.curr = this.tail;
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
	public E get(int index) {
		return this.getNode(index).data;
	}

	private Node<E> getNode(int index){
		Node<E> curr = this.tail;
		int i;
		for(i = 0; i < index; i++){
			if(curr.next != null) {
				curr = curr.next;
			}else{
				return null;
			}
		}
		return curr;
	}

	@Override
	public void add(int i, E e) {
		Node<E> newNode = new Node(e);
		if (this.tail == null) {
			this.tail = newNode;
			this.tail.next = tail;
			this.length++;
			return;
		}
		newNode.next = this.getNode(i).next;
		this.getNode(i).next = newNode;
		this.length++;
	}

	@Override
	public E remove(int i) {
		E data = this.get(i);
		this.getNode(i-1).next = this.getNode(i).next;
		this.length--;
		return data;
	}

	@Override
	public E removeFirst() {
		Node<E> old = this.tail;
		Node<E> last = this.getNode(this.size() - 1);
		last.next = old.next;
		this.tail = last.next;
		this.length--;
		return old.data;
	}

	@Override
	public E removeLast() {
		E data = this.get(this.size() - 1);
		this.remove(this.size() - 1);
		return data;
	}

	@Override
	public Iterator<E> iterator() {
		return new CircularlyLinkedList.Iterator<E>(this.tail);
	}

	@Override
	public void addFirst(E e) {
		this.add(0, e);

	}

	@Override
	public void addLast(E e) {
		Node<E> newNode = new Node<E>(e);
		if(this.tail == null){
			tail = newNode;
			this.length++;
			return;
		}
		Node<E> old = this.getNode(this.size() - 1);
		newNode.next = old.next;
		old.next = newNode;
		this.length++;
	}

	public void rotate() {

	}
	
	public static void main(String[] args) {
		CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<Integer>();
		for(int i = 10; i < 20; ++i) {
			ll.addLast(i);
		}

		System.out.println(ll);

		ll.removeFirst();
		System.out.println(ll);

		ll.removeLast();

		ll.rotate();
		System.out.println(ll);

		ll.removeFirst();
		ll.rotate();
		System.out.println(ll);

		ll.removeLast();
		ll.rotate();
		System.out.println(ll);

		for (Integer e : ll) {
			System.out.println("value: " + e);
		}
	}

	@Override
	public String toString() {
		return "CircularlyLinkedList{" +
				"length=" + this.length +
				", tail=" + this.tail.toString(this.tail) +
				'}';
	}
}
