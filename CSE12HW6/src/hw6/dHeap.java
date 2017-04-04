/*
 Name: Anthony Shih
 PID: A11295870
 Login: cs12wei
 */
package hw6;

import java.util.*;

/*
 Format for implementation used from: 
 http://www.sanfoundry.com/java-program-implement-d-ary-heap/
 Code adjusted to fit our comparable variables
 @author Anthony Shih
 @version 1.0
 @since 02-19-2016
 */
public class dHeap<T extends Comparable<? super T>>
        implements dHeapInterface<T> {

    private int d;
    private int heapSize;
    private T[] heap;

    /*
     The constructor  takes one argument: an initial capacity
     Used when binary heap is needed
     @author Anthony Shih
     @version 1.0
     @since 02-19-2016
     */
    public dHeap(int capacity) {
        heapSize = 0;
        d = 2;
        heap = (T[]) new Comparable[capacity + 1];
    }

    /*
     The constructor takes two arguments: an initial capacity
     and the number of children, d
     if d is less than one, throw IllegalArgumentException();
     @author Anthony Shih
     @version 1.0
     @since 02-19-2016
     */
    public dHeap(int capacity, int numChild) {
        heapSize = 0;
        d = numChild;
        heap = (T[]) new Comparable[capacity + 1];
    }

    /*
     Adds value to the end of the "heap", then adjusts the heap accordingly
     with heapifyUp
     @author Anthony Shih
     @version 1.0
     @since 02-19-2016
     */
    public void add(T o) {
        if (isFull()) {
            T[] bigHeap = (T[]) new Comparable[heap.length * 2];
            for (int i = 0; i < heap.length; i++) {
                bigHeap[i] = heap[i];
            }
            heap = bigHeap;
        }
        heap[heapSize++] = o;
        heapifyUp(heapSize - 1);
    }

    /*
     Taking the root from the heap, placing the last node in its place
     and heapifyDown to update the heap
     @author Anthony Shih
     @version 1.0
     @since 02-19-2016
     */
    public T removeSmallest() {
        if (isEmpty()) {
            throw new NoSuchElementException("No elements in heap");
        }
        T temp = heap[0];
        heap[0] = heap[heapSize - 1];
        heap[heapSize - 1] = null;
        heapSize--;
        heapifyDown(0);
        return temp;
    }

    /*
     Return size of the heap, so heapSize
     @author Anthony Shih
     @version 1.0
     @since 02-19-2016
     */
    public int size() {
        return heapSize;
    }

    /*
     Move up the heap, comparing the current value to the parent node and
     swapping if our current value is smaller
     @author Anthony Shih
     @version 1.0
     @since 02-19-2016
     */
    private void heapifyUp(int childInd) {
        T tmp = heap[childInd];
        while (childInd > 0 && tmp.compareTo(heap[parent(childInd)]) < 0) {
            heap[childInd] = heap[parent(childInd)];
            childInd = parent(childInd);
        }
        heap[childInd] = tmp;
    }

    /*
     Move down the heap, comparing the cuurent value to the children nodes
     Swapping if our current value is greater
     @author Anthony Shih
     @version 1.0
     @since 02-19-2016
     */
    private void heapifyDown(int ind) {
        int child;
        T tmp = heap[ind];
        while (kthChild(ind, 1) < heapSize) {
            child = minChild(ind);
            if (heap[child].compareTo(tmp) < 0) {
                heap[ind] = heap[child];
            } else {
                break;
            }
            ind = child;
        }
        heap[ind] = tmp;
    }

    /*
     Return true if heapSize is 0
     Checks if heap is empty
     @author Anthony Shih
     @version 1.0
     @since 02-19-2016
     */
    public boolean isEmpty() {
        return heapSize == 0;
    }

    /*
     Return true if heapSize is equal to length of array
     Checks if heap is full
     @author Anthony Shih
     @version 1.0
     @since 02-19-2016
     */
    public boolean isFull() {
        return heapSize == heap.length;
    }

    /*
     Clears the heap by setting heapSize to 0
     And resetting the array
     @author Anthony Shih
     @version 1.0
     @since 02-19-2016
     */
    public void clear() {
        heap = (T[]) new Comparable[heap.length];
        heapSize = 0;
    }

    /*
     Returns the index for the parent of the node at the given index
     @author Anthony Shih
     @version 1.0
     @since 02-19-2016
     */
    private int parent(int i) {
        return (i - 1) / d;
    }

    /*
     Returns the index for the kth child of the node at the given index
     @author Anthony Shih
     @version 1.0
     @since 02-19-2016
     */
    private int kthChild(int i, int k) {
        return d * i + k;
    }

    /*
     Returns the index for the smallest child of the node at the given index
     @author Anthony Shih
     @version 1.0
     @since 02-19-2016
     */
    private int minChild(int ind) {
        int bestChild = kthChild(ind, 1);
        int k = 2;
        int pos = kthChild(ind, k);
        while ((k <= d) && (pos < heapSize)) {
            if (heap[pos].compareTo(heap[bestChild]) < 0) {
                bestChild = pos;
            }
            pos = kthChild(ind, k++);
        }
        return bestChild;
    }

    /*
     Printer Method to visualize the heap if necessary
     @author Anthony Shih
     @version 1.0
     @since 02-19-2016
     */
    public void printHeap() {
        System.out.print("\nHeap = ");
        for (int i = 0; i < heapSize; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }
}
