/*
* Name: govinda sahoo
* Email: gsahoo@ucsd.edu
* PID: A16378764
* Sources used: tutors
*
* This file is used to create the class MyMinHeap
* which creates a minHeap using a array
* and implements methods for transersing and modifying it
*/
import java.util.ArrayList;
import java.util.Collection;

/**
* This class implements MyminHeap and methods for a MinHeap
* by using Array
*
* data=stores the elements of the MinHeap
*/
public class MyMinHeap<E extends Comparable<E>> implements MinHeapInterface<E> {
    //instance variables
    protected ArrayList<E> data;

    /**
    * This Constructor initializes the MinHeap
    * heap is initialzied as a new minHeap
    *                        
    */
    public MyMinHeap(){
        data= new ArrayList<E>();
    }

    /**
    * This Constructor initializes MyminHeap with the collection
    * @param collection the data type and collection to be intialized
    *                        
    */
    public MyMinHeap(Collection<? extends E> collection){

        //Throws NullPointerException if collection is valid
        if(collection==null){
            throw new NullPointerException();
        }
        for (E e: collection){
            if(e==null){
                throw new NullPointerException();
            }
        }

        data= new ArrayList<E>(collection);
        //orders the MinHeap
        for(int i=0; i<this.data.size(); i++ ){
            percolateDown(i);
        }
        for(int i=this.data.size()-1; i>0; i-- ){
            percolateUp(i);
        }
    }


    //Helper Methods:
    /**
    * This method is to swap two nodes within the MinHeap
    * @param from the origin element
    * @param to the new index of origin element 
    */
    protected void swap(int from, int to){
        E temp=(E) this.data.get(from);
        E temp2=(E) data.get(to);

        data.set(from, temp2);
        data.set(to, temp);
    }

    /**
    * This method gets the Indexed node's parent index
    * and returns it
    * @param index index of the element
    * @return the parent's index
    */
    protected static int getParentIdx(int index){	
        final int temp=((index-1)/2);
        return temp;
    }

    /**
    * This method gets the Indexed node's leftChild index
    * and returns it
    * @param index index of the element
    * @return the leftChild index
    */
    protected static int getLeftChildIdx(int index){

        final int temp=((index)*2)+1;
        return temp;
   
    }
    /**
    * This method gets the Indexed node's rightChild index
    * and returns it
    * @param index index of the element
    * @return the RightChild index
    */
    protected static int getRightChildIdx(int index){
        final int temp=((index)*2)+2;
        return temp;	
    }
    /**
    * This method gets the Indexed node's smallest child
    * and returns it
    * it does it by comparing values of the left and right child
    * @param index index of the element
    * @return the index of the smallest child node
    */
    protected int getMinChildIdx(int index){
        int temp_rightidx = getRightChildIdx(index);
        int temp_leftidx = getLeftChildIdx(index);
        //if there are no children Nodes
        //&&(temp_rightidx>size()
        if(temp_leftidx>size()-1){
            return -1; 
        }
        // if there are Children Nodes
        else if((temp_leftidx<size())&&(temp_rightidx<size())){

            E temp_right=(E) data.get(temp_rightidx);
            E temp_left=(E) data.get(temp_leftidx);
            int differ= temp_right.compareTo(temp_left);
  
            if( differ>0){
                return temp_leftidx;
            }
            else if(differ<0){
                return temp_rightidx;
            }
            else{
                return temp_leftidx;
            }

        }
        else{
            return temp_leftidx;
        }
    }
    /**
    * This method is used to shift up the indexed node 
    * up the heap so it reaches its correct position 
    * @param index The index of the Node to be shifted up
    */

    protected void percolateUp(int index){
        E element= (E) data.get(index);
        E parent=(E) data.get(getParentIdx(index));
        //swaps with the parent while parent is bigger
        while((element.compareTo(parent)<0)&&(index>0)){
            swap(index, getParentIdx(index));
            index=getParentIdx(index);
            parent=(E) data.get(getParentIdx(index));
        }
    }
    /**
    * This method is used to shift down the indexed node 
    * down the heap so it reaches its correct position 
    * @param index The index of the Node to be shifted down
    */
    protected void percolateDown(int index)	{

        E element= (E) data.get(index);
        int left_idx=getLeftChildIdx(index);
        E temp;
        int min;
        while(data.size()>left_idx){
            min= getMinChildIdx(index);
            //checks if there are more elements in the Heap
            if(min==-1){
                break;
            }
            temp=data.get(min);
            //compares the element with indexed element
            if(element.compareTo(temp)>0){
                swap(index,min);
                index=min;
            }
            else{
                break;
            }
            left_idx=getLeftChildIdx(index);
        }    
    }
    /**
    * This method deletes the node at the Index
    * by swaping the last element with it and 
    *  tranversing the heap so it reaches it correct positon
    * @param index index of the element
    * @return the element of the removed node
    */
    protected E deleteIndex(int index){
        E temp=data.get(index);
        if(size()==1){
            data.remove(0);
            return temp;
        }
        else if(index==size()-1){
            data.remove(index);
            return temp;
        }
        else{
            swap(index, size()-1);
            data.remove(size()-1);
            percolateDown(index);
            percolateUp(index);
            return temp;
        }

    }

    //Core Methods:
    /**
    * This method is used to insert the element to the Heap
    * it is percolated up the heap
    * @param element the elemented to be added to the Heap
    */
    public void insert(E element){
        //Throw a NullPointerException if element is invalid.
        if(element.equals(null)){
            throw new NullPointerException();
        }
        data.add(element);
        percolateUp(size()-1);
    }

    /**
    * This method gets the smallest value in the heap
    * and returns it
    * @return the Root of the Heap
    */
    public E getMin(){
        if(size()>0){
            return (E) data.get(0);
        }
        else{
            return null;
        }

    }
    /**
    * This method removes the root node
    * and returns it
    * @return the Root of the Heap
    */
    @Override
    public E remove(){
        E temp=getMin();
        if(size()>0){ deleteIndex(0);}
        return temp;
    }
    /**
    * This method gets the size of the heap
    * and returns it
    * @return the size of the heap
    */
    @Override
    public int size(){
        int temp=data.size();
        return temp;
    }
    /**
    * This method Clears the Heap by removing all elements in it
    * making it null
    */
    @Override
    public void clear(){
        this.data.clear();
    }

}
