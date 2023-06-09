/*
 * NAME: Aneesh Pamula
 * PID: A17319059
 */

import java.util.AbstractList;

/**
 * DoublyLinkedList class
 * @author Aneesh Pamula
 * @since 5/8/2023
 */
public class DoublyLinkedList<T> extends AbstractList<T> {

    /* DLL instance variables */
    private int nelems;
    private Node head;
    private Node tail;

    /**
     * Node for chaining together to create a linked list
     */
    protected class Node {

        /* Node instance variables */
        T data;
        Node next;
        Node prev;

        /**
         * Constructor to create singleton Node
         */
        private Node(T element) {
            data = element;
            next = null;
            prev = null;
        }

        /**
         * Constructor to create singleton link it between previous and next
         *
         * @param element  Element to add, can be null
         * @param nextNode successor Node, can be null
         * @param prevNode predecessor Node, can be null
         */
        private Node(T element, Node nextNode, Node prevNode) {
            data = element;
            next = nextNode;
            prev = prevNode;
        }

        /**
         * Set the element
         *
         * @param element new element
         */
        public void setElement(T element) {
            data = element;
        }

        /**
         * Accessor to get the Nodes Element
         */
        public T getElement() {
            return data;
        }

        /**
         * Set the next node in the list
         *
         * @param n new next node
         */
        public void setNext(Node n) {
            next = n;
        }

        /**
         * Get the next node in the list
         *
         * @return the successor node
         */
        public Node getNext() {
            return next;
        }

        /**
         * Set the previous node in the list
         *
         * @param p new previous node
         */
        public void setPrev(Node p) {
            prev = p;
        }


        /**
         * Accessor to get the prev Node in the list
         *
         * @return predecessor node
         */
        public Node getPrev() {
            return prev;
        }

        /**
         * Remove this node from the list.
         * Update previous and next nodes
         */
        public void remove() {
            next.prev = prev;
            prev.next = next;
        }
    }

    /**
     * Creates a new, empty doubly-linked list.
     */
    public DoublyLinkedList() {
        head = new Node(null);
        tail = new Node(null, null, head);
        head.next = tail;
        nelems = 0;
    }

    /**
     * Add an element to the end of the list
     *
     * @param element data to be added
     * @return whether or not the element was added
     * @throws NullPointerException if data received is null
     */
    @Override
    public boolean add(T element) throws NullPointerException {
        if(element == null)
            throw new NullPointerException();
        tail.prev = new Node(element, tail, tail.prev);
        tail.prev.prev.next = tail.prev;
        nelems++;
        return true;
    }


    /**
     * Adds an element to a certain index in the list, shifting exist elements
     * create room. Does not accept null values.
     *
     * @param index index to add element to
     * @param element data of the node to be added
     * @throws IndexOutOfBoundsException if index is not in range [0, size]
     * @throws NullPointerException if element is null
     */
    @Override
    public void add(int index, T element)
            throws IndexOutOfBoundsException, NullPointerException {
        if((0 > index) || (index > nelems))
            throw new IndexOutOfBoundsException();
        if(element == null)
            throw new NullPointerException();
        Node n = getNth(index-1);
        n.next.prev = new Node(element, n.next, n);
        n.next = n.next.prev;
        nelems++;
    }

    /**
     * Clear the linked list
     */
    @Override
    public void clear() {
        tail.prev = head;
        head.next = tail;
        nelems = 0;
    }

    /**
     * Determine if the list contains the data element anywhere in the list.
     *
     * @param element data to check for
     * @return True if element is in list, false otherwise
     */
    @Override
    public boolean contains(Object element) {
        T data = (T)element;
        Node n = head.next;
        while(n != tail){
            if(n.getElement().equals(data))
                return true;
            n = n.next;
        }
        return false;
    }

    /**
     * Retrieves the element stored with a given index on the list.
     *
     * @param index index to retrieve element from
     * @return the element at the given index
     * @throws IndexOutOfBoundsException if index is not in range [0, nelems)
     */
    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        if((index >= nelems) || (index < 0))
            throw new IndexOutOfBoundsException();
        return getNth(index).getElement();
    }

    /**
     * Helper method to get the Nth node in our list
     *
     * @param index index of node to get
     * @return Node at the given index
     */
    private Node getNth(int index) {
        Node n = head;
        for(int i = 0; i <= index; i++)
            n = n.next;
        return n;
    }

    /**
     * Determine if the list empty
     *
     * @return True if list is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return head.next == tail;
    }

    /**
     * Remove the element from position index in the list
     *
     * @param index index of node to remove
     * @return value of data in node removed
     * @throws IndexOutOfBoundsException if index is not in range [0, nelems)
     */
    @Override
    public T remove(int index) throws IndexOutOfBoundsException {
        if((0 > index) || (index >= nelems))
            throw new IndexOutOfBoundsException();
        Node n = getNth(index);
        T element = n.getElement();
        n.next.prev = n.prev;
        n.prev.next = n.next;
        nelems--;
        return element;
    }

    /**
     * Set the value of an element at a certain index in the list.
     *
     * @param index index of node whose value will be changed
     * @param element value to change to
     * @return previous value of node
     * @throws IndexOutOfBoundsException if index is not in range [0, nelems)
     * @throws NullPointerException if element is null
     */
    @Override
    public T set(int index, T element)
            throws IndexOutOfBoundsException, NullPointerException {
        // TODO: Fill in implmentation
        if((0 > index) || (index >= nelems))
            throw new IndexOutOfBoundsException();
        if(element == null)
            throw new NullPointerException();
        Node n = getNth(index);
        T prevElem = n.getElement();
        n.setElement(element);
        return prevElem;
    }

    /**
     * Retrieves the amount of elements that are currently on the list.
     *
     * @return number of elements in list
     */
    @Override
    public int size() {
        return nelems;
    }

    /**
     * String representation of this list in the form of:
     * "[(head) -> elem1 -> elem2 -> ... -> elemN -> (tail)]"

     * @returns a string of the form [(head) -> elem1 -> elem2 -> ... -> elemN -> (tail)]
     */
    @Override
    public String toString() {
        String outputString = "[(head) -> ";
        for(int i = 0; i < nelems; i++){
            outputString += getNth(i).getElement() + " -> ";
        }
        outputString += "(tail)]";
        return outputString;
    }

    /* ==================== EXTRA CREDIT ==================== */

    /**
     * Remove nodes whose index is a multiple of base
     *
     * @param base remove nodes at indices who are a multiple of this value
     */
    public void removeMultipleOf(int base) throws IllegalArgumentException{
        if(base < 1)
            throw new IllegalArgumentException();
        Node n = head.next;
        int effectiveIndex = 0;//This value represents the
        // original index of the values, since i is not reliable
        for(int i = 0; i < nelems; i++) {
            n = n.next;
            if (effectiveIndex % base == 0) {
                this.remove(i);
                i--;
            }
            effectiveIndex++;
        }
    }

    /**
     * Swap the nodes between index [0, splitIndex] of two lists
     *
     * TODO: javadoc comments
     */
    public void swapSegment(DoublyLinkedList other, int splitIndex) {

    }

}