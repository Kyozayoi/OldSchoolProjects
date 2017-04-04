/*
Name: Anthony Shih
PID: A11295870
Login: cs12wei
*/

package hw4;

public class MyQueue<T> implements Stack_QueueInterface<T> {

    private final int RANDOM_CAPACITY = 100;
    private int qFront, qBack, qCount;
    private T[] MC;

    /*
     Constructor for MyQueue
     @author Anthony Shih
     @version 1.0
     @since 02-03-2016
     */
    public MyQueue() {
        qFront = 0;
        qBack = 0;
        qCount = 0;
        MC = (T[]) (new Object[RANDOM_CAPACITY]);
    }

    /*
    Return true if qCount is 0, false otherwise
    @author Anthony Shih
    @version 1.0
    @since 02-03-2016
    */
    public boolean isEmpty() {
        return (qCount == 0);
    }

    /*
    Adding through the back, expanding if necessary
    @author Anthony Shih
    @version 1.0
    @since 02-03-2016
    */
    public void addElement(T newItem) {
        if (size() == MC.length) {
            extendQueue();
        }
        MC[qBack] = newItem;
        qBack = (qBack + 1) % MC.length;
        qCount++;
    }
    
    /*
    Removing from the front. Exceptions thrown if empty
    @author Anthony Shih
    @version 1.0
    @since 02-03-2016
    */
    public T removeElement() throws IndexOutOfBoundsException {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Removing from an empty queue");
        }
        T response = MC[qFront];
        MC[qFront] = null;
        qFront = (qFront + 1) % MC.length;
        qCount--;
        return response;
    }

    /*
    Returning the size of the queue
    @author Anthony Shih
    @version 1.0
    @since 02-03-2016
    */
    public int size() {
        return qCount;
    }

    /*
     Helper method to extend the size of the array/queue
    @author Anthony Shih
    @version 1.0
    @since 02-03-2016
     */
    public void extendQueue() {
        T[] larger = (T[]) (new Object[MC.length * 2]);
        for (int i = 0; i < qCount; i++) {
            larger[i] = MC[qFront];
            qFront = (qFront + 1) % MC.length;
        }
        qFront = 0;
        qBack = qCount;
        MC = larger;
    }
    
    /*
    Helper method to find length of the array we are using
    @author Anthony Shih
    @version 1.0
    @since 02-03-2016
    */
    public int getLength(){
        return MC.length;
    }
}
