import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyStackTest {

    MyStack<Integer> list;
    @BeforeEach
    void setUp(){
        list = new MyStack<>();
    }
    @Test
    void size() {
        assertEquals(list.size(), 0);
        list.push(1);
        list.push(2);
        assertEquals(list.size(), 2);
        list.push(3);
        assertEquals(list.size(), 3);
    }

    @Test
    void isEmpty() {
        assertTrue(list.isEmpty());
        list.push(1);
        assertFalse(list.isEmpty());
        list.pop();
        assertTrue(list.isEmpty());
    }

    @Test
    void push() {
        list.push(1);
        assertEquals(list.peek(), 1);
        list.push(2);
        list.push(3);
        assertEquals(list.peek(), 3);
        list.push(4);
        assertEquals(list.peek(), 4);
        boolean throwsException = false;
        try{
            list.push(null);
        }
        catch (IllegalArgumentException e){
            throwsException = true;
        }
        assertTrue(throwsException);
    }

    @Test
    void pop() {
        list.push(1);
        list.push(2);
        list.push(3);
        list.push(4);
        list.pop();
        assertEquals(list.peek(), 3);
        list.pop();
        assertEquals(list.pop(), 2);
        list.pop();
        assertNull(list.pop());
    }

    @Test
    void peek() {
        list.push(1);
        list.push(2);
        list.push(3);
        assertEquals(list.peek(), 3);
        list.pop();
        list.pop();
        assertEquals(list.peek(), 1);
        list.pop();
        assertNull(list.peek());
    }
}