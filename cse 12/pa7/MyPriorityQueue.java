/*
* Name: govinda sahoo
* Email: gsahoo@ucsd.edu
* PID: A16378764
* Sources used: tutors
*
* This file is used to create the class Mypriotityqueue
* by implementing the MyminHeap and its methods
*/


import java.util.Collection;

/**
* This class implements Mypriority queue
* by using a MinHeap ADT
*Heap= stores the priorityqueue
*/
public class MyPriorityQueue<E extends Comparable<E>> {
    //instance variable
    protected MyMinHeap<E> heap;

    /**
    * This Constructor to initializes MyPriotyQueue
    * heap is initialzied as a new minHeap
    *                        
    */
    public MyPriorityQueue(){
        heap= new MyMinHeap<E>();
    }

    /**
    * This Constructor to initializes MyPriotyQueue
    * with the collection
    * by calling 
    * @param collection the data type and collection to be intialized
    *                        
    */

    public MyPriorityQueue(Collection<? extends E> collection){
        heap=new MyMinHeap<E>(collection);
    }
    /**
    * This method adds a element to the priorityQueue 
    * by calling the insert method
    * @param element to be added to the heap
    */
    public void push(E element){
        heap.insert(element);
    }
    /**
    * This method gets the highest priority element 
    *by calling the getMin method
    * and returns it
    * @return the root node
    */
    public E peek(){
        if(heap.size()>0){
        return heap.getMin();
        }
        else{
            return null;
        }
    }
    /**
    * This method removes the element with the highest priority in the Queue 
    * by calling the remove method
    * and returns the element removed
    * @return the element removed
    */
    public E pop(){
        if(heap.size()>0){
        E temp=peek();
        heap.remove();
        return temp;
        }
        else{
            return null;
        }
    }
    /**
    * This method gets the size of the heap of the priorityque
    * by calling the size method 
    * and returns it
    * @return The size of the priorityqueue
    */
    public int getLength(){
        return heap.size();
    }

    /**
    * This method Clears the priorityQueue 
    *such that there are no elements in it
    * by calling the clear method
    */
    public void clear(){
        heap.clear();
    }

}
