/*
 Name: Anthony Shih
 PID: A11295870
 Login: cs12wei
 */
package hw4;

public class DoubleEndedLL<T> implements DoubleEndedLLInterface<T> {

    private Node firstNode; //Head Pointer
    private Node lastNode;  //Tail Pointer
    private int listSize;   //Size of List

    /*
     Constructor for DoubleEndedSinglyLL
     @author Anthony Shih
     @version 1.0
     @since 02-03-2016
     */
    public DoubleEndedLL() {
        firstNode = null;
        lastNode = null;
        listSize = 0;
    }

    /*
     Check if the list is empty
     Check listSize for 0
     @author Anthony Shih
     @version 1.0
     @since 02-03-2016
     */
    public boolean isEmpty() {
        if (listSize == 0) {
            return true;
        } else {
            return false;
        }
    }

    /*
     Returns size of list
     @author Anthony Shih
     @version 1.0
     @since 02-03-2016
     */
    public int size() {
        return listSize;
    }

    /*
     Adding to the front of the list
     Assign the current head as the next Node for the newItem
     Then assign the newItem as the new head
     @author Anthony Shih
     @version 1.0
     @since 02-03-2016
     */
    public void addFirst(T newItem) {
        Node comingIn = new Node(newItem);
        if (isEmpty()) {
            firstNode = comingIn;
            lastNode = comingIn;
        } else {
            comingIn.after = firstNode;
            firstNode = comingIn;
        }
        listSize++;
    }

    /*
     Adding to the end of the list
     Assign the current tail's next value to the newItem
     Then assign the newItem as the new tail
     @author Anthony Shih
     @version 1.0
     @since 02-03-2016
     */
    public void addLast(T newItem) throws IndexOutOfBoundsException {
        Node comingIn = new Node(newItem);
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Got no nodes to addLast");
        } else {
            lastNode.after = comingIn;
            lastNode = comingIn;
        }
        listSize++;
    }

    /*
     Removing the first value of the list
     Assign the next node as the new head
     @author Anthony Shih
     @version 1.0
     @since 02-03-2016
     */
    public T removeFirst() {
        T escapePod = firstNode.info;
        if (size() == 1) { //Getting rid of last Node
            firstNode = null;
            lastNode = null;
        } else {
            firstNode = firstNode.after;
        }
        listSize--;
        return escapePod;
    }

    /*
     Removing the last element
     Dereference by iterating and finding last Node
     @author Anthony Shih
     @version 1.0
     @since 02-03-2016
     */
    public T removeLast() {
        T escapePod = lastNode.info;
        if (size() == 1) { //Getting rid of last Node
            firstNode = null;
            lastNode = null;
        } else {
            Node fastRunner = firstNode;
            while (fastRunner.after != lastNode) {
                fastRunner = fastRunner.after;
            }
            lastNode = fastRunner;
            fastRunner.after = null;
        }
        listSize--;
        return escapePod;
    }

    /*
     Helper class Node for implementation
     */
    protected class Node {

        T info;
        Node after;

        public Node(T data) {
            info = data;
        }
    }
}
