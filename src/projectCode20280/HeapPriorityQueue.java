package projectCode20280;

/*
 */

import java.util.ArrayList;
import java.util.Comparator;

/**
 * An implementation of a priority queue using an array-based heap.
 */

public class HeapPriorityQueue<K,V> extends AbstractPriorityQueue<K,V> {
  private ArrayList<PQEntry<K,V>> values = new ArrayList<>();

	/** Creates an empty priority queue based on the natural ordering of its keys. */
  public HeapPriorityQueue() { super(); }

  /**
   * Creates an empty priority queue using the given comparator to order keys.
   * @param comp comparator defining the order of keys in the priority queue
   */
  public HeapPriorityQueue(Comparator<K> comp) { super(comp); }

  /**
   * Creates a priority queue initialized with the respective
   * key-value pairs.  The two arrays given will be paired
   * element-by-element. They are presumed to have the same
   * length. (If not, entries will be created only up to the length of
   * the shorter of the arrays)
   * @param keys an array of the initial keys for the priority queue
   * @param values an array of the initial values for the priority queue
   */
  public HeapPriorityQueue(K[] keys, V[] values){
    super();
    for(int j = 0; j < Math.min(keys.length, values.length); j++){
      this.add(new PQEntry<>(keys[j], values[j]));
    }
    heapify();
  }

  public void set(int i, PQEntry<K,V> E){
    this.values.set(i, E);
  }

  public boolean isEmpty(){
    return this.size() == 0;
  }

  public int size(){
    return values.size();
  }

  public PQEntry<K,V> get(int i){
    return this.values.get(i);
  }

  public void add(PQEntry<K,V> E){
    this.values.add(this.size(), E);
    heapify();
  }

  public void remove(int i){
    this.values.remove(i);
  }

  // protected utilities
  protected int parent(int j){
    return (j-1) / 2;
  }
  protected int left(int j){
    return 2*j + 1;
  }
  protected int right(int j){
    return 2*j + 2;
  }
  protected boolean hasLeft(int j){
    return left(j) < this.size() && this.get(left(j)) != null;
  }
  protected boolean hasRight(int j){
    return right(j) < this.size() && this.get(right(j)) != null;
  }

  /** Exchanges the entries at indices i and j of the array list. */
  protected void swap(int i, int j){
    PQEntry<K,V> temp = this.get(j);
    this.set(j, this.get(i));
    this.set(i, temp);
  }

  /** Moves the entry at index j higher, if necessary, to restore the heap property. */
  protected void upheap(int j){
    while(j > 0){
      int p = this.parent(j);
      if(compare(this.get(j), this.get(p)) >= 0) break;
      this.swap(j, p);
      j = p;
      }
  }
  
  /** Moves the entry at index j lower, if necessary, to restore the heap property. */
  protected void downheap(int j){
    while(this.hasLeft(j)){ 
      int leftIndex = left(j);
      int smallChildIndex = leftIndex;
      if(this.hasRight(j)){
        int rightIndex = right(j);
        if(compare(this.get(leftIndex), this.get(rightIndex)) > 0)
          smallChildIndex = rightIndex;
      }
      if(compare(this.get(smallChildIndex), this.get(j)) > 0)
        break;
      swap(j, smallChildIndex);
      j = smallChildIndex;
    }
  }

  /** Performs a bottom-up construction of the heap in linear time. */
  protected void heapify(){
    int startIndex = this.parent(this.size() - 1);
    for(int j = startIndex; j >= 0; j--){
      downheap(j);
    }
  }

  // public methods

  /**
   * Returns (but does not remove) an entry with minimal key.
   * @return entry having a minimal key (or null if empty)
   */
  @Override
  public PQEntry<K,V> min() {
    return this.get(0);
  }

  /**
   * Inserts a key-value pair and return the entry created.
   * @param key     the key of the new entry
   * @param value   the associated value of the new entry
   * @return the entry storing the new key-value pair
   * @throws IllegalArgumentException if the key is unacceptable for this queue
   */
  @Override
  public Entry<K,V> insert(K key, V value) throws IllegalArgumentException{
    checkKey(key);
    PQEntry<K,V> newest = new PQEntry<>(key, value);
    this.add(newest);
    upheap(this.size() - 1);
    return newest;
  }

  /**
   * Removes and returns an entry with minimal key.
   * @return the removed entry (or null if empty)
   */
  @Override
  public PQEntry<K,V> removeMin(){
    if(this.isEmpty()) return null;
    PQEntry<K,V> answer = this.get(0);
    this.swap(0, this.size() - 1);
    this.remove(this.size() - 1);
    downheap(0);
    return answer;
  }

  public static void main(String[] args){
    HeapPriorityQueue hpq = new HeapPriorityQueue<Integer, Integer>();
    hpq.add(new PQEntry<Integer, Integer>(0, 0));
    System.out.println(hpq.get(0).getKey());
  }

  /** Used for debugging purposes only */
  private void sanityCheck() {
    for (int j=0; j < this.size(); j++) {
      int left = left(j);
      int right = right(j);
      if (left < this.size() && compare(this.get(left), this.get(j)) < 0)
        System.out.println("Invalid left child relationship");
      if (right < this.size() && compare(this.get(right), this.get(j)) < 0)
        System.out.println("Invalid right child relationship");
    }
  }
}

