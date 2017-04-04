/*
 Anthony Shih
 A11295870
 cs12wei
 */
package hw2;

import java.util.*;

public class DoublyLinkedList12<E> extends AbstractList<E> {

    private int listSize;
    private Node head;
    private Node tail;

    protected class Node {

        E data;
        Node next;
        Node prev;

        /**
         * Constructor to create empty Node
         */
        public Node() {
                //Used to create a Node with a null data field
        }

        /**
         * Constructor to create singleton Node
         */
        public Node(E element) {
            this.data = element; //Create a Node with predetermined data
        }

        /**
         * Constructor to create singleton link it between previous and next
         *
         * @param element Element to add, can be null
         * @param prevNode predecessor Node, can be null
         * @param nextNode successor Node, can be null
         */
        public Node(E element, Node prevNode, Node nextNode) {
            data = element;
            next = nextNode;
            prev = prevNode;
        } //Create a Node with all its values given.

        /**
         * Remove this node from the list. Update previous and next nodes
         */
        public void remove() {
            prev.next = next;
            next.prev = prev;
        } //Sever all connections to the Node.

        /**
         * Set the previous node in the list
         *
         * @param p new previous node
         */
        public void setPrev(Node p) {
            prev = p;
        } //Setting the previous Node.

        /**
         * Set the next node in the list
         *
         * @param n new next node
         */
        public void setNext(Node n) {
            next = n;
        } //Setting the next Node.

        /**
         * Set the element
         *
         * @param e new element
         */
        public void setElement(E e) {
            data = e;
        } //Setting the element.

        /**
         * Accessor to get the next Node in the list
         */
        public Node getNext() {
            return next;
        } //Returning the next Node.

        /**
         * Accessor to get the prev Node in the list
         */
        public Node getPrev() {
            return prev;
        } //Returning the previous Node.

        /**
         * Accessor to get the Nodes Element
         */
        public E getElement() {
            return data;
        } //Returning the Node's data.
    }

    /**
     * ListIterator implementation
     */
    protected class MyListIterator implements ListIterator<E> {

        private boolean canRemove;
        private Node left, right;// Cursor sits between these two nodes
        private Node hereWeAre;
        private int curIndex;   // Tracks current position. what next() would 
        // return

        public MyListIterator() { 
            curIndex = 0;
            left = head;
            right = null;
            hereWeAre = head;
            canRemove = false;
        } //Constructor for the iterator

        @Override
        public void add(E e) throws NullPointerException {
            if (e == null) {
                throw new NoSuchElementException();
            }
            Node newbie = new Node(e, left, right);
            left.next = newbie;
            right.prev = newbie;
            left = newbie;
            canRemove = true;
            listSize++;
        } //Adding in between Left and Right

        @Override
        public boolean hasNext() {
            if (right == null) {
                return false;
            } else {
                return true;
            }
        } //Checking for next Node

        @Override
        public boolean hasPrevious() {
            if (left == null) {
                return false;
            } else {
                return true;
            }
        } //Checking for previous Node

        @Override
        public E next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node response = right;
            right = right.next;
            left = left.next;
            curIndex++;
            return response.data;
        } //Moving to the next Node.
        
        @Override
        public int nextIndex() {
            return curIndex + 1;
        } //Returning the index of the nextNode.

        @Override
        public E previous() throws NoSuchElementException {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            Node esnopser = left;
            left = left.prev;
            right = right.prev;
            curIndex--;
            return esnopser.data;
        } //Moving to the previous Node.

        @Override
        public int previousIndex() {
            return curIndex;
        } //Returning the index of the prevNode.

        @Override
        public void remove() throws IllegalStateException {
            if(!canRemove){
                throw new IllegalStateException();
            }
            Node temp = left.prev;
            temp.next = left.next;
            left.next.prev = temp;
            left = temp;
            curIndex--;
        } //Severing the connections of the current Node.

        @Override
        public void set(E e)
                throws NullPointerException, IllegalStateException {
            if (e == null) {
                throw new NullPointerException();
            }
            left.data = e;
        } //Setting the data of the current Node.
    } 

    //  Implementation of the DoublyLinkedList12 Class
    /**
     * Only 0-argument constructor is define
     */
    public DoublyLinkedList12() {
        listSize = 0;
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    @Override
    public int size() {
        return listSize;
    } //Return the size of the DLL.

    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        if (listSize == 0 || index > listSize) {
            throw new IndexOutOfBoundsException();
        }
        Node runner = head;
        Node firstPlace = new Node();
        for (int i = 0; i < listSize; i++) {
            if (i == index) {
                firstPlace = runner;
            }
            runner = runner.next;
        }
        return firstPlace.data;
    } //Return the data of the Node at the given index.

    @Override
    /**
     * Add an element to the list
     *
     * @param index where in the list to add
     * @param data data to add
     * @throws IndexOutOfBoundsException
     * @throws NullPointerException
     */
    public void add(int index, E data)
            throws IndexOutOfBoundsException, NullPointerException {
        if (data == null) {
            throw new NullPointerException();
        }
        if (index > listSize) {
            throw new IndexOutOfBoundsException();
        }
        Node newcomer = new Node(data);
        Node runner = head;
        if (index == 0) {
            runner.prev = newcomer;
            newcomer.next = runner;
            head = newcomer;
        }
        for (int i = 1; i < listSize; i++) {
            if (i == index) {
                Node backup = runner.next;
                runner.next = newcomer;
                newcomer.prev = runner;
                newcomer.next = backup;
                backup.prev = newcomer;
            }
            runner = runner.next;
        }
        listSize++;
    } //Traverse the List, and add a Node at the given index.

    /**
     * Add an element to the end of the list
     *
     * @param data data to add
     * @throws NullPointerException
     */
    public boolean add(E data) throws NullPointerException {
        if (data == null) {
            throw new NoSuchElementException();
        }
        Node finisher = tail;
        Node finale = new Node(data, finisher, null);
        finisher.next = finale;
        tail = finale;
        listSize++;
        return true;
    } //Add Node at the end.

    /**
     * Set the element at an index in the list
     *
     * @param index where in the list to add
     * @param data data to add
     * @return element that was previously at this index.
     * @throws IndexOutOfBoundsException
     * @throws NullPointerException
     */
    public E set(int index, E data)
            throws IndexOutOfBoundsException, NullPointerException {
        if (data == null) {
            throw new NoSuchElementException();
        }
        if (listSize == 0 || index > listSize) {
            throw new IndexOutOfBoundsException();
        }
        Node runner = head;
        for (int i = 0; i < listSize; i++) {
            if (i == index) {
                runner.data = data;
            }
            runner = runner.next;
        }
        return data;
    } //Traverse the List, and set data of the Node at index given.

    /**
     * Remove the element at an index in the list
     *
     * @param index where in the list to add
     * @return element the data found
     * @throws IndexOutOfBoundsException
     */
    public E remove(int index) throws IndexOutOfBoundsException {
        if (listSize == 0 || index > listSize) {
            throw new IndexOutOfBoundsException();
        }
        Node runner = head;
        Node quitter = new Node();
        for (int i = 0; i < listSize; i++) {
            if (i == index) {
                quitter = runner;
                runner.prev.next = runner.next;
                runner.next.prev = runner.prev;
            }
            runner = runner.next;
        }
        listSize--;
        return quitter.data;
    } //Traverse the List, and remove the data at the given index.

    /**
     * Clear the linked list
     */
    public void clear() {
        Node backingUp = tail;
        while (!isEmpty()) {
            if (backingUp == head) {
                break;
            }
            Node backup = backingUp.prev;
            backingUp.prev.next = null;
            backingUp.prev = null;
            backingUp = backup;
            listSize--;
        }
    } //Traverse through the list, severing all connections.

    /**
     * Determine if the list empty
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return listSize == 0;
    } 

    public Iterator<E> QQQiterator() {
        return new MyListIterator();
    }

    public ListIterator<E> QQQlistIterator() {
        return new MyListIterator();
    }

    // Helper method to get the Node at the Nth index
    private Node getNth(int index) {
        Node runner = head;
        Node chosen = new Node();
        for (int i = 0; i < listSize; i++) {
            if (i == index) {
                chosen = runner;
            }
            runner = runner.next;
        };
        return chosen;
    }

    public Iterator<E> iterator() {
        return new MyListIterator();
    }

    public ListIterator<E> listIterator() {
        return new MyListIterator();
    }

}

// VIM: set the tabstop and shiftwidth to 4 
// vim:tw=78:ts=4:et:sw=4

