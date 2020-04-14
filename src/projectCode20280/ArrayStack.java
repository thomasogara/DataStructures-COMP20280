package projectCode20280;

import java.util.Arrays;

public class ArrayStack<E> implements Stack<E> {
	private Object[] backing_array;
	private int length = 0; // current length of the backing array
	private int capacity = 0; // current number of items in the stack

	public static void main(String[] args) {
		ArrayStack<Integer> stack = new ArrayStack<>();
		for(int i = 0; i < 100; i++) {
			stack.push(i);
		}
		while(!stack.isEmpty()){
			System.out.println(stack.pop());
		}
	}

	@Override
	public int size() {
		return this.capacity;
	}

	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}

	@Override
	public void push(E e) {
		if(this.backing_array == null){
			// array always initialised with length of 10
			int MIN_SIZE = 10;
			this.backing_array = new Object[MIN_SIZE];
			this.length = MIN_SIZE;
		}
		else if(this.capacity > this.length - 1){
			resizeArray();
		}
		this.backing_array[this.capacity] = e;
		this.capacity++;
	}

	private final void resizeArray(){
		// array always resized to 150% of current size
		int RESIZE_FACTOR = 150;
		int increasedLength = (this.length * RESIZE_FACTOR) / 100;
		Object[] newArray = new Object[increasedLength];
		for(int i = 0; i < length; i++){
			newArray[i] = backing_array[i];
		}
		backing_array = newArray;
		this.length = increasedLength;
	}


	@Override
	@SuppressWarnings("unchecked")
	public E top() {
		return (E) this.backing_array[this.capacity - 1];
	}

	@Override
	public E pop() {
		E data = this.top();
		this.backing_array[this.capacity - 1] = null;
		this.capacity--;
		return data;
	}

}
