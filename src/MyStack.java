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

public class MyStack<T> implements MyStackInterface{

    private DoublyLinkedList<T> list;

    public MyStack(){
        list = new DoublyLinkedList<>();
    }
    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void push (Object data) throws IllegalArgumentException{
        if(data == null)
            throw new IllegalArgumentException();
        list.add(0, (T) data);
    }

    @Override
    public T pop() {
        if(this.isEmpty())
            return null;
        return list.remove(0);
    }

    @Override
    public T peek() {
        if(this.isEmpty())
            return null;
        return list.get(0);
    }
}
