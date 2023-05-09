import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyArrayListTest {

    MyArrayList<Integer> list;
    @BeforeEach
    void setUp() {
        list = new MyArrayList<>();
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
    void contains() {
        list.add(1);
        list.add(2);
        list.add(3);
        assertTrue(list.contains(1));
        assertFalse(list.contains(null));
        assertTrue(list.contains(3));
    }

    @Test
    void testToString() {
        list.add(1);
        list.add(1);
        assertEquals(list.toString(), "[1 -> 1]");
        list.add(2);
        assertEquals(list.toString(), "[1 -> 1 -> 2]");
        list.add(3);
        assertEquals(list.toString(), "[1 -> 1 -> 2 -> 3]");
    }

}