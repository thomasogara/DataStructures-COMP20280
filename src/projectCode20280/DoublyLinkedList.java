package projectCode20280;

import java.util.Iterator;

public class DoublyLinkedList<E> implements List<E> {
	private Node<E> head;
	private Node<E> tail;
	private int length;

	private static class Node<E> {
		E data;
		Node<E> next;
		Node<E> prev;

		public Node(E e){
			this.data = e;
		}
	}

	private class ListIterator<E> implements Iterator<E>{
		Node<E> curr;

		public ListIterator(Node<E> curr){
			this.curr = curr;
		}

		@Override
		public boolean hasNext() {
			return curr != null;
		}

		@Override
		public E next() {
			E old = curr.data;
			curr = curr.next;
			return old;
		}
	}

	public E first(){
		return this.get(0);
	}

	public E last(){
		return this.get(size() - 1);
	}

	private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
		Node<E> newNode = new Node<>(e);
		predecessor.next = newNode;
		successor.prev = newNode;
		newNode.prev = predecessor;
		newNode.next = successor;
		++length;
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
		if(index < 0 || index >= size()) return null;
		return getNode(index).data;
	}

	private Node<E> getNode(int index){
		if(index < 0 || index >= size()) return null;
		Node<E> curr = null;
		if(index < size() / 2){
			curr = head;
			for(int i = 0; i < index; i++){
				curr = curr.next;
			}
		}else{
			curr = tail;
			for(int i = 0; i < size() -1 - index; i++){
				curr = curr.prev;
			}
		}
		return curr;
	}

	@Override
	public void add(int index, E e) {
		if(index == 0){
			addFirst(e);
		}else if(index == this.size()){
			addLast(e);
		}else{
			addBetween(e, getNode(index - 1), getNode(index));
		}
	}

	@Override
	public E remove(int index) {
		if(index == 0){
			return removeFirst();
		}else if(index == size() - 1){
			return removeLast();
		}else{
			return removeBetween(getNode(index - 1), getNode(index + 1));
		}
	}

	private E removeBetween(Node<E> predecessor, Node<E> successor){
		E old = predecessor.next.data;
		predecessor.next = successor;
		successor.prev = predecessor;
		--length;
		return old;
	}

	@Override
	public Iterator<E> iterator() {
		return new ListIterator<>(head);
	}


	@Override
	public E removeFirst() {
		if(size() == 1){
			E old = head.data;
			head = null;
			tail = null;
			--length;
			return old;
		}else{
			E old = head.data;
			head = head.next;
			head.prev = null;
			--length;
			return old;
		}
	}

	@Override
	public E removeLast() {
		if(size() == 1){
			E old = tail.data;
			head = null;
			tail = null;
			--length;
			return old;
		}else{
			E old = tail.data;
			tail = tail.prev;
			tail.next = null;
			--length;
			return old;
		}
	}


	@Override
	public void addFirst(E e) {
		if(size() == 0){
			Node<E> newNode = new Node<>(e);
			head = newNode;
			tail = newNode;
		}else{
			Node<E> newNode = new Node<>(e);
			newNode.next = head;
			head.prev = newNode;
			head = newNode;
		}
		++length;
	}

	@Override
	public void addLast(E e) {
		if(size() == 0){
			Node<E> newNode = new Node<>(e);
			head = newNode;
			tail = newNode;
		}else{
			Node<E> newNode = new Node<>(e);
			newNode.prev = tail;
			tail.next = newNode;
			tail = newNode;
		}
		++length;
	}

	@Override
	public String toString(){
		Node<E> curr = this.head;
		StringBuilder s = new StringBuilder();
		s.append("[");
		while(curr != null){
			s.append(curr.data);
			if(curr.next != null) s.append(", ");
			curr = curr.next;
		}
		s.append("]");
		return s.toString();
	}

	public static void main(String[] args) {
		DoublyLinkedList<Integer> ll = new DoublyLinkedList<Integer>();
		System.out.println(ll);
		ll.addFirst(0);
		System.out.println(ll);
		ll.addFirst(1);
		System.out.println(ll);
		ll.addFirst(2);
		System.out.println(ll);
		ll.addLast(-1);
		System.out.println(ll);

		ll.removeFirst();
		System.out.println("first removed");
		System.out.println(ll);

		ll.removeLast();
		System.out.println("last removed");
		System.out.println(ll);

		for(Integer e: ll) {
			System.out.println("value: " + e);
		}
	}


}
