package projectCode20280;

import java.util.Iterator;

public class ArrayList<E> implements List<E>, Iterable<E>{
    // the array containing the elements of the ArrayList
    private Object[] backing_array;

    // the number of items currently in the list
    private int capacity;

    // the length of the backing array
    private int length;

    // the percentage increase in size upon resizing of the list
    private static final int RESIZE_PERCENT = 120;

    // initial size of the backing array
    private static final int MIN_SIZE = 10;

    protected class ListIterator<E> implements Iterator<E>{
        private ArrayList<E> list;
        private int index;

        @Override
        public boolean hasNext() {
            return (index < list.size());
        }

        @Override
        public E next() {
            return list.get(index++);
        }

        public ListIterator(ArrayList<E> e){
            this.list = e;
        }
    }

    public ArrayList(){
        // initialise the array on construction
        // minimum size of 5 is a result of the resize factor being 20%
        this.backing_array = new Object[MIN_SIZE];
        length = MIN_SIZE;
    }

    /**
     * Alternative constructor, taking an array of elements as a parameter
     * Constructs an ArrayList containing the items in E, in O(1) time
     * @param e the items to be added to the ArrayList during initialisation
     */
    public ArrayList(E[] e){
        if(e.length > MIN_SIZE){
            backing_array = e;
            length = e.length;
        }else{
            backing_array = new Object[MIN_SIZE];
            for(int i = 0; i < e.length; i++){
                backing_array[i] = e[i];
            }
            length = MIN_SIZE;
        }
    }

    @Override
    public int size() {
        return capacity;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public E get(int i) {
        if(i >= capacity) throw new IndexOutOfBoundsException(String.format("Index %d out of bounds in list of size %d", i, capacity));
        return (E)backing_array[i];
    }

    public void set(int i, E e){
        if(i >= capacity) throw new IndexOutOfBoundsException(String.format("Index %d out of bounds in list of size %d", i, capacity));
        backing_array[i] = e;
    }

    @Override
    public void add(int i, E e) {
        if(i > capacity) throw new IndexOutOfBoundsException(String.format("Index %d out of bounds in list of size %d", i, capacity));

        // quick check to ensure resize is not needed. if needed, perform resize
        if(capacity == length - 1) resize();

        // if inserting at the end of the list, simply insert
        if(i == capacity){
            backing_array[i] = e;
        }else{
            // move items in indices [i, capacity] forward by 1
            for(int j = capacity; j >= i; j--){
                set(j+1, get(j));
            }
            // insert e at position i
            set(i, e);
        }
        capacity++;
    }

    @Override
    public void addFirst(E e) {
        add(0, e);
    }

    @Override
    public void addLast(E e) {
        add(size(), e);
    }

    @Override
    public E remove(int i) {
        if(i >= capacity) throw new IndexOutOfBoundsException(String.format("Index %d out of bounds in list of size %d", i, capacity));
        E data = this.get(i);
        set(i, null);
        for(int j = i; i < capacity; j++){
            set(j, get(j + 1));
        }
        capacity--;
        return data;
    }

    @Override
    public E removeFirst() {
        return remove(0);
    }

    @Override
    public E removeLast() {
        return remove(size() - 1);
    }

    @Override
    public Iterator<E> iterator() {
        return new ListIterator<>(this);
    }

    protected void resize(){
        int newLength = (length * RESIZE_PERCENT) / 100;
        Object[] newArray = new Object[newLength];
        for(int i = 0; i < length; i++){
            newArray[i] = backing_array[i];
        }
        backing_array = newArray;
        this.length = newLength;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for(int i = 0; i < capacity; i++){
            sb.append(get(i)).append(", ");
        }
        if(sb.length() > 1) sb.delete(sb.length() - 2, sb.length());
        sb.append(']');
        return sb.toString();
    }


    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            if((i % 2) == 0) a.addLast(i);
            else a.addFirst(i);
        }
        System.out.println(a);
    }
}
