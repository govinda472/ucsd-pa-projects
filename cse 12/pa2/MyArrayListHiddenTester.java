import static org.junit.Assert.*;

import org.junit.*;

public class MyArrayListHiddenTester {
    // Do not change the method signatures!
    /**
     * This sets up the test fixture. JUnit invokes this method before
     * every testXXX method. The @Before tag tells JUnit to run this method
     * before each test 
     */
    //I copied the content(The varaibles and conent in SetUp()) below from MYarrayListPublicTest.java and modified it, the tutor told me it was alright to copy it
     static final int DEFAULT_CAPACITY = 5;
     static final int MY_CAPACITY = 8;
 
     Object[] arr = new Object[25];
     Integer[] arrInts = { 1, 2, 3, 4, 5, 6, 7, 8 };
     Integer[] arrNonEmptyInts = {1, null, null};
     String[] Arraywith_null={"1","rat","car","cat",null,null};
     String[] Slist={"a","b","c","d",null,"e"};
     String[] addlist={"a","b"};


     static final int invalid=-5;
     // NOTE: LIST OF SIZE ONE
 
     private MyArrayList null_list,Arraywith_null_L,Slist_L,addlist_L;







    @Before
    public void setUp() throws Exception {
        
        
        null_list=new MyArrayList<>(null);
        Arraywith_null_L=new MyArrayList<>(Arraywith_null);
        Slist_L=new MyArrayList<String>(Slist);
        addlist_L=new MyArrayList<String>(addlist);

    }

    /**
     * Aims to test the constructor when the input argument
     * is not valid
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorInvalidArg(){
      
    
     new MyArrayList(invalid);
    }

    /**
     * Aims to test the constructor when the input argument is null
     */
    @Test
    public void testConstructorNullArg(){
        
    assertArrayEquals(new Object[5], null_list.data);
    assertEquals(5,null_list.data.length);
    }

    /**
     * Aims to test the constructor when the input array has null elements
     */
    @Test
    public void testConstructorArrayWithNull(){
       assertArrayEquals(Arraywith_null_L.data, Arraywith_null);
       assertEquals(Arraywith_null_L.size, 6); 
    }

    /**
     * Aims to test the append method when an element is added to a full list
     * Check reflection on size and capacity
     */
    @Test
    public void testAppendAtCapacity(){
        Slist_L.append("f");
    
        assertArrayEquals(new String[]{"a","b","c","d",null,"e","f",null,null}, Slist_L.data);
        assertEquals(Slist_L.size, 7);
        assertEquals(Slist_L.data.length, 9);
    }

    /**
     * Aims to test the append method when null is added to a full list
     * Check reflection on size and capacity
     */
    @Test
    public void testAppendNull(){
        Slist_L.append(null);
        assertArrayEquals(new String[]{"a","b","c","d",null,"e",null,null,null}, Slist_L.data);
        assertEquals(Slist_L.size, 7);
        assertEquals(Slist_L.data.length, 9);
    }

    /**
     * Aims to test the prepend method when an element is added to a full list
     * Check reflection on size and capacity
     */
    @Test
    public void testPrependAtCapacity(){

        Slist_L.prepend("f");
    
        assertArrayEquals(new String[]{"f","a","b","c","d",null,"e",null,null}, Slist_L.data);
        assertEquals(Slist_L.size, 7);
        assertEquals(Slist_L.data.length, 9);
    }
        
    
    
    /**
     * Aims to test the prepend method when a null element is added
     * Checks reflection on size and capacity
     * Checks whether null was added successfully
     */
    @Test
    public void testPrependNull(){

        Slist_L.prepend(null);
        assertArrayEquals(new String[]{null,"a","b","c","d",null,"e",null,null}, Slist_L.data);
        assertEquals(Slist_L.size, 7);
        assertEquals(Slist_L.data.length, 9);
    }
    
    /**
     * Aims to test the insert method when input index is out of bounds
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testInsertOutOfBound(){
        Slist_L.insert(15, "mice");

    }

    /**
     * Insert multiple (eg. 1000) elements sequentially beyond capacity -
     * Check reflection on size and capacity
     * Hint: for loop could come in handy
     */
    @Test
    public void testInsertMultiple(){
        addlist_L.insert(1, "m");
        addlist_L.insert(1, "m2");
        assertArrayEquals(new String[]{"a","m2","m","b"}, Slist_L.data);
    }

    /**
     * Aims to test the get method when input index is out of bound
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetOutOfBound(){
        Slist_L.get(25);
    }

    /**
     * Aims to test the set method when input index is out of bound
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetOutOfBound(){
        Slist_L.set(30,"rat");
    }

    /**
     * Aims to test the remove method when removing multiple items from a list
     */
    @Test
    public void testRemoveMultiple(){
        Slist_L.remove(2);
        Slist_L.remove(2);
        Slist_L.remove(2);
        Slist_L.remove(2);
        assertArrayEquals(new String[]{"a","b",null,null,null,null}, Slist_L.data);
        assertEquals(2, Slist_L.size);
        assertEquals(6, Slist_L.data.length);
    }

    /**
     * Aims to test the remove method when input index is out of bound
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveOutOfBound(){
        Slist_L.remove(60);
    }

    /**
     * Aims to test the expandCapacity method when 
     * requiredCapacity is strictly less than the current capacity
     */
    @Test(expected= IllegalArgumentException.class)
    public void testExpandCapacitySmaller(){
       Slist_L.expandCapacity(3);
    }

    /**
     * Aims to test the expandCapacity method when 
     * requiredCapacity is greater than current capacity+3 and default capacity
     */
    @Test
    public void testExpandCapacityLarge(){
        
        Slist_L.expandCapacity(20);
        assertEquals(Slist_L.data.length, 20);
    }
    

}
