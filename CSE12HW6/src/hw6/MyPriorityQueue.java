/*
 Name: Anthony Shih
 PID: A11295870
 Login: cs12wei
 */
package hw6;

/*
 Priority Queue implementation
 @author Anthony Shih
 @version 1.0
 @since 02-19-2016
 */
public class MyPriorityQueue<T extends Comparable<T>> {

    private final dHeap<T> thisHeap;
    private int heapSize, tcapacity;

    /*
     Constructor for Priority Queue, takes in int for capacity of the queue
     @author Anthony Shih
     @version 1.0
     @since 02-19-2016
     */
    public MyPriorityQueue(int capacity) {
        thisHeap = new dHeap(capacity);
        tcapacity = capacity;
    }

    /*
     Add method for Priority Queue, uses add method from dHeap
     @author Anthony Shih
     @version 1.0
     @since 02-19-2016
     */
    public void add(T object) {
        thisHeap.add(object);
        heapSize++;
    }

    /*
     Poll method for Priority Queue, essentially the same as pop from stack
     @author Anthony Shih
     @version 1.0
     @since 02-19-2016
     */
    public T poll() {
        int parent, child;
        T item;
        if (thisHeap.isEmpty()) {
            return null;
        }
        item = thisHeap.removeSmallest();
        heapSize--;
        return item;

    }

}
