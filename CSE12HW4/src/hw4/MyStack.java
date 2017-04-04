/*
 Name: Anthony Shih
 PID: A11295870
 Login: cs12wei
 */
package hw4;

public class MyStack<T> implements Stack_QueueInterface<T> {

    DoubleEndedLL<T> MC;

    /*
     Constructor for MyStack
     @author Anthony Shih
     @version 1.0
     @since 02-03-2016
     */
    public MyStack() {
        MC = new DoubleEndedLL<>();
    }

    /*
     Uses isEmpty() method in DELL to check isEmpty()
     @author Anthony Shih
     @version 1.0
     @since 02-03-2016
     */
    public boolean isEmpty() {
        return MC.isEmpty();
    }

    /*
     Uses addFirst method in DELL to push
     @author Anthony Shih
     @version 1.0
     @since 02-03-2016
     */
    public void addElement(T newItem) {
        MC.addFirst(newItem);
    }

    /*
     Uses removeFirst method in DELL to pop
     @author Anthony Shih
     @version 1.0
     @since 02-03-2016
     */
    public T removeElement() {
        return MC.removeFirst();
    }

    /*
     Peek method used just for implementation sake
     @author Anthony Shih
     @version 1.0
     @since 02-03-2016
     */
    public T peekElement() {
        T whackAmole = MC.removeFirst();
        MC.addFirst(whackAmole);
        return whackAmole;
    }

    /*
     Uses the size() method in DELL to return the size of the stack
     @author Anthony Shih
     @version 1.0
     @since 02-03-2016
     */
    public int size() {
        return MC.size();
    }

}
