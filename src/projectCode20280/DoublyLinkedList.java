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
		DoublyLinkedList<E> list;
		int i;

		public ListIterator(DoublyLinkedList<E> list){
			this.list = list;
		}

		@Override
		public boolean hasNext() {
			try{
				this.list.get(i);
				return true;
			} catch(IndexOutOfBoundsException ex){
				return false;
			}
		}

		@Override
		public E next() {
			return this.list.get(i++);
		}
	}

	private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
		Node<E> newNode = new Node<>(e);
		if(predecessor != null) predecessor.next = newNode;
		newNode.prev = predecessor;
		newNode.next = successor;
		if(successor != null) successor.prev = newNode;
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

		if(index == 0) return this.head;
		if(index == this.size() - 1) return this.tail;

		Node<E> node = null;
		if(this.size() - index <= index){
			node = this.tail;
			for(int i = 0; i < this.size() - index; i++){
				node = node.prev;
			}
		}else{
			node = this.head;
			for(int i = 0; i < index; i++){
				node = node.next;
			}
		}

		return node;
	}

	@Override
	public void add(int index, E e) {
		if(index > this.size() || index < 0) throw new IndexOutOfBoundsException(String.format("Index %d is out of bounds in list of size %d", index, this.size()));
		if(index == 0){
			Node<E> newNode = new Node<>(e);
			newNode.next = this.head;
			if(this.head != null) this.head.prev = newNode;
			this.head = newNode;
		}else if(index < this.size()){
			this.addBetween(e, this.getNode(index - 1), this.getNode(index));
		}else{
			Node<E> newNode = new Node<>(e);
			Node<E> prevTail = this.tail;
			prevTail.next = newNode;
			newNode.prev = prevTail;
			this.tail = newNode;
		}
		this.length++;
	}

	@Override
	public E remove(int index) {
		if(index >= this.size() || index < 0) throw new IndexOutOfBoundsException(String.format("Index %d is out of bounds in list of size %d", index, this.size()));
		Node<E> curr;
		if(index == 0){
			curr = this.head;
			this.head = this.head.next;
			this.head.prev = null;
		}else if(index < this.size()){
			curr = this.getNode(index);
			curr.prev.next = curr.next;
			curr.next.prev = curr.prev;
		}else{
			curr = this.tail;
			this.tail.prev.next = null;
			this.tail = this.tail.prev;
		}
		this.length--;
		return curr.data;
	}

	@Override
	public Iterator<E> iterator() {
		return new ListIterator<>(this);
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
	public void addFirst(E e) {
		this.add(0, e);;
	}

	@Override
	public void addLast(E e) {
		this.add(this.size(), e);
	}

	@Override
	public String toString(){
		Node<E> curr = this.head;
		StringBuilder s = new StringBuilder();
		s.append(" <=> ");
		while(curr != null){
			s.append(curr.data).append(" <=> ");
			curr = curr.next;
		}
		s.append("\n");
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
