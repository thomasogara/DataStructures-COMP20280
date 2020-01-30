package projectCode20280;

import java.util.Iterator;
import java.util.function.Consumer;

public class SinglyLinkedList<E> implements List<E> {
	private Node<E> head;
	private int length;

	private static class Node<E> {
		E data;
		Node<E> next;

		public Node(E data){
			this.data = data;
		}
	}

	private static class listIterator<E> implements Iterator<E>{
		Node<E> head;

		public listIterator(Node<E> head){
			this.head = head;
		}

		@Override
		public boolean hasNext(){
			return this.head != null;
		}

		@Override
		public E next(){
			E next = head.data;
			this.head = this.head.next;
			return next;
		}

		@Override
		public void remove() {

		}

		@Override
		public void forEachRemaining(Consumer<? super E> action) {
			while(this.hasNext()){
				action.accept(this.next());
			}
		}
	}
	
	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}

	@Override
	public E get(int index) {
		Node<E> curr = this.head;
		for(int i = 0; i < index; i++){
			curr = curr.next;
		}
		return curr.data;
	}

	@Override
	public void add(int index, E e) {
		Node<E> curr = this.head;
		for(int i = 0; i < index; i++){
			curr = curr.next;
		}

		this.length++;
		curr.data = e;
	}

	@Override
	public E remove(int index) {
		Node<E> prev = this.head;
		Node<E> curr = prev.next;
		for(int i = 0; i < index-1; i++){
			prev = curr;
			curr = curr.next;
		}
		prev.next = curr.next;

		this.length--;
		return curr.data;
	}

	@Override
	public Iterator<E> iterator() {
		return new listIterator<E>(this.head);
	}

	@Override
	public int size() {
		return this.length;
	}	
	

	@Override
	public E removeFirst() {
		E headData = this.head.data;
		this.head = this.head.next;

		this.length--;
		return headData;
	}

	@Override
	public E removeLast() {
		Node<E> prev = this.head;
		Node<E> curr = prev.next;
		for(int i = 0; i < this.length-1; i++){
			prev = curr;
			curr = curr.next;
		}
		prev.next = curr.next;

		this.length--;
		return curr.data;
	}

	@Override
	public void addFirst(E e) {
		Node<E> newHead = new Node<E>(e);
		if(this.head != null) {
			newHead.next = this.head.next;
		}

		this.length++;
		this.head = newHead;
	}

	@Override
	public void addLast(E e) {
		Node<E> curr = this.head;
		while(curr.next != null){
			curr = curr.next;
		}

		this.length++;
		curr.next = new Node<E>(e);
	}

	@Override
	public String toString(){
		Node<E> curr = this.head;
		StringBuilder s = new StringBuilder();
		while(curr != null){
			s.append(curr.data).append("->");
			curr = curr.next;
		}
		s.append("\n");
		return s.toString();
	}

	public static void main(String[] args) {
		String[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");

		SinglyLinkedList<String> sll = new SinglyLinkedList<String>();
		for (String s : alphabet) {
			sll.addFirst(s);
			sll.addLast(s);
		}
		System.out.println(sll.toString());

		sll.removeFirst();
		System.out.println(sll.toString());
		
		sll.removeLast();
		System.out.println(sll.toString());

		sll.remove(2);
		System.out.println(sll.toString());
		
		for (String s : sll) {
			System.out.print(s + ", ");
		}
		System.out.println();
	}


}
