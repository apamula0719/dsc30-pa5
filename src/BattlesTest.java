import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BattlesTest {
    DoublyLinkedList<Integer> dList;
    MyArrayList<Integer> aList;

    private final int NUM_ELEMENTS = 400000;
    @BeforeEach
    void setUp(){
        dList = new DoublyLinkedList<>();
        aList = new MyArrayList<>();
        for(int i = 0; i < NUM_ELEMENTS; i++){//Fill the arrays
            dList.add(i);
            aList.add(i);
        }
    }
    @Test
    void addDouble() {
        double start = System.nanoTime()/(1000000.0);
        dList.add(0, 1);
        double end = (double) System.nanoTime() /(1000000);
        System.out.println("Runtime of adding to DLL: " + (end - start));
    }

    @Test
    void addArray() {
        double start = (double) System.nanoTime() /(1000000);
        aList.add(0, 1);
        double end = (double) System.nanoTime() /(1000000);
        System.out.println("Runtime of adding to MAL: " + (end - start));
    }

    @Test
    void containsDouble() {
        double start = (double) System.nanoTime() /(1000000);
        System.out.println(dList.contains(null));
        double end = (double) System.nanoTime() /(1000000);
        System.out.println("Runtime of contains on DLL: " + (end - start));
    }

    @Test
    void containsArray() {
        double start = (double) System.nanoTime() /(1000000);
        System.out.println(aList.contains(null));
        double end = (double) System.nanoTime() /(1000000);
        System.out.println("Runtime of contains on MAL: " + (end - start));
    }

    @Test
    void getDouble() {
        double start = (double) System.nanoTime() /(1000000);
        System.out.println(dList.get(NUM_ELEMENTS/2));
        double end = (double) System.nanoTime() /(1000000);
        System.out.println("Runtime of get on DLL: " + (end - start));
    }

    @Test
    void getArray() {
        double start = (double) System.nanoTime() /(1000000);
        System.out.println(aList.get(NUM_ELEMENTS/2));
        double end = (double) System.nanoTime() /(1000000);
        System.out.println("Runtime of get on MAL: " + (end - start));
    }
}