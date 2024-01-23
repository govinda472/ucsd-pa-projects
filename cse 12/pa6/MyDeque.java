
/*
 * Name: govinda sahoo
 * Email: gsahoo@ucsd.edu
 * PID: A16378764
 * Sources used: tutors
 * This files creates 
 */


/**
 * This class creates the class MyDeque which a object type E
 * array that can be modified from both sides, It has the
 * functions of a array but specified for stacks and Queues
 * which is implemented by MyStack and MyQueue
 * 
 * data= stores the values in a array
 * size= stores the number of elements in the array
 * rear= stores the location of the last value
 * front=stores the location of the first value
 */

public class MyDeque<E> implements DequeInterface<E>{
    // Instance variables
    Object[] data;
    int size;
    int rear;
    int front;

    /**
     * Constructor to create new array that holds a MyDeque.
     *
     * @param initialCapacity The max amount of elements this data structure
     *                        can hold.
     */
    public MyDeque(int initialCapacity){
        //checks if invalid arguement
        if(initialCapacity<0){
            throw new IllegalArgumentException();
        }
        //initializes the Deque
        this.data=new Object[initialCapacity];
        this.size=0;
        this.rear=0;
        this.front=0;
    }
    /**
    * gets the size
    *
    * @return Returns the number of elements that exist in the deque.
    */
    public int size(){
        return size;
    }

    /**
     *doubles the capacity of the array list by
     *creating a new array twice the length 
     *and copies the elements over in contiguous way
     * @param data the length of data is doubled 
     */
    public void expandCapacity(){
        //If the capacity is 0, sets the capacity 10.
        if(data.length<1){
            final int cap=10;
            data= new Object[cap];
            size=0;
            rear=0;
            front=0;
        }
        else
        {
            final int new_len=2;
            Object[] temp_data= new Object[data.length*new_len];
            //transferring data to new array
            for(int i=0; i<size; i++){
                temp_data[i]=data[(front+i)%data.length];
            }
            //setting the variables
            data=temp_data;
            front=0;
            if(size>0) {rear=size-1;}
            else{rear=0;}
        }
    }

    /**
    * Adds the specified element to the front of the Deque
    * after adding it adjusts the front value
    * @param element the element to add to the Deque
    */
    public void addFirst(E element){
        //checks if arguement is valid
        if(element==null){
            throw new NullPointerException();
        }
        //checks if capacity needs to be increased
        if(data.length<=size||data.length==0){
            expandCapacity();
        }
        
        if(size>0){
            //adds to the front of Deque and updates front
            int index=(front-1+data.length)%data.length;
            data[index]=element;
            front=index;
            size++;
        }
        //if Deque is empty
        else{ 
            data[front]=element;
            rear=front;
            size++;
        }
    }

    /**
     * Adds the specified element to the rear of the Deque
     * after adding it adjusts the rear value
     * @param element the element to add to the Deque
     */
    public void addLast(E element){
        //checks if arguement is valid
        if(element==null){
            throw new NullPointerException();
        }
        //checks if capacity needs to be increased
        if(data.length==size){
            expandCapacity();
        }
        if(size>0){
            //adds to the rear of Deque and updates rear
            rear=(rear+1)%data.length;
            data[rear]=element;
            size++;
        }
        else{ //if Deque is empty
            data[rear]=element;
            size++;
        }
    }

    /**
     * Removes the element at the front of The Deque
     * Returns the element removed, or null if there was no such element.
     *
     * @return the element removed, or null if the size was zero.
     */
    public E removeFirst(){

        if(size>0){
            //updates the instance variables
            E temp;
            temp=(E)data[front];
            data[front]=null;
            if(size>1){
                front=((front+1)%data.length);
            }
            if(size==1){
                front=rear;
            }
            size--;
            return temp;
        }
        //no elements in Deque
        else {
            return null;
        }
    }

    /**
     * Removes the element at the rear of The Deque
     * Returns the element removed, or null if there was no such element.
     *
     * @return the element removed, or null if the size was zero.
     */
    public E removeLast(){
        if(size>0){
            //updates the instance variables
            E temp;
            temp=(E) data[rear];
            data[rear]=null;
            if(size>1){
                rear=((rear-1)+data.length)%data.length;
            }
            if(size==1){
                rear=front;
            }
            size--;
            return temp;
        }
        //no elements in Deque
        else {
            return null;
        }
    }

    /**
     * Returns the element at the front of the deque, 
     * or null if there was no such element.
     *
     * @return the element at the front, or null if the size was zero
     */
    public E peekFirst(){
        if(size>0&&data.length>0)
        return((E) data[front]);
        else return null;
    }

    /**
     * Returns the element at the rear of the deque, 
     * or null if there was no such element.
     *
     * @return the element at the rear, or null if the size was zero
     */
    public E peekLast(){
        if(size>0&&data.length>0)
        return((E) data[rear]);
        else return null;
    }


}
