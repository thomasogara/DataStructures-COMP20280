package projectCode20280;

import java.util.Iterator;

public class DoublyLinkedList<E> implements List<E> {
	Node<E> head;
	private int length;

	private static class Node<E> {
		E data;
		Node<E> next;
		Node<E> prev;

		public Node(E e){
			this.data = e;
		}
	}

	private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
		Node<E> curr = this.head;
		while(curr != null && !(curr.prev == predecessor && curr.next == successor)){
			curr = curr.next;
		}
		if(curr != null){
			curr.prev = curr.next;
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
		Node<E> curr = this.head;
		for(int i = 0; curr != null && i < index; i++){
			curr = curr.next;
		}
		return curr.data;
	}

	@Override
	public void add(int index, E e) {
		Node<E> curr = this.head;
		for(int i = 0; curr != null && i < index; i++){
			curr = curr.next;
		}
		Node<E> newNode = new Node<E>(e);
		curr.prev.next = newNode;
		newNode.next = curr;
		newNode.prev = curr.prev;
	}

	@Override
	public E remove(int index) {
		Node<E> curr = this.head;
		for(int i = 0; curr != null && i < index; i++){
			curr = curr.next;
		}
		curr.prev.next = curr.next;
		return curr.data;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public E removeFirst() {
		E data = this.head.data;
		this.head = this.head.next;
		this.head.prev = null;
		return data;
	}

	@Override
	public E removeLast() {
		Node<E> curr = this.head;
		while(curr.next != null){
			curr = curr.next;
		}
		E data = curr.data;
		if(curr.prev != null) {
			curr.prev.next = null;
			curr.prev = null;
		}
		return data;
	}
	

	@Override
	public void addFirst(E e) {
		Node<E> newNode = new Node<E>(e);
		if(this.head != null && this.head.next != null) {
			newNode.next = this.head;
			this.head.next.prev = newNode;
		}
		this.head = newNode;
	}

	@Override
	public void addLast(E e) {
		Node<E> curr = this.head;
		while(curr.next != null){
			curr = curr.next;
		}
		curr.next = new Node<E>(e);
		curr.next.prev = curr;
	}
	
	public static void main(String[] args) {
		   DoublyLinkedList<Integer> ll = new DoublyLinkedList<Integer>();
           ll.addFirst(0);
           ll.addFirst(1);
           ll.addFirst(2);
           ll.addLast(-1);
           System.out.println(ll);
           
           ll.removeFirst();
           System.out.println(ll);

           ll.removeLast();
           System.out.println(ll);
           
           for(Integer e: ll) {
                   System.out.println("value: " + e);
           }
	}

	
}
