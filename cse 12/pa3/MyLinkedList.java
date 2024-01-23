/*
 * Name: govinda charan sahoo
 * Email: gsahoo@ucsd.edu
 * PID: A16378764
 * Sources used: tutors
 * It creates a doubly linked list 
 * 
 */






import java.util.AbstractList;

public class MyLinkedList<E> extends AbstractList<E> {

    int size;
    Node head;
    Node tail;

    /**
     * A Node class that holds data and references to previous and next Nodes.
     */
    protected class Node {
        E data;
        Node next;
        Node prev;

        /** 
         * Constructor to create singleton Node 
         * @param element Element to add, can be null	
         */
        public Node(E element) {
            // Initialize the instance variables
            this.data = element;
            this.next = null;
            this.prev = null;
        }

        /** 
         * Set the parameter prev as the previous node
         * @param prev new previous node
         */
        public void setPrev(Node prev) {
            this.prev = prev;		
        }

        /** 
         * Set the parameter next as the next node
         * @param next new next node
         */
        public void setNext(Node next) {
            this.next = next;
        }

        /** 
         * Set the parameter element as the node's data
         * @param element new element 
         */
        public void setElement(E element) {
            this.data = element;
        }

        /** 
         * Accessor to get the next Node in the list 
         * @return the next node
         */
        public Node getNext() {
            return this.next;
        }
        /** 
         * Accessor to get the prev Node in the list
         * @return the previous node  
         */
        public Node getPrev() {
            return this.prev;
        } 
        /** 
         * Accessor to get the Nodes Element 
         * @return this node's data
         */
        public E getElement() {
            return this.data;
        } 
    }

    //  Implementation of the MyLinkedList Class
    /** Only 0-argument constructor is defined */

    public MyLinkedList() {
        size=0;
        head= new Node(null);
        tail=new Node(null);
       head.setNext(tail);
       head.setPrev(null);
       tail.setNext(null);
       tail.setPrev(head);
   
       
     
   }
 /**
     * This method returns the size of the linked list
     */
   @Override 
   public int size() {
       return size; //returns the current size
   }
   /**
     * This method returns the data value of a node from a index point in the linked list
     * it implments the getNth(index) function to get the node
     * then getelement() function to get the data from the node 
     */

   @Override //completed
   public E get(int index) {
       if((index<0)||(index>=size)) 
       {throw new IndexOutOfBoundsException();}//Throws a index out of bounds if index is out of range

       Node current=getNth(index);         //gets node at the certain index        
       E current_data=current.getElement(); //extracts the data element from the node

       return current_data; //return the data from the node
   }


/**
     * This method add the data value to the list at the specificed index 
     * it creates a new node and links the previous and current node at the index to it
     * then increments the size of the list
     */
   @Override
   public void add(int index, E data) {
       if(data==null) 
       {throw new NullPointerException();} // Throws a null pointer exception if the data is null
       if((index<0)||(index>size)) 
       {throw new IndexOutOfBoundsException();} //Throws a index out of bounds if index is out of range

   Node newNode=new Node(data); 
   

   if(size>0){ //if the list is not null
    Node current_next=getNth(index); //gets the node that at current index
       if(index>0){ // if the add isn't to the begining of the list and this segment will establish a dual link between the new node
       Node current_prev=getNth((index-1));//gets the node that before the current index 
       newNode.setPrev(current_prev);
       current_prev.setNext(newNode);
       }
       else{// if the add is to the begining of the list, it will connect the head to the new node
       head.setNext(newNode);
       newNode.setPrev(head);
       }
       
        //shifts node at the index and establishes the link between them
       current_next.setPrev(newNode);
       newNode.setNext(current_next);  
   }

   else{//if the list is empty this segment will establish a dual link between the new node and the head and tail
       tail.setPrev(newNode); 
       newNode.setNext(tail);
       head.setNext(newNode);
       newNode.setPrev(head);
   }
       size++; // Increment the size of the list
   }

/**
     * This method add the data value to the list at the end of the list
     * it creates a new node and links the previous and current node at the index to it
     * then increments the size of the list
     */

   public boolean add(E data) {
       if(data==null) 
       throw new NullPointerException(); // Throws a null pointer exception if the data is null

       Node newNode=new Node(data);
       tail.setPrev(newNode);
       newNode.setNext(tail);

       
       
       //if the list length>0 then it assign the pointers of last element prior to the operation to newly added element
       if(size>0){ 
           Node current=getNth(size-1);
           newNode.setPrev(current);
           current.setNext(newNode);

       }
       // if the list is empty it sets the head and tail pointers towards the new element added 
       else{  
           head.setNext(newNode);
           newNode.setPrev(head);

       }

       size++;
       return true;
   }

/**
     * This method set the data value to the list at the specificed index, and then returns the old data vlue at the index
     * it gets the node at the index and and updates its data value
     * then returns the olds values
     * 
     */

   public E set(int index, E data) {
       if(data==null) throw new NullPointerException(); // Throws a null pointer exception if the data is null
       if((index<0)||(index>=size)) throw new IndexOutOfBoundsException(); //Throws a index out of bounds if index is out of range

       E temp_returndata=get(index); //stores old data value

       Node current=getNth(index);
       current.setElement(data); // updates the data values at the index node

       return temp_returndata; 
   }

/**
     * This method removes the node at the index and returns the data stores in the node that is removed
     * it extracts the data from the  node 
     * then removes the links to the node from its ajacent nodes so it can't be accessed through the list
     * then directly connects its neighbour nodes so they become ajacent 
     * then returns the value of the node
     */

   public E remove(int index) {
       if(index<0||index>=size) {
           throw new IndexOutOfBoundsException();
       } //Throws a index out of bounds if index is out of range
       

       E temp_returndata=get(index);

       Node temp_prev=null;

       Node temp_next=null;

       if(index>0)temp_prev=getNth(index-1);
       else temp_prev=getNth(0);

       if((index+1)<size)temp_next=getNth(index+1);
       else temp_next=getNth(index);


       if(index<(size-1)){
           
           temp_prev.setNext(temp_next);}
       
       else {
           temp_prev.setNext(tail);
           tail.setPrev(temp_prev);
       }

       if(index>0) {
           
           temp_next.setPrev(temp_prev);}
       else {
           temp_next.setPrev(head);
           head.setNext(temp_next);
       }

       size--; //reduces the size of the list
       return (E) temp_returndata;
   }

/**
     * This method clears the list and removes all the elements from it 
     * it makes the size=0, and the head node point to the tail node visa versa
     */
   public void clear() {
       head.setNext(tail);
       tail.setPrev(head);
       size=0;
   }

 /**
     * This method checks if the list is empty
     */

   public boolean isEmpty() {
       if(size>0) return false;
       else return true; 

   }
/**
     * This method gets the Nth node from the list and returns it 
     * it loops through all the nodes until it gets to the node at the index position in the list
     * then returns that value
     */

   protected Node getNth(int index) {
       if((index<0)||(index>=size)) throw new IndexOutOfBoundsException(); //Throws a index out of bounds if index is out of range
       
       //Get data of type E within the node at position index.
       Node cur_pointer=head.getNext();
       for(int i=0; i<index; i++){
  
           cur_pointer=cur_pointer.getNext();
       }
       return cur_pointer;
       
   }
}
