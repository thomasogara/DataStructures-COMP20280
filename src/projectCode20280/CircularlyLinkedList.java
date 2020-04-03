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
			return data + " -> " + (next != tail && next != null ? next.toString(tail) : "TAIL");
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
		if(index >= this.size() || index < 0) throw new IndexOutOfBoundsException(String.format("Index %d is out of bounds in list of size %d", index, this.size()));
		return this.getNode(index).data;
	}

	private Node<E> getNode(int index){
		if(index > this.size() || index < 0) throw new IndexOutOfBoundsException(String.format("Index %d is out of bounds in list of size %d", index, this.size()));
		Node<E> curr = this.tail;
		for(int i = 0; i < index; i++){
			if(curr.next != null) curr = curr.next;
		}
		return curr;
	}

	@Override
	public void add(int i, E e) {
		if(i > this.size() || i < 0) throw new IndexOutOfBoundsException(String.format("Index %d is out of bounds in list of size %d", i, this.size()));
		Node<E> newNode = new Node<>(e);
		if (i == 0) {
			Node<E> next = this.tail;
			Node<E> last = null;
			if(this.tail != null) last = this.getNode(this.size() - 1);
			if(last != null) last.next = newNode;
			newNode.next = this.tail;
			this.tail = newNode;

		}else if(i == this.size()){
			newNode.next = this.tail;
			this.getNode(i - 1).next = newNode;
		}else{
			newNode.next = this.getNode(i);
			this.getNode(i - 1).next = newNode;
		}
		this.length++;
	}

	@Override
	public E remove(int i) {
		if(i >= this.size() || i < 0) throw new IndexOutOfBoundsException(String.format("Index %d is out of bounds in list of size %d", i, this.size()));
		if(this.tail == null) throw new IllegalStateException("Cannot remove from empty list");
		Node<E> node;
		if(i == 0){
			node = this.tail;
			this.getNode(this.size() - 1).next = this.tail.next;
			this.tail = this.tail.next;
		}else{
			node = this.getNode(i);
			this.getNode(i - 1).next = node.next;
		}
		this.length--;
		return node.data;
	}

	@Override
	public E removeFirst() {
		return this.remove(0);
	}

	@Override
	public E removeLast() {
		return this.remove(this.size() - 1);
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
		this.add(this.size(), e);
	}

	public void rotate() {
		if(this.tail == null) throw new IllegalStateException("Cannot rotate an empty list");
		this.tail = this.tail.next;
	}
	
	public static void main(String[] args) {
		CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<Integer>();
		for(int i = 10; i < 20; ++i) {
			ll.addLast(i);
		}

		System.out.println(ll);

		ll.removeFirst();
		System.out.println("first removed");
		System.out.println(ll);

		ll.removeLast();
		System.out.println("last removed");
		System.out.println(ll);

		ll.rotate();
		System.out.println("rotated");
		System.out.println(ll);

		ll.removeFirst();
		System.out.println("first removed");
		System.out.println(ll);

		ll.rotate();
		System.out.println("rotated");
		System.out.println(ll);

		ll.removeLast();
		System.out.println("last removed");
		System.out.println(ll);

		ll.rotate();
		System.out.println("rotated");
		System.out.println(ll);

		for (Integer e : ll) {
			System.out.println("value: " + e);
		}
	}

	@Override
	public String toString() {
		return "CircularlyLinkedList{" +
				"length=" + this.length +
				", " + this.tail.toString(this.tail) +
				'}';
	}
}
