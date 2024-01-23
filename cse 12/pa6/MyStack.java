
/*
 * Name: govinda sahoo
 * Email: gsahoo@ucsd.edu
 * PID: A16378764
 * Sources used: tutors
 * This file creates a stack ADT using methods from MyDeque
 * It has the functions of a stack
 */



/**
 * This class implements the Stack ADT using a MyDeque instance variable called
 * theStack.
 */
public class MyStack<E> implements StackInterface<E> {
    MyDeque<E> theStack;

    /**
     * Constructor to create new MyStack that holds a MyDeque.
     *
     * @param initialCapacity The max amount of elements this data structure
     *                        can hold.
     */
    public MyStack(int initialCapacity) {
        theStack= new MyDeque<E>(initialCapacity);

    }

    /**
     * Checks if the stack is empty.
     *
     * @return True if there are no elements in the stack, false otherwise.
     */
    @Override
    public boolean empty() {
        //checks if stack is empty
        if(theStack.size<1){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Adds the specified element to the top of this StackInterface.
     *
     * @param element the element to add to the stack
     */
    @Override
    public void push(E element) {
        theStack.addLast(element);
    }

    /**
     * Removes the element at the top of this StackInterface.
     * Returns the element removed, or null if there was no such element.
     *
     * @return the element removed, or null if the size was zero.
     */
    @Override
    public E pop() {
        return theStack.removeLast();
    }

    /**
     * Returns the element at the top of this stack, or null if there was no
     * such element.
     *
     * @return the element at the top, or null if the size was zero
     */
    @Override
    public E peek() {
        return theStack.peekLast();
    }

    /**
     * Returns the number of elements in this stack.
     *
     * @return the number of elements in this stack.
     */
    @Override
    public int size() {
        return theStack.size();
    }

}
