/*
 * Name: govinda charan sahoo
 * Email: gsahoo@ucsd.edu
 * PID: A16378764
 * Sources used: tutors
 * This file tests the implementation of Iterator class in
 * PA4 for the linkedlist
 * 
 */


import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;
import java.util.AbstractList;
import java.util.NoSuchElementException;
import java.util.ListIterator;
import java.util.Iterator;

public class MyListIteratorCustomTester {

    /**
     * This sets up the test fixture. JUnit invokes this method before
     * every testXXX method. The @Before tag tells JUnit to run this method
     * before each test.
     */

     private MyLinkedList  listLen2;
     private MyLinkedList.MyListIterator listLen2Iter;




    @Before
    public void setUp() throws Exception {
    

        listLen2 = new MyLinkedList();
        listLen2.add("a");
        listLen2.add("b");
        listLen2.add("c");
        listLen2.add("d");
        listLen2.add("e");
        listLen2Iter = listLen2.new MyListIterator();

    }

    /**
     * Aims to test the next() method when iterator is at end of the list 
     */
    @Test(expected= NoSuchElementException.class) 
    public void testNextEnd() {
        listLen2Iter.idx = 4;
        listLen2Iter.left = listLen2.tail.getPrev();
        listLen2Iter.right=listLen2.tail;

        listLen2Iter.next();

    }

    /**
     * Aims to test the previous() method when iterator is at the start of the 
     * list 
     */
    @Test(expected= NoSuchElementException.class)
    public void testPreviousStart() {
        listLen2Iter.idx = 0;
        listLen2Iter.right = listLen2.head.getNext();
        listLen2Iter.right=listLen2.head;
        listLen2Iter.previous();
    }

    /**
     * Aims to test the add(E e) method when an invalid element is added
     */
    @Test(expected = NullPointerException.class)
    public void testAddInvalid() {
        listLen2Iter.add(null);

    }

    /**
     * Aims to test the set(E e) method when canRemoveOrSet is false
     */
    @Test(expected = IllegalStateException.class)
    public void testCantSet() {
        listLen2Iter.idx = 1;
        listLen2Iter.right = listLen2.head.getNext().getNext();
        listLen2Iter.right=listLen2.head.getNext();
        listLen2Iter.canRemoveOrSet=false;
        listLen2Iter.set("cat");

    }


    /**
     * Aims to test the set(E e) method when an invalid element is set
     */
    @Test(expected = NullPointerException.class)
    public void testSetInvalid() {
        listLen2Iter.idx = 1;
        listLen2Iter.right = listLen2.head.getNext().getNext();
        listLen2Iter.right=listLen2.head.getNext();
        listLen2Iter.canRemoveOrSet=true;
        listLen2Iter.set(null);

    }

    /**
     * Aims to test the remove() method when canRemoveOrSet is false
     */
    @Test(expected = IllegalStateException.class)
    public void testCantRemove() {
        listLen2Iter.idx = 1;
        listLen2Iter.right = listLen2.head.getNext().getNext();
        listLen2Iter.right=listLen2.head.getNext();
        listLen2Iter.canRemoveOrSet=false;
        listLen2Iter.remove();
    }

    /**
     * Aims to tests the hasNext() method at the end of a list
     */
    @Test
    public void testHasNextEnd() {
        listLen2Iter.idx = 5;
        listLen2Iter.left = listLen2.tail.getPrev();
        listLen2Iter.right=listLen2.tail;
        listLen2Iter.forward = true;
        listLen2Iter.canRemoveOrSet = true;
        

        assertFalse(listLen2Iter.hasNext());
    }

    /**
     * Aims to test the hasPrevious() method at the start of a list
     */
    @Test
    public void testHasPreviousStart() {

        listLen2Iter.idx = 0;
        listLen2Iter.right = listLen2.head.getNext();
        listLen2Iter.right=listLen2.head;

       boolean temp=listLen2Iter.hasPrevious();

       assertEquals(false, temp);

    }

    /**
     * Aims to test the previousIndex() method at the start of a list
     */
    @Test
    public void testPreviousIndexStart() {
        listLen2Iter.idx = 0;
        listLen2Iter.right = listLen2.head.getNext();
        listLen2Iter.right=listLen2.head;
        int temp=listLen2Iter.previousIndex();
        assertEquals(-1, temp);
    }

    /**
     * Aims to test the nextIndex() method at the end of a list
     */
    @Test
    public void testNextIndexEnd() {
        listLen2Iter.idx = 5;
        listLen2Iter.left = listLen2.tail.getPrev();
        listLen2Iter.right=listLen2.tail;
        listLen2Iter.forward=true;
        listLen2Iter.canRemoveOrSet=true;
        int temp=listLen2Iter.nextIndex();
        
        assertEquals(5, temp);
        

    }
}
