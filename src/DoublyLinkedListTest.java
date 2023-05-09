import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.AbstractList;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class DoublyLinkedListTest {//Constructor testing is in removeMultipleOf method

    DoublyLinkedList<Object> list;

    @BeforeEach
    void setUp(){
        list = new DoublyLinkedList<>();
    }
    @Test
    void add() {
        list.add(1);
        assertEquals(list.get(0), 1);
        list.add(2);
        assertEquals(list.get(1), 2);
        boolean throwsException = false;
        try{
            list.add(null);
        }
        catch(NullPointerException e){
            throwsException = true;
        }
        assertTrue(throwsException);
        assertTrue(list.add(3));

    }

    @Test
    void testAdd() {
        list.add(0, 1);
        assertEquals(list.get(0), 1);
        list.add(0, 2);
        assertEquals(list.get(1), 1);
        //Testing first error case
        boolean throwsException = false;
        try{
            list.add(2, null);
        }
        catch(NullPointerException e){
            throwsException = true;
        }
        assertTrue(throwsException);
        //Testing second error case
        throwsException = false;
        try{
            list.add(5, 3);
        }
        catch(IndexOutOfBoundsException e){
            throwsException = true;
        }
        assertTrue(throwsException);

        list.add(1, 3);
        assertEquals(list.toString(), "[(head) -> 2 -> 3 -> 1 -> (tail)]");
    }

    @Test
    void clear() {
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(list.toString(), "[(head) -> 1 -> 2 -> 3 -> (tail)]");
        list.clear();
        assertEquals(list.toString(), "[(head) -> (tail)]");
    }

    @Test
    void contains() {
        list.add(1);
        list.add(2);
        list.add(3);
        assertTrue(list.contains(1));
        assertFalse(list.contains(null));
        assertTrue(list.contains(3));


    }

    @Test
    void get() {
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(list.get(2), 3);

        boolean throwsException = false;
        try{
            Object x = list.get(3);
        }
        catch(IndexOutOfBoundsException e){
            throwsException = true;
        }
        assertTrue(throwsException);

        assertEquals(list.get(0), 1);

    }

    @Test
    void isEmpty() {
        assertTrue(list.isEmpty());
        list.add(1);
        assertFalse(list.isEmpty());
        list.add(2);
        list.clear();
        assertTrue(list.isEmpty());
    }

    @Test
    void remove() {
        list.add(1);
        list.add(2);
        list.add(3);

        list.remove(2);
        assertEquals(list.toString(), "[(head) -> 1 -> 2 -> (tail)]");

        boolean throwsException = false;
        try{
            list.remove(2);
        }
        catch(IndexOutOfBoundsException e){
            throwsException = true;
        }
        assertTrue(throwsException);

        list.remove(0);
        assertEquals(list.toString(), "[(head) -> 2 -> (tail)]");
        list.remove(0);
        assertTrue(list.isEmpty());
    }

    @Test
    void set() {
        list.add(1);
        list.add(2);
        list.add(3);

        list.set(0, 10);
        assertEquals(list.toString(), "[(head) -> 10 -> 2 -> 3 -> (tail)]");
        //First error case
        boolean throwsException = false;
        try{
            list.set(3, 40);
        }
        catch(IndexOutOfBoundsException e){
            throwsException = true;
        }
        assertTrue(throwsException);

        list.set(2, 30);
        assertEquals(list.toString(), "[(head) -> 10 -> 2 -> 30 -> (tail)]");
        //Second error case
        throwsException = false;
        try{
            list.set(1, null);
        }
        catch(NullPointerException e){
            throwsException = true;
        }
        assertTrue(throwsException);
        list.set(1, 20);
        assertEquals(list.toString(), "[(head) -> 10 -> 20 -> 30 -> (tail)]");




    }

    @Test
    void size() {
        list.add(1);
        assertEquals(list.size(), 1);
        list.add(1);
        assertEquals(list.size(), 2);
        list.add(2);
        assertEquals(list.size(), 3);

    }

    @Test
    void testToString() {
        list.add(1);
        list.add(1);
        assertEquals(list.toString(), "[(head) -> 1 -> 1 -> (tail)]");
        list.add(2);
        assertEquals(list.toString(), "[(head) -> 1 -> 1 -> 2 -> (tail)]");
        list.add(3);
        assertEquals(list.toString(), "[(head) -> 1 -> 1 -> 2 -> 3 -> (tail)]");
    }

    @Test
    void removeMultipleOf(){
        DoublyLinkedList<Integer> list1 = new DoublyLinkedList<Integer>();
        DoublyLinkedList<Character> list2 = new DoublyLinkedList<Character>();
        DoublyLinkedList<String> list3 = new DoublyLinkedList<String>();
        //First test
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);
        list1.removeMultipleOf(3);
        assertEquals(list1.toString(), "[(head) -> 2 -> 3 -> (tail)]");
        //Second test
        list2.add('2');
        list2.add('4');
        list2.add('6');
        list2.add('8');
        list2.add('0');
        list2.removeMultipleOf(2);
        assertEquals(list2.toString(), "[(head) -> 4 -> 8 -> (tail)]");
        //Third test
        list3.add("a");
        list3.add("b");
        list3.add("c");
        list3.add("d");
        list3.removeMultipleOf(1);
        assertEquals(list3.toString(), "[(head) -> (tail)]");
    }
}