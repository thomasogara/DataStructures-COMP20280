package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

public class Main{
    static BufferedWriter heap_bw;
    static BufferedWriter pq_bw;

    public static void main(String[] args) throws IOException {
        /*
        File heap_file = new File("../test_out/heap_out.txt");
        FileWriter heap_fw = new FileWriter(heap_file);
        heap_bw = new BufferedWriter(heap_fw);
        for(int i = 1; i <= 100; i++){
            timeHeapSort(10000 * i);
        }
        heap_bw.close();
        */

        File pq_file = new File("../test_out/pq_out.txt");
        FileWriter pq_fw = new FileWriter(pq_file);
        pq_bw = new BufferedWriter(pq_fw);
        for(int i = 1; i <= 10; i++){
            timePQSort(1000 * i);
        }
        pq_bw.close();
    }

    public static void timeHeapSort(int size) throws IOException {
        Integer[] keys = new Integer[size];
        for(int i = 0; i < size; i++){
            keys[i] = size - i;
        }
        int[] a = new int[size];
        double average = 0;
        for(int j = 0; j < 10; j++) {
            int i = 0;
            long start = System.nanoTime();
            HeapPriorityQueue<Integer, Integer> hpq = new HeapPriorityQueue<>(keys, keys);
            while (!hpq.isEmpty()) {
                a[i++] = hpq.removeMin().getKey();
            }
            long elapsed = System.nanoTime() - start;
            average += (double)elapsed / 10;
        }
        heap_bw.write(String.format("%-9d %f\n", size, average));
    }

    public static void timePQSort(int size) throws IOException {
        Integer[] keys = new Integer[size];
        for(int i = 0; i < size; i++){
            keys[i] = size - i;
        }
        int[] a = new int[size];
        double average = 0;
        for(int j = 0; j < 1; j++){
            int i = 0;
            long start = System.nanoTime();
            SinglyLinkedList<Integer> pq = new SinglyLinkedList<>(keys);
            while (!pq.isEmpty()) {
                a[i++] = pq.removeFirst();
            }
            long elapsed = System.nanoTime() - start;
            average += (double)elapsed / 1;
        }
        pq_bw.write(String.format("%-9d %f\n", size, average));
    }
}
