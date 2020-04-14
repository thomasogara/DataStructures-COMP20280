package projectCode20280;

import java.util.Iterator;

public class SinglyLinkedList<E> implements List<E>{
	private Node<E> head;
	private int length;

	static class Node<E> {
		E data;
		Node<E> next;

		public Node(E data){
			this.data = data;
		}
	}

	private static class ListIterator<E> implements Iterator<E>{
		SinglyLinkedList<E> list;
		int i;

		public ListIterator(SinglyLinkedList<E> list) {
			this.list = list;
		}

		@Override
		public boolean hasNext(){
			try{
				this.list.get(i);
				return true;
			}catch (IndexOutOfBoundsException ex){
				return false;
			}
		}

		@Override
		public E next(){
			return this.list.get(i++);
		}
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
		Node<E> node = this.head;
		for(int i = 0; i < index; i++){
			if(node != null) node = node.next;
		}
		return node;
	}

	public void add(int index, E e) {
		if(index > this.size() || index < 0) throw new IndexOutOfBoundsException(String.format("Index %d is out of bounds in list of size %d", index, this.size()));
		if(index == 0){
			Node<E> newNode = new Node<>(e);
			newNode.next = this.head;
			this.head = newNode;
		}else{
			Node<E> temp = this.getNode(index);
			Node<E> prev = this.getNode(index - 1);
			Node<E> newNode = new Node<>(e);
			newNode.next = temp;
			if (prev != null) prev.next = newNode;
		}
		this.length++;
	}

	@Override
	public E remove(int index) {
		if(index >= this.size() || index < 0) throw new IndexOutOfBoundsException(String.format("Index %d is out of bounds in list of size %d", index, this.size()));
		Node<E> discard = this.getNode(index);
		if(index == 0){
			this.head = this.head.next;
		}else{
			Node<E> prev = this.getNode(index - 1);
			prev.next = discard.next;
		}
		this.length--;
		return discard.data;
	}

	@Override
	public Iterator<E> iterator() {
		return new ListIterator<E>(this);
	}

	@Override
	public int size() {
		return this.length;
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
		this.add(0, e);
	}

	@Override
	public void addLast(E e) {
		this.add(this.size(), e);
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

		SinglyLinkedList<String> sll = new SinglyLinkedList<>();
		for (String s : alphabet) {
			sll.addFirst(s);
			sll.addLast(s);
		}
		System.out.println(sll);

		sll.removeFirst();
		System.out.println(sll);
		
		sll.removeLast();
		System.out.println(sll);

		sll.remove(2);
		System.out.println(sll);
		
		for (String s : sll) {
			System.out.print(s + ", ");
		}
		System.out.println();
	}


}
