
/*
 * Name: govinda sahoo
 * Email: gsahoo@ucsd.edu
 * PID: A16378764
 * Sources used: tutors
 *
 * This file is used to Test the implementations of
 * MyDeque,MyStack and MyQueue
 * Furthermore, the variable names and style
 * of the code were replicated from the public tester
 */




import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This class contains Custom test cases for MyDeque, MyStack, and MyQueue
 */

public class CustomTester{

    /**
     * Helper method to initialize all instance variables of MyDeque
     * @param deque The deque to initialize
     * @param data The data array
     * @param size The value for size
     * @param front The value for front
     * @param rear The value for rear
     */
    static void initDeque(MyDeque<Integer> deque, Object[] data, int size,
                          int front, int rear) {
        deque.data = data;
        deque.size = size;
        deque.front = front;
        deque.rear = rear;
    }



    // ------------ Deque ---------------

    /** Test the Deque constructor, initialize deque with capacity -1*/
    @Test(expected = IllegalArgumentException.class)
    public void testDequeConstructor1(){
        MyDeque<Integer> deque = new MyDeque<>(-1);
    }

    /** Test the addFirst method with invalid element*/
    @Test(expected = NullPointerException.class)
    public void testDeque_addFirst1(){
        MyDeque<Integer> deque = new MyDeque<>(10);
        deque.addFirst(null);

    }
    /** Test the addLast method with invalid element*/
    @Test(expected = NullPointerException.class)
    public void testDeque_addLast1(){
        MyDeque<Integer> deque = new MyDeque<>(10);
        deque.addLast(null);
    }

    /** Test the addLast method when array is at capacity*/
    @Test
    public void testDeque_addLast2(){
        
        MyDeque<Integer> deque = new MyDeque<>(7);
        Integer[] orig = {5, 6, 1, 2,7,8,9};
        Integer[] finalOrdering = { 2,7,8,9, 5, 6, 1, 11 ,null ,
            null ,null ,null ,null , null};
        initDeque(deque, orig, 7, 3, 2);
        
        deque.addLast(11);

        assertEquals( 14, deque.data.length);
        assertEquals( 8, deque.size);
        assertEquals( 0, deque.front);
        assertEquals( 7, deque.rear);

        for (int i = 0; i < 14; i++) {
            assertEquals("Deque structure should be maintained",
                finalOrdering[i], deque.data[i]);
        }

    }

    /**Test the addLast method to check rear wraps around*/
    @Test
    public void testDeque_addLast3(){
        MyDeque<Integer> deque = new MyDeque<>(7);
        Integer[] orig = {null, 6, 1, 2,7,8,9};
        initDeque(deque, orig, 6, 1, 6);
        Integer[] finalOrdering = {11, 6, 1, 2,7,8,9 };
        deque.addLast(11);

        assertEquals( 7, deque.size);
        assertEquals( 1, deque.front);
        assertEquals( 0, deque.rear);

        for (int i = 0; i < 7; i++) {
            assertEquals("Deque structure should be maintained",
                finalOrdering[i], deque.data[i]);
        }

    }

    /** Test the addFirst method when data is at capacity
    * It tests that front wraps around 
    */
    @Test
    public void testDeque_addFirst2(){
       

        MyDeque<Integer> deque = new MyDeque<>(7);
        Integer[] orig = {5,6,1,2,7,8,9};
        Integer[] finalOrdering = {2,7,8,9,5,6,1,null,null,
            null,null,null,null,11};
        initDeque(deque, orig, 7, 3, 2);

        deque.addFirst(11);
        assertEquals( 14, deque.data.length);
        assertEquals( 8, deque.size);
        assertEquals( 13, deque.front);
        assertEquals( 6, deque.rear);

        for (int i = 0; i < 14; i++) {
            assertEquals(finalOrdering[i], deque.data[i]);
        }

    }

    /** Test the removeFirst method when data is empty*/
    @Test
    public void testDeuque_removeFirst1(){
        MyDeque<Integer> deque = new MyDeque<>(10);
        Integer[] orig={null, null, null, null, 
            null, null, null, null, null, null};
        initDeque(deque, orig, 0, 0, 0);
        assertEquals(null,deque.removeFirst()); 
        assertEquals(0, deque.size);
        assertEquals(0, deque.front);
        assertEquals(0, deque.rear);
        assertEquals(10, deque.data.length);

    }
    /** Test the removeFirst method when data is capacity
    * checks if front wraps around
    */
    @Test
    public void testDeuque_removeFirst2(){
        MyDeque<Integer> deque = new MyDeque<>(7);
        Integer[] orig = {11, 6, 1, 2,7,8,9};
        initDeque(deque, orig, 7, 6, 5);
        Integer[] finalOrdering = {11, 6, 1, 2,7,8,null};

        assertEquals(9,(int) deque.removeFirst()); 
        assertEquals(6, deque.size);
        assertEquals(0, deque.front);
        assertEquals(5, deque.rear);
        for (int i = 0; i < 7; i++) {
            assertEquals("Deque structure should be maintained",
                finalOrdering[i], deque.data[i]);
        }
    }

    /** Test the removeLast method when data is empty*/
    @Test
    public void testDeuque_removeLast1(){
        MyDeque<Integer> deque = new MyDeque<>(10);
        Integer[] orig = {null, null, null, null, null, 
            null, null, null, null, null};
        initDeque(deque, orig, 0, 0, 0);
        assertEquals(null,deque.removeLast()); 
        assertEquals(0, deque.size);
        assertEquals(0, deque.front);
        assertEquals(0, deque.rear);
        assertEquals(10, deque.data.length);
    }

    /** Test the removeLast method when data is capacity
    * checks if rear wraps around
    */
    @Test
    public void testDeuque_removeLast2(){
        MyDeque<Integer> deque = new MyDeque<>(7);
        Integer[] orig = {11, 6, 1, 2,7,8,9};
        initDeque(deque, orig, 7, 1, 0);
        Integer[] finalOrdering = {null, 6, 1, 2,7,8,9};

        assertEquals(11,(int) deque.removeLast()); 
        assertEquals(6, deque.size);
        assertEquals(1, deque.front);
        assertEquals(6, deque.rear);

        for (int i = 0; i < 7; i++) {
            assertEquals("Deque structure should be maintained",
                finalOrdering[i], deque.data[i]);
        }
    }


    /** Test the expandCapacity method, when initial capcity is null*/
    @Test
    public void testDeuque_expandCapacity(){
        MyDeque<Integer> deque = new MyDeque<>(0);
        deque.front=0;
        deque.rear=0;
        deque.size=0;
        deque.expandCapacity();

        assertEquals( 10, deque.data.length);
        assertEquals( 0, deque.size);
        assertEquals( 0, deque.front);
        assertEquals(0, deque.rear);
    }

    /** Test the expandCapacity method, when data is at capacity*/
    @Test
    public void testDeuque_expandCapacity1(){

        MyDeque<Integer> deque = new MyDeque<>(7);
        Integer[] orig = {5, 6, 1, 2,7,8,9};
        Integer[] finalOrdering={2,7,8,9,5,6,1,null,null,
            null,null,null,null,null};
        initDeque(deque, orig, 7, 3, 2);
        deque.expandCapacity();

        assertEquals( 14, deque.data.length);
        assertEquals( 7, deque.size);
        assertEquals( 0, deque.front);
        assertEquals( 6, deque.rear);
        for (int i = 0; i < 14; i++) {
            assertEquals("Deque structure should be maintained",
                finalOrdering[i], deque.data[i]);
        }
    }


    // ------------ Stack---------------
    /** Test the getsize method of the stack*/
    @Test
   public void testStack_size(){
        MyStack<Integer> stack = new MyStack<>(10);
        Integer[] orig = {1, 2, 3, null, null, null, null, null, null, null};
        initDeque(stack.theStack, orig, 3, 0, 2);
        assertEquals(3, stack.size()); 

    }
    /** Test the push and pop method of the stack*/
    @Test
    public void testStack() {
        MyStack<Integer> stack = new MyStack<>(10);
        Integer[] orig = {5, 6, 1, 2,7,8,9};
        initDeque(stack.theStack, orig, 7, 6, 5);
        //push operation
        stack.push(12);
        assertEquals(Integer.valueOf(12), stack.peek());
        assertEquals( 14,stack.theStack.data.length);
        assertEquals( 8, stack.theStack.size);
        assertEquals( 0, stack.theStack.front);
        assertEquals(7, stack.theStack.rear);
        //pop operation
        stack.pop();
        assertEquals(Integer.valueOf(8), stack.peek());
        assertEquals( 14, stack.theStack.data.length);
        assertEquals( 7, stack.theStack.size);
        assertEquals( 0, stack.theStack.front);
        assertEquals( 6, stack.theStack.rear);

    }


    // ------------ queue ---------------
    /** Test the getsize method of the queue*/
    @Test
    public void testque_size(){
        MyQueue<Integer> queue = new MyQueue<>(10);
        Integer[] orig = {1, 2, 3, null, null, null, null, null, null, null};
        initDeque(queue.theQueue, orig, 3, 0, 2);

        assertEquals(3, queue.theQueue.size()); 
    }
    /** Test the enque and deque method of the queue*/
    @Test
    public void testQueueEnqueue() {
        MyQueue<Integer> queue = new MyQueue<>(10);
        Integer[] orig = {5, 6, 1, 2,7,8,9};
        initDeque(queue.theQueue, orig, 7, 6, 5);
        //enque
        queue.enqueue(3);
        assertEquals(Integer.valueOf(9), queue.peek());
        assertEquals(14,queue.theQueue.data.length);
        assertEquals( 8, queue.theQueue.size);
        assertEquals( 0, queue.theQueue.front);
        assertEquals( 7, queue.theQueue.rear);
        //deque
        queue.dequeue();
        assertEquals(Integer.valueOf(5), queue.peek());
        assertEquals(14,queue.theQueue.data.length);
        assertEquals( 7, queue.theQueue.size);
        assertEquals( 1, queue.theQueue.front);
        assertEquals( 7, queue.theQueue.rear);

    }


}