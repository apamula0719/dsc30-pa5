/*
 * NAME: Aneesh pamula
 * PID: A17319059
 */

import java.util.AbstractList;


/**
 * ArrayList Implementation
 *
 * @author Aneesh Pamula
 * @since 5/8/2023
 */
public class MyArrayList<T> extends AbstractList<T> {

    private int nelems;
    private T  [ ] arrList;
  
    /**
     * ArrayList constructor
     */
    public MyArrayList() {
      
        arrList = (T[]) new Object[0];
  
    }

    public MyArrayList(int capacity) {
       
        arrList = (T[]) new Object[capacity];    
    }

    /**
     * Retrieves the amount of elements that are currently in the ArrayList.
     *
     * @return Number of elements currently in the ArrayList
     */
    @Override
    public int size() {
        return nelems;
    }

    /**
     * Adds an element to a certain index in the list, shifting existing elements
     * to create space. Does not accept null values.
     *
     * @param index Where in the list to add the element.
     * @param data  Data to be added.
     * @throws IndexOutOfBoundsException if index received is out of bounds for
     *                                   the current list.
     * @throws NullPointerException      if data received is null.
     */
    @Override
    public void add(int index, T data)
            throws IndexOutOfBoundsException, NullPointerException {
        if(index < 0 || index >= arrList.length + 1)
            throw new IndexOutOfBoundsException();
        if(data == null)
            throw new NullPointerException();
        if(this.nelems < this.arrList.length){//If there is enough space
            for(int i = nelems-1; i >= index; i--)//Shift elements from index to end up
                arrList[i+1] = arrList[i];
            arrList[index] = data;
        }
        else{//If we need to expand the array
            T[] newArray = (T[]) new Object[nelems+1];
            for(int i = 0; i < index; i++)
                newArray[i] = arrList[i];
            for(int i = index+1; i < newArray.length; i++)
                newArray[i] = arrList[i-1];
            newArray[index] = data;
            arrList = newArray;
        }
        nelems++;
    }

    /**
     * Retrieves the element stored with a given index on the list.
     *
     * @param index The index of the desired element.
     * @return Returns the data contained at the specified index.
     * @throws IndexOutOfBoundsException if index received is out of bounds for
     *                                   the current list.
     */
    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        if(index < 0 || index >= arrList.length)
            throw new IndexOutOfBoundsException();
        return arrList[index];
    }
                   

    /**
     * Determine if this list contains the given data
     * @param data data to find
     * @return true if list contains given data, false otherwise
     */
    public boolean contains(Object data) {
        for(int i = 0; i < nelems; i++)
            if(arrList[i].equals((T) data))
                return true;
        return false;
    }


    /**
     * String representation of this list in the form of:
     * "[2 -> 45 -> 15 -> 9 -> 1]"
     * @return string representation
     */
    @Override
    public String toString() {
        String output = "[";
        for(int i = 0; i < nelems-1; i++){
            output += arrList[i] + " -> ";
        }
        if(!this.isEmpty())
            output += arrList[nelems-1];
        output += "]";
        return output;
    }


}



    