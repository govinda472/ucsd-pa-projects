
/**
 * IMPORTANT: Do not change the method headers. Your tests will be run against
 * good and bad implementations of MyLinkedList. You will only receive points
 * if your test passes when run on a good implementation and fails for the
 * corresponding bad implementation.
 */

import static org.junit.Assert.*;
import org.junit.*;

public class MyLinkedListCustomTester {


//copied stuff
private MyLinkedList<Integer> null_list;
private MyLinkedList<String> StringList;
private String[] strData = {"a","b","c","d","e"};


/**
 * Standard Test Fixture. An empty list, a list with one entry (0) and
 * a list with several entries (0,1,2)
 */
@Before
public void setUp() {
	null_list = new MyLinkedList<>();
	StringList = new MyLinkedList<>();

}

/**
 * One way to test the code is to manually populate the list to ensure 
 * the internal state is correct. In this way, potential errors in add() 
 * won't cause failures for other methods.
 *
 * However, for you own custom tester, feel free to populate the list 
 * in whatever way you want.
 */
private void populateLinkedList() {
	MyLinkedList<String>.Node node0 =  
		this.StringList.new Node(this.strData[0]);
	MyLinkedList<String>.Node node1 =  
		this.StringList.new Node(this.strData[1]);
	MyLinkedList<String>.Node node2 =  
		this.StringList.new Node(this.strData[2]);
	MyLinkedList<String>.Node node3 =  
		this.StringList.new Node(this.strData[3]);
	MyLinkedList<String>.Node node4 =  
		this.StringList.new Node(this.strData[4]);

	this.StringList.head.next = node0;
	node0.prev = this.StringList.head;
	node0.next = node1;
	node1.prev = node0;
	node1.next = node2;
	node2.prev = node1;
	node2.next= node3;
	node3.prev= node2;
	node3.next=node4;
	node4.prev=node3;

	node4.next = this.StringList.tail;
	this.StringList.tail.prev = node4;
	this.StringList.size = 5;
}



//I copied and modified the variables from Listpublictester that was provided for this pa. I consulted with the tutors before doing it. 


	

	/**
	 * Aims to test the add(E data) method with a valid argument.
	 */
	@Test
	public void testCustomAdd() {
		
		this.populateLinkedList();


        // Obtain the reference to the node before tail
        MyLinkedList<String>.Node oldLastNode = this.StringList.tail.prev;
        this.StringList.add("f");

		//checks the data is properly stored and the tail and last node(prior to the add) points to the current last node
		assertEquals("f", this.StringList.tail.prev.data);
        assertEquals("f", oldLastNode.next.data);

		//checks if the tail is accessible from the old last node visa versa
		assertEquals(oldLastNode.data,  this.StringList.tail.prev.prev.data);
		assertEquals(this.StringList.tail.data,  oldLastNode.next.next.data);

		//checks pointer tail and last node(prior to the add) points to the current last node
		assertSame(oldLastNode, this.StringList.tail.prev.prev);
        assertSame(this.StringList.tail.prev.next, this.StringList.tail);

		//makessure the old last node still has the original data
		assertEquals("e", oldLastNode.data);

		assertEquals(6, this.StringList.size);//checks the size of the list has been incremented

		assertEquals(true, this.StringList.add("f"));



	}

	/**
	 * Aims to test the add(int index, E data) method.
	 * Add a valid argument to the beginning of MyLinkedList.
	 */
	@Test
	public void testCustomAddIdxToStart() {
		this.populateLinkedList();

		MyLinkedList<String>.Node oldfirstNode = this.StringList.head.next;
        this.StringList.add(0,"f");

		//checks the data is properly stored and the head and the first node(prior to the add) points to the current first node
		assertEquals("f", this.StringList.head.next.data);
        assertEquals("f", oldfirstNode.prev.data);

		//checks pointer tail and last node(prior to the add) points to the current last node
		assertSame(oldfirstNode, this.StringList.head.next.next);
		assertSame(oldfirstNode.prev.prev, this.StringList.head);

		assertEquals(6, this.StringList.size);//checks the size of the list has been incremented
	}

	/**
	 * Aims to test the add(int index, E data) method.
	 * Add a valid argument to the middle of MyLinkedList.
	 */
	@Test
	public void testCustomAddIdxToMiddle() {
		this.populateLinkedList();
		int midpoint=2;

		MyLinkedList<String>.Node previous=this.StringList.getNth(midpoint-1);
		MyLinkedList<String>.Node after=this.StringList.getNth(midpoint);

        this.StringList.add(midpoint,"f");
		MyLinkedList<String>.Node current=this.StringList.getNth(midpoint);

		assertEquals("f", previous.next.data);
		assertEquals("f", after.prev.data);
		assertEquals(previous.data, current.prev.data);
		assertEquals(after.data, current.next.data);

		assertEquals(6, this.StringList.size);//checks the size of the list has been incremented
	}

	/**
	 * Aims to test the remove(int index) method. Remove from an empty list.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testCustomRemoveFromEmpty() {
		
		this.null_list.head.next=this.null_list.tail;
		this.null_list.tail.prev=this.null_list.head;
		this.null_list.size=0;

		null_list.remove(25);
		// error not working figure it out
	}

	/**
	 * Aims to test the remove(int index) method.
	 * Remove a valid argument from the middle of MyLinkedList.
	 */
	@Test
	public void testCustomRemoveFromMiddle() {
		this.populateLinkedList();
		int midpoint=2;
		// gets the ajacent neighbouring nodes
		MyLinkedList<String>.Node previous=this.StringList.getNth(midpoint-1);
		MyLinkedList<String>.Node after=this.StringList.getNth(midpoint+1);

		this.StringList.remove(midpoint);

		//checks if the pointer of the nodes between the midpoint towards each other now that the midpoint node has been removed 
		assertEquals(after.data, previous.next.data);
		assertEquals(previous.data, after.prev.data);

		//checks the size of the list is reduced by 1
		assertEquals(4, this.StringList.size);
	}

	/**
	 * Aims to test the set(int index, E data) method.
	 * Set an out-of-bounds index with a valid data argument.
	 */
	@Test (expected = IndexOutOfBoundsException.class)
	public void testCustomSetIdxOutOfBounds() {
		this.populateLinkedList();
		this.StringList.set(8,"rat"); // inputs a outindex value for index to trigger the indexoutofbounds exception
	}
}
